<!DOCTYPE html>
<html>
<head>
<#include "/decorator/include/header.ftl">
</head>
<body id="body">
<script>
<#if obj.eventitemgoodses?size==1>
var url = "${base}/goods/goods/eventlook.action";
window.location = "${obj.eventitemgoodses[0].lasturl}";
</#if>
</script>
<div class="viewport">
<#include "/decorator/include/navmain.ftl">
</div>
</body>
</html>