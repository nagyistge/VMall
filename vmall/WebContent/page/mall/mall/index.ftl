<!DOCTYPE html>
<html>
<head>
<#include "/decorator/include/header.ftl">
</head>
<body id="body">
<div class="viewport">
<#include "/decorator/include/navmain.ftl">
	<div class="content">
		<div data-spm="1003" id="floor-nav" class="list-icon">
		<h3>热门市场</h3>
	    <div>
	    <a class="item" href="/channel.html?id=1&amp;leftNav=true" target="_top"><i class="icon icon-woman-clear"></i>女装</a>
		<a class="item" href="/channel.html?id=2&amp;leftNav=true" target="_top"><i class="icon icon-man-clear"></i>男装</a>
		<a class="item" href="/channel.html?id=4&amp;leftNav=true" target="_top"><i class="icon icon-shoe-clear"></i>鞋品</a>
		<a class="item" href="/channel.html?id=3&amp;leftNav=true" target="_top"><i class="icon icon-handbag-clear"></i>箱包</a>
		<a class="item" href="/channel.html?id=5&amp;leftNav=true" target="_top"><i class="icon icon-infant-clear"></i>母婴</a>
		<a class="item" href="/channel.html?id=6&amp;leftNav=true" target="_top"><i class="icon icon-home-furnishing-clear"></i>生活</a>
		<a class="item" href="/channel.html?id=7&amp;leftNav=true" target="_top"><i class="icon icon-beauty-clear"></i>护肤</a>
		<a class="item" href="/channel.html?id=8&amp;leftNav=true" target="_top"><i class="icon icon-food-clear"></i>美食</a>
		</div>
	</div>
	<div><a style="font-size:18px" href="weixin://profile/gh_3482de7975be">TEST</a></div>
	<#list obj.goodsclasses as goodsclass>
	
	<div data-spm="${goodsclass.internal}" class="floor floor-man">
        <h3><a href="${base}/goods/goods/channel.action?classid=${goodsclass.id}&amp;internal=${goodsclass.internal}" target="_top"><span></span>${goodsclass.cname}<span>${goodsclass.cname}</span></a></h3>
        	<div class="hot-key">
        		<#if goodsclass.subpostgoodsclasses?size &gt; 0>
        		<#assign subpostgoodsclass = goodsclass.subpostgoodsclasses[0]>
			    <a href="${base}/goods/goods/channel.action?classid=${subpostgoodsclass.id}" target="_top" class="hot-key-main">
				    <img style="width:92px;height:96px;display:block;" class="" src="${base}/${subpostgoodsclass.pic!}">
				    <p>最新单凉鞋</p>
				    <p>最适合这天气的鞋</p>
			    </a>
			    </#if>
			    <div class="hot-key-sub">
			    	<#if goodsclass.subpostgoodsclasses?size &gt; 1>
			    	<#assign subpostgoodsclass = goodsclass.subpostgoodsclasses[1]>
				    <a href="${base}/goods/goods/channel.action?classid=${subpostgoodsclass.id}" target="_top">
				    <img style="width:44px;height:44px;display:block;" class="" src="${base}/${subpostgoodsclass.pic!}">
				    <p>${subpostgoodsclass.cname}</p>
				    </a>
				    </#if>
			    	<#if goodsclass.subpostgoodsclasses?size &gt; 2>
			    	<#assign subpostgoodsclass = goodsclass.subpostgoodsclasses[2]>
				    <a href="${base}/goods/goods/channel.action?classid=${subpostgoodsclass.id}" target="_top">
				    <img style="width:44px;height:44px;display:block;" class="" src="${base}/${subpostgoodsclass.pic!}">
				    <p>${subpostgoodsclass.cname}</p>
				    </a>
				    </#if>
		    	</div>
			</div>
		    <div data-id="4" data-count="8" data-keys="" style="height:90px" class="list-keys">
		    	<#list goodsclass.subgoodsclasses as subclass>
		    		<#if subclass_index &lt; 8>
			        <a href="${base}/goods/goods/channel.action?classid=${subclass.id}&amp;internal=${subclass.internal}" target="_top" extra="" class="item">${subclass.cname}</a>
		    		</#if>
		    	</#list>
		    </div>
	
	</div>
	</#list>
</div>

<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>

alert("${obj.shareurl!}");
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
	    title: '测试一下，点了看看', 
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