package com.bjsxt.service.impl;

import com.bjsxt.dao.EmployeeDao;
import com.bjsxt.dao.impl.EmployeeDaoImpl;
import com.bjsxt.entity.Employee;
import com.bjsxt.service.EmployeeService;

import java.util.Date;
import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 21/5/20
 * @Description: com.bjsxt.service.impl
 * @version: 1.0
 */
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeDao=new EmployeeDaoImpl();

    @Override
    public List<Employee> findEmp(String empId, int deptno, int onDuty, Date hireDate) {
        return employeeDao.find( empId,  deptno,  onDuty,  hireDate);
    }

    @Override
    public Employee login(String empId, String password) {
        //登录校验方法一：return employeeDao.login(empId,password);然后select * from employee where empid=? and password=?
        //方法二
        Employee emp = employeeDao.findById(empId);
        if(emp==null){ //用户名是错误等
            return  null;
        } else {
            if (password!=null&& password.equals(emp.getPassword()) ){
                return emp;
            }    else {
                return  null;
            }
        }
    }

    @Override
    public int update(Employee emp) {
        return employeeDao.update(emp);
    }

    @Override
    public Employee findById(String empId) {
        return employeeDao.findById(empId);
    }

    @Override
    public void delete(String empId) {
        employeeDao.delete(empId);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public List<Employee> findEmpByType(int type) {
        return employeeDao.findByType(type);
    }

    @Override
    public int save(Employee employee) {
        return employeeDao.save(employee);
    }
}
