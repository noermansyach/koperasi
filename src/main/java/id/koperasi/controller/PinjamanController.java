package id.koperasi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.koperasi.bean.AngsuranBean;
import id.koperasi.bean.PinjamanBean;
import id.koperasi.entity.Pinjaman;
import id.koperasi.service.KategoriPinjamanService;
import id.koperasi.service.PenggunaService;
import id.koperasi.service.PinjamanService;

@Controller
public class PinjamanController {

	@Autowired
	private PinjamanService pinjamanService;
	
	@Autowired
	private PenggunaService penggunaService;
	
	@Autowired
	private KategoriPinjamanService kategoriService;
	
	@GetMapping(value = "/pinjaman")
	public ModelAndView pinjaman(HttpSession session, RedirectAttributes attribute) {
		ModelAndView mv = new ModelAndView();
		String token = (String) session.getAttribute("token");
		if (token == null || token.equals("")) {
			mv.setViewName("redirect:/login");
			attribute.addFlashAttribute("message", "Anda harus login untuk melihat halaman ini");
			return mv;
		}
		List<Pinjaman> listPinjaman;
		listPinjaman = pinjamanService.getAllPinjaman();
		mv.getModel().put("listPinjaman", listPinjaman);
		mv.setViewName("/transaksi/pinjaman");
		return mv;
	}
	
	
	@GetMapping(value = "/add_pinjaman")
	public ModelAndView addPinjaman(HttpSession session, RedirectAttributes attribute) {
		ModelAndView mv = new ModelAndView();
		String token = (String) session.getAttribute("token");
		if (token == null || token.equals("")) {
			mv.setViewName("redirect:/login");
			attribute.addFlashAttribute("message", "Anda harus login untuk melihat halaman ini");
			return mv;
		}
		mv.getModel().put("listAnggota", penggunaService.getAllAnggota());
		mv.getModel().put("listKategori", kategoriService.getAllKategoriPinjaman());
		mv.setViewName("/transaksi/pinjaman_add");
		return mv;
	}
	
	@PostMapping("/pinjaman_add")
	public ModelAndView pinjamanAdd(HttpSession session, RedirectAttributes attribute, @Valid PinjamanBean pinjamanBean, @Valid AngsuranBean angsuranBean) {
		ModelAndView mv = new ModelAndView();
		String token = (String) session.getAttribute("token");
		try {
			pinjamanService.pinjamanSave(token, pinjamanBean.getNamaPinjaman(), pinjamanBean.getPengguna(), 
					pinjamanBean.getKategoriPinjaman(), pinjamanBean.getBesarPinjaman(), pinjamanBean.getTglPengajuanPinjaman(), 
					pinjamanBean.getTglAccPinjaman(), pinjamanBean.getTglPinjaman(), pinjamanBean.getTglPelunasan(), pinjamanBean.getKet(), angsuranBean.getJatuhTempo());
			attribute.addFlashAttribute("message", "Tambah pinjaman berhasil");
			mv.setViewName("redirect:/pinjaman");
		} catch (Exception e) {
			attribute.addFlashAttribute("pinjaman", pinjamanBean);
			attribute.addFlashAttribute("message", e.getMessage());
			mv.setViewName("redirect:/add_pinjaman");
		}
		return mv;
	}
	
}
