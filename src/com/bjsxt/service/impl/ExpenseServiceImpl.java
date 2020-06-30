package com.bjsxt.service.impl;

import java.util.List;

import com.bjsxt.dao.ExpenseDao;
import com.bjsxt.dao.ExpenseItemDao;
import com.bjsxt.dao.impl.ExpenseDaoImpl;
import com.bjsxt.dao.impl.ExpenseItemDaoImpl;
import com.bjsxt.entity.Expense;
import com.bjsxt.entity.ExpenseItem;
import com.bjsxt.service.ExpenseService;

public class ExpenseServiceImpl implements ExpenseService{
	/**
	 * 问题：如何知道报销单的编号（自增），因为报销单明细需要
	 * 
	 * 
	 */
	@Override
	public void add(Expense expense) {
		   //获取序列下一个自增值
	    ExpenseDao expenseDao=new ExpenseDaoImpl();
	    int expId=expenseDao.nextVal();
		// 添加一条报销单（主单）
		expense.setExpId(expId);
		expenseDao.save(expense);

		//添加报销单所属的多条报销单明细
		ExpenseItemDao expenseItemDao= new ExpenseItemDaoImpl();
		List<ExpenseItem> itemList = expense.getItemList();
		for (int i = 0; i <itemList.size() ; i++) {
			ExpenseItem expenseItem = itemList.get(i);
			expenseItem.setExpId(expId);
			expenseItemDao.save(expenseItem);

		}
		
		
	}

}
