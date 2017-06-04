package com.partjob.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.partjob.constant.TransCanstant;
import com.partjob.utils.HttpRequestUtil;

/**
 * 
 */
@Controller
@RequestMapping(value = "")
public class HomeController {

	private final Logger logger = Logger.getLogger(this.getClass());

	

	/**
	 * 欢迎页面
	 *
	 * @return
	 */
	@RequestMapping(value = "")
	public String welcome() {
		logger.info("进入欢迎页面");
//		return "mchntp/index";
		return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfb5bb3526bdc5da3&redirect_uri=http%3A%2F%2Fwww.mapengju.com%2FPartTimeJob%2Fmchnt%2FredirectUrl&response_type=code&scope=snsapi_base&state=mchnt#wechat_redirect";
	}

	/**
	 * 登录页
	 *
	 * @return
	 */
	@RequestMapping(value = "login")
	public String login() {
		logger.info("进入登录页面");
		return "login";
	}

	/**
	 * 异常页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(value = "error")
	public String error() {
		logger.info("进入报错页面ErroR");
		return "error";
	}

	/**
	 * 输入非法页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(value = "fail")
	public String fail() {
		logger.info("进入输入非法页面");
		return "fail";
	}

	/**
	 * 未找到页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "404")
	public String notFound() {
		logger.info("进入报错页面");
		return "404";
	}

	/**
	 * 无权限页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(value = "noright")
	public String notRight() {
		logger.info("进入无权限访问页面");
		return "noright";
	}

}
