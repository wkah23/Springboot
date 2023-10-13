package com.study.springboot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springboot.service.ISimpleBbsService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyController {
	// MyBatis 사용을 위해 자동주입
//	@Autowired
//	ISimpleBbsDAO dao;	// 약한 결합으로 주입 받는다.
	
	@Autowired
	ISimpleBbsService bbs;
	
	@RequestMapping("/")
	public String root() throws Exception {
//		Service : DAO
		return "redirect:list";
	}
	
	@RequestMapping("/list")
	public String userlistPage(Model model) {
		// dao(Mapper)의 listDAO() 메서드를 호출해서 게시물 목록을 인출
//		model.addAttribute("list", dao.listDAO());
		model.addAttribute("list", bbs.list());
		
		int nTotalCount = bbs.count();
		System.out.println("Count : "+nTotalCount);
		return "/list";
	}
	
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Model model) {
		String sId = request.getParameter("id");
		model.addAttribute("dto", bbs.view(sId));
		return "/view";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		return "/writeForm";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		String sName = request.getParameter("writer");
		String sTitle =  request.getParameter("title");
		String sContent = request.getParameter("content");
		
		// HashMap은 이름이 여러개인 값을 넣을 때. Map에 파라미터를 저장한다.
		Map<String, String> map = new HashMap<String, String>();
		map.put("item1", sName);
		map.put("item2", sTitle);
		map.put("item3", sContent);
		
		int nResult = bbs.write(map);
		System.out.println("Write : "+nResult);
		return "redirect:list";
	}
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		String sId = request.getParameter("id");
		int nResult = bbs.delete(sId);
		System.out.println("Delete : "+ nResult);
		return "redirect:list";
	}
}
