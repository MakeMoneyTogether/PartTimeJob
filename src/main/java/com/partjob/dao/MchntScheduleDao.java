package com.partjob.dao;

import com.partjob.constant.CommonCanstant;
import com.partjob.entity.TblMchntInfo;
import com.partjob.entity.TblMchntSchedule;
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
public class MchntScheduleDao extends HibernateBaseDao<TblMchntSchedule, Serializable> {

	/**
	 * 保存用户资金变化记录
	 * @param uid 用户id
	 * @param money 资金，以分为单位
	 * @param type 变化类型
	 * @param openId 用户的公众号
	 * @param check 是否需要审核
	 */
    public void add(int mchntCd,int money,String type,String openId,boolean check){
    	TblMchntSchedule tblMchntSchedule=new TblMchntSchedule();
    	tblMchntSchedule.setMoney(money);
    	tblMchntSchedule.setMchntCd(mchntCd);
    	if(check){
    		tblMchntSchedule.setStatus(CommonCanstant.UNCHECKED);
    	}
    	tblMchntSchedule.setOpenid(openId);
    	tblMchntSchedule.setType(type);
    	tblMchntSchedule.setTime(new Date());
    	
        save(tblMchntSchedule);
    }

    public List<TblMchntSchedule> getByMid(int mid){
        String hql="from TblMchntSchedule ms where ms.mchntCd=? order by time desc";
        return find(hql, mid);
    }

	public List<TblMchntSchedule> getCashs() {
		String hql="from TblMchntSchedule ms where ms.status=? and ms.type=? order by time desc";
        return find(hql, CommonCanstant.UNCHECKED,CommonCanstant.MONEY_TYPE_CASH);
	}
}
