package com.partjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "adminp")
public class AdminPageController {

	@RequestMapping("{page}")
	public String adminto(@PathVariable String page){
		return "admin/"+page;
	}
	
	@RequestMapping("")
	public String index(){
		return "admin/index";
	}
}
