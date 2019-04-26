package com.bing.util;

import com.bing.model.ProductYisunFilterHy;
import com.bing.model.ProductYisunFilterManMl;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/4/22
 * Time: 16:23
 * To change this template use File | Setting | File Template.
 **/
public class BeanFillUtil {
    /**
     * 将两个JavaBean里相同的字段自动填充
     * @param dto 参数对象
     * @param obj 待填充的对象
     */
    public static Class<?> autoFillEqFields(Object dto, Class<?> obj) {
        try {
            Field[] pfields = dto.getClass().getDeclaredFields();

            Field[] ofields = obj.getClass().getDeclaredFields();

            for (Field of : ofields) {
                if (of.getName().equals("serialVersionUID")) {
                    continue;
                }
                for (Field pf : pfields) {
                    if (of.getName().equals(pf.getName())) {
                        PropertyDescriptor rpd = new PropertyDescriptor(pf.getName(), dto.getClass());
                        Method getMethod = rpd.getReadMethod();// 获得读方法

                        PropertyDescriptor wpd = new PropertyDescriptor(pf.getName(), obj.getClass());
                        Method setMethod = wpd.getWriteMethod();// 获得写方法

                        setMethod.invoke(obj, getMethod.invoke(dto));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 将两个JavaBean里相同的字段自动填充,按指定的字段填充
     * @param dto
     * @param obj
     * @param  fields
     */
    public static void autoFillEqFields(Object dto, Object obj, String[] fields) {
        try {
            Field[] ofields = obj.getClass().getDeclaredFields();

            for (Field of : ofields) {
                if (of.getName().equals("serialVersionUID")) {
                    continue;
                }
                for (String field : fields) {
                    if (of.getName().equals(field)) {
                        PropertyDescriptor rpd = new PropertyDescriptor(field, dto.getClass());
                        Method getMethod = rpd.getReadMethod();// 获得读方法

                        PropertyDescriptor wpd = new PropertyDescriptor(field, obj.getClass());
                        Method setMethod = wpd.getWriteMethod();// 获得写方法

                        setMethod.invoke(obj, getMethod.invoke(dto));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static  void main(String[] args){
        ProductYisunFilterHy productYisunFilterHy = new ProductYisunFilterHy();
        productYisunFilterHy.setPrice(2);
        productYisunFilterHy.setOurcPrice(3);
        productYisunFilterHy.setFactory("222");
        productYisunFilterHy.setProductId("22");
        ProductYisunFilterManMl productYisunFilterManMl = new ProductYisunFilterManMl();

        BeanUtils.copyProperties(productYisunFilterHy, productYisunFilterManMl);
//        autoFillEqFields(productYisunFilterHy,productYisunFilterManMl.getClass());
        System.out.println(productYisunFilterManMl.getPrice());
    }
}
