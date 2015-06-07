<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<meta content="telephone=no" name="format-detection">
	<meta name="baidu-site-verification" content="t7oDT96Amk" />
	<title>微猫商城</title>
	<meta content="微猫商城购物，正品商城，品牌特卖会" name="keywords">
	<meta content="微猫商城购物，正品商城，品牌特卖会" name="description">
	<meta name="sogou_site_verification" content="G7nmLR75yc" />
	<meta name="baidu-tc-cerfication"
	content="0a8c6d28b570b218f78510c29be4529b" />
	<meta name="360-site-verification"
	content="8b6121969d78afda8caeb69053fa29d9" />
	<script src="${base}/lib/jquery-2.1.1.min.js"></script>
	<script src="${base}/lib/jquery-ui.min.js"></script>

	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/base.css" charset="gbk">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/extend.css" charset="gbk">
	
	<link href="${base}/lib/jd/cart/css/title-bar.css" media="all" rel="stylesheet" type="text/css">
	<link href="${base}/lib/jd/cart/css/cart/btn.css" media="all" rel="stylesheet" type="text/css">
	<link href="${base}/lib/jd/cart/css/checkbox.css" media="all" rel="stylesheet" type="text/css">
	<link href="${base}/lib/jd/cart/css/spinner.css" media="all" rel="stylesheet" type="text/css">
	<link href="${base}/lib/jd/cart/css/shopping-cart.css" media="all" rel="stylesheet" type="text/css">
</head>
<body id="body">
    <div id="notEmptyCart" style="display:block">
        <ul class="shp-cart-list">
        
        	<#assign allamount = 0>
        	<#assign allgoodsnums = 0>
            <#list obj.shopcartgoods as cartgoods>
            
            <#assign allamount = allamount + (((cartgoods.saleprice+0)?number)/10)>
            <#assign allgoodsnums = allgoodsnums + (((cartgoods.nums+0)?number)/10)>
            <input type="hidden" name="id" value="${cartgoods.id}">
            <li id="product${cartgoods.id}">
                <a id="shopping${cartgoods.id}" href="" class="shp-cart-conditions-link" style="display: none"></a>
                <div class="items" id="product1279196" name="item183307847">
                    <div class="check-wrapper">
                        <span class="cart-checkbox checked" id="checkIcon1279196" onclick="changeSelected('183307847','1','13',1279196,1)"></span>
                    </div>
                    <div class="shp-cart-item-core ">
                        <div class="cart-product-cell-3">
                            <span class="shp-cart-item-price" id="price1833078471279196">￥${cartgoods.saleprice}</span>
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
    </div>
    <div id="payment_p" style="display:block">
        <div id="paymentp"></div>
        <div class="payment-total-bar" id="payment">
            <div class="shp-chk">
                <span onclick="checkAllHandler();" class="cart-checkbox checked" id="checkIcon-1"></span>
            </div>
            <div class="shp-cart-info">
                <strong class="shp-cart-total">总计:￥<span class="" id="cart_realPrice">${allamount}</span></strong>
                <span class="sale-off">订单数:<span class="bottom-bar-price" id="cart_oriPrice"><b>${obj.shopcartgoods?size}</b></span><span class="sale-off">商品数:<span class="bottom-bar-price" id="cart_oriPrice"><b>${allgoodsnums}</b></span><span class="sale-off">商品总额:<b>￥<span class="bottom-bar-price" id="cart_oriPrice">${allamount}</span></b> 返现:<b>￥<span class="bottom-bar-price" id="cart_rePrice">0.00</span></b></span>
            </div>
            <a class="btn-right-block" id="submit" style="background-color: rgb(192, 0, 0); background-position: initial initial; background-repeat: initial initial;">结算(<span id="checkedNum">${allgoodsnums}</span>)</a>
        </div>
    </div>
    
<script>
$("#submit").click(function() {page_settlement()});

function page_settlement()
{
	var ids = [];
	var fids = $('#notEmptyCart input[name="id"]');
	if(fids.length==0)
	{
		alert("购物车是空的，没有需要结算的商品。");
		return;
	}

	for(i=0;i<fids.length;i++)
	{
		ids.push(fids[i].value);
	}
	
	$.ajax({
		type:'post',
		url:'${base}/order/shopcart/settlement.action',
		contentType: "application/json",
		data:JSON.stringify({"ids":ids}),
		cache:false,
		async:false,
		success:function(data)
		{
			console.log(data);
			if(data=="")
			{
				alert("提交订单异常！");
				return;
			}
			json = eval("(" + data + ")");
			if(json.state=="success")
			{
				alert("成功提交订单！");
				window.location.reload();
				// 更新购物车数量等操作；
			}
			else
			{
				alert("提交订单失败！");
			}
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})
}

</script>        
</body>
</html>