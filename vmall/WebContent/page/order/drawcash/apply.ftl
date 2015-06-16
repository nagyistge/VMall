<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/base.css" charset="gbk">
<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/order.css" charset="gbk">

<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/extend.css" charset="gbk">
<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/hotel.css" charset="gbk">
<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/airline.css" charset="gbk">
	
<link rel="stylesheet" href="${base}/lib/jd/order/misc/css/base.css?v=20150604">
<link rel="stylesheet" href="${base}/lib/jd/order/misc/css/pay.css?v=20150604">
<link href="${base}/lib/jd/cart/css/shopping-cart.css" media="all" rel="stylesheet" type="text/css">
<#include "/decorator/include/header.ftl">
</head>
<body id="body">
<#include "/decorator/include/navmain.ftl">
<style>
.new-title {font-size:12px;color:#aeaeae}
</style>

<form id="form_draw" method="POST" action="${base}/order/drawcash/insert.action">
<input type="hidden" id="cname" name="cname" value="${obj.member.cname}">
<input type="hidden" id="phone" name="phone" value="${obj.member.phone}">
<input type="hidden" id="bank" name="bank" value="${obj.member.bank}">
<input type="hidden" id="openbank" name="openbank" value="${obj.member.openbank}">
<input type="hidden" id="bankaccountno" name="bankaccountno" value="${obj.member.bankaccountno}">


<div class="detail" id="bybase" style="display: block;">

<div class="info-list">

	<div class="info pd">
	<div class="tbl-type">
		<span class="tbl-cell w70"><span>编号：</span></span>
		<span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="cno" id="cno" value="${obj.member.cno}" readonly></span></span>
	</div>
	</div>
	
	<div class="info pd">
	<div class="tbl-type">
		<span class="tbl-cell w70"><span>姓名：</span></span>
	    <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="cname" id="cname" value="${obj.member.cname}" title="姓名" required readonly></span></span>
	</div>
	</div>
	
	<div class="info pd">
	<div class="tbl-type">
		<span class="tbl-cell w70"><span>电话：</span></span>
	    <span class="tbl-cell"><span><input type="text" maxlength="11" class="new-input" name="phone" id="phone" value="${obj.member.phone}" title="电话" required readonly></span></span>
	</div>
	</div>

	<div class="info pd">
	<div class="tbl-type">
    	<span class="tbl-cell w70"><span>所属银行：</span></span>
        <span class="tbl-cell">
        	<span>
            <select name="bank" id="bank" style="width:200px"><option selected="" id="option_add_1" value="1">中国工商银行</option><option id="option_add_2" value="2">中国建设银行</option><option id="option_add_3" value="3">中国交通银行</option><option id="option_add_4" value="4">中国农业银行</option></select>
            </span>
        </span>
    </div>
    </div>
    
	<div class="info pd">
	<div class="tbl-type">
    	<span class="tbl-cell w70"><span>开户银行：</span></span>
        <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="openbank" id="openbank" value="${obj.member.openbank}" title="开户银行" required></span></span>
    </div>
</div>

<div class="info pd">
	<div class="tbl-type">
    	<span class="tbl-cell w70"><span>银行卡号：</span></span>
        <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="bankaccountno" id="bankaccountno" value="${obj.member.bankaccountno}" title="银行卡号" required></span></span>
    </div>
</div>

<div class="info pd">
	<div class="tbl-type">
    	<span class="tbl-cell w70"><span>账户姓名：</span></span>
        <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="bankaccountcname" id="bankaccountcname" value="${obj.member.bankaccountcname}" title="账户姓名" required></span></span>
    </div>
</div>

<div class="info pd">
<div class="tbl-type">
	<span class="tbl-cell w70"><span>关联电话：</span></span>
    <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="bankaccountphone" id="bankaccountphone" value="${obj.member.bankaccountphone}1234"></span></span>
</div>
</div>
    
    
</div>
		
	    
	</div>
	
	</div>


</form>

<div id="payment_p" style="display:block">
<div id="paymentp"></div>
<div class="payment-total-bar" id="payment">
    <div class="shp-chk">
        <span onclick="checkAllHandler();" class="cart-checkbox checked" id="checkIcon-1"></span>
    </div>
    <div class="shp-cart-info">
        <strong class="shp-cart-total">总计:￥<span class="" id="cart_realPrice"></span></strong>
        <span class="sale-off">提现订单条目:
        </span>	
    </div>
    <a class="btn-right-block" id="submit" style="background-color: rgb(192, 0, 0); background-position: initial initial; background-repeat: initial initial;">申请(<span id="checkedNum"></span>)</a>
</div>
</div>

<script>
$("#submit").click(function() {page_submit()});
function page_submit()
{
	// $("#form_draw").submit();
	
	// 检查字段完整性
	var fields = $('#form_draw input,textarea');
	for(i=0;i<fields.length;i++)
	{
		if(fields[i].required==true)
		{
			if(fields[i].value=='')
			{
				alert(fields[i].title+"不能为空。");
				// eval(fields[i].name+'_error.style.display="block";');
				return;
			}
			else
			{
				// eval(fields[i].name+'_error.style.display="none";');
			}
		}
	}
	
	$.ajax({
		type:'POST',
		url:'${base}/order/drawcash/insert.action',
		data:$("#form_draw").serialize(),
		cache:false,
		async:false,
		success:function(data)
		{
			console.log(data);
			if(data=="")
			{
				alert("提现申请异常，请检查后再试试！");
				return;
			}
			json = eval("(" + data + ")");
			if(json.state=="success")
			{
				alert("申请提现成功！");
				window.location = "${base}/order/drawcash/locate.action?id=" + json.drawcash.id;
			}
			else
			{
				alert("申请提现失败："+json.message);
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