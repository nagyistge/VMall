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
<div class="viewport">
<#include "/decorator/include/navmain.ftl">

	<div data-spm="" class="content">
	
		<section class="waterfall">
		
		<div class="waterfall-list" id="waterfall-list">
		
		</div>
		
		</section>
		
	</div>
	
	
	</div>

</div>

<script type="text/j-template" id="tpl_topgoods">
<% _.each(topgoods,function(item, index){ %>
	<% console.log("index:"+index); %>
	<% console.log(item); %>
	<% console.log(item.pic); %>
	<div class="waterfall-column" style="width:50%;" id="waterfall-column1">
	
	<% if (index%2==0) { %>
	<a href="${base}/goods/goods/look.action?id=<%=item.id%>" class="waterfall-item auction">
	<img style="display:block; width: 194px; height: 194px;" class="" src="<%if(item.pic=='') {%>${base}/css/img/default.png<%}else{%>${base}/<%=item.pic%><%}%>" onerror="javascript:this.src='${base}/css/img/default.png'">
	<p style="font-size:10px;color:#8e8e8e"><%=item.cname%></p>
	<p>¥<span class="promote-price"><%=item.promoteprice%></span>¥<del class="cost-price"><%=item.saleprice%></del></p>
	<p><span class="info-sum">人气：<%=item.popular%>&nbsp;&nbsp;月销：<%=item.sales%></span><span class="info-freight"><#--免运费--></span></p>
	</a>
	<% } %>
	
	</div>
	
	<div class="waterfall-column" style="width:50%;" id="waterfall-column2">

	<% if (index%2==1) { %>
	<a href="${base}/goods/goods/look.action?id=<%=item.id%>" class="waterfall-item auction">
	<img style="display:block; width: 194px; height: 194px;" class="" src="<%if(item.pic=='') {%>${base}/css/img/default.png<%}else{%>${base}/<%=item.pic%><%}%>" onerror="javascript:this.src='${base}/css/img/default.png'">
	<p style="font-size:10px;color:#8e8e8e"><%=item.cname%></p>
	<p>¥<span class="promote-price"><%=item.promoteprice%></span>¥<del class="cost-price"><%=item.saleprice%></del></p>
	<p><span class="info-sum">人气：<%=item.popular%>&nbsp;&nbsp;月销：<%=item.sales%></span><span class="info-freight"><#--免运费--></span></p>
	</a>
	<% } %>
	
	</div>
      	
<% }) %>
</script>

<script>

var topgoods;

$(function(){

//////////

function page_topgoods()
{
	console.log("topgoods");
	
	$.ajax({
		type:'post',
		url:'${base}/mall/mall/topgoods.action',
		cache:false,
		async:true,
		success:function(data)
		{
			topgoods = eval("(" + data + ")");
			console.log(topgoods);
			var html = _.template($("#tpl_topgoods").html(), topgoods);
			// console.log(html);
			$("#waterfall-list").html(html);
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})
}

page_topgoods();

//////////
})

</script>

</body>
</html>