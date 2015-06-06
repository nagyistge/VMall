<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta content="telephone=no" name="format-detection">
<meta name="baidu-site-verification" content="t7oDT96Amk" />
<title>微猫商城</title>
<meta content="微猫商城购物，正品商城，品牌特卖会" name="keywords">
<meta content="微猫商城购物，正品商城，品牌特卖会" name="description">
<meta name="sogou_site_verification" content="G7nmLR75yc" />
<meta name="baidu-tc-cerfication"
	content="0a8c6d28b570b218f78510c29be4529b" />
<meta name="360-site-verification"
	content="8b6121969d78afda8caeb69053fa29d9" />
<link href="//ms2-m.vipstatic.com/apple-touch-icon.png/" rel="apple-touch-icon" />
<link rel="stylesheet" type="text/css" href="${base}/css/neat-min.css">
<link rel="stylesheet" type="text/css" href="${base}/css/ui-min.css">
<link rel="stylesheet" type="text/css" href="${base}/css/channel-min.css"
	webapp="im">
<link rel="stylesheet" href="${base}/css/layout.min.css">
<link rel="stylesheet" href="${base}/css/common.min.css">
<link rel="stylesheet" href="${base}/css/main.css">


<script src="${base}/lib/jquery-2.1.1.min.js"></script>
<script src="${base}/lib/jquery-ui.min.js"></script>
<script src="${base}/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="${base}/lib/moment.min.js"></script>
<script src="${base}/lib/moment.zh-cn.js"></script>
<script	src="${base}/lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${base}/lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${base}/lib/lodash.min.js"></script>
<script src="${base}/lib/bootstrap-slider.js"></script>
<script src="${base}/lib/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="${base}/js/main.js"></script>
</head>
<body class="u-index">
<div style="">

<div class="nav_placeholder" style="height: 37px; display: none;"></div>
	<div id="nav" class="u-nav">
		<ul class="clearfix">
			<li class="num06"><a href="${base}/goods/goods/index.action" mars_sead="floating-index_btn"> <span>首页</span>
			</a></li>
			<li class="num06"><a href="${base}/goods/goodsclass/index.action" mars_sead="floating-index-class_btn"> <span>分类</span>
			</a></li>
			<li class="num06"><a href="${base}/order/shopcart/index.action"	mars_sead="floating-index-shopcart_btn"> <span>购物车</span>
			</a></li>
			<li class="num06"><a href="${base}/member/member/index.action" mars_sead="floating-index-member_btn"> <span>个人中心</span>
			</a></li>
		</ul>
	</div>
</div>

</div>

<div id="div_shopcart">
<form id="form_shopcart">
<table id="tb_shopcart" class="table personListTable hover">
	<thead>
	    <tr>
	      <th width="10" class="check min"></th>   
	      <th width="300">名称</th>
	      <th width="50">数量</th>
	      <th width="50">单价</th>
	      <th width="50">促销价</th>
	      <th width="100">金额</th>
	      
	    </tr>
	</thead>	     
<tbody>
<#list obj.shopcartgoods as cartgoods>
      <tr>
        <td><input type="hidden" name="id" value="${cartgoods.id}"></td>
        <td>${cartgoods.goodsname}</td>
        <td>${cartgoods.nums}</td>        
        <td></td>        
        <td></td>
        <td></td>        
      </tr>     
</#list>      
</tbody>
</table>
</form>
</div>

<div style="text-align:left;padding-left:20px;padding-bottom:20px;">
<button id="bt_settlement" class="btn btn-primary">结算</button>
</div>

<script>
$("#bt_settlement").click(function() {page_settlement()});

function page_settlement()
{
	var ids = [];
	var fids = $('#tb_shopcart input[name="id"]');
	if(fids.length==0)
	{
		alert("购物车是空的，没有需要结算的商品。");
		return;
	}

	for(i=0;i<fids.length;i++)
	{
		ids.push(fids[i].value);
	}
	
	$.ajax({
		type:'post',
		url:'${base}/order/shopcart/settlement.action',
		contentType: "application/json",
		data:JSON.stringify({"ids":ids}),
		cache:false,
		async:false,
		success:function(data)
		{
			console.log(data);
			if(data=="")
			{
				alert("添加至购物车异常！");
				return;
			}
			json = eval("(" + data + ")");
			if(json.state=="success")
			{
				alert("成功添加至购物车！");
				window.location.reload();
				// 更新购物车数量等操作；
			}
			else
			{
				alert("添加至购物车失败！");
			}
		},
		error:function(data)
		{
			console.log(data);
			alert("请求异常！");
		}
	})
}

</script>

</body>

</html>