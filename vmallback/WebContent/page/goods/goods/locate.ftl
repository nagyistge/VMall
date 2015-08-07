<html>
<title>优品365.商品管理.发布商品</title>
<body>
<link rel="stylesheet" href="${base}/public/plugins/uploadify/uploadify-min.css">
<link rel="stylesheet" href="${base}/page/goods/goods/input.css">
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
<form action="" method="post" id="add_step2">
<input type="hidden" name="id" value="${obj.goods.id}">
<input type="hidden" name="classid" value="${obj.goodsclass.id}">
<input type="hidden" name="jskuval" value="">
<div class="panel-single panel-single-light mgt20">
    <h3 class="cst_h3 mgb20">基本信息</h3>
    <div class="formitems">
        <label class="fi-name">商品类目：</label>
        <div class="form-controls pdt3">${obj.goodsclass.cname}</div>
    </div>
    <div class="formitems">
        <label class="fi-name">
            <span class="colorRed"></span>商品分类：</label>
        <div class="form-controls">
            <a href="javascript:void(0)" class="btn btn-primary" id="j-select_category_id"><i class="gicon-edit white"></i>编辑商品分类</a>
            <span><input type="text" value="" style="border: 1px solid #fff;width: 500px;" id="category_text"></span>
            <input type="hidden" value="" name="class_id">
            <span class="fi-help-text error"></span>
        </div>
    </div>
    <div class="formitems">
        <label class="fi-name">购买方式：</label>
        <div class="form-controls">
            <div class="radio-group">
                <label><input type="radio" class="j-buyway" name="buy_method" value="1" checked>微分销平台</label>
                <label><input type="radio" class="j-buyway" name="buy_method" value="2" >外部链接</label>
            </div>
        </div>
    </div>
    <div class="formitems">
        <label class="fi-name">商品分组：</label>
        <div class="form-controls pdt3">
            
            <div class="droplist droplist-goodsGroupLabelList">
                <a href="javascript:;" class="droplist-title j-droplist-toggle">
                    <span>选择</span>
                    <i class="gicon-chevron-down mgl5"></i>
                </a>
                <div class="droplist-menu">
                    <div class="checkbox-group">
                                                <label><input type="checkbox" class="j-goodsGroupLabel" data-id="2004831" data-title="分组1" value="2004831" name="group_id[]">分组1</label>                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="formitems">
        <label class="fi-name">排序：</label>
        <div class="form-controls">
            <input type="text" class="input mini" name="serial"  value="0">

            <span class="fi-help-text">序号大的排在前面</span>
        </div>
    </div>
</div>
<!-- end 基本信息 -->

