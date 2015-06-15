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
<div class="good-detail sift-mg">
	<div class="sift-tab" style="height: 42px;">
		<div id="fixed" class="sift-tab" style="height: 42px; width: 439px;">
		<ul class="tab-lst">
			<li><a href="javascript:void(0)" value="bygroup" class="on">人员</a></li>
			<li><a href="javascript:void(0)" value="bygoods" class=""><span class="bar"></span>商品</a></li>
			<li><a href="javascript:void(0)" value="byorder" class=""><span class="bar"></span>订单</a></li>
		</ul>
		</div>
	</div>
	<div class="detail" id="bygroup" style="display: block;">
		<ul>
		</ul>
	</div>
	<div class="detail" id="bygoods" style="display: none;">
		<ul>
		</ul>	
	</div>
	<div class="detail" id="byorder" style="display: none;">
		<ul>
		</ul>
	</div>
</div>

<div id="payment_p" style="display:block">
<div id="paymentp"></div>
<div class="payment-total-bar" id="payment">
    <div class="shp-chk">
        <span onclick="checkAllHandler();" class="cart-checkbox checked" id="checkIcon-1"></span>
    </div>
    <div class="shp-cart-info">
        <strong class="shp-cart-total">本周累计：￥<span class="" id="cart_realPrice"></span></strong>
        <span class="sale-off">本月累计：<b>￥<span class="bottom-bar-price" id="cart_oriPrice"></b></span>
    </div>
    <a class="btn-right-block" id="submit" style="width:150px;background-color: rgb(192, 0, 0); background-position: initial initial; background-repeat: initial initial;">累计积分：￥<span id="sumscore"></span></a>
</div>
</div>

<script type="text/javascript">
$(function(){
	
	$("#submit").click(function() {page_applydraw()});
	
	$("#bygroup").show();
	$("li>a").click(function(){
		if($('#fixed').has('nav-fixed')){
			$('#fixed').removeClass('nav-fixed');
		}
		$(".detail").hide();
		$("li>a").removeClass("on");
		$(this).addClass("on");
		var id=$(this).attr("value");
		$("#"+id).show();
		eval("page_show"+id+"();");
	});
	page_showsum();
	page_showbygroup();
});

function page_showbygroup()
{
	console.log("showbygroup");
	
	$.ajax({
		type:'post',
		url:'${base}/member/member/myrebate/showbygroup.action',
		contentType: "application/json",
		data:JSON.stringify({"temp":"temp"}),
		cache:false,
		async:false,
		success:function(data)
		{
			console.log(data);
			$("#bygroup ul").html(data);
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})
	
	page_showsum();
}

function page_showsum()
{
	console.log("showsum");
	
	$.ajax({
		type:'post',
		url:'${base}/member/member/myrebate/showsum.action',
		contentType: "application/json",
		data:JSON.stringify({"temp":"temp"}),
		cache:false,
		async:false,
		success:function(data)
		{
			console.log(data);
			json = eval("(" + data + ")");
			$("#sumscore").html(json.score);
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})	
}

function page_showbygoods()
{
	console.log("showbygoods");
	
	$.ajax({
		type:'post',
		url:'${base}/member/member/myrebate/showbygoods.action',
		contentType: "application/json",
		data:JSON.stringify({"temp":"temp"}),
		cache:false,
		async:false,
		success:function(data)
		{
			console.log(data);
			$("#bygoods ul").html(data);
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})
	
	page_showsum();
}

function page_showbyorder()
{
	console.log("showbyorder");
	
	$.ajax({
		type:'post',
		url:'${base}/member/member/myrebate/showbyorder.action',
		contentType: "application/json",
		data:JSON.stringify({"temp":"temp"}),
		cache:false,
		async:false,
		success:function(data)
		{
			console.log(data);
			$("#byorder ul").html(data);
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})
	
	page_showsum();
}



function page_applydraw()
{
	window.location = "${base}/order/drawcash/apply.action";
}

</script>

</body>
</html>