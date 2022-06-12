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
        <li><a href="/users/queryOneUserByUid.action?uid=${uid}"><h2>个人信息</h2></a> </li>
        <li><a href="/books/userqueryListBook.action?uid=${uid}"><h2>图书列表</h2></a> </li>
        <li><a href="/cat/userqueryListCat.action?uid=${uid}"><h2>目录列表</h2></a> </li>
        <li><a href="/borrow/queryBorrowByUid.action?uid=${uid}"><h2>借阅信息</h2></a> </li>
        <li><a href="/borrow/queryTop.action?uid=${uid}"><h2>借阅排行榜</h2></a> </li>
        <li><a href="/index/redindex.action"><h2>退出</h2></a></li>
    </ul>
</div>
<div style="position:absolute;top:100px;left:20%;height:600px;">
    <table border="1" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th>书名</th>
            <th>借出时间</th>
            <th>归还时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${borrowBookList}" var="borrows" varStatus="status">

            <tr >
                <td >${borrows.book.bname}</td>
                <td><fmt:formatDate value="${borrows.loandate}" pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${borrows.returndate}" pattern="yyyy-MM-dd"/></td>
                <td><c:if test="${borrows.returndate==null}">
                    <form action="/borrow/returnBook.action" method="post">
                        <input type="hidden" name="bid" value="${borrows.bid}">
                        <input type="hidden" name="uid" value="${borrows.uid}">
                        <input type="submit" value="归还">
                    </form>
                </c:if></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
