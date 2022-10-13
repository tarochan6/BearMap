package com.example.app.service;

import java.util.List;

import com.example.app.domain.Bear;
import com.example.app.domain.InfoType;

public interface BearService {

	List<Bear> getBearList() throws Exception;

	// ページ分割機能用
	List<Bear> getBearListByPage(int page, int numPerPage) throws Exception;
	int getTotalPages(int numPerPage) throws Exception;
	
	// データ数の取得
	Long getCount() throws Exception;
	// 最終データ数の取得
	Long getCountLast() throws  Exception;
	
	Bear getBearById(Integer id) throws Exception;

	void addBear(Bear bear) throws Exception;

	void editBear(Bear bear) throws Exception;

	void deleteBear(Integer id) throws Exception;

	List<InfoType> getTypeList() throws Exception;
	
	
}
