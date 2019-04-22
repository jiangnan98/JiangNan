package com.bing.adapter;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @Desc 全局异常处理类
 * @Author Wisty
 * @Date 2018年10月24日
 */
@ControllerAdvice
public class GlobalExceptionAdapter {

//	@ExceptionHandler(HttpMessageNotReadableException.class)
//	@ResponseBody
//	public ResponseResult<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
//		return ResponseResult.failNotice(LizardSystemCode.PARAMS_ERROR.msg());
//	}

}
