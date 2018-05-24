package com.lh708.microcloud.dao;

import com.lh708.microservice.entities.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 〈the interface of department dao〉
 *
 * @author liujie
 * @create 2018/05/01 15:55
 */

@Mapper
public interface DepartmentDao {
    Department findById(Long id);

    List<Department> findAll();

    boolean addEntity(Department department);
}
