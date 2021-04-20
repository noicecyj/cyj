package com.example.CyjAuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-03-25
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class CyjAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyjAuthApplication.class, args);
    }

}
