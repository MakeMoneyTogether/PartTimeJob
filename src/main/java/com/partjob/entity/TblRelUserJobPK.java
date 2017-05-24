package com.partjob.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Sloriac on 2017/5/24.
 */
public class TblRelUserJobPK implements Serializable {
    private int uid;
    private int jobId;

    @Column(name = "uid", nullable = false)
    @Id
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Column(name = "job_id", nullable = false)
    @Id
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblRelUserJobPK that = (TblRelUserJobPK) o;

        if (uid != that.uid) return false;
        if (jobId != that.jobId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + jobId;
        return result;
    }
}
