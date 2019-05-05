package com.bing.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bing.mapper.ModelBrakShengdiMapper;
import com.bing.mapper.ProductTypeMapper;
import com.bing.mapper.ProductYisunBrakMapper;
import com.bing.middleware.AliOssUploadServer;
import com.bing.model.ModelBrakMofine;
import com.bing.model.ModelBrakShengdi;
import com.bing.model.ProductType;
import com.bing.model.ProductYisunBrak;
import com.bing.req.vo.Pan;
import com.bing.util.ExcelUtil;
import com.bing.util.LogUtils;
import com.bing.util.MoneyUtils;
import com.google.gson.JsonArray;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/4/28
 * Time: 10:12
 * To change this template use File | Setting | File Template.
 **/
@RestController
@RequestMapping("/BrakExcel/")
public class BrakExcelController {
    Logger log = LogUtils.getBussinessLogger();
    @Autowired
    ProductTypeMapper productTypeMapper;
    @Autowired
    AliOssUploadServer aliOssUploadServer;
    @Autowired
    ProductYisunBrakMapper productYisunBrakMapper;
    @Autowired
    ModelBrakShengdiMapper modelBrakShengdiMapper;

    @PostMapping("moFineData")
    public void findList() throws Exception{
        File file = new File("D:\\aaa\\data\\美丰刹车片.xlsx");
        InputStream is = new FileInputStream(file);
        Workbook wb = new XSSFWorkbook(is);
        Sheet sheet = wb.getSheetAt(8);
        is.close();
        //获取行数
        int rowLens = sheet.getLastRowNum();
        log.info("行数："+rowLens);
        //获取车型数据
        List<ProductYisunBrak> productYisunBrakTs = new ArrayList<>();
        List<ProductYisunBrak> productYisunBrakLs = new ArrayList<>();
        ProductYisunBrak productYisunBrakM = new ProductYisunBrak();//美丰
        ProductYisunBrak productYisunBrakF = new ProductYisunBrak();//FMSI
        ProductYisunBrak productYisunBrak;
        String modelId = "";
        String Oem = "";
        String specM = "";
        String authorName = "";
        for(int i = 0;i<rowLens;i++){
            String isFirst = ExcelUtil.getCellValue(sheet.getRow(i).getCell(10));
            if(isFirst.equals("产品尺寸(mm)")){//证明是第一个进行T码的存储美丰商品
                Oem = "";
                if(productYisunBrakTs!=null && productYisunBrakTs.size()!=0){//第一次进行循环或者进入下一个商品循环将产品进行存储
                    final String m = modelId;
                    final String p = productYisunBrakF.getCarousel();
                    final String spec = specM;
                    final String author = authorName;
                    productYisunBrakF.setSpecM(specM);
                    productYisunBrakM.setSpecM(specM);
                    productYisunBrakF.setAnotherName(authorName);
                    productYisunBrakM.setAnotherName(authorName);
                    productYisunBrakF.setClassifyId("508");
                    productYisunBrakM.setClassifyId("508");
                    productYisunBrak = productYisunBrakMapper.findOne(productYisunBrakF);
                    if(productYisunBrak!=null){
                        StringBuffer stringBuffer = new StringBuffer(productYisunBrak.getModel()==null?"":productYisunBrak.getModel());
                        productYisunBrak.setModel(String.valueOf(stringBuffer.append(",").append(modelId)));
                        productYisunBrakMapper.editModel(productYisunBrak);
                    }else{
                        productYisunBrakF.setCarousel(p);
                        System.out.println(productYisunBrakF.getProductId());
                        productYisunBrakMapper.insert(productYisunBrakF);
                    }
                    productYisunBrakLs.forEach(productYisunBrakL ->{
                        productYisunBrakL.setSpecM(spec);
                        productYisunBrakL.setAnotherName(author);
                        productYisunBrakL.setClassifyId("508");
                        ProductYisunBrak Brak = productYisunBrakMapper.findOne(productYisunBrakL);
                        if(Brak!=null){
                            StringBuffer stringBuffer = new StringBuffer(Brak.getModel()==null?"":Brak.getModel());
                            Brak.setModel(String.valueOf(stringBuffer.append(",").append(m)));
                            productYisunBrakMapper.editModel(Brak);
                        }else{
                            productYisunBrakL.setCarousel(p);
                            productYisunBrakMapper.insert(productYisunBrakL);
                        }
                    });
                    productYisunBrak = productYisunBrakMapper.findOne(productYisunBrakM);
                    if(productYisunBrak!=null){
                        StringBuffer stringBuffer = new StringBuffer(productYisunBrak.getModel()==null?"":productYisunBrak.getModel());
                        productYisunBrak.setModel(String.valueOf(stringBuffer.append(",").append(modelId)));
                        productYisunBrakMapper.editModel(productYisunBrak);
                    }else{
                        productYisunBrakM.setCarousel(p);
                        productYisunBrakMapper.insert(productYisunBrakM);
                    }
                    productYisunBrakTs.forEach(productYisunBrakT->{
                        productYisunBrakT.setSpecM(spec);
                        productYisunBrakT.setAnotherName(author);
                        productYisunBrakT.setClassifyId("508");
                        ProductYisunBrak Brak = productYisunBrakMapper.findOne(productYisunBrakT);
                        if(Brak!=null){
                            StringBuffer stringBuffer = new StringBuffer(Brak.getModel()==null?"":Brak.getModel());
                            Brak.setModel(String.valueOf(stringBuffer.append(",").append(m)));
                            Brak.setCarousel(p);
                            productYisunBrakMapper.editModel(Brak);
                        }else{
                            productYisunBrakT.setCarousel(p);
                            productYisunBrakMapper.insert(productYisunBrakT);
                        }
                    });
                    productYisunBrakF = new ProductYisunBrak();
                    productYisunBrakM = new ProductYisunBrak();
                    productYisunBrakLs = new ArrayList<>();
                    productYisunBrakTs = new ArrayList<>();
                }
                authorName = ExcelUtil.getCellValue(sheet.getRow(i).getCell(6));
                specM = ExcelUtil.getCellValue(sheet.getRow(i).getCell(13));
                ModelBrakMofine modelBrakMofine = new ModelBrakMofine();
                modelBrakMofine.setBrand(ExcelUtil.getCellValue(sheet.getRow(i).getCell(0)));
                modelBrakMofine.setModel(ExcelUtil.getCellValue(sheet.getRow(i).getCell(1)));
                modelBrakMofine.setCategory(ExcelUtil.getCellValue(sheet.getRow(i).getCell(2)));
                modelBrakMofine.setDis(ExcelUtil.getCellValue(sheet.getRow(i).getCell(3)));
                modelBrakMofine.setYear(ExcelUtil.getCellValue(sheet.getRow(i).getCell(4)));
                EntityWrapper<ModelBrakMofine> wrapper = new EntityWrapper<>();
                wrapper.setEntity(modelBrakMofine);
                List<ModelBrakMofine> modelBrakMofines = modelBrakMofine.selectList(wrapper);
                modelId = String.valueOf(modelBrakMofines.get(0).getId());
                productYisunBrakM.setFactoryNum(ExcelUtil.getCellValue(sheet.getRow(i).getCell(8)));
                productYisunBrakM.setSpecName("美丰");
            }
            if(isFirst.equals("TRW码")){//进行TRW品牌商品的存储
                String str = ExcelUtil.getCellValue(sheet.getRow(i).getCell(13));
                String[] strings = str.split("/");
                for (String s : strings) {
                    ProductYisunBrak productYisunBrak1 = new ProductYisunBrak();
                    productYisunBrak1.setMarketEnable(1);
                    productYisunBrak1.setSpecName("TRW");
                    productYisunBrak1.setFactoryNum(s);
                    productYisunBrakTs.add(productYisunBrak1);
                }
            }
            if(isFirst.equals("FMSI码")){//进行马克波罗的商品存储
                productYisunBrakF.setSpecName("FMSI");
                productYisunBrakF.setMarketEnable(0);
                String str = ExcelUtil.getCellValue(sheet.getRow(i).getCell(13));
                Integer s = str.indexOf("-")==-1?str.length():str.indexOf("-");
                System.out.println(s);
                str = str.substring(0,s);
                productYisunBrakF.setFactoryNum(str);
            }
            if(isFirst.equals("菲罗多码")){//进行菲罗多商品存储
                String str = ExcelUtil.getCellValue(sheet.getRow(i).getCell(13));
                String[] strings = str.split("/");
                for (String s : strings) {
                    ProductYisunBrak productYisunBrak1 = new ProductYisunBrak();
                    productYisunBrak1.setMarketEnable(1);
                    productYisunBrak1.setSpecName("菲罗多");
                    productYisunBrak1.setFactoryNum(s);
                    productYisunBrakLs.add(productYisunBrak1);
                }
            }
            if(isFirst.equals("产品图片")){
                String pic = "https://sopei001-1251517753.cosgz.myqcloud.com/" + ExcelUtil.getCellValue(sheet.getRow(i).getCell(13));
                String p = "";
                try{
                    p = fileUpload(pic);
                    productYisunBrakF.setCarousel(p);
                    productYisunBrakM.setCarousel(p);
                }catch (Exception e){
                    log.info("处理图片出错行数："+i+e);
                    log.info(e.getMessage());
                }
            }
            if(isFirst.contains("OE")){
                StringBuffer stringBuffer = new StringBuffer(ExcelUtil.getCellValue(sheet.getRow(i).getCell(13)));
                stringBuffer.append(",").append(Oem);
                Oem = stringBuffer.toString();
            }
        }
    }

