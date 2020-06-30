package com.bjsxt.dao.impl;

import com.bjsxt.dao.ExpenseItemDao;
import com.bjsxt.entity.ExpenseItem;
import com.bjsxt.util.DBUtil;

public class ExpenseItemDaoImpl implements ExpenseItemDao {

	@Override
	public void save(ExpenseItem item) {
		String sql = "insert into expenseitem values(seq_item.nextval,?,?,?,?);";
		Object [] params ={item.getExpId(),item.getType(),item.getAmount(),item.getItemDesc()};
		DBUtil.executeUpdate(sql, params);

	}

}
