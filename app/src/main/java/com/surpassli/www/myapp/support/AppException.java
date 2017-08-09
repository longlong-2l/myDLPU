package com.surpassli.www.myapp.support;

/**
 * Created by SurpassLi on 2017/1/10.
 * AppException
 */
public class AppException extends Exception {
    private static final long serialVersionUID = 1L;

    private int eCode;

    private Object eMsg;

    public AppException(int eCode,Object eMsg){
        this.eCode = eCode;
        this.eMsg = eMsg;
    }

    public int getEcode() {
        return eCode;
    }

    public Object getEmsg() {
        return eMsg;
    }
}
