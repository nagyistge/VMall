<!DOCTYPE html>
<html>
<head>
	<#include "/decorator/include/header.ftl">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/base.css" charset="gbk">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/order.css" charset="gbk">
	
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/extend.css" charset="gbk">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/hotel.css" charset="gbk">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/airline.css" charset="gbk">
		
	<link rel="stylesheet" href="${base}/lib/jd/order/misc/css/base.css?v=20150604">
	<link rel="stylesheet" href="${base}/lib/jd/order/misc/css/pay.css?v=20150604">	

</head>
<body id="body" style="background:#ffffff">

<style>

.cart-checkbox.checked {
  background-position: -25px 0;
}

.cart-checkbox {
  display: block;
  width: 20px;
  height: 20px;
  margin: 0 auto;
  background: url(${base}/lib/jd/cart/css/img/shp-cart-icon-sprites.png?a=3)
  no-repeat 0 0;
  background-size: 50px 100px;
  font-family: '微软雅黑';
  font-size:14px;
}

.memo_box {
    width: 100%; 
    min-height: 100px; 
    max-height: 150px;
    _height: 120px; 
    margin-left: auto; 
    margin-right: auto; 
    padding: 3px; 
    outline: 0; 
    border: 1px solid #a0b3d6; 
    font-size: 12px; 
    word-wrap: break-word;
    overflow-x: hidden;
    overflow-y: auto;
    _overflow-y: visible;
}

</style>

<form method="POST" id="form_editmember">
<input type="hidden" id="id" name="id" value="${obj.id!}">
<input type="hidden" id="cno" name="cno" value="${obj.order.cno}">
<input type="hidden" id="state" name="state" value="${obj.order.state}">
<input type="hidden" id="ordergoodsid" name="ordergoodsid" value="${obj.ordergoodsid!}">
<input type="hidden" id="takeover" name="takeover" value="${obj.ordergoods.takeover}">
<input type="hidden" id="takeoverreason" name="takeoverreason" value="${obj.ordergoods.takeoverreason}">

<div class="new-ct shouhuo">

	<#--
    <div class="title">购买人信息</div>
 
    <div class="info-list">
         <div class="info pd">
        	<div class="tbl-type">
            	<span class="tbl-cell w100" style="text-align:right"><span>购买人编号：&nbsp;&nbsp;</span></span>
                <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="membercno" id="membercno" value="${obj.order.membercno}" style="width:100%;" readonly></span></span>
            </div>
        </div>
        <div class="info pd">
        	<div class="tbl-type">
            	<span class="tbl-cell w100" style="text-align:right"><span>购买人姓名：&nbsp;&nbsp;</span></span>
                <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="membercname" id="membercname" value="${obj.order.membercname}" style="width:100%;"></span></span>
            </div>
		</div>
		<div class="info pd">	
        	<div class="tbl-type">
            	<span class="tbl-cell w100" style="text-align:right"><span>购买人电话：&nbsp;&nbsp;</span></span>
                <span class="tbl-cell"><span><input type="text" maxlength="11" class="new-input" name="phone" id="phone" value="${obj.order.phone}" style="width:100%;"></span></span>
            </div>
        </div>
	</div>	
    -->
    
    <div class="title">收货信息</div>  
    <div class="info-list">
        
      
        <div class="info pd">
        	<div class="tbl-type">
            	<span class="tbl-cell w100" style="text-align:right"><span>收货：&nbsp;&nbsp;</span></span>
                <span class="tbl-cell" id="cell_takeover">
                	<p>&nbsp;</p>
                	<p>
                	<span class="cart-checkbox <#if obj.ordergoods.takeover="同意">checked</#if>" style="float:left" id="check_agree" cindex="" value="同意"></span><span style="float:left;width:60px">同意</span>
                	<span class="cart-checkbox <#if obj.ordergoods.takeover='拒绝'>checked</#if>" style="float:left" id="check_reject" cindex="" value="拒绝"></span><span style="width:60px">拒绝</span>
                	</p>
                	<p>&nbsp;</p>
				</span>
            </div>
        </div>
        
       	<div class="info pd">
        	<div class="tbl-type" style="height:150px">
            	<span class="tbl-cell w100" style="text-align:right"><span>原因：&nbsp;&nbsp;</span></span>
                <span class="tbl-cell" id="cell_takeover_reason" style="float:left;font-family:'微软雅黑';font-size:14px;">
	               		<p>&nbsp;<p>
	               		<span><span class="cart-checkbox <#if obj.ordergoods.takeoverreason="包装破损">checked</#if>" style="float:left" id="" cindex="" value="包装破损"></span><span>包装破损</span></span>
	               		<p>&nbsp;<p>
	               		<span><span class="cart-checkbox <#if obj.ordergoods.takeoverreason="商品错发漏发">checked</#if>" style="float:left" id="" cindex="" value="商品错发漏发"></span><span>商品错发漏发</span></span>
	               		<p>&nbsp;<p>
	               		<span><span class="cart-checkbox <#if obj.ordergoods.takeoverreason="商品需要维修">checked</#if>" style="float:left" id="" cindex="" value="商品需要维修"></span><span>商品需要维修</span></span>
	               		<p>&nbsp;<p>
	               		<span><span class="cart-checkbox <#if obj.ordergoods.takeoverreason="发票原因">checked</#if>" style="float:left" id="" cindex="" value="发票原因"></span><span>发票原因</span></span>
	               		<p>&nbsp;<p>
	               		<span><span class="cart-checkbox <#if obj.ordergoods.takeoverreason="收到商品与描述不符">checked</#if>" style="float:left" id="" cindex="" value="收到商品与描述不符"></span><span>收到商品与描述不符</span></span>
	               		<p>&nbsp;<p>
	               		<span><span class="cart-checkbox <#if obj.ordergoods.takeoverreason="商品质量问题">checked</#if>" style="float:left" id="" cindex="" value="商品质量问题"></span><span>商品质量问题</span></span>
	               		<p>&nbsp;<p>
	               		<span><span class="cart-checkbox <#if obj.ordergoods.takeoverreason="未按约定时间发货">checked</#if>" style="float:left" id="" cindex="" value="未按约定时间发货"></span><span>未按约定时间发货</span></span>
	               		<p>&nbsp;<p>
	               		<span><span class="cart-checkbox <#if obj.ordergoods.takeoverreason="收到假货">checked</#if>" style="float:left" id="" cindex="" value="收到假货"></span><span>收到假货</span></span>
     					<p>&nbsp;<p>
                </span>
            </div>
        </div>

    </div>
    <!--[D] 默认时加  new-abtn-default 把a标签换成span-->
    <span class="new-abtn-type mgn" onclick="page_submit()">确定</span>	
