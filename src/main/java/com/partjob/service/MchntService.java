package com.partjob.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.mysql.fabric.Response;
import com.partjob.constant.CommonCanstant;
import com.partjob.constant.ObjectStatuCode;
import com.partjob.constant.ResponseCode;
import com.partjob.constant.TransCanstant;
import com.partjob.dao.InvitationRecordDao;
import com.partjob.dao.JobInfoDao;
import com.partjob.dao.JobTypeDao;
import com.partjob.dao.MchntInfoDao;
import com.partjob.dao.MchntScheduleDao;
import com.partjob.dao.UserInfoDao;
import com.partjob.dao.UserJobDao;
import com.partjob.dao.UserScheduleDao;
import com.partjob.entity.TblInvitationRecord;
import com.partjob.entity.TblJobInfo;
import com.partjob.entity.TblMchntInfo;
import com.partjob.entity.TblMchntSchedule;
import com.partjob.entity.TblRelUserJob;
import com.partjob.entity.TblUserInfo;
import com.partjob.model.CheckTransResult;
import com.partjob.model.JobInfo;
import com.partjob.model.MchntInfo;
import com.partjob.model.MchntSchedule;
import com.partjob.model.WcPay;
import com.partjob.utils.ApplicationUtil;
import com.partjob.utils.BigDecimalUtil;
import com.partjob.utils.CommonUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by MPJ on 17/4/8.
 */
