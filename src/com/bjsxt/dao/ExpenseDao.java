package com.bjsxt.dao;

import com.bjsxt.entity.Expense;

public interface ExpenseDao {
	/**
	 * 获取序列的下一个值
	 * @return
	 */
	int nextVal();

	/**
	 * 保存报销单
	 * @param expense
	 */
	void save(Expense expense);

}
