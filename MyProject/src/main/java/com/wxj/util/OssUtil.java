package com.wxj.util;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

/**
 * Created by xushilong on 2017/8/8.
 */
@Component
public class OssUtil {
    @Value("${oss.endpoint}")
    private String endPoint;
    @Value("${oss.accesskey}")
    private String accessKey;
    @Value("${oss.accessid}")
    private String accessId;
    @Value("${oss.bucket}")
    private String bucket;
    @Value("${oss.url}")
    private String url;

    private volatile OSSClient ossClient;

    public OSSClient getInstance() {
        if (ossClient == null) {
            synchronized (this) {
                if (ossClient == null) {
                    ossClient = new OSSClient(endPoint, accessId, accessKey);
                }
            }
        }
        return ossClient;
    }

    public String uploadFile(ByteArrayInputStream in, String file_name) {
        ossClient = getInstance();
        ossClient.putObject(bucket, file_name, in);
        return url + file_name;
    }
}
