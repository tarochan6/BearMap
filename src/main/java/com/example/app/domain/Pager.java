package com.example.app.domain;

import lombok.Data;

@Data
public class Pager {

	// トータルrangeページ以下
	// 例 ① 2 3 4
	// 例 1 2 3 ④
	// start 1, end totalPages

	// トータルrangeページ以上
	// 以下は10ページの場合の例

	// -条件- currentPage + 2 < range
	// ------ start 1, end range
	// 例 ① 2 3 4 5
	// 例 1 ② 3 4 5

	// -条件- currentPage + 2 >= range
	// ------ start currentPage - 2
	// ------ end currentPage + 2
	// 例 1 2 ③ 4 5
	// 例 2 3 ④ 5 6
	// ...
	// 例 6 ⑦ 8 9 10
	// 例 6 7 ⑧ 9 10

	// -条件- currentPage + 2 > totalPages
	// ------ start totalPages - range + 1
	// ------ end totalPages
	// 例 6 7 8 ⑨ 10
	// 例 6 7 8 9 ⑩

	private static int[] getStartAndEnd(int currentPage, int totalPages, int range) {
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

	public static void main(String[] args) {

		int range = 5;
		int currentPage = 7;
		int totalPages = 10;

		int[] startAndEnd = getStartAndEnd(currentPage, totalPages, range);

		// スタート数、エンド数確認用
		System.out.println(startAndEnd[0]);
		System.out.println(startAndEnd[1]);

		System.out.println("");

		for (int p = startAndEnd[0]; p <= startAndEnd[1]; p++) {
			if (p == currentPage) {
				System.out.print("★");
			}
			System.out.println(p);

		}

	}

}
