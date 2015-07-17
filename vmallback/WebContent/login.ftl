<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>优品365维分销平台</title>
	<link rel="stylesheet" type="text/css" href="${base}/css/component-min.css">
	<link rel="stylesheet" type="text/css" href="${base}/css/jbox-min.css">
	<link rel="stylesheet" type="text/css" href="${base}/css/common_login_reg.css">
	<script src="${base}/public/js/jquery/jquery-1.8.3.min.js"></script>	
	<script src="${base}/lib/md5.js"></script>	
</head>
<body>
	
	<div class="login">
		<a href="javascript:void(0)" class="logo"></a>
		<div class="login-inner">
		<form action="${base}/author/login/login.action" method="post" class="form-signin" id="loginfrom">
		<input type="hidden" id="password" name="password">
			<h1 class="login-title">优品365</h1>
			<div class="login-item mgb20">
				<input type="text" class="clearError" id="ipt_account" name="loginname" placeholder="请输入账号" tabindex="1">
				<a href="javascript:;" class="clearIpt j-clearIpt"><i class="gicon-remove"></i></a>
			</div>
			<div class="login-item mgb20">
				<input type="password" class="clearError" id="ipt_pwd" name="inpassword" placeholder="请输入密码" tabindex="2">
				<a href="javascript:;" class="clearIpt j-clearIpt"><i class="gicon-remove"></i></a>
			</div>
			
			<#--
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
				<button class="btn btn-primary btn-block" style="width:100%" id="btn_login">登录</button>
			</div>
			<div class="login-locationnow">
				<a href="/Public/register">没有账号？立即注册</a>
			</div>
			
			-->
			
			</form>
			
			<div>
				<button class="btn btn-primary btn-block" style="width:100%" id="btn_login">登录</button>
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

	<!-- end tooltips -->


	<script type="text/j-template" id="tpl_hint">
		<div class="hint hint-<%= type %>"><%= content %></div>
	</script>
	<!-- end hint -->



	<!--[if lt IE 10]>
		<script src="${base}/public/js/jquery/jquery.placeholder-min.js"></script>
		<script>
		$(function(){
			//修复IE下的placeholder
			$('input').placeholder();
		});
		</script>
	<![endif]-->

	<!-- end session hint -->
	
<script>

$('#btn_login').click(function(){

	var loginname =  $('#ipt_account').val();
	var pass = hex_md5($('#ipt_pwd').val());
	
	$.ajax({
		type:'POST',
		url:'${base}/author/login/login.action',
		data:{"loginname":loginname,"password":pass},
		cache:false,
		async:false,
		success:function(data)
		{
			if(data=="")
			{
				alert("登录异常！");
				return;
			}
			var json = eval("(" + data + ")");
			if(json.state=="success")
			{
				window.location = "${base}/author/login/home.action";
			}
			else
			{
				alert(json.message);
			}
		},
		error:function(data)
		{
			console.log(data);
			alert("抱歉，服务请求异常，可能网络有什么问题，稍后再试试看吧！");
		}
	})	
	
	
	
});

</script>

</body>
</html>