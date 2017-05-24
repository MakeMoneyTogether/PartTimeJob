package com.partjob.entity;

import javax.persistence.*;

/**
 * Created by Sloriac on 2017/5/24.
 */
@Entity
@Table(name = "tbl_user_schedule", schema = "parttimejob", catalog = "")
public class TblUserSchedule {
    private int id;
    private int uid;
    private double money;
    private int status;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uid", nullable = false)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "money", nullable = false, precision = 0)
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblUserSchedule that = (TblUserSchedule) o;

        if (id != that.id) return false;
        if (uid != that.uid) return false;
        if (Double.compare(that.money, money) != 0) return false;
        if (status != that.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + uid;
        temp = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + status;
        return result;
    }
}