<div class="panel-single panel-single-light mgt20">
    <h3 class="cst_h3 mgb20">商品信息</h3>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>商品名：</label>
        <div class="form-controls">
            <input type="text" class="input xxlarge" name="cname" value="${obj.goods.cname}">
            <span class="fi-help-text"></span>
        </div>
    </div>
    
    <!--<div class="formitems">-->
        <!--<label class="fi-name">分销广告语:</label>-->
        <!--<div class="form-controls">-->
            <!--<textarea  class="textarea xxlarge" name="fenxiao_slogan"></textarea>-->
            <!--<span class="fi-help-text"></span>-->
        <!--</div>-->
    <!--</div>-->

    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>原价：</label>
        <div class="form-controls">
            <input type="text" class="input mini" name="original_price"  value="${obj.goods.saleprice}">
            <span>元</span>
            
            <span class="fi-help-text"></span>
        </div>
    </div>

    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>现价：</label>
        <div class="form-controls">
            <input type="text" class="input mini" name="price"  value="0.00">
            <span>元</span>
                        <!--设置分销商等级价格-->
            <span class="setfxs-pic">
                 &nbsp; <a href="javascript:;" class="btn btn-primary J-edit-setfxs bhaide"><i class="gicon-edit white"></i>编辑会员等级价格</a>
                 &nbsp; <a href="javascript:;" class="btn btn-primary J-close-setbox">收起</a>
                <div class="setfxs-box">
                    <table class="wxtables tables-form">
                        <colgroup>
                            <col width="30%">
                            <col width="70%">
                        </colgroup>
                        <tr>
                            <td class="tables-form-title">店铺客户</td>
                            <td>
                                <input type="hidden" class="input mini" name="rank_id[]" value="2016860">元
                                <input type="text" class="input mini" name="rank_price[]" value="">元
                            </td>
                        </tr><tr>
                            <td class="tables-form-title">普通会员</td>
                            <td>
                                <input type="hidden" class="input mini" name="rank_id[]" value="2016861">元
                                <input type="text" class="input mini" name="rank_price[]" value="">元
                            </td>
                        </tr><tr>
                            <td class="tables-form-title">高级会员</td>
                            <td>
                                <input type="hidden" class="input mini" name="rank_id[]" value="2016862">元
                                <input type="text" class="input mini" name="rank_price[]" value="">元
                            </td>
                        </tr><tr>
                            <td class="tables-form-title">VIP会员</td>
                            <td>
                                <input type="hidden" class="input mini" name="rank_id[]" value="2016863">元
                                <input type="text" class="input mini" name="rank_price[]" value="">元
                            </td>
                        </tr><tr>
                            <td class="tables-form-title">至尊VIP会员</td>
                            <td>
                                <input type="hidden" class="input mini" name="rank_id[]" value="2016864">元
                                <input type="text" class="input mini" name="rank_price[]" value="">元
                            </td>
                        </tr>                    </table>
                </div>
            </span>
            <!--end-->
            <span class="fi-help-text"></span>
        </div>
    </div>

    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>总库存：</label>
        <div class="form-controls">
            <input type="text" class="input mini" id="j-totalStock" name="num" value="0">
            <span><input type="text" class="input xmini" name="unit" value="件"></span>
            <span class="fi-help-text"></span>
        </div>
    </div>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>基础销量：</label>
        <div class="form-controls">
            <input type="text" class="input mini"  name="basic_sales" value="0">
            <span><span><input type="text" class="input xmini" name="unit" value="件"></span></span>
            <span class="fi-help-text"></span>
        </div>
    </div>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>基础点赞数：</label>
        <div class="form-controls">
            <input type="text" class="input mini"  name="praise_num" value="0">
            <span class="fi-help-text"></span>
        </div>
    </div>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>重量：</label>
        <div class="form-controls">
            <input type="text" class="input mini"  name="weight" value="">
            <span>千克(最多四位小数)</span>
            <span class="fi-help-text"></span>
        </div>
    </div>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>体积：</label>
        <div class="form-controls">
            <input type="text" class="input mini"  name="volume" value="">
            <span>m³(最多四位小数)</span>
            <span class="fi-help-text"></span>
        </div>
    </div>
    <div class="formitems">
        <label class="fi-name">上传图片：</label>
        <div class="form-controls pdt5 j-imglistPanel">
            <ul class="img-list clearfix">
                <li class="img-list-add j-addimg">+</li>
            </ul>
            <input type="hidden" name="file_path" class="j-imglist-dataset" value="">
            <span class="fi-help-text">建议上传尺寸640px * 640px</span>
        </div>
    </div>

    <div class="formitems wb_buy">
        <label class="fi-name"><span class="colorRed">*</span>外部购买地址：</label>
        <div class="form-controls">
            <input type="text" class="input xxlarge" name="buy_url" value="">
            <span class="fi-help-text"></span>
        </div>
    </div>
    <div class="formitems mgt5">
        <label class="fi-name">商家编码：</label>
        <div class="form-controls">
            <input type="text" class="input j-code-ipt" value="" name="goods_no">
            <span class="fi-help-text"></span>
        </div>
    </div>
</div>
<!-- end 商品信息 -->

