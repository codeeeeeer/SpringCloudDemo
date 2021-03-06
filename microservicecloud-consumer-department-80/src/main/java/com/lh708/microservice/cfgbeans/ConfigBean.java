package com.lh708.microservice.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 〈the configuration bean of consumer department〉
 *
 * @author liujie
 * @create 2018/05/01 21:41
 */

@Configuration
public class ConfigBean {
    @Bean           //将返回值作为一个bean注册进spring上下文
    @LoadBalanced          //启用Riboon的负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
