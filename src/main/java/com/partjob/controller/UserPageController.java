package com.partjob.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.MidiDevice.Info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.partjob.model.InvitationInfo;
import com.partjob.model.JobInfo;
import com.partjob.model.NetJob;
import com.partjob.model.UserInfo;
import com.partjob.service.JobService;
import com.partjob.service.SwiperService;
import com.partjob.service.UserService;

/**
 * @author InnerAc
 * 用户页面转发控制器
 */
@Controller
@RequestMapping(value = "userp")
public class UserPageController extends BaseController{
	@Autowired
	JobService jobService;
	@Autowired
	UserService userService;
	@Autowired
	SwiperService swiperService;
	
	@RequestMapping("{page}")
	public String userto(@PathVariable String page){
		return "user/"+page;
	}
	
	@RequestMapping("")
	public String index(){
		return "user/index";
	}
	@RequestMapping("me")
	public String me(Model model){
		model.addAttribute("aboutme", swiperService.getAboutMe().getContent());
		return "user/me";
	}
	
	@RequestMapping("netinfo/{nid}")
	public String netInfo(@PathVariable int nid,Model model){
		NetJob netJob = jobService.getNetJobById(nid);
		model.addAttribute("net", netJob);
		return "user/netinfo";
	}
	
	@RequestMapping("info/{jid}")
	public String jobinfo(@PathVariable int jid,Model model){
		JobInfo jobInfo = jobService.getById(jid);
		model.addAttribute("job", jobInfo);
		return "user/info";
	}
	
	@RequestMapping("wallet")
	public String wallet(HttpServletRequest request,Model model){
		UserInfo userInfo =getUserInfo(request);
		List<InvitationInfo> invUsers = userService.getInvitations(userInfo.getPhone());
		model.addAttribute("me", userInfo);
		model.addAttribute("invUsers", invUsers);
		return "user/wallet";
	}
}
