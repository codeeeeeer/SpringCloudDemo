package com.lh708.microcloud.service.impl;

import com.lh708.microcloud.dao.DepartmentDao;
import com.lh708.microcloud.service.DepartmentService;
import com.lh708.microservice.entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈the implemention of department service〉
 *
 * @author liujie
 * @create 2018/05/01 16:17
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao deptDao;

    @Override
    public Department get(Long id) {
        return deptDao.findById(id);
    }

    @Override
    public List<Department> list() {
        return deptDao.findAll();
    }

    @Override
    public boolean add(Department department) {
        return deptDao.addEntity(department);
    }
}
