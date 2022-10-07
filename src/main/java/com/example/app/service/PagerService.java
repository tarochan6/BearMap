package com.example.app.service;

public interface PagerService {
	int[] getStartAndEnd(int currentPage, int totalPages, int range);
}
