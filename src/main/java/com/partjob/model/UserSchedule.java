package com.partjob.model;

import java.sql.Timestamp;

/**
 * Created by Sloriac on 2017/5/24.
 */
public class UserSchedule {
    private int id;
    private int uid;
    private double money;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
