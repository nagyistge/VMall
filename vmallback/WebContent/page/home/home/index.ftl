<html>
<head>
<title>天狗微商城.首页</title>
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
            </div>
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