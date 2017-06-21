package com.partjob.constant;

public class CommonCanstant {

	
	
	public static String USER_INFO="userInfo";
	public static String MCHNT_INFO="mchntInfo";
	public static String VERIFIATION = "verification";
	public static String SMS_SIGN_NAME = "小蜜蜂兼职";
	public static String SMS_APPKEY = "24484679";
	public static String SMS_SECRET = "f730e2907cd92b70c1bac5772ce446f5";
	public static String SMS_URL = "http://gw.api.taobao.com/router/rest";
	public static String SMS_TYPE = "normal";

	public static String SMS_TEMP_REGISTER = "SMS_71960024";
	public static String SMS_TEMP_EDIT = "SMS_71960021";
	public static String SMS_TEMP_NOTIFY = "SMS_71895056";


	public static int PAY_TYPE_HOUR=0;
	public static int PAY_TYPE_DAY=1;
	
	public static String IP="116.62.155.216";
	
	//**********************记录状态常量*******************//
	public static int AVAILAB=1;
	public static int UNAVAILAB=0;
	public static int UNCHECKED=2;
	
	//**********************兼职状态常量*******************//
	/**
	 * 兼职待审核
	 */
	public static int JOB_AUDIT = 0;
	/**
	 * 兼职准备中
	 */
	public static int JOB_PENDING = 1;
	/**
	 * 兼职待评价
	 */
	public static int JOB_EVALUATE = 2;
	
	/**
	 * 兼职已结束
	 */
	public static int JOB_END = 3;
	/**
	 * 兼职被拒绝 
	 */
	public static int JOB_REJECTED = 4;
	
	//****************************用户兼职常量******************/
	public static int USER_PASS=1; //报名通过
	public static int USER_NOT_JOIN=0;//未报名
	public static int USER_REFUSE=2;//拒绝报名
	public static int USER_ABSENCE=3;//工作缺勤
	public static int USER_WORK_FULL=4;//工作满勤
	public static int USER_WORK_CHECK_MONRY=200;//用户参加兼职保证金
	
	//****************************工作常量******************/
	public static int MONEY_LEV_DAY=10000;//每日最低100元
	public static int MONEY_LEV_HOUR=1000;//没小时最低10元
	
	
	public static String MONEY_TYPE_PAY="充值";
	public static String MONEY_TYPE_CASH="提现";
	public static String MONEY_TYPE_RED_PACKET="红包";
	public static String MONEY_TYPE_DEPOSIT="押金";
	public static String MONEY_TYPE_WAGES="工资";
	public static String MONEY_TYPE_PAYMENT="付款";
	public static String MONEY_TYPE_REFUND="退款";
	
	
	public static int INVITATION_UNREALIZE=0;
	public static int INVITATION_REALIZE=1;
	
	public static int INVITATION_MONEY=500;
}
