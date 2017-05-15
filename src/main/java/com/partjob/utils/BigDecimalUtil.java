package com.partjob.utils;

import java.math.BigDecimal;

public class BigDecimalUtil {

	public static String divide100(String value){
		BigDecimal result=new BigDecimal(value).divide(new BigDecimal(100));
		String str=result.stripTrailingZeros().toPlainString();;
		return str;
	}
	
	public static String mult100(String value){
		BigDecimal result=new BigDecimal(value).multiply(new BigDecimal(100));
		String str=result.stripTrailingZeros().toPlainString();;
		return str;
	}
}
