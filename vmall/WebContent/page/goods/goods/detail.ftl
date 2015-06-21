<!DOCTYPE html>
<html>
<head>
<#include "/decorator/include/header.ftl">
<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/base.css" charset="gbk">
<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/extend.css" charset="gbk">

<link href="${base}/lib/jd/cart/css/title-bar.css" media="all" rel="stylesheet" type="text/css">
<link href="${base}/lib/jd/cart/css/cart/btn.css" media="all" rel="stylesheet" type="text/css">
<link href="${base}/lib/jd/cart/css/checkbox.css" media="all" rel="stylesheet" type="text/css">
<link href="${base}/lib/jd/cart/css/spinner.css" media="all" rel="stylesheet" type="text/css">
<link href="${base}/lib/jd/cart/css/shopping-cart.css" media="all" rel="stylesheet" type="text/css">
</head>
<body id="body">
<#include "/decorator/include/navmain.ftl">
<div class="viewport">
	
	<div data-spm="${obj.goodsclass.internal}" class="content">
		<p style="font-size:10px;color:#aeaeae;height:30px;">${obj.goods.cname}</p>
		<nav data-spm="1006" class="tabs">
			<div type="info" data-sort="popular_desc" data-spm-click="" class="tab-item popular_desc active" data-spm-anchor-id="">商品介绍</div>
			<div type="standard" data-sort="sales_desc" data-spm-click="" class="tab-item  sales_desc" data-spm-anchor-id="">规格参数</div>
			<div type="pack" data-sort="price_asc:price_desc" data-spm-click="" class="tab-item sort-price price_asc" data-spm-anchor-id="">包装售后</div>
	    </nav>

		<div class="detail" id="wareinfo" style="display: block;">
            <#list obj.goodsphotoes as photo>
            <#if photo.ctype="商品介绍">
            <p><img src="${base}/${photo.url}" alt="" id="${photo.id}" style="max-width:100%"></p>
			<p><span>${photo.description}</span></p>
            </#if>
            </#list>
        </div>
		<div class="detail" id="warestandard" style="display: none;">
        </div>
		<div class="detail" id="warepack" style="display: none;">
        </div>


	</div>

</div>

<script>	
$(".tabs>div").click(function(){
	$(".detail").each(function(){
		$(this).hide();
	});
	
	$(".tabs>div").removeClass("active");
	$(this).addClass("active");

	var type = $(this).attr("type");
	
	document.getElementById("ware"+type).style.display="block";
});


</script>

</body>
</html>