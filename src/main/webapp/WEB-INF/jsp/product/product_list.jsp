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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>>">
    <link rel="stylesheet" type="text/css"
          href="/style/admin/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="/style/admin/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css"
          href="/style/admin/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css"
          href="/style/admin/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css"
          href="/style/admin/static/h-ui.admin/css/style.css"/>
    <link rel="stylesheet" href="/style/ordersystem.css">
    <!--[if IE 6]>
    <script type="text/javascript" src="/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>商品管理</title>
</head>
<body>
<div>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品管理 <span
            class="c-gray en">&gt;</span> 商品列表<a class="btn btn-success radius r"
                                                 style="line-height:1.6em;margin-top:3px"
                                                 href="javascript:location.replace(location.href);" title="刷新"><i
            class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="page-container">

        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <span class="l">
                <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
            </span>&nbsp;
            <a class="btn btn-primary radius" onclick="product_add('添加','/web/product/add_product_info','','510')"
               href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加商品</a>
            <span class="r">共有数据：${count} 条</span>
        </div>

        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                <tr class="text-c">
                    <th width="40"><input name="" type="checkbox" value=""></th>
                    <th width="40">序号</th>
                    <th width="170">商品条码</th>
                    <th width="80">商品名称</th>
                    <th width="40">所属店铺</th>
                    <th width="80">商品图片</th>
                    <th width="40">商品类型</th>
                    <th width="40">类型名称</th>
                    <th width="40">进货价格</th>
                    <th width="40">出售价格</th>
                    <th width="40">活动价格</th>
                    <th width="60">品牌</th>
                    <th width="60">制作公司</th>
                    <th width="60">注册地址</th>
                    <th width="60">产品规格</th>
                    <th width="60">包装单位</th>
                    <th width="40">商品库存</th>
                    <th width="40">商品销量</th>
                    <th width="60">备注信息</th>
                    <th width="100">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${products}" var="product">
                    <tr class="text-c va-m">
                        <td><input name="" type="checkbox" value=""></td>
                        <td>${product.id }</td>
                        <td>${product.productID }</td>
                        <td>${product.productName }</td>
                        <td>${product.shopObj }</td>
                        <td><img width="60" class="product-thumb" src="${product.productPicture }"></td>
                        <td>${product.productType }</td>
                        <td>${product.productTypeName }</td>
                        <td>${product.productProposedPrice }</td>
                        <td>${product.productRealPrice }</td>
                        <td>${product.productActivityPrice }</td>
                        <td>${product.productBrand }</td>
                        <td>${product.factoryName }</td>
                        <td>${product.registeredAddress }</td>
                        <td>${product.productNorm }</td>
                        <td>${product.productPackingUnit }</td>
                        <td>${product.stockQuantity }</td>
                        <td>${product.sellQuantity }</td>
                        <td>${product.productRemark }</td>

                        <td class="td-manage">
                            <a style="text-decoration:none" class="ml-5" onClick="product_del(this,'${product.id }')"
                               href="javascript:;" title="商品下架"><i class="Hui-iconfont">&#xe6e3;</i></a>
                            <a style="text-decoration:none" class="ml-5" onclick="product_edit('编辑','/web/product/edit_product_info?id='+${product.id},'4','','510')"
                               href="javascript:;" title="编辑"> <i class="Hui-iconfont">&#xe6df;</i></a>
                            <a style="text-decoration:none" class="ml-5" onClick="product_del(this,'${product.id }')"
                                href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

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
    var setting = {
        view: {
            dblClickExpand: false,
            showLine: false,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
            }
        },
        callback: {
            beforeClick: function (treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("tree");
                if (treeNode.isParent) {
                    zTree.expandNode(treeNode);
                    return false;
                } else {
                    //demoIframe.attr("src",treeNode.file + ".html");
                    return true;
                }
            }
        }
    };

    $(document).ready(function () {
        var t = $("#treeDemo");
        t = $.fn.zTree.init(t, setting, zNodes);
        //demoIframe = $("#testIframe");
        //demoIframe.on("load", loadReady);
        var zTree = $.fn.zTree.getZTreeObj("tree");
        //zTree.selectNode(zTree.getNodeByParam("id",'11'));
    });

    // 排序列
    $('.table-sort').dataTable({
        "aaSorting": [[1, "esc"]],//默认第几个排序
        "bStateSave": true,//状态保存
        "aoColumnDefs": [
            {"orderable": false, "aTargets": [0, 19]}// 制定列不参与排序
        ]
    });

    /*商品-编辑*/
    function product_edit(title, url, id, w, h) {
        layer_show(title, url, w, h);
    }

    /*商品-删除*/
    function product_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '/web/product/delete_product_info',
                data: {
                    id: id
                },
                dataType: 'json',
                success: function (data) {
                    if (data.success === "0") {
                        $(obj).parents("tr").remove();
                        layer.msg('已删除！', {icon: 1, time: 1000});
                    }
                    else {
                        alert(data.info);
                    }
                },
                error: function () {
                    console.log("系统异常，请稍后再试！");
                },
            });
        });
    }


    /*商品-添加*/
    function product_add(title, url, w, h) {
        layer_show(title, url, w, h);
    }

</script>
</body>
</html>