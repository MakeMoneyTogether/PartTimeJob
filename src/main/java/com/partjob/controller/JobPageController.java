package com.partjob.controller;

import com.partjob.constant.ResponseCode;
import com.partjob.model.JobInfo;
import com.partjob.model.RelUserJob;
import com.partjob.model.UserInfo;
import com.partjob.service.JobService;
import com.partjob.service.UserJobService;
import com.partjob.service.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping(value = "pages")
public class JobPageController {

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    UserJobService userJobService;
    @Autowired
    JobService jobService;

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
        // 要不要返回json
        return jobService.getNetJobById(jid);
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
    	List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
    	Map<String, Object> item = new HashMap<String, Object>();
    	item.put("lid", 0);
    	item.put("name", "发单");
    	res.add(item);
    	
    	item = new HashMap<String, Object>();
    	item.put("lid", 1);
    	item.put("name", "发单");
    	res.add(item);
    	
    	item = new HashMap<String, Object>();
    	item.put("lid", 2);
    	item.put("name", "义卖");
    	res.add(item);
    	
    	return res;
    }

    @RequestMapping(value = "apply/{phone}/{jid}")
    @ResponseBody
    public Object applyJob(@PathVariable String phone, @PathVariable int jid) {
        return null;
    }

}
