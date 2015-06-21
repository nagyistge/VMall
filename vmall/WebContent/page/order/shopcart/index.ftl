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
        	
            <#list obj.shopcartgoods as cartgoods>
            
            <#assign allamountsale = allamountsale + cartgoods.amountsale!?number>
            <#assign allamountpromote = allamountpromote + cartgoods.amountpromote!?number>
            <#assign allgoodsnums = allgoodsnums + cartgoods.nums!?number>

            <input type="hidden" name="id" value="${cartgoods.id}">
            
            <li id="product${cartgoods.id}">
                <a id="shopping${cartgoods.id}" href="" class="shp-cart-conditions-link" style="display: none"></a>
                <div class="items" id="product${cartgoods.id}" name="item${cartgoods.id}">
					<div class="check-wrapper">
                        <span class="cart-checkbox checked" id="checkIcon${cartgoods.id}"></span>
                    </div>
				
	                <div class="shp-cart-item-core">
	                    <a class="cart-product-cell-1" href="${base}/goods/goods/look.action?id=${cartgoods.goodsid}">
	                        <img class="cart-photo-thumb" alt="" src="${base}/${cartgoods.goodspic}" onerror="">
	                    </a>
	                    
	                    <div class="cart-product-cell-2">
	                        <div class="cart-product-name">
	                            <p><a href="${base}/goods/goods/look.action?id=${cartgoods.goodsid}"><span>${cartgoods.goodsname}</span></a></p>
								<p>
								<span>
								<a class="btn-del" sid="${cartgoods.id}" id="minus${cartgoods.id}">-</a>
								<input type="text" class="fm-txt" value="${cartgoods.nums}" id="nums${cartgoods.id}" onblur="modify();">
								<a class="btn-add" sid="${cartgoods.id}" id="plus${cartgoods.id}">+</a>
								</span>
								</p>
		               		</div>

	               		</div>
	               		
	               		<div class="cart-product-cell-3">
	                		<p><span class="shp-cart-item-price" style="font-size:10px" id="saleprice${cartgoods.id}">￥${cartgoods.saleprice!?number?string("0.00")}/${cartgoods.promoteprice!?number?string("0.00")}</span>
	                        <p><span class="shp-cart-item-price" style="font-size:14px;color:#ff6666">￥${cartgoods.amountpromote!?number?string("0.00")}</span>
	                		<p><span class="shp-cart-item-price"><a class="shp-cart-icon-remove" href="javascript:void(0)" onclick="page_delete('${cartgoods.id}')"></a></span></p>
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
            <a class="btn-right-block" id="submit" style="background-color: rgb(192, 0, 0); background-position: initial initial; background-repeat: initial initial;">下单(<span id="checkedNum">${allgoodsnums}</span>)</a>
        </div>
    </div>
    
<script>
$("#submit").click(function() {page_submit()});

$(".btn-del").click(function() {

	var sid = $(this).attr("sid");
	console.log("sid:" + sid);
	
	var a = parseInt($("#nums"+sid).val(), 10);
	if (a <= 1) {
		$("#nums"+sid).val(1);
		$("#amount").html("1\u4ef6")
	} else {
		a--;
		$("#nums"+sid).val(a);
		$("#amount").html(a + "\u4ef6")
	}	
});

$(".btn-add").click(function() {
	var sid = $(this).attr("sid");
	console.log("sid:" + sid);
	
	var a = parseInt($("#nums"+sid).val(), 10);
	if (a >= 999) {
		$("#nums"+sid).val(1);
		$("#amount").html("1\u4ef6")
	} else {
		a++;
		$("#nums"+sid).val(a);
		$("#amount").html(a + "\u4ef6")
	}	
});


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

function minus() {
	console.log(this);
	console.log($(this));
	var sid = this.sid;
	console.log("sid:" + sid);
	return;

	var a = parseInt($("#number").val(), 10);
	if (a <= 1) {
		$("#number").val(1);
		$("#amount").html("1\u4ef6")
	} else {
		a--;
		$("#number").val(a);
		$("#amount").html(a + "\u4ef6")
	}
}
function plus() {
	console.log(this);
		console.log($(this));
	var sid = this.sid;
	console.log("sid:" + sid);
	return;

	var a = parseInt($("#number").val(), 10);
	if (a >= 999) {
		$("#number").val(1);
		$("#amount").html("1\u4ef6")
	} else {
		a++;
		$("#number").val(a);
		$("#amount").html(a + "\u4ef6")
	}
}
function modify() {
	var a = parseInt($("#number").val(), 10);
	if ("" == $("#number").val()) {
		$("#number").val(1);
		$("#amount").html("1\u4ef6");
		return
	}
	if (!isNaN(a)) {
		if (1 > a || a > 999) {
			$("#number").val(1);
			$("#amount").html("1\u4ef6");
			return
		} else {
			$("#number").val(a);
			$("#amount").html(a + "\u4ef6");
			return
		}
	} else {
		$("#number").val(1);
		$("#amount").html("1\u4ef6")
	}
}

</script>        
</body>
</html>