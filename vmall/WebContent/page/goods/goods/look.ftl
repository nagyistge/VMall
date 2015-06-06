<html>
<head>
<meta charset="utf-8">
<title>宝贝详情</title>
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="format-detection" content="telephone=no">
<link type="text/css" rel="styleSheet"
	href="//g.alicdn.com/mtb/mixsln/0.4.4/mixsln-notheme.css?v=2259751745_2322">
<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport">
<meta name="aplus-waiting" content="1">
<meta name="data-spm" content="a1z3i">

<script src="${base}/lib/jquery-2.1.1.min.js"></script>
<script src="${base}/lib/jquery-ui.min.js"></script>
</head>
<body data-spm="7c" style="padding-top: 42px;">
	<div class="viewport enableNavbar enableToolbar enableTransition">
		<header class="navbar" style="display: none;">
			<ul>
				<li>宝贝详情</li>
				<li><a class="c-btn c-btn-aw" data-id="btn-1433260219737-4"
					style=""><span>返回</span></a></li>
				<li></li>
			</ul>
		</header>
		<section class="content" style="min-height: 221px;">
			<div class="wrap">
				<div class="inactive prev" index="0"></div>
				<div class="active" index="1" data-fragment=""
					style="min-height: 221px;">
					<div id="J_detail">
						<div id="J_detail_main">
							<div class="dt-slider">
								<div class="dt-slider-cont"
									style="width: 465px; -webkit-transform: translateZ(0px);">
									<ul class="dt-slct-ul"
										style="-webkit-transform: translate3d(0px, 0px, 0px); -webkit-backface-visibility: hidden; width: 2325px;">
										<li style="width: 465px" l="1"><img class=""
											src="http://gw.alicdn.com/bao/uploaded/i3/TB1nYUMHFXXXXaAXpXXXXXXXXXX_!!0-item_pic.jpg_320x320q50s150.jpg"></li>
									</ul>
									<div class="dt-slider-mask"></div>
								</div>
								<div class="dt-slider-status">
									<i class="sel"></i><i></i><i></i><i></i><i></i>
								</div>
								<div id="inner-back"></div>
								<div id="inner-cart"></div>
							</div>
							<div class="dt-info dt-ju" data-spm="info">
								<h1 class="dtif-h">${obj.goods.cname}</h1>
								<p><a href="javascript:void(0)" onclick="page_buy('${obj.goods.id}')">立即购买<a></p>
								<p><a href="javascript:void(0)" onclick="page_addshopcart('${obj.goods.id}',1)">加入购物车<a></p>
								
								<ul>
									<li class="dtif-p1"><strong class="dt-jup">¥108.00</strong><span
										class="lgray">0小时13分1秒后开始</span><span class="lgray">0人已购买</span><span
										class="lgray">原价：¥295.00<em class="dtj-em">3.7折</em></span></li>
									<li class="dtif-p2 lgray"><span class="dtifp-l">卖家包邮</span><span
										class="dtifp-m">月售1813笔</span><span class="dtifp-r">广东广州</span></li>
								</ul>
							</div>
							

							
						</div>
					</div>
				</div>		
			</div>
		</section>
	</div>	
	<style>
.r4fdsk {
	background-color: #5f646e;
	position: fixed;
	top: 0;
	left: 0;
	height: 42px;
	width: 100%;
	z-index: 1000;
	font-size: 12px
}

.r4f-point {
	color: #fff;
	display: block;
	text-decoration: none;
	height: 100%;
}

.r4f-close {
	position: absolute;
	left: 0;
	top: 0;
	height: 42px;
	line-height: 42px;
	width: 35px;
}

.r4f-close::before {
	content: "\d7";
	color: #fff;
	text-decoration: none;
	display: inline-block;
	font-size: 12px;
	width: 16px;
	height: 16px;
	line-height: 16px;
	margin-left: 8.5px;
	border: 1px solid #fff;
	border-radius: 50%;
	text-align: center;
}

.r4f-font {
	margin: 0 0 0 40px;
	display: -webkit-box;
	height: 100%;
	overflow: hidden;
	-webkit-box-align: center;
}

.r4f-taobao {
	margin: 0 8px 0 0;
	width: 30px;
	height: 30px;
	display: inline-block;
	vertical-align: top;
}

.r4f-taobao img {
	width: 100%;
	height: 100%;
}

.r4f-font>span {
	-webkit-box-flex: 1;
	display: block;
	line-height: 120%;
	padding-right: 10px;
}

.r4f-dl {
	display: -webkit-box;
	-webkit-box-align: center;
	color: #fff;
	background-color: #FF5000;
	height: 100%;;
	text-align: center;
	font-weight: normal;
	font-size: 14px;
	padding: 0 8px;
}
</style>
<script>
function page_addshopcart(goodsid, nums)
{
	$.ajax(
	{
		type:'post',
		url:'${base}/order/shopcart/addtocart.action',
		data:{"goodsid":goodsid,"nums":nums},
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