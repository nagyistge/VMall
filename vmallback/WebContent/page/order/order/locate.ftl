<html>
<head>
<title>优品365.订单管理.订单查询</title>
</head>
<body>
<h1 class="content-right-title">订单查看</h1>

<div class="tabs clearfix mgt15" id="tabs">
	<a href="javascript:void(0)" class="tabs_a fl active" tab="dd" id="tab_dd">订单</a>
	<a href="javascript:void(0)" class="tabs_a fl" tab="sp" id="tab_sp">商品</a>
	<a href="javascript:void(0)" class="tabs_a fl" tab="lc" id="tab_lc">流程</a>
</div>


<div id="chooseTab-content">

<div id="div_dd">

<div style="text-align:left;padding-left:2px;padding-top:10px;padding-bottom:10px;">
<#if obj.issave==true><button id="bt_save" class="btn btn-primary">保存</button></#if>
<#if obj.isforward==true><button id="bt_forward" class="btn btn-primary">转${obj.flownextstate}</button></#if>
<#if obj.isbackward==true><button id="bt_backward" class="btn btn-primary">退回到${obj.flowbackstate}</button></#if>
</div>
	
<form action="${base}/goods/goods/update.action" method="post" id="editform">
<input type="hidden" name="id" value="${obj.order.id}">
<div class="panel-single panel-single-light mgt20">
    <h3 class="cst_h3 mgb20">基本信息</h3>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>订单号：</label>
        <div class="form-controls">
            <input type="text" class="input" name="cno" value="${obj.order.cno}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>订单时间：</label>
        <div class="form-controls">
            <input type="text" class="input" name="ordertime" value="${obj.order.ordertime!?datetime?string('yyyy-MM-dd HH:mm')}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>
    
     <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>订单总额：</label>
        <div class="form-controls">
            <input type="text" class="input mini" name="amount" value="${obj.order.amount!?string?default("0")}" readonly>
            <span>元</span>
            <span class="fi-help-text"></span>
        </div>
    </div>     
       
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>批次号：</label>
        <div class="form-controls">
            <input type="text" class="input" name="batchno" value="${obj.order.batchno}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>业务状态：</label>
        <div class="form-controls">
            <input type="text" class="input" name="state" value="${obj.order.state}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>
    
</div>
<!-- end 基本信息 -->

<div class="panel-single panel-single-light mgt20">
    <h3 class="cst_h3 mgb20">购买信息</h3>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>购买会员编号：</label>
        <div class="form-controls">
            <input type="text" class="input" name="membercno" value="${obj.order.membercno}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>

    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>购买会员姓名：</label>
        <div class="form-controls">
            <input type="text" class="input" name="membercname" value="${obj.order.membercname}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>

    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>联系电话：</label>
        <div class="form-controls">
            <input type="text" class="input" name="phone" value="${obj.order.phone}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>商品厂家：</label>
        <div class="form-controls">
            <input type="text" class="input" name="sellername" value="${obj.order.sellername}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>
            
</div>
<!-- end 购买人信息 -->

<div class="panel-single panel-single-light mgt20 j-emptyhide">
    <h3 class="cst_h3 mgb20">支付信息</h3>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>支付状态：</label>
        <div class="form-controls">
            <input type="text" class="input" name="paystate" value="${obj.order.paystate}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>收款通知时间：</label>
        <div class="form-controls">
            <input type="text" class="input" name="paynotifytime" value="<#if obj.order.paynotifytime!=''>${obj.order.paynotifytime!?datetime?string('yyyy-MM-dd HH:mm')}</#if>" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>微信平台交易号：</label>
        <div class="form-controls">
            <input type="text" class="input large" name="thirdpaytradeno" value="${obj.order.thirdpaytradeno}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>微信交易类型：</label>
        <div class="form-controls">
            <input type="text" class="input" name="wxpaytradetype" value="${obj.order.wxpaytradetype}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>         
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>支付银行类型：</label>
        <div class="form-controls">
            <input type="text" class="input" name="wxpaybanktype" value="${obj.order.wxpaybanktype}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>实际支付金额：</label>
        <div class="form-controls">
            <input type="text" class="input mini" name="wxpaytotalfee" value="<#if obj.order.wxpaytotalfee!=''>${obj.order.wxpaytotalfee!?number/100}</#if>" readonly>
            <span>元</span>
            <span class="fi-help-text"></span>
        </div>
    </div>  
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>是否关注：</label>
        <div class="form-controls">
            <input type="text" class="input mini" name="wxpayissubscribe" value="${obj.order.wxpayissubscribe}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div> 
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>交易结束时间：</label>
        <div class="form-controls">
            <#-- 
            <input type="text" class="input" name="wxpaytimeend" value="<#if obj.order.wxpaytimeend!=''>${obj.order.wxpaytimeend!?datetime?string('yyyy-MM-dd HH:mm')}</#if>" readonly>
            -->
           <input type="text" class="input" name="wxpaytimeend" value="${obj.order.wxpaytimeend}" readonly>
            
            <span class="fi-help-text"></span>
        </div>
    </div>             
