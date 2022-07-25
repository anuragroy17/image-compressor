package com.anuragroy.imagecompressor.config;

import com.tinify.Tinify;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class TinifyConfig implements InitializingBean{

    public void afterPropertiesSet() {
        String apiKey = System.getenv("TINIFY_API_KEY");
        if (StringUtils.isBlank(apiKey)) {
            throw new IllegalArgumentException("TINIFY_API_KEY must be configured");
        }
        try {
            Tinify.setKey(apiKey);
            Tinify.validate();
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to validate API Key");
        }
    }
}
