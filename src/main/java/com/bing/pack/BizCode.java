package com.bing.pack;

/**
 * 定义业务产生的代码。
 *
 */
public enum BizCode implements ResponseCode {
	
	//用户反馈
	FEED_BACK_ERROR("意见反馈失败"),
	
	//资源上传提示语
	FILE_NOT_EXIST("上传的资源不存在"), 
	FILE_NOT_MATCH("请上传PNG、JPG格式的文件"),
	FILE_NOT_LEGAL("非法的上传资源"),
	FILE_UPLOAD_FAIL("图片上传失败"),
	FILE_TO_LARGE("上传的图片资源过大"),
	
	//商品相关
	PRODUCT_NOT_EXIST("所查询的商品不存在"),
	
	//用户购物车相关
	SHOP_CAR_NOT_MINUS("购物车不能在减少了"),
	SHOP_CAR_NOT_MAX("库存不足,请联系供应商"),
	SHOP_CAR_PRODUCT_NOT_EXIST("购物车操作商品不存在"),
	SHOP_CAR_COUNT_MORE_STOCKMAX("商品所选数量不可大于库存数量"),
	SHOP_CAR_OPER_ERROR("购物车操作失败,请联系管理员"),
	
	//订单相关
	SUBMIT_ORDER_FAIL("订单提交失败,请联系管理员"),
	NOT_PRODUCT_FOR_ORDER("没有可提交的商品"),
	ORDER_NOT_EXIST("查无此订单"),
	ORDER_REFUND_ALL("该订单已全部完成，无法再次申请退款"),
	ORDER_IS_FREEZE("该订单已经申请售后"),
	ORDER_REFUND_TIMEOUT("该订单已超过退款期限，无法发起退款"),
	ORDER_REFUND_YET("该订单已无法退款"),
	ORDER_ALREADY_PAY("订单超时或已支付，请您重新下单"),
	ORDER_REFUND_AUTHING("您尚有正在进行中的退款申请，无法再次申请"),
	ORDER_NO_STRIP("您的权限不足，无法挂账购买商品"),
	ORDER_NOT_VERIFY("非法订单"),
	ORDER_STRIP_FREEZE("您的账期购买权限已被冻结，请联系客服人员"),
	ORDER_STRIP_PAYING("您当前有未还账期，请您还款后购买"),
	ORDER_STRIP_NOMONEY("挂账购买余额不足，无法购买"),
	//退款原因
	REASON_NOT_EXIST("请选择正确的退款原因"),
	REASON_PRODUCT_NOTEXIST("请正确选择您的退款货物"),
	REASON_PRODUCT_NOTUSED("退款业务无法使用，请联系客服"),
	//退款相关
	REFUND_NOT_EXIST("查无该退款订单"),
	REFUND_AUTHING_NOT("您不具备权限进行该项操作"),
	REFUND_HAS_EXIST("已经有一个进行中的退款流程"),
	REFUND_STATUS_EXCEPTION("退款单状态异常，您无法进行此操作"),
	REFUND_STATUS_ALREADY("退款单已被处理，无法再次进行本操作"),
	REFUND_FREEZE_MONEY_NOT_EXIST("退款金额计算有误"),
	REFUND_SUBMIT_FRIST("请先上传退款货物照片"),
	
	
	//支付宝，微信支付提示语
	ALIPAY_NO_FAIL("支付预订单生成失败"),
	ALIPAY_ORDER_TIMEOUT("订单超时，请您重新下单"),
	ALIPAY_VIP_NOTEXIST("Vip会员卡种失效，请您选择其他Vip会员卡种"),
	WX_NO_FAIL("支付预订单生成失败"),
	//通联支付提示语
	ALINPAY_BANKID_NOTEXIST("请选择您的支付银行卡"),
	ALINPAY_BANKID_FAIL("您的银行卡信息异常，请联系客服"),
	ALINPAY_PAY_FAIL("快捷支付失败，请稍后再试"),
	ALINPAY_TIME_OUT("支付超时，请重新支付"),
	
	
	//钱包支付提示语
	WALLET_NO_COMPANY("查无该公司"),
	WALLET_NO_NEW_CARD("查无该被购卡"),
	WALLET_NO_PERSON("查无该用户"),
	WALLET_NO_MONEY("您的余额不足"),
	PAY_NO_TYPE("请选择支付方式"),
	//提现
	DISP_SMALL("您的单笔提现金额过低"),
	DISP_BIG("您的单笔提现金额不得高于5000元"),
	//短信发送提示语
	SMS_SEND_REEOR("短信发送失败"),
	SMS_CODE_INVALID("短信验证码失效,请重新发送"),
	SMS_PHONE_NOT_MATCH("手机号不合法,请确认"),
	SMS_CODE_VERIFY_ERROR("短信验证失败,请确认"),
	//用户注册提示语
	PHONE_HAS_REGISTER("该手机号已经被注册"),
	PHONE_HAS_BIND("该手机号已经被绑定"),
	VERIFY_OLD_PWD_ERROR("旧密码校验失败"),
	USER_NOT_EXIST("该用户不存在"),
	USER_FREEZE("您的账户已被冻结，请联系客服人员"),
	USER_REGISTER_ERROR("注册失败,请联系管理员"), 
	USER_UN_MATCH("用户名或密码错误"),
	USER_NOT_AUTH_LOGIN("你没有权限登录"),
	USER_PAYPW_ERROR("您的支付密码输入错误"),
	USER_STATUS_ABNORMAL("用户状态异常"),
	SELLER_MONEY_NOTEN("代理商余额不足，无法退款"),
	INVITE_USER_NOT_EXIST("请确认填写的邀请码是否正确"),
	PHONE_HAS_GOLDREGISTER("金牌供应商手机号已经被注册"),
	PHONE_HAS_NORMALREGISTER("合作供应商手机号已经被注册"),
	USER_NOT_DIST("您不是县区级代理，无法查看信息"),
	USER_NOTA_GEN("您不是修理厂用户，无法查看信息"),
	USER_QR_AGAIN("登陆超时，请您重新扫码"),
	
