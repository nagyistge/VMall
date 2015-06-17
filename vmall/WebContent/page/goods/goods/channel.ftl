<!DOCTYPE html>
<html>
<head>
<#include "/decorator/include/header.ftl">
</head>
<body id="body">
<#include "/decorator/include/navmain.ftl">
<div class="viewport">

	<div data-spm="${obj.goodsclass.internal}" class="content has-header">
		<section data-spm="${obj.goodsclass.internal}" class="list-keys">
		<#list obj.subgoodsclasses as subclass>
			<a href="#" index="0" querykey="${subclass.cname}" data-spm-click="" class="item active">${subclass.cname}</a>
		</#list>		
		</section>
		<section class="relate-collect hide"></section>
		<section class="relate-style hide"></section>
		<section class="waterfall">
		<div class="waterfall-list">
			<div class="waterfall-column" style="width:50%;">
			<#list obj.goods as good>
			<#if good_index % 2 == 0>
				<a href="${base}/goods/goods/look.action?id=${good.id}" class="waterfall-item auction">
					<img style="display: block;" class="" src="${base}/image/goods/demogoods${good_index%5}.jpg">
					<h3>${good.cname}</h3>
					<p>¥<span class="promote-price">${good.promoteprice}</span>¥<del class="cost-price">${good.costprice}</del></p>
					<p><span class="info-sum">月销：470</span><span class="info-freight">免运费</span></p>
				</a>
			</#if>	
			</#list>	
			</div>
			
			<div class="waterfall-column" style="width:50%;">
			<#list obj.goods as good>
			<#if good_index % 2 == 1>
				<a href="${base}/goods/goods/look.action?id=${good.id}" class="waterfall-item auction">
					<img style="display: block;" class="" src="${base}/image/goods/demogoods${(good_index+3)%5}.jpg">
					<h3>${good.cname}</h3>
					<p>¥<span class="promote-price">${good.promoteprice}</span>¥<del class="cost-price">${good.costprice}</del></p>
					<p><span class="info-sum">月销：470</span><span class="info-freight">免运费</span></p>
					</a>
				</#if>
				</#list>
				</div>			
			</div>
		</div>
	</div>

</div>

</body>
</html>