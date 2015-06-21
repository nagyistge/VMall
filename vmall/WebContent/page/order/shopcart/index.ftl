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
        <form id="formcart" method="POST">
		<input type="hidden" id="keysignature" name="keysignature" value="${obj.keysignature}">        
        
        <ul class="shp-cart-list">
        
        	<#assign allamountsale = 0>
        	<#assign allamountpromote = 0>
        	<#assign allgoodsnums = 0>
        	
            <#list obj.shopcartgoods as cartgoods>
            
            <#assign allamountsale = allamountsale + cartgoods.amountsale!?number>
            <#assign allamountpromote = allamountpromote + cartgoods.amountpromote!?number>
            <#assign allgoodsnums = allgoodsnums + cartgoods.nums!?number>

            <input type="hidden" name="id" value="${cartgoods.id}">
            <input type="hidden" name="promoteprice" value="${cartgoods.promoteprice}">
            <input type="hidden" name="saleprice" value="${cartgoods.saleprice}">
            
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
								<input type="text" class="fm-txt" value="${cartgoods.nums}" id="nums${cartgoods.id}" name="nums" onblur="page_sum();">
								<a class="btn-add" sid="${cartgoods.id}" id="plus${cartgoods.id}">+</a>
								</span>
								</p>
		               		</div>

	               		</div>
	               		
	               		<div class="cart-product-cell-3">
	                		<p><span class="shp-cart-item-price" style="font-size:10px">￥${cartgoods.saleprice!?number?string("0.00")}/${cartgoods.promoteprice!?number?string("0.00")}</span>
	                        <p><span class="shp-cart-item-price" style="font-size:14px;color:#ff6666" id="amountpromote-${cartgoods_index}">￥${cartgoods.amountpromote!?number?string("0.00")}</span>
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
                <strong class="shp-cart-total">总计：￥<span class="" id="cart_promoteprice_all" style="font-weight:bolder">${allamountpromote}</span></strong>
                <span class="sale-off">原价总计：￥<span class="bottom-bar-price" id="cart_saleprice_all" style="font-weight:bolder">${allamountsale}</span>
                <span class="sale-off">明细单数：<span class="bottom-bar-price" id="cart_item_all" style="font-weight:bolder">${obj.shopcartgoods?size}</span>
                <span class="sale-off">商品数量：<span class="bottom-bar-price" id="cart_goods_all" style="font-weight:bolder">${allgoodsnums}</span>
            </div>
            <a class="btn-right-block" id="submit" style="background-color: rgb(192, 0, 0); background-position: initial initial; background-repeat: initial initial;"><span>下单(</span><span id="checkedNum">${allgoodsnums}</span><span>)</span></a>
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
	
	page_sum();
		
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
	
	page_sum();
});

$(".fm_txt").click(function() {
	var sid = $(this).attr("sid");
	console.log("sid:" + sid);
});

function page_submit()
{
	var ids = [];
	var numses = [];
	var fids = $('#notEmptyCart input[name="id"]');
	var fnums = $('#notEmptyCart input[name="nums"]');
	var keysignature = $("#keysignature").val();
	
	if(fids.length==0)
	{
		alert("购物车是空的，没有需要结算的商品。");
		return;
	}

    // 增加检查哪些购物车商品提交订单
	for(i=0;i<fids.length;i++)
	{
		ids.push(fids[i].value);
		numses.push(fnums[i].value);
	}
	
	if(ids.length==0)
	{
		alert("亲，无法下单，看看你是不是忘了选中购物车里的物品了？");
		return;		
	}
	
	$.ajax({
		type:'POST',
		url:'${base}/order/shopcart/placeorder.action',
		contentType: "application/json",
		data:JSON.stringify({"ids":ids,"numses":numses,"keysignature":keysignature}),
		cache:false,
		async:false,
		success:function(data)
		{
			console.log(data);
			if(data=="")
			{
				alert("提交订单异常，请检查后再试试！");
				return;
			}
			json = eval("(" + data + ")");
			if(json.state=="success")
			{
				window.location = "${base}/order/order/look.action?id=" + json.id;
			}
			else
			{
				alert("提交订单失败："+json.message);
			}
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})	
	
    
}

// 计算各项合计总计
function page_sum()
{
	var nums_all = 0;
	var promoteprice_all = 0;
	var saleprice_all = 0;
	
	var fm_txts = $(".fm-txt");
	var fpromoteprices = $('#notEmptyCart input[name="promoteprice"]');
	var fsaleprices = $('#notEmptyCart input[name="saleprice"]');
	
	for(var i=0;i<fm_txts.length;i++)
	{
		var anum = parseInt(fm_txts[i].value, 10);
		var apromoteprice = parseInt(fpromoteprices[i].value, 10);
		var asaleprice = parseInt(fsaleprices[i].value, 10);	
			
		if(isNaN(apromoteprice))
		{
			continue;
		}
		
		if(isNaN(asaleprice))
		{
			continue;
		}
			
		if(isNaN(anum))
		{
			fm_txts[i].value=1;
			nums_all = nums_all + 1;
		}
		else
		{
			nums_all = nums_all + anum;
		}
		
		promoteprice_all = promoteprice_all + (apromoteprice * anum);
		saleprice_all = saleprice_all + (asaleprice * anum);
				
		$("#checkedNum").html(nums_all);
		$("#cart_item_all").html();
		
		$("#cart_goods_all").html(nums_all);
		$("#cart_promoteprice_all").html(promoteprice_all);
		$("#cart_saleprice_all").html(saleprice_all);
		console.log($("#amountpromote"+i));
		$("#amountpromote-"+i).html("￥"+(apromoteprice * anum));
			
	}
}

</script>        
</body>
</html>