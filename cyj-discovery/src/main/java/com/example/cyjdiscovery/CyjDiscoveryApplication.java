package com.example.cyjdiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@SpringBootApplication
@EnableEurekaServer
public class CyjDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyjDiscoveryApplication.class, args);
    }
}
