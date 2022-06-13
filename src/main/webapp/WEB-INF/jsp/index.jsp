<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统登录 - 图书借阅管理系统</title>
<link type="text/css" rel="stylesheet" href="../../css/style.css" />
</head>
<body class="blue-style">
<div id="login">
	<div class="icon"></div>
	<div class="login-box">
		<form  action="/users/queryUserByUsernameAndPwd.action"  name="actionForm" id="actionForm"  method="post" >
			<dl>
				<dt>用户名：</dt>
				<dd><input type="text" class="input-text" name="username"/></dd>
				<dt>密　码：</dt>
				<dd><input type="password"  class="input-text" name="password"/></dd>
			</dl>
			<div class="buttons">
				<input type="submit"  value="登录系统" class="input-button" />
				<input type="reset"  value="重　　填" class="input-button" />
				<label class="control-label" >没有账号请<a href="/users/toregister.action" style="color: red;">注册</a></label>
			</div>
		</form>
	</div>
</div>
</body>
</html>
