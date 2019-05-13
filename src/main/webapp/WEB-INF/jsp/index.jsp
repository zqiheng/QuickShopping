<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
    <link rel="Bookmark" href="/favicon.ico">
    <link rel="Shortcut Icon" href="/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="style/admin/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="style/admin/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="style/admin/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="style/admin/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="style/admin/static/h-ui.admin/css/style.css"/>
    <title>Quick Shopping 后台管理系统</title>
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl">
            <a class="logo navbar-logo f-l mr-10 hidden-xs" href="/aboutHui.shtml">Quick Shopping 后台管理系统</a>
            <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li>【${userName}】管理员</li>
                    <li class="dropDown dropDown_hover">
                        <a class="dropDown_A">admin <i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
                            <li><a href="admin/adminLogin">切换账户</a></li>
                            <li><a href="admin/adminLogin">退出</a></li>
                        </ul>
                    </li>
                    <li id="Hui-skin" class="dropDown right dropDown_hover"><a href="javascript:;" class="dropDown_A"
                                                                               title="换肤"><i class="Hui-iconfont"
                                                                                             style="font-size:18px">&#xe62a;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
                            <li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
                            <li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
                            <li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
                            <li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
                            <li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>

<aside class="Hui-aside">
    <div class="menu_dropdown bk_2">
        <dl id="menu-shop">
            <dt><i class="Hui-iconfont">&#xe620;</i> 店铺管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="/web/shop/query_shop_list_info" data-title="店铺列表" href="javascript:void(0)">店铺列表</a></li>
                    <li><a data-href="/web/shop/shop_category_Info" data-title="店铺类别" href="javascript:void(0)">店铺类别</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-product">
            <dt><i class="Hui-iconfont">&#xe610;</i> 商品管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="/web/product/query_product_list_info" data-title="商品列表" href="javascript:void(0)">商品列表</a></li>
                    <li><a data-href="/web/product/product_category_Info" data-title="商品类别" href="javascript:void(0)">商品类别</a></li>
                    <li><a data-href="/web/product/product_category_Info" data-title="商品调价" href="javascript:void(0)">商品调价</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-orders">
            <dt><i class="Hui-iconfont">&#xe626;</i> 订单管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="/web/orders/orders_list" data-title="所有订单" href="javascript:void(0)">所有订单</a></li>
                    <%--<li><a data-href="/web/orders/todoOrders_list" data-title="待自提订单" href="javascript:void(0)">待自提订单</a></li>--%>
                    <li><a data-href="/web/orders/todoOrders_list" data-title="待配送订单" href="javascript:void(0)">待配送订单</a></li>
                    <%--<li><a data-href="/web/orders/todoOrders_list" data-title="待收货订单" href="javascript:void(0)">已发货订单</a></li>
                    <li><a data-href="/web/orders/todoOrders_list" data-title="已完成订单" href="javascript:void(0)">已完成订单</a></li>--%>
                    <li><a data-href="/web/orders/" data-title="退货订单" href="javascript:void(0)">退货订单</a></li>
                </ul>
            </dd>
        </dl>

        <dl id="menu-data">
            <dt><i class="Hui-iconfont">&#xe602;</i> 数据分析<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="admin/admin_order" data-title="销售额" href="javascript:void(0)">销售额</a></li>
                    <li><a data-href="admin/admin_order" data-title="销售排行" href="javascript:void(0)">销售排行</a></li>
                </ul>
            </dd>
        </dl>

        <dl id="menu-activity">
            <dt><i class="Hui-iconfont">&#xe621;</i> 活动管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="admin/admin_order" data-title="限时折扣" href="javascript:void(0)">限时折扣</a></li>
                    <li><a data-href="admin/admin_order" data-title="优惠劵" href="javascript:void(0)">优惠劵</a></li>
                </ul>
            </dd>
        </dl>

        <dl id="menu-member">
            <dt><i class="Hui-iconfont">&#xe620;</i> 会员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="admin/admin_order" data-title="会员中心" href="javascript:void(0)">会员中心</a></li>
                </ul>
            </dd>
        </dl>

        <dl id="menu-role">
            <dt><i class="Hui-iconfont">&#xe60d;</i> 角色管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="admin/admin_order" data-title="角色管理" href="javascript:void(0)">角色管理</a></li>
                </ul>
            </dd>
        </dl>
    </div>
</aside>

<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>

<section class="Hui-article-box">

    <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active">
                    <span title="主页" data-href="/person/main">主页</span>
                    <em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S"
                                                  href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
                id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
        </div>
    </div>

    <!-- 主页 -->
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display:none" class="loading"></div>
            <iframe scrolling="yes" frameborder="0" src="/person/main"></iframe>
        </div>
    </div>
</section>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="style/admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="style/admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="style/admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="style/admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="style/admin/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>


<script type="text/javascript">
    $(function () {
        /*$("#min_title_list li").contextMenu('Huiadminmenu', {
            bindings: {
                'closethis': function(t) {
                    console.log(t);
                    if(t.find("i")){
                        t.find("i").trigger("click");
                    }
                },
                'closeall': function(t) {
                    alert('Trigger was '+t.id+'\nAction was Email');
                },
            }
        });*/
    });

    /*个人信息*/
    function myselfinfo() {
        layer.open({
            type: 1,
            area: ['300px', '200px'],
            fix: false, //不固定
            maxmin: true,
            shade: 0.4,
            title: '查看信息',
            content: '<div>我是一位默默无闻的系统管理员!</div>'
        });
    }


</script>
</body>
</html>