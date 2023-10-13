package com.study.springboot.jdbc;

import lombok.Data;

// 회원 관리를 위한 myuser 테이블에 대한 DTO 객체
@Data
public class MyUserDTO {
	private String id;
	private String name;
}
