package com.lh708.microservice.controller;

import com.lh708.microservice.entities.Department;
import com.lh708.microservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 〈the controller class of department〉
 *
 * @author liujie
 * @create 2018/05/01 21:46
 */
@RestController
public class DepartmentController_Consumer {
    @Autowired
    private DepartmentService service;

    @GetMapping("/consumer/get/{id}")
    public Department get(@PathVariable Long id){
        return this.service.get(id);
    }

    @PostMapping("consumer/add")
    public boolean add(@RequestBody Department department){
        return this.service.add(department);
    }

    @GetMapping("/consumer/list")
    public List<Department> list(){
        return this.service.list();
    }
}
