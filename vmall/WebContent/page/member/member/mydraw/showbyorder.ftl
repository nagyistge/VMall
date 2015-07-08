<#list obj.rebates as rebate>

<li id="rebate${rebate.orderid}">
    <div class="items" id="items${rebate.orderid}" name="item">
		<div class="check-wrapper">
			<span style="color:#dedede">
	            <p>${rebate_index+1}</p>	
			</span>
        </div>
        <div class="shp-cart-item-core">
            <div class="cart-product-cell-2" style="width:80%">
                <div class="cart-product-name">
                    <p>
                    <span style="font-size:16px;">${rebate.cno}</span><span>&nbsp;&nbsp;</span>
                    <span style="color:#dedede">${rebate.ordertime!?datetime?string("MM-dd HH:mm")}</span>
                    </p>
                    <p><span style="color:#dedede">${rebate.membercname}</span></p>
           		</div>
       		</div>
       		
       		<div class="cart-product-cell-3">
        		<p><span class="shp-cart-item-price" style="font-size:18px;color:#ff6666">${rebate.score!?number}</span>
        		<p><span class="shp-cart-item-price" style="font-size:10px"></span>
                <p><span class="shp-cart-item-price" style="font-size:10px"></span>
        	</div>
        </div>    
	</div>           
</li>
</#list>