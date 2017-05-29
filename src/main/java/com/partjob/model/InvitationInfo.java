package com.partjob.model;

/**
 * Created by Sloriac on 17/5/27.
 */
public class InvitationInfo {

    private String name;
    private int status;

    public InvitationInfo(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
