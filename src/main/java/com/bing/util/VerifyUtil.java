package com.bing.util;

import com.bing.anno.Verify;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;


/**
 * VO参数校验辅助类
 *
 */
public class VerifyUtil {

	protected static Log logger = LogFactory.getLog(VerifyUtil.class);

	public static Boolean verifyParams(Object obj) {
		Boolean flag = true;
		try {
			flag =verifySuperParams(flag,obj.getClass(),obj);
		} catch (Exception e) {
			logger.debug("Verify Exception:" + e.getMessage());
			return false;
		}
		return flag;
	}

	/**
	 * @Desc 校验父类参数
	 * @author Wisty
	 * @Date 2018年10月24日 下午12:00:30
	 */
	private static Boolean verifySuperParams(Boolean flag, Class<?> clazz,
			Object obj) throws Exception {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// 暴力反射,获取私有属性值
			field.setAccessible(true);
			// 获取属性的注解
			Verify verify = field.getAnnotation(Verify.class);
			if (verify != null) {
				// 获取属性的值
				Object objValue = field.get(obj);
				if (verify.require() && objValue == null) {
					flag = false;
				}
				if(verify.require() && objValue instanceof String){
					if(objValue==""){
						flag = false;
					}
				}
			}
		}
		Class<?> superClazz = clazz.getSuperclass();
		if (superClazz != null) {
			flag =verifySuperParams(flag,superClazz,obj);
		}
		return flag;

	}
}
