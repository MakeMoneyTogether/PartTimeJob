package com.partjob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.partjob.model.JobInfo;
import com.partjob.model.MchntInfo;
import com.partjob.model.MchntSchedule;
import com.partjob.model.UserInfo;
import com.partjob.model.UserSchedule;
import com.partjob.service.JobService;
import com.partjob.service.MchntService;
import com.partjob.service.UserCashService;
import com.partjob.service.UserJobService;
import com.partjob.service.UserService;

@Controller
@RequestMapping(value = "adminp")
public class AdminPageController {

	@Autowired
    UserJobService userJobService;
	@Autowired
	JobService jobService;
	@Autowired
	MchntService mchntService;
	@Autowired
	UserService userService;
	@Autowired
	UserCashService userCashService;
	
	@RequestMapping("{page}")
	public String adminto(@PathVariable String page){
		return "admin/"+page;
	}
	
	@RequestMapping("")
	public String index(){
		return "admin/index";
	}
	
	@RequestMapping("jobcheck")
	public String jobcheck(Model model){
		List<JobInfo> jobs = jobService.getUncheckJobs();
		model.addAttribute("jobs", jobs);
		return "admin/jobcheck";
	}
	
	@RequestMapping("mchnt")
	public String mchnt(Model model){
		List<MchntInfo> mchntInfos = mchntService.getAllMchnt();
		model.addAttribute("mchnts", mchntInfos);
		return "admin/mchnt";
	}
	
	@RequestMapping("user")
	public String user(Model model){
		List<UserInfo> userInfos = userService.getAllUser();
		System.out.println(userInfos.size());
		model.addAttribute("users", userInfos);
		return "admin/user";
	}
	
	@RequestMapping("cash")
	public String cash(Model model){
		List<UserSchedule> users = userCashService.getCashs();
		List<MchntSchedule> mchnts = mchntService.getCashs();
		model.addAttribute("users", users);
		model.addAttribute("mchnts", mchnts);
		return "admin/cash";
	}
}