</div>
<!-- end 支付信息 -->

<div class="panel-single panel-single-light mgt20">
    <h3 class="cst_h3 mgb20">发货信息</h3>
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>免物流：</label>
        <div class="form-controls">
            <div class="radio-group">
                <label><input type="radio" class="j-feight" value="是" name="nofreight" <#if obj.order.nofreight=="是">checked</#if>>是</label>
                <label><input type="radio" class="j-feight" value="否" name="nofreight" <#if obj.order.nofreight=="否">checked</#if>>否</label>
            </div>
            <span class="fi-help-text"></span>
        </div>
    </div> 
        
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>运费：</label>
        <div class="form-controls">
            <div class="radio-group">
                <label><input type="radio" class="j-feight" value="包邮" name="freighttype" <#if obj.order.freighttype=="包邮">checked</#if>>包邮</label>
                <label><input type="radio" class="j-feight" value="自定义" name="freighttype" <#if obj.order.freighttype=="自定义">checked</#if>>自定义</label>
            </div>
            <input type="text" class="input mini" name="freight" value="${obj.order.freight}">（元）
            <#if obj.issavefreight??==true><button id="bt_savefreight" type="button" class="btn btn-primary">修改</button></#if>
            <span class="fi-help-text">以上内容用户订单付款前可修改</span>
        </div>
    </div>
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>物流公司：</label>
        <div class="form-controls">

            <input type="text" class="input" name="logisticscomp" value="${obj.order.logisticscomp}">
        	<select name="logisticscompselect" class="select">
                    <option value="其它">其它</option>
                    <option value="顺丰">顺丰</option>
                    <option value="申通">申通</option>
                    <option value="圆通">圆通</option>
            </select>            
            <span class="fi-help-text"></span>
        </div>
    </div>  
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>快递单号：</label>
        <div class="form-controls">
            <input type="text" class="input" name="expressno" value="${obj.order.expressno}">
            <#if obj.issavelogistics??==true><button id="bt_savelogistics" type="button" class="btn btn-primary">修改</button></#if>
            <span class="fi-help-text">以上内容在物流发货后由订单发货员负责填写</span>
        </div>
    </div>      
</div>    
<!-- end 发货信息 -->

<div class="panel-single panel-single-light mgt20">
    <h3 class="cst_h3 mgb20">收货信息</h3>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>收货人：</label>
        <div class="form-controls">
            <input type="text" class="input" name="takercname" value="${obj.order.takercname}">
            <span class="fi-help-text"></span>
        </div>
    </div>

    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>联系电话：</label>
        <div class="form-controls">
            <input type="text" class="input" name="takermobile" value="${obj.order.takermobile}">
            <span class="fi-help-text"></span>
        </div>
    </div>
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>收货地址：</label>
        <div class="form-controls">
            <input type="text" class="input" name="takeaddress" value="${obj.order.takeaddress}">
            <span class="fi-help-text"></span>
        </div>
    </div>
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>邮政编码：</label>
        <div class="form-controls">
            <input type="text" class="input" name="takepostcode" value="${obj.order.takepostcode}">
            <span class="fi-help-text"></span>
        </div>
    </div>
    
   	<div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>签收状态：</label>
        <div class="form-controls">
            <input type="text" class="input" name="takeover" value="${obj.order.takeover}">
            <span class="fi-help-text"></span>
        </div>
    </div>     
 
   	<div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>原因说明：</label>
        <div class="form-controls">
            <input type="text" class="input" name="takeoverreason" value="${obj.order.takeoverreason}">
            <span class="fi-help-text"></span>
        </div>
    </div>      
    <#--
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>省份：</label>
        <div class="form-controls">
            <input type="text" class="input" name="takeprovince" value="${obj.order.takeprovince}">
            <span class="fi-help-text"></span>
        </div>
    </div>
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>地市：</label>
        <div class="form-controls">
            <input type="text" class="input" name="takecity" value="${obj.order.takecity}">
            <span class="fi-help-text"></span>
        </div>
    </div>    

    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>县（街道）：</label>
        <div class="form-controls">
            <input type="text" class="input" name="takecounty" value="${obj.order.takecounty}">
            <span class="fi-help-text"></span>
        </div>
    </div>
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>镇：</label>
        <div class="form-controls">
            <input type="text" class="input" name="taketown" value="${obj.order.taketown}">
            <span class="fi-help-text"></span>
        </div>
    </div>
    -->
