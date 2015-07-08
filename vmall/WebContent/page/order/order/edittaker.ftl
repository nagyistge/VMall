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
                    <span class="new-input-span">
                        <span class="new-sel-box new-p-re">
                            <label id="province_label">${obj.order.takeprovince}</label>
                            <select name="takeprovince" id="takeprovince" style="width:200px;"><option id="option_add_1" value="1">北京</option><option id="option_add_2" value="2">上海</option><option id="option_add_3" value="3">天津</option><option id="option_add_4" value="4">重庆</option><option id="option_add_5" value="5">河北</option><option id="option_add_6" value="6">山西</option><option id="option_add_7" value="7">河南</option><option id="option_add_8" value="8">辽宁</option><option id="option_add_9" value="9">吉林</option><option id="option_add_10" value="10">黑龙江</option><option id="option_add_11" value="11">内蒙古</option><option id="option_add_12" value="12">江苏</option><option id="option_add_13" value="13">山东</option><option id="option_add_14" value="14">安徽</option><option id="option_add_15" value="15">浙江</option><option id="option_add_16" value="16">福建</option><option id="option_add_17" value="17">湖北</option><option id="option_add_18" value="18">湖南</option><option id="option_add_19" value="19">广东</option><option id="option_add_20" value="20">广西</option><option id="option_add_21" value="21">江西</option><option id="option_add_22" value="22">四川</option><option id="option_add_23" value="23">海南</option><option id="option_add_24" value="24">贵州</option><option id="option_add_25" value="25">云南</option><option id="option_add_26" value="26">西藏</option><option selected="" id="option_add_27" value="27">陕西</option><option id="option_add_28" value="28">甘肃</option><option id="option_add_29" value="29">青海</option><option id="option_add_30" value="30">宁夏</option><option id="option_add_31" value="31">新疆</option><option id="option_add_32" value="32">台湾</option><option id="option_add_42" value="42">香港</option><option id="option_add_43" value="43">澳门</option><option id="option_add_84" value="84">钓鱼岛</option></select>
                        </span>
                    </span>
                </span>
            </div>
        </div>
        
        <div class="info pd">
        	<div class="tbl-type">
            	<span class="tbl-cell w50"><span>城市：</span></span>
                <span class="tbl-cell">
                	<span class="new-input-span">
                        <span class="new-sel-box new-p-re">
                            <label id="city_label">${obj.order.takecity}</label>
                            <select name="takecity" id="takecity" style="width:200px;"><option selected="" id="option_add_2376" value="2376">西安市</option><option id="option_add_2386" value="2386">铜川市</option><option id="option_add_2390" value="2390">宝鸡市</option><option id="option_add_2402" value="2402">咸阳市</option><option id="option_add_2416" value="2416">渭南市</option><option id="option_add_2428" value="2428">延安市</option><option id="option_add_2442" value="2442">汉中市</option><option id="option_add_2454" value="2454">榆林市</option><option id="option_add_2468" value="2468">商洛市</option><option id="option_add_2476" value="2476">安康市</option></select>
                        </span>
                    </span>
                </span>
            </div>
        </div>
        
        <div class="info pd">
        	<div class="tbl-type">
            	<span class="tbl-cell w50"><span>区县：</span></span>
                <span class="tbl-cell">
                	<span class="new-input-span">
                        <span class="new-sel-box new-p-re">
                            <label id="area_label">${obj.order.takecounty}</label>
                            <select name="takecounty" id="takecounty" style="width:200px;"><option id="option_add_51881" value="51881">新城区</option><option selected="" id="option_add_4343" value="4343">雁塔区</option><option id="option_add_50230" value="50230">未央区</option><option id="option_add_50231" value="50231">长安区</option><option id="option_add_50232" value="50232">灞桥区</option><option id="option_add_50233" value="50233">碑林区</option><option id="option_add_50235" value="50235">莲湖区</option><option id="option_add_50236" value="50236">临潼区</option><option id="option_add_50237" value="50237">阎良区</option><option id="option_add_50238" value="50238">杨凌农业示范区</option><option id="option_add_2380" value="2380">高陵县</option><option id="option_add_2381" value="2381">蓝田县</option><option id="option_add_2382" value="2382">户县</option><option id="option_add_2383" value="2383">周至县</option></select>
                        </span>
                    </span>
                </span>
            </div>
        </div>
		
        <div class="info pd" id="taketown" style="display: none;">
        	<div class="tbl-type">
            	<span class="tbl-cell w50"><span>乡镇：</span></span>
                <span class="tbl-cell">
                	<span class="new-input-span">
                        <span class="new-sel-box new-p-re">
                            <label id="town_label"></label>
                            <select class="new-select" name="taketown" id="taketown" style="width:200px;"></select>
                        </span>
                    </span>
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