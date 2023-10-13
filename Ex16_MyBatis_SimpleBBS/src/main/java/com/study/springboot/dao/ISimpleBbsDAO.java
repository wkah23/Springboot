package com.study.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.SimpleBbsDTO;
@Mapper
public interface ISimpleBbsDAO {
	public List<SimpleBbsDTO> listDAO();
	public SimpleBbsDTO viewDAO(String id);
	public int writeDAO(String writer, String title, String content);
	public int deleteDAO(String id);
}
