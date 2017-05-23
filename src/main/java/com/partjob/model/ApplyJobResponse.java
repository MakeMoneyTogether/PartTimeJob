package com.partjob.model;

/**
 * Created by Sloriac on 2017/5/23.
 *
 *  用户报名兼职之后的返回数据
 */
public class ApplyJobResponse {
    private int code; // 功能码
    private int applied; // 报名人数
    private int all; //总需求人数

    public ApplyJobResponse() {
    }

    public ApplyJobResponse(int code, int applied, int all) {
        this.code = code;
        this.applied = applied;
        this.all = all;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getApplied() {
        return applied;
    }

    public void setApplied(int applied) {
        this.applied = applied;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "ApplyJobResponse{" +
                "code=" + code +
                ", applied=" + applied +
                ", all=" + all +
                '}';
    }
}
