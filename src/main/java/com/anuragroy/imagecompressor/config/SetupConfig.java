package com.anuragroy.imagecompressor.config;

import com.tinify.Tinify;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SetupConfig implements InitializingBean{

    public void afterPropertiesSet() {
        String tinifyApiKey = System.getenv("TINIFY_API_KEY");
        String apiKey = System.getenv("API_KEY");
        if (StringUtils.isBlank(tinifyApiKey) || StringUtils.isBlank(apiKey)) {
            throw new IllegalArgumentException("Api Keys must be configured");
        }
        try {
            Tinify.setKey(tinifyApiKey);
            Tinify.validate();
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to validate API Key");
        }
    }
}
