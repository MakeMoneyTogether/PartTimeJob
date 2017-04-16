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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblRecInfo that = (TblRecInfo) o;

        if (recId != that.recId) return false;
        if (recSt != null ? !recSt.equals(that.recSt) : that.recSt != null) return false;
        if (recScore != null ? !recScore.equals(that.recScore) : that.recScore != null) return false;
        if (recCrtTime != null ? !recCrtTime.equals(that.recCrtTime) : that.recCrtTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = recId;
        result = 31 * result + (recSt != null ? recSt.hashCode() : 0);
        result = 31 * result + (recScore != null ? recScore.hashCode() : 0);
        result = 31 * result + (recCrtTime != null ? recCrtTime.hashCode() : 0);
        return result;
    }
}
