package com.example.cyjpagemenu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CyjPageMenuApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyjPageMenuApplication.class, args);
    }

}
