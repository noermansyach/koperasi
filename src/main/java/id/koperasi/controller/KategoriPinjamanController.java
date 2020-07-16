package id.koperasi.controller;

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

import id.koperasi.bean.KategoriPinjamanBean;
import id.koperasi.entity.KategoriPinjaman;
import id.koperasi.exception.ApplicationException;
import id.koperasi.service.KategoriPinjamanService;

@Controller
public class KategoriPinjamanController {
	@Autowired
	private KategoriPinjamanService kategoriPinjamanService;
	
	/*
	 * Menampilkan halaman kategori pinjaman
	 */
	
	@GetMapping("/kategori_pinjaman")
	public ModelAndView kategoriPinjaman(HttpSession session, RedirectAttributes attribute) {
		ModelAndView mv = new ModelAndView();
		String token = (String) session.getAttribute("token");
		if (token == null || token.equals("")) {
			mv.setViewName("redirect:/login");
			attribute.addFlashAttribute("message", "Anda harus login untuk melihat halaman ini");
			return mv;
		}
		List<KategoriPinjaman> listKategori;
		listKategori = kategoriPinjamanService.getAllKategoriPinjaman();
		// mv.getModel().put("token", token);
		mv.getModel().put("listKategoriPinjaman", listKategori);
		mv.setViewName("/masterdata/kategori_pinjaman");
		return mv;
	}
	
	@GetMapping("/add_kategori_pinjaman")
	public ModelAndView addKategoriPinjaman(HttpSession session, RedirectAttributes attribute) {
		ModelAndView mv = new ModelAndView();
		String token = (String) session.getAttribute("token");
		if (token == null || token.equals("")) {
			mv.setViewName("redirect:/login");
			attribute.addFlashAttribute("message", "Anda harus login untuk melihat halaman ini");
			return mv;
		}
		mv.setViewName("/masterdata/kategori_pinjaman_add");
		return mv;
	}
	
	@GetMapping("/edit_kategori_pinjaman")
	public ModelAndView editKategoriPinjaman(HttpSession session, RedirectAttributes attribute, @RequestParam int id) {
		ModelAndView mv = new ModelAndView();
		String token = (String) session.getAttribute("token");
		if (token == null || token.equals("")) {
			mv.setViewName("redirect:/login");
			attribute.addFlashAttribute("message", "Anda harus login untuk melihat halaman ini");
			return mv;
		}
		KategoriPinjaman kategoriPinjaman = kategoriPinjamanService.getKategoriPinjamanById(id);
		mv.getModel().put("kategoriPinjaman", kategoriPinjaman);
		mv.setViewName("/masterdata/kategori_pinjaman_edit");
		return mv;
	}
	
	@PostMapping("/save_kategori_pinjaman")
	public ModelAndView saveKategoriPinjaman(HttpSession session, RedirectAttributes attributes, @Valid KategoriPinjamanBean kategoriPinjamanBean) {
		String token = (String) session.getAttribute("token");
		try {
			kategoriPinjamanService.addKategoriPinjaman(token, kategoriPinjamanBean.getNamaPinjaman());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/kategori_pinjaman");
		return mv;
	}
	
	@PostMapping("/update_kategori_pinjaman")
	public ModelAndView updateKategoriPinjaman(HttpSession session, RedirectAttributes attribute, @Valid KategoriPinjamanBean kategoriPinjamanBean) {
		ModelAndView mv = new ModelAndView();
		String token = (String) session.getAttribute("token");
		try {
			kategoriPinjamanService.editKategoriPinjaman(token, kategoriPinjamanBean.getId(), kategoriPinjamanBean.getNamaPinjaman());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("redirect:/kategori_pinjaman");
		return mv;
	}
	
	@GetMapping("/delete_kategori_pinjaman")
	public ModelAndView deleteKategoriPinjaman(HttpSession session, RedirectAttributes attribute, @Valid KategoriPinjamanBean kategoriPinjamanBean) {
		ModelAndView mv = new ModelAndView();
		String token = (String) session.getAttribute("token");
		try {
			kategoriPinjamanService.deleteKategoriPinjaman(token, kategoriPinjamanBean.getId());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("redirect:/kategori_pinjaman");
		return mv;
	}
}
