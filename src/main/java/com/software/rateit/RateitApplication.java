package com.software.rateit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
@SpringBootApplication
public class RateitApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RateitApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RateitApplication.class, args);
    }


    private static Class<RateitApplication> applicationClass = RateitApplication.class;
}