</div>
</form>

<script>

$("#check_agree").click(function(){page_check_agree()});
$("#check_reject").click(function(){page_check_reject()});
$("#cell_takeover_reason .cart-checkbox").click(function(){

	$("#takeover").val("");
	$("#check_agree").removeClass("checked");
	$("#check_reject").addClass("checked");
	($("#cell_takeover_reason .cart-checkbox")).removeClass("checked");
	$(this).addClass("checked");
	
	$("#takeover").val("拒绝");
	$("#takeoverreason").val($(this).attr("value"));

});

function page_check_agree()
{
	$("#takeover").val("");
	$("#takeoverreason").val("");
	
	$("#check_reject").removeClass("checked");
	$("#cell_takeover_reason .cart-checkbox").each(function(){$(this).removeClass("checked")});
	$("#check_agree").addClass("checked");
	
	$("#takeover").val("同意");
	$("#takeoverreason").val("");
	
}

function page_check_reject()
{
	$("#takeover").val("");
	$("#takeoverreason").val("");	
	
	$("#check_agree").removeClass("checked");
	$("#check_reject").addClass("checked");
	($("#cell_takeover_reason .cart-checkbox:first")).addClass("checked");
	
	$("#takeover").val("拒绝");
	$("#takeoverreason").val(($("#cell_takeover_reason .cart-checkbox:first")).attr("value"));
}

function page_submit()
{
	var id = $("#id").val();	
	var ordergoodsid = $("#ordergoodsid").val();
	var takeover = $("#takeover").val();
	var takeoverreason = $("#takeoverreason").val();
	
	if(takeover=="")
	{
		alert("亲，你还没有选好同意还是拒绝收货哦。");
		return;
	}
	
	if(takeover=="拒绝" &&takeoverreason=="")
	{
		alert("亲，你还没有填写拒绝收货原因哦。");
		return;
	}
	
	$.ajax({
		type:'POST',
		url:'${base}/order/order/savetakeover.action',
		data:{"id":id,"ordergoodsid":ordergoodsid,"takeover":takeover,"takeoverreason":takeoverreason},
		cache:false,
		async:true,
		success:function(data)
		{
			console.log(data);
			if(data=="")
			{
				alert("亲，保存收货结果异常，请你再试试！");
				return;
			}
			json = eval("(" + data + ")");
			if(json.state=="success")
			{
				window.location = "${base}/order/order/listordergoods.action?id="+id;
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

</script>

</body>
</html>