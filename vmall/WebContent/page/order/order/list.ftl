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

<#list obj.orders as order>

<li id="order${order.id}">
    <div class="items" id="items${order.id}" name="item">
		<div class="check-wrapper">
			<span style="color:#dedede">
	            <p>&nbsp;</p>
	            <p>${order_index+1}</p>
	            <p>&nbsp;</p>	
			</span>
        </div>
        <div class="shp-cart-item-core">
        
        	<div class="cart-product-cell-2">
                <div class="cart-product-name">
                    <p><span><a href="${base}/order/order/look.action?id=${order.id}" style="font-size:14px;color:#aeaede">${order.cno}</a></span><sapn>&nbsp;&nbsp;</span><span style="font-size:10px;color:#dedede">${order.membercname}</span></p>
                    <p><span>&nbsp;</span></p>
                    <p><span style="font-size:14px;color:#dedede">${order.takeaddress}</span></p>
           		</div>
       		</div>
       		
			<div class="cart-product-cell-3">
        		<p>￥<span style="font-size:18px;color:#ff6666">${order.amount?number?string("0.00")}</span></p>
        		<p><span style="font-size:12px;color:#aeaede">${order.state}</span></p>
	       	</div>       		
        </div>
	</div>           
</li>
</#list>
</body>
</html>





<#--
<#list obj.orders as order>
<li id="order${order.cno}">
<div class="items">
    <div class="check-wrapper">
        <span id="checkIcon1196557" class="cart-checkbox checked"></span>
    </div>
    <div>
 		<span style="margin-right:10px;font-size:18px"><a href="javascript:void(0)" onclick="page_loadordergoods('${order.id}')" style="color:#cecdce">${order.cno}</a></span>	
 		<span style="margin-right:10px;color:#cecdce">${(order.ordertime?datetime?string("HH:mm"))!''}</span>
 		<span style="margin-right:10px;color:#cecdce">${order.state}</span> 		
 		<span>￥<span style="margin-right:20px;font-size:24px;color:#ff6666">${order.amount}</span></span>
 		<span><a href="${base}/order/order/look.action?id=${order.id}" style="color:#cecece">详细</a></span>
 		<a class="shp-cart-icon-remove" href="javascript:void(0)" onclick="page_deleteorder('${order.id}')"></a>		
    </div>
</div>
<div id="ordergoods${order.id}">
</div>
</li>
</#list>
-->