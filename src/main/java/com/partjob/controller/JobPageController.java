package com.partjob.controller;

import com.alibaba.fastjson.JSON;
import com.partjob.constant.CommonCanstant;
import com.partjob.constant.ResponseCode;
import com.partjob.model.JobInfo;
import com.partjob.model.NetJob;
import com.partjob.model.RelUserJob;
import com.partjob.model.UserInfo;
import com.partjob.service.JobService;
import com.partjob.service.MchntService;
import com.partjob.service.UserJobService;
import com.partjob.service.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sloriac on 2017/5/19.
 * 用户和兼职信息的操作在这里
 */
@Controller
@RequestMapping(value = "job")	//这个不要改了，改了我前端又要改
public class JobPageController extends BaseController{

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    UserJobService userJobService;
    @Autowired
    JobService jobService;
    @Autowired
    UserService userService;
    @Autowired
    MchntService mchntService; 

    /**
     * 获取用户参与的兼职列表
     * @param uid
     * @param statuId
     * @return
     */
    @RequestMapping(value = "sitem/{uid}/{statuId}")
    @ResponseBody
    public Object sitem(@PathVariable int uid, @PathVariable int statuId) {
        List<RelUserJob> userJobs = userJobService.getUserJobsByStatus(uid, statuId);
        if(userJobs == null) return null;
        
        return userJobService.getJobsByRels(userJobs);
    }
    
    /**
     * 获取用户参与的兼职列表
     * @return
     */
    @RequestMapping(value = "sitem")
    @ResponseBody
    public Object sitme(HttpServletRequest request){
    	UserInfo userInfo = getUserInfo(request);
    	List<RelUserJob> userJobs = userJobService.getUserJobsByStatus(userInfo.getUid());
        if(userJobs == null) return null;
        return userJobService.getJobsByRels(userJobs);
    }

    /**
     * 获取用户与兼职之间的关系
     * @param phone
     * @param jid
     * @return
     */
    @RequestMapping(value = "u2j/{phone}/{jid}")
    @ResponseBody
    public Object relOfUserJob(@PathVariable String phone, @PathVariable int jid) {
        return userJobService.getRelOfUserJob(phone, jid);
    }


    /**
     * 分页获取兼职列表（兼职的状态应为准备中，CommonCanstant.JOB_PENDING）
     * @param offset	下标
     * @param length	长度
     * @param dises		区县代码（逗号隔开）如果为all则表示所有区县
     * @param labels	兼职类型（逗号隔开）如果为all则表示所有类型
     * @param dates		开始时间（时间戳）	可能为空
     * @param city		城市代码	兼职里面只包含区县代码，没有城市代码，所以需要联表查询，用IN操作
     * @return
     */
    @RequestMapping(value = "select/{offset}/{length}")
    @ResponseBody
    public Object selectJob(@PathVariable int offset, @PathVariable int length,
    		String dises,String labels, String dates,int city){
//    	System.out.println(dises+" "+labels+" "+dates+" "+city);
//    	if(!"all".equals(dates)){
//    		long time = Long.parseLong(dates);
//    	}
    	return jobService.getJobPage(offset, length, dises, labels, dates, city);
    }
    
    /**
     * 分页获取关键字检索
     * @param offset
     * @param length
     * @param keys
     * @param city
     * @return
     */
    @RequestMapping(value = "search/{offset}/{length}")
    @ResponseBody
    public Object searchJob(@PathVariable int offset, @PathVariable int length,
    		String keys,int city){
//    	System.out.println(dises+" "+labels+" "+dates+" "+city);
//    	if(!"all".equals(dates)){
//    		long time = Long.parseLong(dates);
//    	}
    	return jobService.searchJobPage(offset, length, keys, city);
    }

    /**
     * 获取兼职详情
     * @param jid
     * @return
     */
    @RequestMapping(value = "item/{jid}")
    @ResponseBody
    public Object getJobInfo(@PathVariable int jid) {
        // 要不要返回json
        return jobService.getById(jid);
    }

    /**
     * 获取手机兼职详情
     * @param jid
     * @return
     */
    @RequestMapping(value = "netitem/{jid}")
    @ResponseBody
    public Object getNetJobInfo(@PathVariable int jid) {
        return jobService.getNetJobById(jid);
    }
    
    /**
     * 发布手机兼职
     * @param netJob
     * @return
     */
    @RequestMapping(value = "pushNet")
    @ResponseBody
    public Object addNetJob(NetJob netJob){
    	try {
    		jobService.addNetJob(netJob);
        	return ResponseCode.SUCCESS;
		} catch (Exception e) {
			return ResponseCode.FAIL;
		}
    }

    /**
     * 获取手机兼职列表
     * @param offset
     * @param length
     * @return
     */
    @RequestMapping(value = "net/{offset}/{length}")
    @ResponseBody
    public Object getJobInfo(@PathVariable int offset, @PathVariable int length) {
        // 要不要返回json
        return jobService.getNetJobPage(offset, length);
    }
    
