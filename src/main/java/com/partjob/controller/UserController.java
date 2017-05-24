package com.partjob.controller;

import com.partjob.constant.ResponseCode;
import com.partjob.model.UserInfo;
import com.partjob.service.UserCashService;
import com.partjob.service.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Sloriac on 2017/5/19.
 * 用户的操作在这里
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private UserCashService userCashService;

    @RequestMapping(value = "register")
    @ResponseBody
    public Object register(@RequestParam(value = "pwd") String pwd,
                           @RequestParam(value = "phone") String phone,
                           @RequestParam(value = "code") String code,
                           @RequestParam(value = "invitation") String invitation) {
        return userService.register(pwd, phone, code, invitation);
    }

    @RequestMapping(value = "rpwd")
    @ResponseBody
    public Object rpwd(@RequestParam(value = "phone") String phone,
                       @RequestParam(value = "pwd") String pwd,
                       @RequestParam(value = "npwd") String npwd) {
        return userService.rpwd(phone, pwd, npwd);
    }

    @RequestMapping(value = "login")
    @ResponseBody
    public Object login(@RequestParam(value = "phone") String phone,
                           @RequestParam(value = "pwd") String pwd,
                           HttpSession session) {
        UserInfo userInfo = userService.login(phone, pwd);
        System.out.println(userInfo);
        if (userInfo == null){
            logger.info("用户" + phone + "登录失败");
            return ResponseCode.FAIL;
        } else {
            logger.info("用户" + phone + "登录成功");
            //要不要存session
            session.setAttribute("loginUser", userInfo);
            return ResponseCode.SUCCESS;
        }
    }

    @RequestMapping(value = "me")
    @ResponseBody
    public Object me(@RequestParam(value = "phone") String phone,
                           @RequestParam(value = "pwd") String pwd) {
        UserInfo userInfo = userService.login(phone, pwd);
        if (userInfo == null){
            logger.info("没有该用户" + phone);
        } else {
            logger.info("成功获取用户" + phone + "的信息");
        }
        // 要不要转json
        return userInfo;
    }

    @RequestMapping(value = "editcv")
    @ResponseBody
    public Object editcv(@RequestParam(value = "phone") String phone,
                         @RequestParam(value = "pwd") String pwd) {
        UserInfo userInfo = userService.login(phone, pwd);
        if (userInfo == null){
            logger.info("没有该用户" + phone);
        } else {
            logger.info("成功获取用户" + phone + "的信息");
        }
        // 要不要转json
        return userInfo;
    }

    @RequestMapping(value = "cash")
    @ResponseBody
    public Object cash(@RequestParam(value = "phone") String phone,
                         @RequestParam(value = "rmb") double rmb) {
        return userCashService.cash(phone, rmb);
    }

    @RequestMapping(value = "schedule")
    @ResponseBody
    public Object schedule(@RequestParam(value = "phone") String phone) {
        return userCashService.getByPhone(phone);
    }
    

}
