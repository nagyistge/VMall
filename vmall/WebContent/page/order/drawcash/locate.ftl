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

<div class="new-ct">
<div class="new-addr">
	<ul class="new-mu_l2w">
        <li class="new-mu_l2">
    	<p class="new-tit new-p-re">
			<span class="new-title">姓名：</span><span class="new-txt" style="font-size:20px">${obj.member.cname}</span>
			<span class="new-title">手机：</span><span class="new-txt" style="font-size:20px">${obj.member.mobile}</span>
		</p>
        <span class="new-mu_l2a new-p-re">
        	<span class="new-title">提现单号：</span><span class="new-mu_l2cw" style="font-size:16px">${obj.drawcash.cno}</span>
        	<span class="new-title">提现金额：</span><span class="new-mu_l2cw" style="font-size:16px">${obj.drawcash.amount}</span>
        </span>
    </li>
</div>
</div>

</body>

</html>