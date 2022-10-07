package com.example.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class PagerServiceImpl implements PagerService {
	
	public int[] getStartAndEnd(int currentPage, int totalPages, int range) {
		// ページの範囲が偶数の場合1ページ足す
		if (range % 2 == 0) {
			range += 1;
		}

		// 現在ページからのrange
		int plusMinus = (range - 1) / 2;

		int start = 0;
		int end = 0;
		if (totalPages < range) {
			start = 1;
			end = totalPages;
		} else {
			if (currentPage + plusMinus < range) {
				start = 1;
				end = range;
			} else if (currentPage + plusMinus <= totalPages) {
				start = currentPage - plusMinus;
				end = currentPage + plusMinus;
			} else {
				start = totalPages - range + 1;
				end = totalPages;
			}
		}
		return new int[] { start, end };
	}
}
