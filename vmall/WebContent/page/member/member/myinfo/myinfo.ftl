<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/base.css" charset="gbk">
<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/order.css" charset="gbk">
<link rel="stylesheet" href="${base}/lib/jd/order/misc/css/base.css?v=20150604">
<link rel="stylesheet" href="${base}/lib/jd/order/misc/css/pay.css?v=20150604">
<link href="${base}/lib/jd/cart/css/shopping-cart.css" media="all" rel="stylesheet" type="text/css">
<#include "/decorator/include/header.ftl">
</head>
<body id="body" style="background:#ffffff">

<#include "/decorator/include/navmain.ftl">

<form action="${base}/member/member/save.action" method="POST" id="form_info">
<input type="hidden" id="id" name="id" value="${obj.member.id}">

<div data-spm="" class="content">
	<nav data-spm="1006" class="tabs">
		<div type="base" data-sort="" data-spm-click="" class="tab-item active" data-spm-anchor-id="">个人</div>
		<div type="addr" data-sort="" data-spm-click="" class="tab-item" data-spm-anchor-id="">地址</div>
		<div type="bank" data-sort="" data-spm-click="" class="tab-item" data-spm-anchor-id="">账号</div>
	</nav>
	
	<div class="detail" id="bybase" style="display:block;font-size:14px">

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
                <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="cname" id="cname" value="${obj.member.cname}" title="姓名" required></span></span>
            </div>
        </div>
		<div class="info pd" id="cname_error" style="display:none">
        	<div class="tbl-type">
            	<span class="tbl-cell w100"><span style="color:red">姓名不能为空。</span></span>
            </div>
        </div>        
    	<div class="info pd">
    	<div class="tbl-type">
        	<span class="tbl-cell w70"><span>微信昵称：</span></span>
            <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="wxnickname" id="wxnickname" value="${obj.member.wxnickname}" title="昵称" required readonly></span></span>
        </div>
    </div>

        
        <div class="info pd">
        	<div class="tbl-type">
            	<span class="tbl-cell w70"><span>电话：</span></span>
                <span class="tbl-cell"><span><input type="text" maxlength="11" class="new-input" name="phone" id="phone" value="${obj.member.phone}" title="电话" required></span></span>
            </div>
        </div>
        <div class="info pd" id="phone_error" style="display: none;">
        	<div class="tbl-type">
            	<span class="tbl-cell w100"><span style="color:red">电话不能为空。</span></span>
            </div>
        </div>

        <div class="info pd">
    	<div class="tbl-type">
        	<span class="tbl-cell w70"><span>QQ：</span></span>
            <span class="tbl-cell"><span><input type="text" maxlength="11" class="new-input" name="qq" id="qq" value="${obj.member.qq}"></span></span>
        </div>
	    </div>

        <div class="info pd">
    	<div class="tbl-type">
        	<span class="tbl-cell w70"><span>电子邮箱：</span></span>
            <span class="tbl-cell"><span><input type="text" maxlength="11" class="new-input" name="email" id="email" value="${obj.member.email}"></span></span>
        </div>
	    </div>
	    
	    
    </div>

</div>
<div class="detail" id="byaddr" style="display:none;font-size:14px">

	<div class="info-list">

		<div class="info pd">
		<div class="tbl-type">
			<span class="tbl-cell w70"><span>省份：</span></span>
		    <span class="tbl-cell">
		    	<span>
		        <#-- <select name="province" id="province" style="width:200px"></select> -->
                <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="province" id="province" value="${obj.member.province}" title="省份" required></span></span>
		        </span>
		    </span>
		</div>
		</div>
		
		<div class="info pd">
		<div class="tbl-type">
		<span class="tbl-cell w70"><span>市：</span></span>
		<span class="tbl-cell">
			<span>
		    <#-- <select name="city" id="city" style="width:200px"></select> -->
            <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="city" id="city" value="${obj.member.city}" title="市" required></span></span>
		    </span>
		</span>
		</div>
		</div>
		
		<div class="info pd">
		<div class="tbl-type">
		<span class="tbl-cell w70"><span>县区：</span></span>
		<span class="tbl-cell">
			<span>
		    <#-- <select name="county" id="county" style="width:200px;"></select> -->
            <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="county" id="county" value="${obj.member.county}" title="县" required></span></span>
		    </span>
		</span>
		</div>
		</div>
		
		<div class="info pd">
		<div class="tbl-type">
		<span class="tbl-cell w70"><span>乡镇：</span></span>
		<span class="tbl-cell">
			<span>
		    <#-- <select name="town" id="town" style="width:200px;"></select> -->
		    <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="town" id="town" value="${obj.member.town}" title="镇" required></span></span>
		    </span>
		</span>
		</div>
		</div>
		
		<div class="info border-b-none pd">
		<div class="tbl-type">
			<span class="tbl-cell w70"><span>详细地址：</span></span>
		    <span class="tbl-cell"><span><label id="address_label"></label>
		    <textarea rows="5" style="height:auto;width:100%;" type="text" class="new-input" name="addr" id="addr" title="详细地址" required>${obj.member.addr}</textarea></span></span>
		</div>
		</div>

    	<div class="info pd" id="addr_error" style="display:none">
    	<div class="tbl-type">
        	<span class="tbl-cell w100"><span style="color:red">详细地址不能为空。</span></span>
        </div>
        </div>
		
	</div>	

