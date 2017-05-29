package com.partjob.entity;

import javax.persistence.*;

/**
 * Created by Sloriac on 17/5/27.
 */
@Entity
@Table(name = "tbl_invitation_record", schema = "parttimejob", catalog = "")
public class TblInvitationRecord {
    private int uid;
    private int status;
    private int invitorId;

    @Id
    @Column(name = "uid")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "invitor_id")
    public int getInvitorId() {
        return invitorId;
    }

    public void setInvitorId(int invitorId) {
        this.invitorId = invitorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblInvitationRecord record = (TblInvitationRecord) o;

        if (uid != record.uid) return false;
        if (status != record.status) return false;
        if (invitorId != record.invitorId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + status;
        result = 31 * result + invitorId;
        return result;
    }
}
