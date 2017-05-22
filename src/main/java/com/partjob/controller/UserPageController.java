package com.partjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author InnerAc
 * 用户页面转发控制器
 */
@Controller
@RequestMapping(value = "userp")
public class UserPageController {
	@RequestMapping("{page}")
	public String userto(@PathVariable String page){
		return "user/"+page;
	}
	
	@RequestMapping("")
	public String index(){
		return "user/index";
	}
}
