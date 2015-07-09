<!DOCTYPE html>
<html>
<head>
	<#include "/decorator/include/header.ftl">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/base.css" charset="gbk">
	<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/extend.css" charset="gbk">
	<link rel="stylesheet" href="${base}/lib/jd/order/misc/css/base.css?v=20150604">
	<link rel="stylesheet" href="${base}/lib/jd/order/misc/css/pay.css?v=20150604">	
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</head>

	<body id="body">
		<form id="form_order" method="post" action="${base}/order/order/forward.action">
			<input type="hidden" id="keysignature" name="keysignature" value="${obj.keysignature}">    		
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
							<a href="javascript:void(0)" class="s-href" <#if obj.order.state=="下单">onclick="page_edittaker()"</#if>>
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
					<a href="javascript:void(0)" class="s-href" onclick="page_listordergoods()">
						<div class="s-item">
							<div class="sitem-l" id="goodslist">
								<#list obj.ordergoodses as goods>
								<input type="hidden" name="ordergoodsid" value="${goods.id}">
								<div class="sl-img">
									<img src="${base}/${goods.goodspic}">
								</div>
								</#list>	
							</div>

							<div class="sitem-r" style="color:#cecece">明细单数共${obj.ordergoodses?size}条</div>
							<span class="s-point"></span>
						</div>
					</a>
				</div>
					
				<div class="step1 border-1px" id="member_info">
					<div class="m step1-in">
					<a href="javascript:void(0)" class="s-href" <#if obj.order.state=="下单">onclick="page_editpayer()"</#if>>
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
						<div class="sitem-l">实付总额：</div>
						<div class="sitem-r">￥${obj.order.amount?number}</div>
					</div>
				<div class="s-item">
					<div class="sitem-l">原价总额/折扣价总额：</div>
					<div class="sitem-r" style="color:#cecece">￥${obj.order.amountsale?number}/${obj.order.amountpromote?number}</div>
				</div>					
					<div class="s-item">
						<div class="sitem-l">运费：</div>
						<div class="sitem-r"></div>
					</div>
				</div>

			</div>

			<div class="pay-bar" id="pay-bar">
				<div class="payb-con">实付款：<span id="payMoney">￥${obj.order.amount?number?string("0.00")}</span></div>
				<#if obj.order.state=="下单">
				<a id="btn_pay" class="payb-btn" href="javascript:void(0);">订单付款</a>
				<#else>
				<a class="payb-btn" href="javascript:void(0);">&nbsp;</a>
				</#if>
			</div>
		</div>
	</form>
	
	
<script>
var targetUrl=location.href.split("#")[0];
// alert("target url is:" + targetUrl);

wx.config({
    debug: true,
    appId: '${obj.jscfg.appId!}',
    timestamp: ${obj.jscfg.timestamp!},
    nonceStr: '${obj.jscfg.nonceStr!}',
    signature: '${obj.jscfg.signature!}',
    jsApiList: [
      'checkJsApi',
	    'onMenuShareTimeline',
	    'onMenuShareAppMessage',
	    'onMenuShareQQ',
	    'onMenuShareWeibo',
	    'chooseWXPay'
    ]
});

wx.ready(function()
{
	document.querySelector('#btn_pay').onclick = page_pay;
});

// 微信支付
var payFunc = function()
{
	$.ajax({
		url:"${base}/order/order/pay.action?orderno=${obj.order.cno}&amt=1",
		cache: false,
		success:function(data)
		{
			var json = eval("(" + data + ")");

			if(json.state=="success")
			{
				var resulttext = json.res;
				var result = eval("(" + resulttext + ")");
				
				wx.chooseWXPay({
					timestamp: result.sa.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
					nonceStr: result.sa.nonceStr, // 支付签名随机串，不长于 32 位
					package: result.sa.package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
					signType: result.sa.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
					paySign: result.sa.paySign, // 支付签名
					success: function (res) 
					{
						// 支付成功之后的处理
						if(res.errMsg=="chooseWXPay:ok")
						{
							window.location = "${base}/order/order/list.action?batchno=${obj.order.batchno}";
						}
						else
		        		{
		           	 		alert("支付失败，请稍后再试。原因："+res.errMsg);
		        		}
					},
					error:function(data)
					{
						alert("支付失败，网络或服务器无法访问，请稍后再试。");
					}
				});				
			}
			else
			{
				alert("订单付款失败："+json.message);
			}
		},
		error:function(data)
		{
			alert("支付失败，网络或服务器无法访问，请稍后再试。");
		}
		
	});
}

// 防止手机的enter键自动提交
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

function page_editpayer()
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
	
	window.location = "${base}/order/order/editpayer.action?id=" + id;
}

function page_listordergoods()
{
	var id = $("#id").val();
	window.location = "${base}/order/order/listordergoods.action?id=" + id;	
}

// 订单付款
function page_pay()
{
	var sign = false; // 表单提交标识
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
	
	var keysignature = $("#keysignature").val();
	
	if(confirm("亲，是想要马上拍下这些宝贝吗？"))
	{
		payFunc();
	}
}

// 没有微信支付环境下的测试
function page_testpay()
{
	$.ajax({
		type:'POST',
		url:'${base}/order/order/forward.action',
		contentType: "application/json",
		data:JSON.stringify({"id":orderid, "keysignature":keysignature}),
		cache:false,
		async:true,
		success:function(data)
		{
			console.log(data);
			if(data=="")
			{
				alert("亲，对不起，付款不成功，检查一下再试试看！");
				return;
			}
			json = eval("(" + data + ")");
			if(json.state=="success")
			{
				alert("亲，你已成功付款，我们会尽快受理你的订单！");
				// 更新购物车数量等操作；
				window.location.reload();
			}
			else
			{
				alert("订单付款失败："+json.message);
			}
		},
		error:function(data)
		{
			console.log(data);
			alert("抱歉，服务请求异常，可能网络有什么问题，稍后再试试看吧！");
		}
	})
}


</script> 

</body>
</html>
