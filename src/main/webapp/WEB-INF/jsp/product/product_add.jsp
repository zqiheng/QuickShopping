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
    <title>新增店铺</title>
</head>
<body>
<article class="page-container">
    <div class="form form-horizontal" id="form-member-add">

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>所属店铺：</label>
            <div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
				<select name="aId2" class="select" id="shopObj">
					<option value="0">请选择添加商品的店铺</option>
					<c:forEach items="${shops}" var="shop">
                        <option value="${shop.id}"><c:out value="${shop.shopName }"/></option>
                    </c:forEach>
				</select>
				</span>
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品条码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请输入13位长度的编码" id="productID" name="productID">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="productName" name="productName">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品图片：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="商品图片地址" id="productPicture" name="productPicture">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品类型：</label>
            <div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
				<select name="aId2" class="select" id="productCategoryObj">
					<option value="0">请选择商品的类型</option>
					<c:forEach items="${productCategories}" var="productCategory">
                        <option value="${productCategory.id}"><c:out value="${productCategory.productTypeName }"/></option>
                    </c:forEach>
				</select>
				</span>
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>进货价格：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="productProposedPrice" name="productProposedPrice">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>出售价格：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="productRealPrice" name="productRealPrice">
            </div>
        </div>

        <%--<div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">活动价格：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="productActivityPrice" name="productActivityPrice">
            </div>
        </div>--%>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">商品品牌：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="productBrand" name="productBrand">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>制作公司：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="factoryName" name="factoryName">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">注册地址：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="registeredAddress" name="registeredAddress">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>产品规格：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="productNorm" name="productNorm">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">包装单位：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="productPackingUnit" name="productPackingUnit">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>库存数量：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="stockQuantity" name="stockQuantity">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">备注信息：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="productRemark" name="productRemark">
            </div>
        </div>

        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
                &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
                &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
                &nbsp; &nbsp;
                <button class="btn btn-primary radius" onclick="productAdd()">提交</button>
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

    /**
     * 添加店铺信息
     */
    function productAdd() {
        if ($("#shopObj").val() === "0") {
            alert("选择的店铺不能为空");
        } else if ($("#productID").val() === "") {
            alert("商品编码不能为空");
        } else if ($("#productID").val().length !== 13) {
            alert("商品编码只能13位");
        }else if ($("#productName").val() === "") {
            alert("商品名称不能为空");
        } else if ($("#productPicture").val() === "") {
            alert("图片地址不能为空");
        } else if ($("#productCategoryObj").val() === "0") {
            alert("商品类型不能为空");
        } else if ($("#productProposedPrice").val() === "") {
            alert("进货价格不能为空");
        } else if ($("#productRealPrice").val() === "") {
            alert("出售价格不能为空");
        } else if ($("#factoryName").val() === "") {
            alert("商品制作公司不能为空");
        } else if ($("#productNorm").val() === "") {
            alert("产品规格不能为空");
        } else if ($("#stockQuantity").val() === "") {
            alert("库存数量不能为空");
        }
        else {
            $.ajax({
                data: {
                    shopObj: $("#shopObj").val(),
                    productID: $("#productID").val(),
                    productName: $("#productName").val(),
                    productPicture: $("#productPicture").val(),
                    productCategoryObj: $("#productCategoryObj").val(),
                    productProposedPrice: $("#productProposedPrice").val(),
                    productRealPrice: $("#productRealPrice").val(),
                    productBrand: $("#productBrand").val(),
                    factoryName: $("#factoryName").val(),
                    registeredAddress: $("#registeredAddress").val(),
                    productNorm: $("#productNorm").val(),
                    productPackingUnit: $("#productPackingUnit").val(),
                    stockQuantity: $("#stockQuantity").val(),
                    productRemark: $("#productRemark").val(),
                },
                type: "POST",
                url: "web/product/add_product",
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
                error: function() {
                    alert("系统错误,请稍后再试！");
                },
            });
        }
    }
</script>
</body>
</html>