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
    <form id="formcart" method="post" action="${base}/order/shopcart/placeorder.action">
    
    <ul class="shp-cart-list">
    
    	<#assign allamountsale = 0>
    	<#assign allamountpromote = 0>
    	<#assign allgoodsnums = 0>
    	
        <#list obj.ordergoodses as goods>
        
        <#assign allamountsale = allamountsale + goods.amountsale!?number>
        <#assign allamountpromote = allamountpromote + goods.amountpromote!?number>
        <#assign allgoodsnums = allgoodsnums + goods.nums!?number>

        <input type="hidden" name="id" value="${goods.id}">
        <input type="hidden" name="promoteprice" value="${goods.promoteprice}">
        <input type="hidden" name="saleprice" value="${goods.saleprice}">
        
        <li id="product${goods.id}">
            <a id="shopping${goods.id}" href="" class="shp-cart-conditions-link" style="display: none"></a>
            <div class="items" id="product${goods.id}" name="item${goods.id}">
				<div class="check-wrapper">
                    <span class="cart-checkbox checked" id="checkIcon${goods.id}"></span>
                </div>
			
                <div class="shp-cart-item-core">
                    <div class="cart-product-cell-1">
                    <a class="cart-product-cell-1" href="${base}/goods/goods/look.action?id=${goods.goodsid}">
                        <img class="cart-photo-thumb" alt="" src="${base}/${goods.goodspic}" onerror="">
                    </a>
                    </div>
                    
                    <div class="cart-product-cell-2">
                        <p><span>${goods.goodsname}【<span style="color:#ff6666">${goods.nums}</span>】</span></p>
						<p><span>&nbsp;</span></p>
						<p>&nbsp;</p>
               		</div>
               		
               		<div class="cart-product-cell-3">
                		<p><span style="font-size:14px;color:#ff6666" id="amountpromote-${goods_index}">￥${goods.amountpromote!?number?string("0.00")}</span></p>
                        <p><span>&nbsp;</span></p>
                        <p><span><#if obj.order.state=="收货"><a href="javascript:void(0)" class="ordergoods-icon-remove" style="font-size:14px;color:#6e6eff" sid="${goods.id}">同意/拒绝</a></#if></span></p>
                	</div>
                    
                </div>    

        	</div>           
        </li>
        </#list>
    </ul>
    </form>
</div>

<script>
$(".ordergoods-icon-remove").click(function() 
{
	var ordergoodsid = $(this).attr("sid");
	console.log(ordergoodsid);
	window.location = "${base}/order/order/inputgoodstakeover.action?ordergoodsid="+ordergoodsid;
});

</script>
      
</body>
</html>