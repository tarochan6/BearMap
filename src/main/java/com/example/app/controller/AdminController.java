package com.example.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.Admin;
import com.example.app.service.AdminService;
import com.example.app.service.BearService;

@Controller
@RequestMapping("/admins")
public class AdminController {
	@Autowired
	BearService bearService;

	@Autowired
	AdminService adminService;

	@GetMapping("/login")
	public String showLogin(Model model) {
		model.addAttribute("admin", new Admin());
		return "/admins/login";
	}

	@PostMapping("/login")
	public String login(@Valid Admin admin, Errors errors, HttpSession session) throws Exception {
		// 入力に不備がある
		if (errors.hasErrors()) {
			return "/admins/login";
		}

		// パスワードが正しくない
		if (!adminService.isCorrectIdAndPassword(admin.getLoginId(), admin.getLoginPass())) {
			errors.rejectValue("loginId", "error.incorrect_id_password");
			return "/admins/login";
		}

		// 正しいログインIDとパスワード
		// ⇒セッションにログインIDを格納し、adminindexへ
		session.setAttribute("loginId", admin.getLoginId());
		return "redirect:index";
	}
	
	@GetMapping("/index")
	public String index() {
	  return "/admins/index";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// セッションを破棄し、ログインページへ遷移
		session.invalidate();
		return "redirect:/";
	}

}
