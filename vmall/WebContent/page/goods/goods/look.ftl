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

	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/css/base2013.css" charset="gbk">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/css/goods.css" charset="gbk">	

</head>
<body id="body">
	<header>
		<div class="new-header">
			<a href="javascript:pageBack();" class="new-a-back" id="backUrl"><span>返回</span></a>
			<h2>商品详情</h2>
		</div>
		<div class="new-jd-tab" style="display:none" id="jdkey">
			<div class="new-tbl-type">
				<a href="/index.html?sid=b92ff74dfab64b42d614b6243db2b908" class="new-tbl-cell">
					<span class="icon">首页</span>
					<p style="color:#6e6e6e;">首页</p>
				</a>
				<a href="http://m.jd.com/category/all.html?sid=b92ff74dfab64b42d614b6243db2b908" class="new-tbl-cell">
					<span class="icon2">分类搜索</span>
					<p style="color:#6e6e6e;">分类搜索</p>
				</a>
				<a href="javascript:void(0)" id="html5_cart" class="new-tbl-cell">
					<span class="icon3">购物车</span>
					<p style="color:#6e6e6e;">购物车</p>
				</a>
				<a href="http://home.m.jd.com/myJd/home.action?sid=b92ff74dfab64b42d614b6243db2b908" class="new-tbl-cell">
					<span class="icon4">我的京东</span>
					<p style="color:#6e6e6e;">我的京东</p>
				</a>
			</div>
		</div>
	</header>

	<div id="mainLayout">
		<div id="mainStay" class="new-wt">
			<div class="miblebox" id="goods-img-box">
				<div class="new-p-re">
					<div class="detail-img">
						<div id="_zoom" class="imgbox">
							<div id="imgSlider" class="imgbox-i" style="position:relative;left:0px;"> 
								<span class="tbl-cell"><img width="320" height="292" seq="0" src="http://m.360buyimg.com/n12/jfs/t1165/188/863038753/409368/70d475f5/554c80c4N3816fac9.png!q70.jpg"></span>
							</div>
							<div class="last-msg-txt" id="tips"></div>
						</div>
						<input type="hidden" value="http://m.360buyimg.com/n12/jfs/t1165/188/863038753/409368/70d475f5/554c80c4N3816fac9.png!q70.jpg,http://m.360buyimg.com/n12/jfs/t799/356/699872934/136169/3dc44d03/553efb72N64085327.jpg!q70.jpg,http://m.360buyimg.com/n12/jfs/t943/171/698073257/102904/1f851028/553efb73Neac712fd.jpg!q70.jpg,http://m.360buyimg.com/n12/jfs/t1042/244/718407082/114200/f188bf1d/553efb73Nd89903fa.jpg!q70.jpg,http://m.360buyimg.com/n12/jfs/t1078/238/721547497/141969/e1a159f/553efb74Nc2a36396.jpg!q70.jpg," id="imgs">
						<div class="detail-price"> <span id="price" class="p-price">¥${obj.goods.saleprice?number?string("0.00")}  </span> <span id="imgpage" class="pagenum">1/5</span> <a id="attention" class="btn-sc"></a> </div>
					</div>
				</div>
				<div id="spinner1" class="spinner" style="left:50%;position: absolute;height:120px;margin-top:50px;z-index:1000"></div>
				<div class="goodsinfo">
					<h1 id="title" class="detail-title"> <a href="/detail/1539889025.html?sid=b92ff74dfab64b42d614b6243db2b908" class="dis-blk" id="wareName">${obj.goods.cname}|${obj.goods.code}| 黑色 M  </a></h1>
					<p id="promotionInfo" class="subtitle">初夏特惠！手机下单更优惠！！</p> 							</div>
				</div>
				<span class="pop-attention" style="position:absolute;z-index:9999;display:none" id="save">
					<span class="icon-succ" id="guanzhu">关注成功</span>
				</span>
				<div class="saleinfo miblebox">
					<dl class="list-entry"> 
						<dt class="row01" id="promotionitem">  <span class="col01">促销：</span> <span class="col02 reverse-cell" id="sale"> <i class="icon-bg02">赠券</i>  <i class="icon-bg02">多买优惠</i> </span><em class="icon-up"></em></dt>
						<dd class="row02">
							<ul class="list-saleinfo" id="saleInfo">
								<li> <span class="col02"> <em class="icon-bg02">赠券</em> <span class="txt01">{赠券规则}</span> </span> </li>
								<li> <span class="col02"> <em class="icon-bg02">多买优惠</em> <span class="txt01">{满2件，总价打9.0折；满3件，总价打8.5折；满4件，总价打8.0折；}</span> </span> </li>
							</ul>
						</dd>
					</dl>
					<dl class="list-entry"> 
						<dt class="row01">
							<span class="col01">规格：<#list obj.currentgoodsspecs as currentspec>${currentspec.spec}&nbsp;</#list></span> 
							<span class="col02" id="guige">
								<span id="amount">1件<span></span><em class="icon-up"></em></span>
							</span>
						</dt>
						<dd class="row02" style="diskplay:block">
							<#list obj.goodsclassspeces as classspec>
							<section class="select" id="proColor">
								<p class="label">${classspec.specclass}</p>
								<p class="option" id="color">
								<#list obj.goodsspecs as spec>
									<#if spec.specclass = classspec.specclass>
										<#list obj.currentgoodsspecs as currentspec>
											<#if currentspec.specclass==spec.specclass>
												<#if currentspec.spec==spec.spec>
													<a title="${spec.spec}" date="currentColor" class="link-check active" href="javascript:void(0)">${spec.spec}</a>&nbsp;
												<#else>
													<a href="javascript:void(0)" title="${spec.spec}" date="noCurrent" wareid="${obj.goods.id}" class="link-check">${spec.spec}</a>&nbsp;
												</#if>
											</#if>
										</#list>								
									</#if>
								</#list>
								</p>
							</section>							
							</#list>
							<section class="select">
								<p class="label">数量</p>
								<p class="option"><a class="btn-del" id="minus" onclick="minus();">-</a><input type="text" class="fm-txt" value="1" id="number" onblur="modify();"><a class="btn-add" id="plus" onclick="plus();">+</a></p>
							</section>
						</dd>
					</dl>
					<dl class="list-entry-extra">
						<dt class="row01">
							<span class="col01">送至：</span>
							<span class="col02" id="btn-select-region" region-data="1,72,4137,">
								<div class="address address01"><span id="provinceName">${obj.member.province}</span><span id="cityName">${obj.member.city}</span><span id="countyName">${obj.member.county}</span> <span id="townName">${obj.member.town}</span><i class="icon icon-location"></i></div>
								<p class="row01col03" id="stockStatus">现货</p>
							</span>
							<em class="icon-up"></em>
						</dt>
						<dt class="row03" id="fare">
							<span class="col01">运费：</span>
							<span class="col02" id="fareMoney"></span>
						</dt> 
						<dt class="row03">
							<span class="col01">提示：</span>
							<span class="col02" id="fareMoney"><img src="/images/2014/ware/7dayback.png" width="15" height="15" style="margin-bottom:-3px"></span>
						</dt> 
						<div class="promise-ico">
							<span class="txt02">
								<i class="icon-bg03"><img src="http://img30.360buyimg.com/mobilecms/g13/M06/10/11/rBEhUlLL13kIAAAAAAAEeT_oUHcAAHskQP_-boAAASR577.jpg" width="15" height="15">货到付款</i> 
								<span class="txt">支持送货上门后再收款，支持现金、POS机刷卡等方式</span>
							</span>
							<em class="icon-up"></em>
						</div>
					</dl>
					<dl class="list-entry">
						<dt class="row01"> <a href="${base}/goods/goods/detail.action?id=${obj.goods.id}" id="wareInfo"> <span class="col01">商品详情</span> <em class="icon-arr"></em>  </a></dt>
					</dl>
				</div>

				<div class="miblebox goodseval">
					<div class="info"> <a id="btnAssess" href="/comments/1539889025.html?sid=b92ff74dfab64b42d614b6243db2b908"> <span class="text">商品评价</span> <span class="text-fr"> <em id="comments">591</em> 人评价 <em id="goods">99%</em> 好评 </span> </a> <i class="icon-arr"></i> </div>
					<div class="eval-box">
						<div class="eval-box-i"> 
							<a id="orderComment" class="btn-good" href="/orderComment/1539889025.html?resourceType=unknown&amp;resourceValue=unknown&amp;sid=b92ff74dfab64b42d614b6243db2b908"> <span class="icon icon-sd"></span> 商品晒单(3) </a> 
							<a id="consultations" class="btn-ser" href="/consultations/1539889025.html?resourceType=unknown&amp;resourceValue=unknown&amp;sid=b92ff74dfab64b42d614b6243db2b908"> <span class="icon icon-cons"></span> 购买咨询(0) </a>
						</div>
					</div>
				</div>
				<div class="miblebox brand-bar-box">
					<dl class="list-entry">
						<dt class="row01"> <a href="http://ok.jd.com/m/index-111647.htm" class="bran-bar-box-tit"> <span class="col01">鸿丹莹服饰专营店</span> <span class="brand-bar-box-level"> <span class="new-mu-heart"><span style="width:99.155%" class="new-mu-heartv"></span></span> <span class="txt02"></span> </span> </a><span class="icon-arr"></span></dt>                    				<dd class="row02" id="kefu" style="-webkit-transform-origin: 0px 0px; opacity: 1; -webkit-transform: scale(1, 1);"> <span class="jdong"><i class="icon-dong"></i> <a class="txt" id="im" href="http://im.m.jd.com/merchant/index?v=6&amp;sku=1539889025&amp;imgUrl=&amp;goodName=%25E9%259F%25A9%25E8%25B4%259D%25E4%25BC%258A%25E9%2583%25BD2015%25E5%25A4%258F%25E5%25AD%25A3%25E9%259F%25A9%25E7%2589%2588%25E4%25BF%25AE%25E8%25BA%25AB%25E6%2597%25B6%25E5%25B0%259A%25E5%25AD%2597%25E6%25AF%258D%25E5%258D%25B0%25E8%258A%25B1%25E5%259C%2586%25E9%25A2%2586%25E5%2590%258A%25E5%25B8%25A6%25E8%2583%258C%25E5%25BF%2583%25E5%25A5%25B3%25E7%25BA%25AF%25E6%25A3%2589%25E6%2589%2593%25E5%25BA%2595%25E8%25A1%25ABhb0001%2520%25E9%25BB%2591%25E8%2589%25B2%2520M&amp;jdPrice=59.00&amp;sid=b92ff74dfab64b42d614b6243db2b908">联系卖家</a></span> </dd>
					</dl>
				</div>
				<div class="miblebox img-list-border">
					<div class="img-list">
						<h3 class="mible-title">猜你还喜欢：</h3>
						<div id="guessing" class="jd-slider-wrapper">
							<div class="jd-slider-container" style="width: 1224px; height: 298px;">

								<#list obj.likegoodses as likegoods>

								<a href="${base}/goods/goods/look.action?id=${likegoods.id}" class="jd-slider-item" style="width: 102px;">
								<div class="pro-img">
									<span class="img">
									<img width="80" height="80" alt="img" src="http://m.360buyimg.com/n7/jfs/t1084/187/827540238/683832/ab96f38/554d737dN2f98a8ba.png"></span>
									<span class="pro-title">${likegoods.cname}</span> 
									<span class="pro-price"><span class="pro-price">¥${likegoods.promoteprice}</span>
									</span>
								</div>
								</a>

								</#list>	



							</div>
						</div>
					</div>
				</div>
				<div id="cart1" class="cart-btns-fixed" style="display: table;">
					<div class="cart-btns-fixed-box"> <a class="btn btn-buy" id="directorder">立即购买</a> <a class="btn btn-cart" id="add_cart">加入购物车</a> <a href="javascript:void(0)" id="toCart" class="btn cart-num"></a> </div>
				</div>
				
				<div id="yuyuecart" style="width:100%;position:fixed;bottom:0;display:none">
			    	<div class="tbl-type detail-tbn2">
			            <div class="tbl-cell">
			                <a class="btn-cart-1" id="cartyuyue"><span></span>加入购物车</a>
			            </div>
			        </div>
				</div>				

				<div id="yuyueing" style="width:100%;position:fixed;bottom:0;display:none">
					<div class="tbl-type detail-tbn2">
						<div class="tbl-cell">
							<a class="btn-yuyue" data=""><span class="icon-clock"></span><span id="yuyuetime"></span><span class="txt-yuyue" id="yuyuecontext"><span></span>开始预约</span></a>
						</div>
					</div>
				</div>
				<div id="yuyuenow" style="width:100%;position:fixed;bottom:0;display:none">
					<div class="tbl-type detail-tbn2">
						<div class="tbl-cell">
							<a class="btn-yuyue2" href="/yuyue/1539889025.html?sid=b92ff74dfab64b42d614b6243db2b908" id="nowyuyue"><span class="icon-clock"></span><span class="txt-yuyue" id="yuyuenowcontext"><span></span>立即预约</span></a>
						</div>
					</div>
				</div>
				<div id="yuyueend" style="position:fixed;width:100%;bottom:0;display:none">
					<div class="tbl-type detail-tbn2">
						<div class="tbl-cell">
							<a class="btn-yuyue2"><span class="icon-clock"></span><span class="txt-yuyue" id="yuyueendcontext"><span>抢购已结束</span></span></a>
						</div>
					</div>
				</div>
				<div class="pop" style="position: absolute; z-index: 9999; display: none; left: 539.5px;" id="tip">
					<p><span class="pop-txt"></span>
					</p>
					<div class="pop-txt-area">
						<span class="pop-txt2" id="tips">
						</span>
					</div>
					<div class="tbl-type">
						<a href="javascript:void(0)" onclick="$('#tip').hide();$('#_mask').hide();" class="tbl-cell" style="width:50%">知道啦</a>
						<a href="javascript:void(0)" id="myyuyue" class="tbl-cell" style="width:50%">我的预约</a>
					</div>
				</div>
				<div class="cart-pop" id="cart" style="display: none; position: absolute; bottom: 50%; z-index: 9999; left: 560.5px;">
					<div class="ico-succ">
						<span class="att-succ">添加成功！</span>
						<span class="cart-succ">商品已成功加入购物车</span>
					</div>
				</div>	
				<div class="menu-fixed"> 
					<ul class="menu-fixed-list">
						<li class="fore01"><a href="/index.html?sid=b92ff74dfab64b42d614b6243db2b908">首页</a></li>
						<li class="fore02"><a href="http://m.jd.com/category/all.html?sid=b92ff74dfab64b42d614b6243db2b908">搜索</a></li>
						<li class="fore03"><a href="/cart/cart.action?sid=b92ff74dfab64b42d614b6243db2b908"> 购物车</a></li>
						<li class="fore04"><a href="/user/home.action?sid=b92ff74dfab64b42d614b6243db2b908">我的京东</a></li>
					</ul>
					<div class="menu-fixed-mini"><a href="javascript:void(0)"></a></div>
				</div>
			</div>
			
			<div class="menu_sidebar" id="puller">
				<div class="list_content_mask" style="display: none;"></div>
				<div class="sidebar-content" style="-webkit-transform-origin: 0px 0px; opacity: 1; -webkit-transform: scale(1, 1);display: none;">
					<div class="sidebar-header region-title">
						<span id="region-back-arrow" class="region-title-back" data-level="0"><i class=""></i></span><span class="region-title-name">配送至</span>
					</div>
					<div class="sidebar-items-container region-list-group">
						<div class="spacer44"></div>
						<div class="region-wrapper">
							<ul id="jdDeliverList1" level="1" class="cur sidebar-list sidebar-categories region-list"><li class="checked" id="1"><i class="tick"></i><a name="region1"></a><span>北京</span></li><li id="2"><i class="tick"></i><a name="region2"></a><span>上海</span></li><li id="3"><i class="tick"></i><a name="region3"></a><span>天津</span></li><li id="4"><i class="tick"></i><a name="region4"></a><span>重庆</span></li><li id="5"><i class="tick"></i><a name="region5"></a><span>河北</span></li><li id="6"><i class="tick"></i><a name="region6"></a><span>山西</span></li><li id="7"><i class="tick"></i><a name="region7"></a><span>河南</span></li><li id="8"><i class="tick"></i><a name="region8"></a><span>辽宁</span></li><li id="9"><i class="tick"></i><a name="region9"></a><span>吉林</span></li><li id="10"><i class="tick"></i><a name="region10"></a><span>黑龙江</span></li><li id="11"><i class="tick"></i><a name="region11"></a><span>内蒙古</span></li><li id="12"><i class="tick"></i><a name="region12"></a><span>江苏</span></li><li id="13"><i class="tick"></i><a name="region13"></a><span>山东</span></li><li id="14"><i class="tick"></i><a name="region14"></a><span>安徽</span></li><li id="15"><i class="tick"></i><a name="region15"></a><span>浙江</span></li><li id="16"><i class="tick"></i><a name="region16"></a><span>福建</span></li><li id="17"><i class="tick"></i><a name="region17"></a><span>湖北</span></li><li id="18"><i class="tick"></i><a name="region18"></a><span>湖南</span></li><li id="19"><i class="tick"></i><a name="region19"></a><span>广东</span></li><li id="20"><i class="tick"></i><a name="region20"></a><span>广西</span></li><li id="21"><i class="tick"></i><a name="region21"></a><span>江西</span></li><li id="22"><i class="tick"></i><a name="region22"></a><span>四川</span></li><li id="23"><i class="tick"></i><a name="region23"></a><span>海南</span></li><li id="24"><i class="tick"></i><a name="region24"></a><span>贵州</span></li><li id="25"><i class="tick"></i><a name="region25"></a><span>云南</span></li><li id="26"><i class="tick"></i><a name="region26"></a><span>西藏</span></li><li id="27"><i class="tick"></i><a name="region27"></a><span>陕西</span></li><li id="28"><i class="tick"></i><a name="region28"></a><span>甘肃</span></li><li id="29"><i class="tick"></i><a name="region29"></a><span>青海</span></li><li id="30"><i class="tick"></i><a name="region30"></a><span>宁夏</span></li><li id="31"><i class="tick"></i><a name="region31"></a><span>新疆</span></li><li id="32"><i class="tick"></i><a name="region32"></a><span>台湾</span></li><li id="42"><i class="tick"></i><a name="region42"></a><span>香港</span></li><li id="43"><i class="tick"></i><a name="region43"></a><span>澳门</span></li><li id="84"><i class="tick"></i><a name="region84"></a><span>钓鱼岛</span></li></ul>
							<ul id="jdDeliverList2" level="2" class="sidebar-list sidebar-categories region-list">
							</ul>
							<ul id="jdDeliverList3" level="3" class="sidebar-list sidebar-categories region-list">
							</ul>
							<ul id="jdDeliverList4" level="4" class="sidebar-list sidebar-categories region-list">
							</ul>
						</div>

						<div class="loading-mask" style="display: none;"></div>
					</div>
				</div>
			</div>

			<div class="login-area" id="footer" style="margin:0 auto 15px;">
				<div class="login">
					<a rel="nofollow" href="https://passport.m.jd.com/user/login.action?sid=b92ff74dfab64b42d614b6243db2b908">登录</a><span class="lg-bar">|</span><a rel="nofollow" href="https://passport.m.jd.com/user/mobileRegister.action?v=t&amp;sid=b92ff74dfab64b42d614b6243db2b908">注册</a>
					<span class="new-fr"><a rel="nofollow" href="/showvote.html?sid=b92ff74dfab64b42d614b6243db2b908">反馈</a><span class="lg-bar">|</span><a href="#top">回到顶部</a></span>
				</div>

				<div class="version">
					<span id="clientArea" style="">
						<a id="toClient" class="openJD" app_href="openApp.jdMobile://virtual?params={&quot;category&quot;:&quot;jump&quot;,&quot;des&quot;:&quot;productDetail&quot;,&quot;skuId&quot;:&quot;1539889025&quot;,&quot;sourceType&quot;:&quot;JSHOP_SOURCE_TYPE&quot;,&quot;sourceValue&quot;:&quot;JSHOP_SOURCE_VALUE&quot;}" href="http://h5.m.jd.com/active/download/download.html?channel=jd-m">客户端</a>
						<a id="wxToClient" style="display:none;" href="openApp.jdMobile://virtual?params={&quot;category&quot;:&quot;jump&quot;,&quot;des&quot;:&quot;productDetail&quot;,&quot;skuId&quot;:&quot;1539889025&quot;,&quot;sourceType&quot;:&quot;MWEIXIN_SOURCE_TYPE&quot;,&quot;sourceValue&quot;:&quot;MWEIXIN_SOURCE_VALUE&quot;}"></a>
					</span>
					<a href="javascript:void(0)" class="on">触屏版</a>
					<a onclick="skip();" href="javascript:void(0);" id="toPcHome">电脑版</a>        	</div>
					<div class="copyright">Copyright © 2012-2015 版权所有 </div>
					<button id="bt_test" onclick="page_addtocart()">测试</button>
					<button id="bt_test" onclick="page_addtocart()">测试1</button>
				</div>

			</div>
		</div>		
	</div>		
