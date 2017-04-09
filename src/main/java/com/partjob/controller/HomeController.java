package com.partjob.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 */
@Controller
@RequestMapping(value = "")
public class HomeController {


	private final Logger logger = Logger.getLogger(this.getClass());

	
	
	/**
	 * 欢迎页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "" )
	public String welcome() {
	    logger.info("进入欢迎页面");
	    return "index";
	}

	/**
     * 登录页
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "login" )
    public String login() {
        logger.info("进入登录页面");
        return "login";
    }
    
	/**
	 * 异常页面跳转
	 * @return
	 */
	@RequestMapping(value = "error")
	public String error() {
		logger.info("进入报错页面ErroR");
		return "error";
	}
	
	/**
	 * 输入非法页面跳转
	 * @return
	 */
	@RequestMapping(value = "fail")
	public String fail() {
	    logger.info("进入输入非法页面");
	    return "fail";
	}

	/**
	 * 未找到页面
	 * @return
	 */
	@RequestMapping(value = "404")
	public String notFound() {
		logger.info("进入报错页面");
		return "404";
	}

	/**
	 * 无权限页面跳转
	 * @return
	 */
	@RequestMapping(value = "noright")
	public String notRight() {
		logger.info("进入无权限访问页面");
		return "noright";
	}

	
	
	
	

}
