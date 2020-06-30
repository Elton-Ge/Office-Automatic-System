package com.bjsxt.servlet;


import com.bjsxt.entity.Expense;
import com.bjsxt.entity.ExpenseItem;
import com.bjsxt.service.ExpenseService;
import com.bjsxt.service.impl.ExpenseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @Auther: Elton Ge
 * @Date: 26/5/20
 * @Description: ${PACKAGE_NAME}
 * @version: 1.0
 */
@WebServlet(name = "ExpenseServlet")
public class ExpenseServlet extends BaseServlet {

    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //接收表单的数据
        String typeArr [] = request.getParameterValues("type");
        String amountArr [] = request.getParameterValues("amount");
        String itemDescArr [] = request.getParameterValues("itemDesc");

        List<ExpenseItem> itemList = new ArrayList<ExpenseItem>();
        double totalAmount = 0;
        for(int i=0;i<typeArr.length;i++){
            String type = typeArr[i];
            double amount = Double.parseDouble(amountArr[i]);
            String itemDesc = itemDescArr[i];
            ExpenseItem item = new ExpenseItem(type, amount, itemDesc);
            itemList.add(item);
            totalAmount += amount;
            System.out.println(type+" "+ amount +" "+ itemDesc);
        }

        String empId = request.getParameter("empId");
        Date expTime = new Date();
        String expDesc = request.getParameter("expDesc");
        String nextAuditorId = request.getParameter("nextAuditorId");
        String lastResult ="新创建的";
        String status = "0";//新创建
        Expense expense = new Expense(empId, totalAmount, expTime, expDesc, nextAuditorId, lastResult, status);
        expense.setItemList(itemList);
        //调用业务层完成添加操作
        ExpenseService expService  = new ExpenseServiceImpl();
        try{
            expService.add(expense);
            //成功页面跳转
            response.sendRedirect(request.getContextPath()+"/myExpense.html"); //???
        }catch(Exception e){
            e.printStackTrace();
            //失败 页面跳转
            request.setAttribute("error", "添加报销单失败");
            request.getRequestDispatcher("/expense/expenseAdd.jsp").forward(request, response);

        }

        //页面跳转
    }
}
