package com.bing.exception;


import com.bing.pack.LizardSystemCode;
import com.bing.pack.ResponseCode;

public class SystemException extends RuntimeException {

	private static final long serialVersionUID = -9058127557311211064L;
	protected String code;
	protected Throwable exception;

	public SystemException() {
		super(LizardSystemCode.FAIL.msg());
		this.code = LizardSystemCode.FAIL.code();
	}

	public SystemException(ResponseCode code) {
		super(code.msg());
		this.code = code.code();
	}

	public SystemException(ResponseCode code, String msg) {
		super(msg);
		this.code = code.code();
	}

	public SystemException(ResponseCode code, Throwable e) {
		super(code.msg(), e);
		this.code = code.code();
	}

	public SystemException(ResponseCode code, String msg, Throwable e) {
		super(msg, e);
		this.code = code.code();
	}

	public String getCode() {
		return this.code;
	}

	public String toJsonString() {
		return "{\"code\":\"" + code + "\",\"msg\":\"" + getMessage() + "\"}";
	}

	@Override
	public String toString() {
		return "SysException [ code=" + code + ", message=" + getMessage()
				+ ", exception="
				+ (exception != null ? exception.getMessage() : "") + "]";
	}
}
