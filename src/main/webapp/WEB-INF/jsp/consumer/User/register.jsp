<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>系统注册 - 图书借阅管理系统</title>
    <link type="text/css" rel="stylesheet" href="../../css/style.css" />
</head>
<body class="blue-style">
<div id="login">
    <div class="icon"></div>
    <div class="login-box">
        <form  action="/users/addUser.action"  name="actionForm" id="actionForm"  method="post" >
            <dl>
                <dt>用户名：</dt>
                <dd><input type="text" class="input-text" name="username"/></dd>
                <dt>密　码：</dt>
                <dd><input type="password"  class="input-text" name="password"/></dd>
                <dt>电话：</dt>
                <dd><input type="text"  class="input-text" name="phone"/></dd>
                <dt>验证码：</dt>
                <dd><input type="text"  class="input-text" name="code"/>
                <img src="/users/checkCode.action" width="100" height="32" class="passcode"  onclick="this.src=this.src+'?'"></dd>
            </dl>
            <input type="submit" value="注册">
        </form>
    </div>
</div>
</body>
</html>