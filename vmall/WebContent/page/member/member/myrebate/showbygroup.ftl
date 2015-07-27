<#list obj.rebates as rebate>

<li id="rebate${rebate.submemberid}">
    <div class="items" id="items${rebate.submemberid}" name="item">
		<div class="check-wrapper">
			<span style="color:#dedede">
	            <p></p>
	            <p></p>
	            <p></p>
	            <p>${rebate_index+1}</p>	
			</span>
        </div>
        <div class="shp-cart-item-core">
            <a class="cart-product-cell-1">
                <img class="cart-photo-thumb" alt="" src="<#if rebate.wxheadimgurl=="">${base}/css/img/default.png<#else>${rebate.wxheadimgurl}</#if>">
            </a>
            
            <div class="cart-product-cell-2">
                <div class="cart-product-name">
                    <p><span style="font-size:16px">&nbsp;${rebate.cname}</span></p>
                    <p><span style="font-size:16px">&nbsp;${rebate.wxnickname}</span></p>
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