package com.study.springboot.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	// 단순히 다입만으로 자바빈을 자동주입 받는다.
	// 빈이 생성될 때 member1 변수가 참조할 객체를 자동으로 찾아온다.
	@Autowired
	Member member1;
	// 타입과 이름까지 지정해서 자바빈을 자동주입 받는다.
	// printer 변수가 참조할 객체를 자동으로 찾아온다.
	@Autowired
	// 유사한 객체가 printerA, printerB 등 여러개일 때. 빈의 이름으로 정확하게 지정한다.
	@Qualifier("printerB")
	Printer printer;
	@Autowired
	Member member2;
	
	// 웹 브라우저에서 사용자가 /로 get 방식의 URL호출을 하면 다음라인의 root()를 실행시킨다.
	@RequestMapping("/")
	// @ResponseBody 어노테이션은 json 이나 XML등 rest api 형태의 응답을 할 경우
	// HTML 태그없이 순수하게 String 데이터만으로 응답을 할 경우 지정한다.
	public @ResponseBody String root() {
		
		/*
		 	어노테이션을 통해 자동으로 빈을 주입 받으므로 메서드내에는 주입을 위한
		 	코드는 필요하지 않다.
		 */
		// 1. Member Bean 가져오기
		member1.print();
		
		// 2. PrinterB Bean 가져오기
		member1.setPrinter(printer);
		member1.print();
		
		// 3. 싱글톤인지 확인
		if (member1 == member2) {
			System.out.println("동일한 객체입니다.");
		} else {
			System.out.println("서로 다른 객체입니다.");
		}
		return "Annotation 사용하기";
	}
}
