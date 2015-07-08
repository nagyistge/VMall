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
	<link href="${base}/lib/jd/css/goods.css" charset="gbk" media="all" rel="stylesheet" type="text/css">		

</head>
<body id="body">
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
                    <a class="cart-product-cell-1" href="${base}/goods/goods/look.action?id=${goods.goodsid}">
                        <img class="cart-photo-thumb" alt="" src="${base}/${goods.goodspic}" onerror="">
                    </a>
                    
                    <div class="cart-product-cell-2">
                        <div class="cart-product-name">
                            <p><a href="${base}/goods/goods/look.action?id=${goods.goodsid}"><span>${goods.goodsname}</span></a></p>
							<p><span>${goods.nums}</span></p>
	               		</div>
               		</div>
               		
               		<div class="cart-product-cell-3">
                		<p><span class="shp-cart-item-price" style="font-size:10px">￥${goods.saleprice!?number?string("0.00")}/${goods.promoteprice!?number?string("0.00")}</span>
                        <p><span class="shp-cart-item-price" style="font-size:14px;color:#ff6666" id="amountpromote-${goods_index}">￥${goods.amountpromote!?number?string("0.00")}</span>
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