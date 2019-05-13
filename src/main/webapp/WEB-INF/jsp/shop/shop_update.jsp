<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<title>店铺修改</title>
</head>
<body>
<input name="id" id="id" type="hidden" value="${shop.id }"/>
<article class="page-container">
	<div class="form form-horizontal" id="form-member-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>店铺编号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${shop.shopId }" placeholder="" id="shopId" name="shopId">
			</div>
		</div>
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<button class="btn btn-primary radius" onclick="userUpdate()">更新</button>
			</div>
		</div>
	</div>
</article>

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
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	
});

//手机校验
function isPoneAvailable(str) {
          var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
          if (!myreg.test(str)) {
              return false;
          } else {
              return true;
          }
      }

function userUpdate(){
//		$("#phoneError").html("");
//		$("#codeError").html("");
		if($("#shopId").val()==""){
			alert("店铺编号不能为空");
		}
	//	else if(!isPoneAvailable($("#mobile").val())){
	//		alert("请输入正确的手机号");
//			$("#phoneError").html("请输入正确的手机号");
		//}
		else{
			$.ajax({
				type:"POST",
				data:{
					id:$("#id").val(),
					shopId:$("#shopId").val()
//					userName:$("#username").val(),
//					loginName:$("#loginName").val(),
//					phone:$("#mobile").val(),
//					sex:$('input:radio[name="sex"]:checked').val(),
//					des:$("#des").val(),
				},
				url:"shop/updateShopInfor",
				dataType:"JSON",
				success:function(data){
					if(data.success=="success"){
						alert(data.infor);
						window.parent.location.reload(); //刷新父页面
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						parent.layer.close(index);  // 关闭layer
					}
					else{
						alert(data.infor);
					}
				},
				error:function(){
					alert("系统错误");
				},
				
			});
		}
}
</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>