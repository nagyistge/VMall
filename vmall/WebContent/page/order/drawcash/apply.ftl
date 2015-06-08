<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/home/css/base2013.css" charset="gbk">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/home/css/address.css" charset="gbk">
	<link href="${base}/lib/jd/cart/css/shopping-cart.css" media="all" rel="stylesheet" type="text/css">	
	<#include "/decorator/include/header.ftl">
</head>
<body id="body">
<#include "/decorator/include/navmain.ftl">
<style>
.new-title {font-size:12px;color:#aeaeae}
</style>
<form id="form_draw" method="POST" action="${base}/order/drawcash/insert.action">
<input type="hidden" id="cname" name="cname" value="${obj.member.cname}">
<input type="hidden" id="mobile" name="mobile" value="${obj.member.mobile}">
<input type="hidden" id="bank" name="bank" value="${obj.member.bank}">
<input type="hidden" id="bankacountno" name="bankacountno" value="${obj.member.bankacountno}">

<div class="new-ct">
	<div class="new-addr">
		<ul class="new-mu_l2w">
	        <li class="new-mu_l2">
	    	<p class="new-tit new-p-re">
				<span class="new-title">姓名：</span><span class="new-txt" style="font-size:20px">${obj.member.cname}</span>
				<span class="new-title">手机：</span><span class="new-txt" style="font-size:20px">${obj.member.mobile}</span>
				<span class="new-txt-rd2 new-option-r"><span class="new-icon"></span>默认支付信息</span> 
			</p>
	        <span class="new-mu_l2a new-p-re">
	        	<span class="new-title">开户银行：</span><span class="new-mu_l2cw" style="font-size:16px">${obj.member.bank}建设银行</span>
	        	<span class="new-title">银行卡号：</span><span class="new-mu_l2cw" style="font-size:16px">${obj.member.bankacountno}6225 7608 1234 5678</span>
	            
	            <div class="new-addr-btn">
	            	<a href="javascript:void(0)">编辑</a>
	            </div>
	        </span>
	    </li>
	</div>
</div>

</form>

<div id="payment_p" style="display:block">
<div id="paymentp"></div>
<div class="payment-total-bar" id="payment">
    <div class="shp-chk">
        <span onclick="checkAllHandler();" class="cart-checkbox checked" id="checkIcon-1"></span>
    </div>
    <div class="shp-cart-info">
        <strong class="shp-cart-total">总计:￥<span class="" id="cart_realPrice"></span></strong>
        <span class="sale-off">提现订单条目:
        </span>	
    </div>
    <a class="btn-right-block" id="submit" style="background-color: rgb(192, 0, 0); background-position: initial initial; background-repeat: initial initial;">申请(<span id="checkedNum"></span>)</a>
</div>
</div>

<script>
$("#submit").click(function() {page_submit()});
function page_submit()
{
	$("#form_draw").submit();
}
</script>
</body>

</html>