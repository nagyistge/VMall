<#list obj.members as member>

<li id="member${member.id}">
    <div class="items" id="items${member.id}" name="item">
		<div class="check-wrapper">
			<span style="color:#dedede">
	            <p>&nbsp;</p>
	            <p>${member_index+1}</p>	
			</span>
        </div>
        <div class="shp-cart-item-core" style="color:#dedede;font-size:10px">

			<div class="cart-product-cell-1">
            <a href="${base}/member/member/mygroup/lookother.action?id=${member.id}">
            <img class="cart-photo-thumb" alt="" src="<#if member.wxheadimgurl=="">${base}/css/img/default.png<#else>${member.wxheadimgurl}</#if>">
            </a>
            </div>
            <div class="cart-product-cell-2">
                <div class="cart-product-name">
                    <p>
                    <span><#if member.cname=="">&nbsp;<#else>${member.cname}</#if></span>
                    <span>${member.phone}</span>
                    </p>
					<p>
					<span><#if member.wxnickname=="">&nbsp;<#else>${member.wxnickname}</#if></span>
					</p>
					<p>
	                <span style="color:#dedede"><#if member.sex=="">&nbsp;<#else>${member.sex}</#if></span>
                    <span>&nbsp;&nbsp;</span>                    
                    <span style="color:#dedede"><#if member.createtime!="">${member.createtime!?datetime?string("yyyy-MM-dd")}</#if></span>
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
