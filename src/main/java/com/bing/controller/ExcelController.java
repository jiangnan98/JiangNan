package com.bing.controller;

import com.bing.anno.Auth;
import com.bing.constants.RedisKey;
import com.bing.mapper.ProductYisunFilterHyMapper;
import com.bing.middleware.AliOssUploadServer;
import com.bing.pack.LizardSystemCode;
import com.bing.pack.RequestParam;
import com.bing.pack.ResponseResult;
import com.bing.req.vo.TestReqVo;
import com.bing.res.vo.TestResVo;
import com.bing.service.ExcelService;
import com.bing.util.LogUtils;
import com.bing.util.VerifyUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.MessageFormat;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/4/20
 * Time: 14:10
 * To change this template use File | Setting | File Template.
 **/
@RestController
@RequestMapping("/excel/")
public class ExcelController extends BaseController {
    Logger log = LogUtils.getBussinessLogger();

    @PostMapping("testVerify")
    public ResponseResult<TestResVo> testVerify(@RequestBody RequestParam<TestReqVo> testReqVo) throws Exception{
        TestReqVo vo = testReqVo.getParams();
        if(!VerifyUtil.verifyParams(vo)){
            return ResponseResult.failNotice(LizardSystemCode.PARAMS_ERROR.msg());
        }
        TestResVo testResVo = new TestResVo();
        testResVo.setTestName(null);
        testResVo.setAa(22);
        return ResponseResult.success(testResVo);
    }


    @PostMapping("testLock")
    public ResponseResult<String> testLock() throws Exception{
        String token = super.getToken();
        String lockKey = MessageFormat.format(RedisKey.RED_ENVELOPES,token);

        return ResponseResult.success();
    }




    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}
