package com.lh708.microcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 〈the spring application class of department〉
 *
 * @author liujie
 * @create 2018/05/01 16:28
 */
@SpringBootApplication
@EnableEurekaClient     //表明该服务在启动后会自动注册进Eureka中
@EnableDiscoveryClient
public class DepartmentProvider8003_App {
    public static void main(String[] args){
        SpringApplication.run(DepartmentProvider8003_App.class, args);
    }
}
