package com.bjsxt.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.bjsxt.dao.DutyDao;
import com.bjsxt.entity.Department;
import com.bjsxt.entity.Duty;
import com.bjsxt.entity.Employee;
import com.bjsxt.util.DBUtil;

public class DutyDaoImpl implements DutyDao {

	@Override
	public boolean find(String empId, Date today) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Department dept = null;
		boolean flag = false;//没有签到
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			
			//3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement("select * from duty  where empid = ? and dtdate = ?");
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）		
			pstmt.setString(1, empId);
			pstmt.setDate(2, today);
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			if(rs.next()){
				flag  = true;//已经签到
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}
		
		//7.返回数据		
		return flag;
	}

	@Override
	public int save(Duty duty) {
		String sql = "insert into duty(empid,dtdate,signintime) values(?,?,?)";
		Object [] params = {duty.getEmpId(),duty.getDtDate(),duty.getSigninTime()};
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public int update(Duty duty) {
		String sql = "update duty set signouttime = ? where empid = ? and dtdate = ?";
		Object [] params = {duty.getSignoutTime(),duty.getEmpId(),duty.getDtDate()};
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public List<Duty> find(String empId, int deptno, Date dtDate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Duty> list = new ArrayList<Duty>();
		boolean flag = false;//没有签到
		try {
			//2.建立和数据库的连接（url，user、password）			
			conn =DBUtil.getConnection();
			StringBuilder sql =new StringBuilder("select dt.*,e.deptno,e.realname,d.deptname from duty dt "
					+ "join employee e on dt.empid = e.empid "
					+ "join dept d on e.deptno = d.deptno where 1=1 ");
			
			if(empId != null & !"".equals(empId)){
				sql.append(" and  dt.empId= '"+empId+"'");				
			}
			if(deptno !=0){
				sql.append(" and e.deptno ="+ deptno);
				
			}
			
			if(dtDate != null){
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String sdtDate = sdf.format(dtDate);
				sql.append(" and DATE_FORMAT(dt.dtDate,'%Y-%m-%d') >='"+sdtDate+"'");
				
			}
			//3.创建SQL命令发送器（手枪）
			pstmt = conn.prepareStatement(sql.toString());
			//4.使用SQL命令发送器发送SQL命令给数据库，并得到返回的结果（子弹）		
			rs = pstmt.executeQuery();
			
			//5.处理结果（封装到List中）
			while(rs.next()){
				//获取各个字段的值
				int dtID = rs.getInt("dtid");
				Date dtDate2 = rs.getDate("dtDate");
				String signinTime = rs.getString("signinTime");
				String signoutTime = rs.getString("signoutTime");
				
				
				String empId2 = rs.getString("empId");
				String realName = rs.getString("realName");
				
				int deptno2 = rs.getInt("deptno");
				String deptName = rs.getString("deptName");
				Department dept = new Department(deptno2, deptName);
				
				Employee emp = new Employee();
				emp.setEmpId(empId2);
				emp.setRealName(realName);
				emp.setDept(dept);
				//
				Duty duty = new Duty(dtID, dtDate2, signinTime, signoutTime, emp);
				
				//
				list.add(duty);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//6.关闭资源
			DBUtil.closeAll(rs, pstmt, conn);
		}
		
		//7.返回数据		
		return list;
	}

}
