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
        <li><a href="/index/redindex.action"><h2>退出</h2></a></li>
    </ul>
</div>
<div style="position:absolute;top:100px;left:20%;height:600px;">
    <table >
        <thead>
        <tr>
            <th>图书名</th>
            <th>图片</th>
            <th>目录号</th>
            <th>出版社</th>
            <th>总数量</th>
            <th>已借阅数</th>
            <th>剩余数</th>
            <th>操作1</th>
            <th>操作2</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bookList}" var="books" varStatus="status">
            <tr>
                <input type="hidden" value="${books.bid}"  name="bid"/>
                <td>${books.bname}</td>
                <td><img src="/upload/${books.pic}"></td>
                <td><a href="/cat/queryListByCid.action?cid=${books.cid}">${books.cid}</a> </td>
                <td>${books.publishing}</td>
                <td>${books.total}</td>
                <td>${books.borrow}</td>
                <td>${books.residue}</td>
                <td><a href="/books/queryBookBybid.action?bid=${books.bid}">修改</a> </td>
                <td><a href="/books/deleteBookById.action?bid=${books.bid}">删除</a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/books/redAddBook.action">新增</a>
</div>
</body>
</html>
