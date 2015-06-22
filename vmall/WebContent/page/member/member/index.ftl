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
				<a href="javascript:void(0)">
					<p id="">0</p>
					<p>待付款</p>
				</a>
			</li>
			<li>
				<a href="javascript:void(0)">
					<p id="">0</p>
					<p>待确认收货</p>
				</a>
			</li>
			<li>
				<a href="javascript:void(0)">
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
				<a href="${base}/member/member/myinfo.action">
					<img src="${base}/image/member/myinfo.png" alt="">
					<p>我的资料</p>
				</a>
			</li>		
		
			<li>
				<a href="${base}/member/member/myrebate.action">
					<img src="${base}/image/member/myrebate.png" alt="">
					<p>我的积分</p>
				</a>
			</li>

			<li>
			<a href="${base}/member/member/mydraw.action">
				<img src="${base}/image/member/mydraw.png" alt="">
				<p>我的提现</p>
			</a>
			</li>
			
			<li>
			<a href="${base}/member/member/mygroup.action">
				<img src="${base}/image/member/mygroup.png" alt="">
				<p>我的团队</p>
			</a>
			</li>			

			<li>
				<a href="${base}/member/member/myorder.action">
					<img src="${base}/image/member/myorder.png" alt="">
					<p>我的订单</p>
				</a>
			</li>
			
			<li>
				<a href="javascript:void(0)">
					<img src="${base}/image/member/myqrcode.png" alt="">
					<p>我的二维码</p>
				</a>
			</li>
			
			<li>
				<a href="javascript:void(0)">
					<img src="${base}/image/member/myextend.png" alt="">
					<p>我的推荐</p>
				</a>
			</li>
			
			<li>
				<a href="javascript:void(0)">
					<img src="${base}/image/member/mycollect.png" alt="">
					<p>我的浏览</p>
				</a>
			</li>

		</ul>

	</div>

</body>
</html>