package com.partjob.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Sloriac on 2017/4/16.
 */
@Entity
@Table(name = "tbl_job_info", schema = "parttimejob", catalog = "")
public class TblJobInfo {
    private int jobId;
    private int mchntCd;
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
    private String connectPhone;
    private Integer jobSt;

    @Id
    @Column(name = "job_id", nullable = false)
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
    
    @Basic
    @Column(name = "mchnt_cd", nullable = true, length = 10)
    public int getMchntCd() {
		return mchntCd;
	}

	public void setMchntCd(int mchntCd) {
		this.mchntCd = mchntCd;
	}

	@Basic
    @Column(name = "job_title", nullable = true, length = 30)
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Basic
    @Column(name = "job_type", nullable = true)
    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    @Basic
    @Column(name = "payment_type", nullable = true)
    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    @Basic
    @Column(name = "payment_money", nullable = true)
    public Integer getPaymentMoney() {
        return paymentMoney;
    }

    public void setPaymentMoney(Integer paymentMoney) {
        this.paymentMoney = paymentMoney;
    }

    @Basic
    @Column(name = "job_start_time", nullable = true)
    public Timestamp getJobStartTime() {
        return jobStartTime;
    }

    public void setJobStartTime(Timestamp jobStartTime) {
        this.jobStartTime = jobStartTime;
    }

    @Basic
    @Column(name = "job_end_time", nullable = true)
    public Timestamp getJobEndTime() {
        return jobEndTime;
    }

    public void setJobEndTime(Timestamp jobEndTime) {
        this.jobEndTime = jobEndTime;
    }

    @Basic
    @Column(name = "num_people", nullable = true)
    public Integer getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(Integer numPeople) {
        this.numPeople = numPeople;
    }

    @Basic
    @Column(name = "job_desc", nullable = true, length = -1)
    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    @Basic
    @Column(name = "job_address", nullable = true, length = 100)
    public String getJobAddress() {
        return jobAddress;
    }

    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
    }

    @Basic
    @Column(name = "job_validate_time", nullable = true)
    public Timestamp getJobValidateTime() {
        return jobValidateTime;
    }

    public void setJobValidateTime(Timestamp jobValidateTime) {
        this.jobValidateTime = jobValidateTime;
    }

    @Basic
    @Column(name = "connect_name", nullable = true, length = 20)
    public String getConnectName() {
        return connectName;
    }

    public void setConnectName(String connectName) {
        this.connectName = connectName;
    }

    @Basic
    @Column(name = "connect_phone", nullable = true)
    public String getConnectPhone() {
        return connectPhone;
    }

    public void setConnectPhone(String connectPhone) {
        this.connectPhone = connectPhone;
    }

    @Basic
    @Column(name = "job_st", nullable = true)
    public Integer getJobSt() {
        return jobSt;
    }

    public void setJobSt(Integer jobSt) {
        this.jobSt = jobSt;
    }

}
