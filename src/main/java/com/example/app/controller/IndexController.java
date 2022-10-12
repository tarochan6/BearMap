package com.example.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.domain.Admin;
import com.example.app.service.AdminService;
import com.example.app.service.BearService;
import com.example.app.service.PagerService;

@Controller
public class IndexController {
	@Autowired
	BearService bearService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	PagerService pagerService;
	
	// 1ページ当たりの表示数
	private static final int NUM_PER_PAGE = 5;
	// 1ページ当たりのページ番号の表示件数
	private static final int range = 5;
	
	@GetMapping
	private String ShowIndex(@RequestParam(name = "page", defaultValue = "1") Integer page, Model model) throws Exception {
		model.addAttribute("bears", bearService.getBearListByPage(page, NUM_PER_PAGE));
		model.addAttribute("page", page);
		model.addAttribute("totalPages", bearService.getTotalPages(NUM_PER_PAGE));
		int[] startAndEnd = pagerService.getStartAndEnd(page, bearService.getTotalPages(NUM_PER_PAGE), range);
		model.addAttribute("start", startAndEnd[0]);
		model.addAttribute("end", startAndEnd[1]);
		return "index";
		
	}

	@GetMapping("/login")
	public String showLogin(Model model) {
		model.addAttribute("admin", new Admin());
		return "login";
	}

	@PostMapping("/login")
	public String login(@Valid Admin admin, Errors errors, HttpSession session) throws Exception {
		// 入力に不備がある
		if (errors.hasErrors()) {
			return "login";
		}

		// パスワードが正しくない
		if (!adminService.isCorrectIdAndPassword(admin.getLoginId(), admin.getLoginPass())) {
			errors.rejectValue("loginId", "error.incorrect_id_password");
			return "login";
		}

		// 正しいログインIDとパスワード
		// ⇒セッションにログインIDを格納し、adminindexへ
		session.setAttribute("loginId", admin.getLoginId());
		return "redirect:/admins/index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// セッションを破棄し、ログインページへ遷移
		session.invalidate();
		return "redirect:/";
	}
	
}
