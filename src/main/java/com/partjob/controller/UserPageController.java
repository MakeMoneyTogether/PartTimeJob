package com.partjob.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.partjob.model.NetJob;
import com.partjob.model.UserInfo;
import com.partjob.service.JobService;

/**
 * @author InnerAc
 * 用户页面转发控制器
 */
@Controller
@RequestMapping(value = "userp")
public class UserPageController extends BaseController{
	@Autowired
	JobService jobService;
	@RequestMapping("{page}")
	public String userto(@PathVariable String page){
		return "user/"+page;
	}
	
	@RequestMapping("")
	public String index(){
		return "user/index";
	}
	
	@RequestMapping("netinfo/{nid}")
	public String netInfo(@PathVariable int nid,Model model){
		NetJob netJob = jobService.getNetJobById(nid);
		model.addAttribute("net", netJob);
		return "user/netinfo";
	}
	
	@RequestMapping("wallet")
	public String wallet(HttpServletRequest request,Model model){
		UserInfo userInfo =getUserInfo(request);
		model.addAttribute("me", userInfo);
		return "user/wallet";
	}
}
