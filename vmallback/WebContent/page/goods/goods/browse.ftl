<html>
<head>
<title>优品365.商品管理.商品查询</title>
</head>
<body>

<link rel="stylesheet" href="${base}/lib/bootstrap/css/bootstrap.min.css">

    <h1 class="content-right-title">所有订单<a class="gicon-info-sign gicon_linkother" href="javascript:void(0)" target="_blank"></a></h1>
    
    
    <form action="${base}/goods/goods/browse.action" method="post" id="queryform">

		<input type="hidden" id="_page" name="_page" value="${obj._page}">
		<input type="hidden" id="_pagesize" name="_pagesize" value="${obj._pagesize}">
		<input type="hidden" id="_maxpage" name="_maxpage" value="${obj._maxpage}">
		<input type="hidden" id="_startpage" name="_startpage" value="${obj._startpage}">
		<input type="hidden" id="_endpage" name="_endpage" value="${obj._endpage}">    
    
    
    	<input type="hidden" id="state" name="state" value="${obj.state}">
        <div class="tables-searchbox">
            <input type="text" placeholder="商品名称" class="input" name="cname" value="">
            <input type="text" placeholder="分类名称" class="input" name="classname" value="">
            <input type="text" placeholder="厂家名称" class="input" name="dealername" value="">
            <button class="btn btn-primary"><i class="gicon-search white"></i>查询</button>
        </div>
    </form>
    <div class="tabs clearfix mgt15" id="tabs">
	    <a href="javascript:void(0)" class="<#if obj.state=="">active</#if> tabs_a fl" state="">所有商品</a>
	    <a href="javascript:void(0)" class="<#if obj.state=="新建">active</#if> tabs_a fl" state="新建">新建</a>
	    <a href="javascript:void(0)" class="<#if obj.state=="上架">active</#if> tabs_a fl" state="上架">上架</a>
	    <a href="javascript:void(0)" class="<#if obj.state=="下架">active</#if> tabs_a fl" state="下架">下架</a>
	</div>
    <!-- end tabs -->

	<div class="grounp_chenge_box mgt15">
        <span class="grtt">每页显示商品数量:</span>
        <a class="intem <#if obj._pagesize==10>cur</#if>" pagesize="10" href="javascript:void(0)">10</a>
        <a class="intem <#if obj._pagesize==20>cur</#if>" pagesize="20" href="javascript:void(0)">20</a>
        <a class="intem <#if obj._pagesize==40>cur</#if>" pagesize="40" href="javascript:void(0)">40</a>
        <a class="intem <#if obj._pagesize==50>cur</#if>" pagesize="50" href="javascript:void(0)">50</a>
	</div>

        <table class="wxtables table-order mgt20">
            <colgroup>
                <col width="30%" />
                <col width="10%" />
                <col width="30%" />
                <col width="10%" />
                <col width="10%" />
                <col width="10%" />
            </colgroup>
            <thead>
                <tr>
                	<td>名称</td>
                    <td>分类</td>
                    <td>厂家</td>
                    <td>现价</td>
                    <td>销量</td>
                    <td>人气</td>
                </tr>
            </thead>
            <tbody id="tbody">
            <#list obj.goodses as goods>
                    <tr sid="${goods.id}">
                 	<td><a href="${base}/goods/goods/locate.action?id=${goods.id}" style="color:#0055ee">${goods.cname}</a></td>
                    <td>${goods.classname}</td>
                    <td>${goods.dealername}</td>
                    <td>${goods.saleprice}</td>                  
                    <td>${goods.salenum}</td>
                    <td>${goods.popular}</td>
                    </tr>
            </#list>        
            </tbody>
            
        </table>
        

        <!-- end wxtables -->
        <!-- end tables-btmctrl -->
    </form>

    <form action="" method="post" id="ids">
        <input type="hidden" name="ids" value="">
    </form>
    
    <div class="tables-btmctrl clearfix">
	    <div class="fr">
		    <div class="paginate">
		    <a href="javascript:void(0)" pagenum="${obj._startpage?default("1")?number-10}" class="prev <#if obj._startpage?default("1")?number==1>disabled</#if>"></a>
		    <#list obj._startpage .. obj._endpage as aobj>
		    <a href="javascript:void(0)" pagenum="${aobj}" class="<#if aobj &gt; obj._maxpage>disabled</#if> <#if aobj==obj._page>cur</#if>">${aobj}</a>
		    </#list>
		    <a href="javascript:void(0)" pagenum="${obj._endpage?default("10")?number+10}" class="next <#if obj._endpage?default("10")?number&gt;=obj._maxpage>disabled</#if>"></a>
		    </div>
	    </div>            
	</div>    
    
