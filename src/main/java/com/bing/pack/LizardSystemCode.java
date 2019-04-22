package com.bing.pack;




/**
 * 定义系统产生的代码。
 *
 */
public enum LizardSystemCode implements ResponseCode{

	SUCCESS("200","请求成功"),
	FAIL("-1","请求失败"),
	ERROR_404("404","系统资源未找到"),
	NOT_LOGIN("109","登陆超时"),
	NOT_BIND_PHONE("110","未绑定手机号"),
	FORBID_LOGIN("100","该用户被封禁"),
	PARAMS_ERROR("-1","参数校验失败"),
	SYSTEM_PROCESSING("-1","系统正在处理,请稍后"),
	RAS_SECURITY_ERROR("100","RSA解密失败"),
	UN_SAFE_COMMIT("100","非法操作"),
	USER_STATUS_FREEZE("-1","您的账户已被冻结，请联系客服"),
	CODE_1999("1999", "短信验证码已发送"),
	COMPANY_NO_PAYPASSWORD("101","您尚未设置支付密码"),
	COMPANY_NO_LISCE("120","您不是有效年会员");
	
	private String code;
	private String msg;
	private LizardSystemCode(String value,String desc){
		this.code=value;
		this.msg=desc;
	}
	
	public String code(){
		return this.code;
	}
	
	public String msg(){
		return this.msg;
	}
	
	public String toJsonStr(){
		return "{\"code\":"+code+",\"msg\":\""+msg+"\"}";
	}
	
	@Override
	public String toString(){
		return "WistySysCode[code="+code+",msg="+msg+"]";
	}
	
}
