package com.surpassli.www.myapp.support.utils.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by SurpassLi on 2017/1/16.
 */
public class MD5 {
    private static final String TAG = "MD5";

    public static String getMd5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
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
            result = buf.toString();
            System.out.println("result: " + result);//32位的加密
            System.out.println("result: " +
                    buf.toString().substring(8,24));//16位的加密
        } catch (NoSuchAlgorithmException e) {
            
        }
        return result;
    }
}
