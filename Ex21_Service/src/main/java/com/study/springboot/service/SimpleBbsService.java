package com.study.springboot.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.dao.ISimpleBbsDAO;
import com.study.springboot.dto.SimpleBbsDTO;

@Service
public class SimpleBbsService implements ISimpleBbsService {
	@Autowired
	ISimpleBbsDAO dao;
	
	public List<SimpleBbsDTO> list() {
		return dao.listDAO();
	}
	public SimpleBbsDTO view(String id) {
		return dao.viewDAO(id);
	}
	public int write(Map<String, String> map) {
		int nResult = dao.writeDAO(map);
		return nResult;
	}
	public int delete(String id) {
		int nResult = dao.deleteDAO(id);
		return nResult;
	}
	public int count() {
		int nTotalCount = dao.articleCount();
		return nTotalCount;
	}
}
