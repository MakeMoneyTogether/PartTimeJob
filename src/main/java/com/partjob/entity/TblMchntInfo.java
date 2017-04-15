package com.partjob.entity;


import javax.persistence.*;

@Entity
@Table(name = "tbl_mchnt_info")
public class TblMchntInfo implements java.io.Serializable{
    private int mchntCd;
    private String mchntAddress;
    private String mchntName;
    private String phone;
    private String connName;
    private String connPhone;
    private String password;
    private int mchntSt;
    private int balance;

    public TblMchntInfo() {
    }

    @Id

    @Column(name = "mchnt_cd", unique = true)
    public int getMchntCd() {
        return mchntCd;
    }

    public void setMchntCd(int mchntCd) {
        this.mchntCd = mchntCd;
    }
    @Column(name = "mchnt_address")
    public String getMchntAddress() {
        return mchntAddress;
    }

    public void setMchntAddress(String mchntAddress) {
        this.mchntAddress = mchntAddress;
    }
    @Column(name = "mchnt_name")
    public String getMchntName() {
        return mchntName;
    }

    public void setMchntName(String mchntName) {
        this.mchntName = mchntName;
    }
    @Column(name = "conn_name")
    public String getConnName() {
        return connName;
    }

    public void setConnName(String connName) {
        this.connName = connName;
    }
    @Column(name = "phone")
    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "conn_phone")
    public String getConnPhone() {
        return connPhone;
    }

    public void setConnPhone(String connPhone) {
        this.connPhone = connPhone;
    }
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "mchnt_st")
    public int getMchntSt() {
        return mchntSt;
    }

    public void setMchntSt(int mchntSt) {
        this.mchntSt = mchntSt;
    }
    @Column(name = "balance")
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
