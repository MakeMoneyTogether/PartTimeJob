package com.partjob.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.partjob.constant.ResponseCode;
import com.partjob.constant.TransCanstant;
import com.partjob.service.TransService;
import com.partjob.utils.CommonUtil;

@Controller
@RequestMapping(value = "transTest")
public class TransController {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	TransService transService;
	
	@RequestMapping(value = "")
	public String idnex(){
		return "pay";
	}
	
	@RequestMapping(value = "pay")
	public int pay(HttpServletRequest request,@RequestParam(value = "totalFee") String totalFee){
		String ip=CommonUtil.getIpAddr(request);
		String openId=(String) request.getSession().getAttribute(TransCanstant.OPEN_ID);
		transService.pay(totalFee, ip, openId);;
		logger.info("success");
		return ResponseCode.SUCCESS;
	}
}
