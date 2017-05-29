package com.partjob.dao;

import com.partjob.entity.TblInvitationRecord;
import com.partjob.entity.TblUserInfo;
import com.partjob.utils.HibernateBaseDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;


@Repository
public class InvitationRecordDao extends HibernateBaseDao<TblInvitationRecord, Serializable> {

    public TblInvitationRecord getByUid(int uid) {
        String hql = "from TblInvitationRecord u where u.uid=?";
        return findUnique(hql, uid);
    }

    public List<TblInvitationRecord> getByInviterId(int id) {
        String hql = "from TblInvitationRecord u where u.invitorId=?";
        return find(hql, id);
    }

}
