<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/home/css/base2013.css" charset="gbk">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/score.css" charset="gbk">
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
	</body>
</html>

