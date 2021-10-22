package com.thirdparty.common.channel;

/**
 * @项目名： HappyChat
 * @包名： com.thirdparty.common.channel
 * @文件名: ReplyPacket
 * @创建者: Administrator
 * @创建时间: 2021/10/20 18:45
 * @描述： TODO
 */
public class ReplyPacket<T> {

    private String msg;
    private int code;
    private int cmd;
    private int session;
    private T body;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }
}
