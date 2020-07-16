package id.koperasi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

	@GetMapping(value = {"/","/login"})
	public ModelAndView index(HttpSession session, RedirectAttributes attribute) {
		ModelAndView mv = new ModelAndView();
		String token = (String) session.getAttribute("token");
		if (token != null && !token.equals("")) {
			mv.getModel().put("token", token);
			mv.setViewName("redirect:/kategori_pinjaman");
			return mv;
		}	
		mv.setViewName("/login");
		return mv;
	}
	
}