    @PostMapping("TRWPrice")
    public void TRWPrice() throws Exception{
        File file = new File("D:\\aaa\\data\\TRW报价(1).xlsx");
        InputStream is = new FileInputStream(file);
        Workbook wb = new XSSFWorkbook(is);
        Sheet sheet = wb.getSheetAt(3);
        is.close();
        //获取行数
        int rowLens = sheet.getLastRowNum();
        log.info("行数："+rowLens);
        //获取车型数据
        for(int i = 1;i<rowLens;i++){
            try{
                ProductYisunBrak productYisunBrak = new ProductYisunBrak();
                productYisunBrak.setFactoryNum(ExcelUtil.getCellValue(sheet.getRow(i).getCell(1)));
                productYisunBrak.setOurcPrice(Integer.valueOf(MoneyUtils.moneyYuanToFen(ExcelUtil.getCellValue(sheet.getRow(i).getCell(2)))));
                productYisunBrak.setPrice(Integer.valueOf(MoneyUtils.moneyYuanToFen(ExcelUtil.getCellValue(sheet.getRow(i).getCell(3)))));
                productYisunBrak.setRemark(ExcelUtil.getCellValue(sheet.getRow(i).getCell(7)));
                productYisunBrak.setApplyModel(ExcelUtil.getCellValue(sheet.getRow(i).getCell(0)));
                productYisunBrak.setSpecName("TRW");
                productYisunBrakMapper.editBrak(productYisunBrak);
            }catch (Exception e){
                log.info("错误："+e.getMessage());
            }
        }
    }
    @PostMapping("TRWLine")
    public void TRWLine() throws Exception{
        File file = new File("D:\\aaa\\data\\TRWline.xlsx");
        InputStream is = new FileInputStream(file);
        Workbook wb = new XSSFWorkbook(is);
        Sheet sheet = wb.getSheetAt(0);
        is.close();
        //获取行数
        int rowLens = sheet.getLastRowNum();
        log.info("行数："+rowLens);
        //获取车型数据
        for(int i = 1;i<rowLens;i++){
            String testStr = ExcelUtil.getCellValue(sheet.getRow(i).getCell(0));
            ProductYisunBrak productYisunBrak = new ProductYisunBrak();
            productYisunBrak.setFactoryNum(ExcelUtil.getCellValue(sheet.getRow(i).getCell(2)));
            EntityWrapper<ProductYisunBrak> wrapper = new EntityWrapper<>();
            wrapper.setEntity(productYisunBrak);
            List<ProductYisunBrak> productYisunBraks = productYisunBrakMapper.selectList(wrapper);
            if(productYisunBraks == null || productYisunBraks.size()==0){
                continue;
            }
            productYisunBrak = productYisunBraks.get(0);
            productYisunBrak.setProductId(null);
            productYisunBrak.setFactoryNum(ExcelUtil.getCellValue(sheet.getRow(i).getCell(1)));
            productYisunBrak.setSpecM(null);
            productYisunBrak.setCarousel(null);
            productYisunBrak.setRemark(null);
            productYisunBrak.setAnotherName(ExcelUtil.getCellValue(sheet.getRow(i).getCell(0)));
            productYisunBrak.setApplyModel(null);
            productYisunBrak.setUnit(ExcelUtil.getCellValue(sheet.getRow(i).getCell(3)));
            try{
                productYisunBrak.setPrice(Integer.valueOf(ExcelUtil.getCellValue(sheet.getRow(i).getCell(5))));
                productYisunBrak.setOurcPrice(Integer.valueOf(ExcelUtil.getCellValue(sheet.getRow(i).getCell(4))));
            }catch (Exception e){
                continue;
            }
            productYisunBrak.setClassifyId("511");
            ProductYisunBrak productYisunBrak1 = productYisunBrakMapper.selectOne(productYisunBrak);
            if(productYisunBrak1!=null){
                continue;
            }
            productYisunBrakMapper.insert(productYisunBrak);
        }
    }