</div>
<!-- end 收货人信息 -->

</form>

</div>
<!-- end 订单标签页 -->

<div id="div_sp" style="display:none">

    <table class="wxtables table-order mgt20" id="tb_ordergoods">
        <colgroup>
	    <col width="40%">
        <col width="10%">
        <col width="10%">
        <col width="10%">
        <col width="10%">
        <col width="10%">
        </colgroup>
        <thead>
            <tr>
            	<td>商品名称</td>
                <td>数量</td>            	
                <td>现价（元）</td>
                <td>金额（元）</td>
                <td>签收状态</td>
                <td>原因</td>
            </tr>
        </thead>
        <tbody>

        </tbody>
        
    </table>

</div>
<!-- end 商品标签页 -->

<div id="div_lc" style="display:none">

    <table class="wxtables table-order mgt20" id="tb_flowtrace">
        <colgroup>
	    <col width="25%">
        <col width="25%">
        <col width="25%">
        <col width="25%">
        </colgroup>
        <thead>
            <tr>
            	<td>时间</td>
                <td>用户</td>            	
                <td>业务</td>
                <td>操作</td>
            </tr>
        </thead>
        <tbody>

        </tbody>
        
    </table>

</div>
<!-- end 流程标签页 -->

</div>
<!-- end 标签页主体页面 -->



<script src="${base}/public/js/dist/lib-min.js"></script>
<script src="${base}/public/plugins/jbox/jquery.jbox-min.js"></script>
<script src="${base}/public/plugins/zclip/jquery.zclip-min.js"></script>
<script src="${base}/public/plugins/uploadify/jquery.uploadify.min.js"></script>

<script src="${base}/public/js/dist/component-min.js"></script>



<!--[if lt IE 10]>
<script src="${base}/public/js/jquery/jquery.placeholder-min.js"></script>
<script>
    $(function(){
        //修复IE下的placeholder
        $('.input,.textarea').placeholder();
    });
</script>
<![endif]-->

<!-- 模板脚本 -->
<script type="text/j-template" id="tpl_ordergoods">
<%_.each(dataset, function(item) {%> 
<tr>
	<td><%=item.goodsname%></td>
    <td><%=item.nums%></td>            	
    <td><%=item.realprice%></td>
    <td><%=item.amountreal%></td>
    <td><%=item.takeover%></td>
    <td><%=item.takeoverreason%></td>    
</tr>
<%});%>
</script>

<script type="text/j-template" id="tpl_flowtrace">
<%_.each(flowtraces, function(item) {%> 
<tr>
	<td><%=item.createtime%></td>
    <td><%=item.susername%></td>            	
    <td><%=item.sname%></td> 
    <td><%=item.ctype%></td>
</tr>
<%});%>
</script>

<script>

var flowtraces; //流程跟踪模板数据

