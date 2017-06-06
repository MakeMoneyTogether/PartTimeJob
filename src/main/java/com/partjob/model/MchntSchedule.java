package com.partjob.model;

import java.sql.Timestamp;

/**
 * Created by Sloriac on 2017/5/24.
 */
public class MchntSchedule {
    private int id;
    private int mchntCd;
    private String money;
    private int status;
    private String type;
    private String time;
    private String mname;
    private String cname;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  
	public int getMchntCd() {
		return mchntCd;
	}

	public void setMchntCd(int mchntCd) {
		this.mchntCd = mchntCd;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
