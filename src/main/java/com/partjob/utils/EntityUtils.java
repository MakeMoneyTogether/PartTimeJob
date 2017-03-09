package com.partjob.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Entity帮助类
 * 
 */
public class EntityUtils {
	private static final Logger logger = Logger.getLogger(EntityUtils.class);

	/**
	 * 根据实体类和属性名获得属性对应数据库字段名
	 * 
	 * @param entityClass
	 *            指定实体类
	 * @param propertyName
	 *            指定属性名
	 * @return 属性对应数据库字段名
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static String getColumnName(Class<?> entityClass, String propertyName)
			throws SecurityException, NoSuchMethodException {
		String methodName = "get" + propertyName.substring(0, 1).toUpperCase()
				+ propertyName.substring(1, propertyName.length());
		Method method = entityClass.getMethod(methodName);
		Column column = method.getAnnotation(Column.class);
		return column.name();
	}

	/**
	 * 根据实体类和属性名获得属性对应数据库字段名(会检查方法名和字段注解是否存在）
	 * 
	 * @author shirley.jiang
	 * @Date 2013-7-15
	 */
	public static String getColumnNameVerified(Class<?> entityClass,
			String propertyName) throws SecurityException,
			NoSuchMethodException {
		String methodName = "get" + propertyName.substring(0, 1).toUpperCase()
				+ propertyName.substring(1, propertyName.length());
		Method method = entityClass.getMethod(methodName);
		if (null == method)
			return null;
		Column column = method.getAnnotation(Column.class);
		return column == null ? null : column.name();
	}

	/**
	 * 根据实体类获得对应数据库对应表名
	 * 
	 * @param entityClass
	 *            指定实体类
	 * @return 实体类对应表名
	 */
	public static String getTableName(Class<?> entityClass) {
		Table table = (Table) entityClass.getAnnotation(Table.class);
		return table.name();
	}

	public static String getTableNameWithCatalog(Class<?> entityClass) {
		Table table = (Table) entityClass.getAnnotation(Table.class);
		return table.catalog() + "." + table.name();
	}

	/**
	 * 初始化entity中为null的属性，支持的类型请再方法 getDefineType定义
	 * 
	 * @param entity
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("rawtypes")
	public static void initEntityAttribute(Object entity) {
		if (entity == null) {
			logger.debug("实体为空");
			return;
		}
		try {

			Class<? extends Object> entityClass = entity.getClass();

			Method[] methods = entityClass.getMethods();

			Set<Class> set = getDefineType().keySet();

			for (Method method : methods) {
				if (method.getName().startsWith("get")) {
					Object[] args = null;
					Object curField = method.invoke(entity, args);
					if (set.contains(method.getReturnType())) {
						if (curField == null) {
							Method mset = entityClass.getMethod("set"
									+ method.getName().substring(3),
									method.getReturnType());
							mset.invoke(entity,
									getDefineType().get(method.getReturnType()));
						}
					}
				}
			}
		} catch (Exception ex) {
			logger.error("初始化entity中得属性失败");
		}

	}

	/**
	 * 支持的转换的类型
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<Class, Object> getDefineType() {
		Map<Class, Object> map = new HashMap<Class, Object>();
		map.put(String.class, "");
		map.put(int.class, 0);
		map.put(long.class, 0);
		map.put(BigDecimal.class, new BigDecimal(0));
		map.put(Date.class, new Timestamp(System.currentTimeMillis()));
		return map;
	}

	/*
	 * 实体类字符串类型字段去掉尾部空格
	 */
	public static void trimEntityString(Object entity) {
		/* 参数判断 */
		if (entity == null) {
			logger.debug("传入对象为空");
			return;
		}

		Class<? extends Object> entityClass = entity.getClass();

		/*
		 * Table table = entityClass.getAnnotation(Table.class); if (table ==
		 * null) { logger.debug("对象所属类不是实体类"); return ; }
		 */
		Method[] methods = entityClass.getMethods();
		for (Method method : methods) {
			if (method.getReturnType() == String.class
					&& method.getName().startsWith("get")) { // 对String类型字段进行去空格
																// // &&
																// method.getAnnotation(Column.class)
																// != null

				logger.debug("method: " + method.getName() + " , 字段："
						+ method.getName().substring(3).toLowerCase());

				try {
					Method set = entityClass.getMethod("set"
							+ method.getName().substring(3), String.class);
					Object[] args = null;
					Object curField = method.invoke(entity, args);
					set.invoke(entity, StringUtils.trim((String) curField));

				} catch (SecurityException e) {
					logger.error("错误: ", e);
					continue;
				} catch (NoSuchMethodException e) {
					logger.error("字段不存在set方法: ", e);
					continue;
				} catch (IllegalArgumentException e) {
					logger.error("错误: ", e);
				} catch (IllegalAccessException e) {
					logger.error("错误: ", e);
				} catch (InvocationTargetException e) {
					logger.error("错误: ", e);
				}
			}
		}
	}

	public static <T> T transformMapToBean(Map<String, Object> m, T t,
			Class<?> entityClass) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			SecurityException, NoSuchMethodException {

		if (null != m) {
			Class<?> c = t.getClass();
			Method[] entityMethod = entityClass.getMethods();
			for (Map.Entry<String, Object> entry : m.entrySet()) {
				for (int i = 0; i < entityMethod.length; i++) {
					Method mm = entityMethod[i];
					Column column = mm.getAnnotation(Column.class);
					if (null != column) {
						if (column.name().equals(entry.getKey())) {
							String methodName = "set"
									+ mm.getName().substring(3);
							Method method = c.getMethod(methodName,
									mm.getReturnType());
							if (null != method) {
								String typeName = mm.getReturnType().getName();
								if (entry.getValue() instanceof String) {
									method.invoke(t, (String) entry.getValue());
								}
								if (entry.getValue() instanceof Character) {
									method.invoke(t,
											(Character) entry.getValue());
								}
								if (entry.getValue() instanceof Integer) {
									if ("java.lang.String".equals(typeName)) {
										method.invoke(t, ((Integer) entry
												.getValue()).toString());
									} else {
										method.invoke(t,
												(Integer) entry.getValue());
									}
								}
								if (entry.getValue() instanceof BigDecimal) {

									if ("int".equals(typeName)) {
										method.invoke(
												t,
												Integer.parseInt(((BigDecimal) entry
														.getValue()).toString()));
									} else if ("short".equals(typeName)) {
										method.invoke(
												t,
												Short.parseShort(((BigDecimal) entry
														.getValue()).toString()));
									} else if ("long".equals(typeName)) {
										method.invoke(
												t,
												Long.parseLong(((BigDecimal) entry
														.getValue()).toString()));
									} else if ("char".equals(typeName)) {
										method.invoke(t,
												(Character) entry.getValue());
									} else if ("java.lang.String"
											.equals(typeName)) {
										method.invoke(t, ((BigDecimal) entry
												.getValue()).toString());
									} else {
										method.invoke(t,
												(BigDecimal) entry.getValue());
									}

								}
								if (entry.getValue() instanceof Timestamp) {
									if ("java.util.Date".equals(typeName)) {
										method.invoke(t,
												(Timestamp) entry.getValue());
									}
								}

							}
							break;
						}
					}
				}
			}
		}

		return t;

	}

}
