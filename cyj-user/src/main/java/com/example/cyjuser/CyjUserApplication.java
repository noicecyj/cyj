package com.example.cyjuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CyjUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyjUserApplication.class, args);
    }
}
