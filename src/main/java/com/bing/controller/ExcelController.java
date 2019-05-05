package com.bing.controller;

import com.bing.anno.Auth;
import com.bing.anno.Test;
import com.bing.mapper.ProductYisunFilterHyMapper;
import com.bing.middleware.AliOssUploadServer;
import com.bing.model.ProductYisunFilterHy;
import com.bing.pack.LizardSystemCode;
import com.bing.pack.RequestParam;
import com.bing.pack.ResponseCollection;
import com.bing.pack.ResponseResult;
import com.bing.req.vo.TestReqVo;
import com.bing.res.vo.TestResVo;
import com.bing.service.ExcelService;
import com.bing.util.ExcelUtil;
import com.bing.util.LogUtils;
import com.bing.util.VerifyUtil;
import org.apache.http.entity.ContentType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/4/20
 * Time: 14:10
 * To change this template use File | Setting | File Template.
 **/
@RestController
@RequestMapping("/excel/")
public class ExcelController {
    Logger log = LogUtils.getBussinessLogger();

    @Autowired
    ExcelService excelService;
    @Autowired
    ProductYisunFilterHyMapper productYisunFilterHyMapper;
    @Autowired
    AliOssUploadServer aliOssUploadServer;

    @Test
    @PostMapping("testVerify")
    public ResponseResult<TestResVo> testVerify(@RequestBody RequestParam<TestReqVo> testReqVo) throws Exception{
        TestReqVo vo = testReqVo.getParams();
        if(!VerifyUtil.verifyParams(vo)){
            return ResponseResult.failNotice(LizardSystemCode.PARAMS_ERROR.msg());
        }
        System.out.println("1");
        TestResVo testResVo = new TestResVo();
        testResVo.setTestName(vo.getTestName());
        return ResponseResult.success(testResVo);
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