@Service
@Transactional
public class MchntService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private MchntInfoDao mchntInfoDao;
	@Autowired
	private TransService transService;
	@Autowired
	private JobInfoDao jobInfoDao;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private UserJobDao userJobDao;
	@Autowired
	private MchntScheduleDao mchntScheduleDao;
	@Autowired
	private UserScheduleDao userScheduleDao;
	@Autowired
	private InvitationRecordDao invitationRecordDao;
    @Autowired
    JobTypeDao jobTypeDao;
	/**
	 * 保存商户信息
	 * @param mchntInfo
	 * @param password
	 */
	public void saveMchnt(MchntInfo mchntInfo, String password) {

		TblMchntInfo tblMchntInfo = new TblMchntInfo();
		ApplicationUtil.copyProperties(mchntInfo, tblMchntInfo);
		tblMchntInfo.setBalance(0);
		tblMchntInfo.setFrozenMoney(0);
		tblMchntInfo.setPassword(CommonUtil.toMD5(password));
		tblMchntInfo.setMchntSt(CommonCanstant.UNCHECKED);

		mchntInfoDao.save(tblMchntInfo);
	}

	/**
	 * 修改密码，先验证原密码是否正确，正确再进行修改
	 * @param password 原密码
	 * @param phone
	 * @param rePassword 新密码
	 * @return
	 */
	public boolean updatePassword(String password,String phone,String rePassword){
		TblMchntInfo mchntInfo=mchntInfoDao.getMchntInfo(CommonUtil.toMD5(password), phone);
		if(mchntInfo==null){
			return false;
		}else{
			mchntInfo.setPassword(CommonUtil.toMD5(rePassword));
			
			mchntInfoDao.modify(mchntInfo);
			return true;
		}
	}
	/**
	 * 登录
	 * @param password
	 * @param phone
	 * @return
	 */
	public MchntInfo logoin(String password, String phone) {
		String passwordMd5 = CommonUtil.toMD5(password);
		TblMchntInfo tblMchntInfo = mchntInfoDao.getMchntInfo(passwordMd5,
				phone);
//		//如果已经别冻结就不能登录
//		if(tblMchntInfo.getMchntSt()==CommonCanstant.UNAVAILAB){
//			return null;
//		}
		return transModel(tblMchntInfo);

	}

	/**
	 * 检查手机号是否存在
	 * @param phone
	 * @param mchntInfo
	 * @return
	 */
	public boolean checkPhone(String phone, MchntInfo mchntInfo) {
		TblMchntInfo tblMchntInfo = mchntInfoDao.findUniqueByProperty("phone",
				phone);

		if ((tblMchntInfo == null && mchntInfo == null)
				|| (tblMchntInfo != null && mchntInfo.getMchntCd() == tblMchntInfo
						.getMchntCd())) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean checkPhone(String phone) {
		TblMchntInfo tblMchntInfo = mchntInfoDao.findUniqueByProperty("phone",
				phone);

		if (tblMchntInfo == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获取商户信息
	 * @param mchntCd
	 * @return
	 */
	public MchntInfo getMchntInfo(int mchntCd) {
		TblMchntInfo tblMchntInfo = mchntInfoDao.get(mchntCd);
		return transModel(tblMchntInfo);
	}
	
	/**
	 * 获取所有商户信息
	 * @return
	 */
	public List<MchntInfo> getAllMchnt(){
		List<TblMchntInfo>tblMchntInfos=mchntInfoDao.getAllMchntInf();
		List <MchntInfo>list=new ArrayList<MchntInfo>();
		for(TblMchntInfo tblMchntInfo:tblMchntInfos){
			list.add(transModel(tblMchntInfo));
		}
		return list;
	}
	
	

	/**
	 * 更新商户信息
	 * @param mchntInfo
	 */
	public void updateMchtInfo(MchntInfo mchntInfo) {
		TblMchntInfo tblMchntInfo = mchntInfoDao.get(mchntInfo.getMchntCd());
		tblMchntInfo.setMchntName(mchntInfo.getMchntName());
		tblMchntInfo.setMchntAddress(mchntInfo.getMchntAddress());
		tblMchntInfo.setConnName(mchntInfo.getConnName());
		tblMchntInfo.setConnPhone(mchntInfo.getConnPhone());

		mchntInfoDao.modify(tblMchntInfo);
	}
	
	/**
	 * 商户充值
	 * @param totalFee	充值金额
	 * @param ip	交易发起ip
	 * @param openId	充值用户的openid
	 * @param mchntCd	商户充值的商户号
	 * @return
	 */
	public WcPay pay(String totalFee, String ip,String openId,int mchntCd){
		
		return transService.pay(BigDecimalUtil.mult100(totalFee), ip, openId);				
	}
	
	/**
	 * 商户提现，插入资金流转表，等待管理员进行审核
	 * @param totalFee 金额
	 * @param openId 提现的openid
	 * @param mchntCd 商户号
	 * @return
	 */
	public int cash(String totalFee,String openId,int mchntCd){
		int money=Integer.parseInt(BigDecimalUtil.mult100(totalFee));
		TblMchntInfo tblMchntInfo=mchntInfoDao.get(mchntCd);
		
		if(tblMchntInfo.getBalance()<money){
			return ResponseCode.NOT_ENOUGH_MONEY;
		}
		
		tblMchntInfo.setBalance(tblMchntInfo.getBalance()-money);
		mchntInfoDao.modify(tblMchntInfo);
		
		mchntScheduleDao.add(mchntCd, money, CommonCanstant.MONEY_TYPE_CASH, openId, true);
		return ResponseCode.SUCCESS;
	}
	
	/**
	 * 检查充值结果，并作用于账户余额
	 * @param outTradeNo
	 * @param mchntCd
	 * @return
	 */
	public int checkPay(String outTradeNo,int mchntCd,int i,String openId){
		CheckTransResult checkResult=transService.checkPay(outTradeNo);
		
		if(checkResult.getReturn_code().equalsIgnoreCase("SUCCESS")&&
				checkResult.getResult_code().equalsIgnoreCase("SUCCESS")&&
				checkResult.getTrade_state().equalsIgnoreCase("SUCCESS")){
			//如果支付成功，那么就将修改账户余额
			TblMchntInfo tblMchntInfo = mchntInfoDao.get(mchntCd);
			tblMchntInfo.setBalance(tblMchntInfo.getBalance()+Integer.parseInt(checkResult.getTotal_fee()));
			mchntInfoDao.modify(tblMchntInfo);
			mchntScheduleDao.add(mchntCd, Integer.parseInt(checkResult.getTotal_fee()),CommonCanstant.MONEY_TYPE_PAY, openId, false);
			return ResponseCode.SUCCESS;
			
		}else if(checkResult.getReturn_code().equalsIgnoreCase("SUCCESS")&&
				checkResult.getResult_code().equalsIgnoreCase("SUCCESS")&&
				checkResult.getTrade_state().equalsIgnoreCase("USERPAYING")){
			//如果检查出是用户正在支付，那么睡眠1s重新进行检查
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return ResponseCode.PAY_FAIL;
			}
			logger.warn("可能会出现死循环！！！！！！");
			checkPay(outTradeNo,mchntCd,i++,openId);
		}else{
			return ResponseCode.PAY_FAIL;
		}
		return ResponseCode.PAY_FAIL;
	}
	
	/**
	 * 管理员审核通过商户提现请求，商户提现，修改记录状态
	 * @param id
	 * @return
	 */
	public int checkCash(int id){
		TblMchntSchedule tblMchntSchedule=mchntScheduleDao.get(id);
		int result=transService.cash(Integer.toString(tblMchntSchedule.getMoney()), tblMchntSchedule.getOpenid());
		if(result==ResponseCode.SUCCESS){
			tblMchntSchedule.setStatus(CommonCanstant.AVAILAB);
			mchntScheduleDao.modify(tblMchntSchedule);
			return ResponseCode.SUCCESS;
		}else{
			tblMchntSchedule.setStatus(CommonCanstant.UNAVAILAB);
			mchntScheduleDao.modify(tblMchntSchedule);
			
			//回滚商户余额
			TblMchntInfo mchntInfo =mchntInfoDao.get(tblMchntSchedule.getMchntCd());
			mchntInfo.setBalance(mchntInfo.getBalance()+tblMchntSchedule.getMoney());
			mchntInfoDao.modify(mchntInfo);
			
			return ResponseCode.CASH_FAIL;
		}
		
		
	}
	
	/**
	 * 审核商户提现不通过
	 * @param id
	 * @return
	 */
	public int notPassCash(int id){
		TblMchntSchedule tblMchntSchedule=mchntScheduleDao.get(id);
		TblMchntInfo mchntInfo =mchntInfoDao.get(tblMchntSchedule.getMchntCd());
		
		mchntInfo.setBalance(mchntInfo.getBalance()+tblMchntSchedule.getMoney());
		tblMchntSchedule.setStatus(CommonCanstant.UNAVAILAB);
		mchntInfoDao.modify(mchntInfo);
		mchntScheduleDao.modify(tblMchntSchedule);
		return ResponseCode.SUCCESS;
	}
	
	/**
	 * 发送兼职信息
	 * @param job
	 */
	public int postJob(JobInfo job,int mchntCd){
		TblJobInfo tblJob=new TblJobInfo();
		ApplicationUtil.copyProperties(job, tblJob);
		String _money=BigDecimalUtil.mult100(job.getPaymentMoney());
		tblJob.setPaymentMoney(Integer.parseInt(_money));
		tblJob.setMchntCd(mchntCd);
		tblJob.setJobSt(ObjectStatuCode.JOB_AUDIT);
		tblJob.setJoinNum(0);
		//检查兼职是否满足要求
		int result=checkJob(job);
		if(result!=ResponseCode.SUCCESS){
			return result;
		}
		//扣除商家账户金额
		int money=0;
		int paymentMoney=Integer.parseInt(BigDecimalUtil.mult100(job.getPaymentMoney()));
//		long time=job.getJobEndTime().getTime()-job.getJobStartTime().getTime();
		//判断商户的计费类型
		if(job.getPaymentType() == CommonCanstant.PAY_TYPE_HOUR){
//			int hour=(int) (time/1000/60);
			int hour=differeHour(job.getJobStartTime(), job.getJobEndTime(), job.getHoursDay()==0?80:job.getHoursDay());
			money=paymentMoney*job.getNumPeople()*hour/10;	//这里除10是因为工作强度在前台传过来时乘了一个10
			
		}else if(job.getPaymentType() == CommonCanstant.PAY_TYPE_DAY){
//			int day=(int)(time/1000/60/24);
			int day=differeDay(job.getJobStartTime(), job.getJobEndTime());
			
			money=paymentMoney*job.getNumPeople()*day;
		}
		TblMchntInfo tblMchntInfo=mchntInfoDao.get(mchntCd);
		//如果商户的账户余额太低则不能发布兼职
		if(tblMchntInfo.getBalance()<money){
			return ResponseCode.NOT_ENOUGH_MONEY;
		}
		tblMchntInfo.setBalance(tblMchntInfo.getBalance()-money);
		tblMchntInfo.setFrozenMoney(tblMchntInfo.getFrozenMoney()+ money);		//这里不应该直接设置，应该是相加
		
		//最后在提交
		jobInfoDao.save(tblJob);
		mchntInfoDao.modify(tblMchntInfo);
		mchntScheduleDao.add(mchntCd, money, CommonCanstant.MONEY_TYPE_DEPOSIT, "", false);
		return ResponseCode.SUCCESS;
	}
	
	/**
	 * 清算
	 * @param jobId
	 * @return
	 */
	public int clearJob(int jobId){
		List<TblRelUserJob>list=userJobDao.getAvailByJid(jobId);
		TblJobInfo job=jobInfoDao.get(jobId);
		
		//扣除商家账户金额
		int money=0;
//		long time=job.getJobEndTime().getTime()-job.getJobStartTime().getTime();
		//判断商户的计费类型
		if(job.getPaymentType().equals(CommonCanstant.PAY_TYPE_HOUR)){
//			int hour=(int) (time/1000/60);
			int hour=differeHour(job.getJobStartTime(),job.getJobEndTime() , job.getHoursDay()==0?80:job.getHoursDay());
			money=job.getPaymentMoney()*hour /10;	//这里除10也是因为前端乘了10
			
		}else if(job.getPaymentType().equals(CommonCanstant.PAY_TYPE_DAY)){
//			int day=(int)(time/1000/60/24);
			int day=differeDay(job.getJobStartTime(),job.getJobEndTime());
			money=job.getPaymentMoney()*day;
		}		
		
		int numpeople=job.getNumPeople();
		
		int returnMoney = money * numpeople;
		
		//计算有效参加工作的人数，同时给用户进行结算
		int validateNum =0;//有效参加工作的人
		for(TblRelUserJob userJob:list){
			TblUserInfo user=userInfoDao.get(userJob.getUid());
			//如果是满勤
			if(userJob.getStatusId()==CommonCanstant.USER_WORK_FULL){
				validateNum++;
				user.setBalance(user.getBalance()+money+CommonCanstant.USER_WORK_CHECK_MONRY);
				userInfoDao.modify(user);
				userScheduleDao.add(user.getUid(), money+CommonCanstant.USER_WORK_CHECK_MONRY, CommonCanstant.MONEY_TYPE_WAGES, "", false);
				
				//查看邀请码使用情况，如果未被兑现，则进行兑现
				TblInvitationRecord record=invitationRecordDao.getByUid(user.getUid());
				if(record!=null){
					if(record.getStatus()==CommonCanstant.INVITATION_UNREALIZE){
						//查找邀请人，给邀请人账户发红包
						TblUserInfo tblInvidUser=userInfoDao.get(record.getInvitorId());
						if(tblInvidUser!=null){
							tblInvidUser.setBalance(tblInvidUser.getBalance()+CommonCanstant.INVITATION_MONEY);
							userInfoDao.modify(tblInvidUser);
							//记录红包流水
							userScheduleDao.add(tblInvidUser.getUid(), CommonCanstant.INVITATION_MONEY, CommonCanstant.MONEY_TYPE_RED_PACKET, "", false);
							record.setStatus(CommonCanstant.INVITATION_REALIZE);
						}
						
					}
				}
				
				
				
			}
		}
		//将为参加兼职人数的资金回返到商户
		TblMchntInfo mchnt=mchntInfoDao.get(job.getMchntCd());
		mchnt.setBalance(mchnt.getBalance()+(numpeople-validateNum)*money);
		mchnt.setFrozenMoney(mchnt.getFrozenMoney() - returnMoney);
		mchntInfoDao.modify(mchnt);
		mchntScheduleDao.add(mchnt.getMchntCd(), (numpeople-validateNum)*money, CommonCanstant.MONEY_TYPE_REFUND, "", false);
		return ResponseCode.SUCCESS;
	}
	
	
	
	
	/**
	 * 获取该商户下的所有兼职
	 * @param mchntCd
	 * @return
	 */
	public List<JobInfo> getJobInfoList(int mchntCd){
		List<TblJobInfo> tblJobInfoList=jobInfoDao.findByProperty("mchntCd", mchntCd);
		List<JobInfo>result=new ArrayList<JobInfo>();
		for(TblJobInfo temp:tblJobInfoList){
			JobInfo jobInfo=new JobInfo();
			ApplicationUtil.copyProperties(temp, jobInfo);
			jobInfo.setPaymentMoney(BigDecimalUtil.divide100(temp.getPaymentMoney()+""));
			jobInfo.setJobTypeName(jobTypeDao.get(jobInfo.getJobType()).getName());
			result.add(jobInfo);
		}
		
		return result;
	}

	
	public List<MchntSchedule> getMchntSchedules(String phone){
		TblMchntInfo mchntInfo=mchntInfoDao.findUniqueByProperty("phone", phone);
		if(mchntInfo==null){
			return null;
		}
		List<TblMchntSchedule> list=mchntScheduleDao.getByMid(mchntInfo.getMchntCd());
		List<MchntSchedule>schedules=new ArrayList<MchntSchedule>();
		for(TblMchntSchedule tblMchntSchedule:list){
			schedules.add(transModel(tblMchntSchedule));
		}
		return schedules;
	}
	
	/**
	 * 冻结商户
	 * @param mchntCd
	 * @return
	 */
	public int freezeMchnt(int mchntCd){
		TblMchntInfo mchntInfo=mchntInfoDao.get(mchntCd);
		mchntInfo.setMchntSt(CommonCanstant.UNAVAILAB);
		mchntInfoDao.modify(mchntInfo);
		return ResponseCode.SUCCESS;
		
	}
	private MchntSchedule transModel(TblMchntSchedule tblMchntSchedule){
		MchntSchedule mchntSchedule=new MchntSchedule();
		if(tblMchntSchedule==null){
			return null;
		}
		ApplicationUtil.copyProperties(tblMchntSchedule, mchntSchedule);
		String money=BigDecimalUtil.divide100(Integer.toString(tblMchntSchedule.getMoney()));
		mchntSchedule.setMoney(money);
		mchntSchedule.setTime(CommonUtil.transDate(tblMchntSchedule.getTime()));
		TblMchntInfo mchntInfo = mchntInfoDao.get(tblMchntSchedule.getMchntCd());
		mchntSchedule.setMname(mchntInfo.getMchntName());
		mchntSchedule.setCname(mchntInfo.getConnName());
		mchntSchedule.setPhone(mchntInfo.getConnPhone());
		return mchntSchedule;
	}
	
	private List<MchntSchedule> transListSchedules(List<TblMchntSchedule> tblMchntSchedules){
		List<MchntSchedule> mchntSchedules = new ArrayList<MchntSchedule>();
		for(TblMchntSchedule tblMchntSchedule : tblMchntSchedules){
			mchntSchedules.add(transModel(tblMchntSchedule));
		}
		return mchntSchedules;
	}
	/**
	 * 数据转换
	 * @param tblMchntInfo
	 * @return
	 */
	private MchntInfo transModel(TblMchntInfo tblMchntInfo) {
		MchntInfo mchntInfo = new MchntInfo();
		if (tblMchntInfo == null)
			return null;
		ApplicationUtil.copyProperties(tblMchntInfo, mchntInfo);
		String balance=Integer.toString(tblMchntInfo.getBalance());
		mchntInfo.setBalance(BigDecimalUtil.divide100(balance));
		String frozenMonry=Integer.toString(tblMchntInfo.getFrozenMoney());
		mchntInfo.setFrozenMoney(BigDecimalUtil.divide100(frozenMonry));
		
		return mchntInfo;
	}
	
	
	
	/**
	 * 检查兼职任务
	 * @param job
	 * @return
	 */
	private int checkJob(JobInfo job){
		int paymentMoney=Integer.parseInt(BigDecimalUtil.mult100(job.getPaymentMoney()));
		
		if(job.getPaymentType() == CommonCanstant.PAY_TYPE_HOUR){
			if(paymentMoney<CommonCanstant.MONEY_LEV_HOUR){
				return ResponseCode.JOB_PAYMONEY_TO_LOW;
			}
			return ResponseCode.SUCCESS;
			
		}else if(job.getPaymentType() == CommonCanstant.PAY_TYPE_DAY){
			if(paymentMoney<CommonCanstant.MONEY_LEV_DAY){
				return ResponseCode.JOB_PAYMONEY_TO_LOW;
			}
			return ResponseCode.SUCCESS;
		}
		return ResponseCode.SUCCESS;
	}

/**
 * 计算两个时间之间相差多少天
 * @param start
 * @param end
 * @return
 */
	private int differeDay(Timestamp start,Timestamp end){
		Calendar cal1 = Calendar.getInstance();
        cal1.setTime(start);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(end);
        
        int dayStart=cal1.get(Calendar.DAY_OF_YEAR);
        int dayEnd=cal2.get(Calendar.DAY_OF_YEAR);
        return dayEnd-dayStart+1;
	}
	
	/**
	 * 计算两个日期之间相差多少个工作小时
	 * @param start
	 * @param end
	 * @param hour 每天工作的小时数
	 * @return
	 */
	private int differeHour(Timestamp start,Timestamp end,int hour){
		Calendar cal1 = Calendar.getInstance();
        cal1.setTime(start);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(end);
        
        int dayStart=cal1.get(Calendar.DAY_OF_YEAR);
        int dayEnd=cal2.get(Calendar.DAY_OF_YEAR);
        
        int hourStart=cal1.get(Calendar.HOUR_OF_DAY);
        int hourEnd=cal2.get(Calendar.HOUR_OF_DAY);
        
//        if(dayEnd>dayStart){
//        	return (dayEnd-dayStart)*hour+hourEnd-hourStart;
//        }else{
//        	return hourEnd-hourStart;
//        }
        //这里我改了一下，就是按照输入的hour进行计算
        if(dayEnd>dayStart){
        	return (dayEnd-dayStart + 1)*hour;
        }else{
        	return hour;
        }
	}
	public List<MchntSchedule> getCashs() {
		List<TblMchntSchedule> tblMchntSchedules = mchntScheduleDao.getCashs();
		return transListSchedules(tblMchntSchedules);
	}

	/**
	 * 审核商户，将商户从待检查变为已通过
	 * @param mchntCd
	 * @return
	 */
	public int passMchnt(int mchntCd) {
		TblMchntInfo mchntInfo=mchntInfoDao.get(mchntCd);
		mchntInfo.setMchntSt(CommonCanstant.AVAILAB);
		mchntInfoDao.modify(mchntInfo);
		return ResponseCode.SUCCESS;
	}

	/**
	 * 模糊匹配查询商户
	 * @param mchntkey
	 * @return
	 */
	public List<MchntInfo> getMchntByKey(String mchntkey) {
		// TODO Auto-generated method stub
		List<TblMchntInfo> tblMchntInfos=mchntInfoDao.getMchntByName(mchntkey);
		List<MchntInfo> list=new ArrayList<MchntInfo>();
		for(TblMchntInfo tblMchntInfo:tblMchntInfos){
			list.add(transModel(tblMchntInfo));
		}
		return list;
	}
}
