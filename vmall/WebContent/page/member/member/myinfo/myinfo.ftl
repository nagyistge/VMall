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
	<p style="font-size:10px;color:#aeaeae;height:30px;"></p>
	<nav data-spm="1006" class="tabs">
		<div type="base" data-sort="" data-spm-click="" class="tab-item active" data-spm-anchor-id="">个人</div>
		<div type="addr" data-sort="" data-spm-click="" class="tab-item" data-spm-anchor-id="">地址</div>
		<div type="bank" data-sort="" data-spm-click="" class="tab-item" data-spm-anchor-id="">账号</div>
	</nav>
	
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
<div class="detail" id="byaddr" style="display: none;">

	<div class="info-list">

		<div class="info pd">
		<div class="tbl-type">
			<span class="tbl-cell w70"><span>省份：</span></span>
		    <span class="tbl-cell">
		    	<span>
		        <select name="province" id="province" style="width:200px"><option selected="" id="option_add_1" value="1">北京</option><option id="option_add_2" value="2">上海</option><option id="option_add_3" value="3">天津</option><option id="option_add_4" value="4">重庆</option><option id="option_add_5" value="5">河北</option><option id="option_add_6" value="6">山西</option><option id="option_add_7" value="7">河南</option><option id="option_add_8" value="8">辽宁</option><option id="option_add_9" value="9">吉林</option><option id="option_add_10" value="10">黑龙江</option><option id="option_add_11" value="11">内蒙古</option><option id="option_add_12" value="12">江苏</option><option id="option_add_13" value="13">山东</option><option id="option_add_14" value="14">安徽</option><option id="option_add_15" value="15">浙江</option><option id="option_add_16" value="16">福建</option><option id="option_add_17" value="17">湖北</option><option id="option_add_18" value="18">湖南</option><option id="option_add_19" value="19">广东</option><option id="option_add_20" value="20">广西</option><option id="option_add_21" value="21">江西</option><option id="option_add_22" value="22">四川</option><option id="option_add_23" value="23">海南</option><option id="option_add_24" value="24">贵州</option><option id="option_add_25" value="25">云南</option><option id="option_add_26" value="26">西藏</option><option id="option_add_27" value="27">陕西</option><option id="option_add_28" value="28">甘肃</option><option id="option_add_29" value="29">青海</option><option id="option_add_30" value="30">宁夏</option><option id="option_add_31" value="31">新疆</option><option id="option_add_32" value="32">台湾</option><option id="option_add_42" value="42">香港</option><option id="option_add_43" value="43">澳门</option><option id="option_add_84" value="84">钓鱼岛</option></select>
		        </span>
		    </span>
		</div>
		</div>
		
		<div class="info pd">
		<div class="tbl-type">
		<span class="tbl-cell w70"><span>市：</span></span>
		<span class="tbl-cell">
			<span>
		    <select name="city" id="city" style="width:200px"><option selected="" id="option_add_2376" value="2376">西安市</option><option id="option_add_2386" value="2386">铜川市</option><option id="option_add_2390" value="2390">宝鸡市</option><option id="option_add_2402" value="2402">咸阳市</option><option id="option_add_2416" value="2416">渭南市</option><option id="option_add_2428" value="2428">延安市</option><option id="option_add_2442" value="2442">汉中市</option><option id="option_add_2454" value="2454">榆林市</option><option id="option_add_2468" value="2468">商洛市</option><option id="option_add_2476" value="2476">安康市</option></select>
		    </span>
		</span>
		</div>
		</div>
		
		<div class="info pd">
		<div class="tbl-type">
		<span class="tbl-cell w70"><span>县区：</span></span>
		<span class="tbl-cell">
			<span>
		    <select name="county" id="county" style="width:200px;"><option id="option_add_51881" value="51881">新城区</option><option selected="" id="option_add_4343" value="4343">雁塔区</option><option id="option_add_50230" value="50230">未央区</option><option id="option_add_50231" value="50231">长安区</option><option id="option_add_50232" value="50232">灞桥区</option><option id="option_add_50233" value="50233">碑林区</option><option id="option_add_50235" value="50235">莲湖区</option><option id="option_add_50236" value="50236">临潼区</option><option id="option_add_50237" value="50237">阎良区</option><option id="option_add_50238" value="50238">杨凌农业示范区</option><option id="option_add_2380" value="2380">高陵县</option><option id="option_add_2381" value="2381">蓝田县</option><option id="option_add_2382" value="2382">户县</option><option id="option_add_2383" value="2383">周至县</option></select>
		    </span>
		</span>
		</div>
		</div>
		
		<div class="info pd">
		<div class="tbl-type">
		<span class="tbl-cell w70"><span>乡镇：</span></span>
		<span class="tbl-cell">
			<span>
		    <select name="town" id="town" style="width:200px;"><option id="option_add_51881" value="51881">新城区</option><option selected="" id="option_add_4343" value="4343">雁塔区</option><option id="option_add_50230" value="50230">未央区</option><option id="option_add_50231" value="50231">长安区</option><option id="option_add_50232" value="50232">灞桥区</option><option id="option_add_50233" value="50233">碑林区</option><option id="option_add_50235" value="50235">莲湖区</option><option id="option_add_50236" value="50236">临潼区</option><option id="option_add_50237" value="50237">阎良区</option><option id="option_add_50238" value="50238">杨凌农业示范区</option><option id="option_add_2380" value="2380">高陵县</option><option id="option_add_2381" value="2381">蓝田县</option><option id="option_add_2382" value="2382">户县</option><option id="option_add_2383" value="2383">周至县</option></select>
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
<div class="detail" id="bybank" style="display: none;">

	<div class="info-list">

		<div class="info pd">
			<div class="tbl-type">
		    	<span class="tbl-cell w70"><span>所属银行：</span></span>
                <span class="tbl-cell">
	            	<span>
	                <select name="bank" id="bank" style="width:200px">
	                <option selected="" id="option_add_1" value="1">中国工商银行</option>
	                <option id="option_add_2" value="2">中国建设银行</option>
	                <option id="option_add_3" value="3">中国交通银行</option>
	                <option id="option_add_4" value="4">中国农业银行</option>
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
	        <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="bankaccountphone" id="bankaccountphone" value="${obj.member.bankaccountphone}1234"></span></span>
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
		async:false,
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