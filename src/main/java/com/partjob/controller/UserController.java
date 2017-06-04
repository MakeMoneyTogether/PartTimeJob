package com.partjob.controller;

import java.sql.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.partjob.constant.CommonCanstant;
import com.partjob.constant.ResponseCode;
import com.partjob.constant.TransCanstant;
import com.partjob.model.UserInfo;
import com.partjob.model.WcPay;
import com.partjob.service.UserCashService;
import com.partjob.service.UserService;
import com.partjob.test.OpenIdTest;
import com.partjob.utils.CommonUtil;
import com.partjob.utils.HttpRequestUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Sloriac on 2017/5/19.
 * 用户的操作在这里
 */
@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController{

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private UserCashService userCashService;

    
    /**
	 * 用户登录入口
	 * @return
	 */
	@RequestMapping(value = "")
	public String  mchnt() {
		return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfb5bb3526bdc5da3&redirect_uri=http%3A%2F%2Fwww.mapengju.com%2FPartTimeJob%2Fuser%2FredirectUrl&response_type=code&scope=snsapi_base&state=user#wechat_redirect";
	}

	
	
	/**
	 * 获取用户oppenid的地址
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
		return "redirect:/userp/login";
	}
	
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
            return ResponseCode.PHONE_PASSWORD_ERROR;
        } else {
            logger.info("用户" + phone + "登录成功");
            //要不要存session
            session.setAttribute(CommonCanstant.USER_INFO, userInfo);
            return ResponseCode.SUCCESS;
        }
    }

    /**
     * 获取所有用户信息（管理员界面需求）
     * @return
     */
    @RequestMapping(value = "all")
    @ResponseBody
    public Object all(){
    	return userService.getAllUser();
    }
    
    /**
     * 获取某个用户信息
     * @param uid
     * @return
     */
    @RequestMapping(value = "info/{uid}")
    @ResponseBody
    public Object one(@PathVariable int uid){
    	return userService.getUser(uid);
    	
    }
    
    @RequestMapping(value = "me")
    @ResponseBody
    public Object me(@RequestParam(value = "phone") String phone,
                           @RequestParam(value = "pwd") String pwd,HttpServletRequest request) {
        UserInfo userInfo = userService.login(phone, pwd);
        if (userInfo == null){
            logger.info("没有该用户" + phone);
            return ResponseCode.PHONE_PASSWORD_ERROR;
        } else {
        	HttpSession session = request.getSession();
        	OpenIdTest.addOpenId(request);
        	session.setAttribute(CommonCanstant.USER_INFO, userInfo);
            logger.info("成功获取用户" + phone + "的信息");
        }
        // 要不要转json
        return userInfo;
    }

    @RequestMapping(value = "editcv")
    @ResponseBody
    public Object editcv(UserInfo newUserInfo,long lbirthday,HttpServletRequest request) {
    	try {
    		UserInfo userInfo =getUserInfo(request);
            if (userInfo == null){
                logger.info("用户未登录就来修改简历了");
                return ResponseCode.PHONE_PASSWORD_ERROR;
            } else {
            	newUserInfo.setBirthday(new Date(lbirthday));
                return userService.updatecv(newUserInfo, userInfo.getPhone(), userInfo.getPwd());
            }
		} catch (Exception e) {
			logger.error("更新用户简历错误",e);
			return ResponseCode.FAIL;
		}
    }


    /**
     *  查看某个用户的交易流水
     * @param phone
     * @return
     */
    @RequestMapping(value = "schedule")
    @ResponseBody
    public Object schedule(@RequestParam(value = "phone") String phone) {
        return userCashService.getByPhone(phone);
    }

    /**
     * 获取用户邀请的人
     * @param phone
     * @return
     */
    @RequestMapping(value = "invitation")
    @ResponseBody
    public Object invitation(@RequestParam(value = "phone") String phone) {
        return userService.getInvitations(phone);
    }
    
    

    /**
	 * 用户充值下单借口，该接口进行下单
	 * @param totalFee 充值金额
	 * @param request
	 * @return 返回js调用参数
	 */
	@RequestMapping(value = { "pay" })
	@ResponseBody
	public Object pay(@RequestParam(value = "totalFee") String totalFee,
			HttpServletRequest request){
		
		try {
			int uid=getUserInfo(request).getUid();
			String ip=CommonUtil.getIpAddr(request);
			String openId=(String) request.getSession().getAttribute(TransCanstant.OPEN_ID);
			WcPay wcPay=userCashService.pay(totalFee, ip, openId, uid);
			
			return wcPay;
		} catch (Exception e) {
			logger.error("用户充值下单错误",e);
			return ResponseCode.FAIL;
		}
	}
	
	/**
	 * 查询支付结果接口，如果支付成功，那么会在用户的账户加上一定金额，如果失败需要重新下单支付
	 * @param outTradeNo 订单号，下单接口会返回该参数
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "checkPay" })
	@ResponseBody
	public int checkPay(@RequestParam(value = "outTradeNo") String outTradeNo,
			HttpServletRequest request){
		try{
			int uid=getUserInfo(request).getUid();
			return userCashService.checkPay(outTradeNo, uid,getOpenId(request));
		}catch(Exception e){
			logger.error("检查支付结果错误",e);
			return ResponseCode.FAIL;
		}
	}

	/**
	 * 用户发起提现请求
	 * @param 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "cash" })
	@ResponseBody
	public int cash(@RequestParam(value = "totalFee") String totalFee,HttpServletRequest request){
		try{
			int userid = getUserInfo(request).getUid();
			String openId=(String) request.getSession().getAttribute(TransCanstant.OPEN_ID);
			int result=userCashService.cash(totalFee, openId, userid);
			return result;
		}catch (Exception e){
			logger.error("用户发起提现请求失败",e);
			return ResponseCode.FAIL;
		}
	}
	
	/**
	 * 管理员审核提现请求
	 * @param 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "checkcash" })
	@ResponseBody
	public int checkCash(@RequestParam(value = "id") int id,HttpServletRequest request){
		try{
			int result=userCashService.checkCash(id);
			return result;
		}catch (Exception e){
			logger.error("商户发起提现请求失败失败",e);
			return ResponseCode.FAIL;
		}
	}
}
