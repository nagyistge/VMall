<html>
<head>
<title>优品365.订单管理.订单查询</title>
</head>
<body>

<h1 class="content-right-title">订单查询</h1>
<#if obj.issave==true><button id="bt_save" class="btn btn-primary">保存</button></#if>
<#if obj.isforward==true><button id="bt_forward" class="btn btn-primary">转${obj.flownextstate}</button></#if>
<#if obj.isbackward==true><button id="bt_backward" class="btn btn-primary">退回到${obj.flowbackstate}</button></#if>

<form action="${base}/goods/goods/update.action" method="post" id="editform">
<input type="hidden" name="id" value="${obj.order.id}">
<div class="panel-single panel-single-light mgt20">
    <h3 class="cst_h3 mgb20">基本信息</h3>
    <div class="formitems">
        <label class="fi-name"><span class="colorRed">*</span>订单号：</label>
        <div class="form-controls">
            <input type="text" class="input xxlarge" name="cno" value="${obj.order.cno}" readonly>
            <span class="fi-help-text"></span>
        </div>
    </div>

</div>
<!-- end 基本信息 -->

<div class="panel-single panel-single-light mgt20">
    <h3 class="cst_h3 mgb20">购买信息</h3>
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
    <h3 class="cst_h3 mgb20">收货信息</h3>
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

<script src="${base}/public/js/dist/lib-min.js"></script>

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
//////////
	
$("#leftMenu").load('${base}/page/order/leftmenu.ftl');

$("#bt_forward").click(function() {page_forward()});

function page_forward()
{
	$.ajax({
		type:'post',
		url:'${base}/order/order/forward.action',
		data:{id:'${obj.order.id}'},
		cache:false,
		async:false,
		success:function(data)
		{
			console.log(data);
			if(data=="")
			{
				alert("转发异常！");
				return;
			}
			json = eval("(" + data + ")");
			if(json.state=="success")
			{
				alert("转发成功！");
				window.location = "${base}/order/order/locate.action?id=${obj.order.id}";
			}
			else
			{
				alert("转发失败！原因："+json.errormessage);
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