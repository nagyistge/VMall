<html>
<body>
<#list obj.classes as aobj>
	<p>${aobj.cname}</p>
</#list>
<script>
$(function(){
	$("#leftMenu").load('${base}/page/goods/leftmenu.ftl');
});
</script>
</body>
</html>