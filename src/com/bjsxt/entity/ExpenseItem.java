package com.bjsxt.entity;
/**
 * 报销单明细
 * @author Administrator
 *
 */
public class ExpenseItem {

	private int itemId;//明细编号   序列自增
	private int expId;//所属报销单的编号
	private String type;//报销单明细的类型   通信费用，办公耗材  水费电费
	private double amount;//明细金额
	private String itemDesc;//明细说明
	
	
	private Expense expense;//报销单
	
	public ExpenseItem() {
		super();
	}


	public ExpenseItem(String type, double amount, String itemDesc) {
		super();
		this.type = type;
		this.amount = amount;
		this.itemDesc = itemDesc;
	}


	public ExpenseItem(int expId, String type, double amount, String itemDesc) {
		super();
		this.expId = expId;
		this.type = type;
		this.amount = amount;
		this.itemDesc = itemDesc;
	}


	public ExpenseItem(int itemId, int expId, String type, double amount,
			String itemDesc, Expense expense) {
		super();
		this.itemId = itemId;
		this.expId = expId;
		this.type = type;
		this.amount = amount;
		this.itemDesc = itemDesc;
		this.expense = expense;
	}


	


	public int getItemId() {
		return itemId;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	public int getExpId() {
		return expId;
	}


	public void setExpId(int expId) {
		this.expId = expId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getItemDesc() {
		return itemDesc;
	}


	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}


	public Expense getExpense() {
		return expense;
	}


	public void setExpense(Expense expense) {
		this.expense = expense;
	}


	@Override
	public String toString() {
		return "ExpenseItem [itemId=" + itemId + ", expId=" + expId + ", type="
				+ type + ", amount=" + amount + ", itemDesc=" + itemDesc
				+ ", expense=" + expense + "]";
	}
	
	
	
}
