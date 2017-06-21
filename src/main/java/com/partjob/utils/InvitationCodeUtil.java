package com.partjob.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Sloriac on 17/5/27.
 * 邀请码生成与解析工具
 */
public class InvitationCodeUtil {
    /** 自定义进制(0,1没有加入,容易与o,l混淆) */
    private static final char[] r=new char[]{'q', 'w', 'e', '8', 'a', 's', '2', 'd', 'z', 'x', '9', 'c', '7', 'p', '5', 'i', 'k', '3', 'm', 'j', 'u', 'f', 'r', '4', 'v', 'y', 'l', 't', 'n', '6', 'b', 'g', 'h'};
    /** (不能与自定义进制有重复) */
    private static final char b='o';
    /** 进制长度 */
    private static final int binLen=r.length;
    /** 序列最小长度 */
    private static final int s=6;

    /**
     * 根据ID生成六位随机码
     * @param id ID
     * @return 随机码
     
    public static String toSerialCode(int id) {
        char[] buf=new char[32];
        int charPos=32;

        while((id / binLen) > 0) {
            int ind=(int)(id % binLen);
            buf[--charPos]=r[ind];
            id /= binLen;
        }
        buf[--charPos]=r[(int)(id % binLen)];
        String str=new String(buf, charPos, (32 - charPos));
        // 不够长度的自动随机补全
        if(str.length() < s) {
            StringBuilder sb=new StringBuilder();
            sb.append(b);
            Random rnd=new Random();
            for(int i=1; i < s - str.length(); i++) {
                sb.append(r[rnd.nextInt(binLen)]);
            }
            str+=sb.toString();
        }
        return str;
    }
    */
    /**
     * 验证码转换规则[012345] <- [051423]
     * [0] = [0] + 0 % 10
     * [1] = [5] + 5 % 10
     * [2] = [1] + 1 % 10
     * [3] = [4] + 4 % 10
     * [4] = [2] + 2 % 10
     * [5] = [3] + 3 % 10
     * @param id
     * @return
     */
    public static String toSerialCode(int id){
    	String code0 = String.valueOf(id);
    	int deta = s-code0.length();
    	for(int i=0;i<deta;i++){
    		code0 = '0'+code0;
    	}
    	StringBuffer code = new StringBuffer();
    	
    	code.append((code0.charAt(0) - '0'));
    	code.append((code0.charAt(5) - '0' + 5)%10);
    	code.append((code0.charAt(1) - '0' + 1)%10);
    	code.append((code0.charAt(4) - '0' + 4)%10);
    	code.append((code0.charAt(2) - '0' + 2)%10);
    	code.append((code0.charAt(3) - '0' + 3)%10);
    	
    	return String.valueOf(code);
    }

    
    /**
     * 验证码转换规则[012345] <- [024531]
     * [0] = [0] + 0 % 10
     * [1] = [2] - 1 % 10
     * [2] = [4] - 2 % 10
     * [3] = [5] - 3 % 10
     * [4] = [3] - 4 % 10
     * [5] = [1] - 5 % 10
     * @param code
     * @return
     */
    public static int codeToId(String code){
    	int id = 0;
    	id = code.charAt(0) - '0';
    	id = id * 10 + (code.charAt(2)-'0' -1+10)%10;
    	id = id * 10 + (code.charAt(4)-'0' -2+10)%10;
    	id = id * 10 + (code.charAt(5)-'0' -3+10)%10;
    	id = id * 10 + (code.charAt(3)-'0' -4+10)%10;
    	id = id * 10 + (code.charAt(1)-'0' -5+10)%10;
    	return id;
    }
    
    /*
    public static int codeToId(String code) {
        char chs[]=code.toCharArray();
        int res=0;
        for(int i=0; i < chs.length; i++) {
            int ind=0;
            for(int j=0; j < binLen; j++) {
                if(chs[i] == r[j]) {
                    ind=j;
                    break;
                }
            }
            if(chs[i] == b) {
                break;
            }
            if(i > 0) {
                res=res * binLen + ind;
            } else {
                res=ind;
            }
        }
        return res;
    }
    */

    public static void main(String[] args) {
        System.out.println(toSerialCode(1));
        System.out.println(toSerialCode(2));
        System.out.println(codeToId("061423"));
        System.out.println(codeToId("071423"));
//        System.out.println(toSerialCode(1));
//        System.out.println(codeToId("wot7iv"));
//        System.out.println(codeToId("woqt8z"));
//        Set<String> codes = new HashSet<>();
//        for (int i = 0; i < 100000; i++) {
//            String code = toSerialCode(i);
//            assert codeToId(code) == i;
//            codes.add(code);
//        }
//        System.out.println(codes.size());
    }
}
