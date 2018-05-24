package com.lh708.microcloud.service;

import com.lh708.microservice.entities.Department;

import java.util.List;

/**
 * 〈the interface of department service〉
 *
 * @author liujie
 * @create 2018/05/01 16:16
 */
public interface DepartmentService {
    Department get(Long id);

    List<Department> list();

    boolean add(Department department);
}
