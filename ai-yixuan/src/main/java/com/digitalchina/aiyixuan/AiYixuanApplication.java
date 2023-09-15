package com.digitalchina.aiyixuan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.digitalchina.**.mapper"})
public class AiYixuanApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiYixuanApplication.class, args);
    }

}
