package com.bjsxt.servlet;

import com.bjsxt.entity.Department;
import com.bjsxt.entity.Employee;
import com.bjsxt.entity.Position;
import com.bjsxt.service.DepartmentService;
import com.bjsxt.service.EmployeeService;
import com.bjsxt.service.impl.DepartmentServiceImpl;
import com.bjsxt.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 21/5/20
 * @Description: ${PACKAGE_NAME}
 * @version: 1.0
 */

public class EmployeeServlet extends BaseServlet {
    /**
     * 退出操作
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //销毁session
        request.getSession().invalidate();
        //退出，重定向到login。jsp
        
        response.sendRedirect(request.getContextPath()+"/login.jsp");

    }

    /**
     * 登录操作
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          //获取登录信息
        String empId = request.getParameter("empId");
        String password = request.getParameter("password");
           //获取输入验证码
      /*  String verifyCode = request.getParameter("verifyCode");
        //获取网站验证码
        String randStr = (String) request.getSession().getAttribute("randStr");
        if(verifyCode==null || !verifyCode.equals(randStr)){
            request.setAttribute("error","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
             return; //return不可少
        }*/

        //调用业务层处理
        EmployeeService employeeService=new EmployeeServiceImpl();
        Employee emp =employeeService.login(empId,password);
        //跳转
        if(emp!=null){
            request.getSession().setAttribute("emp",emp);
           response.sendRedirect(request.getContextPath()+"/main.html");
        }    else {
                request.setAttribute("error","用户名或者密码不对");
                request.getRequestDispatcher("/login.jsp").forward(request,response);

        }

    }

    /**
     * 预更新操作
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void toUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //接收要修改的员工的编号
        String empId = request.getParameter("empId");

        //调用业务层获取该员工的信息
        EmployeeService empService = new EmployeeServiceImpl();
        Employee emp = empService.findById(empId);
        request.setAttribute("emp", emp);

        //获取所有的部门信息
        DepartmentService deptService = new DepartmentServiceImpl();
        List<Department> deptList = deptService.findAll();
        request.setAttribute("deptList", deptList);
        //获取所有的岗位信息


        //获取上级员工
        List<Employee> mgrList = empService.findEmpByType(2);//1  基层员工  2 各级管理人员
        request.setAttribute("mgrList",mgrList);

        //页面跳转 system/empUpdate.jsp
        request.getRequestDispatcher("/system/empUpdate.jsp").forward(request, response);
    }


    public void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取员工的信息
        String empId = request.getParameter("empId");
        String password ="123456";
        String realName = request.getParameter("realName");
        String sex = request.getParameter("sex");
        //日期类型的处理
        String sbirthDate = request.getParameter("birthDate");
        String shireDate = request.getParameter("hireDate");
        String sleaveDate = request.getParameter("leaveDate");

        Date birthDate= null,hireDate = null,leaveDate = null;
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            birthDate = sdf.parse(sbirthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            hireDate = sdf.parse(shireDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            leaveDate = sdf.parse(sleaveDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //整数的处理
        int onDuty = Integer.parseInt(request.getParameter("onDuty"));
        int empType = Integer.parseInt(request.getParameter("empType"));

        String phone = request.getParameter("phone");
        String qq = request.getParameter("qq");
        String emerContactPerson = request.getParameter("emerContactPerson");
        String idCard = request.getParameter("idCard");

        //对象的处理
        int deptno = Integer.parseInt(request.getParameter("deptno"));
        Department dept = new Department();
        dept.setDeptno(deptno);

        int posId = Integer.parseInt(request.getParameter("posId"));
        Position position = new Position();
        position.setPosId(posId);
        String mgrId = request.getParameter("mgrId");
        Employee mgr = new Employee();
        mgr.setEmpId(mgrId);//!!!

        //调用业务层完成添加操作
        Employee emp = new Employee(empId, password, realName, sex, birthDate, hireDate, leaveDate, onDuty, empType, phone, qq, emerContactPerson, idCard, dept, position, mgr);
        EmployeeService  empService = new EmployeeServiceImpl();
        int n = empService.update(emp);

        //根据结果进行页面跳转
        if(n>0){
            response.sendRedirect(request.getContextPath()+"/servlet/EmployeeServlet?method=findEmp");
            //request.getRequestDispatcher("/servlet/EmployeeServlet?method=findEmp").forward(request, response);
        }else{
            request.setAttribute("error", "更新员工失败");
            request.getRequestDispatcher("/system/empUpdate.jsp").forward(request, response);
        }

    }


    public  void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受要删除到数据
        String empId = request.getParameter("empId2");
        //调用业务层完成添加操作
        EmployeeService employeeService= new EmployeeServiceImpl();
        employeeService.delete(empId);
//        System.out.println(number);
        //响应，跳转到servlet/departmentservlet 。 不能直接跳到deptlist。jsp。 因为它只负责显示，所以仍需要先查询再显示
        request.getRequestDispatcher("/servlet/EmployeeServlet?method=findEmp").forward(request,response);
    }

    public void findEmp(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //接受页面数据
        String empId = request.getParameter("empId");
        int deptno=0;
        String sdeptno=  request.getParameter("deptno");    //maybe null
        try {
            deptno = Integer.parseInt(sdeptno);

        }   catch (  NumberFormatException  e){
            e.printStackTrace();

        }

        int onDuty=1;
        String sonDuty=  request.getParameter("onDuty");    //maybe null
        try {
            onDuty = Integer.parseInt(sonDuty);

        }   catch (  NumberFormatException  e){
            e.printStackTrace();

        }
        String shireDate = request.getParameter("hireDate");
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date hireDate=null;
        if(shireDate!=null&&!"".equals(shireDate)){
            try {
                hireDate = simpleDateFormat.parse(shireDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //调用业务层获取指定的员工信息
        EmployeeService  empService = new EmployeeServiceImpl();
//        List<Employee> empList = empService.findAll();
        List<Employee>  empList=empService.findEmp(empId,deptno,onDuty,hireDate) ;
        request.setAttribute("empList", empList);
        //    调用业务层获取所有的部门信息
        DepartmentService departmentService=new DepartmentServiceImpl();
        List<Department> deptList = departmentService.findAll();
        request.setAttribute("deptList",deptList);
        //跳转到system/empList.jsp
        /**
         *  显示输入的条件
         */
        request.setAttribute("empId",empId);
        request.setAttribute("deptno",deptno);
        request.setAttribute("onDuty",onDuty);
        request.setAttribute("hireDate",shireDate);
        request.getRequestDispatcher("/system/empList.jsp").forward(request, response);

    }
    public void findAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //调用业务层获取所有的员工信息
        EmployeeService  empService = new EmployeeServiceImpl();
        List<Employee> empList = empService.findAll();
        request.setAttribute("empList", empList);
        //    调用业务层获取所有的部门信息
        DepartmentService departmentService=new DepartmentServiceImpl();
        List<Department> deptList = departmentService.findAll();
        request.setAttribute("deptList",deptList);
        //跳转到system/empList.jsp
        request.getRequestDispatcher("/system/empList.jsp").forward(request, response);

    }

        public void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               //获取所有部门的信息
        DepartmentService departmentService=new DepartmentServiceImpl();
        List<Department> deptList = departmentService.findAll();
        request.setAttribute("deptList",deptList);

        //获取所有岗位的信息

                // 获取所有上级
        EmployeeService employeeService=new EmployeeServiceImpl();
        List<Employee> mgrList= employeeService.findEmpByType(2);
        request.setAttribute("mgrList",mgrList);

                //转发给system/empadd。jsp
        request.getRequestDispatcher("/system/empAdd.jsp").forward(request,response);

    }
        public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受视图层数据-获取员工信息
        String empId = request.getParameter("empId");
        String password ="123456";
        String realName = request.getParameter("realName");
        String sex = request.getParameter("sex");
        //日期类型的处理
        String sbirthDate = request.getParameter("birthDate");
        String shireDate = request.getParameter("hireDate");
        String sleaveDate = request.getParameter("leaveDate");

        Date birthDate= null,hireDate = null,leaveDate = null;
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            birthDate = sdf.parse(sbirthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            hireDate = sdf.parse(shireDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            leaveDate = sdf.parse(sleaveDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //整数的处理
        int onDuty = Integer.parseInt(request.getParameter("onDuty"));
        int empType = Integer.parseInt(request.getParameter("empType"));

        String phone = request.getParameter("phone");
        String qq = request.getParameter("qq");
        String emerContactPerson = request.getParameter("emerContactPerson");
        String idCard = request.getParameter("idCard");

        //对象的处理
        int deptno = Integer.parseInt(request.getParameter("deptno"));
        Department dept = new Department();
        dept.setDeptno(deptno);

        int posId = Integer.parseInt(request.getParameter("posId"));
        Position position = new Position();
        position.setPosId(posId);
        String mgrId = request.getParameter("mgrId");
        Employee mgr = new Employee();
        mgr.setEmpId(mgrId);//!!!
        //调用业务层完成添加操作
        EmployeeService employeeService= new EmployeeServiceImpl();
        Employee employee = new Employee(empId, password, realName, sex, birthDate, hireDate, leaveDate, onDuty, empType, phone, qq, emerContactPerson, idCard, dept, position, mgr);
        int n= employeeService.save(employee);
        // 根据结果进行页面跳转

        if(n>0){
//            response.sendRedirect(request.getContextPath()+"/empList.html");
            response.sendRedirect(request.getContextPath()+"/servlet/EmployeeServlet?method=findEmp");

        }   else {
            request.setAttribute("error","添加更新失败");
            request.getRequestDispatcher("/system/empAdd.jsp").forward(request,response);
        }
    }
}
