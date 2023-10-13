package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springboot.jdbc.MyUserDAO;

@Controller
public class MyController {
	@Autowired
	private MyUserDAO userDAO;
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "JdbcTemplate 사용하기";
	}
	
//	@GetMapping("/user")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userlistPage(Model model) {
		// DAO 클래스의 list() 메서드를 호출하여 회원목록을 userlist로 반환한 후
		// Model 객체에 저장하고 View를 반환한다.
		model.addAttribute("users", userDAO.listForBeanPropertyRowMapper());
		return "userlist";
	}
}
