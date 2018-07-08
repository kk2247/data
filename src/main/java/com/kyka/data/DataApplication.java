package com.kyka.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DataApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }
}