    /**
     * 获取所有兼职类型（发单、传销、义卖等）
     * @return
     */
    @RequestMapping(value = "types")
    @ResponseBody
    public Object getTypes(){
    	
//    	Map<String, Object> item = new HashMap<String, Object>();
//    	item.put("lid", 0);
//    	item.put("name", "发单");
//    	res.add(item);
//    	
//    	item = new HashMap<String, Object>();
//    	item.put("lid", 1);
//    	item.put("name", "发单");
//    	res.add(item);
//    	
//    	item = new HashMap<String, Object>();
//    	item.put("lid", 2);
//    	item.put("name", "义卖");
//    	res.add(item);
    	try{
    		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
        	res=jobService.getAllJobType();
        	return res;
    	}catch(Exception e){
    		return ResponseCode.FAIL;
    	}
    	
    }

    /**
     * 用户报名兼职
     * @param phone
     * @param jid
     * @return
     */
    @RequestMapping(value = "apply/{phone}/{jid}")
    @ResponseBody
    public Object applyJob(@PathVariable String phone, @PathVariable int jid) {
    	return userJobService.applyJob(phone, jid);
    }
    
    /**
     * 获取兼职报名的用户列表
     * @param jobId
     * @param request
     * @return
     */
    @RequestMapping(value = "userOfJob/{jobId}")
    @ResponseBody
    public Object getUserList(@PathVariable int jobId,
			HttpServletRequest request){
		try{
			System.out.println("get user of job");
			return userJobService.getUsersOfJob(jobId);
		}catch(Exception e){
			logger.error("获取报名兼职信息错误",e);
			return ResponseCode.FAIL;
		}
	}
    
    /**
     * 拒绝用户参加兼职
     * @param userId
     * @param jobId
     * @return
     */
    @RequestMapping(value = "refuseUser")
    @ResponseBody
    public int refuseUser(@RequestParam(value = "userId") int userId,
            @RequestParam(value = "jobId") int jobId){
    	
    	try{
    		userJobService.noPassUser(userId, jobId);
    		return ResponseCode.SUCCESS;
    	}catch(Exception e ){
    		logger.error("拒绝用户报名错误",e);
			return ResponseCode.FAIL;
    	}
    }
    /**
     * 针对缺勤和中途离开用户调用，添加零分评价
     * @param userId
     * @param jobId
     * @param status
     * @return
     */
    @RequestMapping(value = "checkUser")
    @ResponseBody
    public int checkUser(@RequestParam(value = "userId") int userId,
            @RequestParam(value = "jobId") int jobId,
            @RequestParam(value = "status") int status){
    	
    	try{
    		userJobService.checkUserWork(userId, jobId, status);
    		//如果用户中途离开或者缺勤，那么分数置为0分
    		userJobService.scoreUserWork(userId, jobId, 0);
    		return ResponseCode.SUCCESS;
    	}catch(Exception e ){
    		logger.error("考勤用户工作错误",e);
			return ResponseCode.FAIL;
    	}
    }
    
    
    /**
     * 提交用户信息，sql有点问题，记得改一下
     * 给满勤用户加状态
     * @param users
     * @param jobId
     * @return
     */
    @RequestMapping(value = "scoreUser")
    @ResponseBody
    public int scoreUser(@RequestParam(value = "users") String users,
            @RequestParam(value = "jobId") int jobId){
    	
    	try{
    		List<HashMap> useres = JSON.parseArray(users,HashMap.class);
    		for(HashMap<String, String> user : useres){
    			int userId = Integer.parseInt(user.get("uid").toString());
    			int score = Integer.parseInt(user.get("grade").toString());
    			System.out.println(userId+" "+score);
        		userJobService.scoreUserWork(userId, jobId, score);
        		userJobService.checkUserWork(userId, jobId, CommonCanstant.USER_WORK_FULL);
    		}

    		jobService.endJob(jobId);
    		return mchntService.clearJob(jobId);
//    		return ResponseCode.SUCCESS;
    	}catch(Exception e ){
    		logger.error("用户评分错误",e);
			return ResponseCode.FAIL;
    	}
    }
    
    /**
     * 管理员审核通过兼职
     * @param jid	兼职id
     * @return
     */
    @RequestMapping(value = "passJob/{jid}")
    @ResponseBody
    public Object passJob(@PathVariable int jid){
    	try {
    		jobService.passJob(jid);
			return ResponseCode.SUCCESS;
		} catch (Exception e) {
			return ResponseCode.FAIL;
		}
    }
    
    /**
     * 管理员审核拒绝兼职
     * @param jid	兼职id
     * @return
     */
    @RequestMapping(value = "refuseJob/{jid}")
    @ResponseBody
    public Object refuseJob(@PathVariable int jid){
    	try {
    		jobService.refuseJob(jid);
			return ResponseCode.SUCCESS;
		} catch (Exception e) {
			return ResponseCode.FAIL;
		}
    }
    
}
