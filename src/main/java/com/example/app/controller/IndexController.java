package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.service.BearService;

@Controller
public class IndexController {
	
	// 1 ページ当たりの表示数
	private static final int NUM_PER_PAGE = 5;
	// ページネーションの表示件数
	private static final int range = 5;

	
	@Autowired
	BearService service;
	
	// ページ番号
	private int[] getStartAndEnd(int currentPage, int totalPages, int range) {
		// ページの範囲が偶数の場合1ページ足す
		if(range % 2 == 0) {
			range += 1;
		}
		
		// 現在ページからのrange
		int plusMinus = (range - 1) / 2;
		
		int start = 0;
		int end = 0;
		if(totalPages < range) {
			start = 1;
			end = totalPages;
		}
		else {
			if(currentPage + plusMinus < range) {
				start = 1;
				end = range;
			}
			else if(currentPage + plusMinus  <= totalPages) {
				start = currentPage - plusMinus ;
				end = currentPage + plusMinus ;
			}
			else {
				start = totalPages - range + 1;
				end = totalPages;
			}			
		}
		
		return new int[] {start, end};
	}
	
	@GetMapping
	private String ShowIndex(@RequestParam(name = "page", defaultValue = "1") Integer page, Model model) throws Exception {
		model.addAttribute("bears", service.getBearListByPage(page, NUM_PER_PAGE));
		model.addAttribute("page", page);
		model.addAttribute("totalPages", service.getTotalPages(NUM_PER_PAGE));
		int[] startAndEnd = getStartAndEnd(page, service.getTotalPages(NUM_PER_PAGE), range);
		model.addAttribute("start", startAndEnd[0]);
		model.addAttribute("end", startAndEnd[1]);
		
		return "index";
		
	}


}
