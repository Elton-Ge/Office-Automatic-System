package com.bjsxt.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 报销单
 * @author Administrator
 *
 */
public class Expense {

	private  int expId;//报销单编号  序列自增
	private String empId;//报销人编号  添加时使用
	private double totalAmount;//报销单总金额
	private Date expTime;//报销时间  yyyMMdd
	private String expDesc;//报销单总备注信息
	private String nextAuditorId;//下一个审核人的编号
	private String lastResult;//最新的审核结果  数据中该列虽然有冗余，可以提高效率
	private String  status;//报销单的状态     0：新创建  1：审核中   2 审核结束通过  3 审核通过驳回  4.已打款 
	
	private List<ExpenseItem> itemList = new ArrayList<ExpenseItem>(); //该报销单的所有明细信息
	
	public Expense() {
		super();
	}
	
	
	public Expense(String empId, double totalAmount, Date expTime,
			String expDesc, String nextAuditorId, String lastResult,
			String status) {
		super();
		this.empId = empId;
		this.totalAmount = totalAmount;
		this.expTime = expTime;
		this.expDesc = expDesc;
		this.nextAuditorId = nextAuditorId;
		this.lastResult = lastResult;
		this.status = status;
	}


	public Expense(int expId, String empId, double totalAmount, Date expTime,
			String expDesc, String nextAuditorId, String lastResult,
			String status, Employee emp, Employee nextAuditor) {
		super();
		this.expId = expId;
		this.empId = empId;
		this.totalAmount = totalAmount;
		this.expTime = expTime;
		this.expDesc = expDesc;
		this.nextAuditorId = nextAuditorId;
		this.lastResult = lastResult;
		this.status = status;
		this.emp = emp;
		this.nextAuditor = nextAuditor;
	}
	private Employee  emp;//报销人  查询时使用  
	private Employee  nextAuditor;//下一个审核人
	public int getExpId() {
		return expId;
	}
	public void setExpId(int expId) {
		this.expId = expId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Date getExpTime() {
		return expTime;
	}
	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}
	public String getExpDesc() {
		return expDesc;
	}
	public void setExpDesc(String expDesc) {
		this.expDesc = expDesc;
	}
	public String getNextAuditorId() {
		return nextAuditorId;
	}
	public void setNextAuditorId(String nextAuditorId) {
		this.nextAuditorId = nextAuditorId;
	}
	public String getLastResult() {
		return lastResult;
	}
	public void setLastResult(String lastResult) {
		this.lastResult = lastResult;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public Employee getNextAuditor() {
		return nextAuditor;
	}
	public void setNextAuditor(Employee nextAuditor) {
		this.nextAuditor = nextAuditor;
	}


	public List<ExpenseItem> getItemList() {
		return itemList;
	}


	public void setItemList(List<ExpenseItem> itemList) {
		this.itemList = itemList;
	}


	@Override
	public String toString() {
		return "Expense [expId=" + expId + ", empId=" + empId
				+ ", totalAmount=" + totalAmount + ", expTime=" + expTime
				+ ", expDesc=" + expDesc + ", nextAuditorId=" + nextAuditorId
				+ ", lastResult=" + lastResult + ", status=" + status
				+ ", emp=" + emp + ", nextAuditor=" + nextAuditor + "]";
	}
	
	
	
}
