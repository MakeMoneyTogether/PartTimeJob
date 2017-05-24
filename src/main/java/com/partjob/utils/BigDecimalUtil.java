package com.partjob.utils;

import java.math.BigDecimal;

public class BigDecimalUtil {

	/**
	 * 除100
	 * @param value
	 * @return
	 */
	public static String divide100(String value){
		BigDecimal result=new BigDecimal(value).divide(new BigDecimal(100));
		String str=result.stripTrailingZeros().toPlainString();;
		return str;
	}
	
	/**
	 * 乘100
	 * @param value
	 * @return
	 */
	
	public static String mult100(String value){
		BigDecimal result=new BigDecimal(value).multiply(new BigDecimal(100));
		String str=result.stripTrailingZeros().toPlainString();;
		return str;
	}
	
	public static void main(String[] args) {
		String str="100";
		System.out.println(BigDecimalUtil.divide100(str));;
		
	}
}
