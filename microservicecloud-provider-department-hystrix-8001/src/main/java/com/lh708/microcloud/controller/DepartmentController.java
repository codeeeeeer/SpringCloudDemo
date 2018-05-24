package com.lh708.microcloud.controller;

import com.lh708.microcloud.service.DepartmentService;
import com.lh708.microservice.entities.Department;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 一旦调用服务方法失败并抛出了错误信息后，
     * 会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
     * 避免出现服务雪崩
     */
    @GetMapping("/dept/get/{id}")
    //当该方法内捕获到未经处理的异常时，会调用fallbackmethod对应的方法，返回一个备用实体类，避免微服务拥堵
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Department get(@PathVariable Long id){
        Department dept = departmentService.get(id);
        if (dept == null){
            throw new RuntimeException("no data with id " + id + " in database");
        }
        return  dept;
    }

    public Department processHystrix_Get(@PathVariable Long id){
        return new Department().
                setId(id).
                setDeptName("数据库中没有对应该id的数据").
                setDataSource("No Such data in database");
    }
}
