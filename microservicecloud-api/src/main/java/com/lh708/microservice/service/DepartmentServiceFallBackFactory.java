package com.lh708.microservice.service;

import com.lh708.microservice.entities.Department;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 〈the fall back factory class of department service〉
 *
 * @author liujie
 * @create 2018/05/10 20:23
 */
@Component  //添加这个注解以便于Spring能够扫描到，一定要添加
public class DepartmentServiceFallBackFactory implements FallbackFactory<DepartmentService> {
    @Override
    public DepartmentService create(Throwable throwable) {
        //返回一个用于备用的bean
        return new DepartmentService() {
            @Override
            public boolean add(Department department) {
                return false;
            }

            @Override
            public List<Department> list() {
                return null;
            }

            @Override
            public Department get(Long id) {
                return new Department()
                        .setId(id)
                        .setDeptName("数据库中没有该条数据，这是由服务降级提供的信息")
                        .setDataSource("No suck data in database");
            }
        };
    }
}
