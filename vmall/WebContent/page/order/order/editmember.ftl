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
<body id="body">

<form action="${base}/order/order/savetaker.action" method="POST" id="form_edittaker">
<input type="hidden" id="id" name="id" value="${obj.order.id}">
<input type="hidden" id="cno" name="cno" value="${obj.order.cno}">
<input type="hidden" id="state" name="state" value="${obj.order.state}">

<div class="new-ct shouhuo">
    <div class="title">购买人信息</div>
    <div class="info-list">
         <div class="info pd">
        	<div class="tbl-type">
            	<span class="tbl-cell w100"><span>购买人编号：</span></span>
                <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="membercno" id="membercno" value="${obj.order.membercno}" style="width:100%;" readonly></span></span>
            </div>
        </div>
		<div class="info pd" id="membercno_error" style="display:none">
        	<div class="tbl-type">
            	<span class="tbl-cell w80"><span style="color:red">购买人编号不能为空，请检查个人资料是否完整后，重新下单。</span></span>
            </div>
        </div>   
    
        <div class="info pd">
        	<div class="tbl-type">
            	<span class="tbl-cell w100"><span>购买人姓名：</span></span>
                <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="membercname" id="membercname" value="${obj.order.membercname}" style="width:100%;" readonly></span></span>
            </div>
        </div>
		<div class="info pd" id="membercname_error" style="display:none">
        	<div class="tbl-type">
            	<span class="tbl-cell w80"><span style="color:red">购买人姓名不能为空，请检查个人资料是否完整后，重新下单。</span></span>
            </div>
        </div>        

        <div class="info pd">
        	<div class="tbl-type">
            	<span class="tbl-cell w100"><span>购买人电话：</span></span>
                <span class="tbl-cell"><span><input type="text" maxlength="11" class="new-input" name="phone" id="phone" value="${obj.order.phone}" style="width:100%;"></span></span>
            </div>
        </div>
        <div class="info pd" id="phone_error" style="display: none;">
        	<div class="tbl-type">
            	<span class="tbl-cell w80"><span style="color:red">购买人联系电话不能为空。</span></span>
            </div>
        </div>
    </div>
		<div class="info pd" id="takeaddress_error" style="display:none">
        	<div class="tbl-type">
            	<span class="tbl-cell w80"><span style="color:red">收货地址不能为空。</span></span>
            </div>
        </div>
    <!--[D] 默认时加  new-abtn-default 把a标签换成span-->
    <span class="new-abtn-type mgn" onclick="page_submiteditform()">保存</span>	
</div>
</form>

<script>
function page_submiteditform()
{
	if($("#membercno").val()=="")
	{
		membercno_error.style.display = "block";
		return;	
	}
	membercno_error.style.display = "none";

	if($("#membercname").val()=="")
	{
		membercname_error.style.display = "block";
		return;	
	}
	membercname_error.style.display = "none";

	if($("#phone").val()=="")
	{
		phone_error.style.display = "block";
		return;	
	}
	phone_error.style.display = "none";
	
	var id = $("#id").val();
	var membercname = $("#membercname").val();
	var phone = $("#phone").val();
	
	$.ajax({
		type:'POST',
		url:'${base}/order/order/savetaker.action',
		contentType: "application/json",
		data:JSON.stringify({"id":id,"membercname":membercname,"phone":phone}),
		cache:false,
		async:false,
		success:function(data)
		{
			console.log(data);
			if(data=="")
			{
				alert("保存收货人异常，请再试试！");
				return;
			}
			json = eval("(" + data + ")");
			if(json.state=="success")
			{
				window.location = "${base}/order/order/look.action?id="+id;
			}
			else
			{
				alert("保存收货人失败！");
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