package com.study.springboot.jdbc;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

/*
 	컨트롤러와 맵퍼(XML파일) 사이에서 매개역할을 하는 서비스 인터페이스로
 	JdbcTemplate에서는 @Service를 사용하지만 Mybatis는 @Mapper를 사용한다.
 */
@Mapper	// 인터페이스 구현을 XML로 한다는 의미.
public interface IMyUserDAO {
	// MyUserDAO를 select해줌.
	ArrayList<MyUserDTO> getUser();
}
