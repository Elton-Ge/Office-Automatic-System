package com.bjsxt.service;

import java.sql.Date;
import java.util.List;

import com.bjsxt.entity.Duty;

public interface DutyService {
	/**
	 * 完成签到操作
	 * @param empId
	 * @return
	 */
	int signin(String empId);
	/**
	 * 完成签退操作
	 * @param empId
	 * @return
	 */
	int signout(String empId);
	/**
	 * 查询符合条件的考勤信息
	 * @param empId
	 * @param deptno
	 * @param dtDate
	 * @return
	 */
	List<Duty> findDuty(String empId, int deptno, Date dtDate);

}
