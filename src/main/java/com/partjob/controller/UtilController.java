package com.partjob.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.partjob.constant.ResponseCode;
import com.partjob.service.MchntService;
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
	@Autowired
	MchntService mchntService;
	
	@RequestMapping(value = "sendCode")
	@ResponseBody
	public Object sendCode(String phone,HttpSession session){
		
		if(userService.checkPhone(phone)){
			return ResponseCode.PHONE_EXIST;
		}
		
		String code = VerificationUtil.genCode();
//		String code = "666666";
		session.setAttribute(phone, code);
		return VerificationUtil.sendRegistCode(phone, code);
//		return 0;
	}
	
	@RequestMapping(value = "sendForgot")
	@ResponseBody
	public Object sendForgot(String phone,HttpSession session){
		String code = VerificationUtil.genCode();
		session.setAttribute(phone, code);
		return VerificationUtil.sendForgotCode(phone, code);
	}
	
	@RequestMapping(value = "sendMode")
	@ResponseBody
	public Object sendMode(String phone,HttpSession session){
		
		if(mchntService.checkPhone(phone)){
			return ResponseCode.PHONE_EXIST;
		}
		
		String code = VerificationUtil.genCode();
		session.setAttribute(phone, code);
		return VerificationUtil.sendRegistMode(phone, code);
	}
	
	@RequestMapping(value = "editCode")
	@ResponseBody
	public Object editCode(String phone,HttpSession session,HttpServletRequest request){	
		String code = VerificationUtil.genCode();
		session.setAttribute(phone, code);
		System.out.println("code = "+code);
		return VerificationUtil.sendEditCode(phone, code, getMchntInfo(request).getMchntName());
	}
}
