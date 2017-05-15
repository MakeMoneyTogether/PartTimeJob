package com.partjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by InnerAc on 17/5/15.
 */

@Controller
@RequestMapping(value = "mchntp")
public class MchntPageController {
	
	@RequestMapping("{page}")
	public String merto(@PathVariable String page){
		return "mchnt/"+page;
	}
}
