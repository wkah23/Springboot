package com.study.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.SimpleBbsDTO;
@Mapper
public interface ISimpleBbsDAO {
	public List<SimpleBbsDTO> listDAO();
	// 게시물 보기 -> 배열처럼 0번 인덱스부터 시작하는 형식으로 #{0}, #{1}...
	public SimpleBbsDTO viewDAO(String id);
	// 게시물 등록 -> #{param1},#{param2}...
	public int writeDAO(String writer, String title, String content);
	// 게시물 삭제 -> #{_id}와 같이 어노테이션에서 지정한 이름을 사용한다.
	// 실제는 긴 이름을 짧게 하거나, 이해하기 어려운 변수를 이해하기 쉬운
	// 변수명으로 바꾸는데 사용, 가독성을 좋게한다.
	public int deleteDAO(@Param("_id") String id);
}
