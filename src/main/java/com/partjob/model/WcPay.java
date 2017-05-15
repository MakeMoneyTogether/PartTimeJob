package com.partjob.model;

public class WcPay {

	private String appId;
	private String timeStamp;
	private String nonceStr;
	private String wcPackage;
	private String signType;
	private String paySign;
	
	private String outTradeNO;
	
	public String getOutTradeNO() {
		return outTradeNO;
	}
	public void setOutTradeNO(String outTradeNO) {
		this.outTradeNO = outTradeNO;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getWcPackage() {
		return wcPackage;
	}
	public void setWcPackage(String wcPackage) {
		this.wcPackage = wcPackage;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
	
}
