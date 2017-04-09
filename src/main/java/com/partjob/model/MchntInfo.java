package com.partjob.model;

/**
 * Created by MPJ on 17/4/8.
 */
public class MchntInfo {

    private int mchntCd;
    private String mchntAddress;
    private String mchntName;
    private String connName;
    private String connPhone;
    private int mchntSt;
    private int balance;

    public int getMchntCd() {
        return mchntCd;
    }

    public void setMchntCd(int mchntCd) {
        this.mchntCd = mchntCd;
    }

    public String getMchntAddress() {
        return mchntAddress;
    }

    public void setMchntAddress(String mchntAddress) {
        this.mchntAddress = mchntAddress;
    }

    public String getMchntName() {
        return mchntName;
    }

    public void setMchntName(String mchntName) {
        this.mchntName = mchntName;
    }

    public String getConnName() {
        return connName;
    }

    public void setConnName(String connName) {
        this.connName = connName;
    }

    public String getConnPhone() {
        return connPhone;
    }

    public void setConnPhone(String connPhone) {
        this.connPhone = connPhone;
    }

    public int getMchntSt() {
        return mchntSt;
    }

    public void setMchntSt(int mchntSt) {
        this.mchntSt = mchntSt;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