</div>
<div class="detail" id="bybank" style="display:none;font-size:14px">

	<div class="info-list">
	
		<div class="info pd">
		<div class="tbl-type">
			<span class="tbl-cell w70"><span>账户类型：</span></span>
		    <span class="tbl-cell">
			    <span>
			    <select name="accounttype" id="accounttype" class="new-input" style="width:200px" required>
			    <option <#if obj.member.accounttype=="银行账号">selected</#if> value="银行账号">银行账号</option>
			    </select>
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
	                <select name="bank" id="bank" class="new-input" style="width:200px" required>
		        	<option <#if obj.member.bank=="中国工商银行">selected</#if> value="中国工商银行">中国工商银行</option>
		        	<option <#if obj.member.bank=="中国建设银行">selected</#if> value="中国建设银行">中国建设银行</option>
		        	<option <#if obj.member.bank=="中国交通银行">selected</#if> value="中国交通银行">中国交通银行</option>
		        	<option <#if obj.member.bank=="中国农业银行">selected</#if> value="中国农业银行">中国农业银行</option>
		        	<option <#if obj.member.bank=="中国银行">selected</#if> value="中国银行">中国银行</option>
		        	<option <#if obj.member.bank=="其它">selected</#if> value="其它">其它</option>
		        	</select>
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
		<div class="info pd" id="bank_error" style="display:none">
			<div class="tbl-type">
		    	<span class="tbl-cell w100"><span style="color:red">开户银行不能为空。</span></span>
		    </div>
		</div>
		
		<div class="info pd">
			<div class="tbl-type">
		    	<span class="tbl-cell w70"><span>银行卡号：</span></span>
		        <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="bankaccountno" id="bankaccountno" value="${obj.member.bankaccountno}" title="银行卡号" required></span></span>
		    </div>
	    </div>
		<div class="info pd" id="bankaccountno_error" style="display:none">
			<div class="tbl-type">
		    	<span class="tbl-cell w100"><span style="color:red">银行卡号不能为空。</span></span>
		    </div>
		</div> 	
		
		<div class="info pd">
			<div class="tbl-type">
		    	<span class="tbl-cell w70"><span>账户姓名：</span></span>
		        <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="bankaccountcname" id="bankaccountcname" value="${obj.member.bankaccountcname}" title="账户姓名" required></span></span>
		    </div>
	    </div>
		<div class="info pd" id="bankaccountcname_error" style="display:none">
			<div class="tbl-type">
		    	<span class="tbl-cell w100"><span style="color:red">账户姓名不能为空。</span></span>
		    </div>
		</div>
		
		<div class="info pd">
		<div class="tbl-type">
	    	<span class="tbl-cell w70"><span>账户关联电话：</span></span>
	        <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="bankaccountphone" id="bankaccountphone" value="${obj.member.bankaccountphone}"></span></span>
	    </div>
	    </div>
		<div class="info pd" id="bankaccountphone_error" style="display:none">
			<div class="tbl-type">
		    	<span class="tbl-cell w100"><span style="color:red">账户关联电话不能为空。</span></span>
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
    </div>
    <div class="shp-cart-info">
    </div>
    <a class="btn-right-block" id="submit" style="width:150px;background-color: rgb(192, 0, 0); background-position: initial initial; background-repeat: initial initial;">保存资料</a>
</div>
</div>

<script type="text/javascript">
$(function(){
	
	$("#submit").click(function() {page_save()});
	
	$("#bybase").show();
	$("li>a").click(function(){
		if($('#fixed').has('nav-fixed')){
			$('#fixed').removeClass('nav-fixed');
		}
		$(".detail").hide();
		$("li>a").removeClass("on");
		$(this).addClass("on");
		var id=$(this).attr("value");
		$("#"+id).show();
		eval("page_show"+id+"();");
	});
	page_showbybase();
});

function page_showbybase()
{
}

function page_showbyaddr()
{
}

function page_showbybank()
{
}

function page_save()
{
	// 检查字段完整性
	var fields = $('#form_info input,textarea');
	for(i=0;i<fields.length;i++)
	{
		if(fields[i].required==true)
		{
			if(fields[i].value=='')
			{
				//alert(fields[i].title+"不能为空。");
				//eval(fields[i].name+'_error.style.display="block";');
				//return;
			}
			else
			{
				// eval(fields[i].name+'_error.style.display="none";');
			}
		}
	}
	
	$.ajax({
		type:'POST',
		url:'${base}/member/member/myinfo/saveinfo.action',
		data:$("#form_info").serialize(),
		cache:false,
		async:true,
		success:function(data)
		{
			console.log(data);
			if(data=="")
			{
				alert("保存个人资料异常，请检查后再试试！");
				return;
			}
			json = eval("(" + data + ")");
			if(json.state=="success")
			{
				alert("保存个人资料成功！")
			}
			else
			{
				alert("保存个人资料失败："+json.message);
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

<script>	
$(".tabs>div").click(function(){
	$(".detail").each(function(){
		$(this).hide();
	});
	
	$(".tabs>div").removeClass("active");
	$(this).addClass("active");

	var type = $(this).attr("type");
	
	document.getElementById("by"+type).style.display="block";
});


</script>


</body>
</html>