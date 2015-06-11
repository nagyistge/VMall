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

		<form id="form_order" method="post" action="${base}/order/order/forward.action">
			<input type="hidden" id="id" name="id" value="${obj.order.id}">
			<input type="hidden" id="state" name="state" value="${obj.order.state}">

			<div class="common-wrapper">
				<div class="w checkout" style="padding:0px;">
					<div class="step1 border-1px">
						<div class="m step1-in ">
							<a href="" class="s-href">
								<div class="mt_new">                      
									<div class="s1-name"><i></i>订购人：${obj.order.membercname}</div>
									<div class="s1-phone"><i></i>联系电话：130****4107</div>

								</div>
								<div class="mc step1-in-con">地址：${obj.order.takeaddress}</div>
							</a>
						</div>


						<b class="s1-borderT"></b>
						<b class="s1-borderB"></b>
						<span class="s-point"></span>
					</div>
					<div class="step2 border-1px">

						<a href="" class="s-href">
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

						<#--	
						<a href="" class="s-href">
							<div class="m s-item">
								<div class="mt_new">纸质发票:个人</div>
								<div class="mc">非图书商品－不开发票</div>
								<span class="s-point"></span>
							</div>
						</a>
						-->
					</div>

					<div class="step3 border-1px step3-more">
						<a href="" class="s-href">
							<div class="s-item">
								<div class="sitem-l" id="goodslist">

									<#assign allamount = 0>
									<#assign allgoodsnums = 0>

									<#list obj.ordergoodses as goods>

									<#assign allamount = allamount + (((goods.saleprice+0)?number)/10)>
									<#assign allgoodsnums = allgoodsnums + (((goods.nums+0)?number)/10)>
									<input type="hidden" name="ordergoodsid" value="${goods.id}">
									<div class="sl-img">
										<img src="http://img10.360buyimg.com/n4/jfs/t1249/100/386284951/341939/a2f205fb/551d0baaNf363954e.jpg">
									</div>
									</#list>	

								</div>

								<div class="sitem-r">共${obj.ordergoodses?size}件</div>
								<span class="s-point"></span>
							</div>
						</a>
					</div>


					<div class="step5 border-1px" id="yunfeeList" style="margin-bottom: 3.125em;">


						<div class="s-item">
							<div class="sitem-l">商品金额：</div>
							<div class="sitem-r">￥${allamount?string("0.00")}</div>
						</div>
						<div class="s-item">
							<div class="sitem-l">运费：</div>
							<div class="sitem-r">￥5.00</div>
						</div>
					</div>
				</div>

			</div>

			<div class="pay-bar" id="pay-bar">
				<div class="payb-con">实付款：<span id="payMoney">￥${allamount?string("0.00")}</span></div>
				<a class="payb-btn" onclick="page_forward()" href="javascript:void(0);">提交订单</a>
			</div>
		</div>
	</form>
	
	
	<script>

// 提交订单
function page_forward()
{
	var orderid = $("#id").val();
	var state = $("#state").val();	

	if(orderid=="")
	{
		alert("订单数据异常，无法提交订单。");
		return;
	}

	if(!("下单"==state))
	{
		alert("您无法提交该订单，该订单已经处理过或异常。");
		return;
	}


	
	if(confirm("订单提交后无法修改，请您确认提交订单吗？"))
	{
		$.ajax({
			type:'post',
			url:'${base}/order/order/forward.action',
			contentType: "application/json",
			data:JSON.stringify({"id":orderid}),
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
					// 更新购物车数量等操作；
					window.location.reload();
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
}

</script> 

</body>
</html>
