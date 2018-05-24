package com.lh708.microservice.controller;

import com.lh708.microservice.entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 〈the controller class of department〉
 *
 * @author liujie
 * @create 2018/05/01 21:46
 */
@RestController
public class DepartmentController_Consumer {
//    private static final String URL_PREFIX = "http://localhost:8001";
//因为要使用到eureka 集群，一般是多个provider实例共享一个name，所以最好使用name来访问
    private static final String URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";
    @Autowired
    private RestTemplate template;

    @GetMapping("/consumer/get/{id}")
    public Department get(@PathVariable Long id){
        return template.getForObject(URL_PREFIX + "/dept/get/" + id, Department.class);
    }

    @PostMapping("consumer/add")
    public boolean add(@RequestBody Department department){
        return template.postForObject(URL_PREFIX + "/dept/add", department, Boolean.class);
    }

    @GetMapping("/consumer/list")
    public List<Department> list(){
        return template.getForObject(URL_PREFIX + "/dept/list", List.class);
    }

    @GetMapping("/consumer/dept/discoveryClient")
    public Object discoveryClient(){
        return template.getForObject(URL_PREFIX + "/dept/discoveryClient", Object.class);
    }
}
