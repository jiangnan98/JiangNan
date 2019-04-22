package com.bing.exception;

import com.bing.pack.LizardSystemCode;
import com.bing.pack.ResponseCode;


/**
 * @Desc RSA
 * @Author Wisty
 * @Date 2018年10月24日
 */
public class RSASecurityException extends RuntimeException {

	private static final long serialVersionUID = -9058127557311211064L;
	
	protected String code;
	
	protected Throwable exception;

	public RSASecurityException() {
		super(LizardSystemCode.FAIL.msg());
		this.code = LizardSystemCode.FAIL.code();
	}

	public RSASecurityException(ResponseCode code) {
		super(code.msg());
		this.code = code.code();
	}

	public RSASecurityException(ResponseCode code, String msg) {
		super(msg);
		this.code = code.code();
	}

	public RSASecurityException(ResponseCode code, Throwable e) {
		super(code.msg(), e);
		this.code = code.code();
	}

	public RSASecurityException(ResponseCode code, String msg, Throwable e) {
		super(msg, e);
		this.code = code.code();
	}

	public String getCode() {
		return this.code;
	}
}
