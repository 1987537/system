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
    <script src="js/echarts.min.js"></script>
    <div id="container" style="height: 100%;width: 400%"></div>


    <script type="text/javascript" src="https://fastly.jsdelivr.net/npm/echarts@5/dist/echarts.min.js"></script>
    <script type="text/javascript">
        var dom = document.getElementById('container');
        var myChart = echarts.init(dom, null, {
            renderer: 'canvas',
            useDirtyRect: false
        });
        var app = {};

        var option;

        option = {
            xAxis: {
                type: 'category',
                data: ['${name[0]}', '${name[1]}', '${name[2]}']
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    data: [${balance[0]},${balance[1]},${balance[2]}],
                    type: 'bar'
                }
            ]
        };

        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }

        window.addEventListener('resize', myChart.resize);
    </script>
</div>
</body>
</html>
