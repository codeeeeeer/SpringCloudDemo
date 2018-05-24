package com.lh708.microcloud.controller;

import com.lh708.microcloud.service.DepartmentService;
import com.lh708.microservice.entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 〈the controller class of department 〉
 *
 * @author liujie
 * @create 2018/05/01 16:20
 */

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    DiscoveryClient client;

    @PostMapping("/dept/add")
    public boolean add(Department department){
        return departmentService.add(department);
    }

    @GetMapping("/dept/get/{id}")
    public Department get(@PathVariable Long id){
        return  departmentService.get(id);
    }

    @GetMapping("/dept/list")
    public List<Department> list(){
        return departmentService.list();
    }

    @GetMapping("/dept/discoveryClient")
    public Object discoveryClient(){
        List<String> services = client.getServices();
        return this.client;
    }
}
