package com.partjob.dao;

import com.partjob.entity.TblRelUserJob;
import com.partjob.entity.TblUserInfo;
import com.partjob.utils.HibernateBaseDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sloriac on 2017/4/16.
 * 用户信息的DAO类
 */
@Repository
public class UserJobDao extends HibernateBaseDao<TblRelUserJob, Serializable> {

    public void update(TblRelUserJob tblRelUserJob) {
        super.saveOrUpdate(tblRelUserJob);
    }

    public List<TblRelUserJob> getByStatus(int uid, int statusId) {
        String hql = "from TblRelUserJob ujob where ujob.uid=:uid and statusId=:statusId";
        return find(hql, uid, statusId);
    }

    public TblRelUserJob getByUidJid(int uid, int jid) {
        String hql = "from TblRelUserJob ujob where ujob.uid=:uid and jobId=:jid";
        return findUnique(hql, uid, jid);
    }
}
