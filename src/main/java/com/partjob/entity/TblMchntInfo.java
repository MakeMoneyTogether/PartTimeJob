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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblMchntInfo that = (TblMchntInfo) o;

        if (mchntCd != that.mchntCd) return false;
        if (mchntAddress != null ? !mchntAddress.equals(that.mchntAddress) : that.mchntAddress != null) return false;
        if (mchntName != null ? !mchntName.equals(that.mchntName) : that.mchntName != null) return false;
        if (connName != null ? !connName.equals(that.connName) : that.connName != null) return false;
        if (connPhone != null ? !connPhone.equals(that.connPhone) : that.connPhone != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (mchntSt != null ? !mchntSt.equals(that.mchntSt) : that.mchntSt != null) return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mchntCd;
        result = 31 * result + (mchntAddress != null ? mchntAddress.hashCode() : 0);
        result = 31 * result + (mchntName != null ? mchntName.hashCode() : 0);
        result = 31 * result + (connName != null ? connName.hashCode() : 0);
        result = 31 * result + (connPhone != null ? connPhone.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (mchntSt != null ? mchntSt.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
