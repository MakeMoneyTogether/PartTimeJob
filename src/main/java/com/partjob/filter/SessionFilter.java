/*
 * Copyright 2012, China UnionPay Co., Ltd. All right reserved.
 * 
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF CHINA UNIONPAY CO., LTD. THE CONTENTS OF THIS FILE MAY NOT BE
 * DISCLOSED TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART, WITHOUT THE PRIOR WRITTEN
 * PERMISSION OF CHINA UNIONPAY CO., LTD.
 */
package com.partjob.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.partjob.constant.CommonCanstant;

/**
 * 用于拦截请求的filter 从U聊获取用户信息后，放入HttpSession
 * 
 * @author cswu
 * @version
 * @since
 * 
 */
public class SessionFilter implements Filter {

	private static final String[] IGNORE_URI = { "resources/", "static","login","forgot", "list","info", "index","register","net","util" };

	public void init(FilterConfig config) throws ServletException {
	}


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain fc) throws IOException, ServletException {
		
//		fc.doFilter(request, response);
//		return;
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		String currentUri = req.getRequestURI().toString();
		
		// 如果当前请求的context在配置的excluded的context名单内，则放过，不拦截
		if (checkExclued(req)) {
			fc.doFilter(request, response);
			return;
		}

		
		if(currentUri.toLowerCase().contains("userp")){
			Object object = session.getAttribute(CommonCanstant.USER_INFO);
//			// 如果session中存放的用户信息为空
			if (object == null) {
//				System.out.println("未获取用户信息，跳转至用户授权页面");
				// 获取用户信息并放session中
				
//				resp.sendRedirect("login?curUrl=" + currentUri);
				resp.sendRedirect("login");
				return;
			}
		}else if(currentUri.toLowerCase().contains("mchntp")){
			Object object = session.getAttribute(CommonCanstant.MCHNT_INFO);
//			// 如果session中存放的用户信息为空
			if (object == null) {
//				System.out.println("未获取用户信息，跳转至用户授权页面");
				// 获取用户信息并放session中
				
//				resp.sendRedirect("login?curUrl=" + currentUri);
				resp.sendRedirect("index");
				return;
			}
		}
//
//
//		// go on
		fc.doFilter(request, response);
		return ;
	}

	public void destroy() {
		// do nothing
	}
	
	/**
	 * 校验请求是否在忽略列表中
	 * 
	 * @since
	 * @param request
	 * @return
	 */
	private boolean checkExclued(HttpServletRequest request) {
		boolean flag = false;
		String currentUri = request.getRequestURI().toString();
		for (String uri : IGNORE_URI) {
			if (currentUri.toLowerCase().contains(uri)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	
	public static void main(String[] args) {
		String string="/PartTimeJob/mchntp/idnex";
		System.out.println(string.contains("mchntp"));
		System.out.println(string.contains("idnex"));
		System.out.println(string.indexOf("index"));
		
	}
	
}
