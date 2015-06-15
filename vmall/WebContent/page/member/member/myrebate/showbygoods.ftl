<#list obj.rebates as rebate>
<li id="rebate${rebate.goodsid}">
<div>
    <div>
 		<span style="margin-right:20px;font-size:12px;color:#cecece">${rebate.cname}</span> 	
 		<span>￥<span style="margin-right:20px;font-size:20px;color:#ff6666">${rebate.score}</span></span>
    </div>
</div>
</li>
</#list>