package com.partjob.model;

import java.sql.Date;

/**
 * Created by Sloriac on 2017/4/16.
 */
public class UserInfo {

    private int uid;
    private String name;
    private int userSt;
    private String gender;
    private String phone;
    private String pwd;
    private String balance;
    private String major;
    private String grade;
    private String direction;
    private Date birthday;
    private String school;
    private String shareCode;
    private String connectPhone;

    public UserInfo() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserSt() {
		return userSt;
	}

	public void setUserSt(int userSt) {
		this.userSt = userSt;
	}

	public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }

	public String getConnectPhone() {
		return connectPhone;
	}

	public void setConnectPhone(String connectPhone) {
		this.connectPhone = connectPhone;
	}
    
}
