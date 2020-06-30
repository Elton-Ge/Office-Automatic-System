package com.bjsxt.dao;

import com.bjsxt.entity.ExpenseItem;

public interface ExpenseItemDao {
	/**
	 * 保存报销单明细
	 * @param expenseItem
	 */
	void save(ExpenseItem expenseItem);

}
