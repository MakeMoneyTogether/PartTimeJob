package com.partjob.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.partjob.model.JobInfo;
import com.partjob.model.UserInfo;
import com.partjob.service.JobService;
import com.partjob.service.UserJobService;
import com.partjob.service.UserService;

/**
 * Created by InnerAc on 17/5/15.
 */

@Controller
@RequestMapping(value = "mchntp")
public class MchntPageController {
	
	@Autowired
	JobService jobService;
	@Autowired
    UserJobService userJobService;
	@Autowired
	UserService userService;
	
	@RequestMapping("{page}")
	public String merto(@PathVariable String page){
		return "mchnt/"+page;
	}
	
	@RequestMapping("info/{jid}")
	public String jobinfo(@PathVariable int jid,Model model){
		JobInfo job = jobService.getById(jid);
		model.addAttribute("job", job);
		return "mchnt/info";
		
	}
	/**
	 * 获取某个兼职报名的人员列表
	 * @param jid
	 * @param model
	 * @return
	 */
	@RequestMapping("peoplem/{jid}")
	public String peoplelem(@PathVariable int jid,Model model){
		List<UserInfo> users = userJobService.getUsersOfJob(jid);
		model.addAttribute("users", users);
		model.addAttribute("jid", jid);
		return "mchnt/peoplem";
	}
	/**
	 * 兼职报名人员评分
	 * @param jid
	 * @param model
	 * @return
	 */
	@RequestMapping("evaluate/{jid}")
	public String evaluate(@PathVariable int jid,Model model){
		List<UserInfo> users = userJobService.getUsersOfJob(jid);
		model.addAttribute("users", users);
		model.addAttribute("jid", jid);
//		model.addAttribute("users",new ArrayList<UserInfo>());	//这里要包含用户id，用户姓名
		return "mchnt/evaluate";
	}
	/**
	 * 查看用户信息
	 * @param uid
	 * @param model
	 * @return
	 */
	@RequestMapping("userinfo/{uid}")
	public String userinfo(@PathVariable int uid,Model model){
		UserInfo user = userService.getUser(uid);
		model.addAttribute("user", user);
		return "mchnt/userinfo";
	}
}
