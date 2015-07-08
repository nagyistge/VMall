<#list obj.rebates as rebate>

<li id="rebate${rebate.goodsid}">
    <div class="items" id="items${rebate.goodsid}" name="item">
		<div class="check-wrapper">
			<span style="color:#dedede">
	            <p></p>
	            <p></p>
	            <p></p>
	            <p>${rebate_index+1}</p>	
			</span>
        </div>
        <div class="shp-cart-item-core">
            <a class="cart-product-cell-1" href="${base}/member/member/look.action?id=${rebate.goodsid}">
                <img class="cart-photo-thumb" alt="" src="${base}/${rebate.pic}">
            </a>
            
            <div class="cart-product-cell-2">
                <div class="cart-product-name">
                    <p><span>${rebate.cname}</span></p>
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