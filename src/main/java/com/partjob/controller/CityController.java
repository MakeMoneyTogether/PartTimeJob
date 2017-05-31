package com.partjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.partjob.service.CityService;

/**
 * @author InnerAc
 * 城市控制器
 */
@Controller
@RequestMapping(value = "city")
public class CityController {
	@Autowired
	CityService cityService;
	
	/**
	 * 根据城市代码获取下属区县的代码和名称
	 * @param citycode
	 * @return
	 */
	@RequestMapping("districts/{citycode}")
	@ResponseBody
	public Object districts(@PathVariable int citycode){
//		System.out.println("城市代码 "+citycode);
//		return null;
		return cityService.getCitys(citycode);
	}
	
	/**
	 * 根据citycode查看城市名称
	 * @param citycode
	 * @return
	 */
	@RequestMapping("getCityName/{citycode}")
	@ResponseBody
	public Object getCityName(@PathVariable int citycode){
		return cityService.getCityName(citycode);
	}
}
