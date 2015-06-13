<#list obj.orders as order>
<li id="order${order.cno}">
<div class="items">
    <div class="check-wrapper">
        <span id="checkIcon1196557" class="cart-checkbox checked"></span>
    </div>
    <div>
 		<span style="margin-right:10px;font-size:18px"><a href="javascript:void(0)" onclick="page_loadordergoods('${order.id}')" style="color:#cecdce">${order.cno}</a></span>	
 		<span style="margin-right:10px;color:#cecdce">${(order.ordertime?datetime?string("HH:mm"))!''}</span>
 		<span style="margin-right:10px;color:#cecdce">${order.state}</span> 		
 		<span>￥<span style="margin-right:20px;font-size:24px;color:#ff6666">${order.amount}</span></span>
 		<span><a href="${base}/order/order/look.action?id=${order.id}" style="color:#cecece">详细</a></span>
 		<a class="shp-cart-icon-remove" href="javascript:void(0)" onclick="page_deleteorder('${order.id}')"></a>		
    </div>
</div>
<div id="ordergoods${order.id}">
</div>
</li>
</#list>