<div class="panel-single panel-single-light mgt20">
    <h3 class="cst_h3 mgb20">佣金设置</h3>
    <div class="formitems">
        <label class="fi-name" style="width:160px;">直属上级能拿到的佣金：</label>
        <div class="form-controls">
            <input type="text" class="input mini" name="directly_money"  value="0.00">
            <span>元&nbsp;&nbsp;或&nbsp;&nbsp;</span>
            <input type="text" class="input mini" name="directly_rate"  value="">
            <span>%</span>
            <span class="fi-help-text" style="margin-left:40px;">金额和比例都为0.00或空表示采用<a href="/User/agent_rank" class="colorBlue" target="_blank">分销商等级</a>或<a href="/System/setting" class="colorBlue" target="_blank">系统设置</a>的提成比例计算佣金</span>
        </div>
    </div>

    <div class="formitems">
        <label class="fi-name" style="width:160px;">二级上级能拿到的佣金：</label>
        <div class="form-controls">
            <input type="text" class="input mini" name="superior_money"  value="0.00">
            <span>元&nbsp;&nbsp;或&nbsp;&nbsp;</span>
            <input type="text" class="input mini" name="superior_rate"  value="">
            <span>%</span>
            <span class="fi-help-text" style="margin-left:40px;">金额和比例都为0.00或空表示采用<a href="/User/agent_rank" class="colorBlue" target="_blank">分销商等级</a>或<a href="/System/setting" class="colorBlue" target="_blank">系统设置</a>的提成比例计算佣金</span>
        </div>
    </div>

    <div class="formitems">
        <label class="fi-name" style="width:160px;">三级上级能拿到的佣金：</label>
        <div class="form-controls">
            <input type="text" class="input mini" name="three_money"  value="0.00">
            <span>元&nbsp;&nbsp;或&nbsp;&nbsp;</span>
            <input type="text" class="input mini" name="three_rate"  value="">
            <span>%</span>
            <span class="fi-help-text" style="margin-left:40px;">金额和比例都为0.00或空表示采用<a href="/User/agent_rank" class="colorBlue" target="_blank">分销商等级</a>或<a href="/System/setting" class="colorBlue" target="_blank">系统设置</a>的提成比例计算佣金</span>
        </div>
    </div>
</div>
<!-- end 佣金设置 -->

<div class="panel-single panel-single-light mgt20 j-emptyhide">
    <h3 class="cst_h3 mgb20">库存/规格</h3>
    <div class="formitems">
        <label for="" class="fi-name">sku类型：</label>
        <div class="form-controls normsPanel">
            <label for="sku1"><input type="radio" name="sku_style" value="0" id="sku1" class="j-skustyle" style="vertical-align:middle" checked><span>文字</span></label>
            <label for="sku2"><input type="radio" name="sku_style" value="1" id="sku2" class="j-skustyle" style="vertical-align:middle" ><span>图片</span></label>
        </div>
    </div>
    <div class="formitems">
        <label class="fi-name">商品规格：</label>
        <div class="form-controls normsPanel">
   		        <#list obj.goodsclassspecs as aspec>
    		    <h3 class="form-controls-h3"></h3>
                <div class="form-controls-sku">
                   <dl class="j-selectProps">
                        <dt class="j-selectAllProps">
                        <label><input type="checkbox" class="j-normsid" value="${aspec.id}" data-name="${aspec.specclass}">${aspec.specclass}</label>
                        </dt>
                        <dd>
						<#list aspec.specvalues as avalue>
                            <label>
                            <input class="j-propid" type="checkbox" value="${avalue.id}" >
                            <span class="J-alias" data-v="${avalue.id}" data-n="${avalue.cvalue}">${avalue.cvalue}</span>
                            </label>
                        </#list>
                        </dd>    
                   </dl>
                </div>  		        	
   		        </#list>
   		        
                <input type="hidden" id="j-normsVal" name="norms" value=''>
	            <input type="hidden" id="j-propsVal" name="props" value=''>
	            <input type="hidden" id="j-propsAlias" name="alias" value=''>

        </div>
    </div>



    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>商品库存：</label>
        <div class="form-controls" id="j-skuPanel">
            <input type="hidden" id="j-skuVal" name="sku_props" value=''>
        </div>
    </div>
</div>
<!-- end 库存/规格 -->



