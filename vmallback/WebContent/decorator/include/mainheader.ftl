
    <div class="inner clearfix">
        <div class="fl">
            <a href="/" class="header-logo"><img src="${base}/public/images/logo.jpg"></a>
        </div>
        <!-- end logo -->

        <div class="header-nav fl">
            <ul class="header-nav-list clearfix">
                
                <li class="fl "><a href="javascript:void(0)">首页</a></li>
                <li class="fl "><a href="javascript:void(0)">店铺</a></li>
                <li class="fl "><a href="${base}/goods/goods/selectgoodsclass.action">商品</a></li>
                <li class="fl "><a href="${base}/order/order/browse.action">订单</a></li>
                <li class="fl "><a href="javascript:void(0)">会员</a></li>
                <li class="fl "><a href="javascript:void(0)">分销商</a></li>
                <li class="fl "><a href="javascript:void(0)">财务</a></li>
                <li class="fl "><a href="${base}/mall/message/input.action">营销</a></li>
                <li class="fl "><a href="javascript:void(0)">设置</a></li>                
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
                        <li><a href="javascript:void(0)" target="_blank">更新日志</a></li>
                        <li><a href="javascript:void(0)" target="_blank">帮助中心</a></li>
                        <li><a href="javascript:void(0)">设置</a></li>
                        <li><a href="${base}/author/login/log.action">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- end list -->
		<span class="account_inbox_switch fr"><a href="/System/notice_list" class="header_mail"><span class="act"></span></a></span>
        <span class="header-welcome fr">
        <a href="javasecript:void(0)" class="tips" data-trigger="hover" data-placement="top" data-content="<strong>版本：</strong><font style=&quot;color:red&quot;>免费版</font>">${Session.sys_login_token.sys_login_username}，欢迎回来！</a></span>

        <!-- end header-welcome -->
    </div>
