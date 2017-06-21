package com.partjob.utils;

import java.util.Random;

import com.partjob.constant.CommonCanstant;
import com.partjob.constant.ResponseCode;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class VerificationUtil {

	public static String genCode(){
		Random random = new Random();
		StringBuffer code = new StringBuffer();
		for(int i=0;i<6;i++){
			code.append(random.nextInt(10));
		}
		return new String(code);
	}
	
	/**
	 * 用户注册验证码发送
	 * @param phone
	 * @param code
	 * @return
	 */
	public static int sendRegistCode(String phone,String code){
		String text = "{code:'"+code+"',product:'"+"小蜜蜂兼职"+"'}";
		return sendMsg(phone, text, CommonCanstant.SMS_TEMP_REGISTER);
	}
	
	/**
	 * 商户注册验证码发送
	 * @param phone
	 * @param code
	 * @return
	 */
	public static int sendRegistMode(String phone,String code){
		String text = "{code:'"+code+"',product:'"+"小蜜蜂兼职商家"+"'}";
		return sendMsg(phone, text, CommonCanstant.SMS_TEMP_REGISTER);
	}
	
	/**
	 * 发送活动确认通知
	 * 勤劳的的小蜜蜂${name}您好，您已报名${mchnt}发布的${job}，请确认谢谢。如有意外请及时联系商家。
	 * @return
	 */
	public static int sendNotify(String phone,String name,String mchnt,String job){
		String text = "{name:'"+name+"',mchnt:'"+mchnt+"',job:'"+job+"'}";
		return sendMsg(phone, text, CommonCanstant.SMS_TEMP_NOTIFY);
	}
	
	public static int sendMsg(String phone,String text,String SMSTemplateCode){
		TaobaoClient client = new DefaultTaobaoClient(CommonCanstant.SMS_URL, CommonCanstant.SMS_APPKEY	, CommonCanstant.SMS_SECRET);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend( "" );
		req.setSmsType(CommonCanstant.SMS_TYPE);
		req.setSmsFreeSignName( CommonCanstant.SMS_SIGN_NAME );
		req.setSmsParamString( text);
		req.setRecNum( phone );
		req.setSmsTemplateCode(SMSTemplateCode);
		AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			System.out.println(rsp.getBody());
			return ResponseCode.SUCCESS;
		} catch (ApiException e) {
			return ResponseCode.FAIL;
		}
	}
	
//	public static void main(String args[]){
//		sendRegistCode("13222765139",genCode());
//	}
}
