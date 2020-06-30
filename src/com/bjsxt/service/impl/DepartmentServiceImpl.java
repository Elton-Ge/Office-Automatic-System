package com.bjsxt.service.impl;

import com.bjsxt.dao.DepartmentDao;
import com.bjsxt.dao.impl.DepartmentDaoImpl;
import com.bjsxt.entity.Department;
import com.bjsxt.service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService{

	private DepartmentDao deptDao = new DepartmentDaoImpl();
	
	@Override
	public int add(Department dept) {
		return this.deptDao.save(dept);
	}

	@Override
	public int update(Department dept) {
		return deptDao.update(dept);
	}

	@Override
	public Department findById(int deptno) {
		return deptDao.findById(deptno);
	}

	@Override
	public List<Department> findAll() {
		return deptDao.findAll();
	}

	@Override
	public int delete(int deptno) {
		return deptDao.delete(deptno);
	}

}
