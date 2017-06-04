package com.partjob.model;

import java.sql.Timestamp;

/**
 * Created by Sloriac on 2017/5/24.
 */
public class MchntSchedule {
    private int id;
    private int mchntCd;
    private String money;
    private String status;
    private String type;
    private String time;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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

   

}
