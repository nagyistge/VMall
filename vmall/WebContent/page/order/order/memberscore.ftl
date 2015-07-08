<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/home/css/base2013.css" charset="gbk">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/score.css" charset="gbk">
	<link href="${base}/lib/jd/cart/css/shopping-cart.css" media="all" rel="stylesheet" type="text/css">	
	<#include "/decorator/include/header.ftl">
</head>
<body id="body">
	<#include "/decorator/include/navmain.ftl">
		<div class="common-wrapper">
			<ul class="concern-con">
				<li>
					<span class="p-price">${obj.sumscore}</span>
				</li>
			</ul>
		</div>
		<div id="wareHistoryDiv" class="common-wrapper">
			<ul class="concern-con">
				<#list obj.scores as score>
				<li>
					<span class="p-img"><img src="http://img12.360buyimg.com/n2/jfs/t175/14/2182635453/71587/66a2e553/53c8cd50N7b9c1079.jpg" alt=""></span>
					<span class="p-price">${score.score}/${score.level}</span>
					<span class="p-name">${score.ordergoodsname}</span>
				</li>
				</#list>	
			</ul>
		</div>

		<div id="autoHistoryHeight"></div>
		
    <div id="payment_p" style="display:block">
        <div id="paymentp"></div>
        <div class="payment-total-bar" id="payment">
            <div class="shp-chk">
                <span onclick="checkAllHandler();" class="cart-checkbox checked" id="checkIcon-1"></span>
            </div>
            <div class="shp-cart-info">
                <strong class="shp-cart-total">总计:￥<span class="" id="cart_realPrice">${obj.sumscore}</span></strong>
                <span class="sale-off">提现条数:
                	<span class="bottom-bar-price" id="cart_oriPrice"><b>${obj.scores?size}</b></span>
                	<span class="sale-off">
                	提现总额:<b>￥<span class="bottom-bar-price" id="cart_oriPrice">${obj.sumscore}</span></b> 
                	返现:<b>￥<span class="bottom-bar-price" id="cart_rePrice">0.00</span></b>
                	</span>
                </span>	
            </div>
            <a class="btn-right-block" id="submit" style="background-color: rgb(192, 0, 0); background-position: initial initial; background-repeat: initial initial;">结算(<span id="checkedNum">${obj.scores?size}</span>)</a>
        </div>
    </div>		

<script>
$("#submit").click(function() {page_applydraw()});
function page_applydraw()
{
	window.location = "${base}/order/drawcash/apply.action";
}
</script>

</body>
</html>

