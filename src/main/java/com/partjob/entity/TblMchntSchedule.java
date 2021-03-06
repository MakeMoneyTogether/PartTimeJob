package com.partjob.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Created by Sloriac on 2017/5/24.
 */
@Entity
@Table(name = "tbl_mchnt_schedule", schema = "parttimejob", catalog = "")
public class TblMchntSchedule {
    private int id;
    private int mchntCd;
    private int money;
    private int status;
    private String type;
    private String openid;
    private Date time;
    
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 
    @Basic
    @Column(name = "mchntCd", nullable = false)
    public int getMchntCd() {
		return mchntCd;
	}

	public void setMchntCd(int mchntCd) {
		this.mchntCd = mchntCd;
	}

	@Basic
    @Column(name = "money", nullable = false, precision = 0)
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    @Basic
    @Column(name = "type", nullable = false, precision = 0)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Basic
	@Column(name = "openid", nullable = false, precision = 0)
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	 @Basic
	 @Column(name = "time", nullable = false, precision = 0)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
