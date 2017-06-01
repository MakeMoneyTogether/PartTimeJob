package com.partjob.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.partjob.entity.TblJobInfo;
import com.partjob.entity.TblMchntInfo;
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
    			values.add(_dises[i]);
    		}
    		
    		hql.deleteCharAt(hql.length()-1);
    		hql.append(")");
    	}
    	
    	if(!"all".equalsIgnoreCase(labels)){
    		
    		hql.append(" and job.cityCode in (");
    		String []_labels=labels.split(",");
    		for(int i=0;i<_labels.length;i++){
    			hql.append("?,");
    			values.add(_labels[i]);
    		}
    		
    		
    		
    		hql.deleteCharAt(hql.length()-1);
    		hql.append(")");
    	}
    	
    	if(!"all".equalsIgnoreCase(dates)){
    		hql.append(" and jobStartTime>? ");
    		values.add(dates);
    	}
    	
    	if(city!=0){
			hql.append("and city_code in "
					+ "(select city_code from tbl_city_info where super_code =?)");
			values.add(city);
    	}
    	
    	return findPage(hql.toString(), offset, length, values.toArray());
    }

}
