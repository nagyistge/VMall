<#list obj.orders as order>

<li id="order${order.id}">
    <div class="items" id="items${order.id}" name="item">
		<div class="check-wrapper">
			<span style="color:#6e6e6e">
	            <p><span>&nbsp;</span></p>
	            <p><span>${order_index+1}</span></p>	
			</span>
        </div>
        <div class="shp-cart-item-core"  style="color:#dedede;font-size:12px">
        
        	<div class="cart-product-cell-2">
                <p>
                <span style="color:#6e6e6e"><a href="${base}/order/order/look.action?id=${order.id}">${order.cno}</a></span>
                <sapn>&nbsp;&nbsp;</span>
                <span>${order.ordertime?datetime?string("HH:mm")}</span>
                </p>
                <p><span>${order.sellername}</span></p>
                <p><span>${order.takeaddress}</span></p>
       		</div>
       		
			<div class="cart-product-cell-3">
        		<p>￥<span style="color:#ff6666;">${order.amount?number?string("0.00")}</span></p>
        		<p><span>${order.paystate}/${order.state}</span></p>
        		<p><span><#if order.state=="下单"><a class="order-icon-remove" style="color:#6e6eff" sid="${order.id}">删除</a></#if></span></p>
	       	</div>       		
        </div>
	</div>           
</li>
</#list>

<script>
$(".order-icon-remove").click(function() {
	var sid = $(this).attr("sid");
	$.ajax({
		type:'POST',
		url:'${base}/order/order/delete.action',
		data:{id:sid},
		cache:false,
		success:function(data)
		{
			if(data=="")
			{
				alert("删除异常，请检查后再试试！");
				return;
			}
			json = eval("(" + data + ")");
			if(json.state=="success")
			{
				window.location.reload();
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
			// window.location.reload();
		}
	})
});
</script>