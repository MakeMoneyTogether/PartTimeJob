package com.partjob.model;

import java.sql.Timestamp;

public class JobInfo {

	 	private int jobId;
	    private String jobTitle;
	    private Integer jobType;
	    private String jobTypeName;
	    private Integer paymentType;
	    private String paymentMoney;
	    private Timestamp jobStartTime;
	    private Timestamp jobEndTime;
	    private int hoursDay;
	    private Integer numPeople;
	    private String jobDesc;
	    private String jobAddress;
	    private Timestamp jobValidateTime;
	    private String connectName;
	    private String connectPhone;
	    private Integer jobSt;
	    private Integer joinNum;
	    private Integer cityCode;
	    private Integer userJobStatu;
	    private String mchntName;
	    private String sex;
		public int getJobId() {
			return jobId;
		}
		public void setJobId(int jobId) {
			this.jobId = jobId;
		}
		
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getJobTitle() {
			return jobTitle;
		}
		public void setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
		}
		public Integer getJobType() {
			return jobType;
		}
		public void setJobType(Integer jobType) {
			this.jobType = jobType;
		}
		
		
		public String getJobTypeName() {
			return jobTypeName;
		}
		public void setJobTypeName(String jobTypeName) {
			this.jobTypeName = jobTypeName;
		}
		public Integer getPaymentType() {
			return paymentType;
		}
		public void setPaymentType(Integer paymentType) {
			this.paymentType = paymentType;
		}
		public String getPaymentMoney() {
			return paymentMoney;
		}
		public void setPaymentMoney(String paymentMoney) {
			this.paymentMoney = paymentMoney;
		}
		public Timestamp getJobStartTime() {
			return jobStartTime;
		}
		public void setJobStartTime(Timestamp jobStartTime) {
			this.jobStartTime = jobStartTime;
		}
		public Timestamp getJobEndTime() {
			return jobEndTime;
		}
		public void setJobEndTime(Timestamp jobEndTime) {
			this.jobEndTime = jobEndTime;
		}
		public Integer getNumPeople() {
			return numPeople;
		}
		public void setNumPeople(Integer numPeople) {
			this.numPeople = numPeople;
		}
		public String getJobDesc() {
			return jobDesc;
		}
		public void setJobDesc(String jobDesc) {
			this.jobDesc = jobDesc;
		}
		public String getJobAddress() {
			return jobAddress;
		}
		public void setJobAddress(String jobAddress) {
			this.jobAddress = jobAddress;
		}
		public Timestamp getJobValidateTime() {
			return jobValidateTime;
		}
		public void setJobValidateTime(Timestamp jobValidateTime) {
			this.jobValidateTime = jobValidateTime;
		}
		public String getConnectName() {
			return connectName;
		}
		public void setConnectName(String connectName) {
			this.connectName = connectName;
		}
		
		public String getConnectPhone() {
			return connectPhone;
		}
		public void setConnectPhone(String connectPhone) {
			this.connectPhone = connectPhone;
		}
		public Integer getJobSt() {
			return jobSt;
		}
		public void setJobSt(Integer jobSt) {
			this.jobSt = jobSt;
		}
		public Integer getJoinNum() {
			return joinNum;
		}
		public void setJoinNum(Integer joinNum) {
			this.joinNum = joinNum;
		}
		public Integer getCityCode() {
			return cityCode;
		}
		public void setCityCode(Integer cityCode) {
			this.cityCode = cityCode;
		}
		public Integer getUserJobStatu() {
			return userJobStatu;
		}
		public void setUserJobStatu(Integer userJobStatu) {
			this.userJobStatu = userJobStatu;
		}
		public int getHoursDay() {
			return hoursDay;
		}
		public void setHoursDay(int hoursDay) {
			this.hoursDay = hoursDay;
		}
		public String getMchntName() {
			return mchntName;
		}
		public void setMchntName(String mchntName) {
			this.mchntName = mchntName;
		}
}
