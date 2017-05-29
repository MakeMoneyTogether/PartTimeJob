package com.partjob.model;

/**
 * Created by Sloriac on 17/5/27.
 */
public class InvitationRecord {
    private int uid;
    private int status;
    private int invitorId;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getInvitorId() {
        return invitorId;
    }

    public void setInvitorId(int invitorId) {
        this.invitorId = invitorId;
    }
}
