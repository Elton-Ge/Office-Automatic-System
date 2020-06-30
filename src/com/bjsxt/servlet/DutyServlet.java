/**
 * @Auther: Elton Ge
 * @Date: 25/5/20
 * @Description: ${PACKAGE_NAME}
 * @version: 1.0
 */
package com.bjsxt.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.entity.Department;
import com.bjsxt.entity.Duty;
import com.bjsxt.entity.Employee;
import com.bjsxt.service.DepartmentService;
import com.bjsxt.service.DutyService;
import com.bjsxt.service.impl.DepartmentServiceImpl;
import com.bjsxt.service.impl.DutyServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

public class DutyServlet extends BaseServlet {

    public void exportXls(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //获取三个查询条件
        String empId = request.getParameter("empId");
        String sdeptno = request.getParameter("deptno");//null  ""
        int deptno = 0;
        try{
            deptno = Integer.parseInt(sdeptno);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        String sdtDate = request.getParameter("dtDate");//234r1324  null
        java.sql.Date dtDate = null;
        try{
            dtDate = java.sql.Date.valueOf(sdtDate);
        }catch(RuntimeException e){
            e.printStackTrace();
        }

        //调用业务层完成查询操作
        DutyService dutyService = new DutyServiceImpl();
        List<Duty> dutyList = dutyService.findDuty(empId,deptno,dtDate);

        //返回outputStream流
          createExcel(dutyList,response);
    }


    private static void createExcel(List<Duty> list,HttpServletResponse response) {
        // 创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表
        HSSFSheet sheet = workbook.createSheet("考勤信息");

        CellRangeAddress region = new CellRangeAddress(0, // first row
                0, // last row
                0, // first column
                5 // last column
        );
        sheet.addMergedRegion(region);

        HSSFRow hssfRow = sheet.createRow(0);
        HSSFCell headCell = hssfRow.createCell(0);
        headCell.setCellValue("尚学堂考勤信息");

        // 设置单元格格式居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headCell.setCellStyle(cellStyle);


        // 添加表头行
        hssfRow = sheet.createRow(1);
        // 添加表头内容
        headCell = hssfRow.createCell(0);
        headCell.setCellValue("用户名");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(1);
        headCell.setCellValue("真实姓名");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(2);
        headCell.setCellValue("所属部门");
        headCell.setCellStyle(cellStyle);


        headCell = hssfRow.createCell(3);
        headCell.setCellValue("出勤日期");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(4);
        headCell.setCellValue("签到时间");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(5);
        headCell.setCellValue("签退时间");
        headCell.setCellStyle(cellStyle);

        // 添加数据内容
        for (int i = 0; i < list.size(); i++) {
            hssfRow = sheet.createRow((int) i + 2);
            Duty duty = list.get(i);

            // 创建单元格，并设置值
            HSSFCell cell = hssfRow.createCell(0);
            cell.setCellValue(duty.getEmp().getEmpId());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(1);
            cell.setCellValue(duty.getEmp().getRealName());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(2);
            cell.setCellValue(duty.getEmp().getDept().getDeptName());
            cell.setCellStyle(cellStyle);


            cell = hssfRow.createCell(3);
            cell.setCellValue(duty.getDtDate());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(4);
            cell.setCellValue(duty.getSigninTime());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(5);
            cell.setCellValue(duty.getSignoutTime());
            cell.setCellStyle(cellStyle);
        }

        // 保存Excel文件
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=duty.xls");//附件形式下载，文件名叫duty.xls
            //OutputStream outputStream = new FileOutputStream("D:/duty.xls");//保存到本地（服务器端）
            OutputStream outputStream = response.getOutputStream();	 //写到客户端
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void findDuty(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //获取三个查询条件
        String empId = request.getParameter("empId");
        String sdeptno = request.getParameter("deptno");//null  ""
        int deptno = 0;
        try{
            deptno = Integer.parseInt(sdeptno);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        String sdtDate = request.getParameter("dtDate");//234r1324  null
        java.sql.Date dtDate = null;
        try{
            dtDate = java.sql.Date.valueOf(sdtDate);
        }catch(RuntimeException e){
            e.printStackTrace();
        }

        //调用业务层完成查询操作
        DutyService dutyService = new DutyServiceImpl();
        List<Duty> dutyList = dutyService.findDuty(empId,deptno,dtDate);

        //返回json字符串
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //Gson gson = new Gson();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String jsonStr = gson.toJson(dutyList);
        out.println(jsonStr);


    }

    public void findAllDept(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //调用业务层获取所有的部门
        DepartmentService  deptService = new DepartmentServiceImpl();
        List<Department> deptList = deptService.findAll();

        //不需要页面跳转，直接返回内容
        //request.setAttribute("deptList", deptList);
        //request.getRequestDispatcher("/system/deptList.jsp").forward(request, response);

        response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		Gson gson = new Gson();
//		String jsonStr = gson.toJson(deptList);
//		out.println(jsonStr);
        response.getWriter().println(new Gson().toJson(deptList));

    }

    public void signout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取当前用户的empId
        Employee emp = (Employee)request.getSession().getAttribute("emp");
        String empId = emp.getEmpId();
        //调用业务层完成签退操作
        DutyService dutyService = new DutyServiceImpl();
        int n = dutyService.signout(empId); //1  成功  0 失败   2 没有签到

        //不需要页面跳转，直接返回内容
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if(n==1){
            out.println("签退成功");
        }else if(n==0){
            out.println("签退失败");
        }else{
            out.println("尚未签到");
        }
    }

    public void signin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //
        Employee emp = (Employee)request.getSession().getAttribute("emp");
        String empId = emp.getEmpId();
        //调用业务层完成签到操作
        DutyService dutyService = new DutyServiceImpl();
        int n = dutyService.signin(empId); //1  成功  0 失败   2 已经签到

        //不需要页面跳转，直接返回内容
        response.getWriter().println(n);
    }

}
