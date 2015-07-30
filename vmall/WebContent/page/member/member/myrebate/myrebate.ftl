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
<body id="body" style="background:#ffffff">
<style>
.sum{height:30px;width:25%;float:left;font-size:12px;color:#dedede}
</style>
<#include "/decorator/include/navmain.ftl">

<div data-spm="" class="content">
	<div style="height:30px;width:100%">
		<div class="sum"><span>已生效：<span id="score_rebate" style="color:#ff6666;font-size:18px"></span></div>
		<div class="sum"><span>待生效：</span><span id="score_rebate_wait" style="font-size:18px"></span></div>
		<div class="sum"><span>全部：</span><span id="score_rebate_all" style="font-size:18px"></span></div>		
		<div class="sum"><span>潜在：</span><span id="score_rebate_latent" style="font-size:18px"></span></div>	
	</div>
	<nav data-spm="1006" class="tabs">
		<div type="group" data-sort="" data-spm-click="" class="tab-item active" data-spm-anchor-id="">会员</div>
		<div type="goods" data-sort="" data-spm-click="" class="tab-item" data-spm-anchor-id="">商品</div>
		<div type="order" data-sort="" data-spm-click="" class="tab-item" data-spm-anchor-id="">订单</div>
	</nav>
	
	<div class="detail" id="bygroup" style="display: block;">
		<ul class="shp-cart-list">
		</ul>
	</div>
	<div class="detail" id="bygoods" style="display: none;">
		<ul class="shp-cart-list">
		</ul>	
	</div>
	<div class="detail" id="byorder" style="display: none;">
		<ul class="shp-cart-list">
		</ul>
	</div>
	
	
</div>

<div id="payment_p" style="display:block">
<div id="paymentp"></div>
<div class="payment-total-bar" id="payment">
    <div class="shp-chk">
        <#-- <span class="cart-checkbox checked" id="checkIcon-1"></span> -->
    </div>
    <div class="shp-cart-info">
        <strong class="shp-cart-total"><#-- 本周累计：￥<span class="" id="sum_week"></span>--></strong>
        <span class="sale-off"><#--本月累计：<b>￥<span class="bottom-bar-price" id="sum_month"></b>--></span>
    </div>
    <a class="btn-right-block" id="submit" style="width:150px;background-color: rgb(192, 0, 0); background-position: initial initial; background-repeat: initial initial;">申请提现：￥<span id="sum_apply"></span></a>
</div>
</div>

<script type="text/javascript">
var type = "";
$(function(){
	
	$("#submit").click(function() {page_applydraw()});
	$("#bygroup").show();

	$(".tabs>div").click(function(){
		$(".detail").each(function(){
			$(this).hide();
		});
		
		$(".tabs>div").removeClass("active");
		$(this).addClass("active");

		type = $(this).attr("type");
		
		document.getElementById("by"+type).style.display="block";
		
		eval("page_showby"+type+"();");
		
	});

	page_showsum_rebate_latent();
	page_showsum_rebate_all();
	page_showsum_rebate_wait();
	page_showsum_rebate();
	
	
	page_showbygroup();
});

function page_showbygroup()
{
	console.log("showbygroup");
	
	$.ajax({
		type:'post',
		url:'${base}/member/member/myrebate/showbygroup.action',
		contentType: "application/json",
		data:JSON.stringify({"orderstate":"结束"}),
		cache:false,
		async:true,
		success:function(data)
		{
			//console.log(data);
			$("#bygroup ul").html(data);
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
		data:JSON.stringify({"orderstate":"结束"}),
		cache:false,
		async:true,
		success:function(data)
		{
			//console.log(data);
			$("#bygoods ul").html(data);
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})
}

function page_showbyorder()
{
	console.log("showbyorder");
	
	$.ajax({
		type:'post',
		url:'${base}/member/member/myrebate/showbyorder.action',
		contentType: "application/json",
		data:JSON.stringify({"orderstate":"结束"}),
		cache:false,
		async:true,
		success:function(data)
		{
			//console.log(data);
			$("#byorder ul").html(data);
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})
}

function page_showsum_rebate_latent()
{
	$.ajax({
		type:'POST',
		url:'${base}/member/member/myrebate/showsum.action',
		contentType: "application/json",
		data:JSON.stringify({"orderstate":"下单"}),
		cache:false,
		async:true,
		success:function(data)
		{
			console.log(data);
			json = eval("(" + data + ")");
			$("#score_rebate_latent").html(json.score);
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})	
}

function page_showsum_rebate_all()
{
	$.ajax({
		type:'POST',
		url:'${base}/member/member/myrebate/showsum.action',
		contentType: "application/json",
		data:JSON.stringify({"orderstatebegin":"收款","orderstateend":"结束"}),
		cache:false,
		async:true,
		success:function(data)
		{
			console.log(data);
			json = eval("(" + data + ")");
			$("#score_rebate_all").html(json.score);
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})	
}

function page_showsum_rebate_wait()
{
	$.ajax({
		type:'POST',
		url:'${base}/member/member/myrebate/showsum.action',
		contentType: "application/json",
		data:JSON.stringify({"orderstatebegin":"收款", "orderstateend":"结算"}),
		cache:false,
		async:true,
		success:function(data)
		{
			console.log(data);
			json = eval("(" + data + ")");
			$("#score_rebate_wait").html(json.score);
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})	
}

function page_showsum_rebate()
{
	$.ajax({
		type:'POST',
		url:'${base}/member/member/myrebate/showsum.action',
		contentType: "application/json",
		data:JSON.stringify({"orderstate":"结束"}),
		cache:false,
		async:true,
		success:function(data)
		{
			console.log(data);
			json = eval("(" + data + ")");
			$("#score_rebate").html(json.score);
			$("#sum_apply").html(json.score);
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})	
}

function page_applydraw()
{
	var lis = $("#by"+type+" ul li");
	if(lis.length==0)
	{
		alert("亲，好像没有看到你的积分啊，无法体现哦。");
		return;
	}
	
	// window.location = "${base}/order/drawcash/apply.action";
	
	
	$.ajax({
		type:'POST',
		url:'${base}/order/drawcash/apply.action',
		data:$("#form_draw").serialize(),
		cache:false,
		async:true,
		success:function(data)
		{
			console.log(data);
			if(data=="")
			{
				alert("提现申请异常，请检查后再试！");
				return;
			}
			json = eval("(" + data + ")");
			if(json.state=="success")
			{
//				alert("申请提现成功！");
				window.location = "${base}/order/drawcash/locate.action?id=" + json.drawcash.id;
			}
			else
			{
				alert(json.message);
			}
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