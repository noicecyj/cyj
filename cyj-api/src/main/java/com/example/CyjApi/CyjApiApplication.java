package com.example.CyjApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-12-27
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CyjApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyjApiApplication.class, args);
    }

}
