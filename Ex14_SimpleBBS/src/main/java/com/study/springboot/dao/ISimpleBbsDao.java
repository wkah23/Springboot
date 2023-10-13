package com.study.springboot.dao;

import java.util.List;

import com.study.springboot.dto.SimpleBbsDto;

public interface ISimpleBbsDao {
	public List<SimpleBbsDto> listDAO();
	public SimpleBbsDto viewDAO(String id);
	public int writeDAO(String writer, String title, String content);
	public int deleteDAO(String id);
}
