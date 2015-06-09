<#list obj.members as member>
<li id="member${member.id}">
<div class="items">
    <div>
		<a class="cart-product-cell-1">
			<img class="cart-photo-thumb" alt="" src="${base}/css/img/default.png" onerror="${base}/css/img/default.png">
        </a>
 		<span style="margin-right:20px;font-size:18px">${member.cname}</span> 	
 		<span id="subcount${member.id}" class="subcount" style="margin-right:10px;font-size:12px;color:#9e9ece"></span>	
 		<span>￥<span style="margin-right:20px;font-size:20px;color:#ff6666"></span></span>
    </div>
</div>
</li>
</#list>