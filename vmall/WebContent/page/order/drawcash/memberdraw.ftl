<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/base.css" charset="gbk">
<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/extend.css" charset="gbk">

<link href="${base}/lib/jd/cart/css/title-bar.css" media="all" rel="stylesheet" type="text/css">
<link href="${base}/lib/jd/cart/css/cart/btn.css" media="all" rel="stylesheet" type="text/css">
<link href="${base}/lib/jd/cart/css/checkbox.css" media="all" rel="stylesheet" type="text/css">
<link href="${base}/lib/jd/cart/css/spinner.css" media="all" rel="stylesheet" type="text/css">
<link href="${base}/lib/jd/cart/css/shopping-cart.css" media="all" rel="stylesheet" type="text/css">
<#include "/decorator/include/header.ftl">
</head>
<body id="body">
<#include "/decorator/include/navmain.ftl">
<div id="notEmptyCart" style="display:block">
	<form id="form_browse" method="POST">
	<ul class="shp-cart-list" id="shp-cart-list">
	</ul>
	</form>
</div>
<script>
page_browse();

function page_browse()
{
	$.ajax({
		type:'post',
		url:'${base}/member/member/browsemydraw.action',
		data:{temp:'temp'},
		cache:false,
		async:true,
		success:function(data)
		{
			console.log(data);
			$("#shp-cart-list").append(data);			
			
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})		
}
</script>
</body>
</html>