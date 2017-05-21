package com.partjob.dao;

import com.partjob.entity.TblJobInfo;
import com.partjob.entity.TblNetJob;
import com.partjob.utils.HibernateBaseDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sloriac on 2017/5/21.
 */
@Repository
public class NetJobDao extends HibernateBaseDao<TblNetJob,Serializable> {

    public TblNetJob getById(int jid){
        String hql = "from TblNetJob netJob where netJob.jobId=:jid";
        return findUnique(hql, jid);
    }

    public List<TblNetJob> getJobPage(int offset, int length){
        String hql = "from TblJobInfo job";
        return findPage(hql, offset, length);
    }
}
