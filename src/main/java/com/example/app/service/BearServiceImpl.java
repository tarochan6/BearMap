package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Bear;
import com.example.app.domain.InfoType;
import com.example.app.mapper.BearMapper;
import com.example.app.mapper.InfoTypeMapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class BearServiceImpl implements BearService {

	@Autowired
	BearMapper bearMapper;
	@Autowired
	InfoTypeMapper infoTypeMapper;

	@Override
	public List<Bear> getBearList() throws Exception {
		return bearMapper.selectAll();
	}

	@Override
	public List<Bear> getBearListByPage(int page, int numPerPage) throws Exception {
		int offset = numPerPage * (page - 1);
		return bearMapper.selectLimited(offset, numPerPage);
	}

	@Override
	public int getTotalPages(int numPerPage) throws Exception {
		double totalNum = (double) bearMapper.count();
		return (int) Math.ceil(totalNum / numPerPage);
	}

	@Override
	public Bear getBearById(Integer id) throws Exception {
		return bearMapper.selectById(id);
	}

	@Override
	public void addBear(Bear bear) throws Exception {
		bearMapper.insert(bear);

	}

	@Override
	public void editBear(Bear bear) throws Exception {
		bearMapper.update(bear);

	}

	@Override
	public void deleteBear(Integer id) throws Exception {
		bearMapper.delete(id);
	}

	@Override
	public List<InfoType> getTypeList() throws Exception {
		return infoTypeMapper.selectAll();
	}

}
