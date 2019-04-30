package com.bing.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bing.anno.Auth;
import com.bing.mapper.*;
import com.bing.middleware.AliOssUploadServer;
import com.bing.model.*;
import com.bing.pack.LizardSystemCode;
import com.bing.pack.RequestParam;
import com.bing.pack.ResponseResult;
import com.bing.req.vo.TestReqVo;
import com.bing.res.vo.TestResVo;
import com.bing.service.ExcelService;
import com.bing.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.http.entity.ContentType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
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
import java.util.List;
import java.util.Map;

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
    @Autowired
    ProductYisunFilterManMlMapper productYisunFilterManMlMapper;
    @Autowired
    ProductTypeMapper productTypeMapper;
    @Autowired
    ModelFilterHyMapper modelFilterHyMapper;
    @Autowired
    ProductYisunIgnitionMapper productYisunIgnitionMapper;
    @Autowired
    ModelChassisTnkMapper modelChassisTnkMapper;
    @Autowired
    ProductYisunChassisMapper productYisunChassisMapper;
    @Autowired
    ModelBrakMofineMapper modelBrakMofineMapper;

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


    @PostMapping("manFilter")
    public void manFilter() throws Exception{
        String path = "D:\\\\aaa\\\\data\\\\hgst-man-ma.xls";
        File file = new File(path);
        boolean isExcel2003 = path.toLowerCase().endsWith("xls")?true:false;
        InputStream is = new FileInputStream(file);
        Workbook wb;
        if(isExcel2003){
            wb = new HSSFWorkbook(is);
        }else{
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        is.close();
        //获取行数
        int rowLens = sheet.getLastRowNum();
        log.info("行数："+rowLens);
        //获取车型数据
        for(int i = 2;i<rowLens;i++) {
           try{
               String strHgst = ExcelUtil.getCellValue(sheet.getRow(i).getCell(3));
               String[] str3 = strHgst.split("\n");
               for (String s : str3) {
                   System.out.println("汉格斯特："+s.replaceAll(" ",""));
                   String strArr = ExcelUtil.getCellValue(sheet.getRow(i).getCell(11));
                   String[] strs = strArr.split("\n");
                   ProductYisunFilterHy productYisunFilterHy = new ProductYisunFilterHy();
                   if(StringUtils.isBlank(s) || s.equals("-")){
                       continue;
                   }
                   productYisunFilterHy.setFactoryNum(s);
                   productYisunFilterHy.setSpecName("汉格斯特");
                   EntityWrapper<ProductYisunFilterHy> productYisunFilterHyEntityWrapper = new EntityWrapper<>();
                   productYisunFilterHyEntityWrapper.setEntity(productYisunFilterHy);
                   List<ProductYisunFilterHy> productYisunFilterHyList = productYisunFilterHyMapper.selectList(productYisunFilterHyEntityWrapper);
                   for (String s1 : strs) {
                       String factory = s1.replaceAll(" ","");
                       System.out.println("曼牌："+factory);
                       //曼牌数据
                       productYisunFilterHyList.forEach(data->{
                           ProductYisunFilterManMl productYisunFilterM = new ProductYisunFilterManMl();
                           BeanUtils.copyProperties(productYisunFilterHyList.get(0), productYisunFilterM);
                           productYisunFilterM.setProductId(null);
                           productYisunFilterM.setFactoryNum(factory);
                           productYisunFilterM.setOurcPrice(0);
                           productYisunFilterM.setSpecName("曼牌");
                           productYisunFilterM.setPrice(0);
                           productYisunFilterM.setMarketEnable(0);
                           productYisunFilterManMlMapper.insert(productYisunFilterM);
                       });
                   }
                   String strArr2 = ExcelUtil.getCellValue(sheet.getRow(i).getCell(12));
                   String[] strs2 = strArr2.split("\n");
                   for (String s1 : strs2) {
                       String factory = s1.replaceAll(" ","");
                       System.out.println("马勒："+factory);
                       //马勒数据
                       productYisunFilterHyList.forEach(data->{
                           ProductYisunFilterManMl productYisunFilterM = new ProductYisunFilterManMl();
                           BeanUtils.copyProperties(productYisunFilterHyList.get(0), productYisunFilterM);
                           productYisunFilterM.setProductId(null);
                           productYisunFilterM.setFactoryNum(factory);
                           productYisunFilterM.setSpecName("马勒");
                           productYisunFilterM.setOurcPrice(0);
                           productYisunFilterM.setPrice(0);
                           productYisunFilterM.setMarketEnable(0);
                           productYisunFilterManMlMapper.insert(productYisunFilterM);
                       });
                   }
               }
           }catch (Exception e){
               System.out.println("错误行数："+i);
               e.getStackTrace();
           }
        }
    }

    @PostMapping("hyData")
    public void hyData() throws Exception{
        String path = "D:\\\\aaa\\\\data\\\\海业滤清.xlsx";
        File file = new File(path);
        boolean isExcel2003 = path.toLowerCase().endsWith("xls")?true:false;
        InputStream is = new FileInputStream(file);
        Workbook wb;
        if(isExcel2003){
            wb = new HSSFWorkbook(is);
        }else{
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(1);
        is.close();
        //获取行数
        int rowLens = sheet.getLastRowNum();
        log.info("行数："+rowLens);
        //获取车型数据
        for(int i = 0;i<rowLens;i++) {
            ProductYisunFilterHy productYisunFilterHy = new ProductYisunFilterHy();
            ModelFilterHy modelFilterHy = new ModelFilterHy();
            modelFilterHy.setLetter(ExcelUtil.getCellValue(sheet.getRow(i).getCell(0)));
            modelFilterHy.setBrand(ExcelUtil.getCellValue(sheet.getRow(i).getCell(1)));
            modelFilterHy.setModel(ExcelUtil.getCellValue(sheet.getRow(i).getCell(2)));
            modelFilterHy.setCategory(ExcelUtil.getCellValue(sheet.getRow(i).getCell(3)));
            modelFilterHy.setSwept(ExcelUtil.getCellValue(sheet.getRow(i).getCell(4)));
            modelFilterHy.setSeries(ExcelUtil.getCellValue(sheet.getRow(i).getCell(5)));
            modelFilterHy.setMotor(ExcelUtil.getCellValue(sheet.getRow(i).getCell(6)));
            EntityWrapper<ModelFilterHy> wrapper = new EntityWrapper<>();
            wrapper.setEntity(modelFilterHy);
            List<ModelFilterHy> modelFilterHIES = modelFilterHyMapper.selectList(wrapper);
            if(modelFilterHIES != null && modelFilterHIES.size()!= 0){
                productYisunFilterHy.setModel(modelFilterHIES.get(0).getId());
            }
            //分类
            String auName = ExcelUtil.getCellValue(sheet.getRow(i).getCell(7)).replaceAll("(0)","").replaceAll("(1)","").replaceAll("(2)","").replaceAll("\\(|\\)","").replaceAll("（）","");
            ProductType productType = new ProductType();
            productType.setName(auName);
            EntityWrapper<ProductType> wrapper1 = new EntityWrapper<>();
            wrapper1.setEntity(productType);
            List<ProductType> productTypes = productTypeMapper.selectList(wrapper1);
            if(productTypes!=null && productTypes.size() != 0){
                productYisunFilterHy.setClassifyId(productTypes.get(0).getSId().toString());
            }
            productYisunFilterHy.setAnotherName(auName);
            //工厂号
            productYisunFilterHy.setFactoryNum(ExcelUtil.getCellValue(sheet.getRow(i).getCell(8)));
            System.out.println("工厂号："+ExcelUtil.getCellValue(sheet.getRow(i).getCell(8)));
            //图片
            String p = "";
            String pic = ExcelUtil.getCellValue(sheet.getRow(i).getCell(9));
            try{
                p = fileUpload(pic);
            }catch (Exception e){
                log.info("处理图片报错 行数："+i);
            }
            //图片地址
            productYisunFilterHy.setCarousel(p);
            productYisunFilterHyMapper.insert(productYisunFilterHy);
        }
    }


    @PostMapping("hyPrice")
    public void hyPrice() throws Exception{
        String path = "D:\\\\aaa\\\\data\\\\海业空调滤报价.xls";
        File file = new File(path);
        boolean isExcel2003 = path.toLowerCase().endsWith("xls")?true:false;
        InputStream is = new FileInputStream(file);
        Workbook wb;
        if(isExcel2003){
            wb = new HSSFWorkbook(is);
        }else{
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        is.close();
        //获取行数
        int rowLens = sheet.getLastRowNum();
        log.info("行数："+rowLens);
        for(int i = 1;i<rowLens;i++) {
            ProductYisunFilterHy productYisunFilterHy = new ProductYisunFilterHy();
            //获取工厂号
            productYisunFilterHy.setFactoryNum(ExcelUtil.getCellValue(sheet.getRow(i).getCell(0)));
            if (ExcelUtil.getCellValue(sheet.getRow(i).getCell(5)).equals("0".equals("0"))){
                continue;
            }
            //获取价格
            productYisunFilterHy.setOurcPrice(Integer.valueOf(MoneyUtils.moneyYuanToFen(ExcelUtil.getCellValue(sheet.getRow(i).getCell(5)))));
            productYisunFilterHy.setPrice(Integer.valueOf(MoneyUtils.moneyYuanToFen(ExcelUtil.getCellValue(sheet.getRow(i).getCell(6)))));
            //更新数据
            productYisunFilterHyMapper.editPrice(productYisunFilterHy);
        }

    }

    @PostMapping("mlOurcPrice")
    public void mlOurcPrice() throws Exception{
        String path = "D:\\\\aaa\\\\data\\\\马勒汽配.xls";
        File file = new File(path);
        boolean isExcel2003 = path.toLowerCase().endsWith("xls")?true:false;
        InputStream is = new FileInputStream(file);
        Workbook wb;
        if(isExcel2003){
            wb = new HSSFWorkbook(is);
        }else{
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        is.close();
        //获取行数
        int rowLens = sheet.getLastRowNum();
        log.info("行数："+rowLens);
        for(int i = 2;i<rowLens;i++) {
            try{
                ProductYisunFilterHy productYisunFilterHy = new ProductYisunFilterHy();
                Double obj = new Double(ExcelUtil.getCellValue(sheet.getRow(i).getCell(3)));
                productYisunFilterHy.setOurcPrice(Integer.valueOf(MoneyUtils.moneyYuanToFen(String.valueOf((int)Math.rint(obj)))));

//                productYisunFilterHy.setOurcPrice(Integer.valueOf(MoneyUtils.moneyYuanToFen(ExcelUtil.getCellValue(sheet.getRow(i).getCell(3)))));
                productYisunFilterHy.setFactoryNum(ExcelUtil.getCellValue(sheet.getRow(i).getCell(0)).replaceAll(" ",""));
                if(StringUtils.isBlank(productYisunFilterHy.getFactoryNum()))
                    continue;
                productYisunFilterHyMapper.editOurcPriceMa(productYisunFilterHy);
            }catch (Exception e){
                log.info("错误行数："+i);
                log.info("错误原因："+e.getMessage());
            }

        }

    }
    @PostMapping("mlPrice")
    public void mlPrice() throws Exception{
        String path = "D:\\\\aaa\\\\data\\\\马勒汽修.xls";
        File file = new File(path);
        boolean isExcel2003 = path.toLowerCase().endsWith("xls")?true:false;
        InputStream is = new FileInputStream(file);
        Workbook wb;
        if(isExcel2003){
            wb = new HSSFWorkbook(is);
        }else{
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        is.close();
        //获取行数
        int rowLens = sheet.getLastRowNum();
        log.info("行数："+rowLens);

//        int i = ;
//        System.out.println("输出："+i);
        for(int i = 2;i<rowLens;i++) {
            try{
//                System.out.println("价格1："+ExcelUtil.getCellValue(sheet.getRow(i).getCell(0)));
//                System.out.println("价格2："+ExcelUtil.getCellValue(sheet.getRow(i).getCell(1)));
//                System.out.println("价格3："+ExcelUtil.getCellValue(sheet.getRow(i).getCell(2)));
//                System.out.println("价格4："+MoneyUtils.moneyYuanToFen(String.valueOf(Integer.valueOf(ExcelUtil.getCellValue(sheet.getRow(i).getCell(3))))));
//                System.out.println("价格："+i);
//                return;
                ProductYisunFilterHy productYisunFilterHy = new ProductYisunFilterHy();
                Double obj = new Double(ExcelUtil.getCellValue(sheet.getRow(i).getCell(3)));
                productYisunFilterHy.setPrice(Integer.valueOf(MoneyUtils.moneyYuanToFen(String.valueOf((int)Math.rint(obj)))));
                productYisunFilterHy.setFactoryNum(ExcelUtil.getCellValue(sheet.getRow(i).getCell(0)).replaceAll(" ",""));
                if(StringUtils.isBlank(productYisunFilterHy.getFactoryNum()))
                    continue;
                productYisunFilterHyMapper.editPriceMa(productYisunFilterHy);

            }catch (Exception e){
                log.info("错误行数："+i);
                log.info("错误原因："+e.getMessage());
            }

        }

    }

    @PostMapping("ngkData")
    public void ngkData() throws Exception{
        String path = "D:\\\\aaa\\\\data\\\\NGK火花塞.xlsx";
        File file = new File(path);
        boolean isExcel2003 = path.toLowerCase().endsWith("xls")?true:false;
        InputStream is = new FileInputStream(file);
        Workbook wb;
        if(isExcel2003){
            wb = new HSSFWorkbook(is);
        }else{
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(1);
        is.close();
        //获取行数
        int rowLens = sheet.getLastRowNum();
        log.info("行数："+rowLens);
        for(int i = 0;i<rowLens;i++) {
                ProductYisunIgnition productYisunIgnition = new ProductYisunIgnition();
                productYisunIgnition.setOem(ExcelUtil.getCellValue(sheet.getRow(i).getCell(7)));
                productYisunIgnition.setFactoryNum(ExcelUtil.getCellValue(sheet.getRow(i).getCell(8)));
                productYisunIgnition.setMarketEnable(1);
                productYisunIgnition.setShopId("1118401976018423810");
                /**
                 * 车型处理
                 */
                ModelFilterHy modelFilterHy = new ModelFilterHy();
                modelFilterHy.setBrand(ExcelUtil.getCellValue(sheet.getRow(i).getCell(1)));
                modelFilterHy.setLetter(ExcelUtil.getCellValue(sheet.getRow(i).getCell(0)));
                modelFilterHy.setModel(ExcelUtil.getCellValue(sheet.getRow(i).getCell(2)));
                modelFilterHy.setCategory(ExcelUtil.getCellValue(sheet.getRow(i).getCell(3)));
                modelFilterHy.setSwept(ExcelUtil.getCellValue(sheet.getRow(i).getCell(4)));
                modelFilterHy.setSeries(ExcelUtil.getCellValue(sheet.getRow(i).getCell(5)));
                modelFilterHy.setMotor(ExcelUtil.getCellValue(sheet.getRow(i).getCell(6)));
                EntityWrapper<ModelFilterHy> wrapper = new EntityWrapper<>();
                wrapper.setEntity(modelFilterHy);
                List<ModelFilterHy> modelFilterHIES = modelFilterHyMapper.selectList(wrapper);
                if(modelFilterHIES!=null && modelFilterHIES.size()!=0){
                    StringBuffer stringBuffer = new StringBuffer();
                    modelFilterHIES.forEach(modelF ->{
                        stringBuffer.append(modelF.getId()+",");
                    });
                    productYisunIgnition.setModel(stringBuffer.toString());
                }
                /**
                 * 图片处理
                 */
                String num = ExcelUtil.getCellValue(sheet.getRow(i).getCell(9));
                if(num.equals("none")){
                    num = null;
                }
                try{
                    String fatory = ExcelUtil.getCellValue(sheet.getRow(i).getCell(8));
                    num = num==null?fatory:fatory+"_"+num;
                    num.replaceAll("/","_");
                    File file1 = new File("D:\\aaa\\data\\火花塞图片\\"+num+".jpg");
                    FileInputStream fileInputStream = new FileInputStream(file1);
                    MultipartFile multipartFile = new MockMultipartFile(file1.getName(), file1.getName(),
                            ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
                    String  p =  aliOssUploadServer.fileOssUpload(multipartFile);
                    productYisunIgnition.setCarousel(p);
                }catch (Exception e){
                    log.info("处理图片出错行数："+i+e);
                    log.info(e.getMessage());
                }
                //分类处理
                productYisunIgnition.setClassifyId("701");
                productYisunIgnition.setSpecName("NGK");
                productYisunIgnitionMapper.insert(productYisunIgnition);
        }

    }

    @PostMapping("ngkPrice")
    public void ngkPrice() throws Exception{
        String path = "D:\\\\aaa\\\\data\\\\NGK火花塞报价.xls";
        File file = new File(path);
        boolean isExcel2003 = path.toLowerCase().endsWith("xls")?true:false;
        InputStream is = new FileInputStream(file);
        Workbook wb;
        if(isExcel2003){
            wb = new HSSFWorkbook(is);
        }else{
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        is.close();
        //获取行数
        int rowLens = sheet.getLastRowNum();
        log.info("行数："+rowLens);
        for(int i = 1;i<rowLens;i++) {
            ProductYisunIgnition productYisunIgnition = new ProductYisunIgnition();
            productYisunIgnition.setFactoryNum(ExcelUtil.getCellValue(sheet.getRow(i).getCell(3)));
            productYisunIgnition.setPrice(Integer.valueOf(MoneyUtils.moneyYuanToFen(ExcelUtil.getCellValue(sheet.getRow(i).getCell(7)))));
            productYisunIgnition.setOurcPrice(Integer.valueOf(MoneyUtils.moneyYuanToFen(ExcelUtil.getCellValue(sheet.getRow(i).getCell(6)))));
            productYisunIgnitionMapper.editPrice(productYisunIgnition);
        }
    }

    @PostMapping("tnkModel")
    public void tnkModel() throws Exception{
        String path = "D:\\\\aaa\\\\data\\\\天纳克 减震器.xlsx";
        File file = new File(path);
        boolean isExcel2003 = path.toLowerCase().endsWith("xls")?true:false;
        InputStream is = new FileInputStream(file);
        Workbook wb;
        if(isExcel2003){
            wb = new HSSFWorkbook(is);
        }else{
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(2);
        is.close();
        //获取行数
        int rowLens = sheet.getLastRowNum();
        log.info("行数："+rowLens);

        for(int i = 1;i<rowLens;i++) {
            try{
                ModelChassisTnk modelChassisTnk = new ModelChassisTnk();
                modelChassisTnk.setBrand(ExcelUtil.getCellValue(sheet.getRow(i).getCell(0)));
                modelChassisTnk.setModel(ExcelUtil.getCellValue(sheet.getRow(i).getCell(1)));
                modelChassisTnk.setCategory(ExcelUtil.getCellValue(sheet.getRow(i).getCell(2)));
//                modelChassisTnk.setYear1(ExcelUtil.getCellValue(sheet.getRow(i).getCell(3)));
//                modelChassisTnk.setYear2(ExcelUtil.getCellValue(sheet.getRow(i).getCell(4)));
//                modelChassisTnk.setYear3(ExcelUtil.getCellValue(sheet.getRow(i).getCell(5)));
//                modelChassisTnk.setSwept(ExcelUtil.getCellValue(sheet.getRow(i).getCell(6)));
//                modelChassisTnk.setMotor1(ExcelUtil.getCellValue(sheet.getRow(i).getCell(7)));
                modelChassisTnk.setMotor(ExcelUtil.getCellValue(sheet.getRow(i).getCell(8)));
                EntityWrapper<ModelChassisTnk> modelChassisTnkEntityWrapper = new EntityWrapper<>();
                modelChassisTnkEntityWrapper.setEntity(modelChassisTnk);
                List<ModelChassisTnk> tnk = modelChassisTnkMapper.selectList(modelChassisTnkEntityWrapper);
                if(tnk == null || tnk.size() == 0){
                    continue;
                }
                modelChassisTnk.setId(tnk.get(0).getId());
//                modelChassisTnk.setProduct(ExcelUtil.getCellValue(sheet.getRow(i).getCell(9)));
                modelChassisTnkMapper.editProduct(modelChassisTnk);
            }catch (Exception e){
                log.info("错误行数："+i);
                log.info("错误信息："+e.getMessage());
            }
        }
    }

    @PostMapping("tnkData")
    public void tnkData() throws Exception{
        String path = "D:\\\\aaa\\\\data\\\\天纳克 减震器.xlsx";
        File file = new File(path);
        boolean isExcel2003 = path.toLowerCase().endsWith("xls")?true:false;
        InputStream is = new FileInputStream(file);
        Workbook wb;
        if(isExcel2003){
            wb = new HSSFWorkbook(is);
        }else{
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(3);
        is.close();
        //获取行数
        int rowLens = sheet.getLastRowNum();
        log.info("行数："+rowLens);
        for(int i = 1;i<rowLens;i++) {
            try{
                ModelChassisTnk modelChassisTnk = new ModelChassisTnk();
//                modelChassisTnk.setProduct(ExcelUtil.getCellValue(sheet.getRow(i).getCell(0)));
                EntityWrapper<ModelChassisTnk> modelChassisTnkEntityWrapper = new EntityWrapper<>();
                modelChassisTnkEntityWrapper.setEntity(modelChassisTnk);
                List<ModelChassisTnk> tnk = modelChassisTnkMapper.selectList(modelChassisTnkEntityWrapper);
                if(tnk == null || tnk.size() == 0){
                    continue;
                }
                /**
                 * 处理类型只要减震器
                 */
                String type = ExcelUtil.getCellValue(sheet.getRow(i).getCell(2));
                if(!type.equals("减振器")){
                    continue;
                }
                String around = ExcelUtil.getCellValue(sheet.getRow(i).getCell(5));
                StringBuffer stringBuffer = new StringBuffer();
                if(around.indexOf("前")!=-1){
                    type = "前减震器";
                }
                if(around.indexOf("后")!=-1){
                    type = "后减震器";
                }
                ProductType productType = new ProductType();
                productType.setName(type);
                EntityWrapper<ProductType> wrapper = new EntityWrapper<>();
                wrapper.setEntity(productType);
                List<ProductType> productTypes = productTypeMapper.selectList(wrapper);
                ProductYisunChassis productYisunChassis = new ProductYisunChassis();
                productYisunChassis.setAnotherName(type);
                productYisunChassis.setSpecName("蒙诺");
                productYisunChassis.setClassifyId(String.valueOf(productTypes.get(0).getSId()));
                productYisunChassis.setModel(String.valueOf(tnk.get(0).getId()));
                productYisunChassis.setFactoryNum(ExcelUtil.getCellValue(sheet.getRow(i).getCell(4)));
                productYisunChassisMapper.insert(productYisunChassis);
            }catch (Exception e){
                log.info("错误行数："+i);
                log.info("错误信息："+e.getMessage());
            }
        }
    }
    @PostMapping("tnkPrice")
    public void tnkPrice() throws Exception{
        String path = "D:\\\\aaa\\\\data\\\\2019年天纳克价格表20190405010.xlsx";
        File file = new File(path);
        boolean isExcel2003 = path.toLowerCase().endsWith("xls")?true:false;
        InputStream is = new FileInputStream(file);
        Workbook wb;
        if(isExcel2003){
            wb = new HSSFWorkbook(is);
        }else{
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        is.close();
        //获取行数
        int rowLens = sheet.getLastRowNum();
        log.info("行数："+rowLens);
        for(int i = 1;i<rowLens;i++) {
            try{
                ProductYisunChassis productYisunChassis = new ProductYisunChassis();
                productYisunChassis.setFactoryNum(ExcelUtil.getCellValue(sheet.getRow(i).getCell(1)));
                productYisunChassis.setUnit(ExcelUtil.getCellValue(sheet.getRow(i).getCell(2)));
                productYisunChassis.setPrice(Integer.valueOf(MoneyUtils.moneyYuanToFen(ExcelUtil.getCellValue(sheet.getRow(i).getCell(4)))));
                productYisunChassis.setOurcPrice(Integer.valueOf(MoneyUtils.moneyYuanToFen(ExcelUtil.getCellValue(sheet.getRow(i).getCell(3)))));
                productYisunChassisMapper.editPrice(productYisunChassis);
            }catch (Exception e){
                log.info("错误行数："+i);
                log.info("错误信息："+e.getMessage());
            }
        }
    }

    @PostMapping("tnkLetter")
    public void tnkLetter() throws Exception{
        ModelChassisTnk modelChassisTnk = new ModelChassisTnk();
        EntityWrapper<ModelChassisTnk> wrapper = new EntityWrapper<>();
        wrapper.setEntity(modelChassisTnk);
        List<ModelChassisTnk> chassisTnks = modelChassisTnkMapper.selectList(wrapper);
        chassisTnks.forEach(chassisTnk->{
           chassisTnk.setLetter(ChineseUtils.getPinYinHeadChar(chassisTnk.getBrand().substring(0,1)));
           modelChassisTnkMapper.editLetter(chassisTnk);
           return;
        });
    }



    @PostMapping("mofineLetter")
    public void mofineLetter() throws Exception{
        ModelBrakMofine modelBrakMofine = new ModelBrakMofine();
        EntityWrapper<ModelBrakMofine> wrapper = new EntityWrapper<>();
        wrapper.setEntity(modelBrakMofine);
        List<ModelBrakMofine> modelBrakMofines = modelBrakMofineMapper.selectList(wrapper);
        modelBrakMofines.forEach(modelBrakMofine1 -> {
            modelBrakMofine1.setLetter(ChineseUtils.getPinYinHeadChar(modelBrakMofine1.getBrandName()).substring(0,1));
            modelBrakMofineMapper.updateById(modelBrakMofine1);
        });
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

    public static  void main(String[] args){
        if(true){
            System.out.println("12");
        }else{
            System.out.println("22");
        }
        String str = "{'直径': '282', '节圆直径': '114.3', '中心孔直径': '60', '制动盘厚度': '24', '最小厚度': '22', '总高': '42.5', '制动盘类型': '通风盘'}";
        Map maps = (Map) JSON.parse(str);
        maps.forEach((k, v) -> System.out.println("key:value = " + k + ":" + v));
//        String[] strs = str.split("],");
//        List<String> strings = new ArrayList<>();
//        List<Pan> pans = new ArrayList<>();
//        Pan pan = new Pan();
//        for (String s : strs) {
//            strings.add(s.replaceAll("\\[",""));
//        }
//        strings.forEach(s->{
//            String[] sts = s.split(",");
//            for (int i =0;i<sts.length;i++) {
//                sts[i] =sts[i].replaceAll("\\]","").replaceAll("'","").replaceAll(" ","");
//                if(i==0){
//                    pan.setName(sts[i]);
//                    pan.setFactory(null);
//                }else{
//                    List<String> a = pan.getFactory();
//                    if(a==null)
//                        a=new ArrayList<>();
//                    a.add(sts[i]);
//                   pan.setFactory(a);
//                }
//            }
//            pans.add(pan);
//        });
        System.out.println("1");
//        List<Pan> pans = new ArrayList<>();
//        Pan pan = new Pan();
//        for (String s : strs) {
//            s = s.replaceAll("\\[","").replaceAll("\\]","").replaceAll("'","").replaceAll(" ","");
//            System.out.println(s);
//            if(s.equals("博世") || s.equals("TRW") || s.equals("泰明顿") || s.equals("菲罗多") || s.equals("AIMCO")){
//                if(StringUtils.isNotBlank(pan.getName())){
//                    pans.add(pan);
//                    pan = new Pan();
//                }
//                pan.setName(s);
//            }else{
//                List<String> as = pan.getFactory();
//                if(as == null)
//                    as = new ArrayList<>();
//                as.add(s);
//                pan.setFactory(as);
//            }
//        }
//        System.out.println("返回数据："+pans.toString());
//        pans.forEach(pa->{
//            System.out.println(pa.getFactory());
//            System.out.println(pa.getFactory().size());
//            pa.getFactory().forEach(st->{
//                System.out.println(st);
//            });
//        });
    }

}