<div class="panel-single panel-single-light mgt20 j-emptyhide">
    <h3 class="cst_h3 mgb20">（新测试）库存/规格</h3>
    <div class="formitems">
        <label for="" class="fi-name">sku类型：</label>
        <div class="form-controls normsPanel">
            <label for="sku1"><input type="radio" name="sku_style" value="0" id="sku1" class="j-skustyle" style="vertical-align:middle" checked><span>文字</span></label>
            <label for="sku2"><input type="radio" name="sku_style" value="1" id="sku2" class="j-skustyle" style="vertical-align:middle" ><span>图片</span></label>
        </div>
    </div>
    <div class="formitems">
        <label class="fi-name">商品规格：</label>
        <div class="form-controls normsPanel">
		<a class="addspec" href="javascript:void(0)" style="color:#5e5eff">添加规格/型号</a>
        </div>
        
        <div class="form-controls normsPanel" id="div_specvalues">
		
        </div>
    </div>
</div>


<div class="panel-single panel-single-light mgt20 j-showinhyd">
    <h3 class="cst_h3 mgb20">物流及其它</h3>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>免物流：</label>
        <div class="form-controls">
            <div class="radio-group">
                <label><input type="radio" class="is_free_logistics" value="1" name="is_free_logistics" >是</label>
                <label><input type="radio" class="is_free_logistics" value="0" name="is_free_logistics" checked>否</label>
            </div>
            <span class="fi-help-text">会员购买免物流商品时，不需要填写收货地址。</span>
        </div>
    </div>
    <div class="formitems user_show">
        <label class="fi-name"><span class="colorRed">*</span>显示用户信息：</label>
        <div class="form-controls">
            <div class="radio-group">
                <label><input type="radio" class="free_logistics_type" value="1" name="free_logistics_type" >是</label>
                <label><input type="radio" class="free_logistics_type" value="0" name="free_logistics_type" checked>否</label>
            </div>
            <span class="fi-help-text"></span>
        </div>
    </div>
    <div class="formitems show">
        <label class="fi-name"><span class="colorRed">*</span>运费设置：</label>
        <div class="form-controls">
            <div class="radio-group">
                <label><input type="radio" class="j-feight" value="1" name="freight_payer" checked>包邮</label>
                <label><input type="radio" class="j-feight" value="2" name="freight_payer" >统一运费</label>
            </div>
                        <input type="text" class="input xmini j-feight-ipt" name="post_fee" value="10" disabled>            <span style="margin-right: 20px;">元</span>
            <div class="radio-group">
                <label><input type="radio" class="j-feight" value="3" name="freight_payer" >运费模板</label>
            </div>
            <select name="freight_tpl_id" class="select small" id="freight_tpl_id" disabled>
                <option value="">请选择</option>
                            </select>
            <a href="/System/add_freightTpl.html" class="colorBlue">立即添加</a>            <span class="fi-help-text"></span>
        </div>
    </div>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed"></span>满件包邮：</label>
        <div class="form-controls">
                            <input type="text" class="input mini" name="full_num_mail" value="0">件
                <span class="fi-help-text">0代表不设置</span>        </div>
    </div>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>库存显示：</label>
        <div class="form-controls">
            <div class="radio-group">
                <label><input type="radio" class="j-feight" value="1" name="hide_stock" checked>是</label>
                <label><input type="radio" class="j-feight" value="0" name="hide_stock" >否</label>
            </div>
            <span class="fi-help-text"></span>
        </div>
    </div>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>每人限购：</label>
        <div class="form-controls">
                        <input type="text" class="input mini" name="quota" value="0">
            <span class="fi-help-text">0代表不限购</span>        </div>
    </div>
    <div class="formitems">
        <label class="fi-name">购买所需积分：</label>
        <div class="form-controls">
                            <input type="text" class="input mini" name="buy_need_points" value="">            <span class="fi-help-text">购买每件该商品额外需要的积分</span>
        </div>
    </div>
    <div class="formitems">
        <label class="fi-name">消费送优惠券：</label>
        <div class="form-controls">
                            <select name="consum_coupon" class="select">
                    <option value="0">请选择</option>
                                    </select>            <span class="fi-help-text"></span>
        </div>
    </div>
    <div class="formitems">
        <label class="fi-name">消费送积分：</label>
        <div class="form-controls">
                            <input type="text" class="input mini" name="consum_point" value="">            <span class="fi-help-text"></span>
        </div>
    </div>
    <div class="formitems inline">
        <label class="fi-name"><span class="colorRed">*</span>会员等级折扣：</label>
        <div class="form-controls">
            <div class="radio-group">
                <label><input type="radio" value="1" name="join_level_discount" checked>开启</label>
                <label><input type="radio" value="0" name="join_level_discount" >关闭</label>
            </div>
            <span class="fi-help-text"></span>
        </div>
    </div>
