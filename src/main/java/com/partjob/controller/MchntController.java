package com.partjob.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.partjob.constant.CommonCanstant;
import com.partjob.constant.ObjectStatuCode;
import com.partjob.constant.ResponseCode;
import com.partjob.constant.TransCanstant;
import com.partjob.model.JobInfo;
import com.partjob.model.MchntInfo;
import com.partjob.model.WcPay;
import com.partjob.service.MchntService;
import com.partjob.utils.CommonUtil;
import com.partjob.utils.HttpRequestUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
//		return "views/mchnt/index";
		return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfb5bb3526bdc5da3&redirect_uri=http%3A%2F%2Fwww.mapengju.com%2FPartTimeJob%2Fmchnt%2FredirectUrl&response_type=code&scope=snsapi_base&state=mchnt#wechat_redirect";
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
	public String getOpenId(
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
		
//		try {
//			String res=HttpRequestUtil.sendGet("http://www.mapengju.com/PartTimeJob/transTest/pay?totalFee=1", null);
//			logger.info(res);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return "redirect:/mchntp/login";
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
			logger.error("商户注册错误",e);
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
			logger.error("商户登录错误",e);
			return ResponseCode.FAIL;
		}

	}

	/**
	 * 修改密码
	 * @param password
	 * @param phone
	 * @param repassword
	 * @return
	 */
	@RequestMapping(value = { "repassword" })
	@ResponseBody
	public Object repassword(@RequestParam(value = "password") String password,
			@RequestParam(value = "phone") String phone,
			@RequestParam(value = "repassword") String repassword) {
		try {
			if (mchntService.updatePassword(password, phone, repassword)) {
				return ResponseCode.SUCCESS;
			} else {
				return ResponseCode.PHONE_PASSWORD_ERROR;
			}
		} catch (Exception e) {
			logger.error("商户修改密码错误",e);
			return ResponseCode.FAIL;
		}

	}
	
	
	/**
	 * 登出
	 * 
	 * @param phone
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "logout" })
	@ResponseBody
	public Object logout(@RequestParam(value = "phone") String phone,
			HttpServletRequest request) {
		try {
				HttpSession session = request.getSession();
				session.removeAttribute(CommonCanstant.MCHNT_INFO);
				return ResponseCode.SUCCESS;
		} catch (Exception e) {
			logger.error("商户登出错误",e);
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
			logger.error("检查商户手机号是否存在错误",e);
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
			logger.error("获取商户信息错误",e);
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
			logger.error("更新商户信息错误",e);
			return ResponseCode.FAIL;
		}
	}
	
	
	/**
	 * 商户充值下单借口，该接口进行下单
	 * @param totalFee 充值金额
	 * @param mchntCd	商户号
	 * @param request
	 * @return 返回js调用参数
	 */
	@RequestMapping(value = { "pay" })
	@ResponseBody
	public Object pay(@RequestParam(value = "totalFee") String totalFee,
			HttpServletRequest request){
		
		try {
			int mchntCd=getMchntInfo(request).getMchntCd();
			String ip=CommonUtil.getIpAddr(request);
			String openId=(String) request.getSession().getAttribute(TransCanstant.OPEN_ID);
			WcPay wcPay=mchntService.pay(totalFee, ip, openId, mchntCd);
			
			return wcPay;
		} catch (Exception e) {
			logger.error("商户充值下单错误",e);
			return ResponseCode.FAIL;
		}
	}
	
	/**
	 * 查询支付结果接口，如果支付成功，那么会在商户的账户加上一定金额，如果失败需要重新下单支付
	 * @param outTradeNo 订单号，下单接口会返回该参数
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "checkPay" })
	@ResponseBody
	public int checkPay(@RequestParam(value = "outTradeNo") String outTradeNo,
			HttpServletRequest request){
		try{
			int mchntCd=getMchntInfo(request).getMchntCd();
			return mchntService.checkPay(outTradeNo, mchntCd);
		}catch(Exception e){
			logger.error("检查支付结果错误",e);
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
	public int postJob(JobInfo jobInfo,long jjobStartTime, long jjobEndTime, long jjobValidateTime,
			HttpServletRequest request){
		try{
			jobInfo.setJobStartTime(new Timestamp(jjobStartTime));
			jobInfo.setJobEndTime(new Timestamp(jjobEndTime));
			jobInfo.setJobValidateTime(new Timestamp(jjobValidateTime));
			jobInfo.setJobSt(ObjectStatuCode.JOB_AUDIT);
			int mchntCd=getMchntInfo(request).getMchntCd();
			mchntService.postJob(jobInfo, mchntCd);
			return ResponseCode.SUCCESS;
		}catch(Exception e){
			logger.error("发送兼职信息错误",e);
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
			logger.error("获取兼职信息列表错误",e);
			return ResponseCode.FAIL;
		}
	}
	
	
	public Object getUserList(@RequestParam(value = "jobId") String jobId,
			HttpServletRequest request){
		try{
			return ResponseCode.SUCCESS;
		}catch(Exception e){
			logger.error("获取报名兼职信息错误",e);
			return ResponseCode.FAIL;
		}
	}
}
