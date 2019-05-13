<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="style/shopping.css">
    <link rel="stylesheet" href="style/bootstrap.min.css">
    <script src="style/jquery-3.3.1.min.js"></script>
    <title>Quick Shopping 后台管理系统</title>
</head>
<body >
<div style="background: url('/style/admin/images/login-bg.png');position: fixed; right:0; top:0; left:0; bottom: 0; color: white">
    <div  style="border:1px rgb(79,171,192) solid;margin: 0 auto; margin-top: 10%; text-align: center ;width: 460px; background: rgb(79,171,192);">
        <h2>Quick Shopping 后台管理系统</h2>
        <br/>

        <%--<label for="exampleInputFile"></label>--%>
        工&nbsp;&nbsp;&nbsp;&nbsp;号：<input type="text"  id="personId" name="personId" placeholder="请输入工号" style="width: 260px;color: black"/>

        <br/><br/><br/>
        密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" id="adminPassword" name="adminPassword" placeholder="请输入密码" style="width: 260px;color: black"/>
        <br/><br/><br/>

        <div class="form-group">
            验证码：<input type="text" id="checkcode" style="width: 100px;color: black"/> <img alt="验证码" id="imagecode" src="person/code" style="margin-left: 20px"/>
            <a onclick="reCreateCode()" style="color: white">看不清楚</a><br>
        </div>
        <span id="login_error" style="color: #FF0000;font-size:15px"></span><br/>
        <div style="margin: 0 auto; margin-bottom:10px; width: 200px;text-align: center">
            <button class="btn btn-default" onclick="login()">登陆</button>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button class="btn btn-default">重置</button>
        </div>
    </div>
</div>
<script type="text/javascript">
    //重新生成验证码
    function reCreateCode() {
        $("#imagecode").attr("src", "person/code?flag=" + Math.random());
    }

    //验证管理员登陆
    function login() {
        if ($("#personId").val() === "") {
            $("#login_error").html("工号不能为空");
        } else if ($("#adminPassword").val() === "") {
            $("#login_error").html("密码不能为空");
        } else if ($("#checkcode").val() === "") {
            $("#login_error").html("验证码不能为空");
        } else {
            $.ajax({
                type: "POST",
                url: "person/checkUserLogin",
                data: {
                    "personId": $("#personId").val(),
                    "password": $("#adminPassword").val(),
                    "checkcode": $("#checkcode").val(),
                },
                dataType: "JSON",
                success: function (data) {
                    if (data.success === "success") {
                        alert("登陆成功");
                        window.location = "person/index";
                    }
                    else {
                        alert(data.info);
                    }
                },
                error: function (XMLHttpRequest) {
                    alert(XMLHttpRequest.status);
                    alert("系统错误");
                }
            });

        }
    }
</script>
</body>
</html>