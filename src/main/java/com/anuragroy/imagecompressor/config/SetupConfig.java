package com.anuragroy.imagecompressor.config;

import com.tinify.Tinify;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class SetupConfig implements InitializingBean{

    public void afterPropertiesSet() {
        String tinifyApiKey = System.getenv("TINIFY_API_KEY");
        String apiKey = System.getenv("API_KEY");
        if (StringUtils.isBlank(tinifyApiKey) || StringUtils.isBlank(apiKey)) {
            throw new IllegalArgumentException("Api Keys must be configured");
        }
        try {
            log.info("Setting up tinify");
            Tinify.setKey(tinifyApiKey);
            Tinify.validate();
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to validate API Key");
        }
    }
}
