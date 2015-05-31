
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no,minimum-scale=1,maximum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title></title>
    <link rel="shortcut icon" href="logo32.png">
    <link rel="stylesheet" href="${base}/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base}/lib/font-awesome/css/font-awesome.min.css">
    <link href="logo60.png" rel="apple-touch-icon">
    <link href="logo76.png" rel="apple-touch-icon" sizes="76x76">
    <link href="logo120.png" rel="apple-touch-icon" sizes="120x120">
    <link href="logo152.png" rel="apple-touch-icon" sizes="152x152">
    <script src="${base}/lib/jquery-2.1.1.min.js"></script>
    <link rel="stylesheet" href="${base}/css/gs.css">
    <link rel="stylesheet" href="${base}/css/choose.css">
    <base target="mainFrame">
  </head>
  <body>
    <div id="gsLayout">
      <div id="gsSidebar"><a href="${base}/frame/home.action" class="home"><i class="fa fa-home"></i></a>
        <div class="logo" style="color:#cc1c1e;font-size:8px;margin-left:10px;"><img src="${base}/css/img/logo-v2.png">&nbsp;&nbsp;</div>
        <div class="user"><a href="cog.html" class="gravatar"><img src="pics/guoshuang.png"></a>
          <div class="name">
            <div class="t">${Session.sys_login_token.sys_login_username}<a href="${base}/login.html" target="top" class="quit">退出</a></div><a href="_home/msg.html" class="msg">你有3个消息</a>
          </div>
        </div>
        <div id="leftNavi">

        </div>
      </div>
      <div id="gsMainpage">
        <div id="gsheader">
          <div id="gsToggleSidebar"><i class="fa fa-bars"></i></div>
		  <div id="gsTab"> 	
		    	<div id="chooseTab-title">
				<ul>
				  <li data-type="home" class="active" url="">首页</li>
				  <li data-type="shop" url="">店铺</li>
				  <li data-type="goods" url="${base}/frame/frame/menu_goods.action">商品</li>
				  <li data-type="order" url="">订单</li>
				  <li data-type="member" url="">会员</li>
				  <li data-type="distributor" url="">分销商</li>
				  <li data-type="finance" url="">财务</li>
				  <li data-type="market" url="">营销</li>
				  <li data-type="set" url="">设置</li>
				</ul>
				</div>
		  </div>	
          <div id="gsTopMenu">
            <ul>
              <li class="home"><a href="${base}/frame/home.action" title="首页"><i class="fa fa-home"></i></a></li>
              <li class="msg"><a href="_home/msg.html" title="系统消息"><i class="fa fa-comments"></i>
                  <div class="num">10</div></a></li>
            </ul><span class="user"><img src="pics/guoshuang.png">${Session.sys_login_token.sys_login_deptname}&nbsp;${Session.sys_login_token.sys_login_username}<i class="fa fa-angle-down"></i>
              <ul>
                <li><a href="#1" title="个人设置"><i class="fa fa-cog"></i> 个人设置</a></li>
                <li><a href="#1" title="帮助"><i class="fa fa-question-circle"></i> 帮助</a></li>
                <li class="quit"><a href="${base}/login.html" title="退出" target="top"><i class="fa fa-power-off"></i> 退出</a></li>
              </ul></span>
          </div>
        </div>
        <div id="gsBody">
          <iframe src="about:blank" frameborder="0" name="mainFrame" allowtransparency="true"></iframe>
        </div>
      </div>
    </div>
    <script src="${base}/lib/jquery-ui.min.js"></script>
    <script>

    jQuery(function($){
    //////

    //toggle sidebar
    $('#gsToggleSidebar').on('click',function(e){
    	e.stopPropagation();
    	$('#gsLayout').toggleClass('closed');
    })

    //#leftNavi - toggle
    $('#leftNavi').on('click','.t',function(e){
    	e.stopPropagation();
    	var li=$(this).closest('li');
    	li.siblings().removeClass('active');
    	li.toggleClass('active');
    })

    //#leftNavi - click
    var leftNaviSubLis=$('#leftNavi').find('.sub li');
    $('#leftNavi').on('click','.sub a',function(){
    	leftNaviSubLis.removeClass('active');
    	$(this).closest('li').addClass('active');
    })

    //手机 关闭 sidebar
    if($(window).width()<420){
    	$(window).on('click',function(){
    		$('#gsLayout').removeClass('closed');
    	})
    }

    //暂时关闭 #1 链接
    $('a[href^=#]').on('click',function(){
    	console.error('对不起，还没写链接！')
    	return false;
    })

	//切换标签页
	$('#chooseTab-title').on('click', 'li', function() 
	{
		var li = $(this);
		var oid = li.attr('data-type');
		li.addClass('active').siblings().removeClass('active');
		// $('#mainFrame').attr('src',li.attr('url'));
		page_menu(li.attr('url'));
	});
	
	function page_menu(url)
	{
		$.ajax({
			type:'post',
			url:url,
			dataType: "html",
			cache:false,
			async:false,
			success:function(data)
			{
				$("#leftNavi").html(data);
			},
			error:function(data)
			{
				console.log("执行异常");
			}
		})
	}

    //////	
    })
    </script>
  </body>
</html>