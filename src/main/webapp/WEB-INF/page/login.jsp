<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="GBK">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/mdl/material.min.css">
    <link rel = "stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/login.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto:100" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/script/jquery-3.3.1.min.js"></script>
    <script src = "${pageContext.request.contextPath}/style/mdl/material.min.js"></script>
</head>
<body class="tm_login_body">
<div class="tm_login_container">
    <div class="tm_login_form">
        <div class="tm_login_title">
            <table border="0" cellpadding="0" cellspacing="0" class="tm_login_title_table">
                <tr>
                    <th><img src="${pageContext.request.contextPath}/style/skins/images/logo_max.png" align="absmiddle" /></th>
                    <td><span>上海电力学院考勤系统</span></td>
                </tr>
            </table>
        </div>
        <form action="validate.mvc" method="post">
            <table border="0" cellpadding="5" cellspacing="0" class="tm_login_table">
                <tr>
                    <th>用户名</th>
                    <td><input type="text" class="mdl-textfield__input" name="username" maxlength="20" value="" style="width:255px" placeholder = "其实就是你的学号" path = "userName"/></td>
                </tr>
                <tr>
                    <th>密码</th>
                    <td><input type="password" class="mdl-textfield__input" name="password" maxlength="20" value="" style="width:255px" placeholder="别被人看到你的密码" path = "password"/></td>
                </tr>
                <c:if test = "${sessionScope.flag eq true}">
                    <tr>
                        <th></th>
                        <td><span style="font-family: 'Roboto', sans-serif; color: #ff3d00">密码或用户名输入错误</span></td>
                    </tr>
                </c:if>
                <tr>
                    <th>类型</th>
                    <td>
                        <select class="tm_select" name="userType" type="select">
                            <option value="0">学生</option>
                            <option value="1">老师</option>
                            <option value="2">管理员</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <div style="margin-top:10px">
                            <button id = "login" type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent" style="width:40%">登陆</button>
                            <button id = "register" type="button" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent" style="width:40%" onclick="alert('.......');">注册</button>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <div class="tm_login_foot">
        <div></div>
        <div>Copyright &copy; idiot.shen.com  All Right Reserved 2009-2018</div>
    </div>
</div>
</body>
</html>
