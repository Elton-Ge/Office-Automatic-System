package com.bjsxt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.entity.Department;
import com.bjsxt.service.DepartmentService;
import com.bjsxt.service.impl.DepartmentServiceImpl;

public class DepartmentServlet extends BaseServlet {

	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收视图层的表单数据
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		String deptName = request.getParameter("deptName");
		String location = request.getParameter("location");

		//调用业务层完成添加操作
		DepartmentService  deptService = new DepartmentServiceImpl();
		Department dept = new Department(deptno, deptName, location);
		int n = deptService.update(dept);
		System.out.println(n);

		//根据结果跳转到不同的页面
		if(n>0){
			//如果是表单的提交，成功之后建议使用重定向，避免表单的重复提交
			//request.getRequestDispatcher("/deptList.html").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/servlet/DepartmentServlet?method=findAll");
//			System.out.println(request.getContextPath());
		}else{
			request.setAttribute("error", "更新失败");
			//此时必须使用转发，因为要复用保存在request中的数据
			request.getRequestDispatcher("/system/deptUpdate.jsp").forward(request, response);

		}
	}


	public  void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接受要查询到数据
		int deptno= Integer.parseInt(request.getParameter("deptno"));
		//调用业务层完成添加操作
		DepartmentService departmentService=new DepartmentServiceImpl();
		Department department= departmentService.findById(deptno);
		//响应,显示到deptupdate。jsp
		request.setAttribute("dept",department);
		request.getRequestDispatcher("/system/deptUpdate.jsp").forward(request,response);
	}

	public  void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //接受要删除到数据
		int deptno= Integer.parseInt(request.getParameter("deptno"));
		//调用业务层完成添加操作
		DepartmentService departmentService=new DepartmentServiceImpl();
		int number= departmentService.delete(deptno);
		System.out.println(number);
		//响应，跳转到servlet/departmentservlet 。 不能直接跳到deptlist。jsp。 因为它只负责显示，所以仍需要先查询再显示
		request.getRequestDispatcher("/servlet/DepartmentServlet?method=findAll").forward(request,response);
	}

	public void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收视图层的表单数据
		

		//调用业务层完成添加操作
		DepartmentService  deptService = new DepartmentServiceImpl();
		List<Department> deptList= deptService.findAll();

		//查询结果跳转到 system/deptList。jsp
		 request.setAttribute("deptList",deptList);
		 request.getRequestDispatcher("/system/deptList.jsp").forward(request,response);
	}



	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收视图层的表单数据
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		String deptName = request.getParameter("deptName");
		String location = request.getParameter("location");
		
		//调用业务层完成添加操作
		DepartmentService  deptService = new DepartmentServiceImpl();
		Department dept = new Department(deptno, deptName, location);	
		int n = deptService.add(dept);
		
		//根据结果跳转到不同的页面
		if(n>0){
			//如果是表单的提交，成功之后建议使用重定向，避免表单的重复提交
			//request.getRequestDispatcher("/deptList.html").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/servlet/DepartmentServlet?method=findAll");
//			System.out.println(request.getContextPath());
		}else{
			request.setAttribute("error", "添加失败");
			//此时必须使用转发，因为要复用保存在request中的数据
			request.getRequestDispatcher("/system/deptAdd.jsp").forward(request, response);
			
		}
	}

	

}
