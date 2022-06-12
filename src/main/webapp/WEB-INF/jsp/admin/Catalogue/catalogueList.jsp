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
<h2>图书列表</h2>
<!--<form action="/goods/editGoodsList" method="post">-->
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
        <li><a href="/index/redindex.action"><h2>退出</h2></a></li>
    </ul>
</div>

<div style="position:absolute;top:100px;left:20%;height:600px;">
    <table border="1" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th>类别</th>
            <th>查询书目</th>
            <th>修改</th>
            <th>删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${catalogueList}" var="cat" varStatus="status">
            <tr>
                <input type="hidden" value="${cat.cid}"  name="cid"/>
                <td>${cat.cname}</td>
                <td><a href="/cat/queryListByCid.action?cid=${cat.cid}">查询书目</a></td>
                <td><a href="/cat/queryCatByCid.action?cid=${cat.cid}">修</a> </td>
                <td><a href="/cat/deleteCatById.action?cid=${cat.cid}">删</a> </td>
            </tr>
        </c:forEach>
        <td><a href="/cat/redAdd.action">增加</a></td>
        </tbody>
    </table>
</div>
</body>
</html>
