package com.partjob.controller;

import com.partjob.constant.CommonCanstant;
import com.partjob.model.UserInfo;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



/**
 * Created by gpzhao on 2016/03/24. 公共方法抽取
 */
@Controller
@RequestMapping(value = "baseController")
public class BaseController {

    private final Logger logger = Logger.getLogger(this.getClass());


    /**
     * 获取当前登录用户信息
     * 
     * @param request
     * @return
     */
    public UserInfo getUserInfo(HttpServletRequest request) {
        logger.info("获取当前用户信息");
        HttpSession session = request.getSession();

        UserInfo user = (UserInfo) session.getAttribute(CommonCanstant.USER_INFO);
        return user;
    }

}
