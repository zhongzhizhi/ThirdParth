package com.thirdparty.common.channel;

import com.alibaba.fastjson.JSON;

public class Packet<T> {
	public byte cmd; // 命令	
	public byte flags; // 特性，如是否加密，是否压缩等
	public int sessionId; // 会话id。客户端生成。
	public byte lrc; // 校验，纵向冗余校验。只校验head
	public T body;

	public Packet() {

	}

	public byte getCmd() {
		return cmd;
	}

	public void setCmd(byte cmd) {
		this.cmd = cmd;
	}

	public byte getFlags() {
		return flags;
	}

	public void setFlags(byte flags) {
		this.flags = flags;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public byte getLrc() {
		return lrc;
	}

	public void setLrc(byte lrc) {
		this.lrc = lrc;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