	//提现申请与审核
	DISP_NOT_EXIST("查无该提现记录"),
	DISP_MONEY_NO("提现金额不得低于1元"),
	DISP_ALREADY("该提现记录已被处理，请勿重复操作"),
	//通联第三方支付
	HAS_BIND_BANK_CARD("该银行卡已经被绑定"),
	BIND_BANK_CARD_FAIL("银行卡绑定失败"),
	BANK_CARD_NOT_EXIST("银行卡不存在"),
	UNTYING_BANK_CARD_FAIL("银行卡解绑失败"),
	UNTYING_BANK_CARD_SUCCESS("银行卡解绑成功"),
	BANK_CARD_SMS_FAIL("重发签约短信验证码失败"),
	ALLIN_PAY_FAIL("通联支付失败"),
	ALLIN_PAY_SUCCESS("通联支付成功"),
	PAY_SMS_FAIL("支付短信验证码获取失败"),
	//发票
	INVOICE_NOT_EXIST("该发票记录已被删除"),
	
	
	//图片操作提示语
	IMAGE_KAPTCHA_VERIFY_ERROR("图形验证码校验失败"),
	LICENSE_OCR_FAIL("营业执照识别失败"),
	VIN_OCR_FAIL("车辆VIN识别失败"),
	//创建店铺提示语
	COMPANY_NO_USER("查无该用户"),
	COMPANY_NO_LISCE("您不是有效年会员，无法申请门店"),
	COMPANY_NO_APPLY("查无申请记录"),
	COMPANY_NOT_ADMIN("您不是该门店管理员"),
	COMPANY_NOT_AUTHING("门店审核已被处理，您暂时无法撤销申请"),
	COMPANY_NO_COMPANY("查无该门店"),
	COMPANY_NOT_EXIST("门店信息异常"),
	COMPANY_IS_NORMAL("门店审核已被处理"),
	COMPANY_HAVE_COMPANY("您已有所属公司或申请中公司"),
	COMPANY_NORMAL_USER("您已是有效会员"),
	COMPANY_BELONG_USER("您已有归属门店"),
	COMPANY_BELONG_EMP("该员工已有归属门店"),
	COMPANY_STATUS_ERROR("门店未通过审核"),
	COMPANY_SIZE_FULL("门店成员已满"),
	COMPANY_FREEZE_YES("您的会员权限已被冻结"),
	COMPANY_EMP_ERROR("该员工状态异常"),
	COMPANY_EMP_NOT_YOURS("该用户并不属于您的店内员工"),
	COMPANY_STRIP_NOT_RETURN("您尚有未结清账期，无法转让门店"),
	COMPANY_EMP_NOMORE("该员工已解绑当前门店"),
	COMPANY_EMP_AREADLY("该员工已绑定您的门店"),
	COMPANY_EMP_VIP("该员工已成为个人会员，无法加入门店"),
	COMPAY_EMP_PRICEERROR("员工名额价格计算有误，请联系客服"),
	COMPAY_EMP_NUMERROR("员工名额购买数量有误，请重新填写"),
	COMPANY_ADMIN_ERROR("您不是当前门店管理员"),
	COMPANY_APPLY_AUTHING("您已有申请中记录，请勿重复申请"),
	COMPANY_NO_APPLY_AUTHING("没有您的申请记录，或您的申请记录已被处理"),
	COMPANY_NOT_APPLY_AUTHING("您的申请记录已被处理,无法被撤销"),
	COMPANY_FULL_SIZE("门店员工已达到人数上限，您请购买员工名额"),
	COMPANY_APPLY_REPLACE("请不要重复认证店铺"),
	//申请挂账提示语
	STRIP_NO_COMPANY("您还没有开通门店，无法申请挂账"),
	STRIP_IS_EXIST("您已有挂账申请记录，无法再次申请"),
	STRIP_NO_RECORD("您的当前账单暂未生成"),
	STRIP_NOT_ADMIN("挂账功能必须购买年会员并申请店铺才有权限申请挂账"),
	STRIP_IS_AREADLYPAY("您的账期已经归还，请勿重复归还账期"),
	STRIP_NOT_EXIST("您的账期异常，请联系客服"),
	STRIP_NOT_VIP("您不是有效年会员，无法进行相关操作"),
	STRIP_NO_EXIST("挂账信息异常，请联系客服"),
	STRIP_ALREADY_AUTHING("挂账信息已被审核，请勿重复操作"),
	STRIP_REFUND_NOTEXIST("挂账资质异常，无法退款"),
	STRIP_FREEZE("挂账权限已被冻结"),
	STRIP_NOT_RETURN("账期未还，无法退款"),
	STRIP_NOT_AUTH("您的用户身份异常，无法申请挂账"),
	//vip权限提示语
	VIP_NO_LISCE("您还不是有效会员"),
	VIP_NOT_EXIST("查无该卡，无法购买"),
	//询价单提示语
	SHEET_VIP_FREEZE("您的Vip权限已被冻结，请联系客服人员"),
	SHEET_EXCEPTION("询价单信息异常，无法查看"),
	SHEET_TIMEOUT("询价单过期，无法进行报价"),
	SHEET_COMPLETE("询价单已完成，无法进行报价"),
	SHEET_NOT_VIP("您不是有效Vip会员，无法发布询价单"),
	SHEET_NOT_OEM("请您填写询价内容"),
	SHEET_NOT_OFFER("请您填写报价内容"),
	SHEET_STATUS_FREEZE("您的账户已被冻结，请联系客服人员"),
	SHEET_REDIS_FULL("您最多只能添加4条询价零件信息"),
	SHEET_REDIS_LOST("询价零件信息有误，无法操作"),
	SHEET_NO_AUTH("您不具备询价相关操作权限"),
	SHEET_FULL("该询价单已超过报价人数上限，请您选择其他询价单报价"),
	SHEET_NO_PRODUCT("您尚未选择经营范围"),
	SHEET_EXCEPTION_PUBLIC("询价单信息异常，无法重新发布"),
	
	//订货相关
	ORDER_BOOK_HAS_APPLY("商品已经有一个申请的订货单"),
	ORDER_BOOK_ERROR("该订货单异常");
	
	private String msg;

	private BizCode(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	@Override
	public String code() {
		return null;
	}

	@Override
	public String msg() {
		return this.msg;
	}

}
