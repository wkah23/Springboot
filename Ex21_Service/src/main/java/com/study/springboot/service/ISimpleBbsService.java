package com.study.springboot.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.SimpleBbsDTO;

public interface ISimpleBbsService {
	public List<SimpleBbsDTO> list();
	public SimpleBbsDTO view(String id);
	// 게시물 수정 -> Map 컬렉션을 사용하므로 #{key값1}, #{key값2}로 사용한다.
	public int write(Map<String, String> map);
	public int delete(@Param("_id") String id);
	public int count();
}