<script src="${base}/public/js/jquery/jquery-1.8.3.min.js"></script>

<script src="${base}/public/js/dist/lib-min.js"></script>
<script src="${base}/public/plugins/jbox/jquery.jbox-min.js"></script>
<script src="${base}/public/plugins/zclip/jquery.zclip-min.js"></script>
<script src="${base}/public/plugins/uploadify/jquery.uploadify.min.js"></script>

<script src="${base}/public/js/dist/component-min.js"></script>
<script src="${base}/public/modulesJs/scroll.js"></script>
<script src="${base}/public/plugins/My97DatePicker/WdatePicker.js"></script>
<script src="${base}/page/order/order/lists.js"></script>

<!--[if lt IE 10]>
<script src="${base}/public/js/jquery/jquery.placeholder-min.js"></script>
<script>
    $(function(){
        //修复IE下的placeholder
        $('.input,.textarea').placeholder();
    });
</script>
<![endif]-->

<script>
$(function(){
	$("#leftMenu").load('${base}/goods/goods/leftmenu.action');
});
</script>

    

<script>
    $(function(){
        $(".j-copy").zclip({
            path: '/Public/plugins/zclip/ZeroClipboard.swf',
            copy: function(){
                return $(this).data('copy');
            },
            afterCopy:function(){
                HYD.hint("success","内容已成功复制到您的剪贴板中");
            }
        });
        $(".btn-notice").click(function(){
            // $.post('/System/readAllNotice',{},function(){
            //     window.location.reload();
            // })
            $.ajax({
                url: '/System/readAllNotice',
                type: 'POST',
                success:function(data){
                    if(data.status == 1){
                        window.location.reload();
                    }else{
                         HYD.hint("danger",data.msg);
                    }
                    
                }
            })
        });
        
        
        ;(function(){
            // 首页竖线到底
            var height1=$(".content-right").height();
            var height2=$(".content-left").height();
            if(parseInt(height1) < parseInt(height2)){
                $(".content-right").css({'min-height': height2});
            };
            
                    })();
       
    });
</script>
<!-- end session hint -->


<!-- pujian add -->
<script>
var ordergoods;
</script>
<script type="text/j-template" id="tpl_ordergoods">
<% _.each(ordergoods,function(item){ %>
<ul>


    <div class="goodsList-info">
        <p class="colorGray"><%= item.goodsname %></p>
		<span class="colorGray">&yen;：<span class="colorRed"><%= item.realprice %></span></span>
        <span class="colorGray">数量：<span class="colorRed"><%= item.nums %></span></span>
    </div>

</ul>        	
<% }) %>
</script>

<script>
$(function(){

//////////

$("#tabs a").click(function() {
	$("#state").val($(this).attr("state"));
	$("#tabs a").removeClass("active");
	$(this).addClass("active");
	page_browse();
})

$("#tbody tr").each(function(){
	var ctr = $(this);
	var orderid = $(this).attr("sid");
	console.log("orderid:"+orderid);
	$.ajax({
		type:'post',
		url:'${base}/order/ordergoods/ajaxlist.action',
		data:{"orderid":orderid},
		cache:false,
		async:true,
		success:function(data)
		{
			ordergoods = eval("(" + data + ")");

			//console.log(ordergoods);

			var html = _.template($("#tpl_ordergoods").html(), ordergoods);

			console.log(ctr.find("td.goods").html());
			ctr.find("td.goods").html(html);
		},
		error:function(data)
		{
			console.log(data);
			alert("服务请求异常！");
		}
	})
})

function page_browse()
{
	$("#queryform").submit();
}


$('div .paginate a').on('click', function()
{
	var pagenum = $(this).attr("pagenum");
	$("#_page").val(pagenum);
	$("#queryform").submit();
});

$('.intem').on('click', function()
{
	var pagesize = $(this).attr("pagesize");
	$("#_pagesize").val(pagesize);
	$("#queryform").submit();
});



//////////
})

</script>

</body>
</html>