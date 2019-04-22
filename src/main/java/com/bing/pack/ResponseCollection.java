package com.bing.pack;

import java.io.Serializable;
import java.util.Collection;

public class ResponseCollection<R> implements Serializable {
	private static final long serialVersionUID = 319987396861861088L;

	private boolean success = false;
	private String code;
	private String msg = null;
	private PageInfo page;

	public PageInfo getPage() {
		return page;
	}

	public void setPage(PageInfo page) {
		this.page = page;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	private Collection<R> result = null;

	public ResponseCollection() {
	}

	public ResponseCollection(String code, String message) {
		this(code, message, null);
	}

	public ResponseCollection(ResponseCode code) {
		this(code.code(), code.msg(), null);
	}

	public ResponseCollection(ResponseCode code, Collection<R> result) {
		this(code.code(), code.msg(), result);
	}

	public ResponseCollection(ResponseCode code, Collection<R> result,
			PageInfo page) {
		this(code.code(), code.msg(), result, page);
	}

	public ResponseCollection(String code, String message, Collection<R> result) {
		this.code = code;
		if (this.code == LizardSystemCode.SUCCESS.code())
			this.success = true;
		this.msg = message;
		this.result = result;
	}

	public ResponseCollection(String code, String message,
			Collection<R> result, PageInfo page) {
		this.code = code;
		if (this.code == LizardSystemCode.SUCCESS.code())
			this.success = true;
		this.msg = message;
		this.result = result;
		this.page = page;
	}

	public ResponseCollection<R> success(boolean success) {
		this.success = success;
		return this;
	}

	public ResponseCollection<R> setResult(Collection<R> data) {
		this.result = data;
		return this;
	}

	public ResponseCollection<R> setCode(String code) {
		this.code = code;
		return this;
	}

	public ResponseCollection<R> setMsg(String msg) {
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

	public Collection<R> getResult() {
		return result;
	}

	public static <T> ResponseCollection<T> failNotice(String message) {
		return new ResponseCollection<T>(LizardSystemCode.FAIL.code(), message);
	}

	public static <T> ResponseCollection<T> fail() {
		return new ResponseCollection<T>(LizardSystemCode.FAIL);
	}

	public static <T> ResponseCollection<T> fail(String message,
			Collection<T> result) {
		return new ResponseCollection<T>(LizardSystemCode.FAIL.code(), message,
				result);
	}

	public static <T> ResponseCollection<T> fail(ResponseCode syscode) {
		return new ResponseCollection<T>(syscode);
	}

	public static <T> ResponseCollection<T> paramError(String message) {
		return new ResponseCollection<T>(LizardSystemCode.FAIL.code(), message);
	}

	public static <T> ResponseCollection<T> notLogin() {
		return new ResponseCollection<T>(LizardSystemCode.NOT_LOGIN);
	}

	public static <T> ResponseCollection<T> notLogin(String message) {
		return new ResponseCollection<T>(LizardSystemCode.NOT_LOGIN.code(),
				message);
	}

	public static <T> ResponseCollection<T> success() {
		return new ResponseCollection<T>(LizardSystemCode.SUCCESS);
	}

	public static <T> ResponseCollection<T> successNotice(String message) {
		return new ResponseCollection<T>(LizardSystemCode.SUCCESS.code(), message);
	}

	public static <T> ResponseCollection<T> success(Collection<T> result,
			PageInfo page) {
		return new ResponseCollection<T>(LizardSystemCode.SUCCESS, result, page);
	}

	public static <T> ResponseCollection<T> success(Collection<T> result) {
		return new ResponseCollection<T>(LizardSystemCode.SUCCESS,result);
	}

	public String toJsonString() {
		return "{\"code\":" + code + ",\"msg\":\"" + msg + "\"}";
	}
}
