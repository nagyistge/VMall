<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/home/css/base2013.css" charset="gbk">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/home/css/guessing.css" charset="gbk">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/home/css/list.css" charset="gbk">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/home/css/myhome.css" charset="gbk">
	<#include "/decorator/include/header.ftl">

</head>
<body id="body">
	<#include "/decorator/include/navmain.ftl">

	<div class="common-wrapper">

		<div class="head-img">
			<span class="my-img" style="background-image:url('${base}/css/img/default.png')"></span>
			<p>${obj.member.cname}</p>
			<p>铜牌用户</p>
		</div>

		<ul class="padding-list">
			<li>
				<a href="/user/waite4Payment.action?sid=b0ef5bb405ad55364ae07a3a85a51e00">
					<p id="waite4PaymentSum">0</p>
					<p>待付款</p>
				</a>
			</li>
			<li>
				<a href="/user/waitDeliveryOrderList.action?sid=b0ef5bb405ad55364ae07a3a85a51e00">
					<p id="waitDeliveryOrderListSum">0</p>
					<p>待确认收货</p>
				</a>
			</li>
			<li>
				<a href="/myJd/message/list.action?sid=b0ef5bb405ad55364ae07a3a85a51e00">
					<p>
						<span id="infoCount">0</span>
						<span id="unread-msg-point"></span>
					</p>
					<p>我的消息</p>
				</a>
			</li>
		</ul>

		<ul class="menu-list">
			<li>
				<a href="/user/allOrders.action?functionId=quanbudingdan&amp;sid=b0ef5bb405ad55364ae07a3a85a51e00">
					<img src="http://img30.360buyimg.com/mobilecms/jfs/t604/33/677153327/2632/39dbca27/547bc6b5Ncc52a3b8.png" alt="">
					<p>我的订单</p>
				</a>
			</li>
			
			<li>
				<a href="${base}/order/order/memberscore.action">
					<img src="http://img30.360buyimg.com/mobilecms/jfs/t535/111/664109451/3395/3c18f3cb/547bc6eaN6c97383c.png" alt="">
					<p>我的积分</p>
				</a>
			</li>

			<li>
			<a href="${base}/order/drawcash/memberdraw.action">
				<img src="http://img30.360buyimg.com/mobilecms/jfs/t475/327/686010649/2674/7cf56bb1/547bc6dbN3dabf32a.png" alt="">
				<p>我的提现</p>
			</a>
			</li>
			
			<li>
			<a href="${base}/member/member/group.action">
				<img src="http://img30.360buyimg.com/mobilecms/jfs/t547/103/678884525/2510/c82066d7/547bc727Nde7da59c.png" alt="">
				<p>我的团队</p>
			</a>
			</li>			
			
			<li>
				<a href="/myJd/history/wareHistory.action?functionId=liulanjilu&amp;sid=b0ef5bb405ad55364ae07a3a85a51e00">
					<img src="http://img30.360buyimg.com/mobilecms/jfs/t571/182/648860482/3266/f4f4ed01/547bc70aNf7e3462a.png" alt="">
					<p>我的浏览</p>
				</a>
			</li>
			

			
			<li>
				<a href="/user/accountCenter.action?functionId=zhanghuguanli&amp;sid=b0ef5bb405ad55364ae07a3a85a51e00">
					<img src="http://img30.360buyimg.com/mobilecms/jfs/t448/16/669601077/3241/d50da28d/547bc742N95a14876.png" alt="">
					<p>我的资料</p>
				</a>
			</li>
			
			<li>
				<a href="/user/preSells.action?functionId=wodeyuyue&amp;sid=b0ef5bb405ad55364ae07a3a85a51e00">
					<img src="http://img30.360buyimg.com/mobilecms/jfs/t625/30/670318670/3709/3488e48/547bc75fNc5c6209c.png" alt="">
					<p>我的二维码</p>
				</a>
			</li>
			
			<li>
				<a href="//m.jd.com/download/downApp.html?functionId=yingyongtuijian&amp;sid=b0ef5bb405ad55364ae07a3a85a51e00">
					<img src="http://img30.360buyimg.com/mobilecms/jfs/t619/235/660356215/2788/5b40e4c9/547bc772Nbdf299f1.png" alt="">
					<p>我的推荐</p>
				</a>
			</li>
			
		</ul>

	</div>

</body>
</html>