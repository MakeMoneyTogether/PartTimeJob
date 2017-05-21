package com.partjob.entity;

import javax.persistence.*;

/**
 * Created by Sloriac on 2017/5/21.
 */
@Entity
@Table(name = "tbl_net_job", schema = "parttimejob", catalog = "")
public class TblNetJob {
    private int jobId;
    private String jobTitle;
    private String jobDesc;
    private String connectName;
    private String connectPhone;

    @Id
    @Column(name = "job_id", nullable = false)
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "job_title", nullable = true, length = 30)
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Basic
    @Column(name = "job_desc", nullable = true, length = -1)
    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    @Basic
    @Column(name = "connect_name", nullable = true, length = 20)
    public String getConnectName() {
        return connectName;
    }

    public void setConnectName(String connectName) {
        this.connectName = connectName;
    }

    @Basic
    @Column(name = "connect_phone", nullable = true, length = 255)
    public String getConnectPhone() {
        return connectPhone;
    }

    public void setConnectPhone(String connectPhone) {
        this.connectPhone = connectPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblNetJob tblNetJob = (TblNetJob) o;

        if (jobId != tblNetJob.jobId) return false;
        if (jobTitle != null ? !jobTitle.equals(tblNetJob.jobTitle) : tblNetJob.jobTitle != null) return false;
        if (jobDesc != null ? !jobDesc.equals(tblNetJob.jobDesc) : tblNetJob.jobDesc != null) return false;
        if (connectName != null ? !connectName.equals(tblNetJob.connectName) : tblNetJob.connectName != null)
            return false;
        if (connectPhone != null ? !connectPhone.equals(tblNetJob.connectPhone) : tblNetJob.connectPhone != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobId;
        result = 31 * result + (jobTitle != null ? jobTitle.hashCode() : 0);
        result = 31 * result + (jobDesc != null ? jobDesc.hashCode() : 0);
        result = 31 * result + (connectName != null ? connectName.hashCode() : 0);
        result = 31 * result + (connectPhone != null ? connectPhone.hashCode() : 0);
        return result;
    }
}
