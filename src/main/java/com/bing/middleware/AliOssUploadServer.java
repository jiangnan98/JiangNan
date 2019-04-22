package com.bing.middleware;

import com.aliyun.oss.OSSClient;
import com.bing.config.AliOssConfig;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.UUID;

/**
 * 阿里云OSS文件上传
 *
 */
@Component
public class AliOssUploadServer {
	
	protected static final Logger logger = LoggerFactory.getLogger(AliOssUploadServer.class);

	@Autowired
	private AliOssConfig aliOssConfig;
	
	private static final String HOME_FILE="Lizard/";
	
	public String fileOssUpload(MultipartFile file){
		String reqUrl="";
		String endPoint=aliOssConfig.getAddress();
		String accessKey=aliOssConfig.getAccessKey();
		String accessSecret=aliOssConfig.getAccessSecret();
		OSSClient client = new OSSClient(endPoint, accessKey, accessSecret);
		String bucket=aliOssConfig.getBucketName();
		//获取文件后缀名
		String fileType = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
		String fileName=HOME_FILE.concat(UUID.randomUUID().toString()).concat(".").concat(fileType);
		try {
			Date expire = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
			client.putObject(bucket, fileName, new ByteArrayInputStream(file.getBytes()));
			reqUrl=client.generatePresignedUrl(bucket, fileName, expire).toString();
			reqUrl = reqUrl.substring(0,reqUrl.indexOf("?"));
		} catch (Exception e) {
			logger.error("aliOssUpload Fail:"+e.getMessage());
			return reqUrl;
		}finally{
			client.shutdown();
		}
		return reqUrl;
	}
}
