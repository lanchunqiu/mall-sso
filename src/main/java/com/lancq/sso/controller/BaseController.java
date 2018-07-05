package com.lancq.sso.controller;

/**
 * @Author lancq
 * @Description
 * @Date 2018/7/5
 **/
public class BaseController {
    static ThreadLocal<String> uidThreadLocal = new ThreadLocal<String>();

    public void setUid(String uid){
        uidThreadLocal.set(uid);
    }

    public String getUid(){
        return uidThreadLocal.get();
    }
}
