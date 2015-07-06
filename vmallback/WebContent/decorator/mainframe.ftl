<!DOCTYPE html>
<html lang="en">
<head>
<#include "/decorator/include/header.ftl">
<sitemesh:write property='head'/>
</head>
<body class="cp-bodybox">
<#include "/decorator/include/checkversion.ftl">

<div class="header">
<#include "/decorator/include/mainheader.ftl">
</div>
<!-- end header -->

<div class="container">
<div class="inner clearfix">
	<div class="content-left fl" id="leftMenu">
			
	</div>
	<!-- end content-left -->
	
	<div class="content-right fl">
	<sitemesh:write property='body'/>
	</div>
	<!-- end content-right -->
</div>
</div>
<!-- end container -->
</body>
</html>