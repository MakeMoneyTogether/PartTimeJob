package com.partjob.service;

import com.partjob.constant.CommonCanstant;
import com.partjob.constant.ResponseCode;
import com.partjob.dao.UserInfoDao;
import com.partjob.dao.UserScheduleDao;
import com.partjob.entity.TblMchntInfo;
import com.partjob.entity.TblMchntSchedule;
import com.partjob.entity.TblNetJob;
import com.partjob.entity.TblUserInfo;
import com.partjob.entity.TblUserSchedule;
import com.partjob.model.CheckTransResult;
import com.partjob.model.NetJob;
import com.partjob.model.UserInfo;
import com.partjob.model.UserSchedule;
import com.partjob.model.WcPay;
import com.partjob.utils.ApplicationUtil;
import com.partjob.utils.BigDecimalUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sloriac on 2017/5/24.
 */
@Service
@Transactional
public class UserCashService {

    @Autowired
    private UserScheduleDao userScheduleDao;
    @Autowired
    private UserService userService;
    @Autowired
	private TransService transService;
    @Autowired
    private UserInfoDao userInfoDao;
    
    
    /**
	 * 用户充值
	 * @param totalFee	充值金额
	 * @param ip	交易发起ip
	 * @param openId	充值用户的openid
	 * @param mchntCd	用户充值的用户id
	 * @return
	 */
	public WcPay pay(String totalFee, String ip,String openId,int uid){
		return transService.pay(totalFee, ip, openId);				
	}
	
	/**
	 * 检查充值结果，并作用于账户余额
	 * @param outTradeNo
	 * @param uid
	 * @return
	 */
	public int checkPay(String outTradeNo,int uid,String openId){
		CheckTransResult checkResult=transService.checkPay(outTradeNo);
		
		if(checkResult.getReturn_code().equalsIgnoreCase("SUCCESS")&&
				checkResult.getResult_code().equalsIgnoreCase("SUCCESS")&&
				checkResult.getTrade_state().equalsIgnoreCase("SUCCESS")){
			//如果支付成功，那么就将修改账户余额
			TblUserInfo tblUser=userInfoDao.get(uid);
			tblUser.setBalance(tblUser.getBalance()+Integer.parseInt(checkResult.getTotal_fee()));
			userInfoDao.modify(tblUser);
			userScheduleDao.add(uid, Integer.parseInt(checkResult.getTotal_fee()), CommonCanstant.MONEY_TYPE_PAY, openId, false);
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
			transService.checkPay(outTradeNo);
		}else{
			return ResponseCode.PAY_FAIL;
		}
		return ResponseCode.PAY_FAIL;
	}
	
	
	/**
	 * 商户提现，插入资金流转表，等待管理员进行审核
	 * @param totalFee 金额
	 * @param openId 提现的openid
	 * @param mchntCd 商户号
	 * @return
	 */
	public int cash(String totalFee,String openId,int uId){
		int money=Integer.parseInt(BigDecimalUtil.mult100(totalFee));
		TblUserInfo tblUserInfo=userInfoDao.get(uId);
		
		if(tblUserInfo.getBalance()<money){
			return ResponseCode.NOT_ENOUGH_MONEY;
		}
		
		tblUserInfo.setBalance(tblUserInfo.getBalance()-money);
		userInfoDao.modify(tblUserInfo);
		
		userScheduleDao.add(uId, money, CommonCanstant.MONEY_TYPE_CASH, openId, true);
		return ResponseCode.SUCCESS;
	}
	
	/**
	 * 管理员审核通过商户提现请求，商户提现，修改记录状态
	 * @param id
	 * @return
	 */
	public int checkCash(int id){
		TblUserSchedule tblUserSchedule=userScheduleDao.get(id);
		int result=transService.cash(Integer.toString(tblUserSchedule.getMoney()), tblUserSchedule.getOpenid());
		if(result==ResponseCode.SUCCESS){
			tblUserSchedule.setStatus(CommonCanstant.AVAILAB);
			userScheduleDao.modify(tblUserSchedule);
			return ResponseCode.SUCCESS;
		}else{
			tblUserSchedule.setStatus(CommonCanstant.UNAVAILAB);
			userScheduleDao.modify(tblUserSchedule);
			return ResponseCode.CASH_FAIL;
		}
		
		
	}

    public List<UserSchedule> getByPhone(String phone) {
        UserInfo userInfo = userService.getByPhone(phone);
        //用户不存在
        if(userInfo == null){
            return null;
        }
        return transModelList(userScheduleDao.getByUid(userInfo.getUid()));
    }

//    private UserSchedule transModel(TblUserSchedule tblUserSchedule) {
//        UserSchedule userSchedule = new UserSchedule();
//        if (tblUserSchedule == null)
//            return null;
//        ApplicationUtil.copyProperties(tblUserSchedule, userSchedule);
//        return userSchedule;
//    }

    private List<UserSchedule> transModelList(List<TblUserSchedule> tblUserSchedules) {
        if (tblUserSchedules == null || tblUserSchedules.size() == 0)
            return null;
        List<UserSchedule> userSchedules = new ArrayList<UserSchedule>();
        for (TblUserSchedule tblUserSchedule : tblUserSchedules) {
            UserSchedule userSchedule = new UserSchedule();
            ApplicationUtil.copyProperties(tblUserSchedule, userSchedule);
            String money=BigDecimalUtil.divide100(Integer.toString(tblUserSchedule.getMoney()));
            userSchedule.setMoney(money);
            userSchedules.add(userSchedule);
        }
        return userSchedules;
    }
}
