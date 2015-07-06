<html>
<head>
<title>优品365.商品管理.发布商品</title>
</head>
<body>
<script src="${base}/public/js/dist/lib-min.js"></script>
<select size="20" style="width:50%">
<#list obj.classes as aobj>
	<option value="${aobj.id}">${aobj.cname}</option>
</#list>
</select>
<script>
$(function(){
	$("#leftMenu").load('${base}/page/goods/leftmenu.ftl');
});
</script>
</body>
</html>