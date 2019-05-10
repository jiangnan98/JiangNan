package com.bing.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bing.mapper.ModelCellTuhuMapper;
import com.bing.mapper.ProductTypeMapper;
import com.bing.mapper.ProductYisunCellCopy1Mapper;
import com.bing.middleware.AliOssUploadServer;
import com.bing.model.ModelCellTuhu;
import com.bing.model.ProductYisunCellCopy1;
import com.bing.util.ExcelUtil;
import com.bing.util.LogUtils;
import org.apache.http.entity.ContentType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/5/10
 * Time: 9:21
 * To change this template use File | Setting | File Template.
 **/
@RestController
@RequestMapping("/Cell/")
public class CellController {

    Logger log = LogUtils.getBussinessLogger();
    @Autowired
    ProductTypeMapper productTypeMapper;
    @Autowired
    ModelCellTuhuMapper modelCellTuhuMapper;
    @Autowired
    AliOssUploadServer aliOssUploadServer;
    @Autowired
    ProductYisunCellCopy1Mapper productYisunCellCopy1Mapper;


    @PostMapping("cellData")
    public void testVerify() throws Exception{
        File file = new File("D:\\aaa\\data\\图片\\蓄电池.xlsx");
        InputStream is = new FileInputStream(file);
        Workbook wb = new XSSFWorkbook(is);
        Sheet sheet = wb.getSheetAt(0);
        is.close();
        //获取行数
        int rowLens = sheet.getLastRowNum();
        log.info("行数："+rowLens);
        for(int i = 1;i<rowLens;i++){
            String model = ExcelUtil.getCellValue(sheet.getRow(i).getCell(1));
            String applyModel = ExcelUtil.getCellValue(sheet.getRow(i).getCell(2)).replaceAll("\uD83D\uDC4D","");
            String specNmae = applyModel.substring(0,applyModel.indexOf("/"));
            applyModel = applyModel.replaceAll(specNmae+"/","");
            String specM = ExcelUtil.getCellValue(sheet.getRow(i).getCell(4));
            String factoryNum = ExcelUtil.getCellValue(sheet.getRow(i).getCell(7));
            String brand = ExcelUtil.getCellValue(sheet.getRow(i).getCell(5));
            String dis = ExcelUtil.getCellValue(sheet.getRow(i).getCell(10));
            String year = ExcelUtil.getCellValue(sheet.getRow(i).getCell(11));
            System.out.println("111");
            ModelCellTuhu modelCellTuhu = new ModelCellTuhu();
            modelCellTuhu.setF(model);
            List<ModelCellTuhu> modelCellTuhus = modelCellTuhuMapper.selectList(new EntityWrapper<>(modelCellTuhu));
            StringBuffer stringBuffer = new StringBuffer();
            modelCellTuhus.forEach(mode->{
                stringBuffer.append(mode.getId()).append(",");
            });
            ProductYisunCellCopy1 productYisunCellCopy1 = new ProductYisunCellCopy1();
            productYisunCellCopy1.setFactoryNum(factoryNum);
            ProductYisunCellCopy1 ps = productYisunCellCopy1Mapper.selectOne(productYisunCellCopy1);
            if(ps != null){
                stringBuffer.append(ps.getModel()).append(",");
                stringBuffer.append(stringBuffer.toString()).append(",");
                productYisunCellCopy1.setModel(stringBuffer.toString());
                productYisunCellCopy1Mapper.editModel(ps);
                continue;
            }
            String pic = "https://img2.tuhu.org"+ExcelUtil.getCellValue(sheet.getRow(i).getCell(3));
            //处理图片
            String p = "";
            try{
                p = fileUpload(pic);
            }catch (Exception e){
                log.info("处理图片出错行数："+i+e);
                log.info(e.getMessage());
            }
            productYisunCellCopy1.setSpecM(specM);
            productYisunCellCopy1.setAnotherName("蓄电池");
            if(factoryNum.contains("AGM")){
                //启停
                productYisunCellCopy1.setClassifyId("201");
            }else{
                //免维护
                productYisunCellCopy1.setClassifyId("202");
            }
            productYisunCellCopy1.setModel(stringBuffer.toString());
            productYisunCellCopy1.setAnotherName("蓄电池");
            productYisunCellCopy1.setCarousel(p);
            productYisunCellCopy1.setApplyModel(applyModel);
            productYisunCellCopy1.setBrand(brand);
            productYisunCellCopy1.setYear(year);
            productYisunCellCopy1.setDis(dis);
            productYisunCellCopy1.setSpecName(specNmae);
            productYisunCellCopy1Mapper.insert(productYisunCellCopy1);
        }
    }

    public String fileUpload(String pic) throws Exception{
        String p = "";
        URL url = new URL(pic);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        InputStream inStream = conn.getInputStream();
        byte[] data = readInputStream(inStream);
        InputStream in = new ByteArrayInputStream(data);
        MultipartFile multipartFile = new MockMultipartFile("a.jpg","a.jpg",ContentType.APPLICATION_OCTET_STREAM.toString(),in);
        p =  aliOssUploadServer.fileOssUpload(multipartFile);
        System.out.println("图片地址为："+p);
        return p;
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
