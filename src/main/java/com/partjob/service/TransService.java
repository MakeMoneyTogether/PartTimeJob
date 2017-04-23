package com.partjob.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.partjob.constant.TransCanstant;
import com.partjob.model.Transaction;
import com.partjob.utils.CommonUtil;
import com.partjob.utils.HttpRequestUtil;

@Service
@Transactional
public class TransService {

	public void pay(String totalFee, String ip,String openId) {

		String outTradeNo="";
		Transaction trans=setTrans(outTradeNo, totalFee, ip,openId);
		String param=CommonUtil.obj2xml(trans, Transaction.class)
				.substring(56);
		System.out.println(param);
		String result=HttpRequestUtil.sendPost(TransCanstant.PAY_URL, param);
		System.out.println(result);
	}

	
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

	private String sign(Transaction trans) {
		Map<String, Object> map;
		try {
			map = CommonUtil.objectToMap(trans);

			ArrayList<String> list = new ArrayList<String>();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				if ( !StringUtils.isEmpty(entry.getValue())) {
					list.add(entry.getKey() + "=" + entry.getValue() + "&");
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
	
//	
//	public static void main(String[] args) {
//		String totalFee="100";
//		String ip="132.13.255.2";
//		TransService transService=new TransService();
//		transService.pay(totalFee, ip);
//	}
}
