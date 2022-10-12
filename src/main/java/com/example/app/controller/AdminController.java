package com.example.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.Bear;
import com.example.app.service.BearService;
import com.example.app.service.PagerService;

@Controller
@RequestMapping("/admins")
public class AdminController {
	@Autowired
	BearService bearService;

	@Autowired
	PagerService pagerService;

	// 1ページ当たりの表示数
	private static final int NUM_PER_PAGE = 10;
	// 1ページ当たりのページ番号の表示件数
	private static final int range = 5;

	@GetMapping("/index")
	public String showIndex(@RequestParam(name = "page", defaultValue = "1") Integer page, Model model)
			throws Exception {
		model.addAttribute("bears", bearService.getBearListByPage(page, NUM_PER_PAGE));
		model.addAttribute("page", page);
		model.addAttribute("totalPages", bearService.getTotalPages(NUM_PER_PAGE));
		int[] startAndEnd = pagerService.getStartAndEnd(page, bearService.getTotalPages(NUM_PER_PAGE), range);
		model.addAttribute("start", startAndEnd[0]);
		model.addAttribute("end", startAndEnd[1]);
		return "admins/index";
	}

	@GetMapping("/add")
	public String addGet(Model model) throws Exception {
		Bear bear = new Bear();
		bear.setId(Math.toIntExact(bearService.getCount()) + 1);
		bear.setAdult(0);
		bear.setChild(0);
		bear.setUnknown(0);
		model.addAttribute("title", "データの追加");
		model.addAttribute("bear", bear);
		model.addAttribute("types", bearService.getTypeList());
		return "admins/save";
	}
	
	@PostMapping("/add")
	public String addPost(@Valid Bear bear, Errors errors, RedirectAttributes rd, Model model) throws Exception {
		if(errors.hasErrors()) {
			model.addAttribute("title", "データの追加");
			model.addAttribute("types", bearService.getTypeList());
			return "admins/save";
		}
			bearService.addBear(bear);
			rd.addFlashAttribute("statusMessage", "データを追加しました。");
		return "redirect:/admins/index";
	}
	
	@GetMapping("/edit/{id}")
	public String editGet(@PathVariable Integer id, Model model) throws Exception {
		model.addAttribute("title", "データの編集");
		model.addAttribute("bear", bearService.getBearById(id));
		model.addAttribute("type", bearService.getTypeList());
		return "admins/save";
	}

	@PostMapping("/edit/{id}")
	public String editPost(@PathVariable Integer id, @Valid Bear bear, Errors errors,
									RedirectAttributes rd, Model model) throws Exception {
		if (errors.hasErrors()) {
			model.addAttribute("title", "データの編集");
			model.addAttribute("types", bearService.getTypeList());
			return "admins/save";
		}
		bear.setId(id);// 更新に必要な会員ID をセット
		bearService.editBear(bear);
		rd.addFlashAttribute("statusMessage", "データを更新しました。");
		return "redirect:/admins/index";
	}
 
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, RedirectAttributes rd) throws Exception {
		bearService.deleteBear(id);
		rd.addFlashAttribute("statusMessage", "データを削除しました。");
	return "redirect:/admins/index";
	}

}
