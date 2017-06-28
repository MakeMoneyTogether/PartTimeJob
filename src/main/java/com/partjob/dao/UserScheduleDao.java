package com.partjob.dao;

import com.partjob.constant.CommonCanstant;
import com.partjob.entity.TblUserSchedule;
import com.partjob.utils.HibernateBaseDao;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Sloriac on 2017/5/24.
 */
@Repository
public class UserScheduleDao extends HibernateBaseDao<TblUserSchedule, Serializable> {

	/**
	 * 保存用户资金变化记录
	 * @param uid 用户id
	 * @param money 资金，以分为单位
	 * @param type 变化类型
	 * @param openId 用户的公众号
	 * @param check 是否需要审核
	 */
    public void add(int uid,int money,String type,String openId,boolean check){
    	TblUserSchedule tblUserSchedule=new TblUserSchedule();
    	tblUserSchedule.setMoney(money);
    	tblUserSchedule.setUid(uid);
    	if(check){
    		tblUserSchedule.setStatus(CommonCanstant.UNCHECKED);
    	}else{
    		tblUserSchedule.setStatus(CommonCanstant.AVAILAB);
    	}
    	tblUserSchedule.setOpenid(openId);
    	tblUserSchedule.setType(type);
    	tblUserSchedule.setTime(new Date());
    	
//    	getSession().merge(tblUserSchedule);
        save(tblUserSchedule);
        getSession().clear();
    }

    public List<TblUserSchedule> getByUid(int uid){
        String hql="from TblUserSchedule us where us.uid=? order by time desc";
        return find(hql, uid);
    }

	public List<TblUserSchedule> getCashs() {
		String hql="from TblUserSchedule us where us.status=? and us.type=? order by time desc";
		return find(hql, CommonCanstant.UNCHECKED,CommonCanstant.MONEY_TYPE_CASH);
	}
}
