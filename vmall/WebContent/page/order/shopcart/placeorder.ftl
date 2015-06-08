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
	<script src="${base}/lib/jquery-2.1.1.min.js"></script>
	<script src="${base}/lib/jquery-ui.min.js"></script>

	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/base.css" charset="gbk">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/extend.css" charset="gbk">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/hotel.css" charset="gbk">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/airline.css" charset="gbk">
	
	<body id="body">
		<link rel="stylesheet" href="${base}/lib/jd/order/misc/css/base.css?v=20150604">
		<link rel="stylesheet" href="${base}/lib/jd/order/misc/css/pay.css?v=20150604">
	
		<form method="post" action="/norder/submit.action?sid=4d8f86754c44f93307cf2cca06789add" id="orderForm">
	
			<div class="common-wrapper">
				<div class="w checkout" style="padding:0px;">
					<div class="step1 border-1px">
						<div class="m step1-in ">
							<a href="/norder/address.action?sid=4d8f86754c44f93307cf2cca06789add" class="s-href">
								<div class="mt_new">                      <div class="s1-name">
									<i></i>蒲剑
								</div>
								<div class="s1-phone">
									<i></i>130****4107
								</div>
	
							</div>
							<div class="mc step1-in-con">
								陕西西安市雁塔区等驾坡街道公园南路鑫龙天然居1期7号楼301室
							</div>
						</a>
					</div>
	
	
					<b class="s1-borderT"></b>
					<b class="s1-borderB"></b>
					<span class="s-point"></span>
				</div>
				<div class="step2 border-1px">
	
					<a href="/norder/payShipment.action?sid=4d8f86754c44f93307cf2cca06789add" class="s-href">
						<div class="m s-item bdb-1px">
							<div class="mt_new">
								支付方式：在线支付
							</div>
							<div class="mc">
								配送方式：普通快递
							</div>
							<span class="s-point"></span>
						</div>
					</a>
	
	
					<a href="/norder/invoice.action?sid=4d8f86754c44f93307cf2cca06789add" class="s-href">
						<div class="m s-item">
							<div class="mt_new">纸质发票:个人</div>
							<div class="mc">非图书商品－不开发票</div>
							<span class="s-point"></span>
						</div>
					</a>
				</div>
	
	
				<div class="step3 border-1px step3-more">
					<a href="" class="s-href">
						<div class="s-item">
							<div class="sitem-l" id="cartgoodslist">

							<#assign allamount = 0>
	        				<#assign allgoodsnums = 0>
	        	
							<#list obj.shopcartgoodses as cartgoods>
							
							<#assign allamount = allamount + (((cartgoods.saleprice+0)?number)/10)>
							<#assign allgoodsnums = allgoodsnums + (((cartgoods.nums+0)?number)/10)>
								<input type="hidden" name="id" value="${cartgoods.id}">
								<div class="sl-img">
									<img src="http://img10.360buyimg.com/n4/jfs/t1249/100/386284951/341939/a2f205fb/551d0baaNf363954e.jpg">
								</div>
							</#list>	
	
							</div>
	
							<div class="sitem-r">共${obj.shopcartgoodses?size}件</div>
							<span class="s-point"></span>
						</div>
					</a>
				</div>
	
	
				<div class="step5 border-1px" id="yunfeeList" style="margin-bottom: 3.125em;">
	
	
					<div class="s-item">
						<div class="sitem-l">
							商品金额
						</div>
						<div class="sitem-r">${allamount}</div>
					</div>
					<div class="s-item">
						<div class="sitem-l">
							运费
						</div>
						<div class="sitem-r">
							5.00
						</div>
					</div>
				</div>
	
	
	
	
	
	
	
			</div>
	
			<div class="pay-bar" id="pay-bar">

				<div class="payb-con">
					实付款：<span id="payMoney">￥95.80</span>
				</div>
	
				<a class="payb-btn" onclick="page_settlement()" href="javascript:void(0);">
					提交订单
				</a>
				<input type="hidden" id="sid" value="4d8f86754c44f93307cf2cca06789add">
			</div>
	
		</div>
	
	</form>
	
	
<script>

function page_settlement()
{
	var ids = [];
	var fids = $('#cartgoodslist input[name="id"]');
	if(fids.length==0)
	{
		alert("订单商品是空的，没有可下单的商品。");
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
				alert("提交订单异常！");
				return;
			}
			json = eval("(" + data + ")");
			if(json.state=="success")
			{
				alert("成功提交订单！");
				window.location.reload();
				// 更新购物车数量等操作；
			}
			else
			{
				alert("提交订单失败！");
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
