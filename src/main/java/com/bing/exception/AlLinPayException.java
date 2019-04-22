package com.bing.exception;

import com.bing.pack.LizardSystemCode;
import com.bing.pack.ResponseCode;

/**
 * 通联自定义异常
 */
public class AlLinPayException extends RuntimeException {

	private static final long serialVersionUID = -1789127511995201505L;

	protected String code;

	protected Throwable exception;

	public AlLinPayException() {
		super(LizardSystemCode.FAIL.msg());
		this.code = LizardSystemCode.FAIL.code();
	}

	public AlLinPayException(ResponseCode code) {
		super(code.msg());
		this.code = code.code();
	}

	public AlLinPayException(String msg) {
		super(msg);
		this.code = LizardSystemCode.FAIL.code();
	}
}