$(function(){
//////////



$("#leftMenu").load('${base}/order/order/leftmenu.action');

$("#tabs a").click(function() {
	var oid = $(this).attr('tab');
	$("#tabs a").removeClass("active");
	$(this).addClass("active");
	
	$('#div_' + oid).show().siblings().hide();
})

$("#bt_forward").click(function() {page_forward()});
$("#tab_dd").click(function() {page_do_dd()});
$("#tab_sp").click(function() {page_do_sp()});
$("#tab_lc").click(function() {page_do_lc()});

$(".j-feight[name='nofreight']").click(function(){page_nofreight()});
$(".j-feight[name='freighttype']").click(function(){page_freighttype()});
$(".select[name='logisticscompselect']").change(function(){page_logisticscompselect()});
$("#bt_savefreight").click(function(){page_savefreight()});
$("#bt_savelogistics").click(function(){page_savelogistics()});
var page_do_dd = function()
{
	console.log("page_do_dd");
};

var page_do_sp = function()
{
	console.log("page_do_sp");
	
	var orderid = '${obj.order.id}';
	
	$.ajax({
		type:'POST',
		url:'${base}/order/ordergoods/ajaxlist.action',
		data:{"orderid":orderid},
		cache:false,
		async:true,
		success:function(data)
		{
			dataset = eval("("+data+")");
			$("#tb_ordergoods tbody").html(_.template($("#tpl_ordergoods").html(), dataset)); 
		},
		error:function(data)
		{
			console.log(data);
			alert("抱歉，服务请求异常，可能网络有什么问题，稍后再试试看吧！");
		}
	})	
	
};

var page_do_lc = function()
{
	console.log("page_do_lc");
	
	var orderid = '${obj.order.id}';
	
	$.ajax({
		type:'POST',
		url:'${base}/flow/flow/ajaxtrace.action',
		data:{"dataid":orderid,"flowname":"订单"},
		cache:false,
		async:true,
		success:function(data)
		{
			flowtraces = eval("("+data+")");
			console.log(flowtraces);
			$("#tb_flowtrace tbody").html(_.template($("#tpl_flowtrace").html(), flowtraces)); 
		},
		error:function(data)
		{
			console.log(data);
			alert("抱歉，服务请求异常，可能网络有什么问题，稍后再试试看吧！");
		}
	})		
};

function page_forward()
{
	$.ajax({
		type:'post',
		url:'${base}/order/order/forward.action',
		data:{id:'${obj.order.id}'},
		cache:false,
		async:true,
		success:function(data)
		{
			console.log(data);
			if(data=="")
			{
				pub_alert("转发异常！");
				return;
			}
			var json = eval("(" + data + ")");
			if(json.state=="success")
			{
				pub_alert("转发成功！");
				window.location = "${base}/order/order/locate.action?id=${obj.order.id}";
			}
			else
			{
				pub_alert(json.message);
			}
		},
		error:function(data)
		{
			console.log(data);
			pub_alert("服务请求异常！");
		}
	})
}

function page_nofreight()
{
	var e = event.target;
	console.log($(".j-feight[name='freight']"));
	if("是"==e.value)
	{
		$(".j-feight[name='freighttype']").each(function() {$(this).removeAttr("checked")});
		$(".j-feight[name='freighttype']").each(function() {$(this).prop("disabled", true)});
		$(".j-feight[name='freighttype'][value='包邮']").prop("checked", true);
		$("input[name='freight']").val("0");
		$("input[name='freight']").prop("readonly", true);
	}
	else
	{
		$(".j-feight[name='freighttype']").each(function() {$(this).removeAttr("disabled")});
		$(".j-feight[name='freighttype'][value='包邮']").prop("checked", true);
		$("input[name='freight']").removeAttr("readonly");	
	}
}

function page_freighttype()
{
	var e = event.target;
	if("包邮"==e.value)
	{
		$("input[name='freight']").val("0");
		$("input[name='freight']").prop("readonly", true);
	}
	else
	{
		$("input[name='freight']").removeAttr("readonly");	
	}
}
	
function page_logisticscompselect()
{
	var e = event.target;
	console.log(e.options[e.options.selectedIndex].value);
	$("input[name='logisticscomp']").val(e.options[e.options.selectedIndex].value);
}	

function page_savefreight()
{
	var nofreight = $(".j-feight[name='nofreight']:checked").val();
	var freighttype = $(".j-feight[name='freighttype']:checked").val();
	var freight = Number($(".input[name='freight']").val());
	console.log(nofreight);
	console.log(freighttype);
	console.log(freight);

	$.ajax({
		type:'post',
		url:'${base}/order/order/savefreight.action',
		data:{'id':'${obj.order.id}', 'nofreight':nofreight, 'freighttype':freighttype, 'freight':freight},
		cache:false,
		async:true,
		success:function(data)
		{
			console.log(data);
			if(data=="")
			{
				alert("保存异常！");
				return;
			}
			var json = eval("(" + data + ")");
			if(json.state=="success")
			{
				alert("保存成功！");
				// window.location = "${base}/order/order/locate.action?id=${obj.order.id}";
			}
			else
			{
				alert(json.message);
			}
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})	
}

function page_savelogistics()
{
	var logisticscomp = $(".input[name='logisticscomp']").val();
	var expressno = $(".input[name='expressno']").val();

	$.ajax({
		type:'post',
		url:'${base}/order/order/savelogistics.action',
		data:{'id':'${obj.order.id}', 'logisticscomp':logisticscomp, 'expressno':expressno},
		cache:false,
		async:true,
		success:function(data)
		{
			console.log(data);
			if(data=="")
			{
				alert("保存异常！");
				return;
			}
			var json = eval("(" + data + ")");
			if(json.state=="success")
			{
				alert("保存成功！");
				// window.location = "${base}/order/order/locate.action?id=${obj.order.id}";
			}
			else
			{
				alert(json.message);
			}
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})	
}
	
//////////	
});



</script>

</body>
</html>