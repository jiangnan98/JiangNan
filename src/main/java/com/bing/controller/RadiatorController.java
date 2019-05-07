package com.bing.controller;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/5/7
 * Time: 9:20
 * To change this template use File | Setting | File Template.
 **/

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bing.mapper.ModelRadiatorCarhomeMapper;
import com.bing.mapper.ProductYisunRadiatorMapper;
import com.bing.model.ModelRadiatorCarhome;
import com.bing.model.ProductYisunRadiator;
import com.bing.util.ExcelUtil;
import com.bing.util.LogUtils;
import org.apache.commons.lang.StringUtils;
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

@RestController
@RequestMapping("/radiator/")
public class RadiatorController {
    Logger log = LogUtils.getBussinessLogger();

    @Autowired
    ProductYisunRadiatorMapper productYisunRadiatorMapper;
    @Autowired
    ModelRadiatorCarhomeMapper modelRadiatorCarhomeMapper;

    @PostMapping("JiaShiData")
    public void JiaShiData() throws Exception{
        File file = new File("D:\\aaa\\data\\汽车之家(1)(1).xlsx");
        InputStream is = new FileInputStream(file);
        Workbook wb = new XSSFWorkbook(is);
        Sheet sheet = wb.getSheetAt(0);
        is.close();
        //获取行数
        int rowLens = sheet.getLastRowNum();
        log.info("行数："+rowLens);
        //获取车型数据
        for(int i = 1;i<rowLens;i++){
            String num = ExcelUtil.getCellValue(sheet.getRow(i).getCell(8));
            if(StringUtils.isBlank(num)){
                continue;
            }
            ModelRadiatorCarhome modelRadiatorCarhome = new ModelRadiatorCarhome();
            modelRadiatorCarhome.setLetter(ExcelUtil.getCellValue(sheet.getRow(i).getCell(0)));
            modelRadiatorCarhome.setBrand(ExcelUtil.getCellValue(sheet.getRow(i).getCell(1)));
            modelRadiatorCarhome.setFactory(ExcelUtil.getCellValue(sheet.getRow(i).getCell(2)));
            modelRadiatorCarhome.setModel(ExcelUtil.getCellValue(sheet.getRow(i).getCell(3)));
            modelRadiatorCarhome.setModelDetail(ExcelUtil.getCellValue(sheet.getRow(i).getCell(5)));
            modelRadiatorCarhome.setEngineInfo(ExcelUtil.getCellValue(sheet.getRow(i).getCell(4)));
            modelRadiatorCarhome.setTrans(ExcelUtil.getCellValue(sheet.getRow(i).getCell(6)));
            EntityWrapper<ModelRadiatorCarhome> wrapper = new EntityWrapper<>();
            wrapper.setEntity(modelRadiatorCarhome);
            List<ModelRadiatorCarhome> carhomes = modelRadiatorCarhomeMapper.selectList(wrapper);
            ProductYisunRadiator productYisunRadiator = new ProductYisunRadiator();
            productYisunRadiator.setFactoryNum(num);
            ProductYisunRadiator p = productYisunRadiatorMapper.selectOne(productYisunRadiator);
            if(p!=null){
                StringBuffer strs = new StringBuffer(p.getModel());
                strs.append(carhomes.get(0).getId()).append(",");
                p.setModel(strs.toString());
                productYisunRadiatorMapper.updateById(p);
            }else{
                productYisunRadiator.setSpecName(ExcelUtil.getCellValue(sheet.getRow(i).getCell(7)));
                productYisunRadiator.setOem(ExcelUtil.getCellValue(sheet.getRow(i).getCell(9)));
                productYisunRadiator.setClassifyId("1501");
                productYisunRadiator.setAnotherName("水箱");
                productYisunRadiator.setModel(String.valueOf(carhomes.get(0).getId())+",");
                productYisunRadiatorMapper.insert(productYisunRadiator);
            }
        }
    }

}
