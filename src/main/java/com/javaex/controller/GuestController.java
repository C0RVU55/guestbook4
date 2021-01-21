package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;


@Controller
@RequestMapping(value="/guest")
public class GuestController {
	
	//필드
	@Autowired
	private GuestDao gDao;
	
	//리스트
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("list");
		
		List<GuestVo> guestList = gDao.getList();
		
		//모델 어트리뷰트
		model.addAttribute("gList", guestList);
		
		return "addList";
	}
	
	//등록2 --> @ModelAttribute
	@RequestMapping(value="/add", method= {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestVo gVo) {
		System.out.println("add");
		
		gDao.contentAdd(gVo);
		
		return "redirect:/guest/list";
	}
	
	//삭제폼 
	@RequestMapping(value="/dform", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() { 
		System.out.println("deleteForm");
		
		return "deleteForm";
	}
	
	//삭제
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("password") String pw, @RequestParam("no") int no) {
		System.out.println("delete");
	
		int count = gDao.contentDelete(no, pw);
		
		//비번 실패시 문구 출력
		if(count == 0) { 
			return "redirect:/guest/dform?result=0&no="+no;
		}
		
		return "redirect:/guest/list";
	}
}