</div>
<!-- end 物流及其它 -->


</form>

<div class="panel-single panel-single-light mgt20 txtCenter">
    <a href="" class="btn">上一步</a>
    <input type="button" class="btn btn-primary" value="下一步" id="bt_submit">
</div>


    <script type="text/j-template" id="tpl_add_step2_goodsGroupLabelList_item">
        <% if(dataset.length){ %>
        <ul class="labelList j-goodsGroupLabelList clearfix">
            <% _.each(dataset,function(item){ %>
            <% if(item.isCheck){ %>
            <li><span><%= item.title %></span><i class="gicon-remove white j-del" data-id="<%= item.id %>"></i></li>
            <% } %>
            <% }) %>
        </ul>
        <% } %>
    </script>
    <!-- end tpl_add_step2_goodsGroupLabelList_item -->

    <script type="text/j-template" id="tpl_add_step2_imgList">
        <ul class="img-list clearfix">
            <% _.each(dataset,function(item,index){ %>
            <li data-index="<%=index%>">
                <span class="img-move img-move-left"></span>
                <span class="img-move img-move-right"></span>
                <span class="img-list-btndel j-delimg"><i class="gicon-trash white"></i></span>
                <span class="img-list-overlay"></span>
                <img src="<%= item %>">
            </li>
            <% }) %>
            <li class="img-list-add j-addimg">+</li>
        </ul>
        <input type="hidden" name="file_path" class="j-imglist-dataset" value="<%= str_dataset %>">
        <span class="fi-help-text">建议上传尺寸640px * 640px</span>
    </script>
    <!-- end tpl_add_step2_imgList -->

    <script type="text/j-template" id="tpl_add_step2_normslist">
        <% _.each(dataset,function(item,index){%>
        <dl class="normslist" data-index="<%= index %>" data-id="<%= item.id %>">
            <dt class="clearfix">
                <input type="text" class="fl input mini j-normsName" value="<%= item.name %>" maxlength="4">
                <span class="fi-help-text"></span>
                <a href="javascript:;" class="fr j-delNorms" title="移除"><i class="gicon-trash"></i>移除</a>
            </dt>
            <dd class="clearfix skuitemPanel">
                <% if(item.props.length){ %>
                <ul class="labelList labelList-sku clearfix fl">
                    <% _.each(item.props,function(val,index){ %>
                    <li><span><%= props[val] %></span><i class="gicon-remove white j-delNormsVal" data-index="<%= index %>"></i></li>
                    <% }) %>
                </ul>
                <% } %>
                <input type="text" class="input mini fl j-addNormsVal-ipt">
                <a href="javascript:;" class="btn btn-primary btn-small j-addNormsVal">添加</a>
            </dd>
        </dl>
        <% }) %>
    </script>
    <!-- end tpl_add_step2_normslist -->

    <script type="text/j-template" id="tpl_add_step2_sku">
        <table class="wxtables wxtables-sku">
            <thead>
            <tr>
                <% _.each(norms,function(item,index){ %>
                    <td><%= item.name %></td>
                <% }) %>
                <td>原价</td>
                <td>现价</td>
                <td>库存</td>
                <td>商品编码</td>
                <td>销量</td>
                <td>设置会员价</td>
            </tr>
            </thead>
            <tbody>
            <% for(var key in sku){ %>
            <tr data-key="<%= key %>" data-skuId="<%= sku[key].skuId %>">
                <% if(skustyle.skustyle == 0 || skustyle.skustyle ==undefined){ %>
                    <% _.each(sku[key].props,function(id,item){ %>
                        <td><%= props[id] %></td>
                    <% }) %>
                <% }else{ %>
                    <% _.each(sku[key].props,function(id,item){ %>
                        <% if(item == 0){ %>
                            <td colspan="<%=sku[key].props.length%>">
                                <div class="imgnav j-title-selectimg">
                                    <img class="j-view_pic-ipt" style="display:block;cursor:pointer;margin:0 auto;" src="<% if(sku[key].sku_img){ %><%= sku[key].sku_img%><% }else{ %>/Public/images/diy/waitupload.png<% } %>"  width="30">
                                </div>
                            </td>
                        <% } %>
                    <% }) %>
                <% } %>
               
                <td>
                    <input type="text" class="input mini j-price-modify" data-name="o_price" value="<%= sku[key].o_price %>">元<br/>
                                    </td>
                <td>
                    <input type="text" class="input mini j-price-modify" data-name="price" value="<%= sku[key].price %>">元<br/>
                                    </td>
                <td><input type="text" class="input mini j-price-modify" data-name="stock" value="<%= sku[key].stock %>"></td>
                <td><input type="text" class="input mini j-price-modify" data-name="code" value="<%= sku[key].code %>"></td>
                <td><%= sku[key].salenum %></td>
                <td>
                    <span class="setfxs-pic options">
                         &nbsp; <a href="javascript:;" class="btn J-options-slideToggle">设置</a>
                        <div class="setfxs-box">
                            <table class="wxtables tables-form">
                                <colgroup>
                                    <col width="30%">
                                    <col width="70%">
                                </colgroup>
                                <tr>
                                        <td class="tables-form-title">店铺客户</td>
                                        <td>
                                            <input type="text" class="input mini" name="sku_rank_price[<%= sku[key].rank_props %>][2016860]" value="<%= sku[key].rank_price[2016860] %>">元
                                        </td>
                                    </tr><tr>
                                        <td class="tables-form-title">普通会员</td>
                                        <td>
                                            <input type="text" class="input mini" name="sku_rank_price[<%= sku[key].rank_props %>][2016861]" value="<%= sku[key].rank_price[2016861] %>">元
                                        </td>
                                    </tr><tr>
                                        <td class="tables-form-title">高级会员</td>
                                        <td>
                                            <input type="text" class="input mini" name="sku_rank_price[<%= sku[key].rank_props %>][2016862]" value="<%= sku[key].rank_price[2016862] %>">元
                                        </td>
                                    </tr><tr>
                                        <td class="tables-form-title">VIP会员</td>
                                        <td>
                                            <input type="text" class="input mini" name="sku_rank_price[<%= sku[key].rank_props %>][2016863]" value="<%= sku[key].rank_price[2016863] %>">元
                                        </td>
                                    </tr><tr>
                                        <td class="tables-form-title">至尊VIP会员</td>
                                        <td>
                                            <input type="text" class="input mini" name="sku_rank_price[<%= sku[key].rank_props %>][2016864]" value="<%= sku[key].rank_price[2016864] %>">元
                                        </td>
                                    </tr>                            </table>
                        </div>
                    </span>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </script>
    <!-- end tpl_add_step2_sku -->

    <script type="text/j-template" id="tpl_item_class">
        <div class="formitems">
            <label class="fi-name"><span class="colorRed"></span>选择分类：</label>
            <div class="form-controls">
                <select name="class_id" class="select">
                    <option value="2982" >A01</option><option value="2983" >&nbsp;&nbsp;└─A0101</option><option value="2985" >&nbsp;&nbsp;└─A0102</option><option value="2986" >A02</option><option value="2988" >&nbsp;&nbsp;└─A0201</option>                </select>
                <span class="fi-help-text"></span>
            </div>
        </div>
    </script>
    <!-- end tpl_add_step2_sku -->
    
    <script type="text/j-template" id="tpl_item_specvalue">
	<% _.each(specvalues,function(aspec,index){ %>
    <h3 class="form-controls-h3"></h3>
    <div class="form-controls-sku">
       <dl class="j-selectProps">
            <dt class="j-selectAllProps">
            <label><input type="checkbox" class="j-normsid" value="<%=aspec.specclass%>" data-name="<%=aspec.specclass%>"><%=aspec.specclass%></label>
            </dt>
            <dd>
            <% _.each(aspec.specvalues,function(avalue,index_value){ %>
                <label>
                <input class="j-propid" type="checkbox" value="<%=avalue.cvalue%>" >
                <span class="J-alias" data-v="<%=avalue.cvalue%>" data-n="<%=avalue.cvalue%>"><%=avalue.cvalue%></span>
                </label>
            <% }) %>
            <a class="addspecvalue" href="javascript:void(0)" style="color:#5e5eff" specclass="<%=aspec.specclass%>">添加型号</a>
            </dd>    
       </dl>
    </div>  		        	
    <% }) %>
    
    </script>


<!--end front template  -->


<script src="${base}/public/js/dist/lib-min.js"></script>
<script src="${base}/public/plugins/jbox/jquery.jbox-min.js"></script>
<script src="${base}/public/plugins/zclip/jquery.zclip-min.js"></script>
<script src="${base}/public/plugins/uploadify/jquery.uploadify.min.js"></script>

<!--[if lt IE 10]>
<script src="${base}/public/js/jquery/jquery.placeholder-min.js"></script>
<script>
    $(function(){
        //修复IE下的placeholder
        $('.input,.textarea').placeholder();
    });
</script>
<![endif]-->

<script>
$(function(){
	$("#leftMenu").load('${base}/page/goods/leftmenu.ftl');
});
</script>

<script>
var specvalues;
var current_specclass;
</script>

<!-- 添加规格 -->
<script type="text/j-template" id="tpl_add_spec">
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>规格：</label>
        <div class="form-controls">
            <input type="text"  class="input" name="specclass">
            <span class="fi-help-text"></span>
        </div>
    </div>
</script>

<!-- 添加型号 -->
<script type="text/j-template" id="tpl_add_specvalue">
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>规格：</label>
        <div class="form-controls">
            <input type="text"  class="input" name="specclass" value="<%=current_specclass%>" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>型号：</label>
        <div class="form-controls">
            <input type="text"  class="input" name="spec">
            <span class="fi-help-text"></span>
        </div>
    </div>
</script>    

<script>
$(function(){
    $('.bhaide').on('click',function() {
        $(this).parent().children('.setfxs-box').slideDown();
        $(this).parent().children('.J-close-setbox').attr('style','opacity:1');
    });
    $('.J-close-setbox').on('click',function() {
        $(this).parent().children('.setfxs-box').slideUp();
        $(this).attr('style','opacity:0');
    });
    // 商品图片排序
    // 图片左移
    $(document).on('click','.img-list>li .img-move-left',function(){
        var me = $(this);
        var curindex = me.closest('li').index();
        var html = me.closest('li');
        var str = '';
        if(curindex!=0){
            curindex--;
            me.closest('ul').find('li').eq(curindex).before(html);
            me.closest('ul').find('li').not('.img-list-add').each(function(index, el) {
                var imgsrc=$(this).children('img').attr('src');
                str += imgsrc + ','
            });
            str = str.substring(0,str.length-1)
            $('.j-imglist-dataset[name=file_path]').val(str);
        };
    });
    // 图片右移
    $(document).on('click','.img-list>li .img-move-right',function(){
        var me = $(this),
            len = me.closest('ul').find('li').length-1;
        var curindex = me.closest('li').index();
        var str = '';
        var html = me.closest('li');
        if(curindex!=(len-1)){
            curindex++;
            me.closest('ul').find('li').eq(curindex).after(html);
            me.closest('ul').find('li').not('.img-list-add').each(function(index, el) {
                var imgsrc=$(this).children('img').attr('src');
                str += imgsrc + ','
            });
            str = str.substring(0,str.length-1)
            $('.j-imglist-dataset[name=file_path]').val(str);
        };
    })
});
</script>


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

<script src="${base}/page/goods/goods/locate.js"></script>
</body>
</html>