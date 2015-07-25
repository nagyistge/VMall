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
    <div class="title">收货人地址</div>
    <div class="info-list">
        <div class="info pd">
        	<div class="tbl-type">
            	<span class="tbl-cell w100"><span>收货人姓名：</span></span>
                <span class="tbl-cell"><span><input type="text" maxlength="50" class="new-input" name="takercname" id="takercname" value="${obj.order.takercname}" style="width:100%;"></span></span>
            </div>
        </div>
		<div class="info pd" id="takercname_error" style="display:none">
        	<div class="tbl-type">
            	<span class="tbl-cell w80"><span style="color:red">收货人姓名不能为空。</span></span>
            </div>
        </div>        

        <div class="info pd">
        	<div class="tbl-type">
            	<span class="tbl-cell w100"><span>收货人手机号：</span></span>
                <span class="tbl-cell"><span><input type="text" maxlength="11" class="new-input" name="takermobile" id="takermobile" value="${obj.order.takermobile}" style="width:100%;"></span></span>
            </div>
        </div>
        <div class="info pd" id="takermobile_error" style="display: none;">
        	<div class="tbl-type">
            	<span class="tbl-cell w80"><span style="color:red">收货人联系电话不能为空。</span></span>
            </div>
        </div>
		<div class="info pd">
        	<div class="tbl-type">
            	<span class="tbl-cell w50"><span>省份：</span></span>
                <span class="tbl-cell"> 
                	<#--               	
                    <span class="new-input-span">
                        <span class="new-sel-box new-p-re">
                            <label id="province_label">${obj.order.takeprovince}</label>
                        </span>
                    </span>
                    -->
                    <span><input type="text" maxlength="50" class="new-input" name="takeprovince" id="takeprovince" value="${obj.order.takeprovince}" style="width:100%;"></span>
                </span>
            </div>
        </div>
        
        <div class="info pd">
        	<div class="tbl-type">
            	<span class="tbl-cell w50"><span>城市：</span></span>
                <span class="tbl-cell">
                    <span><input type="text" maxlength="50" class="new-input" name="takecity" id="takecity" value="${obj.order.takecity}" style="width:100%;"></span>
                </span>
            </div>
        </div>
        
        <div class="info pd">
        	<div class="tbl-type">
            	<span class="tbl-cell w50"><span>区县：</span></span>
                <span class="tbl-cell">
					<span><input type="text" maxlength="50" class="new-input" name="takecounty" id="takecounty" value="${obj.order.takecounty}" style="width:100%;"></span>
                </span>
            </div>
        </div>
		
        <div class="info pd" id="taketown" style="display: none;">
        	<div class="tbl-type">
            	<span class="tbl-cell w50"><span>乡镇：</span></span>
                <span class="tbl-cell">
	 				<span><input type="text" maxlength="50" class="new-input" name="taketown" id="taketown" value="${obj.order.taketown}" style="width:100%;"></span>
                </span>
            </div>
        </div>
        
        <div class="info border-b-none pd">
        	<div class="tbl-type">
            	<span class="tbl-cell w70"><span>地址信息：</span></span>
                <span class="tbl-cell"><span><label id="address_label"></label>
                <textarea rows="2" style="height:auto;width:100%;" type="text" class="new-input" name="takeaddress" id="takeaddress">${obj.order.takeaddress}</textarea></span></span>
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
	if($("#takercname").val()=="")
	{
		takercname_error.style.display = "block";
		return;	
	}
	takercname_error.style.display = "none";

	if($("#takermobile").val()=="")
	{
		takermobile_error.style.display = "block";
		return;	
	}
	takermobile_error.style.display = "none";
	
	if($("#takeaddress").val()=="")
	{
		takeaddress_error.style.display = "block";
		return;	
	}
	takeaddress_error.style.display = "none";			
	
	var id = $("#id").val();
	var takercname = $("#takercname").val();
	var takermobile = $("#takermobile").val();
	var takeprovince = $("#takeprovince").val();
	var takecity = $("#takecity").val();
	var takecounty = $("#takecounty").val();
	var taketown = $("#taketown").val();
	var takepostcode = $("#takepostcode").val();
	var takeaddress = $("#takeaddress").val();	
	
	$.ajax({
		type:'POST',
		url:'${base}/order/order/savetaker.action',
		contentType: "application/json",
		data:JSON.stringify({"id":id,"takercname":takercname,"takermobile":takermobile,"takeprovince":takeprovince,"takecity":takecity,"takecounty":takecounty,"taketown":taketown,"takepostcode":takepostcode,"takeaddress":takeaddress}),
		cache:false,
		async:true,
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