<#list obj.rebates as rebate>
<li id="rebate${rebate.submemberid}">
<div>
    <div>
		<a>
			<img class="cart-photo-thumb" alt="" src="${base}/css/img/default.png" onerror="${base}/css/img/default.png">
        </a>
 		<span style="margin-right:20px;font-size:12px;color:#cecece">${rebate.cname}</span> 	
 		<span id="subcount${rebate.submemberid}" class="subcount" style="margin-right:10px;font-size:12px;color:#9e9ece"></span>	
 		<span>￥<span style="margin-right:20px;font-size:20px;color:#ff6666">${rebate.score}</span></span>
    </div>
</div>
</li>
</#list>