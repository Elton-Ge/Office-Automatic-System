<%--
  Created by IntelliJ IDEA.
  User: elton
  Date: 25/5/20
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript">
        $(function(){
            //给按钮1绑定单击事件，实现签到功能
            $("#signin").click(function(){
                //发送一个Ajax请求，完成签到，并通过回调函数显示签到的结果
                $.ajax({
                    url:"servlet/DutyServlet?method=signin",
                    type:"POST",
                    dataType:"text",
                    success:function(data){ //0 1  2
                        //显示签到的结果
                        if(data==0){
                            $("#result").html("签到失败");
                        }else if(data ==1){
                            $("#result").html("签到成功");
                        }else {
                            $("#result").html("已经签到，不能重复签到");
                        }
                    }
                });
            });

            //给按钮2绑定单击事件，实现签退的功能
            $("#signout").click(function(){
                $.ajax({
                    url:"servlet/DutyServlet?method=signout",
                    success:function(result){
                        $("#result").html(result);
                    }

                });
            });

        });
    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">考勤管理</a></li>
        <li><a href="#">签到签退</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>

    <ul class="forminfo">
        <li><label>&nbsp;</label><input name="" type="button" class="btn" value="签到" id="signin"/> 每天签到一次，不可重复签到</li>
        <li><label>&nbsp;</label></li>
        <li><label>&nbsp;</label></li>
        <li><label>&nbsp;</label><input name="" type="button" class="btn" value="签退" id="signout"/>可重复签退，以最后一次签退为准</li>
    </ul>

</div>
<div id="result"></div>

</body>

</html>
