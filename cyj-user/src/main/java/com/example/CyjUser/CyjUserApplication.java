package com.example.CyjUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-03-25
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CyjUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyjUserApplication.class, args);
    }

}
