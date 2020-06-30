package com.bjsxt.service;

import com.bjsxt.entity.Department;

import java.util.List;

public interface DepartmentService {
	/**
	 * 添加部门
	 * @param dept
	 * @return
	 */
	public int add(Department dept);

	/**
	 * 部门管理：查询所有部门
	 * @return
	 */
    List<Department> findAll();

	/**
	 * 删除制定部门的数据
	 * @param deptno
	 * @return
	 */
	int delete(int deptno);
	/**
	 * 查询制定部门的数据
	 * @param deptno
	 * @return
	 */
    Department findById(int deptno);
	/**
	 * 更新制定部门的数据
	 * @param dept
	 * @return
	 */
	int update(Department dept);
}
