<#list obj.draws as draw>
<li id="draw${draw.cno}">
<div class="items">
    <div class="check-wrapper">
        <span id="checkIcon1196557" class="cart-checkbox checked"></span>
    </div>
    <div>
 		<span style="margin-right:10px;font-size:18px"><a href="javascript:void(0)" onclick="page_loaddrawgoods('${draw.id}')" style="color:#8c8cdc">${draw.cno}</a></span>	
 		<span style="margin-right:10px">${(draw.applytime?datetime?string("HH:mm"))!''}</span>
 		<span style="margin-right:10px">${draw.state}</span> 		
 		<span>￥<span style="margin-right:20px;font-size:24px;color:#ff6666">${draw.amount}</span></span>
 		<span><a href="${base}/draw/draw/look.action?id=${draw.id}">详细</a></span>
    </div>
</div>
<div id="drawgoods${draw.id}">
</div>
</li>
</#list>