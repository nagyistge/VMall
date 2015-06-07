<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta content="telephone=no" name="format-detection" />

<link rel="stylesheet" type="text/css" href="${base}/lib/tb/css/neat-min.css">
<link rel="stylesheet" type="text/css" href="${base}/lib/tb/css/ui-min.css">
<link rel="stylesheet" type="text/css" href="${base}/lib/tb/css/index-min.css">
<link rel="stylesheet" type="text/css" href="${base}/lib/jd/css/layout.min.css">
<link rel="stylesheet" type="text/css" href="${base}/lib/jd/css/common.min.css">
</head>
<body class="u-index">
<#include "/decorator/include/navmain.ftl">
<div class="viewport">
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
	
	<#list obj.goodsclasses as class>
	
	<div data-spm="${class.internal}" class="floor">
        <h3><a href="${base}/goods/goods/channel.action?classid=${class.id}&amp;internal=${class.internal}" target="_top"><span></span>${class.cname}<span>2015${class.cname}</span></a></h3>
		    <div class="hot-key">
			    <a href="/search.html?q=2015单凉鞋&amp;back=true" target="_top" class="hot-key-main">
				    <img style="width:92px;height:96px;display:block;" class="" src="//gtms03.alicdn.com/tps/i3/TB1KND3HFXXXXbyXFXXPXbf0FXX-184-192.jpg">
				    <p>最新单凉鞋</p>
				    <p>最适合这天气的鞋</p>
			    </a>
			    <div class="hot-key-sub">
				    <a href="/search.html?q=罗马凉鞋&amp;back=true" target="_top">
				    <img style="width:44px;height:44px;display:block;" class="" src="//gtms04.alicdn.com/tps/i4/TB1V.QtHFXXXXaIXVXXiDSbJFXX-88-88.png">
				    <p>罗马凉鞋</p>
				    </a>
				    <a href="/search.html?q=韩版运动鞋&amp;back=true" target="_top">
				    <img style="width:44px;height:44px;display:block;" class="" src="//gtms01.alicdn.com/tps/i1/TB13QrTHFXXXXbmaXXXEDmbJFXX-88-88.jpg">
				    <p>运动鞋</p>
				    </a>
		    	</div>
			</div>
		    <div data-id="4" data-count="8" data-keys="最新单凉鞋,罗马凉鞋,运动鞋" style="height:90px" class="list-keys">
		    	<#list class.subgoodsclasses as subclass>
		    		<#if subclass_index &lt; 8>
			        <a href="${base}/goods/goods/channel.action?classid=${subclass.id}&amp;internal=${subclass.internal}" target="_top" extra="" class="item">${subclass.cname}</a>
		    		</#if>
		    	</#list>
		    </div>
	
	</div>
	
	</#list>

</div>
</body>
</html>