<!DOCTYPE html>
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
<link href="//ms2-m.vipstatic.com/apple-touch-icon.png/" rel="apple-touch-icon" />
<link rel="stylesheet" type="text/css" href="${base}/css/neat-min.css">
<link rel="stylesheet" type="text/css" href="${base}/css/ui-min.css">
<link rel="stylesheet" type="text/css" href="${base}/css/channel-min.css"
	webapp="im">
<link rel="stylesheet" href="${base}/css/layout.min.css">
<link rel="stylesheet" href="${base}/css/common.min.css">
<link rel="stylesheet" href="${base}/css/main.css">


<script src="${base}/lib/jquery-2.1.1.min.js"></script>
<script src="${base}/lib/jquery-ui.min.js"></script>
<script src="${base}/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="${base}/lib/moment.min.js"></script>
<script src="${base}/lib/moment.zh-cn.js"></script>
<script	src="${base}/lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${base}/lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${base}/lib/lodash.min.js"></script>
<script src="${base}/lib/bootstrap-slider.js"></script>
<script src="${base}/lib/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="${base}/js/main.js"></script>
</head>
<body class="u-index">
<div style="">

<div class="nav_placeholder" style="height: 37px; display: none;"></div>
	<div id="nav" class="u-nav">
		<ul class="clearfix">
			<li class="num06"><a href="${base}/goods/goods/index.action" mars_sead="floating-index_btn"> <span>首页</span>
			</a></li>
			<li class="num06"><a href="${base}/goods/goodsclass/index.action" mars_sead="floating-index-class_btn"> <span>分类</span>
			</a></li>
			<li class="num06"><a href="${base}/order/shopcart/index.action"	mars_sead="floating-index-shopcart_btn"> <span>购物车</span>
			</a></li>
			<li class="num06"><a href="${base}/member/member/index.action" mars_sead="floating-index-member_btn"> <span>个人中心</span>
			</a></li>
		</ul>
	</div>
</div>

</div>

<div id="div_shopcart">
<form id="form_shopcart">
<table id="tb_score" class="table personListTable hover">
	<thead>
	    <tr>
	      <th width="10" class="check min"></th>   
	      <th width="300">会员</th>
	      <th width="300">姓名</th>
	      <th width="50">层级</th>
	      <th width="50">积分</th>
	      <th></th>
	    </tr>
	</thead>	     
<tbody>
<#list obj.members as member>
      <tr>
        <td>${member_index+1}<input type="hidden" name="id" value="${member.id}"></td>
        <td>${member.id}</td>
        <td>${member.cname}</td>        
        <td>${member.level}</td>
        <td>${member.score}</td>        
      </tr>     
</#list>      
</tbody>
</table>
</form>
</div>

</body>

</html>