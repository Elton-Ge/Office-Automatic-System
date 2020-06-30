package com.bjsxt.service;

import com.bjsxt.entity.Employee;

import java.util.Date;
import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 21/5/20
 * @Description: com.bjsxt.service
 * @version: 1.0
 */
public interface EmployeeService {
    int save(Employee employee);

    List<Employee> findEmpByType(int type);

    List<Employee> findAll();

    //多条件查询
    List<Employee> findEmp(String empId, int deptno, int onDuty, Date hireDate);

    /**
     * 删除指定员工
     * @param empId
     */
    void delete(String empId);

    /**
     * 根据员工empid显示信息
     * @param empId
     * @return
     */
    Employee findById(String empId);

    int update(Employee emp);

    Employee login(String empId, String password);
}
