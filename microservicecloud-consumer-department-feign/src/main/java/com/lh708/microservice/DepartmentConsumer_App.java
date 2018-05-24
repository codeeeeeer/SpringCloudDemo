package com.lh708.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 〈the application of department consumer〉
 *
 * @author liujie
 * @create 2018/05/01 21:55
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class DepartmentConsumer_App {
    public static void main(String[] args){
        SpringApplication.run(DepartmentConsumer_App.class, args);
    }
}
