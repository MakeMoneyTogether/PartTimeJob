package com.partjob.entity;

import javax.persistence.*;

/**
 * Created by Sloriac on 2017/4/16.
 */
@Entity
@Table(name = "tbl_mchnt_info", schema = "parttimejob", catalog = "")
public class TblMchntInfo {
    private int mchntCd;
    private String mchntAddress;
    private String mchntName;
    private String connName;
    private String connPhone;
    private String password;
    private Integer mchntSt;
    private Integer balance;
    private String phone;

    @Id
    @Column(name = "mchnt_cd", nullable = false)
    public int getMchntCd() {
        return mchntCd;
    }

    public void setMchntCd(int mchntCd) {
        this.mchntCd = mchntCd;
    }

    @Basic
    @Column(name = "mchnt_address", nullable = true, length = 255)
    public String getMchntAddress() {
        return mchntAddress;
    }

    public void setMchntAddress(String mchntAddress) {
        this.mchntAddress = mchntAddress;
    }

    @Basic
    @Column(name = "mchnt_name", nullable = true, length = 255)
    public String getMchntName() {
        return mchntName;
    }

    public void setMchntName(String mchntName) {
        this.mchntName = mchntName;
    }

    @Basic
    @Column(name = "conn_name", nullable = true, length = 255)
    public String getConnName() {
        return connName;
    }

    public void setConnName(String connName) {
        this.connName = connName;
    }

    @Basic
    @Column(name = "conn_phone", nullable = true, length = 255)
    public String getConnPhone() {
        return connPhone;
    }

    public void setConnPhone(String connPhone) {
        this.connPhone = connPhone;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "mchnt_st", nullable = true)
    public Integer getMchntSt() {
        return mchntSt;
    }

    public void setMchntSt(Integer mchntSt) {
        this.mchntSt = mchntSt;
    }

    @Basic
    @Column(name = "balance", nullable = true)
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 255)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
