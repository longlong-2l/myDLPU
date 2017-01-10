package com.surpassli.www.myapp.support.listener;

/**
 * Created by SurpassLi on 2017/1/10.
 */
public class DisposeDataHandle {
    public DisposeDataListener mListener = null;
    public Class<?> mClass = null;
//    public String mSource = null;

    public DisposeDataHandle(DisposeDataListener listener)
    {
        this.mListener = listener;
    }

    /**
     * 有字节码时调用的方法
     * @param listener
     * @param clazz
     */
    public DisposeDataHandle(DisposeDataListener listener, Class<?> clazz)
    {
        this.mListener = listener;
        this.mClass = clazz;
    }

//    public DisposeDataHandle(DisposeDataListener listener, String source)
//    {
//        this.mListener = listener;
//        this.mSource = source;
//    }
}
