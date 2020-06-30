package com.bjsxt.dao;

import com.bjsxt.entity.Department;

import java.util.List;

public interface DepartmentDao {
	/**
	 * 添加部门
	 * @param dept
	 * @return
	 */
	public int save(Department dept);

	/**
	 *   查询全部部门
	 * @return
	 */

	List<Department> findAll();
	/**
	 *   删除选定的部门
	 * @return
	 */

	int delete(int deptno);

	/**
	 *  查询选定的部门
	 * @return
	 */
    Department findById(int deptno);
	/**
	 *  更新选定的部门
	 * @return
	 */
	int update(Department dept);
}
