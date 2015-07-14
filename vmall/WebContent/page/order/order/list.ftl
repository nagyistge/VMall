<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/base.css" charset="gbk">
<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/extend.css" charset="gbk">

<link href="${base}/lib/jd/cart/css/title-bar.css" media="all" rel="stylesheet" type="text/css">
<link href="${base}/lib/jd/cart/css/cart/btn.css" media="all" rel="stylesheet" type="text/css">
<link href="${base}/lib/jd/cart/css/checkbox.css" media="all" rel="stylesheet" type="text/css">
<link href="${base}/lib/jd/cart/css/spinner.css" media="all" rel="stylesheet" type="text/css">
<link href="${base}/lib/jd/cart/css/shopping-cart.css" media="all" rel="stylesheet" type="text/css">
<#include "/decorator/include/header.ftl">
</head>
<body id="body" style="background:#ffffff">
<#include "/decorator/include/navmain.ftl">
<div id="notEmptyCart" style="display:block">
	<form id="form_browse" method="POST">
	<ul class="shp-cart-list" id="shp-cart-list">
<#list obj.orders as order>

<li id="order${order.id}">
    <div class="items" id="items${order.id}" name="item">
		<div class="check-wrapper">
			<span style="color:#6e6e6e">
	            <p><span>&nbsp;</span></p>
	            <p><span>${order_index+1}</span></p>	
			</span>
        </div>
        <div class="shp-cart-item-core"  style="color:#dedede;font-size:12px">
        
        	<div class="cart-product-cell-2">
                <p>
                <span style="color:#6e6e6e"><a href="${base}/order/order/look.action?id=${order.id}">${order.cno}</a></span>
                <sapn>&nbsp;&nbsp;</span>
                <span>${order.ordertime?datetime?string("HH:mm")}</span>
                </p>
                <p><span>${order.sellername}</span></p>
                <p><span>${order.takeaddress}</span></p>
       		</div>
       		
			<div class="cart-product-cell-3">
        		<p>￥<span style="color:#ff6666;">${order.amount?number?string("0.00")}</span></p>
        		<p><span>${order.paystate}/${order.state}</span></p>
        		<p><span><#if order.state=="下单"><a class="order-icon-remove" style="color:#6e6eff" sid="${order.id}">删除</a></#if></span></p>
	       	</div>       		
        </div>
	</div>           
</li>
</#list>	
	</ul>
	</form>
</div>
</body>
</html>