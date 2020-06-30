package com.bjsxt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bjsxt.dao.ExpenseDao;
import com.bjsxt.entity.Department;
import com.bjsxt.entity.Expense;
import com.bjsxt.util.DBUtil;

public class ExpenseDaoImpl implements ExpenseDao{

	@Override
	public int nextVal() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int nextVal = 0;
		try {
			//2.建立和数据库的连接（url，user、password）
			conn =DBUtil.getConnection();
			//3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select seq_exp.nextval from dual");
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）
			rs = pstmt.executeQuery();

			//5.处理结果（封装到List中）
			rs.next();
			nextVal = rs.getInt(1);


		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}
		//7.返回数据
		return nextVal;
	}

	@Override
	public void save(Expense exp) {
		String sql = "insert into expense values(?,?,?,?,?,?,?,?)";
		Object [] params = {exp.getExpId(),exp.getEmpId(),exp.getTotalAmount(),
				new java.sql.Date(exp.getExpTime().getTime()),exp.getExpDesc(),exp.getNextAuditorId(),
				exp.getLastResult(),exp.getStatus()};
		DBUtil.executeUpdate(sql, params);


	}
}
