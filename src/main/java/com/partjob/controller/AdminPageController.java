package com.partjob.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.partjob.constant.CommonCanstant;
import com.partjob.model.JobInfo;
import com.partjob.model.MchntInfo;
import com.partjob.model.MchntSchedule;
import com.partjob.model.NetJob;
import com.partjob.model.UserInfo;
import com.partjob.model.UserSchedule;
import com.partjob.service.JobService;
import com.partjob.service.MchntService;
import com.partjob.service.SwiperService;
import com.partjob.service.UserCashService;
import com.partjob.service.UserJobService;
import com.partjob.service.UserService;

@Controller
@RequestMapping(value = "adminp")
public class AdminPageController extends BaseController{

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
	@Autowired
	SwiperService swiperService;
	
	@RequestMapping("{page}")
	public String adminto(@PathVariable String page,HttpServletRequest request,Model model){
		if(!checkLogin(request)){
			model.addAttribute("islogin", 0);
			return "admin/index";
		}else {
			model.addAttribute("islogin", 1);
		}
		return "admin/"+page;
	}
	
	@RequestMapping("")
	public String index(HttpServletRequest request,Model model){
		if(checkLogin(request)){
			model.addAttribute("islogin", 1);
		}else{
			model.addAttribute("islogin", 0);
		}
		return "admin/index";
	}
	
	@RequestMapping("jobcheck")
	public String jobcheck(Model model,HttpServletRequest request,String jobkey){
		if(!checkLogin(request)){
			model.addAttribute("islogin", 0);
			return "admin/index";
		}
		List<JobInfo> jobs = null;
		if(jobkey ==null || "".equals(jobkey)){
			jobs = jobService.getUncheckJobs();
		}else{
			jobs = jobService.getUncheckJobsByKey(jobkey);
		}
		
		model.addAttribute("jobs", jobs);
		return "admin/jobcheck";
	}
	@RequestMapping("jobs")
	public String jobs(Model model,HttpServletRequest request,int uid,int flag){
		if(!checkLogin(request)){
			model.addAttribute("islogin", 0);
			return "admin/index";
		}
		List<JobInfo> jobs = null;
		if(flag == 0){//用户
			jobs = jobService.getJobByUid(uid);
		}else{//商户
			jobs = mchntService.getJobInfoList(uid);
		}
		
		model.addAttribute("jobs", jobs);
		return "admin/jobs";
	}
	
	@RequestMapping("mchnt")
	public String mchnt(Model model,HttpServletRequest request,String mchntkey){
		if(!checkLogin(request)){
			model.addAttribute("islogin", 0);
			return "admin/index";
		}
		List<MchntInfo> mchntInfos = null;
		if(mchntkey == null || "".equals(mchntkey)){
			mchntInfos = mchntService.getAllMchnt();
		}else {
			mchntInfos = mchntService.getMchntByKey(mchntkey);
		}
		model.addAttribute("mchnts", mchntInfos);
		return "admin/mchnt";
	}
	
	@RequestMapping("user")
	public String user(Model model,HttpServletRequest request, String userkey){
		if(!checkLogin(request)){
			model.addAttribute("islogin", 0);
			return "admin/index";
		}
		List<UserInfo> userInfos =null;
		if(userkey == null || "".equals(userkey)){
			userInfos = userService.getAllUser();
		}else {
			userInfos = userService.getUserByKey(userkey);
		}
		 
		System.out.println(userInfos.size());
		model.addAttribute("users", userInfos);
		return "admin/user";
	}
	
	@RequestMapping("cash")
	public String cash(Model model,HttpServletRequest request){
		if(!checkLogin(request)){
			model.addAttribute("islogin", 0);
			return "admin/index";
		}
		List<UserSchedule> users = userCashService.getCashs();
		List<MchntSchedule> mchnts = mchntService.getCashs();
		model.addAttribute("users", users);
		model.addAttribute("mchnts", mchnts);
		return "admin/cash";
	}
	
	@RequestMapping("netjob")
	public String netjob(Model model,HttpServletRequest request){
		if(!checkLogin(request)){
			model.addAttribute("islogin", 0);
			return "admin/index";
		}
		List<NetJob> netJobs = jobService.getAllNetJob();
		model.addAttribute("netjobs", netJobs);
		return "admin/netjob";
	}
	
	@RequestMapping("login")
	public String login(HttpServletRequest request,Model model,String phone,String pwd){
		MchntInfo mchntInfo = mchntService.logoin(pwd, phone);
		HttpSession session = request.getSession();
		if("10000000000".equals(mchntInfo.getPhone()) || "13212345678".equals(mchntInfo.getPhone())){
			session.setAttribute(CommonCanstant.MCHNT_INFO, mchntInfo);
			model.addAttribute("islogin", 1);
			return "admin/index";
		}else {
			model.addAttribute("islogin", 0);
			return "admin/index";
		}
		
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request,Model model){
		HttpSession session = request.getSession();
		session.removeAttribute(CommonCanstant.MCHNT_INFO);
		model.addAttribute("islogin", 0);
		return "admin/index";
		
	}
	
	@RequestMapping("swiper")
	@ResponseBody
	public String upFile(MultipartFile file,String fileName,String url,HttpSession session){
		try{
			String realPath=session.getServletContext().getRealPath("/")+"resources/images/";
			String path = realPath+fileName+".jpg";
			File img = new File(path);
			img.deleteOnExit();
			file.transferTo(img);
			swiperService.insert(fileName+".jpg", url);
			return "success";
		}catch(Exception e){
			return "fail";
		}
	}
	@RequestMapping("delete/{jid}")
	@ResponseBody
	public int delete(@PathVariable int jid){
		try {
			jobService.delete(jid);
			return 0;
		} catch (Exception e) {
			return 1;
		}
		
	}
	public boolean checkLogin(HttpServletRequest request){
		MchntInfo mchntInfo = getMchntInfo(request);
		if(mchntInfo == null || (!"10000000000".equals(mchntInfo.getPhone()) && !"13212345678".equals(mchntInfo.getPhone()))){
			return false;
		}else if("10000000000".equals(mchntInfo.getPhone()) || "13212345678".equals(mchntInfo.getPhone())){
			return true;
		}
		return false;
	}
	
}
