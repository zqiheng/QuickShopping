<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="style/admin/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="style/admin/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="style/admin/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="style/admin/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="style/admin/static/h-ui.admin/css/style.css"/>
    <link rel="stylesheet" href="style/ordersystem.css">
    <!--[if IE 6]>
    <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>配送列表</title>
</head>
<body>
<article class="page-container">
    <div class="form form-horizontal" id="form-member-add">

        <div class="row cl">
            <div class="col-xs-1 col-sm-6" id="productID"> <b>订单编号：${ordersInfo.ordersId} </b></div>
        </div>
        <div class="row cl">
            <div class="col-xs-1 col-sm-6"> <b>购买时间：${ordersInfo.ordersCreateTime} </b></div>
        </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                <tr class="text-c">
                    <th width="120">商品条码</th>
                    <th width="160">商品名称</th>
                    <th width="80">商品图片</th>
                    <th width="40">商品类型</th>
                    <th width="40">类型名称</th>
                    <th width="40">数量</th>
                    <th width="40" style="color: red">确认</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ordersInfo.orderDetailsInfoList}" var="product">
                    <tr class="text-c va-m">
                        <td>${product.productInfo.productID }</td>
                        <td>${product.productInfo.productName }</td>
                        <td><img width="60" class="product-thumb" src="${product.productInfo.productPicture }"></td>
                        <td>${product.productInfo.productType }</td>
                        <td>${product.productInfo.productTypeName }</td>
                        <td>${product.productNum }</td>
                        <td><input name="" type="checkbox" value=""></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>


        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
                &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
                &nbsp; &nbsp; &nbsp;&nbsp;
                <button class="btn btn-primary radius" onclick="confirmDelivery()">配送</button>
            </div>
        </div>
    </div>

</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="style/admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="style/admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="style/admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="style/admin/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="style/admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="style/admin/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="style/admin/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">

    $(function () {
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });
    });

    // 配送订单确认
    function confirmDelivery() {
        layer.confirm('确认配送该订单？', function (index) {
            $.ajax({
                data: {
                    ordersID: '${ordersInfo.ordersId}',
                },
                type: "POST",
                url: "web/orders/confirm_delivery",
                dataType: "JSON",
                success: function (res) {
                    if (res.success === "0") {
                        alert(res.info);
                        window.parent.location.reload(); //刷新父页面
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.close(index);  // 关闭layer
                    } else {
                        alert(res.info);
                    }
                },
                error: function (res) {
                    alert("系统错误,请稍后再试！");
                },
            });
        });
    }
</script>
</body>
</html>