package com.bjsxt.dao.impl;

import com.bjsxt.dao.DepartmentDao;
import com.bjsxt.entity.Department;
import com.bjsxt.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class DepartmentDaoImpl implements DepartmentDao{


	@Override
	public Department findById(int deptno) {
		//声明jdbc变量
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Department department= new Department();
		try {
			//创建连接
			connection = DBUtil.getConnection();
			//创建sql命令
			String sql = "select * from dept where deptno=?";
			//创建sql对象
			preparedStatement = connection.prepareStatement(sql);
			//给占位符赋值
			preparedStatement.setInt(1,deptno);
			//执行sql命令
			resultSet = preparedStatement.executeQuery();
			//遍历
			if (resultSet.next()) {
				department.setDeptno(deptno);
				department.setDeptName(resultSet.getString("deptName"));
				department.setLocation(resultSet.getString("location"));

			}
			//关闭资源
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(resultSet, preparedStatement, connection);
		}
		return department;
	}

	@Override
	public int update(Department dept) {
		String sql="update  dept set deptName=? , location=? where deptno = ?"  ;
		Object []object={dept.getDeptName(),dept.getLocation(),dept.getDeptno()};
		return DBUtil.executeUpdate(sql,object);
	}

	@Override
	public int delete(int deptno) {
		String sql= "delete from dept where deptno=?" ;
		Object [] i={deptno};

		return DBUtil.executeUpdate(sql,i);
	}

	@Override
	public int save(Department dept) {
		String sql = "insert into dept values(?,?,?)";
		Object [] params = {dept.getDeptno(),dept.getDeptName(),dept.getLocation()};

		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public List<Department> findAll() {

			//声明jdbc变量
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			List <Department> userList= new ArrayList<>();
			try {
				//创建连接
				connection = DBUtil.getConnection();
				//创建sql命令
				String sql = "select * from dept";
				//创建sql对象
				preparedStatement = connection.prepareStatement(sql);
				//给占位符赋值
				//执行sql命令
				resultSet = preparedStatement.executeQuery();

				//遍历
				while (resultSet.next()) {
					Department department= new Department();
					department.setDeptno(resultSet.getInt("deptno"));
					department.setDeptName(resultSet.getString("deptName"));
					department.setLocation(resultSet.getString("location"));
					userList.add(department);
				}
				//关闭资源
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeAll(resultSet, preparedStatement, connection);
			}
			return userList;
		}

	}


