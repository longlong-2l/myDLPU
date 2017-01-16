package com.surpassli.www.myapp.support.utils.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by dell on 2017/1/16.
 */
public class getMD5 {

    public static String getMD5(String val) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(val.getBytes());
        byte[] m = md5.digest();//加密
        return getString(m);
    }

    private static String getString(byte[] b){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < b.length; i ++){
            sb.append(b[i]);
        }
        return sb.toString();
    }
}
