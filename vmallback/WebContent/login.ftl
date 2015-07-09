﻿<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>优品365维分销平台</title>
	<link rel="stylesheet" href="http://res.mp.wifenxiao.com/Public/css/dist/component-min.css">
	<link rel="stylesheet" href="/Public/plugins/jbox/jbox-min.css">
	<link rel="stylesheet" href="http://res.mp.wifenxiao.com/Public/css/dist/home/Public/common_login_reg.css">

	
</head>
<body>
	<form action="/vmallback/author/login/login.action" method="post" class="form-signin">
	<div class="login">
		<a href="/" class="logo"></a>
		<div class="login-inner">
			<h1 class="login-title">优品365</h1>
			<div class="login-item mgb20">
				<input type="text" class="clearError" id="ipt_account" name="loginname" placeholder="请输入账号" tabindex="1">
				<a href="javascript:;" class="clearIpt j-clearIpt"><i class="gicon-remove"></i></a>
			</div>
			<div class="login-item mgb20">
				<input type="password" class="clearError" id="ipt_pwd" name="password" placeholder="请输入密码" tabindex="2">
				<a href="javascript:;" class="clearIpt j-clearIpt"><i class="gicon-remove"></i></a>
			</div>
			<div class="clearfix mgb10">
				<div class="login-item code fl">
					<input type="text" class="clearError" id="ipt_code" placeholder="验证码" data-autohide="1" tabindex="3">
					<a href="javascript:;" class="clearIpt j-clearIpt"><i class="gicon-remove"></i></a>
				</div>
				<div class="code-img fl mgl5">
					<img src="/Public/verify" class="j-codeReresh" id="code-img-enti">
				</div>
				<div class="fl mgl5">
					<a href="javascript:;" class="j-codeReresh" id="code-refresh">换一张</a>
				</div>
			</div>
			<div class="checkbox-group clearfix mgb20">
				<label class="fl"><input type="checkbox" id="rd_remember">记住密码</label>
				<a href="/Public/forget" class="fr login-forgetlink">忘记密码？</a>
			</div>
			<div>
				<button type="submit" class="btn btn-primary btn-block" style="width:100%" id="btn_login">登录</button>
			</div>
			<div class="login-locationnow">
				<a href="/Public/register">没有账号？立即注册</a>
			</div>
		</div>
	</div>
	<!-- end login -->

	<div class="tooltips" data-origin="ipt_account" data-currentleft="0" style="top: 228px; left: 712.5px;">
		<span class="tooltips-arrow tooltips-arrow-left"><em>◆</em><i>◆</i></span>
		<span class="tooltips-content">请输入手机号码或邮箱</span>
	</div>

	<div class="tooltips" data-origin="ipt_pwd" data-currentleft="0" style="top: 288px; left: 712.5px;">
		<span class="tooltips-arrow tooltips-arrow-left"><em>◆</em><i>◆</i></span>
		<span class="tooltips-content">请输入密码</span>
	</div>

	<div class="tooltips" data-origin="ipt_code" data-currentleft="0" style="top: 348px; left: 544.5px;">
		<span class="tooltips-arrow tooltips-arrow-left"><em>◆</em><i>◆</i></span>
		<span class="tooltips-content">请输入验证码</span>
	</div>
	
	</form>
	<!-- end tooltips -->


	<script type="text/j-template" id="tpl_hint">
		<div class="hint hint-<%= type %>"><%= content %></div>
	</script>
	<!-- end hint -->

	<script src="http://res.mp.wifenxiao.com/Public/js/dist/lib-min.js"></script>
	<script src="/Public/plugins/jbox/jquery.jbox-min.js"></script>
	<script src="http://res.mp.wifenxiao.com/Public/js/dist/component-min.js"></script>
	<script src="http://res.mp.wifenxiao.com/Public/js/dist/home/Public/common_login_reg.js"></script>
	
	



	<!--[if lt IE 10]>
		<script src="http://res.mp.wifenxiao.com/Public/js/jquery/jquery.placeholder-min.js"></script>
		<script>
		$(function(){
			//修复IE下的placeholder
			$('input').placeholder();
		});
		</script>
	<![endif]-->

	<!-- end session hint -->

</body>
</html>