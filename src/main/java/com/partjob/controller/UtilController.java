package com.partjob.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.partjob.constant.ResponseCode;
import com.partjob.service.UserService;
import com.partjob.utils.VerificationUtil;

/**
 * @author InnerAc
 * 用于验证码发送等操作
 */
@Controller
@RequestMapping(value = "util")
public class UtilController extends BaseController{

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "sendCode")
	@ResponseBody
	public Object sendCode(String phone,HttpSession session){
		
		if(userService.checkPhone(phone)){
			return ResponseCode.PHONE_EXIST;
		}
		
		String code = VerificationUtil.genCode();
		session.setAttribute(phone, code);
		return VerificationUtil.sendRegistCode(phone, code);
	}
}
