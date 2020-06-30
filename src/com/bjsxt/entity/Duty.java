package com.bjsxt.entity;

import java.util.Date;

/**
 * 考勤类
 * @author Administrator
 *
 */
public class Duty {

	private int dtID;
	
	private Date dtDate;
	private String signinTime;
	private String signoutTime;
	private String empId; //可以同时提供id和对象属性
	private Employee emp;//员工的信息
	
	
	
	public Duty() {
		super();
	}
	
	
	public Duty(Date dtDate, String signinTime, String signoutTime, String empId) {
		super();
		this.dtDate = dtDate;
		this.signinTime = signinTime;
		this.signoutTime = signoutTime;
		this.empId = empId;
	}


	public Duty(int dtID, Date dtDate, String signinTime, String signoutTime,
			Employee emp) {
		super();
		this.dtID = dtID;
		this.dtDate = dtDate;
		this.signinTime = signinTime;
		this.signoutTime = signoutTime;
		this.emp = emp;
	}


	public int getDtID() {
		return dtID;
	}
	public void setDtID(int dtID) {
		this.dtID = dtID;
	}
	public Date getDtDate() {
		return dtDate;
	}
	public void setDtDate(Date dtDate) {
		this.dtDate = dtDate;
	}
	public String getSigninTime() {
		return signinTime;
	}
	public void setSigninTime(String signinTime) {
		this.signinTime = signinTime;
	}
	public String getSignoutTime() {
		return signoutTime;
	}
	public void setSignoutTime(String signoutTime) {
		this.signoutTime = signoutTime;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}


	@Override
	public String toString() {
		return "Duty [dtID=" + dtID + ", dtDate=" + dtDate + ", signinTime="
				+ signinTime + ", signoutTime=" + signoutTime + ", empId="
				+ empId + ", emp=" + emp + "]";
	}
	
	
}
