package com.bing.pack;



import com.bing.exception.SystemException;

import java.io.Serializable;


public class ResponseResult<R> implements Serializable {
	private static final long serialVersionUID = 319987396861861088L;

	private boolean success = false;

	private String code;

	private String msg = null;

	private R result = null;

	public ResponseResult() {
	}

	public ResponseResult(String code, String message) {
		this(code, message, null);
	}

	public ResponseResult(ResponseCode code) {
		this(code.code(), code.msg(), null);
	}

	public ResponseResult(ResponseCode code, R result) {
		this(code.code(), code.msg(), result);
	}

	public ResponseResult(String code, String message, R result) {
		this.code = code;
		if (this.code == LizardSystemCode.SUCCESS.code())
			this.success = true;
		this.msg = message;
		this.result = result;
	}

	public ResponseResult<R> success(boolean success) {
		this.success = success;
		return this;
	}

	public ResponseResult<R> setResult(R data) {
		this.result = data;
		return this;
	}

	public ResponseResult<R> setCode(String code) {
		this.code = code;
		return this;
	}

	public ResponseResult<R> setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public R getResult() {
		return result;
	}

	public static <T> ResponseResult<T> failNotice(String message) {
		return new ResponseResult<T>(LizardSystemCode.FAIL.code(), message);
	}
	public static <T> ResponseResult<T> codeNotice(String code,String message) {
		return new ResponseResult<T>(code, message);
	}

	public static <T> ResponseResult<T> fail() {
		return new ResponseResult<T>(LizardSystemCode.FAIL);
	}

	public static <T> ResponseResult<T> fail(String message, T result) {
		return new ResponseResult<T>(LizardSystemCode.FAIL.code(), message, result);
	}
	
	public static <T> ResponseResult<T> fail(ResponseCode syscode, T result) {
		return new ResponseResult<T>(syscode.code(), syscode.msg(), result);
	}

	public static <T> ResponseResult<T> fail(SystemException ex) {
		return new ResponseResult<T>(ex.getCode(), ex.getMessage());
	}

	public static <T> ResponseResult<T> fail(ResponseCode syscode) {
		return new ResponseResult<T>(syscode);
	}

	public static <T> ResponseResult<T> paramError(String message) {
		return new ResponseResult<T>(LizardSystemCode.PARAMS_ERROR.code(), message);
	}

	public static <T> ResponseResult<T> notLogin() {
		return new ResponseResult<T>(LizardSystemCode.NOT_LOGIN);
	}

	public static <T> ResponseResult<T> notLogin(String message) {
		return new ResponseResult<T>(LizardSystemCode.NOT_LOGIN.code(), message);
	}

	public static <T> ResponseResult<T> success() {
		return new ResponseResult<T>(LizardSystemCode.SUCCESS);
	}

	public static <T> ResponseResult<T> successNotice(String message) {
		return new ResponseResult<T>(LizardSystemCode.SUCCESS.code(), message);
	}

	public static <T> ResponseResult<T> success(T result) {
		return new ResponseResult<T>(LizardSystemCode.SUCCESS, result);
	}
	public static <T> ResponseResult<T> failException(T result) {
		return new ResponseResult<T>(LizardSystemCode.FAIL, result);
	}

	public static <T> ResponseResult<T> success(String message, T result) {
		return new ResponseResult<T>(LizardSystemCode.SUCCESS.code(), message,
				result);
	}
	public static <T> ResponseResult<T> successCode(String code,String message, T result) {
		return new ResponseResult<T>(code, message,
				result);
	}

	public String toJsonString() {
		return "{\"code\":" + code + ",\"msg\":\"" + msg + "\"}";
	}
}
