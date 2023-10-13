package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	// 컨텐스트 루트 경로에 대한 매핑
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "JSP in Gradle";
	}
	// JSP를 뷰로 사용하기 위한 매핑
	@RequestMapping("/test1")
	public String test1() {
		// 뷰의 경로를 반환한다. 해당 파일은 views 하위에 생성하면 된다.
		return "test1";
	}
	@RequestMapping("/test2")
	public String test2() {
		return "sub/test2";
	}
}
