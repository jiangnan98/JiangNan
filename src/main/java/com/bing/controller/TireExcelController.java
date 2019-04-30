package com.bing.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bing.mapper.ModelTireDlpMapper;
import com.bing.mapper.ProductYisunTireMapper;
import com.bing.model.ModelTireDlp;
import com.bing.model.ProductYisunTire;
import com.bing.util.ExcelUtil;
import com.bing.util.LogUtils;
import com.bing.util.MoneyUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/4/29
 * Time: 15:13
 * To change this template use File | Setting | File Template.
 **/
@RestController
@RequestMapping("/TireExcel/")
public class TireExcelController {
    Logger log = LogUtils.getBussinessLogger();

    @Autowired
    ProductYisunTireMapper productYisunTireMapper;
    @Autowired
    ModelTireDlpMapper modelTireDlpMapper;

    @PostMapping("deDta")
    public void findList() throws Exception{
        String path = "D:\\\\aaa\\\\data\\\\邓禄普价格表(2).xls";
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
        for(int i = 2;i<rowLens;i++){
            ProductYisunTire productYisunTire = new ProductYisunTire();
            productYisunTire.setFactoryNum(ExcelUtil.getCellValue(sheet.getRow(i).getCell(2)));
            String str =   ExcelUtil.getCellValue(sheet.getRow(i).getCell(3));
            if(!(str.contains("/") && str.contains("R"))){
                continue;
            }
            productYisunTire.setTreadWidth(str.substring(0,str.indexOf("/")));
            productYisunTire.setSize(str.substring(str.indexOf("R"),str.length()));
            productYisunTire.setFlatRatio(str.substring(str.indexOf("/")+1,str.indexOf("R")));
            productYisunTire.setSpeedLevel(ExcelUtil.getCellValue(sheet.getRow(i).getCell(4)));
            productYisunTire.setTireType(ExcelUtil.getCellValue(sheet.getRow(i).getCell(5)));
            productYisunTire.setOurcPrice(Integer.valueOf(MoneyUtils.moneyYuanToFen(ExcelUtil.getCellValue(sheet.getRow(i).getCell(6)))));
            productYisunTire.setPrice(Integer.valueOf(MoneyUtils.moneyYuanToFen(ExcelUtil.getCellValue(sheet.getRow(i).getCell(7)))));
            productYisunTire.setSpecName("邓禄普");
            productYisunTire.setClassifyId("801");
            productYisunTire.setCarousel("http://oss.zzbhxc.com/Lizard/6c44dfd4-6746-4507-8aaa-aa05c44bfbcd.png,http://oss.zzbhxc.com/Lizard/83688772-7ce1-4fac-b358-6a7399b262e6.png,http://oss.zzbhxc.com/Lizard/34fa9730-63e3-4078-87b0-2e143dab6fba.png");
            productYisunTire.setDetail("http://oss.zzbhxc.com/Lizard/6c44dfd4-6746-4507-8aaa-aa05c44bfbcd.png,http://oss.zzbhxc.com/Lizard/83688772-7ce1-4fac-b358-6a7399b262e6.png,http://oss.zzbhxc.com/Lizard/34fa9730-63e3-4078-87b0-2e143dab6fba.png");
            productYisunTireMapper.insert(productYisunTire);
        }


    }
    @PostMapping("deModel")
    public void deModel() throws Exception{
        ProductYisunTire productYisunTire = new ProductYisunTire();
        EntityWrapper<ProductYisunTire> wrapper = new EntityWrapper<>();
        wrapper.setEntity(productYisunTire);
        List<ProductYisunTire> productYisunTires = productYisunTireMapper.selectList(wrapper);
        productYisunTires.forEach(productYisunTire1 -> {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(productYisunTire1.getTreadWidth());
            stringBuffer.append("/");
            stringBuffer.append(productYisunTire1.getFlatRatio());
            stringBuffer.append(productYisunTire1.getSize());
            ModelTireDlp modelTireDlp = new ModelTireDlp();
            modelTireDlp.setTire(stringBuffer.toString());
            List<ModelTireDlp> modelTireDlps = modelTireDlpMapper.findModel(modelTireDlp);
            StringBuffer strModel = new StringBuffer();
            modelTireDlps.forEach(modelTireDlp1 -> {
                strModel.append(modelTireDlp1.getId());
                strModel.append(",");
            });
            productYisunTire1.setModel(strModel.toString());
            productYisunTireMapper.updateById(productYisunTire1);
        });
    }

}
