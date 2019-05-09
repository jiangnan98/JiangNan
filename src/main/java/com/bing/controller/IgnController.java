package com.bing.controller;

import com.bing.mapper.ProductYisunIgnitionMapper;
import com.bing.middleware.AliOssUploadServer;
import com.bing.model.ProductYisunIgnition;
import com.bing.util.ChineseUtils;
import com.bing.util.ExcelUtil;
import com.bing.util.LogUtils;
import com.bing.util.MoneyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.entity.ContentType;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/5/8
 * Time: 14:36
 * To change this template use File | Setting | File Template.
 **/
@RestController
@RequestMapping("/ign/")
public class IgnController {
    Logger log = LogUtils.getBussinessLogger();

    @Autowired
    AliOssUploadServer aliOssUploadServer;
    @Autowired
    ProductYisunIgnitionMapper productYisunIgnitionMapper;


    @PostMapping("dianhuo")
    public void dianhuo() throws Exception{
        String path = "D:\\\\aaa\\\\data\\\\上海铃宇点火线圈电子目录（2018.12.11修订）.xls";
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
        List<HSSFPictureData> pictures = (List<HSSFPictureData>)wb.getAllPictures();
        is.close();
        //获取行数
        int rowLens = sheet.getLastRowNum();
        System.out.println("行数："+rowLens);
        //获取车型数据
        for(int i = 0;i<rowLens;i++) {
            ProductYisunIgnition productYisunIgnition = new ProductYisunIgnition();
            productYisunIgnition.setClassifyId("703");
            productYisunIgnition.setApplyModel(ExcelUtil.getCellValue(sheet.getRow(i).getCell(5)));
            String factoryNum = ExcelUtil.getCellValue(sheet.getRow(i).getCell(1));
            String remark = ExcelUtil.getCellValue(sheet.getRow(i).getCell(3));
            System.out.println("factory_num："+factoryNum);
            System.out.println("remark："+remark);
            if(StringUtils.isBlank(factoryNum)){
                continue;
            }
            File pdfFile;
            FileInputStream fileInputStream;
            //处理图片
            try{
                pdfFile = new File("D:\\aaa\\pic\\0_"+i+"_2.jpeg");
                fileInputStream  = new FileInputStream(pdfFile);
                MultipartFile multipartFile = new MockMultipartFile(pdfFile.getName(), pdfFile.getName(),
                        ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
                path =  aliOssUploadServer.fileOssUpload(multipartFile);
                System.out.println("图片地址为："+path);
                productYisunIgnition.setCarousel(path);
            }catch (Exception e){
                try{
                    pdfFile = new File("D:\\aaa\\pic\\0_"+i+"_2.png");
                    fileInputStream = new FileInputStream(pdfFile);
                    MultipartFile multipartFile = new MockMultipartFile(pdfFile.getName(), pdfFile.getName(),
                            ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
                    path =  aliOssUploadServer.fileOssUpload(multipartFile);
                    System.out.println("图片地址为："+path);
                    productYisunIgnition.setCarousel(path);
                }catch (Exception e1){
                }
            }
            String str = ExcelUtil.getCellValue(sheet.getRow(i).getCell(4));
            str = str.trim().replaceAll("：","");
            String[] s = str.split("[\n\\\\\t\\\\ ]");
            List<String> tmp = new ArrayList<String>();
            for(String st:s){
                if(st!=null && st.length()!=0){
                    tmp.add(st);
                }
            }
            List<String> d = new ArrayList<>();
            if(!(tmp.size()==0)){
                d.add(tmp.get(tmp.size()-1));
            }
            for (int j = 1; j < tmp.size(); j++) {
                try{
                    if(ChineseUtils.isContainChinese(tmp.get(j))){
                        d.add(tmp.get(j-1));
                    }
                }catch (Exception e){

                }
            }
            StringBuffer stringBuffer = new StringBuffer();
            d.forEach(dd->{
                stringBuffer.append(dd.replaceAll("[\u4e00-\u9fa5]","")).append(",");
            });
            productYisunIgnition.setOem(stringBuffer.toString());
            productYisunIgnition.setRemark(remark);
            productYisunIgnition.setFactoryNum(factoryNum);
            productYisunIgnition.setApplyModel(ExcelUtil.getCellValue(sheet.getRow(i).getCell(5)));
            productYisunIgnition.setDis(ExcelUtil.getCellValue(sheet.getRow(i).getCell(6)));
            productYisunIgnition.setFactory(ExcelUtil.getCellValue(sheet.getRow(i).getCell(7)));
            productYisunIgnition.setSpecName("世宝");
            productYisunIgnitionMapper.insert(productYisunIgnition);
        }
    }

    @PostMapping("dianhuoPrice")
    public void dianhuoPrice() throws Exception {
        String path = "D:\\\\aaa\\\\data\\\\Book1.xls点火线圈1 (1).xlsx";
        File file = new File(path);
        boolean isExcel2003 = path.toLowerCase().endsWith("xls") ? true : false;
        InputStream is = new FileInputStream(file);
        Workbook wb;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        is.close();
        //获取行数
        int rowLens = sheet.getLastRowNum();
        System.out.println("行数：" + rowLens);
        //获取车型数据
        for (int i = 1; i < rowLens; i++) {
            ProductYisunIgnition productYisunIgnition = new ProductYisunIgnition();
            productYisunIgnition.setFactoryNum(ExcelUtil.getCellValue(sheet.getRow(i).getCell(10)));
            productYisunIgnition.setOurcPrice(Integer.valueOf(MoneyUtils.moneyYuanToFen(ExcelUtil.getCellValue(sheet.getRow(i).getCell(7)))));
            productYisunIgnition.setPrice(Integer.valueOf(MoneyUtils.moneyYuanToFen(ExcelUtil.getCellValue(sheet.getRow(i).getCell(9)))));
            productYisunIgnitionMapper.editPrice(productYisunIgnition);
            System.out.println(i);
        }
    }

    public static  void main(String[] args)throws Exception{
        String path = "D:\\\\aaa\\\\data\\\\上海铃宇点火线圈电子目录（2018.12.11修订）.xls";
        File file = new File(path);
        boolean isExcel2003 = path.toLowerCase().endsWith("xls")?true:false;
        InputStream is = new FileInputStream(file);
        Workbook wb;
        if(isExcel2003){
            wb = new HSSFWorkbook(is);
        }else{
            wb = new XSSFWorkbook(is);
        }
        Sheet sheeta = wb.getSheetAt(0);
        List<HSSFPictureData> pictures = (List<HSSFPictureData>)wb.getAllPictures();
        is.close();
        //获取行数
        int rowLens = sheeta.getLastRowNum();
        System.out.println("行数："+rowLens);
        //获取车型数据
        HSSFSheet sheet = (HSSFSheet) wb.getSheetAt(0);

        int i = 0;
        for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
            HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();

            if (shape instanceof HSSFPicture) {
                HSSFPicture pic = (HSSFPicture) shape;
                int row = anchor.getRow1();
                System.out.println(i + "--->" + anchor.getRow1() + ":"
                        + anchor.getCol1());
                int pictureIndex = pic.getPictureIndex()-1;
                HSSFPictureData picData = pictures.get(pictureIndex);
                System.out.println(i + "--->" + pictureIndex);
                savePic(row, picData);
            }
            i++;
        }
//       String str = "丰田：90919-02230                        \n" +
//               "          9091902230       \n" +
//               "丰田：90080-19027    9008019027                                  丰田：90080-91180    9008091180\n" +
//               "丰田：90919-01210    9091901210\n" +
//               "丰田：90919-02249    9091902249\n" +
//               "丰田：90919-02259    9091902259 \n" +
//               "丰田：90919-19027    9091919027\n" +
//               "丰田：88921392";
//       str = str.trim().replaceAll("：","");
//       String[] s = str.split("[\n\\\\\t\\\\ ]");
//        List<String> tmp = new ArrayList<String>();
//        for(String st:s){
//            if(st!=null && st.length()!=0){
//                tmp.add(st);
//            }
//        }
//       List<String> d = new ArrayList<>();
//        d.add(tmp.get(tmp.size()-1));
//        for (int i = 1; i < tmp.size(); i++) {
//            if(ChineseUtils.isContainChinese(tmp.get(i))){
//                d.add(tmp.get(i-1));
//            }
//        }
//        d.forEach(dd->{
//            System.out.println(dd.replaceAll("[\u4e00-\u9fa5]",""));
//        });
    }
    private static void savePic(int i, PictureData pic) throws Exception {

        String ext = pic.suggestFileExtension();

        byte[] data = pic.getData();
        if (ext.equals("jpeg")) {
            FileOutputStream out = new FileOutputStream(
                    "D:\\aaa\\data\\DHpic\\" + i + ".jpg");
            out.write(data);
            out.close();
        }
        if (ext.equals("png")) {
            FileOutputStream out = new FileOutputStream(
                    "D:\\aaa\\data\\DHpic\\" + i + ".png");
            out.write(data);
            out.close();
        }
    }

}
