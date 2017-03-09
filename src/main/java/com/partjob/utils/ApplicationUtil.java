package com.partjob.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/** 
 * @description:应用级别的工具类。在应用启动的时候，设置spring的ApplicationContext。
 * @author: 张贞亮
 * @version: 1.0
 * @modify: 
 * @Copyright: 中国银联股份有限公司版权拥有
 */
public class ApplicationUtil  implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;

	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		ApplicationUtil.applicationContext = ctx;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	/**
	 * 获得Bean
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName){
		ApplicationContext context = getApplicationContext();
		return context.getBean(beanName);
	}
	
	/**
	 * 获得Bean
	 * @param beanName
	 * @param cls
	 * @return
	 */
	public static <T> T getBean(String beanName,Class<T> cls){
		ApplicationContext context = getApplicationContext();
		return context.getBean(beanName, cls);
	}
	
	/**
	 * 获取Bean（以class的name为ID获取）
	 * @param cls
	 * @return
	 */
	public static <T> T getBean(Class<T> cls){
		return getBean(cls.getName(),cls);
	}
	/**
	 * 拷贝属性，从来源到目标
	 * @param source
	 * @param target
	 */
	public static void copyProperties(Object source,Object target){
		BeanUtils.copyProperties(source, target);
	}
	/**
	 * 拷贝属性，从来源到目标
	 * @param source
	 * @param target
	 */
	public static void copyProperties(Object source,Object target,String[] ignoreProperties){
		BeanUtils.copyProperties(source, target,ignoreProperties);
	}
	/**
	 * 将对象转换为对应的类型
	 * @param obj
	 * @param cls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertObjectToTargetClass(Object obj,Class<T> cls){
	    return (T)obj;
	}
}
