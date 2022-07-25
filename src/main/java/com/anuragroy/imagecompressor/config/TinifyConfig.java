package com.anuragroy.imagecompressor.config;

import com.tinify.Tinify;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class TinifyConfig {

    @PostConstruct
    public void init() {
        Tinify.setKey(System.getenv("TINIFY_API_KEY"));
    }
}
