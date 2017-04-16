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

    @Id
    @Column(name = "job_id", nullable = false)
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
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
    @Column(name = "csonnect_iphone", nullable = true)
    public Integer getCsonnectIphone() {
        return csonnectIphone;
    }

    public void setCsonnectIphone(Integer csonnectIphone) {
        this.csonnectIphone = csonnectIphone;
    }

    @Basic
    @Column(name = "job_st", nullable = true)
    public Integer getJobSt() {
        return jobSt;
    }

    public void setJobSt(Integer jobSt) {
        this.jobSt = jobSt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblJobInfo that = (TblJobInfo) o;

        if (jobId != that.jobId) return false;
        if (jobTitle != null ? !jobTitle.equals(that.jobTitle) : that.jobTitle != null) return false;
        if (jobType != null ? !jobType.equals(that.jobType) : that.jobType != null) return false;
        if (paymentType != null ? !paymentType.equals(that.paymentType) : that.paymentType != null) return false;
        if (paymentMoney != null ? !paymentMoney.equals(that.paymentMoney) : that.paymentMoney != null) return false;
        if (jobStartTime != null ? !jobStartTime.equals(that.jobStartTime) : that.jobStartTime != null) return false;
        if (jobEndTime != null ? !jobEndTime.equals(that.jobEndTime) : that.jobEndTime != null) return false;
        if (numPeople != null ? !numPeople.equals(that.numPeople) : that.numPeople != null) return false;
        if (jobDesc != null ? !jobDesc.equals(that.jobDesc) : that.jobDesc != null) return false;
        if (jobAddress != null ? !jobAddress.equals(that.jobAddress) : that.jobAddress != null) return false;
        if (jobValidateTime != null ? !jobValidateTime.equals(that.jobValidateTime) : that.jobValidateTime != null)
            return false;
        if (connectName != null ? !connectName.equals(that.connectName) : that.connectName != null) return false;
        if (csonnectIphone != null ? !csonnectIphone.equals(that.csonnectIphone) : that.csonnectIphone != null)
            return false;
        if (jobSt != null ? !jobSt.equals(that.jobSt) : that.jobSt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobId;
        result = 31 * result + (jobTitle != null ? jobTitle.hashCode() : 0);
        result = 31 * result + (jobType != null ? jobType.hashCode() : 0);
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        result = 31 * result + (paymentMoney != null ? paymentMoney.hashCode() : 0);
        result = 31 * result + (jobStartTime != null ? jobStartTime.hashCode() : 0);
        result = 31 * result + (jobEndTime != null ? jobEndTime.hashCode() : 0);
        result = 31 * result + (numPeople != null ? numPeople.hashCode() : 0);
        result = 31 * result + (jobDesc != null ? jobDesc.hashCode() : 0);
        result = 31 * result + (jobAddress != null ? jobAddress.hashCode() : 0);
        result = 31 * result + (jobValidateTime != null ? jobValidateTime.hashCode() : 0);
        result = 31 * result + (connectName != null ? connectName.hashCode() : 0);
        result = 31 * result + (csonnectIphone != null ? csonnectIphone.hashCode() : 0);
        result = 31 * result + (jobSt != null ? jobSt.hashCode() : 0);
        return result;
    }
}
