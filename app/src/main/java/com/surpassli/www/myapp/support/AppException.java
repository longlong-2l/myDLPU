package com.surpassli.www.myapp.support;

/**
 * Created by dell on 2017/1/10.
 */
public class AppException extends Exception {
    private static final long serialVersionUID = 1L;

    private int ecode;

    private Object emsg;

    public AppException(int ecode,Object emsg){
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public int getEcode() {
        return ecode;
    }

    public Object getEmsg() {
        return emsg;
    }
}
