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
<a href="http://mp.weixin.qq.com/s?__biz=MzI5NDAyODY5Mg==&mid=209013408&idx=1&sn=457de9a2babe75837302bcf10b4bac69&scene=0#rd">关注天狗微商城</a>

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

	<div class="waterfall-column" style="width:50%;" id="waterfall-column1">
	
	<% if (index%2==0) { %>
	<a href="${base}/goods/goods/look.action?id=<%=item.id%>" class="waterfall-item auction">
	<img style="display:block;" class="" src="<%if(item.pic=='') {%>${base}/css/img/default.png<%}else{%>${base}/<%=item.pic%><%}%>">
	<h3 style="font-size:10px;color:#8e8e8e"><%=item.cname%></h3>
	<p>¥<span class="promote-price"><%=item.promoteprice%></span>¥<del class="cost-price"><%=item.saleprice%></del></p>
	<p><span class="info-sum">人气：<%=item.popular%>&nbsp;&nbsp;月销：<%=item.sales%></span><span class="info-freight"><#--免运费--></span></p>
	</a>
	<% } %>
	
	</div>
	
	<div class="waterfall-column" style="width:50%;" id="waterfall-column2">

	<% if (index%2==1) { %>
	<a href="${base}/goods/goods/look.action?id=<%=item.id%>" class="waterfall-item auction">
	<img style="display:block;" class="" src="<%if(item.pic=='') {%>${base}/css/img/default.png<%}else{%>${base}/<%=item.pic%><%}%>">
	<h3 style="font-size:10px;color:#8e8e8e"><%=item.cname%></h3>
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

<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>

<#if obj.jscfg??>
 
wx.config({
	debug: true,
	appId: '${obj.jscfg.appId!}',
	timestamp: ${obj.jscfg.timestamp!},
	nonceStr: '${obj.jscfg.nonceStr!}',
	signature: '${obj.jscfg.signature!}',
	jsApiList: [
	    'checkJsApi',
	    'onMenuShareTimeline',
	    'onMenuShareAppMessage',
	    'onMenuShareQQ',
	    'onMenuShareWeibo']
});

</#if>   

<#if obj.shareurl??>

wx.ready(function()
{
	//config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
    wx.onMenuShareAppMessage({
	    title: '天狗微商城', 
	    desc: '${obj.shareurl!}', 
	    link: '${obj.shareurl!}', 
	    imgUrl: '', 
	    type: '', 
	    dataUrl: '', 
	    success: function () { 
	        
	    },
	    cancel: function () { 
	    }
	});
});
  	
</#if>	

</script>

</body>
</html>