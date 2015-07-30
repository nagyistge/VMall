<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta content="telephone=no" name="format-detection" />

<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/order.css" charset="gbk">
<link href="${base}/lib/jd/cart/css/shopping-cart.css" media="all" rel="stylesheet" type="text/css">


<link rel="stylesheet" type="text/css" href="${base}/lib/jd/css/layout.min.css">
<link rel="stylesheet" type="text/css" href="${base}/lib/jd/css/common.min.css">


<script src="${base}/lib/jquery-2.1.1.min.js"></script>
<script src="${base}/lib/jquery-ui.min.js"></script>
</head>

<body id="body" style="background:#ffffff">

<#include "/decorator/include/navmain.ftl">
<form id="form_draw" method="POST">
<input type="hidden" id="keysignature" name="keysignature" value="${obj.keysignature}">
<input type="hidden" id="id" name="id" value="${obj.drawcash.id}">
<div class="detail" id="bybase" style="display: block;">

<div class="info-list">

	<div class="info pd">
	<div class="tbl-type">
		<span class="tbl-cell w70"><span>提现单号：</span></span>
		<span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="cno" id="cno" value="${obj.drawcash.cno}" readonly></span></span>
	</div>
	</div>
	
	<div class="info pd">
	<div class="tbl-type">
		<span class="tbl-cell w70"><span>提现额：</span></span>
		<span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="amount" id="amount" value="${obj.drawcash.amount}" readonly></span></span>
	</div>
	</div>	

	<div class="info pd">
	<div class="tbl-type">
		<span class="tbl-cell w70"><span>状态：</span></span>
		<span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="state" id="state" value="${obj.drawcash.state}" readonly></span></span>
	</div>
	</div>	

	<div class="info pd">
	<div class="tbl-type">
		<span class="tbl-cell w70"><span>会员编号：</span></span>
		<span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="membercno" id="membercno" value="${obj.drawcash.membercno}" readonly></span></span>
	</div>
	</div>
	
	<div class="info pd">
	<div class="tbl-type">
		<span class="tbl-cell w70"><span>姓名：</span></span>
	    <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="membercname" id="membercname" value="${obj.drawcash.membercname}" title="姓名" required readonly></span></span>
	</div>
	</div>
	
	<div class="info pd">
	<div class="tbl-type">
		<span class="tbl-cell w70"><span>电话：</span></span>
	    <span class="tbl-cell"><span><input type="text" maxlength="11" class="new-input" name="memberphone" id="memberphone" value="${obj.drawcash.memberphone}" title="电话" required readonly></span></span>
	</div>
	</div>
	
	<div class="info pd">
	<div class="tbl-type">
		<span class="tbl-cell w70"><span>账户类型：</span></span>
	    <span class="tbl-cell">
		    <span>
        	<input type="text" maxlength="50" class="new-input" name="accounttype" id="accounttype" value="${obj.drawcash.accounttype}" title="账户类型" required readonly>
		    <#--
		    <input type="radio" name="accounttype" value="银行" required>&nbsp;银行账号	    
		    <input type="radio" name="accounttype" value="支付宝" required>&nbsp;支付宝		    
		    <input type="radio" name="accounttype" value="微信" required>&nbsp;微信支付	
		    -->	  
		    </span>
	    </span>
	</div>
	</div>	

	<div class="info pd">
	<div class="tbl-type">
	<span class="tbl-cell w70"><span>所属银行：</span></span>
        <span class="tbl-cell">
        	<span>
        	<input type="text" maxlength="50" class="new-input" name="bank" id="bank" value="${obj.drawcash.bank}" title="所属银行" required readonly>
        	<#--
            <select name="bank" id="bank" style="width:200px">
        	<option <#if obj.drawcash.bank=="中国工商银行">selected</#if> value="中国工商银行">中国工商银行</option>
        	<option <#if obj.drawcash.bank=="中国建设银行">selected</#if> value="中国建设银行">中国建设银行</option>
        	<option <#if obj.drawcash.bank=="中国交通银行">selected</#if> value="中国交通银行">中国交通银行</option>
        	<option <#if obj.drawcash.bank=="中国农业银行">selected</#if> value="中国农业银行">中国农业银行</option>
        	<option <#if obj.drawcash.bank=="中国银行">selected</#if> value="中国银行">中国银行</option>
        	<option <#if obj.drawcash.bank=="其它">selected</#if> value="其它">其它</option>
            </select>
            -->
            </span>
        </span>
    </div>
    </div>
    
	<div class="info pd">
	<div class="tbl-type">
    	<span class="tbl-cell w70"><span>开户银行：</span></span>
        <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="openbank" id="openbank" value="${obj.drawcash.openbank}" title="开户银行" required readonly></span></span>
    </div>
</div>

<div class="info pd">
	<div class="tbl-type">
    	<span class="tbl-cell w70"><span>银行卡号：</span></span>
        <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="bankaccountno" id="bankaccountno" value="${obj.drawcash.bankaccountno}" title="银行卡号" required readonly></span></span>
    </div>
</div>

<div class="info pd">
	<div class="tbl-type">
    	<span class="tbl-cell w70"><span>账户姓名：</span></span>
        <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="bankaccountcname" id="bankaccountcname" value="${obj.drawcash.bankaccountcname}" title="账户姓名" required readonly></span></span>
    </div>
</div>

<div class="info pd">
<div class="tbl-type">
	<span class="tbl-cell w70"><span>关联电话：</span></span>
    <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="bankaccountphone" id="bankaccountphone" value="${obj.drawcash.bankaccountphone}" readonly></span></span>
</div>
</div>
<br/>    
<p>亲，请你注意核对提现的账户信息哦</p>    
</div>
		
	    
	</div>
	
	</div>


</form>


<div id="payment_p" style="display:block">
<div id="paymentp"></div>
<div class="payment-total-bar" id="payment">
    <div class="shp-chk"></div>
    <div class="shp-cart-info">
        <strong class="shp-cart-total"><#--可提现总额:￥<span class="" id="cart_realPrice"></span>--></strong>
        <span class="sale-off"><#--提现订单条目:--></span>	
    </div>

    <#if obj.drawcash.state=="申请">	
    <a class="btn-right-block" id="submit" style="width:150px;background-color: rgb(192, 0, 0); background-position: initial initial; background-repeat: initial initial;">提交申请</a>
    </#if>
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
	
	var id = $("#id").val();
	
	$.ajax({
		type:'POST',
		url:'${base}/order/drawcash/forward.action',
		data:$("#form_draw").serialize(),
		cache:false,
		async:true,
		success:function(data)
		{
			console.log(data);
			if(data=="")
			{
				alert("提现单提交申请异常，请检查后再试试！");
				return;
			}
			json = eval("(" + data + ")");
			if(json.state=="success")
			{
				alert("提现单提交申请成功！");
				window.location = "${base}/order/drawcash/locate.action?id=" + id;
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