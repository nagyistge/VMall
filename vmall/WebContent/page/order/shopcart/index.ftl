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

    <div id="notEmptyCart" style="display:block">
        <form id="formcart" method="post" action="${base}/order/shopcart/placeorder.action">
        <ul class="shp-cart-list">
        
        	<#assign allamountsale = 0>
        	<#assign allamountpromote = 0>
        	<#assign allgoodsnums = 0>

            <#list obj.shopcartgoods as cartgoods>
            
            <#assign allamountsale = allamountsale + cartgoods.amountsale!?number>
            <#assign allamountpromote = allamountpromote + cartgoods.amountpromote!?number>
            <#assign allgoodsnums = allgoodsnums + cartgoods.nums!?number>
            <input type="hidden" name="id" value="${cartgoods.id}">
            <li id="product${cartgoods.id}">
                <a id="shopping${cartgoods.id}" href="" class="shp-cart-conditions-link" style="display: none"></a>
                <div class="items" id="product1279196" name="item183307847">
                    <div class="check-wrapper">
                        <span class="cart-checkbox checked" id="checkIcon1279196" onclick="changeSelected('183307847','1','13',1279196,1)"></span>
                    </div>
                    <div class="shp-cart-item-core ">
                        <div class="cart-product-cell-3">
                            <span class="shp-cart-item-price" id="saleprice${cartgoods.id}">￥${cartgoods.saleprice}/</span>
                            <span class="shp-cart-item-price" style="font-size:18px;color:#ff6666" id="promoteprice${cartgoods.id}">${cartgoods.promoteprice}</span>
                            
                        </div>
                        <a class="cart-product-cell-1" href="${base}/goods/goods/look.action?id=${cartgoods.goodsid}">
                            <img class="cart-photo-thumb" alt="" src="http://img10.360buyimg.com/n7/jfs/t580/67/493022257/56071/c6721088/546d968aN87849b99.jpg!q70.jpg" onerror="http://misc.360buyimg.com/lib/skin/e/i/error-jd.gif">
                        </a>
                        <div class="cart-product-cell-2">
                            <div class="cart-product-name">
                                <a href="${base}/goods/goods/look.action?id=${cartgoods.goodsid}"><span>${cartgoods.goodsname}</span></a>
                            </div>
                            <div class="shp-cart-opt">
                                <div class="quantity-wrapper">
                                    <input type="hidden" id="limitSukNum1279196" value="200">
                                    <a class="quantity-decrease disabled" id="subnum1279196" href="javascript:subWareBybutton('183307847','1',13,1279196,1)">-</a>
                                    <input type="text" size="4" value="${cartgoods.nums}" name="num" id="num1279196" class="quantity" onchange="modifyWare('183307847','1',13,1279196,1)">
                                    <a class="quantity-increase" id="addnum1279196" href="javascript:addWareBybutton('183307847','1',13,1279196,1,1)">+</a>
                                </div>
                                <a class="shp-cart-icon-remove" id="addnum1279196" href="javascript:deleteWare('183307847','1',13,1279196,1)"></a>
                            </div>
                        </div>
                    </div>
                </div>        
            </li>
            </#list>
        </ul>
        </form>
    </div>
    <div id="payment_p" style="display:block">
        <div id="paymentp"></div>
        <div class="payment-total-bar" id="payment">
            <div class="shp-chk">
                <span onclick="checkAllHandler();" class="cart-checkbox checked" id="checkIcon-1"></span>
            </div>
            <div class="shp-cart-info">
                <strong class="shp-cart-total">总计：￥<span class="" id="cart_realPrice">${allamountpromote}</span></strong>
                <span class="sale-off">原价总计：<b>￥<span class="bottom-bar-price" id="cart_oriPrice">${allamountsale}</b></span>
                <span class="sale-off">明细单数：<span class="bottom-bar-price" id="cart_oriPrice"><b>${obj.shopcartgoods?size}</b></span>
                <span class="sale-off">购买产品数量：<span class="bottom-bar-price" id="cart_oriPrice"><b>${allgoodsnums}</b></span>
            </div>
            <a class="btn-right-block" id="submit" style="background-color: rgb(192, 0, 0); background-position: initial initial; background-repeat: initial initial;">结算(<span id="checkedNum">${allgoodsnums}</span>)</a>
        </div>
    </div>
    
<script>
$("#submit").click(function() {page_submit()});

function page_submit()
{
	var ids = [];
	var fids = $('#notEmptyCart input[name="id"]');
	if(fids.length==0)
	{
		alert("购物车是空的，没有需要结算的商品。");
		return;
	}

    // 增加检查哪些购物车商品提交订单
	for(i=0;i<fids.length;i++)
	{
		ids.push(fids[i].value);
	}
	
    $("#formcart").submit();
}

</script>        
</body>
</html>