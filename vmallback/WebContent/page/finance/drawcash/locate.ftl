<html>
<head>
<title>优品365.订单管理.提现查询</title>
</head>
<body>
<h1 class="content-right-title">提现查看</h1>

<div class="tabs clearfix mgt15" id="tabs">
	<a href="javascript:void(0)" class="tabs_a fl active" tab="dd" id="tab_dd">提现</a>
	<a href="javascript:void(0)" class="tabs_a fl" tab="sp" id="tab_sp">订单</a>
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
<input type="hidden" name="id" value="${obj.drawcash.id}">
<div class="panel-single panel-single-light mgt20">
    <h3 class="cst_h3 mgb20">基本信息</h3>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>提现单号：</label>
        <div class="form-controls">
            <input type="text" class="input" name="cno" value="${obj.drawcash.cno}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>申请时间：</label>
        <div class="form-controls">
            <input type="text" class="input" name="applytime" value="${obj.drawcash.applytime!?datetime?string('yyyy-MM-dd HH:mm')}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>
    
     <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>提现总额：</label>
        <div class="form-controls">
            <input type="text" class="input mini" name="amount" value="${obj.drawcash.amount!?string?default("0")}" readonly>
            <span>元</span>
            <span class="fi-help-text"></span>
        </div>
    </div>     
       
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>业务状态：</label>
        <div class="form-controls">
            <input type="text" class="input" name="state" value="${obj.drawcash.state}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>
    
</div>
<!-- end 基本信息 -->

<div class="panel-single panel-single-light mgt20">
    <h3 class="cst_h3 mgb20">提现人信息</h3>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>会员编号：</label>
        <div class="form-controls">
            <input type="text" class="input" name="membercno" value="${obj.drawcashmember.cno}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>

    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>姓名：</label>
        <div class="form-controls">
            <input type="text" class="input" name="membercname" value="${obj.drawcash.membercname}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>

    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>联系电话：</label>
        <div class="form-controls">
            <input type="text" class="input" name="phone" value="${obj.drawcashmember.phone}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>
    
</div>
<!-- end 提现人信息 -->

<div class="panel-single panel-single-light mgt20 j-emptyhide">
    <h3 class="cst_h3 mgb20">支付信息</h3>
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>支付单据号：</label>
        <div class="form-controls">
            <input type="text" class="input" name="paybillcno" value="${obj.drawcash.paybillcno}" <#if obj.issavepay==true><#else>readonly</#if>>
            <span class="fi-help-text"></span>
        </div>
    </div>  

    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>支付时间：</label>
        <div class="form-controls">
            <input type="text" class="input" name="paytime" value="<#if obj.drawcash.paytime!=''>${obj.drawcash.paytime!?datetime?string('yyyy-MM-dd HH:mm')}</#if>" <#if obj.issavepay==true><#else>readonly</#if>>
 	        <#if obj.issavepay==true><button id="bt_savepay" type="button" class="btn btn-primary">修改</button></#if>
            <span class="fi-help-text">由付款人员填写付款单据号及付款时间登记</span>
        </div>
    </div>       
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>支付状态：</label>
        <div class="form-controls">
            <input type="text" class="input" name="paystate" value="${obj.drawcash.paystate}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>

    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>付款人：</label>
        <div class="form-controls">
            <input type="text" class="input" name="handlercname" value="<#if obj.issavepay && obj.drawcash.handlercname=="">${Session.sys_login_token.sys_login_username}<#else>${obj.drawcash.handlercname}</#if>" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>付款时间：</label>
        <div class="form-controls">
            <input type="text" class="input" name="handletime" value="${obj.drawcash.handletime}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>    

	<#--
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>付款账号类型：</label>
        <div class="form-controls">
            <input type="text" class="input" name="payaccounttype" value="${obj.drawcash.payaccounttype}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div> 
    
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>付款账号：</label>
        <div class="form-controls">
            <input type="text" class="input" name="payaccountno" value="${obj.drawcash.payaccountno}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>
    
    -->
    
</div>
<!-- end 支付信息 -->

</form>

</div>
<!-- end 订单标签页 -->

<div id="div_sp" style="display:none">

    <table class="wxtables table-order mgt20" id="tb_ordergoods">
        <colgroup>
	    <col width="15%">
        <col width="10%">
        <col width="5%">
        <col width="50%">
        <col width="5%">
        <col width="5%">
        <col width="5%">
        </colgroup>
        <thead>
            <tr>
	            <td>订单号</td>
	            <td>会员</td>
                <td>级别</td>	            
            	<td>商品</td>
                <td>数量</td>            	
                <td>返额</td>
                <td>总额</td>
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
	<td><a href="${base}/order/order/locate.action?id=<%=item.orderid%>" target="_blank" style="color:#0055ee"><%=item.ordercno%></a></td>
	<td><%=item.submembercname%></td>
    <td><%=item.level%></td>	
	<td><%=item.ordergoodsname%></td>
    <td><%=item.nums%></td>            	
    <td><%=item.rebate%></td>
    <td><%=item.score%></td>
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



$("#leftMenu").load('${base}/finance/drawcash/leftmenu.action');

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
$("#bt_savepay").click(function(){page_savepay()});
$("#bt_savelogistics").click(function(){page_savelogistics()});

var page_do_dd = function()
{
	console.log("page_do_dd");
};

var page_do_sp = function()
{
	console.log("page_do_sp");
	
	var drawcashid = '${obj.drawcash.id}';
	
	$.ajax({
		type:'POST',
		url:'${base}/finance/drawcash/listgoodsrebate.action',
		data:{"id":drawcashid},
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
	
	var drawcashid = '${obj.drawcash.id}';
	
	$.ajax({
		type:'POST',
		url:'${base}/flow/flow/ajaxtrace.action',
		data:{"dataid":drawcashid,"flowname":"提现"},
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
		url:'${base}/finance/drawcash/forward.action',
		data:{id:'${obj.drawcash.id}'},
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
				window.location = "${base}/finance/drawcash/locate.action?id=${obj.drawcash.id}";
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

function page_savepay()
{
	var paybillcno = $("input[name='paybillcno']").val();
	var paytime = $("input[name='paytime']").val();
	
	if(paybillcno=="")
	{
		pub_alert("没有填写付款单据号，不能保存。");	
		return;
	}

	$.ajax({
		type:'post',
		url:'${base}/finance/drawcash/savepay.action',
		data:{'id':'${obj.drawcash.id}', 'paybillcno':paybillcno, 'paytime':paytime},
		cache:false,
		async:true,
		success:function(data)
		{
			console.log(data);
			if(data=="")
			{
				pub_alert("提现付款登记异常！");
				return;
			}
			var json = eval("(" + data + ")");
			if(json.state=="success")
			{
				pub_alert("提现付款登记成功！");
				window.location = "${base}/finance/drawcash/locate.action?id=${obj.drawcash.id}";
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

function page_savelogistics()
{
	var logisticscomp = $(".input[name='logisticscomp']").val();
	var expressno = $(".input[name='expressno']").val();

	$.ajax({
		type:'post',
		url:'${base}/finance/drawcash/savelogistics.action',
		data:{'id':'${obj.drawcash.id}', 'logisticscomp':logisticscomp, 'expressno':expressno},
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
				// window.location = "${base}/finance/drawcash/locate.action?id=${obj.drawcash.id}";
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