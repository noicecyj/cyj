package com.example.cyjdiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CyjDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyjDiscoveryApplication.class, args);
    }
}
