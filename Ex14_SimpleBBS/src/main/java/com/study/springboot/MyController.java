package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springboot.dao.ISimpleBbsDao;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyController {
	/*
	 	DAO의 부모로 선언된 서비스 인터페이스를 통해 dao객체를 생성한다.
	 	상속구조를 가지고 있으므로 부모를 통해 자식의 메서드를 호출할 수 있다.
	 */
	@Autowired
	ISimpleBbsDao dao;
	
	@RequestMapping("/")
	public String root() throws Exception {
//		JdbcTemplate : SimpleBBS
		return "redirect:list";
	}
	
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Model model) {
		String sId = request.getParameter("id");
		model.addAttribute("dto", dao.viewDAO(sId));
		return "/view";
	}
	
	@RequestMapping("/list")
	public String userlistPage(Model model) {
		/*
		 	DAO클래스의 select() 메서드를 호출하여 회원목록을 List로 반환받은 후
		 	Model 객체에 저장하고 View로 반환한다.
		 */
		model.addAttribute("list", dao.listDAO());
		return "/list";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		return "/writeForm";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		dao.writeDAO(request.getParameter("writer"), 
					request.getParameter("title"), 
					request.getParameter("content"));
		return "redirect:list";
	}
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		dao.deleteDAO(request.getParameter("id"));
		return "redirect:list";
	}
}
