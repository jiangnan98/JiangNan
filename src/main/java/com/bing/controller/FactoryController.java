package com.bing.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bing.mapper.CompanyMapper;
import com.bing.mapper.ProductTypeMapper;
import com.bing.mapper.ProductYisunCellCopy1Mapper;
import com.bing.model.Company;
import com.bing.model.ProductType;
import com.bing.model.ProductYisunCellCopy1;
import com.bing.res.vo.FactoryNum;
import com.bing.util.ChineseUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/5/30
 * Time: 15:08
 * To change this template use File | Setting | File Template.
 **/
@RestController
@RequestMapping("/factory/")
public class FactoryController {

    @Autowired
    ProductTypeMapper productTypeMapper;
    @Autowired
    ProductYisunCellCopy1Mapper productYisunCellCopy1Mapper;
    @Autowired
    CompanyMapper companyMapper;


    @PostMapping("num/add")
    public void xyFilter() throws Exception {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("p_id","0");
//        wrapper.eq("table_name","electric");
        List<ProductType> types = productTypeMapper.selectList(wrapper);
        int i = 00166;
        for (ProductType t : types) {
            List<FactoryNum> factoryNums = productYisunCellCopy1Mapper.findFactory(t.getTableName());
            for (FactoryNum num : factoryNums) {
                String companyName;
                i++;
                if(StringUtils.isNotBlank(num.getShopId())){
                    Company company =new Company();
                    company.setId(num.getShopId());
                    company = companyMapper.selectOne(company);

                    if(company == null){
                        companyName = "bhyx";
                    }else{
                        companyName = company.getComName();
                    }
                    try{
                        num.setFactoryNum(ChineseUtils.getPinYinHeadChar(companyName,4)+"-"+i);
                    }catch (Exception e){
                        num.setFactoryNum("bhyx"+"-"+i);
                    }
                }else{
                    num.setFactoryNum("bhyx"+"-"+i);
                }
                num.setTab(t.getTableName());
                productYisunCellCopy1Mapper.editFactory(num);
            }
        }
    }
}
