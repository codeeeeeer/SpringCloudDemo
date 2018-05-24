package com.lh708.microservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 〈the definitions of Department Entity〉
 *
 * @author liujie
 * @create 2018/04/30 12:15
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Department implements Serializable {
    private Long id;
    private String deptName;
    private String dataSource;

    public Department(String deptName) {
        this.deptName = deptName;
    }
}
