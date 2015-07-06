
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>发布商品 - 微分销</title>
    <!-- 线上环境 -->
        <link rel="stylesheet" href="http://res.mp.wifenxiao.com/Public/css/dist/component-min.css">    
        <link rel="stylesheet" href="/Public/plugins/jbox/jbox-min.css">
    
    <link rel="stylesheet" href="/Public/plugins/uploadify/uploadify-min.css">
    <link rel="stylesheet" href="http://res.mp.wifenxiao.com/Public/css/dist/home/Item/add_step2.css">
    <style>
        .imgnav{
            max-height: 30px;
            overflow: hidden;
            cursor: pointer;
        }
    </style>
    
 <script src="http://res.mp.wifenxiao.com/Public/js/jquery/jquery-1.8.3.min.js"></script>

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
                
                <li class="fl "><a href="/Shop/home">首页</a></li><li class="fl "><a href="/Shop/list_homepage">店铺</a></li><li class="fl active"><a href="/Item/lists/item_status/onsale">商品</a></li><li class="fl "><a href="/Order/lists">订单</a></li><li class="fl "><a href="/User/lists">会员</a></li><li class="fl "><a href="/User/apply_lists">分销商</a></li><li class="fl "><a href="/Dist/apply_list">财务</a></li><li class="fl "><a href="/Ump/checkin">营销</a></li><li class="fl "><a href="/System/shopInfo">设置</a></li>                
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
    <dl class="left-menu shop_2 sub_commodity">
            <dt>
                <i class="icon-menu commodity"></i>
                <span id="shop_2" data-id="2">商品管理</span>
            </dt>
            <dd class="subshop_0 ">
                    <a href="/Item/add">发布商品</a>
                                    </dd><dd class="subshop_6 ">
                    <a href="/Item/lists/item_status/onsale">出售中的商品</a>
                                    </dd><dd class="subshop_7 ">
                    <a href="/Item/lists/item_status/onsku">仓库中的商品</a>
                    <i class="icon_hot"></i>                </dd><dd class="subshop_8 ">
                    <a href="/Item/lists/item_status/outstock">已售罄的商品</a>
                                    </dd><dd class="subshop_9 ">
                    <a href="/Item/lists/item_status/warn">警戒的商品</a>
                                    </dd><dd class="subshop_12 ">
                    <a href="/Item/category_list">自定义类目</a>
                                    </dd>        </dl><dl class="left-menu shop_3 sub_tags">
            <dt>
                <i class="icon-menu tags"></i>
                <span id="shop_3" data-id="3">分组管理</span>
            </dt>
            <dd class="subshop_0 ">
                    <a href="/Item/list_group">商品分组</a>
                                    </dd><dd class="subshop_5 ">
                    <a href="/Item/list_class">商品分类</a>
                                    </dd>        </dl><dl class="left-menu shop_4 sub_download">
            <dt>
                <i class="icon-menu download"></i>
                <span id="shop_4" data-id="4">批量导入</span>
            </dt>
            <dd class="subshop_0 ">
                    <a href="/Item/import">淘宝商品</a>
                                    </dd><dd class="subshop_1 ">
                    <a href="/Item/import_fromfiles">数据包导入</a>
                                    </dd><dd class="subshop_2 ">
                    <a href="/Item/import_qfx">启分销商品导入</a>
                                    </dd><dd class="subshop_5 ">
                    <a href="/Item/item_import">备份商品导入</a>
                                    </dd>        </dl></div>
<!-- end content-left -->

<div class="content-right fl">
 

    <h1 class="content-right-title">发布商品</h1>
    
    
<input type="hidden" id="j-isSid" value='1'><!-- 是否选择了淘宝类目 -->
<input type="hidden" id="j-hasNorms" value='1'><!-- 淘宝类目下是否有属性 -->
<ul class="wizard">
    <li class="wizard-item complete">
        <dl class="wizard-item-content">
            <dt class="wizard-ic-step">
                <span class="wizard-icstp-num">1</span>
                <span class="wizard-icstp-title">选择商品类目</span>
            </dt>
            <dd class="wizard-ic-desc"></dd>
        </dl>
    </li>
    <li class="wizard-item process">
        <dl class="wizard-item-content">
            <dt class="wizard-ic-step">
                <span class="wizard-icstp-num">2</span>
                <span class="wizard-icstp-title">编辑商品信息</span>
            </dt>
            <dd class="wizard-ic-desc"></dd>
        </dl>
    </li>
    <li class="wizard-item">
        <dl class="wizard-item-content">
            <dt class="wizard-ic-step">
                <span class="wizard-icstp-num">3</span>
                <span class="wizard-icstp-title">编辑商品详情</span>
            </dt>
        </dl>
    </li>
</ul>
<form action="${base}/goods/goods/update.action" method="post" id="editform">
<input type="hidden" name="id" value="${obj.order.id}">
<div class="panel-single panel-single-light mgt20">
    <h3 class="cst_h3 mgb20">基本信息</h3>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>订单号：</label>
        <div class="form-controls">
            <input type="text" class="input xxlarge" name="cno" value="${obj.order.cno}">
            <span class="fi-help-text"></span>
        </div>
    </div>

</div>
<!-- end 基本信息 -->

<div class="panel-single panel-single-light mgt20">
    <h3 class="cst_h3 mgb20">购买人信息</h3>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>购买人：</label>
        <div class="form-controls">
            <input type="text" class="input xxlarge" name="membercname" value="${obj.order.membercname}">
            <span class="fi-help-text"></span>
        </div>
    </div>
    
</div>
<!-- end 购买人信息 -->

<div class="panel-single panel-single-light mgt20">
    <h3 class="cst_h3 mgb20">收货人信息</h3>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>收货人：</label>
        <div class="form-controls">
            <input type="text" class="input xxlarge" name="takercname" value="${obj.order.takercname}">
            <span class="fi-help-text"></span>
        </div>
    </div>

</div>
<!-- end 收货人信息 -->

<div class="panel-single panel-single-light mgt20 j-emptyhide">
    <h3 class="cst_h3 mgb20">支付信息</h3>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>支付状态：</label>
        <div class="form-controls">
            <input type="text" class="input xxlarge" name="paystate" value="${obj.order.paystate}">
            <span class="fi-help-text"></span>
        </div>
    </div>
</div>
<!-- end 库存/规格 -->


</form>

<div class="panel-single panel-single-light mgt20 txtCenter">
    <input type="button" class="btn btn-primary" value="保存" id="bt_submit">
</div>

</div>
<!-- end content-right -->
</div>
</div>
<!-- end container -->

</body>
</html>