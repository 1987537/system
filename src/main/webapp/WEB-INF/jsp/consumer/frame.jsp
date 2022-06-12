﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>图书借阅管理系统</title>
<style>
 body{
	 background-size: cover;
	 background-color:#d3fdff;
 }
</style>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<div style="position:absolute;top:0px;left:0px;width:100%;height:100px;background-color:lightblue">
	<h1>欢迎你，${user.username}</h1>
</div>
<div style="position:absolute;top:100px;left:0px;width:20%;height:600px;background-color:powderblue">
	<ul>
		<li><a href="/users/queryOneUserByUid.action?uid=${uid}"><h2>个人信息</h2></a> </li>
		<li><a href="/books/userqueryListBook.action?uid=${uid}"><h2>图书列表</h2></a> </li>
		<li><a href="/cat/userqueryListCat.action?uid=${uid}"><h2>目录列表</h2></a> </li>
		<li><a href="/borrow/queryBorrowByUid.action?uid=${uid}"><h2>借阅信息</h2></a> </li>
		<li><a href="/borrow/queryTop.action?uid=${uid}"><h2>借阅排行榜</h2></a> </li>
		<li><a href="/index/redindex.action"><h2>退出</h2></a></li>
	</ul>
</div>
<div style="position:absolute;top:100px;left:20%;height:600px;">
	<h2>欢迎使用图书借阅管理系统</h2>
</div>
</body>
</html>