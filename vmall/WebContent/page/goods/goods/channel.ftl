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
<body id="body" style="background:#ffffff">
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
		
		<nav data-spm="1006" class="tabs">
			<div type="popular" data-sort="popular_desc" data-spm-click="" class="tab-item popular_desc active" data-spm-anchor-id="">人气</div>
			<div type="sales" data-sort="sales_desc" data-spm-click="" class="tab-item  sales_desc" data-spm-anchor-id="">销量</div>
			<div type="saleprice" data-sort="price_asc:price_desc" data-spm-click="" class="tab-item sort-price price_asc" data-spm-anchor-id="">价格
	        <b class="asc arrow arrow-top"></b><b class="desc arrow arrow-bottom"></b>
	        </div>
	    </nav>		
		
		<div class="waterfall-list">
			<div class="waterfall-column" style="width:50%;" id="waterfall-column1">
			</div>
			
			<div class="waterfall-column" style="width:50%;" id="waterfall-column2">
			</div>			
		</div>
		
		</section>
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

function page_showgoods (type, asc) 
{
	if(_maxpage == "Y")
	{
		return;
	}
	
	
	$.ajax({
		type:'post',
		url:'${base}/goods/goods/channelshow.action',
		contentType: "application/json",
		data:JSON.stringify({"classid":"${obj.goodsclass.id}","_orderby":type,"_order":asc,"_page":_page,"_pagesize":_pagesize}),
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

			for(var i=0;i<len;i++)
			{
				goods1 = goodses[i];
				
				var html = '';
				html += '<a href="${base}/goods/goods/look.action?id='+goods1.id+'" class="waterfall-item auction">';
				html += '<img style="display:block;" class="" src="${base}/'+goods1.pic+'">';
				html += '<h3 style="font-size:10px;color:#8e8e8e">'+goods1.cname+'</h3>';
				html += '<p>¥<span class="promote-price">'+goods1.promoteprice+'</span>¥<del class="cost-price">'+goods1.saleprice+'</del></p>';
				html += '<p><span class="info-sum">人气：'+goods1.popular+'　　月销：'+goods1.sales+'</span><span class="info-freight">免运费</span></p>';
				html += '</a>';
				
				$('#waterfall-column1').append(html);
				
				goods2 = goodses[i+1];
				
				html = '';
				html += '<a href="${base}/goods/goods/look.action?id='+goods2.id+'" class="waterfall-item auction">';
				html += '<img style="display:block;" class="" src="${base}/'+goods2.pic+'">';
				html += '<h3 style="font-size:10px;color:#8e8e8e">'+goods2.cname+'</h3>';
				html += '<p>¥<span class="promote-price">'+goods2.promoteprice+'</span>¥<del class="cost-price">'+goods2.saleprice+'</del></p>';
				html += '<p><span class="info-sum">人气：'+goods2.popular+'　　月销：'+goods2.sales+'</span><span class="info-freight">免运费</span></p>';
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

$("#submit").click(function() {page_showgoods()});
$(".tabs>div").click(function(){
	
	$(".tabs>div").removeClass("active");
	$(this).addClass("active");

	$("#waterfall-column1").html("");
	$("#waterfall-column2").html("");
	
	_page = 1;
	_maxpage = "N";
	
	var type=$(this).attr("type");
	var asc = "desc";	
	
	eval('page_showgoods("'+type+'","'+asc+'");');
});

page_showgoods("popular", "desc");

</script>

</body>
</html>