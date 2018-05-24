package com.lh708.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 〈the eureka application of department〉
 *
 * @author liujie
 * @create 2018/05/02 20:58
 */
@SpringBootApplication
@EnableEurekaServer
public class DepartmentEureka7003_App {
    public static void main(String[] args){
        SpringApplication.run(DepartmentEureka7003_App.class, args);
    }
}
