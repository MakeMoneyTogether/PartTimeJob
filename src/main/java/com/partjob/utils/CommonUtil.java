package com.partjob.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;


/**
 * util公用方法
 */
public class CommonUtil {

    /**
     * 获取当前月份
     * 
     * @return
     */
    public static int getCurrentMonth() {
        Calendar ca = Calendar.getInstance();
        return ca.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前时间戳
     * 
     * @return
     */
    public static String getCurrentDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }
    
    /**
     * 获取当前时间戳
     * 
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    /**
     * 获取配置文件内容
     */
    /*
     * public static String getPropertyByKey(String key) { try { Properties pps = new Properties(); ClassPathResource
     * resource = new ClassPathResource("/appCfg/" + Constant.ACP_SDK_PROPERTY_NM); String ppsPath =
     * resource.getURL().getPath().replace("20%", ""); InputStream in = new BufferedInputStream(new
     * FileInputStream(ppsPath)); pps.load(in); String value = pps.getProperty(key); in.close(); return value; } catch
     * (Exception e) { e.printStackTrace(); } return ""; }
     */

    /**
     * 时间戳格式化为字符串
     * 
     * @since
     * @param time
     * @return
     */
    public static String transTimeStamp(Timestamp time) {
        String s = "";
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        s = sdf.format(time);
        return s;
    }

    public static String getPropetiesByKey(String propertieNm, String key) throws IOException {
        String ppsPath = System.getProperty("user.dir").replace("bin", "") + "upjas-conf" + File.separator + "appCfg"
                + File.separator + propertieNm;
        Properties pps = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(ppsPath));
            pps.load(in);
            String value = pps.getProperty(key).trim();
            in.close();
            return value;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 方法名 ：format 方法说明 ：把数字字符串格式化成两位小数形式 备注 ：数字字符串请先处理好精度，否则有可能会发生精度丢失
     * 
     * @param str
     *            -数字字符串
     * @return String 格式好的字符串
     */
    public static String format(String str) {

        // 空串返回0.00
        if (StringUtils.isBlank(str)) {
            return "0.00";
        }
        // 会计千分位格式,保留两位小数点
        NumberFormat fmt = null;
        StringBuffer buf = new StringBuffer("###,###.##");
        fmt = new DecimalFormat(buf.toString());
        fmt.setMinimumFractionDigits(2);
        fmt.setMaximumFractionDigits(2);

        return fmt.format(new BigDecimal(str));

    }

    /**
     * 方法名 ：getDiBigDecimalValue 方法说明 ：得到除以100后的BigDecimal值 备注 ：无
     * 
     * @param value
     *            -原值
     * @return BigDecimal 除以100后的值
     */
    public static BigDecimal getDiBigDecimalValue(BigDecimal value) {
        if (value == null) {
            return new BigDecimal("0");
        }
        return value.divide(new BigDecimal("100"));
    }

    public static BigDecimal getMuBigDecimalValue(BigDecimal value) {
        if (value == null) {
            return new BigDecimal("0");
        }
        return value.multiply(new BigDecimal("100"));
    }

    /**
     * 生成6位验证码
     * 
     * @since
     * @return
     */
    public static String createRandomVcode() {
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int) (Math.random() * 9);
        }
        return vcode;
    }
    
    /**
     * 将obj转为map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Object obj) throws Exception {    
        if(obj == null){    
            return null;    
        }   
  
        Map<String, Object> map = new HashMap<String, Object>();    
  
        Field[] declaredFields = obj.getClass().getDeclaredFields();    
        for (Field field : declaredFields) {    
            field.setAccessible(true);  
            map.put(field.getName(), field.get(obj));  
        }    
  
        return map;  
    }   

    /**
     * 获取当前网络ip
     * 
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 方法名 ：obj2String<BR>
     * 方法说明 ：对象转换成字符串<BR>
     * 备注 ：无
     * 
     * @param obj
     * @return
     * 
     */
    public static String obj2String(Object obj) {
        if (null == obj || obj.toString().trim().equals("")) {
            return "";
        } else if (obj instanceof Integer) {
            return ((Integer) obj).toString();
        } else if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).toString();
        } else if (obj instanceof Timestamp) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.format((Timestamp) obj);
            // return ((Timestamp)obj).toString();
        } else {
            return ((String) obj).trim();
        }
    }

    public static String obj2xml(Object obj ){
    	JAXBContext context;
		try {
			context = JAXBContext.newInstance(obj.getClass());
		  // 获取上下文对象  
        Marshaller marshaller = context.createMarshaller(); // 根据上下文获取marshaller对象  
          
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  // 设置编码字符集  
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化XML输出，有分行和缩进  
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);  
//        marshaller.marshal(obj,System.out);   // 打印到控制台  
          
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        marshaller.marshal(obj, baos);  
        String xmlObj = new String(baos.toByteArray());         // 生成XML字符串  
//        return xmlObj.substring(56); 
        return xmlObj;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}  
    }
    /**
     * @param xmlStr 字符串
     * @param c 对象Class类型
     * @return 对象实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T xml2Object(String xmlStr,Class<T> c)
    { 
        try
        { 
            JAXBContext context = JAXBContext.newInstance(c); 
            Unmarshaller unmarshaller = context.createUnmarshaller(); 
             
            T t = (T) unmarshaller.unmarshal(new StringReader(xmlStr)); 
             
            return t; 
             
        } catch (JAXBException e) {  e.printStackTrace();  return null; } 
         
    } 
    /**
     * 对字符串进行MD5加密
     * @since 
     * @param plainText
     * @return 返回加密后的字符串，异常返回 null
     */
    public static String toMD5(String plainText) {
        try {
            // 生成实现指定摘要算法的 MessageDigest 对象。
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 使用指定的字节数组更新摘要。
            md.update(plainText.getBytes());
            // 通过执行诸如填充之类的最终操作完成哈希计算。
            byte b[] = md.digest();
            // 生成具体的md5密码到buf数组
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 通过配置文件中的URL刷新对方的缓存
     * @since 
     * @param cacheName 缓存名称
     */
    public static void refreshCacheByUrl(String cacheName){
            //String refreshCacheUrl = CommonUtil.getPropetiesByKey(Constant.CONFIG_PROPERTIES, Constant.REFRESH_CACHE_KEY);
           String refreshCacheUrl="";
        	String param = "cacheName=" + cacheName;
            HttpRequestUtil.sendPost(refreshCacheUrl, param);
        
    }
}
