<%@ page language="java" contentType="text/html; charset=UTF-8"
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
    <h1>欢迎你</h1>
</div>
<div style="position:absolute;top:100px;left:0px;width:20%;height:600px;background-color:powderblue">
    <ul>
        <li><a href="/books/queryListBook.action"><h2>图书列表</h2></a> </li>
        <li><a href="/cat/queryListCat.action"><h2>目录列表</h2></a> </li>
        <li><a href="/borrow/queryAllBorrow.action"><h2>借阅信息</h2></a> </li>
        <li><a href="/borrow/queryTopAdmin.action"><h2>借阅排行榜</h2></a> </li>
        <li><a href="/users/queryAllUser.action"><h2>管理用户</h2></a> </li>
        <li><a href="/users/queryBalanceTop.action"><h2>用户余额排行榜</h2></a> </li>
        <li><a href="/index/redindex.action"><h2>退出</h2></a></li>
    </ul>
</div>
<div style="position:absolute;top:100px;left:20%;height:600px;">
    <table border="1" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th>用户名</th>
            <th>余额</th>
            <th>电话</th>
            <th>修改</th>
            <th>删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userList}" var="user" varStatus="status">
            <tr>
                <td><a href="/users/queryUserByUid.action?uid=${user.uid}">${user.username}</a></td>
                <td>${user.balance}</td>
                <td>${user.phone}</td>
                <td><a href="/users/toUpdate.action?uid=${user.uid}">修</a> </td>
                <td><c:if test="${user.identity==2}">
                    <a href="/users/deleteUserByUid.action?uid=${user.uid}">删</a>
                </c:if></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
