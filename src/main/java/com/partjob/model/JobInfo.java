package com.partjob.model;

import java.sql.Timestamp;

public class JobInfo {

	 	private int jobId;
	    private String jobTitle;
	    private Integer jobType;
	    private Integer paymentType;
	    private Integer paymentMoney;
	    private Timestamp jobStartTime;
	    private Timestamp jobEndTime;
	    private Integer numPeople;
	    private String jobDesc;
	    private String jobAddress;
	    private Timestamp jobValidateTime;
	    private String connectName;
	    private Integer csonnectIphone;
	    private Integer jobSt;
		public int getJobId() {
			return jobId;
		}
		public void setJobId(int jobId) {
			this.jobId = jobId;
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
		public Integer getPaymentType() {
			return paymentType;
		}
		public void setPaymentType(Integer paymentType) {
			this.paymentType = paymentType;
		}
		public Integer getPaymentMoney() {
			return paymentMoney;
		}
		public void setPaymentMoney(Integer paymentMoney) {
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
		public Integer getCsonnectIphone() {
			return csonnectIphone;
		}
		public void setCsonnectIphone(Integer csonnectIphone) {
			this.csonnectIphone = csonnectIphone;
		}
		public Integer getJobSt() {
			return jobSt;
		}
		public void setJobSt(Integer jobSt) {
			this.jobSt = jobSt;
		}
	    
	    
	    

}