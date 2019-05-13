<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	href="style/admin/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="style/admin/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="style/admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="style/admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="style/admin/static/h-ui.admin/css/style.css" />
<link rel="stylesheet" href="style/ordersystem.css">
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>公告</title>
</head>
<body>
<div class="page-container">
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="r">共有数据：${count} 条</span> </div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="80">序号</th>
				<th width="100">公告内容</th>
				<th width="130">创建时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${notices }" var="notice">
			<tr class="text-c">
				<td><input type="checkbox" value="1" name=""></td>
				<td>${notice.id }</td>
				<td>${notice.noticeContent }</td>
				<td ><fmt:formatDate value="${notice.noticeCreateTime}" pattern="yyyy年MM月dd日"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="style/admin/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="style/admin/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="style/admin/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="style/admin/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="style/admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="style/admin/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="style/admin/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript">
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "esc" ]],//默认第几个排序
	"aLengthMenu": [[10, 20, 30, 40], [10, 20, 30, 40]],
	"bStateSave": true,//状态保存
	"pading":false,
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,3]}// 不参与排序的列
	]
});

/*用户-删除*/
function member_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'admin/userDel',
			data:{
				phone:id
			},
			dataType: 'json',
			success: function(data){
				if(data.success=="success"){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
				}else{
					alert(data.infor);
				}
				
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}
</script> 
</body>
</html>