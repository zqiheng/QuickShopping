<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>Title</title>
    <!-- 引入 ECharts 文件 -->
    <script src="/style/echarts/echarts.min.js"></script>
    <style type="text/css">
        .main{
            display: table;
            width: 100%;
            height:200px;
            table-layout: fixed;
            border-spacing: 40px;
            /*padding: 10px 40px 10px 40px;*/
            /*border: 1px red solid;*/
        }

        .main .child{
            display: table-cell;
            border-radius: 5px;
            color: white;
            /*border: 1px blueviolet solid;*/
        }

        .main .child .detail{
            margin-left: 50px;
        }
    </style>
</head>
<body>

    <div class="main">
        <div id="n1" class="child" style="background: rgb(250,119,89)">
            <div class="detail">
                <h3>总销售（周）</h3>
                <span>¥</span>&nbsp;&nbsp;15000
                <p>Incresed by 20%</p>
            </div>
        </div>
        <div id="n2" class="child" style="background: rgb(54,170,275)">
            <div class="detail">
                <h3>总销量（周）</h3>
                15000
                <p>Incresed by 50%</p>
            </div>
        </div>
        <div id="n3" class="child" style="background: rgb(171,210,82)">
            <div class="detail">
                <h3>总订单（周）</h3>
                15000
                <p>Incresed by 30%</p>
            </div>
        </div>
    </div>

    <div class="row" style="height:300px; padding: 0px 40px 10px 40px">
        <div id="day-sale-num" style="width: 350px;height:300px;float: left"></div>
        <div id="day-sale-money" style="width: 350px;height:300px;float: right"></div>
    </div>


    <%-- JS代码 --%>
    <script type="text/javascript">
        // 日销量数据统计图
        var daySaleNum = echarts.init(document.getElementById('day-sale-num'));
        // 指定图表的配置项和数据
        var option1 = {
            title: {
                text: '日销量'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data:['成都大学店','春熙路店']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine:{
                    lineStyle:{
                        color:'#000',
                    }
                },
                data: ['周一','周二','周三','周四','周五','周六','周日']
            },
            yAxis: {
                type: 'value',
                axisLine:{
                    lineStyle:{
                        color:'#000',
                    }
                },
            },
            series: [
                {
                    type:'line',
                    symbol:'none',
                    color: ["#db1818"],
                    smooth: 0.2,
                    stack: '总量',
                    name:'成都大学店',
                    data:[120, 132, 101, 134, 90, 230, 210]
                },
                {
                    name:'春熙路店',
                    type:'line',
                    symbol:'none',
                    color: ["#6c93db"],
                    smooth: 0.2,
                    stack: '总量',
                    data:[450, 600, 530, 720, 388, 876, 746]
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        daySaleNum.setOption(option1);

        // 日销售额统计图
        var daySaleMoney = echarts.init(document.getElementById("day-sale-money"))
        // 指定图表的配置项和数据
        var option2 = {
            title: {
                text: '销售额'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data:['成都大学店','春熙路店']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine:{
                    lineStyle:{
                        color:'#000',
                    }
                },
                data: ['周一','周二','周三','周四','周五','周六','周日']
            },
            yAxis: {
                type: 'value',
                axisLine:{
                    lineStyle:{
                        color:'#000',
                    }
                },
            },
            series: [
                {
                    name:'成都大学店',
                    type:'line',
                    symbol:'none',
                    color: ["#db1818"],
                    smooth: 0.2,
                    stack: '总量',
                    data:[120, 132, 101, 134, 90, 230, 210]
                },
                {
                    name:'春熙路店',
                    type:'line',
                    symbol:'none',
                    color: ["#6c93db"],
                    smooth: 0.2,
                    stack: '总量',
                    data:[450, 600, 530, 720, 388, 876, 746]
                }
            ]
        };
        daySaleMoney.setOption(option2);
    </script>
</body>
</html>
