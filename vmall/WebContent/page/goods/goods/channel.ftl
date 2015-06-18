<!DOCTYPE html>
<html>
<head>
<#include "/decorator/include/header.ftl">
<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/base.css" charset="gbk">
<link rel="stylesheet" type="text/css" href="${base}/lib/jd/order/css/extend.css" charset="gbk">

<link href="${base}/lib/jd/cart/css/title-bar.css" media="all" rel="stylesheet" type="text/css">
<link href="${base}/lib/jd/cart/css/cart/btn.css" media="all" rel="stylesheet" type="text/css">
<link href="${base}/lib/jd/cart/css/checkbox.css" media="all" rel="stylesheet" type="text/css">
<link href="${base}/lib/jd/cart/css/spinner.css" media="all" rel="stylesheet" type="text/css">
<link href="${base}/lib/jd/cart/css/shopping-cart.css" media="all" rel="stylesheet" type="text/css">
</head>
<body id="body">
<#include "/decorator/include/navmain.ftl">
<div class="viewport">
	<div data-spm="${obj.goodsclass.internal}" class="content has-header">
		<section data-spm="${obj.goodsclass.internal}" class="list-keys">
		<#list obj.subgoodsclasses as subclass>
			<a href="#" index="0" querykey="${subclass.cname}" data-spm-click="" class="item active">${subclass.cname}</a>
		</#list>		
		</section>
		<section class="relate-collect hide"></section>
		<section class="relate-style hide"></section>
		<section class="waterfall">
		<div class="waterfall-list">
			<div class="waterfall-column" style="width:50%;" id="waterfall-column1">
			</div>
			
			<div class="waterfall-column" style="width:50%;" id="waterfall-column2">
			</div>			
		</div>
	</div>
    <div id="payment_p" style="display:block">
    <div id="paymentp"></div>
    <div class="payment-total-bar" id="payment">
        <div class="shp-chk">
            <span onclick="checkAllHandler();" class="cart-checkbox checked" id="checkIcon-1"></span>
        </div>
        <div class="shp-cart-info">
        </div>
        <a class="btn-right-block" id="submit" style="background-color: rgb(192, 0, 0); background-position: initial initial; background-repeat: initial initial;">显示更多</a>
    </div>
</div>	


<script>

var _page = 1;
var _pagesize = 20;
var _maxpage = "N";

function pullUpAction () 
{
	
	if(_maxpage == "Y")
	{
		return;
	}
	
	$.ajax({
		type:'post',
		url:'${base}/goods/goods/channelshow.action',
		contentType: "application/json",
		data:JSON.stringify({"classid":"${obj.goodsclass.id}","_page":_page,"_pagesize":_pagesize}),
		cache:false,
		async:true,
		success:function(data)
		{
			if(data=="")
			{
				alert("加载数据异常！");
				return;
			}
			json = eval("(" + data + ")");
			
			var goodses = json.goodses;
			console.log(goodses);
			
			if(goodses.length==0)
			{
				_maxpage = "Y";
			}
			
			var goods1;
			var goods2;
			var len = goodses.length;
			if(goodses.length%2==1)
			{
				len = goodses.length - 1;
			}
			console.log("len:" + len);
			console.log("goodes len:" + goodses.length);				
			for(var i=0;i<len;i++)
			{
				goods1 = goodses[i];
				
				var html = '';
				
				html += '<a href="/vmall/goods/goods/look.action?id="'+goods1.id+'" class="waterfall-item auction">';
				html += '<img style="display:block;" class="" src="/vmall/image/goods/demogoods1.jpg">';
				html += '<h3>'+goods1.cname+'</h3>';
				html += '<p>¥<span class="promote-price">'+goods1.promoteprice+'</span>¥<del class="cost-price">'+goods1.saleprice+'</del></p>';
				html += '<p><span class="info-sum">月销：470</span><span class="info-freight">免运费</span></p>';
				html += '</a>';
				
				$('#waterfall-column1').append(html);
				
				
				goods2 = goodses[i+1];
				
				html = '';
				

				html += '<a href="/vmall/goods/goods/look.action?id="'+goods2.id+'" class="waterfall-item auction">';
				html += '<img style="display:block;" class="" src="/vmall/image/goods/demogoods1.jpg">';
				html += '<h3>'+goods2.cname+'</h3>';
				html += '<p>¥<span class="promote-price">'+goods2.promoteprice+'</span>¥<del class="cost-price">'+goods2.saleprice+'</del></p>';
				html += '<p><span class="info-sum">月销：470</span><span class="info-freight">免运费</span></p>';
				html += '</a>';
				
				$('#waterfall-column2').append(html);
				
				i++;
			
			}
			
			_page = _page + 1;
			
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})	
}

$("#submit").click(function() {pullUpAction()});

pullUpAction();

</script>

</body>
</html>