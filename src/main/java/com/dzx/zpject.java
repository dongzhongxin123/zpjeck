package com.dzx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dzx.dao")
public class zpject {

    public static void main(String[] args) {

        SpringApplication.run(zpject.class, args);
        System.out.println("test");
    }
}
