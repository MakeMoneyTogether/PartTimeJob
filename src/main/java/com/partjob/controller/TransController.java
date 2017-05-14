package com.partjob.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public int pay(HttpServletRequest request,@RequestParam(value = "totalFee") String totalFee){
		logger.info("支付开始");
		String ip=CommonUtil.getIpAddr(request);
		String openId=(String) request.getSession().getAttribute(TransCanstant.OPEN_ID);
		
//		String openId="oaelhwCJmuYs2KRKT3eYTnH1Bmyo";
		logger.info("支付步骤1");
		transService.pay(totalFee, ip, openId);
		logger.info("success");
		return ResponseCode.SUCCESS;
	}
	
	@RequestMapping(value = "cash")
	@ResponseBody
	public int cash(HttpServletRequest request,@RequestParam(value = "totalFee") String totalFee){
		logger.info("支付开始");
		String ip=CommonUtil.getIpAddr(request);
		String openId=(String) request.getSession().getAttribute(TransCanstant.OPEN_ID);
		
//		String openId="oaelhwCJmuYs2KRKT3eYTnH1Bmyo";
		logger.info("支付步骤1");
		transService.cash(totalFee,  openId);
		logger.info("success");
		return ResponseCode.SUCCESS;
	}
	
	@RequestMapping(value = "notify")
	@ResponseBody
	public int notifyUrl(){
		logger.info("支付成功");
		return ResponseCode.SUCCESS;
	}
}
