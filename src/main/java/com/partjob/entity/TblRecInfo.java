package com.partjob.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Sloriac on 2017/4/16.
 */
@Entity
@Table(name = "tbl_rec_info", schema = "parttimejob", catalog = "")
public class TblRecInfo {
    private int recId;
    private int userId;
    private int mchntCd;
    private int jobId;
    private Integer recSt;
    private Integer recScore;
    private Timestamp recCrtTime;

    @Id
    @Column(name = "rec_id", nullable = false)
    public int getRecId() {
        return recId;
    }

    public void setRecId(int recId) {
        this.recId = recId;
    }
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	 @Column(name = "mchnt_cd", nullable = false)
	public int getMchntCd() {
		return mchntCd;
	}

	public void setMchntCd(int mchntCd) {
		this.mchntCd = mchntCd;
	}
	 @Column(name = "job_id", nullable = false)
	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	@Basic
    @Column(name = "rec_st", nullable = true)
    public Integer getRecSt() {
        return recSt;
    }

    public void setRecSt(Integer recSt) {
        this.recSt = recSt;
    }

    @Basic
    @Column(name = "rec_score", nullable = true)
    public Integer getRecScore() {
        return recScore;
    }

    public void setRecScore(Integer recScore) {
        this.recScore = recScore;
    }

    @Basic
    @Column(name = "rec_crt_time", nullable = true)
    public Timestamp getRecCrtTime() {
        return recCrtTime;
    }

    public void setRecCrtTime(Timestamp recCrtTime) {
        this.recCrtTime = recCrtTime;
    }


}
