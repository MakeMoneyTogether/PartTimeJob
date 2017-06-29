package com.partjob.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.partjob.constant.CommonCanstant;
import com.partjob.entity.TblJobInfo;
import com.partjob.entity.TblMchntInfo;
import com.partjob.utils.CommonUtil;
import com.partjob.utils.HibernateBaseDao;
@Repository
public class JobInfoDao extends HibernateBaseDao<TblJobInfo,Serializable>{

	@Autowired
	CityInfoDao cityInfoDao;
	
    public TblJobInfo getById(int jid){
        String hql = "from TblJobInfo job where job.jobId=?";
        return findUnique(hql, jid);
    }

    public List<TblJobInfo> getJobPage(int offset, int length){
        String hql = "from TblJobInfo job";
        return findPage(hql, offset, length);
    }
    
    
    public List<TblJobInfo> getJobPage(int offset,int length ,
    		String dises,String labels, String dates,int city){
    	StringBuffer hql=new StringBuffer("from TblJobInfo job where 1=1");
    	
    	List<Object> values=new ArrayList<Object>();
    	
    	if(!"all".equalsIgnoreCase(dises)){
    		hql.append(" and job.cityCode in (");
    		String []_dises=dises.split(",");
    		for(int i=0;i<_dises.length;i++){
    			hql.append("?,");
    			values.add(Integer.parseInt(_dises[i]));
    		}
    		
    		hql.deleteCharAt(hql.length()-1);
    		hql.append(")");
    	}
    	
    	if(!"all".equalsIgnoreCase(labels)){
    		
    		hql.append(" and job.jobType in (");
    		String []_labels=labels.split(",");
    		for(int i=0;i<_labels.length;i++){
    			hql.append("?,");
    			values.add(Integer.parseInt(_labels[i]));
    		}
    		
    		
    		
    		hql.deleteCharAt(hql.length()-1);
    		hql.append(")");
    	}
    	
    	if(!"all".equalsIgnoreCase(dates)){
    		hql.append(" and jobStartTime>? ");
    		values.add(new Timestamp(Long.parseLong(dates)));
    	}
    	
    	//兼职时间判断
    	hql.append(" and jobValidateTime > ?");
    	values.add(CommonUtil.getTimestamp());
    	
    	//兼职有效判断
    	hql.append(" and jobSt = ?");
    	values.add(CommonCanstant.JOB_PENDING);
    	
    	if(city!=0){
			hql.append("and cityCode in "
					+ "(select cityCode from TblCityInfo where superCode =?)");
			values.add(city);
    	}
    	
    	return findPage(hql.toString(), offset, length, values.toArray());
    }
    
    public List<TblJobInfo> searchJobPage(int offset,int length ,
    		String keys,int city){
StringBuffer hql=new StringBuffer("from TblJobInfo job where ");
    	
    	List<Object> values=new ArrayList<Object>();
    	
    	if(city!=0){
			hql.append("cityCode in "
					+ "(select cityCode from TblCityInfo where superCode =?)");
			values.add(city);
    	}
    	if(keys != null){
    		hql.append(" and job.jobTitle like ?");
    		values.add("%"+keys+"%");
    		hql.append(" or job.jobType in (select id from TblJobType where name like ?)");
    		values.add("%"+keys+"%");
    	}

    	//兼职时间判断
    	hql.append(" and jobValidateTime > ?");
    	values.add(CommonUtil.getTimestamp());

    	//兼职有效判断
    	hql.append(" and jobSt = ?");
    	values.add(CommonCanstant.JOB_PENDING);
    	
    	return findPage(hql.toString(), offset, length, values.toArray());
    }

	public List<TblJobInfo> getJobByStatus(int josSt) {
		String hql = "from TblJobInfo job where job.jobSt=?";
        return find(hql, josSt);
	}
	
	public List<TblJobInfo> getJobByName(String name){
		//根据名字模糊查询未审核的兼职
		String hql="from TblJobInfo job where job.jobTitle like ? and job.jobSt=0 order by job.jobId desc";
		return find(hql, "%"+name+"%");
	}

	public List<TblJobInfo> getjobByUid(int uid) {
		String hql = "from TblJobInfo job where job.jobId in (select r.jobId from TblRelUserJob r where r.uid = ?)";
		return find(hql, uid);
	}

}
