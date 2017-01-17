package com.surpassli.www.myapp.support.utils.MD5;

import android.util.Log;

import java.math.BigInteger;
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
    public static String getMD5_new(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(32);
        } catch (Exception e) {
//            throw new SpeedException("MD5加密出现错误");
            Log.i("MD5", "getMD5: " +"加密出错");
        }
        return null;
    }
}
