<html>
<head>
<title>天狗微商城.商品管理.发布商品</title>
</head>
<body>
<script src="${base}/public/js/dist/lib-min.js"></script>


<div class="panel-single panel-single-light mgt20">
    <div class="radio-group">
        <label><input type="radio" name="type" value="2">使用综合类目</label>
        <span class="fi-help-text mgl15 mgt5"></span>
    </div>

    <div class="mgt15 cat_2">


	<select size="20" style="width:20%;height:300px" id="select1">
	<#list obj.classes as aobj>
		<option value="${aobj.id}">${aobj.cname}</option>
	</#list>
	</select>
	<select size="20" style="width:20%;height:300px" id="select2">
	</select>
	<select size="20" style="width:20%;height:300px" id="select3">
	</select>
	
	</div>
</div>		
		


<div class="panel-single panel-single-light mgt20 txtCenter">
    <input type="button" id="bt_submit" class="btn btn-primary btn-submit" value="下一步">
</div>

<script>
$(function(){
	$("#leftMenu").load('${base}/goods/goods/leftmenu.action');
});
</script>

<script type="text/j-template" id="tpl_goodsclass">
<% _.each(goodsclasses,function(item, index){ %>
	<option value="<%=item.id%>"><%=item.cname%></option>
<% }) %>
</script>

<script>
var goodsclasses;
var selectedgoodsclass;

$(function(){

	$("#bt_submit").click(function(){
		
		if(selectedgoodsclass=="")
		{
			pub_alert("请先选择商品分类。");
			return;
		}
		window.location = "${base}/goods/goods/input.action?classid="+selectedgoodsclass;
	});

	$("#select1").change(function(){
	
		selectedgoodsclass = "";
	
		var supid = $(this).val();
		console.log("supid:" + supid);
		
		$.ajax(
		{
			type:'POST',
			url:'${base}/goods/goods/getsubgoodsclass.action',
			data:{"supid":supid},
			cache:false,
			async:true,
			success:function(data)
			{
				console.log(data);
				if(data=="")
				{
					return;
				}
				
				goodsclasses = eval("(" + data + ")");
				
				var html = _.template($("#tpl_goodsclass").html(), goodsclasses);
				// console.log(html);
				$("#select2").html(html);
				$("#select3").html("");
			},
			error:function(data)
			{
				console.log(data);
				console.log("服务请求异常！");
			}
		})
	});
	
	$("#select2").change(function(){
	
		selectedgoodsclass = "";
		var supid = $(this).val();
		console.log("supid:" + supid);
		
		$.ajax(
		{
			type:'POST',
			url:'${base}/goods/goods/getsubgoodsclass.action',
			data:{"supid":supid},
			cache:false,
			async:true,
			success:function(data)
			{
				console.log(data);
				if(data=="")
				{
					return;
				}
				
				goodsclasses = eval("(" + data + ")");
				
				var html = _.template($("#tpl_goodsclass").html(), goodsclasses);
				// console.log(html);
				$("#select3").html(html);
			},
			error:function(data)
			{
				console.log(data);
				console.log("服务请求异常！");
			}
		})
	});
	
	$("#select3").change(function(){
		selectedgoodsclass = $(this).val();
	});

});
</script>
</body>
</html>