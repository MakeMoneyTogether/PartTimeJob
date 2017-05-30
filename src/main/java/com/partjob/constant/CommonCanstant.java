package com.partjob.constant;

public class CommonCanstant {

	
	
	public static String USER_INFO="userInfo";
	public static String MCHNT_INFO="mchntInfo";


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
	public static int USER_NOT_PASS=0;//报名不通过
	public static int USER_ABSENCE=2;//工作缺勤
	public static int USER_LEAVE_HALFWAY=3;//工作中途离开
	public static int USER_WORK_FULL=4;//工作满勤
	public static int USER_WORK_CHECK_MONRY=200;//用户参加兼职保证金
	
	//****************************工作常量******************/
	public static int MONEY_LEV_DAY=10000;//每日最低100元
	public static int MONEY_LEV_HOUR=1000;//没小时最低10元
}
