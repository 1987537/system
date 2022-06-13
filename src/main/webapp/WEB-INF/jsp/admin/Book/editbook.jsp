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
    <h1>欢迎你，${user.username}</h1>
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
    <form action="/books/updateBookById.action" method="post" enctype="multipart/form-data">
        <input type="hidden" name="bid" value="${book.bid}"><br>
        图书名：<input type="text" name="bname" value="${book.bname}"><br>
        出版社：<input type="text" name="publishing" value="${book.publishing}"><br>
        总数量：<input type="text" name="total" value="${book.total}"><br>
        图片：<input type="file" name="items_pic">
        <img src="/upload/${book.pic}" width="200px"><br>
        类型：
        <select name="cid">
            <c:forEach items="${catalogueList}" var="cats" varStatus="status">
                <option value="${cats.cid}">${cats.cname}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="borrow" value="${book.borrow}">
        <input type="submit">
    </form>
</div>
</body>
</html>
