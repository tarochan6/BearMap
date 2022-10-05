package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.app.domain.Bear;

public interface BearMapper {
	List<Bear> selectAll() throws Exception;

	Bear selectById(Integer id) throws Exception;

	// ページ分割機能用
	Long count() throws Exception;

	List<Bear> selectLimited(@Param("offset") int offset, @Param("limit") int limit) throws Exception;

	void insert(Bear bear) throws Exception;

	void update(Bear bear) throws Exception;

	void delete(Integer id) throws Exception;
}