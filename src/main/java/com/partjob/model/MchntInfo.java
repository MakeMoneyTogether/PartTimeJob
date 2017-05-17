package com.partjob.model;

/**
 * Created by MPJ on 17/4/8.
 */
public class MchntInfo {

    private int mchntCd;
    private String phone;
    private String mchntAddress;
    private String mchntName;
    private String connName;
    private String connPhone;
    private int mchntSt;
    private String  balance;
    private String frozenMoney;

    public int getMchntCd() {
        return mchntCd;
    }

    public void setMchntCd(int mchntCd) {
        this.mchntCd = mchntCd;
    }

    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

	public String getFrozenMoney() {
		return frozenMoney;
	}

	public void setFrozenMoney(String frozenMoney) {
		this.frozenMoney = frozenMoney;
	}
    
    
}
