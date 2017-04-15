package com.partjob.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.partjob.constant.CommonCanstant;
import com.partjob.constant.ResponseCode;
import com.partjob.model.MchntInfo;
import com.partjob.service.MchntService;

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
public class MchntController extends BaseController{
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private MchntService mchntService;
    
    /**
     * 注册
     * @param mchntInfo
     * @param password
     * 
     * @return
     */
    @RequestMapping(value = { "register" })
    @ResponseBody
    public Object register(MchntInfo mchntInfo,
                           @RequestParam(value="password")String password
                           ){

      
        try{
            mchntService.saveMchnt(mchntInfo,password);
        }catch(Exception e){
        	logger.error(e);
            return ResponseCode.FAIL;
        }

        return ResponseCode.SUCCESS;
    }
    
    /**
     * 登录
     * @param password
     * @param phone
     * @param request
     * @return
     */
    @RequestMapping(value = { "login" })
    @ResponseBody
    public Object login( @RequestParam(value="password")String password,
    		@RequestParam(value="phone")String phone,HttpServletRequest request){
    	try{
    		MchntInfo mchntInfo=mchntService.logoin(password, phone);
        	if(mchntInfo!=null){
        		HttpSession session = request.getSession();
            	session.setAttribute(CommonCanstant.MCHNT_INFO, mchntInfo);
            	
            	return ResponseCode.SUCCESS;
        	}else{
        		return ResponseCode.PHONE_PASSWORD_ERROR;
        	}
    	}catch(Exception e){
    		logger.error(e);
    		 return ResponseCode.FAIL;
    	}
    	
    }
    
    /**
     * 检查手机号是否存在
     * @param phone
     * @param request
     * @return
     */
    @RequestMapping(value = { "checkPhone" })
    @ResponseBody
    public Object checkPhone(@RequestParam(value="phone")String phone,HttpServletRequest request){
    	try{
    		boolean flag=mchntService.checkPhone(phone,getMchntInfo(request));
    		if(flag==true){
    			return ResponseCode.PHONE_EXIST;
    		}else{
    			return ResponseCode.SUCCESS;
    		}
    	}catch(Exception e){
    		logger.error(e);
    		return ResponseCode.FAIL;
    	}
    	
    }
    
    /**
     * 获取商户信息
     * @param request
     * @return
     */
    @RequestMapping(value = { "getMchntInfo" })
    @ResponseBody
    public Object prepareData(HttpServletRequest request){
    	try{
    		return mchntService.getMchntInfo(getMchntInfo(request).getMchntCd());
    	}catch(Exception e){
    		logger.error(e);
    		return ResponseCode.FAIL;
    	}
    }
    
    /**
     * 更新商户信息
     * @param mchntInfo
     * @param request
     * @return
     */
    @RequestMapping(value = { "update" })
    @ResponseBody
    public Object updateMchntInfo(MchntInfo mchntInfo,HttpServletRequest request){
    	try{
    		//检查是否具有更新权限
    		if(mchntInfo.getMchntCd()!=getMchntInfo(request).getMchntCd()){
    			return ResponseCode.FORBIDDEN;
    		}
    		mchntService.updateMchtInfo(mchntInfo);
    		return ResponseCode.SUCCESS;
    	}catch(Exception e){
    		logger.error(e);
    		return ResponseCode.FAIL;
    	}
    }
}
