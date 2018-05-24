package com.lh708.microservice.service;

import com.lh708.microservice.entities.Department;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 〈the interface of department service〉
 *
 * @author liujie
 * @create 2018/05/09 20:05
 */
//@FeignClient("MICROSERVICECLOUD-DEPT")
    //当Eureka不能找到对应的注册实例的时候，就会调用fallBackFactory配置的类对象
@FeignClient(value = "MICROSERVICECLOUD-DEPT", fallbackFactory = DepartmentServiceFallBackFactory.class)
public interface DepartmentService {

    //这回查询eureka，找到application-name对应的注册实例，然后根据mapping配置，请求相应的方法
    @PostMapping("/dept/add")
    public boolean add(Department department);

    @GetMapping("/dept/list")
    public List<Department> list();

    @GetMapping("/dept/get/{id}")
    public Department get(@PathVariable("id") Long id);
}
