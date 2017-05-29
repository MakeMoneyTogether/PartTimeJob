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
     */
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

    public static void main(String[] args) {
        System.out.println(toSerialCode(1));
        System.out.println(toSerialCode(2));
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
