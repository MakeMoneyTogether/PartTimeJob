package com.partjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author InnerAc
 * 城市控制器
 */
@Controller
@RequestMapping(value = "city")
public class CityController {
	
	/**
	 * 根据城市代码获取下属区县的代码和名称
	 * @param citycode
	 * @return
	 */
	@RequestMapping("districts/{citycode}")
	public Object districts(@PathVariable int citycode){
		System.out.println("城市代码 "+citycode);
		return null;
	}
}
