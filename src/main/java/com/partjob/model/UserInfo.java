package com.partjob.model;

import java.util.Date;

public class UserInfo {

    private Integer userId;   // 用户ID
    private String  telephone; // 手机号
    private String  username; // 姓名
    private String  ip;        // 用户IP
    private Date    onlineTime; //登录时间
    private int roleId;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    
    public Date getOnlineTime() {
        return onlineTime;
    }

    
    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }


}
