package com.thirdparty.common.network.entity;

import android.os.Build;

public class CommonReq<T> {
    private String token  = ""; //鉴权令牌(登录获取)
    private String ver    = "1.0.0"; //当前版本
    private String sys    = "android"; //系统名称	andriod,ios,wince,win,linux
    private String sysVer = String.valueOf(Build.VERSION.SDK_INT); ///Y	系统版本
    private T body;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getSysVer() {
        return sysVer;
    }

    public void setSysVer(String sysVer) {
        this.sysVer = sysVer;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
