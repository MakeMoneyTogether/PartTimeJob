package com.partjob.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.partjob.constant.CommonCanstant;
import com.partjob.constant.ResponseCode;
import com.partjob.constant.TransCanstant;
import com.partjob.model.CashTransResult;
import com.partjob.model.CashTransaction;
import com.partjob.model.TransResult;
import com.partjob.model.Transaction;
import com.partjob.model.WcPay;
import com.partjob.utils.CommonUtil;
import com.partjob.utils.HttpRequestUtil;

@Service
@Transactional
public class TransService {
	private final Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 付款
	 * @param totalFee 付款金额
	 * @param ip 终端ip
	 * @param openId	用户标识
	 */
	public WcPay pay(String totalFee, String ip,String openId) {
		logger.info("支付步骤2");
		WcPay wcPay=null;
		String outTradeNo=CommonUtil.getCurrentDay()+CommonUtil.createRandomVcode();
		Transaction trans=setTrans(outTradeNo, totalFee, ip,openId);
		logger.info("支付步骤2.1"+trans);
		String param=CommonUtil.obj2xml(trans);
		logger.info("支付步骤2.2"+param);
		String result=HttpRequestUtil.sendPost(TransCanstant.PAY_URL, param);
		logger.info("支付步骤3"+result);
		try{
			TransResult transResult=CommonUtil.xml2Object(result, TransResult.class);
			if("SUCCESS".equals(transResult.getReturn_code())&&"SUCCESS".equals(transResult.getResult_code())){
				logger.info("支付步骤4：success");
				wcPay=setWcPay(transResult.getPrepay_id());
				return wcPay;
			}else{
				logger.info("支付步骤4：fail");
				return wcPay;
			}
		}catch(Exception e){
			logger.info("支付步骤4：fail");
			return wcPay;
		}
		
		
	}
	
	public int cash(String amount,String openId){
		String partnerTradeNo=CommonUtil.getCurrentDay()+CommonUtil.createRandomVcode();
		CashTransaction trans=setCashTrans(partnerTradeNo, amount, openId);
		String param=CommonUtil.obj2xml(trans);
		logger.info(param);
		String result;
		try {
			result = HttpRequestUtil.sendSSLPost(TransCanstant.PAY_URL, param);
			logger.info(result);
			CashTransResult transResult=CommonUtil.xml2Object(result, CashTransResult.class);
			if("SUCCESS".equals(transResult.getReturn_code())&&"SUCCESS".equals(transResult.getResult_code())){
				return ResponseCode.SUCCESS;
			}
			return ResponseCode.FAIL;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseCode.FAIL;
		}
		
		
	}

	private WcPay setWcPay(String prepayId){
		WcPay wcPay=new WcPay();
		wcPay.setAppId(TransCanstant.APP_ID);
		wcPay.setNonceStr(CommonUtil.toMD5(CommonUtil.createRandomVcode()));
		wcPay.setTimeStamp(Long.toString((new Date()).getTime()));
		wcPay.setWcPackage("prepay_id="+prepayId);
		wcPay.setSignType("MD5");
		wcPay.setPaySign(sign(wcPay));
		return wcPay;
	}
	/**
	 * 包装付款参数
	 * @param outTradeNo
	 * @param totalFee
	 * @param ip
	 * @param openId
	 * @return
	 */
	private Transaction setTrans(String outTradeNo, String totalFee, String ip,String openId) {
		Transaction trans = new Transaction();

		trans.setAppid(TransCanstant.APP_ID);
		trans.setMch_id(TransCanstant.MCHNT_ID);
		trans.setNonce_str(CommonUtil.toMD5(CommonUtil.createRandomVcode()));
		trans.setBody(TransCanstant.BODY);
		trans.setOut_trade_no(outTradeNo);
		trans.setTotal_fee(totalFee);
		trans.setSpbill_create_ip(ip);
		trans.setNotify_url(TransCanstant.NOTIFY_URL);
		trans.setTrade_type(TransCanstant.TRADE_TYPE);
		trans.setOpenid(openId);
		trans.setSign(sign(trans));

		return trans;
	}
	
	
	private CashTransaction setCashTrans(String partnerTradeNo,String amount,String openId){
		CashTransaction trans=new CashTransaction();
		
		trans.setMch_appid(TransCanstant.APP_ID);
		trans.setMchid(TransCanstant.MCHNT_ID);
		trans.setNonce_str(CommonUtil.toMD5(CommonUtil.createRandomVcode()));
		trans.setPartner_trade_no(partnerTradeNo);
		trans.setOpenid(openId);;
		trans.setCheck_name(TransCanstant.CHECK_NAME);
		trans.setAmount(amount);
		trans.setDesc(TransCanstant.DESC);
		trans.setSpbill_create_ip(CommonCanstant.IP);
		trans.setSign(sign(trans));
		
		return trans;
	}

	/**
	 * 签名
	 * @param trans
	 * @return
	 */
	private String sign(Object trans) {
		Map<String, Object> map;
		try {
			map = CommonUtil.objectToMap(trans);

			ArrayList<String> list = new ArrayList<String>();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				if ( !StringUtils.isEmpty(entry.getValue())) {
					if(entry.getKey().equalsIgnoreCase("wcPackage")){
						list.add("package=" + entry.getValue() + "&");
					}else{
						list.add(entry.getKey() + "=" + entry.getValue() + "&");
					}
					
				}
			}
			int size = list.size();
			String[] arrayToSort = list.toArray(new String[size]);
			Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < size; i++) {
				sb.append(arrayToSort[i]);
			}
			String result = sb.toString();
			result += "key=" + TransCanstant.KEY;
			// Util.log("Sign Before MD5:" + result);
//			System.out.println(result);
			result = CommonUtil.toMD5(result).toUpperCase();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void main(String[] args) {
//		String totalFee="100";
//		String ip="132.13.255.2";
//		TransService transService=new TransService();
//		transService.pay(totalFee, ip,"123");
		

		String str="<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx2421b1c4370ec43b]]></appid><mch_id><![CDATA[10000100]]></mch_id><nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str><openid><![CDATA[oUpF8uMuAJO_M2pxb1Q9zNjWeS6o]]></openid><sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign><result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id><trade_type><![CDATA[JSAPI]]></trade_type></xml>";
		TransResult result=CommonUtil.xml2Object(str, TransResult.class);
		result.getReturn_code();
		
	}
}
