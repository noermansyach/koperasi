package id.koperasi.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.koperasi.bean.SimpananBean;
import id.koperasi.entity.Pengguna;
import id.koperasi.entity.Simpanan;
import id.koperasi.exception.ApplicationException;
import id.koperasi.service.PenggunaService;
//import id.koperasi.entity.Pengguna;
//import id.koperasi.exception.ApplicationException;
import id.koperasi.service.SimpananService;

@Controller
public class SimpananController {

	@Autowired
	private SimpananService simpananService;
	
	@Autowired
	private PenggunaService penggunaService;
	
	@SuppressWarnings("unused")
	@GetMapping(value = "/simpanan")
	public ModelAndView simpanan(HttpSession session, RedirectAttributes attribute) throws ApplicationException {
		ModelAndView mv = new ModelAndView();
		String token = (String) session.getAttribute("token");
		if (token == null || token.equals("")) {
			mv.setViewName("redirect:/login");
			attribute.addFlashAttribute("message", "Anda harus login untuk melihat halaman ini");
			return mv;
		}
		int tipeUser = (int) session.getAttribute("tipePengguna");
		List<Simpanan> listSimpanan;
		if (tipeUser == 2) {
			listSimpanan = simpananService.getSimpananAnggota(token);
			Pengguna pengguna = penggunaService.getPenggunaByToken(token);
			mv.getModel().put("totalSimpanan", pengguna.getTotalSimpanan());
		} else {
			listSimpanan = simpananService.getAllSimpanan();
		}
		mv.getModel().put("listSimpanan", listSimpanan);
		mv.setViewName("/transaksi/simpanan");
		return mv;
	}
	
	@GetMapping(value = "/add_simpanan")
	public ModelAndView add_simpanan(HttpSession session, RedirectAttributes attribute) {
		ModelAndView mv = new ModelAndView();
		String token = (String) session.getAttribute("token");
		if (token == null || token.equals("")) {
			mv.setViewName("redirect:/login");
			attribute.addFlashAttribute("message", "Anda harus login untuk melihat halaman ini");
			return mv;
		}
		mv.getModel().put("listAnggota", penggunaService.getAllAnggota());
		mv.setViewName("/transaksi/simpanan_add");
		return mv;
	}
	
	@PostMapping("/simpanan_add")
	public ModelAndView simpananAdd(HttpSession session, RedirectAttributes attribute, @Valid SimpananBean simpananBean) throws ParseException {
		ModelAndView mv = new ModelAndView();
		String token = (String) session.getAttribute("token");
		try {
			simpananService.simpananSave(token, simpananBean.getPengguna(), simpananBean.getNamaSimpanan(), simpananBean.getTglSimpanan(), simpananBean.getJumlahTambahan(), simpananBean.getKet());
			attribute.addFlashAttribute("message", "Tambah simpanan berhasil");
			mv.setViewName("redirect:/simpanan");;
		} catch (ApplicationException e) {
			attribute.addFlashAttribute("simpanan", simpananBean);
			attribute.addFlashAttribute("message", e.getMessage());
			mv.setViewName("redirect:/add_simpanan");
		}
		
		return mv;
	}
	
}
