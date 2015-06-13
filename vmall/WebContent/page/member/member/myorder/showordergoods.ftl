<#list obj.ordergoodses as ordergoods>
<li id="product1196557">
	<div class="items">

        <div class="shp-cart-item-core">
            <div class="cart-product-cell-3">
                <span class="shp-cart-item-price" id="price${ordergoods.id}" style="font-size:18px;color:#ff6666">${ordergoods.realprice!?number}</span>
            </div>
            <a class="cart-product-cell-1" href="http://m.jd.com/product/1196557.html?sid=07c48dfbe04c2493f56136867acee31c">
                <img class="cart-photo-thumb" alt="" src="http://img10.360buyimg.com/n7/jfs/t1150/277/9118664/79460/dac224b1/54dc483bN75af2578.jpg!q70.jpg" onerror="http://misc.360buyimg.com/lib/skin/e/i/error-jd.gif">
            </a>
            <div class="cart-product-cell-2">
                <div class="cart-product-name"><a href="http://m.jd.com/product/1196557.html?sid=07c48dfbe04c2493f56136867acee31c"><span>${ordergoods.goodsname}</span></a></div>
                <div class="shp-cart-opt">
                    <div class="quantity-wrapper">
                    	╳<span style="font-size:16px;color:#9e9e9e">${ordergoods.nums}</span>
                    </div>
                </div>
            </div>
        </div>
	</div>
</li>
</#list>