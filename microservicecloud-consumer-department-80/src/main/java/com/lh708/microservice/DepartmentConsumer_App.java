package com.lh708.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 〈the application of department consumer〉
 *
 * @author liujie
 * @create 2018/05/01 21:55
 */
@SpringBootApplication
@EnableEurekaClient         //表示需要访问eureka，但是由于yml文件中的配置，不会注册进eureka
public class DepartmentConsumer_App {
    public static void main(String[] args){
        SpringApplication.run(DepartmentConsumer_App.class, args);
    }
}
