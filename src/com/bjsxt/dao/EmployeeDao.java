package com.bjsxt.dao;

import com.bjsxt.entity.Employee;
import com.bjsxt.service.EmployeeService;

import java.util.Date;
import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 21/5/20
 * @Description: com.bjsxt.dao
 * @version: 1.0
 */
public interface EmployeeDao {
    //添加
    int save(Employee employee);

    List<Employee> findByType(int type);

    List<Employee> findAll();

    List<Employee> find(String empId, int deptno, int onDuty, Date hireDate);

    void delete(String empId);

    Employee findById(String empId);

    int update(Employee emp);

}
