package com.partjob.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.partjob.constant.CommonCanstant;
import com.partjob.constant.ResponseCode;
import com.partjob.constant.TransCanstant;
import com.partjob.model.JobInfo;
import com.partjob.model.MchntInfo;
import com.partjob.service.MchntService;
import com.partjob.utils.CommonUtil;
import com.partjob.utils.HttpRequestUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by MPJ on 17/4/8.
 */
@Controller
@RequestMapping(value = "mchnt")
public class MchntController extends BaseController {
	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private MchntService mchntService;

	/**
	 * 商户登录入口
	 * @return
	 */
	@RequestMapping(value = "")
	public String  mchnt() {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize";
		String param = "appid="
				+ TransCanstant.APP_ID
				+ "&redirect_uri="
				+ URLEncoder
						.encode(TransCanstant.NOTIFY_MCHNT_URL)
								+ "&response_type=code&scope=snsapi_base&state=mchnt#wechat_redirect";

		String result = HttpRequestUtil.sendGet(url, param);
		logger.info("url:"+url+"?"+param);
		logger.info("result"+result);
		
		//返回登录页面
		return "login";
	}
	
	
	public static void main(String[] args) {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize";
		String param = "appid="
				+ TransCanstant.APP_ID
				+ "&redirect_uri="
				+ URLEncoder
						.encode(TransCanstant.NOTIFY_MCHNT_URL)
								+ "&response_type=code&scope=snsapi_base&state=mchnt#wechat_redirect";

		System.out.println(url+"?"+param);
//		logger.info("url:"+url+"?"+param);
	}
	
	/**
	 * 获取用户oppenid的地址
	 * @param code
	 * @param state
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "redirectUrl")
	public void getOpenId(
			HttpServletRequest request,HttpServletResponse response) {

		String code=request.getParameter("code");
		
		String param = "appid=" + TransCanstant.APP_ID + "&secret="
				+ TransCanstant.SECRET + "&code=" + code
				+ "&grant_type=authorization_code";
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
		String result = HttpRequestUtil.sendGet(url, param);
		JSONObject json=JSON.parseObject(result);
		String openId=(String) json.get("openid");
		HttpSession session = request.getSession();
		session.setAttribute(TransCanstant.OPEN_ID, openId);
		logger.info("openId:"+openId);
		
		try {
			String res=HttpRequestUtil.sendGet("http://mapengju.com/PartTimeJob/transTest/pay?totalFee=1", null);
			logger.info(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return "pay";
	}

	
	/**
	 * 注册
	 * 
	 * @param mchntInfo
	 * @param password
	 * 
	 * @return
	 */
	@RequestMapping(value = { "register" })
	@ResponseBody
	public Object register(MchntInfo mchntInfo,
			@RequestParam(value = "password") String password) {

		try {
			mchntService.saveMchnt(mchntInfo, password);
		} catch (Exception e) {
			logger.error(e);
			return ResponseCode.FAIL;
		}

		return ResponseCode.SUCCESS;
	}

	/**
	 * 登录
	 * 
	 * @param password
	 * @param phone
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "login" })
	@ResponseBody
	public Object login(@RequestParam(value = "password") String password,
			@RequestParam(value = "phone") String phone,
			HttpServletRequest request) {
		try {
			MchntInfo mchntInfo = mchntService.logoin(password, phone);
			if (mchntInfo != null) {
				HttpSession session = request.getSession();
				session.setAttribute(CommonCanstant.MCHNT_INFO, mchntInfo);

				return ResponseCode.SUCCESS;
			} else {
				return ResponseCode.PHONE_PASSWORD_ERROR;
			}
		} catch (Exception e) {
			logger.error(e);
			return ResponseCode.FAIL;
		}

	}

	/**
	 * 检查手机号是否存在
	 * 
	 * @param phone
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "checkPhone" })
	@ResponseBody
	public Object checkPhone(@RequestParam(value = "phone") String phone,
			HttpServletRequest request) {
		try {
			boolean flag = mchntService
					.checkPhone(phone, getMchntInfo(request));
			if (flag == true) {
				return ResponseCode.PHONE_EXIST;
			} else {
				return ResponseCode.SUCCESS;
			}
		} catch (Exception e) {
			logger.error(e);
			return ResponseCode.FAIL;
		}

	}

	/**
	 * 获取商户信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "getMchntInfo" })
	@ResponseBody
	public Object prepareData(HttpServletRequest request) {
		try {
			return mchntService
					.getMchntInfo(getMchntInfo(request).getMchntCd());
		} catch (Exception e) {
			logger.error(e);
			return ResponseCode.FAIL;
		}
	}

	/**
	 * 更新商户信息
	 * 
	 * @param mchntInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "update" })
	@ResponseBody
	public int updateMchntInfo(MchntInfo mchntInfo,
			HttpServletRequest request) {
		try {
			// 检查是否具有更新权限
			if (mchntInfo.getMchntCd() != getMchntInfo(request).getMchntCd()) {
				return ResponseCode.FORBIDDEN;
			}
			mchntService.updateMchtInfo(mchntInfo);
			return ResponseCode.SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return ResponseCode.FAIL;
		}
	}
	
	
	/**
	 * 商户充值
	 * @param totalFee 充值金额
	 * @param mchntCd	商户号
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "pay" })
	@ResponseBody
	public int pay(@RequestParam(value = "totalFee") String totalFee,
			HttpServletRequest request){
		
		try {
			int mchntCd=getMchntInfo(request).getMchntCd();
			String ip=CommonUtil.getIpAddr(request);
			String openId=(String) request.getSession().getAttribute(TransCanstant.OPEN_ID);
			int code=mchntService.pay(totalFee, ip, openId, mchntCd);
			
			return code;
		} catch (Exception e) {
			logger.error(e);
			return ResponseCode.FAIL;
		}
	}
	
	/**
	 * 发送兼职信息
	 * @param jobInfo
	 * @param mchntCd
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "podtJob" })
	@ResponseBody
	public int postJob(JobInfo jobInfo,
			HttpServletRequest request){
		try{
			int mchntCd=getMchntInfo(request).getMchntCd();
			mchntService.postJob(jobInfo, mchntCd);
			return ResponseCode.SUCCESS;
		}catch(Exception e){
			logger.error(e);
			return ResponseCode.FAIL;
		}
	}
	/**
	 * 获取兼职信息列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "getJobList" })
	@ResponseBody
	public Object getJobList(HttpServletRequest request){
		try{
			int mchntCd=getMchntInfo(request).getMchntCd();
			List<JobInfo>result=mchntService.getJobInfoList(mchntCd);
			return result;
		}catch (Exception e){
			logger.error(e);
			return ResponseCode.FAIL;
		}
	}
	
	
	public Object getUserList(@RequestParam(value = "jobId") String jobId,
			HttpServletRequest request){
		try{
			return ResponseCode.SUCCESS;
		}catch(Exception e){
			logger.error(e);
			return ResponseCode.FAIL;
		}
	}
}
