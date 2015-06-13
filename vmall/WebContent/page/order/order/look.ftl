<!DOCTYPE html>
<html>
<head>
	<#include "/decorator/include/header.ftl">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/base.css" charset="gbk">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/extend.css" charset="gbk">
	<link rel="stylesheet" href="${base}/lib/jd/order/misc/css/base.css?v=20150604">
	<link rel="stylesheet" href="${base}/lib/jd/order/misc/css/pay.css?v=20150604">	
</head>

	<body id="body">
		<form id="form_order" method="post" action="${base}/order/order/forward.action">
			<input type="hidden" id="id" name="id" value="${obj.order.id}" required>
			<input type="hidden" id="cno" name="cno" value="${obj.order.cno}" required>
			<input type="hidden" id="state" name="state" value="${obj.order.state}">
			<input type="hidden" id="membercname" name="membercname" value="${obj.order.membercname}" title="购买人" required>
			<input type="hidden" id="membercno" name="membercno" value="${obj.order.membercno}" title="购买人编号" required>
			<input type="hidden" id="phone" name="phone" value="${obj.order.phone}" title="购买人电话" required>
			<input type="hidden" id="takercname" name="takercname" value="${obj.order.takercname}" title="收货人" required>			
			<input type="hidden" id="takermobile" name="takermobile" value="${obj.order.takermobile}" title="收货人电话" required>
			<input type="hidden" id="takeaddress" name="takeaddress" value="${obj.order.takeaddress}" title="收货地址" required>
			<div class="common-wrapper">
				<div class="w checkout" style="padding:0px;">
					<div class="step1 border-1px" id="taker_info">
						<div class="m step1-in ">
							<a href="javascript:void(0)" class="s-href" onclick="page_edittaker()">
								<div class="mt_new"><div class="mt_new"><span style="margin-right:20px">编号：${obj.order.cno}</span><span>状态：${obj.order.state}</span></div></div>
								<div class="mt_new"><div class="mc">收货人：${obj.order.takercname}</div></div>
								<div class="mt_new"><div class="mc">联系电话：${obj.order.takermobile}</div></div>
								<div class="mt_new"><div class="mc step1-in-con">收货地址：${obj.order.takeaddress}</div></div>
							</a>
						</div>
						<b class="s1-borderT"></b>
						<b class="s1-borderB"></b>
						<span class="s-point"></span>
					</div>
					
					<div class="step3 border-1px step3-more" id="goodses_info">
					<a href="" class="s-href">
						<div class="s-item">
							<div class="sitem-l" id="goodslist">

								<#assign allamount = 0>
								<#assign allgoodsnums = 0>

								<#list obj.ordergoodses as goods>

								<#assign allamount = allamount + (((goods.promoteprice+0)?number)/10)>
								<#assign allgoodsnums = allgoodsnums + (((goods.nums+0)?number)/10)>
								<input type="hidden" name="ordergoodsid" value="${goods.id}">
								<div class="sl-img">
									<img src="http://img10.360buyimg.com/n4/jfs/t1249/100/386284951/341939/a2f205fb/551d0baaNf363954e.jpg">
								</div>
								</#list>	
							</div>

							<div class="sitem-r">共${obj.ordergoodses?size}件</div>
							<span class="s-point"></span>
						</div>
					</a>
				</div>
					
				<div class="step1 border-1px" id="member_info">
					<div class="m step1-in">
					<a href="javascript:void(0)" class="s-href" onclick="page_editmember()">
						<div class="mt_new"><div class="mc">购买人编号：${obj.order.membercno}</div></div>
						<div class="mt_new"><div class="mc">姓名：${obj.order.membercname}</div></div>
						<div class="mt_new"><div class="mc">联系电话：${obj.order.phone}</div></div>
						<span class="s-point"></span>
						</a>	
					</div>
				</div>
				
				<div class="step1 border-1px" id="paymode_info">
					<div class="m step1-in">
					<a href="" class="s-href">
						<div class="mt_new"><div class="mc">支付方式：在线支付</div></div>
						<div class="mt_new"><div class="mc">配送方式：普通快递</div></div>
						<span class="s-point"></span>
						</a>	
					</div>
				</div>

				<div class="step5 border-1px" id="carriage_info" style="margin-bottom: 3.125em;">
					<div class="s-item">
						<div class="sitem-l">商品金额：</div>
						<div class="sitem-r">￥${allamount?string("0.00")}</div>
					</div>
					<div class="s-item">
						<div class="sitem-l">运费：</div>
						<div class="sitem-r"></div>
					</div>
				</div>

			</div>

			<div class="pay-bar" id="pay-bar">
				<div class="payb-con">实付款：<span id="payMoney">￥${allamount?string("0.00")}</span></div>
				<a class="payb-btn" onclick="page_forward()" href="javascript:void(0);">提交订单</a>
			</div>
		</div>
	</form>
	
	
<script>
/**
 *   防止手机的enter键自动提交。
 * @returns {boolean}
 */
document.onkeypress =function()
{
    if(event.keyCode == 13)
    {
        return false;
    }
}

function page_edittaker()
{
	var id = $("#id").val();
	var state = $("#state").val();
	if(id=="")
	{
		alert("订单信息异常，请检查订单。");
		return;
	}
	
	if(state!="下单")
	{
		alert("该订单已受理，不允许修改收货信息。");
		return;
	}
	
	window.location = "${base}/order/order/edittaker.action?id=" + id;
}

function page_editmember()
{
	var id = $("#id").val();
	var state = $("#state").val();
	if(id=="")
	{
		alert("订单信息异常，请检查订单。");
		return;
	}
	
	if(state!="下单")
	{
		alert("该订单已受理，不允许修改购买人信息。");
		return;
	}
	
	window.location = "${base}/order/order/editmember.action?id=" + id;
}

// 提交订单
function page_forward()
{
	var sign = false; //表单提交标识
	var orderid = $("#id").val();
	var state = $("#state").val();	
	
	if(orderid=="")
	{
		alert("订单数据异常，无法提交订单。");
		return;
	}

	if(!("下单"==state))
	{
		alert("您无法提交该订单，该订单已经处理过或异常。");
		return;
	}
	
	// 检查字段完整性
	var fields = $('#form_order input');
	for(i=0;i<fields.length;i++)
	{
		if(fields[i].required==true&&fields[i].value=='')
		{
			alert("必须填写"+fields[i].title);
			return;
		}
	}
	
	if(confirm("订单提交后无法修改，请您确认提交订单吗？"))
	{
		$.ajax({
			type:'post',
			url:'${base}/order/order/forward.action',
			contentType: "application/json",
			data:JSON.stringify({"id":orderid}),
			cache:false,
			async:false,
			success:function(data)
			{
				console.log(data);
				if(data=="")
				{
					alert("提交订单异常！");
					return;
				}
				json = eval("(" + data + ")");
				if(json.state=="success")
				{
					alert("成功提交订单！");
					// 更新购物车数量等操作；
					window.location.reload();
				}
				else
				{
					alert("提交订单失败！");
				}
			},
			error:function(data)
			{
				console.log(data);
				alert("服务请求异常！");
			}
		})		
	}
}

</script> 

</body>
</html>
