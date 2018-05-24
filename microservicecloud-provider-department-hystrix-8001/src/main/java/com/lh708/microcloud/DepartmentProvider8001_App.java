package com.lh708.microcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * 〈the spring application class of department〉
 *
 * @author liujie
 * @create 2018/05/01 16:28
 */
@SpringBootApplication
//表明该服务在启动后会自动注册进Eureka中,还需要在yml文件中进行相应的Eureka配置
@EnableEurekaClient
@EnableDiscoveryClient
//表明启动断路器，还需要在controller中进行相应的编码
@EnableCircuitBreaker
public class DepartmentProvider8001_App {
    public static void main(String[] args){
        SpringApplication.run(DepartmentProvider8001_App.class, args);
    }
}






