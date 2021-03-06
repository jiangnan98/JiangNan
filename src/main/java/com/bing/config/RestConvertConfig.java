package com.bing.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Restful接口NULL回执转换器
 *
 */
@Configuration
public class RestConvertConfig extends WebMvcConfigurationSupport {
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(
				SerializerFeature.WriteNullListAsEmpty,// 空集合
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullStringAsEmpty,// 字符串null返回空字符串
				SerializerFeature.WriteNullBooleanAsFalse,
				SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.PrettyFormat);
		fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		converter.setFastJsonConfig(fastJsonConfig);
		List<MediaType> types = new ArrayList<MediaType>();
		types.add(MediaType.APPLICATION_JSON_UTF8);
		converter.setSupportedMediaTypes(types);
		converter.setDefaultCharset(Charset.forName("UTF-8"));
		converters.add(converter);
	}
}
