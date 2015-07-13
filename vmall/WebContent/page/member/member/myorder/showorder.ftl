<#list obj.orders as order>

<li id="order${order.id}">
    <div class="items" id="items${order.id}" name="item">
		<div class="check-wrapper">
			<span style="color:#6e6e6e">
	            <p><span>&nbsp;</span></p>
	            <p><span>${order_index+1}</span></p>	
			</span>
        </div>
        <div class="shp-cart-item-core"  style="color:#dedede;font-size:10px">
        
        	<div class="cart-product-cell-2">
                <p>
                <span style="color:#6e6e6e;font-size:16px;"><a href="${base}/order/order/look.action?id=${order.id}">${order.cno}</a></span>
                <sapn>&nbsp;&nbsp;</span>
                <span>${order.ordertime?datetime?string("HH:mm")}</span>
                </p>
                <p><span>${order.sellername}</span></p>
                <p><span>${order.takeaddress}</span></p>
       		</div>
       		
			<div class="cart-product-cell-3">
        		<p>￥<span style="color:#ff6666;font-size:16px;">${order.amount?number?string("0.00")}</span></p>
        		<p><span>${order.paystate}</span></p>
        		<p><span>${order.state}</span></p>
	       	</div>       		
        </div>
	</div>           
</li>
</#list>






<#--
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
-->