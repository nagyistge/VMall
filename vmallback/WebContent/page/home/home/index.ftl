<html>
<head>
<title>优品365.首页</title>
</head>
<body>
<link rel="stylesheet" href="${base}/page/home/home/home.css">

    <h1 class="content-right-title">欢迎您！</h1>
    <div class="zh_infor_box">
        <div class="zh_infor_left">
            <div class="zh_infor_tt">
                <i class="lit_icon mfl"></i>
                <span class="itt"><b>当前账号：</b></span></div>
            <div class="zh_infor_cent" style="margin-top:8px;">
                <div class="zh_infor_item">分销商数：<span class="red">0</span></div>
                <div class="zh_infor_item">待审核分销商数：<span class="red">0</span></div>
                <div class="zh_infor_item">已支出佣金：<span class="red">0</span> 元</div>
                <div class="zh_infor_item">待提现佣金笔数：<span class="red">0</span> 笔</div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="zh_infor_right">
            <div class="zh_infor_tt">
                <i class="lit_icon mfr"></i>
                <span class="itt"><b>库存提醒：</b></span><a href="/Item/lists/item_status/warn">有<span class="red">0</span>件商品已达到警戒库存</a>
            </div>
            <div class="zh_infor_cent" style="margin-top:8px;">
                <div class="zh_infor_item">出售中的商品数：<span class="red">0</span></div>
                <div class="zh_infor_item">仓库中商品数：<span class="red">0</span></div>
                <div class="zh_infor_item">已售罄的商品数：<span class="red">3</span></div>
                <div class="clear"></div>
            </div>
            <div class="zh_infor_cent">
                <div class="zh_infor_item">待付款订单数：<span class="red">0</span> 笔</div>
                <div class="zh_infor_item">待发货订单数：<span class="red">0</span> 笔</div>
                <div class="zh_infor_item">待退/换货订单数：<span class="red">0</span> 笔</div>
                <div class="zh_infor_item">已完成订单数：<span class="red">0</span> 笔</div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="clear"></div>
    </div>

    <table class="wxtables data mgt15">
        <colgroup>
			<col width="20%">
			<col width="20%">
			<col width="20%">
			<col width="20%">
            <col width="20%">
        </colgroup>
        <thead>
            <tr>
                <td colspan="6" class="left" style="font-size:14px;">数据统计</td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    <div class="dataItems">总计订单（笔）</div>
                    <div class="dataItems">
                        <span class="num1">0</span>
                    </div>
                    <div class="dataItems">环比增幅
                        <span class="num2 data-rise">0% <i class="icon-tablesData"></i>
                        </span>
                    </div>
                </td>
                <td>
                    <div class="dataItems">总消费金额（元）</div>
                    <div class="dataItems">
                        <span class="num1">0</span>
                    </div>
                    <div class="dataItems">环比增幅
                        <span class="num2 data-rise">0% <i class="icon-tablesData"></i>
                        </span>
                    </div>
                </td>

                <td>
                    <div class="dataItems">本月订单（笔）</div>
                    <div class="dataItems">
                        <span class="num1">0</span>
                    </div>
                    <div class="dataItems">环比增幅
                        <span class="num2 data-rise">0% <i class="icon-tablesData"></i>
                        </span>
                    </div>
                </td>
                <td>
                    <div class="dataItems">本月消费金额（元）</div>
                    <div class="dataItems">
                        <span class="num1">0</span>
                    </div>
                    <div class="dataItems">环比增幅
                        <span class="num2 data-rise"> 0% <i class="icon-tablesData"></i>
                        </span>
                    </div>
                </td>
                <td class="bdr">
                    <div class="dataItems">昨日店铺浏览量</div>
                    <div class="dataItems">
                        <span class="num1">0</span>
                    </div>
                    <div class="dataItems">环比增幅
                        <span class="num2 data-rise"> 100% <i class="icon-tablesData"></i>
                        </span>
                    </div>
                </td>

            </tr>
        </tbody>
    </table>

    <div class="chartWrap mgt15 clearfix">
        <div class="chartBox chartBox-bdr chartBox-fullcolor per50 fl">
            <div class="cb-title">
                <h2>订单笔数趋势图</h2>
            </div>
            <div class="cb-contain">
            	<div class="cbc-live clearfix">
            		<div class="cbc-live-today fl">
            			<span>今日订单数（笔）</span>
            			<strong id="ddzs_t">0</strong>
            		</div>
            		<div class="cbc-live-yesterday fl">
            			<span>昨日订单数（笔）</span>
            			<strong id="ddzs_y">0</strong>
            		</div>
            	</div>
            	<div class="table-loading" id="loading_chart_ddzs">
	            	<div class="progress">
						<div class="progress-bar progress-bar-warning progress-bar-striped active" style="width: 100%;"></div>
					</div>
            	</div>
            	<div id="chart_ddzs" class="mgt10 txtCenter" style="display:none;width:442px; height:200px;"></div>
            </div>
        </div>
        <div class="chartBox chartBox-bdr chartBox-fullcolor per50 fr">
            <div class="cb-title">
                <h2>分销订单笔数趋势图</h2>
            </div>
            <div class="cb-contain">
            	<div class="cbc-live clearfix">
            		<div class="cbc-live-today fl">
            			<span>今日分销订单数（笔）</span>
            			<strong id="fxddzs_t">0</strong>
            		</div>
            		<div class="cbc-live-yesterday fl">
            			<span>昨日分销订单数（笔）</span>
            			<strong id="fxddzs_y">0</strong>
            		</div>
            	</div>
            	<div class="table-loading" id="loading_chart_fxddzs">
	            	<div class="progress">
						<div class="progress-bar progress-bar-warning progress-bar-striped active" style="width: 100%;"></div>
					</div>
            	</div>
            	<div id="chart_fxddzs" class="mgt10 txtCenter" style="display:none;width:442px; height:200px;"></div>
            </div>
        </div>
    </div>

    <div class="chartWrap mgt15 clearfix">
        <div class="chartBox chartBox-bdr chartBox-fullcolor per50 fl">
            <div class="cb-title">
                <h2>订单金额统计</h2>
            </div>
            <div class="cb-contain">
            	<div class="table-loading" id="loading_chart_ddje">
	            	<div class="progress">
						<div class="progress-bar progress-bar-warning progress-bar-striped active" style="width: 100%;"></div>
					</div>
            	</div>
            	<div id="chart_ddje" class="mgt10 txtCenter" style="display:none;width:442px; height:250px;"></div>
            </div>
        </div>
        <div class="chartBox chartBox-bdr chartBox-fullcolor per50 fr">
            <div class="cb-title">
                <h2>订单统计</h2>
            </div>
            <div class="cb-contain">
            	<div class="table-loading" id="loading_chart_ddtjpei">
	            	<div class="progress">
						<div class="progress-bar progress-bar-warning progress-bar-striped active" style="width: 100%;"></div>
					</div>
            	</div>
            	<div id="chart_ddtjpei" class="mgt10 txtCenter" style="display:none;width:442px; height:250px;"></div>
            </div>
        </div>
    </div>

    <div class="chartWrap mgt15 clearfix">
        <div class="chartBox chartBox-bdr per50 fl">
            <div class="cb-title">
                <h2>分销商上月佣金真实排名</h2>
            </div>
            <div class="cb-contain" style="padding:0;">
                <table class="chart-table">
                    <colgroup>
                        <col width="7%">
                        <col width="25%">
                        <col width="20%">
                        <col width="18%">
                        <col width="18%">
                    </colgroup>
                    <thead>
                    <tr>
                        <td></td>
                        <td>昵称/手机号</td>
                        <td>收入佣金</td>
                        <td>佣金余额</td>
                        <td>排名</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr><td colspan="4" class="txtCenter">暂无信息</td></tr>
                                            </tbody>
                </table>
            </div>
        </div>
        <div class="chartBox chartBox-bdr per50 fr">
            <div class="cb-title">
                <h2>分销商总佣金真实排名</h2>
            </div>
            <div class="cb-contain" style="padding:0;">
                <table class="chart-table">
                    <colgroup>
                        <col width="7%">
                        <col width="20%">
                        <col width="18%">
                        <col width="18%">
                        <col width="18%">
                    </colgroup>
                    <thead>
                    <tr>
                        <td></td>
                        <td>昵称/手机号</td>
                        <td>收入佣金</td>
                        <td>佣金余额</td>
                        <td>排名</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr><td colspan="4" class="txtCenter">暂无信息</td></tr>
                                            </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="chartWrap mgt15 clearfix">
        <div class="chartBox chartBox-bdr per50 fl">
            <div class="cb-title">
                <h2>会员上月积分真实排名</h2>
            </div>
            <div class="cb-contain" style="padding:0;">
                <table class="chart-table">
                    <colgroup>
                        <col width="9%">
                        <col width="41%">
                        <col width="30%">
                        <col width="20%">
                    </colgroup>
                    <thead>
                    <tr>
                        <td></td>
                        <td>昵称/手机号</td>
                        <td>获得积分</td>
                        <td>排名</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr><td colspan="4" class="txtCenter">暂无信息</td></tr>
                                            </tbody>
                </table>
            </div>
        </div>
        <div class="chartBox chartBox-bdr per50 fr">
            <div class="cb-title">
                <h2>会员当前积分真实排名</h2>
            </div>
            <div class="cb-contain" style="padding:0;">
                <table class="chart-table">
                    <colgroup>
                        <col width="9%">
                        <col width="41%">
                        <col width="30%">
                        <col width="20%">
                    </colgroup>
                    <thead>
                    <tr>
                        <td></td>
                        <td>昵称/手机号</td>
                        <td>当前积分</td>
                        <td>排名</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr><td colspan="4" class="txtCenter">暂无信息</td></tr>
                                            </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="fxslist">
        <div class="fxstitle"><span></span>最近1分钟内完成订单交易的商家</div>
        <div class="fxsmain">
            <!-- 滚动主要部分 -->
            <div class="fxsitem clearfix">
                <!-- 列表 -->
                <div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2001237/2015-05/555753952a41e.png" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>淘淘货源网</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2001237.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2003433/2015-06/55701c7112d95.jpg@!w640" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>助梦平台</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2003433.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2003088/2015-05/55670b88d2940.jpg@!w640" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>迷纱丽旗舰店</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2003088.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2000583/2015-04/55368889bf371.png" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>艾尚铭丽</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000583.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2002638/2015-06/558a29d13b12e.jpg" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>6号手机店</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2002638.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2002082/2015-05/5567c553ed122.jpg@!w640" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>首相总仓</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2002082.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://res.mp.wifenxiao.com/Public/images/fxser.png" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>生生慢微商城</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000793.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2003144/2015-05/55628fe8959a8.jpg" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>藏菁阁 </p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2003144.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2003587/2015-06/5587dfb1c829f.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>柏沛乐</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2003587.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2002542/2015-05/5552e2c37745e.jpg@!w640" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>茶清酒浓</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2002542.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2002807/2015-06/557942d45ef80.jpg" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>广州好惠联电子商务有限公司</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2002807.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2001118/2015-05/5551f1a669127.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>缔凡奇</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2001118.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2000232/2015-03/551120751de42.jpg" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>净血</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000232.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wifenxiao.com/1661/2015-03/5502a9cca5b2c.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>知味养生商城</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F1647.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2001965/2015-06/5577c6ece2966.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>芳源集团微分销</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2001965.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2000636/2015-05/554aeb4619944.jpg" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>OneCity</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000636.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wifenxiao.com/644/2015-01/54b6a5514392f.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>今日英才理财网校</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F630.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2002590/2015-05/555313828f753.jpeg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>孔嘉优品</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2002590.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2001106/2015-04/552e20b1b0208.png" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>嘿车</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2001106.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2000830/2015-05/55698e049223a.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>云上购</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000830.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2001558/2015-04/553cb36a59409.jpg@!w640" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>茶酒人生</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2001558.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2001833/2015-05/554a2d28c83f3.jpg" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>良品易购</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2001833.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2002291/2015-06/5572ab6e88074.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>250车品网</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2002291.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2000337/2015-06/55747458e2c7d.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>约美会</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000337.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://res.mp.wifenxiao.com/Public/images/fxser.png" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>国琼养生</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000556.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2004413/2015-06/557ecf5075048.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>莉莎度品牌（中国）事业部</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2004413.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2000173/2015-03/55122b98b01c5.png" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>艾 Acting  庭</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000173.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2000383/2015-04/553dfb811ec9c.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>又见五台山票务</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000383.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2002810/2015-05/5559d71d529ea.jpg@!w640" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>聚贤圈微分销商城</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2002810.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2000892/2015-04/552886c665994.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>林爱朴国际商城</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000892.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2002101/2015-05/554833aeb1e4b.jpg" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>MEWE营养专家</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2002101.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2003481/2015-05/5567e7afccc33.jpg@!w640" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>宁夏红微商城</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2003481.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2002298/2015-05/5554aa7992de4.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>众筹商城</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2002298.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wifenxiao.com/992/2015-01/54c89a5221fd0.png" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>土得土微商圈</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F978.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wifenxiao.com/707/2015-03/55089e0637e68.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>苏城生活微商城</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F693.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2000326/2015-03/551a3b74e3ccd.jpg" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>波比爱BIOBERI</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000326.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2000913/2015-04/552895dae7071.jpg@!w640" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>CRISTHOURS库里思·奥</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000913.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2000120/2015-03/55190d6fe68d0.png" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>微云分销商城</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000120.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2001311/2015-04/55345579438ad.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>兰博仕汽车养护专家</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2001311.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2000836/2015-04/553f70fb616c4.png" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>K佳生活馆</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000836.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2002454/2015-05/5555c03848632.JPG@!w640" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>九谷吉地</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2002454.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://res.mp.wifenxiao.com/Public/images/fxser.png" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>全球扫微商城</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F1267.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2000087/2015-03/550c08d12823e.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>禧汇翔</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000087.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2002004/2015-05/5549fd1cb980b.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>优贝贝</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2002004.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://res.mp.wifenxiao.com/Public/images/fxser.png" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>花篮子商城</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F1000.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2000228/2015-05/556a6b8f877b2.png" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>易禾微盟</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000228.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wifenxiao.com/1421/2015-04/552f3bd0e2a5b.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>智取时代（北京）科技有限公司石家庄分公司</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F1407.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2003353/2015-05/5566787fb95ef.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>一网一店</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2003353.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wifenxiao.com/1397/2015-03/5504052ad341b.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>云南元素神玛哥商城</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F1383.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wifenxiao.com/919/2015-02/54d6d4c504f81.png" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>赢在云端</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F905.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2000851/2015-05/555018c54548c.png" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>黛芙琪商城</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000851.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2002819/2015-06/556d0032cb69d.gif" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>汇美零食</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2002819.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2000548/2015-04/552c7252a11a8.jpg@!w640" width="82" height="82" alt=""><span class="enterprise">企业版</span></div>
                        <p>量子康</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000548.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2001275/2015-04/553476ae5f309.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>叁盛集团</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2001275.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2001839/2015-05/555a8eebecaf2.jpeg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>汐颜若水</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2001839.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://res.mp.wifenxiao.com/Public/images/fxser.png" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>.</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode ">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretArr"></i>
                                <i class="caretArr caretTop"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F455.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2000709/2015-04/5520b0103e791.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>爱客兔</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2000709.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2002734/2015-05/555d94ef2cd27.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>内衣微连锁</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2002734.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2003517/2015-06/557969f185e8a.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>花样童依</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2003517.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/1813/2015-04/55422d5f8a935.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>博慧综合商城</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F1799.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://res.mp.wifenxiao.com/Public/images/fxser.png" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>蓝鲨国际</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2002915.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/2001658/2015-05/55518ccbba7a5.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>天下瑰宝</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F2001658.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://img.wxfenxiao.com/1999940/2015-05/5569251ec0a47.jpg" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>顺康之家创富商城</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F26.html" alt="">
                            </div>
                        </div>
                    </div><div class="fxsinfo fl">
                        <div class="fxsimg"><img src="http://res.mp.wifenxiao.com/Public/images/fxser.png" width="82" height="82" alt=""><span class="group">集团版</span></div>
                        <p>微妆美集</p>
                        <!-- 商家二维码 -->
                        <div class="fxscode fxscode2">
                            <div class="fxscodewrap">
                                <!-- 小箭头 -->
                                <i class="caretDomn"></i>
                                <i class="caretDomn caretDomn2"></i>
                                <!-- 二维码 -->
                                <img src="/Public/qrcode?link=http%3A%2F%2Fm.wxfenxiao.com%2FShop%2Findex%2Fsid%2F659.html" alt="">
                            </div>
                        </div>
                    </div>            </div>
            <!-- 滚动焦点 -->
            <div class="fxspageicon">
                <span class="cur"></span>
                <span></span>
                <span></span>
                <span></span>
            </div>
        </div>
    </div>


</body>
</html>