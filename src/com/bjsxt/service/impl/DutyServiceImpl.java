package com.bjsxt.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bjsxt.dao.DutyDao;
import com.bjsxt.dao.impl.DutyDaoImpl;
import com.bjsxt.entity.Duty;
import com.bjsxt.service.DutyService;

public class DutyServiceImpl implements DutyService {
	
	private DutyDao dutyDao = new DutyDaoImpl();
	@Override
	public int signin(String empId) {
		//判断用户是否已经签到
		Date now = new Date();//yyyyMMdd hhmmss
		java.sql.Date today = new java.sql.Date(now.getTime());
		boolean flag = dutyDao.find(empId,today);
		
		//如果已经签到，返回2；如果没有签到，进行签到
		int n=0;
		if(flag){//已经签到
			return  2;
		}else{
			//完成签到
			Duty duty = new Duty();
			//duty.setDtID(dtID); // 序列自增
			duty.setEmpId(empId);//员工编号
			//duty.setEmp(emp); //此处使用它就繁琐了
			duty.setDtDate(today);
			DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			String signinTime = sdf.format(now);
			duty.setSigninTime(signinTime);
			
			n = dutyDao.save(duty);
			return n;
		}		
	}
	@Override
	public int signout(String empId) {
		//判断用户是否已经签到
		Date now = new Date();//yyyyMMdd hhmmss
		java.sql.Date today = new java.sql.Date(now.getTime());
		boolean flag = dutyDao.find(empId,today);
		
		//如果已经签到，返回2；如果没有签到，进行签到
		int n=0;
		if(!flag){//尚未签到
			return  2;
		}else{
			//完成签退
			Duty duty = new Duty();
			//duty.setDtID(dtID); // 序列自增
			duty.setEmpId(empId);//员工编号
			//duty.setEmp(emp); //此处使用它就繁琐了
			duty.setDtDate(today);
			DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			String signoutTime = sdf.format(now);
			duty.setSignoutTime(signoutTime);
			
			n = dutyDao.update(duty);
			return n;
		}	
	}
	@Override
	public List<Duty> findDuty(String empId, int deptno, java.sql.Date dtDate) {		
		return this.dutyDao.find(empId, deptno,dtDate);
	}

}
