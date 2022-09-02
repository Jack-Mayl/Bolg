package com.longzai;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Map;

@SpringBootApplication
@MapperScan("com.longzai.mapper")
@EnableScheduling
public class LongZaiBolgApplication {
    public static void main(String[] args) {
        System.setProperty("user.name", "root");
        for (Map.Entry<Object, Object> objectObjectEntry : System.getProperties().entrySet()) {
            System.out.println(objectObjectEntry.getKey()+"==="+objectObjectEntry.getValue());
        }
        SpringApplication.run(LongZaiBolgApplication.class);
    }
}
