<html><head>
<title>
	京东触屏版
</title>
<meta name="author" content="m.jd.com">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" content="-1">           
<meta http-equiv="Cache-Control" content="no-cache">           
<meta http-equiv="Pragma" content="no-cache">           
<link rel="stylesheet" type="text/css" href="http://st.360buyimg.com/item/css/base.css?v=jd201505191606it" charset="gbk">

<link rel="stylesheet" type="text/css" href="http://st.360buyimg.com/item/css/extend.css?v=jd201505191606it" charset="gbk">
<link rel="stylesheet" type="text/css" href="http://st.360buyimg.com/item/css/hotel.css?v=jd201505191606it" charset="gbk">
<link rel="stylesheet" type="text/css" href="http://st.360buyimg.com/item/css/airline.css?v=jd201505191606it" charset="gbk">

<link rel="apple-touch-icon-precomposed" href="http://st.360buyimg.com/item/images/apple-touch-icon.png?v=jd201505191606it">
<script type="text/javascript">
			var _winLocation=window.location.href;//获得当前页面的路径，根据路径规则进行逐页替换
			var _isWebKit = '__proto__' in {};//是否是webkit内核
			if(_isWebKit){//如果是webkit内核，则分模块使用zepto
				//要使用zeptojs的路径列表，可以做为分模块替换的开关
				var _locationList=new Array();
				//活动模块
				_locationList.push('/activity/proActList');
				_locationList.push('/activity/proActWareList');
				_locationList.push('/activity/list');
				//商品分类模块
				_locationList.push('/category/');
				//京东快讯模块
				_locationList.push('/newslist.html');
				_locationList.push('/newslist/');
				_locationList.push('/news/');
				//机票模块
				_locationList.push('/airline/');
				//酒店模块
				_locationList.push('/hotel/');
				//团购模块
				_locationList.push('/tuan/');
				//首页相关
				_locationList.push('/hotbrand.html');//品牌馆
				//商品筛选相关
				_locationList.push('/ware/view.action');
				_locationList.push('/ware/expandSort.action');
				_locationList.push('/ware/categoryFilter.action');
				_locationList.push('/ware/search.action');
				_locationList.push('/products/');
				_locationList.push('/index/getWare.action')//热门特惠
				_locationList.push('/notice/');//通告模块
				_locationList.push('/coupons/');//随地惠模块
				_locationList.push('/chongzhi/');//手机充值模块
				_locationList.push('/comment/');//手机推荐模块
				_locationList.push('/pay/');//支付
				_locationList.push('/order/');//订单
				_locationList.push('/norder/');//订单
				
				_locationList.push('/seckill/');//秒杀
				
				_locationList.push('/sale/act/');//sale
				_locationList.push('/sale/mall/');//sale
				//_locationList.push('/market/floorWare.action');//掌上专享
				
				var _needReplace = false;
				//如果当前路径符合要使用的路径规则，则进行替换
				for(var i=0;i<_locationList.length;i++){
					if(_winLocation.indexOf(_locationList[i])!=-1){
						_needReplace=true;
						break;
					}
				}
				//如果是首页的话，则使用zepto
				var _tmp = _winLocation.replace(/(^http:\/\/)|(\/*$)/g,'');
				if(_tmp.indexOf('/')<0 || (_tmp.split('/').length<=2 && _tmp.indexOf('/index')>=0)){
					_needReplace=true;
				}
				//如果是商品详情页的话，则使用zepto
				var _dlocationList=new Array();
				_dlocationList.push(/\/product\/([0-9]+)\.html/);
				_dlocationList.push(/\/orderComment\/([0-9]+)\.html/);
				_dlocationList.push(/\/consultations\/([0-9]+)\.html/);
				_dlocationList.push(/\/consultations\/([0-9]+)-([0-9]+)\.html/);
				_dlocationList.push(/\/comments\/([0-9]+)\.html/);
				for(var i=0,len=_dlocationList.length;i<len;i++){
					if(_dlocationList[i].test(_winLocation)){
						_needReplace=true;
						break;
					}
				}
				if(_needReplace){
					document.write('<script src="http://st.360buyimg.com/item/js/zepto.min.js?v=jd201505191606it"><\/script>');
					document.write('<script type="text/javascript">window.jQuery=window.Zepto;<\/script>');
				}else{
					document.write('<script src="http://st.360buyimg.com/item/js/jquery-1.6.2.min.js?v=jd201505191606it"><\/script>');
				}
			}else{//如果是非webkit内核直接使用jquery
				document.write('<script src="http://st.360buyimg.com/item/js/jquery-1.6.2.min.js?v=jd201505191606it"><\/script>');
			}
		</script><script src="http://st.360buyimg.com/item/js/jquery-1.6.2.min.js?v=jd201505191606it"></script>
		<script type="text/javascript" src="http://st.360buyimg.com/item/js/html5/common.js?v=jd201505191606it"></script>
		<script type="text/javascript" src="http://st.360buyimg.com/item/js/html5/spin.min.js?v=jd201505191606it"></script><style></style>
		<script src="http://st.360buyimg.com/item/js/2013/installapp.js?v=jd201505191606it" type="text/javascript" charset="utf-8"></script></head>

		<body id="body" style="margin: 0px auto;">
			<a name="top"></a>
			<header>
				<div class="download-con" id="down_app" style="position: relative; display: none;">
					<div class="down_app">
						<div class="download-logo"></div>
						<div class="alogo">
							<div id="bookApp" style="display:none">
								<p class="client-name">下载京东阅读客户端</p>
								<p class="client-name">精彩好书免费看</p>
							</div>
							<div id="normolApp">
								<p class="client-name">客户端首单满79元送79元！</p>
							</div>		
							<p class="client-logon"></p>
						</div>
						<div class="open_now">
							<a id="openJD" app_href="openApp.jdMobile://virtual?params={&quot;category&quot;:&quot;jump&quot;,&quot;des&quot;:&quot;productDetail&quot;,&quot;skuId&quot;:&quot;1446346047&quot;,&quot;sourceType&quot;:&quot;M_DOWNLOAD_TYPE&quot;,&quot;sourceValue&quot;:&quot;M_DOWNLOAD_VALUE&quot;}" href="http://h5.m.jd.com/active/download/download.html?channel=jd-m"><span class="open_btn">立即打开</span></a>
						</div>
						<div class="close-btn-con close-btn" id="close">
							<span class="close-btn-icon"></span>
						</div>
					</div>
				</div>
				<div class="new-header">
					<a href="javascript:pageBack();" class="new-a-back" id="backUrl"><span>返回</span></a>
					<h2>详细介绍</h2>
				</div>
			</header>
			<script type="text/javascript" src="http://st.360buyimg.com/item/js/2013/ware/nav.js?v=jd201505191606it"></script>
			<style type="text/css">
				.nav-fixed{position:fixed;background-color:rgba(222,222,222,0.7);top:0;zoom:1;z-index:10;}
				*{
					margin:0;padding:0;
				}
				table{
					max-width: 100% !important;
					width: auto !important;
				}
				img{
					display: block;
					max-width: 100%;
					width: auto !important;
					height: auto !important;
				}
				.book-container{width: 100%;}
				.book-container .book-info-line{    margin: 10px;background: #ffffff;overflow: hidden;margin-top: 16px;}
				.book-info-line .info-title{display: inline-block;width: 25%;color: #999999;font-size:0.75em }
				.book-info-line .info-content{font-size:0.75em;}
				.book-container .book-container-item{margin:0 10px 50px 10px;}
				.book-container-item .book-item-title{font-size: 0.875em;color: #686868;}
				.book-container-item .book-item-content{font-size: 0.75em;color: #333333;margin-top: 10px;}
			</style>

			<div class="good-detail sift-mg">
    <div class="sift-tab" style="height: 42px;"><div id="fixed" class="sift-tab" style="height: 42px; width: 360px;">
    	<ul class="tab-lst">
					<li><a href="javascript:void(0)" value="wareInfo" class="on">商品介绍</a></li>
            <li><a href="javascript:void(0)" value="wareStandard"><span class="bar"></span>规格参数</a></li>
            <li><a href="javascript:void(0)" value="warePack"><span class="bar"></span>包装售后</a></li>
		        </ul>
    </div></div>
	    		<div class="detail" id="wareInfo" style="display: block;">
                <p>
                	<span>	
                		                			</span></p><p><img src="http://img30.360buyimg.com/popWaterMark/jfs/t1243/332/870225771/67221/28da6217/55546ec3N0ef12eff.jpg" alt="" id="3e77a121b2cd4070ad83000c04fe8156
" style="max-width: 270px;"></p><p><span style="font-size:x-large;"><strong></strong></span><img id="4e41c10dafa14385908981b46460318e" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t775/332/1420805651/395677/c06f3df1/5539be10Nfc270812.jpg" style="max-width: 270px;"><br></p><p><img id="72caad751cb346498328c800acb4b7fd" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t1102/18/683583851/427345/f5e5c1de/5539be11N81d68793.jpg" style="max-width: 270px;"></p><p><img id="139cfd7ee4f5410593cc3980a20c9a79" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t1252/299/638038525/400259/c5d9436e/5539be14Nba4c30ee.jpg" style="max-width: 270px;"></p><p><img id="978d1c8e059a4b90809e862d068f0891" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t847/212/664198148/479217/e3cded23/5539be12Ne5a38846.jpg" style="max-width: 270px;"></p><p><br></p><p><img id="0a71c72870f74b1bb7544e39b02726c3" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t1192/92/672776575/366596/f3c455f/5539be13N9f133a37.jpg" style="max-width: 270px;"></p><p><img id="f759c0a9d45f42be9905d622b336120c" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t1216/243/672884967/492756/185b6961/5539be13N8b6f857e.jpg" style="max-width: 270px;"></p><p><img id="cc34e9aead184295b7bccb9157f882e9" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t775/344/1397359786/296537/e2f00c41/5539be1fNb7546e6e.jpg" style="max-width: 270px;"></p><p><img id="1b6a2fe3130846d1b6e2e687e3f311c1" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t940/86/663441519/451673/8c168d74/5539be20N84fd58bb.jpg" style="max-width: 270px;"><br><img id="2ea6d82771d548c2b47d2b512010544a" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t790/325/664086182/318397/f83a26a7/5539be1fN3c8e8d0e.jpg" style="max-width: 270px;"><br></p><p><img id="7150cef2a0f64ed784be9162533f0074" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t967/93/654368227/430660/1380b88f/5539be63Nb0434602.jpg" style="max-width: 270px;"></p><p><img id="94dfe7ed56ca46178200a9d8d703ba70" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t1084/71/649136386/264361/cb5cda82/5539be74N9d60f664.jpg" style="max-width: 270px;"></p><p><img id="6b5a67f5ccea4ed3a226bbef11deb5a6" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t721/247/1379275657/509061/846e4bfb/5539be1dN69cc2f11.jpg" style="max-width: 270px;"><br><img id="8053fbfd6d1647d99e4e3af0e188df29" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t1078/284/665472827/476833/1b6081a5/5539be1cNf6e21059.jpg" style="max-width: 270px;"><br><img id="07e2fc8b1aa0401cb54c1e67169ea079" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t1060/307/673718387/331524/ae476c1b/5539be1bNad248b8c.jpg" style="max-width: 270px;"><br><img id="e77ad1de8f884e1c9e7959839c90b5ad" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t913/16/665403907/320056/cdcd8da8/5539be1bNb270bd51.jpg" style="max-width: 270px;"><br><img id="b196d2b42d174a339bd7c1ae087d498a" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t862/79/667332551/480566/13905cec/5539be1aN8faa925d.jpg" style="max-width: 270px;"></p><p><img id="6a5391e379754c2796ebd5f36a83a975" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t1198/18/664078418/492448/c840d571/5539be1eN193597c1.jpg" style="max-width: 270px;"></p><p><img id="ecffc754567b4b79aa49c6768eebfa72" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t1162/171/666923582/414591/436c5b9f/5539be18N0a30b714.jpg" style="max-width: 270px;"><br><img id="970a00f41ee2421297d39c6d01c4f1ce" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t1210/189/650551705/374554/263e1cad/5539be19N07a5819a.jpg" style="max-width: 270px;"></p><p><img id="d711a519c1eb48b8904e8392dff05ca8" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t958/176/648447365/509769/33fc3204/5539be17Nb74ce588.jpg" style="max-width: 270px;"><br><img id="3bddf93141bd4e1cb8c3e45bceeae3aa" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t859/108/684680742/427508/7fd42e65/5539be15N04c89351.jpg" style="max-width: 270px;"></p><p><img id="e31e5d026fb845e280e1445f2f7e5e27" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t1105/53/695482307/363459/d4b0bcf1/5539be16N9124ad6a.jpg" style="max-width: 270px;"></p><p><img id="81ae0db672364278ab375ca0e3371d4b" alt="" src="http://img30.360buyimg.com/popWareDetail/jfs/t1066/106/663196042/300132/bfe8ed69/5539be18N6ccce456.jpg" style="max-width: 270px;"><br><br><br><br></p><br><br>
                		    				
                <p></p>
            </div>
    		<div class="detail" id="wareStandard" style="display: none;">
				<style>
        		.Ptable{border-spacing:0px;border-collapse:collapse;}
								.Ptable td{height:10px;padding:0 5px;line-height:20px;font-size:12px;color:#333;word-break:break-all;}
				.Ptable th{height:10px;line-height:40px;padding-left:20px;font-size:18px;text-align:left}
				.Ptable td.td-txtlft{padding-left:10px;text-align:left}
				.Ptable .w130{width:130px}
				 .tdTitle{text-align:center;padding-left:20px;width:130px}
				</style>
                <p>
                	<span>	
                		暂无
    				</span>
                </p>
            </div>
    		<div class="detail" id="warePack" style="display: none;">
                <p>
                	<span>	
                		暂无<br>
    				</span>
					<span>	
                    		 本产品质保期为：<br>
    				</span>
                </p>
            </div>
    		        		<div class="detail" id="wareService" style="display: none;">
                    <p>
                    	<span>	
                    		 本产品质保期为：<br>
        				</span>
                    </p>
                </div>
		 	</div>



<script type="text/javascript">
	$(function(){
		$("iframe").remove();
		$(".detail").hide();
		$("#bookContent").show();
		$("#wareInfo").show();
		$("li>a").click(function(){
			if($('#fixed').has('nav-fixed')){
				$('#fixed').removeClass('nav-fixed');
			}
			$(".detail").hide();
			$("li>a").removeClass("on");
			$(this).addClass("on");
			var id=$(this).attr("value");
			$("#"+id).show();
    	});
		$("body").css("margin","0 auto");
		if($("#wareInfo").html()){
    		$("#wareInfo").html($("#wareInfo").html().replace(/<\/td>/g,'</td></tr><tr>'));
    		$("#wareInfo").html($("#wareInfo").html().replace(/item.jd.com/g,'m.jd.com/product'));
    		$("#wareInfo").html($("#wareInfo").html().replace(/www.360buy.com/g,'m.jd.com'));
		}
		$("#wareInfo img").css("max-width","270px");
		$("#wareInfo table").css("width","300px");
		$("#wareInfo td").css("background-size","300px");
		$("#wareInfo td").css("max-width","300px");
		$("#wareInfo tr").css("max-width","300px");
		$("#wareInfo div").css("width","300px");
		$("#wareInfo div").css("height","auto");
		$("#wareInfo div").css("max-width","300px");
		$("#wareInfo div").css("word-break","break-all");
		$("#wareInfo ul").css("width","300px");
	});
	
			
	$('#fixed').floatNav({
		fixedClass: 'nav-fixed',
    	range: 30
}); 
	
</script>

<div class="login-area" id="footer" style="margin:0 auto 15px;">
        	<div class="login">
									    					<a rel="nofollow" href="https://passport.m.jd.com/user/login.action?sid=b92ff74dfab64b42d614b6243db2b908">登录</a><span class="lg-bar">|</span><a rel="nofollow" href="https://passport.m.jd.com/user/mobileRegister.action?v=t&amp;sid=b92ff74dfab64b42d614b6243db2b908">注册</a>
    												<span class="new-fr"><a rel="nofollow" href="/showvote.html?sid=b92ff74dfab64b42d614b6243db2b908">反馈</a><span class="lg-bar">|</span><a href="#top">回到顶部</a></span>
            </div>
        	
			<div class="version">
				<span id="clientArea" style="">
									<a href="http://h5.m.jd.com/active/download/download.html?channel=jd-m" id="toClient" class="openJD">客户端</a>
								</span>
				<a href="javascript:void(0)" class="on">触屏版</a>
				<a onclick="skip();" href="javascript:void(0);" id="toPcHome">电脑版</a>        	</div>
            <div class="copyright">Copyright © 2012-2015 京东JD.com 版权所有 </div>
        </div>

        <div style="display:none;">
    					<img src="/ja.jsp?&amp;utmn=25868352&amp;utmr=http%3A%2F%2Fitem.m.jd.com%2Fproduct%2F1534727240.html&amp;utmp=%2Fware%2Fdetail.action%3Fsid%3Db92ff74dfab64b42d614b6243db2b908%26resourceValue%3Dunknown%26wareId%3D1534727240%26resourceType%3Dunknown&amp;guid=ON&amp;jav=html5&amp;pin=-&amp;utmac=MO-J2011-1&amp;provinceId=&amp;cityId=&amp;countryId=&amp;townId=&amp;skuId=1534727240&amp;skuPrice=&amp;stockState=">
			    </div>

			    <script type="text/javascript" src="http://h5.m.jd.com/active/track/mping.min.js"></script>

<script type="text/javascript">
        try{
            var pv= new MPing.inputs.PV();   //构造pv 请求
            var mping = new MPing();        //构造上报实例
            mping.send(pv);                //上报pv
        } catch (e){}
    </script>

    <script src="http://st.360buyimg.com/item/js/2014/module/plugIn/downloadAppPlugIn.js?v=jd201505191606it" type="text/javascript"></script>

    <script type="text/javascript">
$("#unsupport").hide();
if(!testLocalStorage()){ //not support html5
    if(7!=0 && !$clearCart && !$teamId){
		$("#html5_cart_num").text(7>0>0);
	}
}else{
	updateToolBar('');
}

$("#html5_cart").click(function(){
//	syncCart('b92ff74dfab64b42d614b6243db2b908',true);
	location.href='/cart/cart.action';
});

function reSearch(){
var depCity = window.sessionStorage.getItem("airline_depCityName");
	if(testSessionStorage() && isNotBlank(depCity) && !/^\s*$/.test(depCity) && depCity!=""){
    	var airStr = '<form action="/airline/list.action" method="post" id="reseach">'
        +'<input type="hidden" name="sid"  value="b92ff74dfab64b42d614b6243db2b908"/>'
        +'<input type="hidden" name="depCity" value="'+ window.sessionStorage.getItem("airline_depCityName") +'"/>'
        +'<input type="hidden" name="arrCity" value="'+ window.sessionStorage.getItem("airline_arrCityName") +'"/>'
        +'<input type="hidden" name="depDate" value="'+ window.sessionStorage.getItem("airline_depDate") +'"/>'
        +'<input type="hidden" name="depTime" value="'+ window.sessionStorage.getItem("airline_depTime") +'"/>'
        +'<input type="hidden" name="classNo" value="'+ window.sessionStorage.getItem("airline_classNo") +'"/>'
        +'</form>';
    	$("body").append(airStr);
    	$("#reseach").submit();
	}else{
    	window.location.href='/airline/index.action?sid=b92ff74dfab64b42d614b6243db2b908';
	}
}
 //banner 关闭点击
    $('.div_banner_close').click(function(){
        $('#div_banner_header').unbind('click');
        jQuery.post('/index/addClientCookieVal.json',function(d){
              $('#div_banner_header').slideUp(500);
        });
    });
    //banner 下载点击
    $('.div_banner_download').click(function(){
         var downloadUrl = $(this).attr('url');
         jQuery.post('/index/addClientCookieVal.json',function(d){
               window.location.href=downloadUrl;
        });
    });
    window._clientVersion_ = '';
    
	$(document).ready(function(){
		var _loadScript = function(url, options,cb){
			var script = document.createElement("script");
			var def = {
				type: "text/javascript",
				charset:"utf-8"
			}
			options= options|| {
			}
			for(var i in options){
				def[i] = options[i];
			}
			script.src = url;
			
			for(var i in def){
				script.setAttribute(i,def[i]);
			}
			script.addEventListener("load",function(){
				cb && cb();
			},false)
			document.getElementsByTagName("head")[0].appendChild(script);
		}
				if($(".download-con").length || $("#clientArea").length){
			_loadScript("http://st.360buyimg.com/item/js/2013/installapp.js?v=jd201505191606it",{},function(){
				 $("#clientArea").length && downcheck($("#clientArea"),false);
			});
		}
				
	})
			function skip(){
		addCookie('pcm', '1' ,1, '', 'jd.com');
		var localurl = 'http://www.jd.com/#m';
		if(localurl == 'http://www.jd.com/#m'){
			var localurl = document.location.href;
    		if(localurl.indexOf('http://m.jd.com/sale/mall') == 0){
    			var saleurl = localurl.replace('http://m.jd.com/sale/mall', 'http://sale.jd.com/mall');
    			saleurl = saleurl+'#m'
    			window.location.href = saleurl;
				return;
    		}else if(localurl.indexOf('http://m.jd.com/sale/act') == 0){
    			var saleurl = localurl.replace('http://m.jd.com/sale/act', 'http://sale.jd.com/act');
    			saleurl = saleurl+'#m'
    		 	window.location.href = saleurl;
				return;
    		}else{
    			 window.location.href = 'http://www.jd.com/#m';
				 return;
    		}
		}
		window.location.href = localurl;
	}
	
	function addCookie(name, value, expires, path, domain){ 
        var str=name+"="+escape(value); 
        if(expires!=""){ 
            var date=new Date(); 
            date.setTime(date.getTime()+expires*24*3600*1000);//expires单位为天 
            str+=";expires="+date.toGMTString(); 
        } 
        if(path!=""){ 
        	str+=";path="+path;//指定可访问cookie的目录 
        } 
        if(domain!=""){ 
        	str+=";domain="+domain;//指定可访问cookie的域 
        } 
        document.cookie=str; 
    } 
	function search_new(){
		var a = $("#newkeyword").val();
		addSearchHistory(a);
		$('#searchForm').submit();
	}
	
	window.onload = function(){
		$('#close').downloadAppPlugInClose('down_app');
	}
</script>
</body></html>