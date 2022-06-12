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
        <li><a href="/index/redindex.action"><h2>退出</h2></a></li>
    </ul>
</div>
<div style="position:absolute;top:100px;left:20%;height:600px;">
    <%

        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");//定义时间格式，不想要时分秒的话，也可以只是yyyy-MM-dd

        java.util.Date currentTime = new java.util.Date();//获取当前系统时间

        String str1 = formatter.format(currentTime); //将当前时间按格式转化为字符串


    %>
    <form action="/borrow/queryListByTimeBorrow.action" method="post">
        开始查询时间:<input type="date" name="startIndex" max="<%=str1%>">
        结束查询时间:<input type="date" name="endIndex" max="<%=str1%>">
        <input type="submit" value="查询">
    </form>
    <h3>近一个月借书记录以及未及时归还书籍列表</h3>
    <table border="1" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th>书名</th>
            <th>借书用户编号</th>
            <th>借出时间</th>
            <th>归还时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${borrowBookList}" var="borrows" varStatus="status">
            <tr >
                <td >${borrows.book.bname}</td>
                <td><a href="/users/queryUserByUid.action?uid=${borrows.uid}">${borrows.uid}</a> </td>
                <td><fmt:formatDate value="${borrows.loandate}" pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${borrows.returndate}" pattern="yyyy-MM-dd"/>
                    <c:if test="${borrows.returndate==null}">
                        未归还
                    </c:if></td>
            </tr>
        </c:forEach>
        <c:forEach items="${borrowBookListsuper}" var="borrows" varStatus="status">
            <tr >
                <td >${borrows.book.bname}</td>
                <td><a href="/users/queryUserByUid.action?uid=${borrows.uid}" style="color: red">${borrows.uid}</a> </td>
                <td><fmt:formatDate value="${borrows.loandate}" pattern="yyyy-MM-dd"/></td>
                <td>已超时，未归还</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
