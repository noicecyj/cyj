package com.example.cyjdatadictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CyjDataDictionaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyjDataDictionaryApplication.class, args);
    }
}
