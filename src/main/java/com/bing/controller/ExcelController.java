package com.bing.controller;

import com.bing.anno.Auth;
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

    @Auth
    @PostMapping("testVerify")
    public ResponseResult<TestResVo> testVerify(@RequestBody RequestParam<TestReqVo> testReqVo) throws Exception{
        TestReqVo vo = testReqVo.getParams();
        if(!VerifyUtil.verifyParams(vo)){
            return ResponseResult.failNotice(LizardSystemCode.PARAMS_ERROR.msg());
        }
        TestResVo testResVo = new TestResVo();
        testResVo.setTestName(vo.getTestName());
        return ResponseResult.success(testResVo);
    }

    @PostMapping("test2")
    public ResponseResult<TestResVo> test2(@RequestBody RequestParam<TestReqVo> testReqVo) throws Exception{
        TestReqVo vo = testReqVo.getParams();
        if(!VerifyUtil.verifyParams(vo)){
            return ResponseResult.failNotice(LizardSystemCode.PARAMS_ERROR.msg());
        }
        TestResVo testResVo = new TestResVo();
        testResVo.setTestName(vo.getTestName());
        return ResponseResult.success(testResVo);
    }

    @PostMapping("hyFilter")
    public void findList() throws Exception{
        File file = new File("D:\\aaa\\data\\海业滤清.xlsx");
        InputStream is = new FileInputStream(file);
        Workbook wb = new XSSFWorkbook(is);
        Sheet sheet = wb.getSheetAt(1);
        is.close();

        //获取行数
        int rowLens = sheet.getLastRowNum();
        log.info("行数："+rowLens);
//        Row row = sheet.getRow(255);
        //获取车型数据
        for(int i = 0;i<rowLens;i++){
            ProductYisunFilterHy productYisunFilterHy = new ProductYisunFilterHy();
            productYisunFilterHy.setFactoryNum(ExcelUtil.getCellValue(sheet.getRow(i).getCell(8)));
            //图片处理
            String pic = ExcelUtil.getCellValue(sheet.getRow(i).getCell(9));
            String path = "";
            try{
                URL url = new URL(pic);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");
                //超时响应时间为5秒
                conn.setConnectTimeout(5 * 1000);
                //通过输入流获取图片数据
                InputStream inStream = conn.getInputStream();
                //得到图片的二进制数据，以二进制封装得到数据，具有通用性
                byte[] data = readInputStream(inStream);
                InputStream in = new ByteArrayInputStream(data);
                MultipartFile multipartFile = new MockMultipartFile("a.jpg","a.jpg",ContentType.APPLICATION_OCTET_STREAM.toString(),in);
                path =  aliOssUploadServer.fileOssUpload(multipartFile);
                System.out.println("图片地址为："+path);
            }catch (Exception e){
                log.info("处理图片报错 行数："+i);
            }
            productYisunFilterHy.setCarousel(path);
            productYisunFilterHy.setAnotherName(ExcelUtil.getCellValue(sheet.getRow(i).getCell(7)).replace("(0)","").replace("(1)",""));
            productYisunFilterHy.setMarketEnable(0);
            productYisunFilterHyMapper.insertAllColumn(productYisunFilterHy);
        }
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
