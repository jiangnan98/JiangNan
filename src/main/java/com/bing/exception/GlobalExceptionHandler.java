package com.bing.exception;

import com.bing.pack.ArgumentInvalidResult;
import com.bing.pack.ResponseResult;
import com.bing.util.ExceptionUtil;
import com.bing.util.LogUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/4/30
 * Time: 10:45
 * To change this template use File | Setting | File Template.
 **/

@ControllerAdvice
//如果返回的为json数据或其它对象，添加该注解
@ResponseBody
public class GlobalExceptionHandler {
    Logger log = LogUtils.getBussinessLogger();
    Logger logException = LogUtils.getExceptionLogger();

    @ExceptionHandler(value=Exception.class)
    public ResponseResult<ArgumentInvalidResult> MethodArgumentNotValidHandler(Exception exception) throws Exception {
        //按需重新封装需要返回的错误信息
        ArgumentInvalidResult invalidArguments = new ArgumentInvalidResult();
        log.info("进入异常处理");
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        invalidArguments.setDefaultMessage(exception.getMessage());
        if(StringUtils.isBlank(exception.getMessage()))
            invalidArguments.setField(ExceptionUtil.getStackTrace(exception));
        try{
            StackTraceElement str = exception.getStackTrace()[0];
            invalidArguments.setClassName(str.getClassName());
            invalidArguments.setFiledName(str.getFileName());
            invalidArguments.setLineNumber(str.getLineNumber());
            invalidArguments.setMethodName(str.getMethodName());
            //错误入库
            logException.info(invalidArguments.toString());
        }catch (Exception e){
            //不做任何处理即可
        }
        return ResponseResult.failException(invalidArguments);
    }
}
