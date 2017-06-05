package com.partjob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.partjob.model.JobInfo;
import com.partjob.service.JobService;
import com.partjob.service.UserJobService;

@Controller
@RequestMapping(value = "adminp")
public class AdminPageController {

	@Autowired
    UserJobService userJobService;
	@Autowired
	JobService jobService;
	
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
}
