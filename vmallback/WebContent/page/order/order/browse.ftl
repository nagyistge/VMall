
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>所有订单 - 微分销</title>
    <!-- 线上环境 -->
        <link rel="stylesheet" href="http://res.mp.wifenxiao.com/Public/css/dist/component-min.css">    <link rel="stylesheet" href="/Public/plugins/jbox/jbox-min.css">
    
    <style>
        .ftnormal {line-height: 25px!important;}
        .btn{vertical-align: baseline;}
        .grounp_chenge_box{ padding:10px 0px; text-align:right;}
        .grounp_chenge_box .grtt{ font-weight:900;}
        .grounp_chenge_box .intem{ border:1px solid #CCC; color:#666; padding:2px 10px;}
        .grounp_chenge_box .intem.cur{ border:1px solid #1C89D5; background:#1C89D5; color:#FFF;}
		.infor_logistics_box{ display:none; position:absolute; width:260px; left:-105px;top:20px; border:1px solid #ccc; padding:15px; background:#fff; z-index:999;}
		.arrow-top{ display:none;top: 15px;left: 30px;width: 11px;height: 6px;position: absolute;overflow: hidden; background:url(/Public/images/arrow_cut.png) no-repeat; z-index:9999;}
		.infor_logistics_box .courier{margin-bottom: 8px;border-bottom: 1px solid #f1f1f1;color: #666;}
		.infor_logistics_box .courier .name{ margin-right:10px;}
		.infor_logistics_box .courier em{font-style: normal;font-weight: 400;}
		.infor_logistics_box .address li{position: relative;padding: 0 25px 12px;}
		.infor_logistics_box .address .current{ color:#f40;}
		.infor_logistics_box .address .current .symbol{background:url(/Public/images/xlt.png) 0 0 no-repeat}
		.infor_logistics_box .address .place{display: block;overflow: hidden;word-wrap: break-word;}
		.infor_logistics_box .address .symbol{position: absolute;top: 6px;left: 8px;_left: -17px;width: 6px;height: 6px;overflow: hidden;background:url(/Public/images/xlt.png) 0 -11px no-repeat;}
    </style>

</head>
<body class="cp-bodybox">
<!--[if lt IE 9]>
<div class="alert alert-danger disable-del txtCenter" id="tipLowIEVer">
    <h4>系统检测到您使用的浏览器版本过低，为达到更好的体验效果请升级您的浏览器，我们为您推荐：</h4>
    <p>
        <a href="https://www.google.com.hk/chrome/" target="_blank">Chrome浏览器</a>
        <a href="http://www.firefox.com.cn/download/" target="_blank">Firefox浏览器</a>
        <a href="http://www.maxthon.cn/" target="_blank">遨游浏览器</a>
        <a href="http://se.360.cn/" target="_blank">360浏览器</a>
        <a href="http://www.liebao.cn/" target="_blank">猎豹浏览器</a>
    </p>
</div>
<![endif]-->
<div class="header">
    <div class="inner clearfix">
        <div class="fl">
            <a href="/" class="header-logo"><img src="http://res.mp.wifenxiao.com/Public/images/logo.jpg"></a>
        </div>
        <!-- end logo -->

        <div class="header-nav fl">
            <ul class="header-nav-list clearfix">
                
                <li class="fl "><a href="/Shop/home">首页</a></li><li class="fl "><a href="/Shop/list_homepage">店铺</a></li><li class="fl "><a href="/Item/lists/item_status/onsale">商品</a></li><li class="fl active"><a href="/Order/lists">订单</a></li><li class="fl "><a href="/User/lists">会员</a></li><li class="fl "><a href="/User/apply_lists">分销商</a></li><li class="fl "><a href="/Dist/apply_list">财务</a></li><li class="fl "><a href="/Ump/checkin">营销</a></li><li class="fl "><a href="/System/shopInfo">设置</a></li>                
            </ul>
        </div>
        <!-- end header-nav -->

        <div class="fr">
            <ul class="header-ctrl clearfix">
                <li class="header-ctrl-item fl">
                    <a href="javascript:;" class="header-ctrl-item-parent">
                        <i class="gicon-user white"></i>
                        <i class="gicon-user"></i>
                        账户
                    </a>
                    <ul class="header-ctrl-item-children">
                        <li><a href="/System/updateLog" target="_blank">更新日志</a></li>
                        <li><a href="http://www.wifenxiao.com/Index/help_list/lm/help.html" target="_blank">帮助中心</a></li>
                        <li><a href="/System/shopInfo">设置</a></li>
                        <li><a href="/Public/logout">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- end list -->
		<span class="account_inbox_switch fr"><a href="/System/notice_list" class="header_mail"><span class="act"></span></a></span>
        <span class="header-welcome fr"><a href="javasecript:;" class="tips" data-trigger="hover" data-placement="top" data-content='<strong>版本：</strong><font style="color:red">免费版</font>'>13002994107，欢迎回来</a></span>

        <!-- end header-welcome -->
    </div>
</div>
<!-- end header -->

<div class="container">
<div class="inner clearfix">
<div class="content-left fl">
    <dl class="left-menu shop_5 sub_signup">
            <dt>
                <i class="icon-menu signup"></i>
                <span id="shop_5" data-id="5">订单管理</span>
            </dt>
            <dd class="subshop_0 active">
                    <a href="/Order/lists">所有订单</a>
                                    </dd><dd class="subshop_13 ">
                    <a href="/Order/export">导出订单</a>
                                    </dd><dd class="subshop_14 ">
                    <a href="/Order/del_log">删除日志</a>
                                    </dd><dd class="subshop_16 ">
                    <a href="/Order/order_import">备份订单导入</a>
                                    </dd>        </dl><dl class="left-menu shop_6 sub_shield">
            <dt>
                <i class="icon-menu shield"></i>
                <span id="shop_6" data-id="6">售后服务</span>
            </dt>
            <dd class="subshop_0 ">
                    <a href="/Order/exchange">退/换货审核</a>
                                    </dd><dd class="subshop_2 ">
                    <a href="/Comment/lists">商品评价</a>
                                    </dd><dd class="subshop_7 ">
                    <a href="/Comment/custom_comment">自定义评价</a>
                    <i class="icon_hot"></i>                </dd>        </dl></div>
<!-- end content-left -->

<div class="content-right fl">
 

    <h1 class="content-right-title">所有订单<a class="gicon-info-sign gicon_linkother" href="http://www.wifenxiao.com/Index/help_show/lm/help/id/46.html" target="_blank"></a></h1>
    
    
    <form action="${base}/order/order/browse.action" method="post" id="queryform">
    	<input type="hidden" id="state" name="state" value="">
        <div class="tables-searchbox">
            <input type="text" placeholder="输入收货人/手机号/订单号" class="input" name="mobile_orderno" value="">
            <input type="text" placeholder="输入商品货号" class="input" name="goods_no" value="">
            <input type="text" placeholder="输入收货地址" class="input" name="receiver_address" value="">
            <select name="shipping_type" class="select mini">
                <option value="-1" selected>配送方式</option>
                <option value="1" >快递</option>
                <option value="2" >EMS</option>
                <option value="3" >平邮</option>
                <option value="4" >自提</option>
            </select>
            <input type="text" autocomplete="off" name="start_create_time" value="" placeholder="订单时间" class="input Wdate mini" onclick="WdatePicker({ dateFmt:'yyyy-MM-dd'})">
            <span class="mgr5">至</span>
            <input type="text" autocomplete="off" name="end_create_time" value="" placeholder="订单时间" class="input Wdate mini" onclick="WdatePicker({ dateFmt:'yyyy-MM-dd'})">
            <button class="btn btn-primary"><i class="gicon-search white"></i>查询</button>
        </div>
    </form>
        <div class="tabs clearfix mgt15" id="tabs">
        <a href="javascript:void(0)" class="active tabs_a fl" state="">所有订单(0)</a>
        <a href="javascript:void(0)" class="tabs_a fl" state="下单">下单(0)</a>
        <a href="javascript:void(0)" class="tabs_a fl" state="收款">收款(0)</a>
        <a href="javascript:void(0)" class="tabs_a fl">待收货(0)</a>
        <a href="javascript:void(0)" class="tabs_a fl">交易完成(0)</a>
        <a href="javascript:void(0)" class="tabs_a fl">已关闭(0)</a>
    </div>
        <!-- end tabs -->
        <div class="grounp_chenge_box mgt15">
            <span class="grtt">每页显示订单数量:</span>
            <a class="intem  cur " href="#">10</a>
            <a class="intem  " href="#">20</a>
            <a class="intem  " href="#">40</a>
            <a class="intem  " href="#">50</a>
        </div>
        <table class="wxtables table-order mgt20">
            <colgroup>
                <col width="15%">
                    <col width="30%">
                        <col width="10%">
                            <col width="10%">
                                <col width="25%">
            </colgroup>
            <thead>
                <tr>
                	<td>订单号</td>
                    <td>商品</td>
                    <td>收货人</td>
                    <td>实付金额</td>
                    <td>买家留言</td>
                </tr>
            </thead>
            <tbody>
            <#list obj.orders as order>
                    <tr>
                 	<td><a href="${base}/order/order/locate.action?id=${order.id}" style="color:#0055ee">${order.cno}</a></td>
                    <td></td>
                    <td>${order.takercname}</td>
                    <td>${order.amount}</td>
                    <td></td>
                    </tr>
            </#list>        
            </tbody>
            
        </table>
        

        <!-- end wxtables -->
                <!-- end tables-btmctrl -->
    </form>

    <form action="/Order/print_invoice" method="post" id="ids">
        <input type="hidden" name="ids" value="">
    </form>


    <script src="http://res.mp.wifenxiao.com/Public/js/jquery/jquery-1.8.3.min.js"></script>
<script>
$(function(){
	$('.logistics_box').hover(function(){
        var id = $(this).find('.infor_logistics').data("id");
        $.post('/Order/getExpressInfo',{id:id},function(data){
            if(data.status == 1){
                var datas = data.data;
                var html = '';
                    html +='<p class="courier"><em class="name">'+datas.express_name+'</em><em class="number">运单号：'+datas.order_no+'</em></p><ul class="address">';
                if(datas.data){
                    $.each(datas.data,function(n,value){
                        if(n == 0){
                            html +='<li class="current"><span class="place" >'+value.context+'</span><span class="time">'+value.time+'</span><i class="symbol"></i>';
                        }else{
                            html +='<li><span class="place" >'+value.context+'</span><span class="time">'+value.time+'</span><i class="symbol"></i>';
                        }
                    })
                }else if(datas.content){
                    $.each(datas.content,function(n,value){
                        if(n == 0){
                            html +='<li class="current"><span class="place" >'+value['list'][2]+'</span><span class="time">'+value['list'][0]+value['list'][1]+'</span><i class="symbol"></i>';
                        }else{
                            html +='<li><span class="place" >'+value['list'][2]+'</span><span class="time">'+value['list'][0]+value['list'][1]+'</span><i class="symbol"></i>';
                        }
                    })
                }else{
                    $.each(datas.content,function(n,value){
                        if(n == 0){
                            html +='<li class="current"><span class="place" >'+value['list'][1]+'</span><span class="time">'+value['list'][0]+'</span><i class="symbol"></i>';
                        }else{
                            html +='<li><span class="place" >'+value['list'][1]+'</span><span class="time">'+value['list'][0]+'</span><i class="symbol"></i>';
                        }

                    })
                }

                html += '<ul>';
                $('.infor_logistics_box').html(html);
            }else{
                var html = '';
                html +='<p class="courier"><em class="name">获取物流信息失败</em></p>';
                $('.infor_logistics_box').html(html);
            }
        });
		$(this).children('.infor_logistics_box').show();
		$(this).children('.arrow-top').show();
	},function(){
		$(this).children('.infor_logistics_box').hide();
		$(this).children('.arrow-top').hide();
	});	
});
</script>

</div>
<!-- end content-right -->
</div>
</div>
<!-- end container -->

<!--gonggao-->
<div id="gonggao">
	<div class="gonggao_tt"><i class="gicon_gg_up"></i>系统公告<a href="#" class="fr gound_close"></a></div>
    <div class="gonggao_cent">
    	<div class="gonggao_cent_tt">2015-07-05 微分销系统更新</div>
        <div class="gonggao_cent_cent">
            <p><a href="/System/notice_list">添加在线客服方</a></p><p><a href="/System/notice_list">图片库中的图片可以重命名，客户可以根据名称模糊搜索图片库</a></p><p><a href="/System/notice_list">提货点管理添加自提地址增加个固话，但不是必填项</a></p>        </div>
    </div>
    <div class="gonggao_href"><a href="javascript:;" class="fl btn-notice">我知道了</a><a href="/System/notice_list">查看更多>></a></div>
</div><div class="footer">&copy; 2015 西安可可西里电子商务 , Inc. All rights reserved.</div>
<!-- end footer -->
    <div class="fixedBar">
        <ul>
            <li class="shopManager5"><a href="javascript:;" data-target="#shop_5">订单管理</a></li><li class="shopManager6"><a href="javascript:;" data-target="#shop_6">售后服务</a></li>        </ul>
    </div><a href="#" id="j-gotop" class="gotop" title="回到顶部"></a>
<!-- end gotop -->

<script type="text/j-template" id="tpl_tooltips">
	<div class="tooltips" style="display:none;">
	    <span class="tooltips-arrow tooltips-arrow-<%= placement %>"><em>◆</em><i>◆</i></span>
	    <%= content %>
	</div>
</script>
<!-- end tooltips -->

<script type="text/j-template" id="tpl_hint">
	<div class="hint hint-<%= type %>"><%= content %></div>
</script>
<!-- end hint -->

<script type="text/j-template" id="tpl_jbox_simple">
	<div class="mgt30"><%= content %></div>
</script>
<!-- end tpl_jbox_simple -->

<script type="text/j-template" id="tpl_qrcode">
	<div id="qrcode">
		<img src="<%= src %>">
		<a href="javascript:;" class="qrcode-btn j-closeQrcode"><i class="gicon-remove white"></i></a>
	</div>
</script>
<!-- end qrcode -->

<script type="text/j-template" id="tpl_verify_set">
	
	<div class="tabs clearfix mgt15" id="verify-set-tabs">    
	    <a href="javascript:;" class="tabs_a fl active">店铺信息设置</a>
	    <a href="javascript:;" class="tabs_a fl">支付宝收款帐号设置</a>
	    <a href="javascript:;" class="tabs_a fl">微信设置</a>
	    <a href="javascript:;" class="tabs_a fl">提成设置</a>
	</div>  
	<form action="" method="post" id='form-set'>

		<!-- 店铺信息 -->
		<div class="panel-single panel-single-light mgt20 verify-set-div ">
			<div class="formitems">  
				<label class="fi-name"><span class="colorRed">*</span>店铺名称：</label>
				<div class="form-controls">
					<input type="text" class="input" name="title" value="">
					<span class="fi-help-text"></span>
				</div>
			</div>
			<div class="formitems">  
				<label class="fi-name"><span class="colorRed">*</span>联系人：</label>
				<div class="form-controls">
					<input type="text" class="input" name="contact_name" value="">
					<span class="fi-help-text"></span>
				</div>
			</div>
			<div class="formitems">  
				<label class="fi-name"><span class="colorRed">*</span>联系电话：</label>
				<div class="form-controls">
					<input type="text" class="input" name="contact_phone" value="">
					<span class="fi-help-text"></span>
				</div>
			</div>
			<div class="formitems">  
				<label class="fi-name">店铺简介：</label>
				<div class="form-controls">
					<textarea class="textarea" name="description"></textarea>
					<span class="fi-help-text"></span>
				</div>
			</div>
		</div>
		<!-- 支付宝收款帐号 -->
		<div class="panel-single panel-single-light mgt20 verify-set-div hide">

			<div class="alert alert-info disable-del"><h4>提示</h4>目前微信屏蔽了支付宝的链接，使用支付宝支付需要在浏览器中打开支付链接，推荐使用微信支付。</div>
		    <div class="formitems">
		        <label class="fi-name"><span class="colorRed"></span>快速签约：</label>
		        <div class="form-controls">
		            <a href="https://b.alipay.com/order/productDetail.htm?productId=2013080604609688" class="btn btn-warning btn-small" target="_blank">去支付宝</a>
		        </div>
		    </div>
			<div class="formitems mgt20">  
				<label class="fi-name"><span class="colorRed">*</span>支付宝账号：</label>
				<div class="form-controls">
					<input type="text" class="input xlarge j-account" name="alipay_account" value="">
					<span class="fi-help-text"></span>
				</div>
			</div>
			<div class="formitems">  
				<label class="fi-name"><span class="colorRed">*</span>支付宝账号姓名：</label>
				<div class="form-controls">
					<input type="text" class="input xlarge j-name" name="alipay_name" value="">
					<span class="fi-help-text"></span>
				</div>
			</div>
			<div class="formitems">  
				<label class="fi-name"><span class="colorRed">*</span>PID：</label>
				<div class="form-controls">
					<input type="text" class="input xlarge j-pid" name="alipay_partner" value="">
					<span class="fi-help-text">成功申请支付宝接口后获取到的PID</span>
				</div>
			</div>
			<div class="formitems">  
				<label class="fi-name"><span class="colorRed">*</span>KEY：</label>
				<div class="form-controls">
					<input type="text" class="input xlarge j-key" name="alipay_key" value="">
					<span class="fi-help-text">成功申请支付宝接口后获取到的Key</span>
				</div>
			</div>
		</div>
		<!-- 微信 -->
		<div class="panel-single panel-single-light mgt20 verify-set-div hide">
			<div class="alert alert-info">开通微信营销需要绑定微信公众帐号，不知道怎么绑定请参考 <a href="/PubMarketing/setting_guide" target="_blank" class="a_hover">开通指引</a></div>

			<div class="formitems">  
				<label class="fi-name"><span class="colorRed">*</span>名称：</label>
				<div class="form-controls">
					<input type="text" class="input" name="pub_name" value="">
					<span class="fi-help-text"></span>
				</div>
			</div>
			<div class="formitems">  
				<label class="fi-name"><span class="colorRed">*</span>原始ID：</label>
				<div class="form-controls">
					<input type="text" class="input" name="source_id" value="">
					<span class="fi-help-text"></span>
				</div>
			</div>

			<div class="formitems">  
				<label class="fi-name"><span class="colorRed">*</span>微信号：</label>
				<div class="form-controls">
					<input type="text" class="input" name="pub_account" value="">
					<span class="fi-help-text"></span>
				</div>
			</div>
			<div class="formitems" style="margin-top:30px;">  
				<label class="fi-name">回调URL：</label>
				<div class="form-controls pdt5">
					<div class="fl" style="width:355px;">http://m.wxfenxiao.com/Wxapi/index.html?id=2003320</div>
					<a href="javascript:;" class="btn btn-small j-copy" data-copy="http://m.wxfenxiao.com/Wxapi/index.html?id=2003320">复制</a>
				</div>
			</div>
			<div class="formitems">  
				<label class="fi-name">Token：</label>
				<div class="form-controls pdt5">
					<div class="fl" style="width:355px;">fa0f758cb9e5a147358ae0ef0ac878b2</div>
					<a href="javascript:;" class="btn btn-small j-copy" data-copy="fa0f758cb9e5a147358ae0ef0ac878b2">复制</a>
				</div>
			</div>
			<div class="formitems mgt5">  
				<label class="fi-name"><span class="colorRed">*</span>AppId：</label>
				<div class="form-controls">
					<input type="text" class="input xlarge" value="" name="app_id" >
					<span class="fi-help-text"><a href="https://mp.weixin.qq.com/" target="_blank">点击获取</a>(开发者中心)</span>
				</div>
			</div>

			<div class="formitems">  
				<label class="fi-name"><span class="colorRed">*</span>Secret：</label>
				<div class="form-controls">
					<input type="text" class="input xlarge" name="app_secret" value="">
					<span class="fi-help-text"><a href="https://mp.weixin.qq.com/" target="_blank">点击获取</a>(开发者中心)</span>
				</div>
			</div>

		</div>
		<!-- 提成 -->
		<div class="panel-single panel-single-light mgt20 verify-set-div hide">
			<div class="formitems">  
				<label class="fi-name" style="width: 121px;"><span class="colorRed">*</span>直属上级提成比例：</label>
				<div class="form-controls">
					<input type="text" class="input mini j-pid" name="directly_online_ratio" value="0">
					<span class="fi-help-text">购买者直属上级会员的提成比例</span>
				</div>
			</div>

			<div class="formitems">  
				<label class="fi-name" style="width: 121px;"><span class="colorRed">*</span>上级会员提成比例：</label>
				<div class="form-controls">
					<input type="text" class="input mini j-pid" name="superior_online_ratio" value="0">
					<span class="fi-help-text">购买者直属上级会员的上级会员提成比例</span>
				</div>
			</div>

            <div class="formitems">
                <label class="fi-name" style="width: 121px;"><span class="colorRed">*</span>上级的上级会员提成比例：</label>
                <div class="form-controls">
                    <input type="text" class="input mini j-pid" name="superior_online_ratio" value="0">
                    <span class="fi-help-text">购买者直属上级会员上级的上级提成比例</span>
                </div>
            </div>

		</div>

		<div class="panel-single panel-single-light mgt20 txtCenter">
		    <a href="javascript:;" class="btn" id='verify-set-prev'>上一步</a>
		    <a href="javascript:;" class="btn btn-primary" id='verify-set-next'>下一步</a>
		    <a href="javascript:;" class="btn btn-primary hide" id='verify-set-save'>保存</a>
		</div>
	</form>
</script>



    <!-- deliver start -->
    <script type="text/j-template" id="tpl_order_lists_deliver">
    <div>
        <div class="formitems">
            <label class="fi-name"><span class="colorRed">*</span>自提：</label>
            <div class="form-controls">
                <div class="radio-group">
                <label><input type="radio" name="is_self_take" value="0" <% if(!self_id){%>checked<%}%>>否</label>
                <label><input type="radio" name="is_self_take" value="1" <% if(self_id){%>checked<%}%>>是</label>
                </div>
            </div>
        </div>
        <div class="formitems J_express_company">
            <label class="fi-name"><span class="colorRed">*</span>物流公司：</label> 
            <div class="form-controls">
                <select name="deliver_wuliu" class="select send_deliver">
                    <option value="" selected>请选择</option>
                    <option value="1">顺丰</option><option value="2">申通</option><option value="3">圆通</option><option value="4">EMS</option><option value="5">中通</option><option value="6">韵达</option><option value="7">中国邮政</option><option value="8">宅急送</option><option value="9">天天</option><option value="11">百世汇通</option><option value="10">其它</option>                </select>
                <span class="fi-help-text"></span>
            </div> 
        </div>
        <div class="formitems hide express_class">
            <label class="fi-name"><span class="colorRed"></span>快递公司：</label>
            <div class="form-controls">
                <input type="text" name="express_name" class="input">
                <span class="fi-help-text"></span>
            </div>
        </div>
        <div class="formitems J_express_no">
            <label class="fi-name"><span class="colorRed">*</span>快递单号：</label>
                <div class="form-controls">
                    <input type="text" name="deliver_number" class="input">
                <span class="fi-help-text"></span>
            </div>
        </div>
        <div class="formitems J_select_address hide">
            <label class="fi-name"><span class="colorRed">*</span>自提地址：</label>
            <div class="form-controls">
                <select name="self_address_id" class="select">
                    <option value="-1" selected>请选择</option>
                    <option value="0">新建自提地址</option>
                                    </select>
                <span class="fi-help-text"></span>
            </div>
        </div>
        <!--<div class="formitems hide J_self_address">
            <label class="fi-name"><span class="colorRed">*</span>自提地址：</label>
            <div class="form-controls">
                <input type="text" name="self_take_address" class="input xlarge">
                <span class="fi-help-text"></span>
            </div>
        </div>-->
        <div class="formitems hide J-name">
            <label class="fi-name"><span class="colorRed">*</span>联系人姓名：</label>
            <div class="form-controls">
                <input type="text" placeholder="联系人姓名" name="name" class="input">
                <span class="fi-help-text"></span>
            </div>
        </div>
        <div class="formitems hide J-mobile">
            <label class="fi-name"><span class="colorRed">*</span>联系人手机号：</label>
            <div class="form-controls">
                <input type="text" placeholder="联系人手机号" name="mobile" class="input">
                <span class="fi-help-text"></span>
            </div>
        </div>
        <div class="formitems hide J-mobile">
            <label class="fi-name"><span class="colorRed">*</span>联系人固定电话：</label>
            <div class="form-controls">
                <input type="text" placeholder="联系人固定电话" name="phone" class="input">
                <span class="fi-help-text"></span>
            </div>
        </div>
        <div class="formitems hide J-province">
            <label class="fi-name"><span class="colorRed">*</span>选择省份：</label>
            <div class="form-controls">
                <select name="province_id" class="select s_province">
                    <option value="0" selected="">--请选择--</option>
                    <option value="310000">上海</option><option value="530000">云南省</option><option value="150000">内蒙古自治区</option><option value="110000">北京</option><option value="710000">台湾</option><option value="220000">吉林省</option><option value="510000">四川省</option><option value="120000">天津</option><option value="640000">宁夏回族自治区</option><option value="340000">安徽省</option><option value="370000">山东省</option><option value="140000">山西省</option><option value="440000">广东省</option><option value="450000">广西壮族自治区</option><option value="650000">新疆维吾尔自治区</option><option value="320000">江苏省</option><option value="360000">江西省</option><option value="130000">河北省</option><option value="410000">河南省</option><option value="330000">浙江省</option><option value="460000">海南省</option><option value="990000">海外</option><option value="420000">湖北省</option><option value="430000">湖南省</option><option value="820000">澳门特别行政区</option><option value="620000">甘肃省</option><option value="350000">福建省</option><option value="540000">西藏自治区</option><option value="520000">贵州省</option><option value="210000">辽宁省</option><option value="500000">重庆</option><option value="610000">陕西省</option><option value="630000">青海省</option><option value="810000">香港特别行政区</option><option value="230000">黑龙江省</option>                </select>
                <span class="fi-help-text"></span>
            </div>
        </div>
        <div class="formitems hide J-city">
            <label class="fi-name"><span class="colorRed">*</span>选择城市：</label>
            <div class="form-controls">
                <select name="city_id" class="select s_city">

                </select>
                <span class="fi-help-text"></span>
            </div>
        </div>
        <div class="formitems hide J-area">
            <label class="fi-name"><span class="colorRed">*</span>选择地区：</label>
            <div class="form-controls">
                <select name="area_id" class="select s_area">

                </select>
                <span class="fi-help-text"></span>
            </div>
        </div>
        <div class="formitemsm hide J-address">
            <label class="fi-name"><span class="colorRed">*</span>详细地址：</label>
            <div class="form-controls">
                <textarea name="address" id="" class="textarea small"></textarea>
                <span class="fi-help-text"></span>
            </div>
        </div>
    </script>
    <!-- deliver end -->

    <!-- batch_deliver start -->
    <script type="text/j-template" id="tpl_batch_deliver">
        <div>
            <div class="formitems">
                <label class="fi-name"><span class="colorRed">*</span>物流公司：</label>
                <div class="form-controls">
                    <select name="deliver_wuliu" class="select">
                        <option value="" selected>请选择</option>
                        <option value="1">顺丰</option><option value="2">申通</option><option value="3">圆通</option><option value="4">EMS</option><option value="5">中通</option><option value="6">韵达</option><option value="7">中国邮政</option><option value="8">宅急送</option><option value="9">天天</option><option value="11">百世汇通</option><option value="10">其它</option>                    </select>
                    <span class="fi-help-text"></span>
                </div>
            </div>
            <div class="formitems">
                <label class="fi-name"><span class="colorRed">*</span>起始快递单号：</label>
                <div class="form-controls">
                    <input type="text" name="deliver_number" class="input">
                    <span class="fi-help-text"></span>
                </div>
            </div>
        </div>
    </script>

    <script type="text/j-template" id="tpl_order_lists_express">
        <div>
            <div class="formitems">
                <label class="fi-name"><span class="colorRed">*</span>物流公司：</label>
                <div class="form-controls">
                    <select name="deliver_wuliu" class="select send_deliver">
                        <option value="" selected>请选择</option>
                        <option value="1" <% if(express == 1){ %>selected<% } %>>顺丰</option><option value="2" <% if(express == 2){ %>selected<% } %>>申通</option><option value="3" <% if(express == 3){ %>selected<% } %>>圆通</option><option value="4" <% if(express == 4){ %>selected<% } %>>EMS</option><option value="5" <% if(express == 5){ %>selected<% } %>>中通</option><option value="6" <% if(express == 6){ %>selected<% } %>>韵达</option><option value="7" <% if(express == 7){ %>selected<% } %>>中国邮政</option><option value="8" <% if(express == 8){ %>selected<% } %>>宅急送</option><option value="9" <% if(express == 9){ %>selected<% } %>>天天</option><option value="11" <% if(express == 11){ %>selected<% } %>>百世汇通</option><option value="10" <% if(express == 10){ %>selected<% } %>>其它</option>                    </select>
                    <span class="fi-help-text"></span>
                </div>
            </div>
            <div class="formitems hide express_class">
                <label class="fi-name"><span class="colorRed"></span>快递公司：</label>
                <div class="form-controls">
                    <input type="text" name="express_name" class="input" value="<%= express_name %>">
                    <span class="fi-help-text"></span>
                </div>
            </div>
            <div class="formitems">
                <label class="fi-name"><span class="colorRed">*</span>快递单号：</label>
                <div class="form-controls">
                    <input type="text" name="deliver_number" class="input" value="<%= express_no %>">
                    <span class="fi-help-text"></span>
                </div>
            </div>
        </div>
    </script>
    <!-- batch_deliver end -->

    <!-- deliver start -->
    <script type="text/j-template" id="tpl_order_lists_print">
        <div>
            <div class="formitems">
                <label class="fi-name"><span class="colorRed">*</span>快递单模板：</label>
                <div class="form-controls">
                    <select name="print_tpl" class="select">
                                            </select>
                </div>
            </div>
        </div>
    </script>

    <script type="text/j-template" id="tpl_order_lists_address">
        <div>
            <div class="formitems">
                <label class="fi-name"><span class="colorRed"></span>收货人姓名：</label>
                <div class="form-controls">
                    <input type="text" name="name" class="input" value="<%= name %>">
                    <span class="fi-help-text"></span>
                </div>
            </div>
            <div class="formitems">
                <label class="fi-name"><span class="colorRed">*</span>收货人手机号：</label>
                <div class="form-controls">
                    <input type="text" name="mobile" class="input" value="<%= mobile %>">
                    <span class="fi-help-text"></span>
                </div>
            </div>
            <div class="formitems">
                <label class="fi-name"><span class="colorRed">*</span>收货人地址详情：</label>
                <div class="form-controls">
                    <input type="text" name="address" class="input" value="<%= address %>">
                    <span class="fi-help-text"></span>
                </div>
            </div>
        </div>
    </script>
    <!-- batch_address end -->
    <script type="text/j-template" id="tpl_order_lists_sku">
        <div class="modifyPanel">
            <ul class="goodsList">
                <% _.each(data,function(item){ %>
                <li>
                    <a href="<%= item.href %>" target="_blank" title="<%= item.title %>">
                        <div class="goodsList-img">
                            <img src="<%= item.img %>" alt="<%= item.title %>">
                        </div>
                        <div class="goodsList-info">
                            <p><%= item.title %></p>
                            <span>&yen;<%= item.price %></span>
                            <span class="colorGray">数量：<%= item.num %></span>
                        </div>
                    </a>
                    <% if(item.arr_sku.length){ %>
                    <div class="formitems">
                        <div class="form-controls">
                            <select name="sku_id[]" class="select">
                                <%_.each(item.arr_sku,function(sku){ %>
                                <span class="colorGray"><option value="<%= sku.sku_id %>" <% if(item.sku_id ==  sku.sku_id){ %>selected<% }%>><%= sku.sku_name%></option></span>
                                <% }); %>
                            </select>
                            <input type="hidden" name="item_id[]" value="<%= item.item_id %>">
                            <span class="fi-help-text"></span>
                        </div>
                    </div>
                    <% } %>
                </li>
                <% }); %>
            </ul>
        </div>
    </script>

    <script type="text/j-template" id="tpl_order_slef_address">
        <div class="formitems">
            <label class="fi-name"><span class="colorRed">*</span>自提地址：</label>
            <div class="form-controls">
                <select name="self_address_id" class="select J-selectAddress">
                    <option value="-1" selected>请选择</option>
                    <option value="0">新建自提地址</option>
                                    </select>
                <span class="fi-help-text"></span>
            </div>
        </div>
        <div class="formitems hide J-selfname">
            <label class="fi-name"><span class="colorRed">*</span>联系人姓名：</label>
            <div class="form-controls">
                <input type="text" placeholder="联系人姓名" name="name" class="input">
                <span class="fi-help-text"></span>
            </div>
        </div>
        <div class="formitems hide J-selfmobile">
            <label class="fi-name"><span class="colorRed">*</span>联系人手机号：</label>
            <div class="form-controls">
                <input type="text" placeholder="联系人手机号" name="mobile" class="input">
                <span class="fi-help-text"></span>
            </div>
        </div>
        <div class="formitems hide J-selfmobile">
            <label class="fi-name"><span class="colorRed">*</span>联系人固定电话：</label>
            <div class="form-controls">
                <input type="text" placeholder="联系人固定电话" name="phone" class="input">
                <span class="fi-help-text"></span>
            </div>
        </div>
        <div class="formitems hide J-selfprovince">
            <label class="fi-name"><span class="colorRed">*</span>选择省份：</label>
            <div class="form-controls">
                <select name="province_id" class="select s_province">
                    <option value="0" selected="">--请选择--</option>
                    <option value="310000">上海</option><option value="530000">云南省</option><option value="150000">内蒙古自治区</option><option value="110000">北京</option><option value="710000">台湾</option><option value="220000">吉林省</option><option value="510000">四川省</option><option value="120000">天津</option><option value="640000">宁夏回族自治区</option><option value="340000">安徽省</option><option value="370000">山东省</option><option value="140000">山西省</option><option value="440000">广东省</option><option value="450000">广西壮族自治区</option><option value="650000">新疆维吾尔自治区</option><option value="320000">江苏省</option><option value="360000">江西省</option><option value="130000">河北省</option><option value="410000">河南省</option><option value="330000">浙江省</option><option value="460000">海南省</option><option value="990000">海外</option><option value="420000">湖北省</option><option value="430000">湖南省</option><option value="820000">澳门特别行政区</option><option value="620000">甘肃省</option><option value="350000">福建省</option><option value="540000">西藏自治区</option><option value="520000">贵州省</option><option value="210000">辽宁省</option><option value="500000">重庆</option><option value="610000">陕西省</option><option value="630000">青海省</option><option value="810000">香港特别行政区</option><option value="230000">黑龙江省</option>                </select>
                <span class="fi-help-text"></span>
            </div>
        </div>
        <div class="formitems hide J-selfcity">
            <label class="fi-name"><span class="colorRed">*</span>选择城市：</label>
            <div class="form-controls">
                <select name="city_id" class="select s_city">

                </select>
                <span class="fi-help-text"></span>
            </div>
        </div>
        <div class="formitems hide J-selfarea">
            <label class="fi-name"><span class="colorRed">*</span>选择地区：</label>
            <div class="form-controls">
                <select name="area_id" class="select s_area">

                </select>
                <span class="fi-help-text"></span>
            </div>
        </div>
        <div class="formitemsm hide J-selfaddress">
            <label class="fi-name"><span class="colorRed">*</span>详细地址：</label>
            <div class="form-controls">
                <textarea name="address" id="" class="textarea small"></textarea>
                <span class="fi-help-text"></span>
            </div>
        </div>
    </script>

    <!-- deliver end -->
    <script type="text/j-template" id="tpl_order_lists_modify">
	<div class="modifyPanel">
		<ul class="goodsList">
			<% _.each(dataset,function(data){ %>
				<li>
					<a href="<%= data.href %>" target="_blank" title="<%= data.title %>">
						<div class="goodsList-img">
							<img src="<%= data.img %>" alt="<%= data.title %>">
						</div>
						<div class="goodsList-info">
							<p><%= data.title %></p>
							<span>&yen;<%= data.price %></span>
							<span class="colorGray"><%= data.sku %></span>
							<span class="colorGray">数量：<%= data.num %></span>
						</div>
					</a>
				</li>
			<% }); %>
		</ul>
		<ul class="orderInfo pdt20">
			<% if(coupon){ %>
			<li class="formitems inline">
				<label class="fi-name">使用优惠券：</label>  
			    <div class="form-controls pdt3"><%= coupon.title %> 抵现<%= coupon.money %>元</div> 
			</li>
			<% } %>
			<li class="formitems inline">
				<label class="fi-name">涨价或减价：</label>  
			    <div class="form-controls">
					<input type="text" class="input mini j-modify-riseOrDrop" value="<%= riseOrDrop %>">
			    </div> 
			</li>
			<li class="formitems inline">
				<label class="fi-name">运费：</label>  
			    <div class="form-controls">
					<div class="radio-group">  
			            <label><input type="radio" name="freight" class="j-modify-freight" value="0" <% if(freight==0){ %> checked <% } %> >免运费</label>
			            <label><input type="radio" name="freight" class="j-modify-freight" value="1" <% if(freight>0){ %> checked <% } %>>自定义</label>
			        </div>
			        <div class="custom_yunfei inline-block">
		            	<input type="text" class="input j-modify-freightipt" value="<%= freight %>" style="width:50px;" <% if(freight==0){ %> disabled <% } %>>
		            	<span>元</span>
		            </div>
			    </div> 
			</li>
			<li class="formitems inline">
				<label class="fi-name">商品总价：</label>  
			    <div class="form-controls pdt3 ftsize20">
			    	&yen;<span><%= orderPrice %></span>
			    </div> 
			</li>
			<li class="formitems inline">
				<label class="fi-name">实际支付：</label>  
			    <div class="form-controls pdt3 ftsize20 colorRed">
			    	&yen;<span class="j-modify-ptout-realPrice"><%= realPrice %></span>
			    	<span class="j-modify-ptout-freight ftsize12 colorGray">
			    		<% if(freight==0){ %>
				    		(包邮)
				    	<% } else { %>
				    		(包含<%= freight %>元邮费)
				    	<% }%>
			    	</span>
			    </div> 
			</li>
		</ul>
	</div>
</script>
<!-- end tpl_order_lists_modify -->

<!--end front template  -->

<script src="http://res.mp.wifenxiao.com/Public/js/dist/lib-min.js"></script>
<script src="${base}/public/plugins/jbox/jquery.jbox-min.js"></script>
<script src="${base}/public/plugins/zclip/jquery.zclip-min.js"></script>
<!-- 线上环境 -->
    <script src="http://res.mp.wifenxiao.com/Public/js/dist/component-min.js"></script>
    <script src="http://res.mp.wifenxiao.com/Public/modulesJs/scroll.js"></script>
<!--[if lt IE 10]>
<script src="http://res.mp.wifenxiao.com/Public/js/jquery/jquery.placeholder-min.js"></script>
<script>
    $(function(){
        //修复IE下的placeholder
        $('.input,.textarea').placeholder();
    });
</script>
<![endif]-->


    <script src="http://res.mp.wifenxiao.com/Public/js/dist/home/Order/lists.js"></script>
    <script src="${base}/public/plugins/My97DatePicker/WdatePicker.js"></script>
    

<script>
    $(function(){
                                $(".j-copy").zclip({
            path: '/Public/plugins/zclip/ZeroClipboard.swf',
            copy: function(){
                return $(this).data('copy');
            },
            afterCopy:function(){
                HYD.hint("success","内容已成功复制到您的剪贴板中");
            }
        });
        $(".btn-notice").click(function(){
            // $.post('/System/readAllNotice',{},function(){
            //     window.location.reload();
            // })
            $.ajax({
                url: '/System/readAllNotice',
                type: 'POST',
                success:function(data){
                    if(data.status == 1){
                        window.location.reload();
                    }else{
                         HYD.hint("danger",data.msg);
                    }
                    
                }
            })
        });
        
        
        ;(function(){
            // 首页竖线到底
            var height1=$(".content-right").height();
            var height2=$(".content-left").height();
            if(parseInt(height1) < parseInt(height2)){
                $(".content-right").css({'min-height': height2});
            };
            
                    })();
       
    });
</script>
<!-- end session hint -->
<script>
	$(function () {
		setTimeout(gggoup(),5000);
		$('.gound_close').click(function(){
			$('#gonggao').animate({bottom:"-270px"},1000);
		});
	});
	function gggoup(){
		$('#gonggao').animate({bottom:"3px"},1000);
	};
</script>


<!-- pujian add -->
<script>

$(function(){

//////////

$("#tabs a").click(function() {
	console.log($(this));
	console.log($(this).attr("state"));
	$("#state").val($(this).attr("state"));
	$("#tabs a").removeClass("active");
	$(this).addClass("active");
	page_browse();
})

$("#bt_submit").click(function() {page_submit()});

function page_browse()
{
	$("#queryform").submit();
}

//////////
})

</script>

</body>
</html>