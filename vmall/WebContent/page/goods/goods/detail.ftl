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

<script src="${base}/lib/jquery-2.1.1.min.js"></script>
<script src="${base}/lib/underscore/underscore-min.js/"></script>

</head>
<body id="body">
<#include "/decorator/include/navmain.ftl">
<div class="viewport">
	
	<div data-spm="${obj.goods.classinternal}" class="content">
		<p style="font-size:10px;color:#aeaeae;height:30px;">${obj.goods.cname}</p>
		<nav data-spm="1006" class="tabs">
			<div type="info" ctype="商品介绍" data-sort="popular_desc" data-spm-click="" class="tab-item popular_desc active" data-spm-anchor-id="">商品介绍</div>
			<div type="standard" ctype="规格参数" data-sort="sales_desc" data-spm-click="" class="tab-item  sales_desc" data-spm-anchor-id="">规格参数</div>
			<div type="pack" ctype="包装售后" data-sort="price_asc:price_desc" data-spm-click="" class="tab-item sort-price price_asc" data-spm-anchor-id="">包装售后</div>
	    </nav>

		<div class="detail" id="wareinfo" style="display: block;" >
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

<script type="text/j-template" id="tpl_photo">

	<%for(var i=0;i<goodsphotoes.length;i++){%>
	<% var photo = goodsphotoes[i]; console.log(photo); %>
	<p><img src="${base}/<%=photo.url%>" alt="" id="<%=photo.id%>" style="max-width:100%"></p>
	<p><span><%=photo.description%></span></p>
	<%}%>
</script>

<script>
var photos;
var goodsphotoes;
	
$(".tabs>div").click(function(){
	$(".detail").each(function(){
		$(this).hide();
	});
	
	$(".tabs>div").removeClass("active");
	$(this).addClass("active");

	var type = $(this).attr("type");
	var ctype = $(this).attr("ctype");
	
	var goodsid = "${obj.goods.id}";
	console.log("ctype:"+ctype);
	document.getElementById("ware"+type).style.display="block";
	
	$.ajax({
		type:'post',
		url:'${base}/goods/goods/getphoto.action',
		data:{goodsid:goodsid, ctype:ctype},
		cache:false,
		async:true,
		success:function(data)
		{
			console.log(data);
			if(data=="")
			{
				alert("读取信息异常。");
			}
			var json = eval("(" + data + ")");
			console.log("json:"+json);
			
			photos = {goodsphotoes:json.goodsphotoes};
			goodsphotoes = photos.goodsphotoes;
			var html=_.template($("#tpl_photo").html(), photos);
			console.log(html);
			$("#ware"+type).html(html);
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})	
	
	
});


</script>

</body>
</html>