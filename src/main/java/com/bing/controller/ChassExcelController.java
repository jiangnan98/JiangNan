package com.bing.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bing.mapper.ProductYisunChassisMapper;
import com.bing.model.ProductYisunChassis;
import com.bing.util.ExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
 * Date: 2019/5/20
 * Time: 11:54
 * To change this template use File | Setting | File Template.
 **/
@RestController
@RequestMapping("/chassis/")
public class ChassExcelController {

    @Autowired
    ProductYisunChassisMapper productYisunChassisMapper;

    @PostMapping("data")
    public void testVerify() throws Exception{
        String path = "D:\\aaa\\data\\数据\\kyb2.xlsx";
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
        System.out.println("行数："+rowLens);
        //获取车型数据
        for(int i = 0;i<rowLens;i++) {
            String oem = ExcelUtil.getCellValue(sheet.getRow(i).getCell(0));
            String kyb = ExcelUtil.getCellValue(sheet.getRow(i).getCell(1));
            String mengNuo = ExcelUtil.getCellValue(sheet.getRow(i).getCell(2));
            if(!StringUtils.isBlank(mengNuo)){
               EntityWrapper wrapper = new EntityWrapper();
               wrapper.eq("factory_num",mengNuo);
               ProductYisunChassis chassisa = new ProductYisunChassis();
               chassisa.setFactoryNum(mengNuo);
               ProductYisunChassis chassis = productYisunChassisMapper.selectOne(chassisa);
               if(chassis!=null){
                   StringBuffer stringBuffer = new StringBuffer();
                   stringBuffer.append(chassis.getOem()).append(",");
                   stringBuffer.append(oem);
                   chassis.setProductId(null);
                   ProductYisunChassis productYisunChassis = new ProductYisunChassis();
                   productYisunChassis.setSpecName("KYB");
                   productYisunChassis.setFactoryNum(kyb);
                   ProductYisunChassis c = productYisunChassisMapper.selectOne(productYisunChassis);
                   if(c!=null){
                       continue;
                   }
                   chassis.setSpecName("KYB");
                   chassis.setFactoryNum(kyb);
                   chassis.setOem(oem);
                   productYisunChassisMapper.insert(chassis);
                   continue;
               }
            }
            ProductYisunChassis productYisunChassis = new ProductYisunChassis();
            productYisunChassis.setSpecName("KYB");
            productYisunChassis.setFactoryNum(kyb);
            ProductYisunChassis c = productYisunChassisMapper.selectOne(productYisunChassis);
            StringBuffer s= new StringBuffer();
            if(c!=null){
               s.append(c.getOem()).append(",").append(oem);
               c.setOem(s.toString());
               productYisunChassisMapper.editFactory(c);
               continue;
            }
            productYisunChassis.setOem(oem);
            productYisunChassisMapper.insert(productYisunChassis);
        }
    }

    @PostMapping("oem")
    public void oem() throws Exception{
        String path = "D:\\aaa\\data\\数据\\kyb2.xlsx";
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
        System.out.println("行数："+rowLens);
        //获取车型数据
        for(int i = 0;i<rowLens;i++) {
            String oem = ExcelUtil.getCellValue(sheet.getRow(i).getCell(0));
            String kyb = ExcelUtil.getCellValue(sheet.getRow(i).getCell(1));
            String mengNuo = ExcelUtil.getCellValue(sheet.getRow(i).getCell(2));
            if (!StringUtils.isBlank(mengNuo)) {
                EntityWrapper wrapper = new EntityWrapper();
                wrapper.eq("factory_num", mengNuo);
                ProductYisunChassis chassisa = new ProductYisunChassis();
                chassisa.setFactoryNum(mengNuo);
                ProductYisunChassis chassis = productYisunChassisMapper.selectOne(chassisa);
                if (chassis != null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(chassis.getOem()).append(",");
                    stringBuffer.append(oem);
                    chassis.setOem(stringBuffer.toString().replaceAll("null,","").replaceAll(",,",""));
                    productYisunChassisMapper.editFactory(chassis);
                    continue;
                }
            }
        }
    }
}