</div>



<input type="hidden" id="goodsid" value="${obj.goods.id}">


<script>

function minus() {
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

$("#add_cart").click(page_addtocart);
// 添加至购物车
var eTime;
function page_addtocart() 
{
	eTime = new Date().getTime() / 1000 + 4;
	var goodsid = $("#goodsid").val();
	var nums = $("#number").val();
	
	$.ajax(
	{
		type:'post',
		url:'${base}/order/shopcart/addtocart.action',
		data:{"goodsid":goodsid,"nums":nums},
		cache:false,
		async:false,
		success:function(data)
		{
			
			console.log(data);
			if(data=="")
			{
				alert("添加至购物车异常！");
				return;
			}
			json = eval("(" + data + ")");
			if(json.state=="success")
			{
			
				if ($("#toCart i").length == 0) 
				{
					$("#toCart").prepend("<i></i>")
				}
				
				$("#toCart i").text(json.cartgoodsnum);
			
				// 更新购物车数量等操作；
				cartShow();
			}
			else
			{
				alert("添加至购物车失败！");
			}
		},
		error:function(data)
		{
			console.log(data);
			alert("请求异常！");
		}
	})
};


function cartShow() {

	var a = new Date().getTime() / 1000;
	var d = Math.floor(eTime - a);

	if (d > 0) {
		setTimeout("cartShow()", 1000);
		var c = 100;
		var b = window.pageYOffset || document.documentElement.scrollTop
				|| document.body.scrollTop;
		var e = document.documentElement.clientHeight
				|| document.body.clientHeight;
		document.getElementById("cart").style.bottom = "20px";
		$("#cart").show();
	} else {
		$("#cart").hide();
	}
}

</script>


</body>
</html>