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

<div class="viewport">

<div data-spm="1001" class="content has-header">
	<section class="waterfall">
		<div class="waterfall-list">

			<div class="waterfall-column" style="width: 211px;">
			<#list obj.goods as good>
				<a href="${base}/goods/goods/look.action?id=${good.id}" class="waterfall-item auction">
					<img style="width: 211px; height: 211px; display: block;" class="" src="${base}/image/goods/demogoods${good_index%5}.jpg">
					<h3>${good.cname}</h3>
					<p>¥<span class="promote-price">${good.promoteprice}</span>¥<del class="cost-price">${good.costprice}</del></p>
					<p><span class="info-sum">月销：470</span><span class="info-freight">免运费</span></p>
				</a>
			</#list>	
			</div>
			
			<div class="waterfall-column" style="width: 211px;">
			<#list obj.goods as good>
				<a href="${base}/goods/goods/look.action?id=${good.id}" class="waterfall-item auction">
					<img style="width: 211px; height: 211px; display: block;" class="" src="${base}/image/goods/demogoods${(good_index+3)%5}.jpg">
					<h3>${good.cname}</h3>
					<p>¥<span class="promote-price">${good.promoteprice}</span>¥<del class="cost-price">${good.costprice}</del></p>
					<p><span class="info-sum">月销：470</span><span class="info-freight">免运费</span></p>
				</a>
			</#list>	
			</div>			
		</div>

		<div class="waterfall-footer">
			<div class="waterfall-status-done" style="display: none;">没有更多宝贝啦~</div>
			<div class="waterfall-status-loading" style="display: none;"></div>
			<div class="waterfall-status-error" style="display: none;">加载失败</div>
		</div>
	</section>
	
</div>

</body>
</html>