    @PostMapping("SDData")
    public void SDData() throws Exception {
        File file = new File("D:\\aaa\\data\\胜地刹车盘.xlsx");
        InputStream is = new FileInputStream(file);
        Workbook wb = new XSSFWorkbook(is);
        Sheet sheet = wb.getSheetAt(7);
        is.close();
        //获取行数
        int rowLens = sheet.getLastRowNum();
        log.info("行数：" + rowLens);
        //获取车型数据
        for (int j = 0; j < rowLens; j++) {
            ProductYisunBrak productYisunBrak = new ProductYisunBrak();
            String str = ExcelUtil.getCellValue(sheet.getRow(j).getCell(5));
            String[] strs = str.split("],");
            List<String> strings = new ArrayList<>();
            List<Pan> pans = new ArrayList<>();//品牌与工厂号  一个工厂号代表一个商品
            Pan pan = new Pan();
            for (String s : strs) {
                strings.add(s.replaceAll("\\[",""));
            }
            strings.forEach(s->{
                String[] sts = s.split(",");
                for (int i =0;i<sts.length;i++) {
                    sts[i] =sts[i].replaceAll("\\]","").replaceAll("'","").replaceAll(" ","");
                    if(i==0){
                        pan.setName(sts[i]);
                        pan.setFactory(null);
                    }else{
                        List<String> a = pan.getFactory();
                        if(a==null)
                            a=new ArrayList<>();
                        a.add(sts[i]);
                        pan.setFactory(a);
                    }
                }
                pans.add(pan);
            });
            productYisunBrak.setFactoryNum(ExcelUtil.getCellValue(sheet.getRow(j).getCell(1)));//工厂号
            //查询二级分类
            productYisunBrak.setClassifyId("509");
            productYisunBrak.setAnotherName( ExcelUtil.getCellValue(sheet.getRow(j).getCell(2)));
            //查询车型
            ModelBrakShengdi modelBrakShengdi = new ModelBrakShengdi();
            modelBrakShengdi.setProduct(ExcelUtil.getCellValue(sheet.getRow(j).getCell(0)));
            try{
                if(StringUtils.isNotBlank(modelBrakShengdi.getProduct())){
                    EntityWrapper wrapper = new EntityWrapper();
                    wrapper.setEntity(modelBrakShengdi);
                    List<ModelBrakShengdi> modelBrakShengdis = modelBrakShengdiMapper.selectList(wrapper);
                    StringBuffer stringBuffer = new StringBuffer();
                    modelBrakShengdis.forEach(modelBrakShengdi1 -> {
                        stringBuffer.append(modelBrakShengdi1.getId());
                        stringBuffer.append(",");
                    });
                    productYisunBrak.setModel(stringBuffer.toString());
                }
            }catch (Exception e){
                log.info("处理车型出错："+j);
            }
            Map ma = (Map) JSON.parse(ExcelUtil.getCellValue(sheet.getRow(j).getCell(6)));
            pans.forEach(p-> {
                //品牌
                productYisunBrak.setSpecName(p.getName());
                try {
                    p.getFactory().forEach(f -> {
                        //添加产品
                        productYisunBrak.setProductId(null);
                        productYisunBrak.setFactoryNum(f);
                        productYisunBrak.setHeight(ma.get("总高").toString());
                        productYisunBrak.setDiscType(ma.get("直径").toString());
                        productYisunBrak.setMinimumThickness(ma.get("最小厚度").toString());
                        productYisunBrak.setPitchDiameter(ma.get("节圆直径").toString());
                        productYisunBrak.setDiscThickness(ma.get("制动盘厚度").toString());
                        productYisunBrak.setCenterHoleDiameter(ma.get("中心孔直径").toString());
                        productYisunBrakMapper.insert(productYisunBrak);
                    });
                } catch (Exception e) {
                }
            });
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
