package com.anuragroy.imagecompressor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class ImageCompressorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageCompressorApplication.class, args);
	}

}
