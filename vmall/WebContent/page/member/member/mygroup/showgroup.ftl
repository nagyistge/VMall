<#list obj.members as member>

<li id="member${member.id}">
    <div class="items" id="items${member.id}" name="item">
		<div class="check-wrapper">
			<span style="color:#dedede">
	            <p>&nbsp;</p>
	            <p>${member_index+1}</p>	
			</span>
        </div>
        <div class="shp-cart-item-core">
            <a class="cart-product-cell-1" href="${base}/member/member/mygroup/lookother.action?id=${member.id}">
                <img class="cart-photo-thumb" alt="" src="${base}/css/img/default.png">
            </a>
            
            <div class="cart-product-cell-2">
                <div class="cart-product-name">
                    <p>
                    <span style="font-size:18px;color:#dedede"><#if member.cname=="">无名<#else>${member.cname}</#if></span>
                    <span style="color:#dedede">${member.phone}</span>
                    </p>
					<p>&nbsp;</p>
					<p>
	                <span style="color:#dedede">${member.cno}</span>
                    <span>&nbsp;&nbsp;</span>                    
                    <span style="color:#dedede">${member.createtime!?datetime?string("yyyy-MM-dd")}</span>
                    </p>                   
           		</div>
       		</div>
       		
       		<div class="cart-product-cell-3">
       			<p><span id="subcount${member.id}" class="subcount" style="margin-right:10px;font-size:18px;color:#9e9ece"></p>
       			<p>&nbsp;</p>
        	</div>
            
        </div>    

	</div>           
</li>
</#list>
