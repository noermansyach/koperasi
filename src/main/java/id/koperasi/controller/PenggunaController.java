package id.koperasi.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.koperasi.bean.PenggunaBean;
import id.koperasi.entity.Pengguna;
import id.koperasi.exception.ApplicationException;
import id.koperasi.service.PenggunaService;

@Controller
public class PenggunaController {
	@Autowired
	private PenggunaService penggunaService;
	
	/*
	 * Menampilkan halaman kategori pinjaman
	 */
	
	@PostMapping(value = "/login")
	public ModelAndView login(HttpSession session, RedirectAttributes attribute, @Valid PenggunaBean penggunaBean) {
		ModelAndView mv = new ModelAndView();
		try {
			Pengguna user = penggunaService.login(penggunaBean.getNamaPengguna(), penggunaBean.getPassword());
			session.setAttribute("token", user.getToken());
			session.setAttribute("tipePengguna", user.getTipeAnggota());
			mv.setViewName("redirect:/kategori_pinjaman");
			attribute.addFlashAttribute("user", user);
		} catch (ApplicationException e) {
			attribute.addFlashAttribute("nama", penggunaBean.getNamaPengguna());
			attribute.addFlashAttribute("message", e.getMessage());
			mv.setViewName("redirect:/");
		}
		return mv;
	}
	
	@GetMapping("/logout")
	public ModelAndView logOut(HttpSession session, RedirectAttributes attribute, @RequestParam String token)  {
		ModelAndView mv = new ModelAndView();
		if (token == null || token.equals("")) {
			mv.setViewName("redirect:/login");
			attribute.addFlashAttribute("message", "Anda harus login untuk melihat halaman ini");
			return mv;
		}
		try {
			penggunaService.logout(token);
			session.invalidate();
		} catch (ApplicationException e) {
			attribute.addFlashAttribute("message", e.getMessage());
		}
		
		mv.setViewName("redirect:/login");
		return mv;
	}
	
	@GetMapping("/pengguna")
	public ModelAndView pengguna(HttpSession session, RedirectAttributes attribute) {
		ModelAndView mv = new ModelAndView();
		String token = (String) session.getAttribute("token");
		int tipeUser = (int) session.getAttribute("tipePengguna");
		if (token == null || token.equals("")) {
			mv.setViewName("redirect:/login");
			attribute.addFlashAttribute("message", "Anda harus login untuk melihat halaman ini");
			return mv;
		}
		List<Pengguna> listPengguna;
		if (tipeUser == 2) {
			listPengguna = penggunaService.getAllAnggota();
		} else {
			listPengguna = penggunaService.getAllPengguna();
		}
		mv.getModel().put("listPengguna", listPengguna);
		mv.setViewName("/masterdata/pengguna");
		return mv;
	}
	
	@GetMapping("/add_pengguna")
	public ModelAndView addKategoriPinjaman(HttpSession session, RedirectAttributes attribute) {
		ModelAndView mv = new ModelAndView();
		String token = (String) session.getAttribute("token");
		if (token == null || token.equals("")) {
			mv.setViewName("redirect:/login");
			attribute.addFlashAttribute("message", "Anda harus login untuk melihat halaman ini");
			return mv;
		}
		mv.setViewName("/masterdata/pengguna_add");
		return mv;
	}
	
	@GetMapping("/edit_pengguna")
	public ModelAndView editKategoriPinjaman(HttpSession session, RedirectAttributes attribute, @RequestParam int id) {
		ModelAndView mv = new ModelAndView();
		String token = (String) session.getAttribute("token");
		if (token == null || token.equals("")) {
			mv.setViewName("redirect:/login");
			attribute.addFlashAttribute("message", "Anda harus login untuk melihat halaman ini");
			return mv;
		}
		Pengguna pengguna = penggunaService.getPenggunaById(id);
		mv.getModel().put("pengguna", pengguna);
		mv.setViewName("/masterdata/pengguna_edit");
		return mv;
	}
	
	@PostMapping("/save_pengguna")
	public ModelAndView saveKategoriPinjaman(HttpSession session, RedirectAttributes attributes, @Valid PenggunaBean penggunaBean) throws ParseException {
		String token = (String) session.getAttribute("token");
		try {
			penggunaService.addPengguna(token, penggunaBean.getNamaPengguna(), penggunaBean.getAlamat(), penggunaBean.getTglLhr(), penggunaBean.getTmpLhr(), 
					penggunaBean.getStatus(), penggunaBean.getNoTlp(), penggunaBean.getKet(), penggunaBean.getTipeAnggota(), penggunaBean.getPassword());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/pengguna");
		return mv;
	}
	
	@PostMapping("/update_pengguna")
	public ModelAndView updateKategoriPinjaman(HttpSession session, RedirectAttributes attribute, @Valid PenggunaBean penggunaBean) throws ParseException {
		ModelAndView mv = new ModelAndView();
		String token = (String) session.getAttribute("token");
		try {
			penggunaService.editPengguna(token, penggunaBean.getId(), penggunaBean.getNamaPengguna(), penggunaBean.getAlamat(), penggunaBean.getTglLhr(), penggunaBean.getTmpLhr(), 
					penggunaBean.getStatus(), penggunaBean.getNoTlp(), penggunaBean.getKet(), penggunaBean.getTipeAnggota());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("redirect:/pengguna");
		return mv;
	}
	
	@GetMapping("/delete_pengguna")
	public ModelAndView deleteKategoriPinjaman(HttpSession session, RedirectAttributes attributes, @Valid PenggunaBean penggunaBean) {
		String token = (String) session.getAttribute("token");
		try {
			penggunaService.deletePengguna(token, penggunaBean.getId());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/pengguna");
		return mv;
	}
}
