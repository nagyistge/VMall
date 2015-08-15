<html>
<head>
<title>天狗微商城.商品管理.设置图片</title>
</head>
<body>

<link rel="stylesheet" href="/Public/css/dist/component-min.css">
<link rel="stylesheet" href="/Public/plugins/jbox/jbox-min.css">
<link rel="stylesheet" href="/Public/css/dist/home/PubMarketing/pubMarketing.css">
<link rel="stylesheet" href="/Public/plugins/uploadify/uploadify-min.css">
<style>
.uploadify-button {background-color: transparent;border: none;border-radius:0;padding: 0;}
.uploadify:hover .uploadify-button {background-color: transparent;}
</style>

	<h1 class="content-right-title">商品图片管理</h1>
	
	<div class="mgt15" style="">
	
		<form action="" method="post" id="form_photo" style="">
		<input type="hidden" name="goodsid" value="obj.goodsid">
		<input type="hidden" name="cclass" value="GoodsPhoto">
		<table class="wxtables table-order mgt20">
            <colgroup>
                <col width="15%" />
                <col width="10%" />
                <col width="10%" />
                <col width="50%" />
                <col width="15%" />
			</colgroup>
            <thead>
                <tr>
                	<td>图片类型</td>
                	<td>序号</td>
                    <td>允许多图</td>
                    <td>预览</td>
                    <td></td>
                </tr>
            </thead>
            <tbody id="tbody">

            </tbody>
            
        </table>
		
		</form>
	
	</div>
	
	
<script src="/Public/js/dist/lib-min.js"></script>
<script src="/Public/plugins/jbox/jquery.jbox-min.js"></script>
<script src="/Public/plugins/zclip/jquery.zclip-min.js"></script>
<!-- 线上环境 -->
    <script src="/Public/js/dist/component-min.js"></script>
    <script src="/Public/modulesJs/scroll.js"></script>
<!--[if lt IE 10]>
<script src="/Public/js/jquery/jquery.placeholder-min.js"></script>
<script>
    $(function(){
        //修复IE下的placeholder
        $('.input,.textarea').placeholder();
    });
</script>
<![endif]-->

<!-- 配置文件 -->
<script src="/Public/plugins/uploadify/jquery.uploadify.min.js?ver=5429"></script>	

<script type="text/j-template" id="tpl_photos">
	
	<% for(var i=0;i<photos.length;i++) { %>
		<% var aphoto = photos[i] %>    
	<tr>
		<input type="hidden" name="ctype" value="<%=aphoto.ctype%>">
        <input type="hidden" name="goodsphotoid" value="<%=aphoto.id%>">
        <input type="hidden" name="ismulti" value="<%=aphoto.ismulti%>">
        <input type="hidden" name="sno" value="<%=aphoto.sno%>">
		<td><%=aphoto.ctype%></td>
	  	<td><%=aphoto.sno%></td>		
	  	<td><%=aphoto.ismulti%></td>
	  	<td>
			<div class="j-render-con" id="upload_con<%=i%>">
			</div>						
	  	</td>                  	
		<td>
			<div class="j-render-ctrl" id="upload_ctrl<%=i%>">
			</div>
			
			<div class="j-render-newctrl" id="upload_newctrl<%=i%>">
			</div>									
		</td>
	</tr> 
	<%}%>

</script>
<!-- end tpl_photos -->

<script type="text/j-template" id="tpl_ctrl">

	<div class="formitems">
	    <div class="form-controls">
	        <img src="" class="img-cover " style="display:none;" id="img-cover-img">
	        <input type="file" class="btn btn-primary btn-small btn-imgcover j-imgcover" id="j-imgcover<%=Math.floor(Math.random()*100)%>" name="fupload"/>
	        <span class="fi-help-text mgl90"></span>
	        <input class="validate[required]" type="text" name="cover_img" value="${base}/<%=pic%>" id="cover_img<%=Math.floor(Math.random()*100)%>" style="width: 1px;border: 0;height: 1px;display: inline" data-errormessage="上传图片才行哦" >
	        <span id="hidden-img-error" style="width: 150px;display: block"></span>
	    </div>
	</div>

	<div class="formitems">
		<div class="form-controls">
			<button type="button" class="btn btn-primary addphoto" style="display:<%if (ismulti=="是") { %>block<% }else{ %>none<%}%>">添加图片</button>
		</div>
	</div>
	

</script>
<!-- end tpl_ctrl -->

<script type="text/j-template" id="tpl_con">
	<div class="coer_img">
	<img src="${base}/<%=pic%>">
	</div>
</script>
<!-- end tpl_con -->

<!-- end script template -->

<script>
var files;
var photos;

    var kid = "${obj.goodsid}";
	var goodsid = "${obj.goodsid}";
	
$(function(){
    

	
	function page_getphoto()
	{
	
		$.ajax({
			type:'post',
			url:'${base}/goods/goods/getphoto.action',
			data:{goodsid:goodsid},
			cache:false,
			async:true,
			success:function(data)
			{
				console.log(data);
				if(data=="")
				{
					pub_alert("读取图片异常！");
					return;
				}
				var json = eval("(" + data + ")");
				console.log(json);
				if(json.state=="success")
				{
					files = {photos:json.photos};
					photos = json.photos;
					console.log(files);
					render_files();
				}
				else
				{
					pub_alert(json.message);
				}
			},
			error:function(data)
			{
				console.log(data);
				pub_alert("服务请求异常！");
			}
		})		
	}
    
    function render_files()
	{
		console.log("begin photos.");

		var html=_.template($("#tpl_photos").html(),files);
		
		$("#tbody").empty().append(html);
		console.log("end photos.");
		
		render_ctrl();
		render_addphoto();
    }
    
    // 渲染上传控件
	function render_ctrl()
	{
		$(".j-render-ctrl").each(function(i, elment){
			var aphoto = photos[i];
			var html=_.template($("#tpl_ctrl").html(), aphoto);
			$(this).empty().append(html);
		});
		
		$(".j-imgcover").each(function(i, elment){
		console.log(i);

		var aphoto = photos[i];
		
		console.log(aphoto);
		console.log(aphoto.ctype);
		
        var html=_.template($("#tpl_con").html(), aphoto);
		$(($(".j-render-con")[i])).empty().append(html);		
		
		//选择文件

        $(this).uploadify({
            "debug": false,
            "auto": true,
            "formData": {'cclass':'GoodsPhoto', 'kid':aphoto.id},	            
            "width": 75,
            "multi": false,
            'swf': '/Public/plugins/uploadify/uploadify.swf',
            "buttonImage": "/Public/plugins/uploadify/uploadify-image.png",
            'uploader': '/vmallback/system/attach/upload.action', //接口名称
            "buttonText": "+",
            "fileSizeLimit": "5MB",
            "fileTypeExts": "*.jpg; *.jpeg; *.png; *.gif; *.bmp",
            "fileObjName": "fupload",
            'onSelectError': function(file, errorCode, errorMsg) {
                switch (errorCode) {
                    case -100:
                        HYD.hint("danger", "对不起，系统只允许您一次最多上传10个文件");
                        break;
                    case -110:
                        HYD.hint("danger", "对不起，文件 [" + file.name + "] 大小超出5MB！");
                        break;
                    case -120:
                        HYD.hint("danger", "文件 [" + file.name + "] 大小异常！");
                        break;
                    case -130:
                        HYD.hint("danger", "文件 [" + file.name + "] 类型不正确！");
                        break;
                }
            },
            'onFallback': function() {
                HYD.hint("danger", "您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
            },
            'onUploadSuccess': function(file, data, response) {
                
                var json = eval('(' + data + ')');
                console.log(json);
                 console.log(aphoto["pic"]);
                aphoto["pic"] = json.file_path;
                console.log(aphoto["pic"]);
                ($("input[name='cover_img']")[i]).value = json.file_path;
                
                var html=_.template($("#tpl_con").html(), aphoto);
				$(($(".j-render-con")[i])).empty().append(html);
				
				var attachid = json.attach.id;
				var ctype = aphoto.ctype;
				var goodsphotoid = aphoto.id;
				var sno = aphoto.sno;
				
				// 更新图片数据
				$.ajax({
					type:'post',
					url:'${base}/goods/goods/updatephoto.action',
					data:{goodsid:goodsid,attachid:attachid,goodsphotoid:goodsphotoid,sno:sno,ctype:ctype},
					cache:false,
					async:true,
					success:function(data)
					{
						console.log(data);
						if(data=="")
						{
							pub_alert("上传更新图片异常！");
							return;
						}
						var json = eval("(" + data + ")");
						if(json.state=="success")
						{
							// pub_alert("上传更新图片成功！");
							page_getphoto();
						}
						else
						{
							pub_alert(json.message);
						}
					},
					error:function(data)
					{
						console.log(data);
						pub_alert("服务请求异常！");
					}
				})
				
            },
            onUploadError: function(file, errorCode, errorMsg, errorString) {
                HYD.hint("danger", "对不起：" + file.name + "上传失败：" + errorString);
            },
            'onUploadStart': function (file) {  
                // $("#j-imgcover").uploadify("settings", "formData", {'cclass':'GoodsPhoto', 'kid':aphoto.id});  
                //在onUploadStart事件中，也就是上传之前，把参数写好传递到后台。  
            }  
        });	
		
		});
		
	};
	
	function render_addphoto()
	{
		$(".addphoto").each(function(i, elment){
			var aphoto = photos[i];
			aphoto.goodsid = "";
			var url = "";
			var ctype = aphoto.ctype;
							
			// 更新图片数据
			$(this).click(function(){
			
				$.ajax({
					type:'post',
					url:'${base}/goods/goods/addphoto.action',
					data:{goodsid:goodsid,ctype:ctype},
					cache:false,
					async:true,
					success:function(data)
					{
						console.log(data);
						if(data=="")
						{
							pub_alert("添加图片异常！");
							return;
						}
						var json = eval("(" + data + ")");
						if(json.state=="success")
						{
							// pub_alert("添加图片成功！");
							page_getphoto();
						}
						else
						{
							pub_alert(json.message);
						}
					},
					error:function(data)
					{
						console.log(data);
						pub_alert("服务请求异常！");
					}
				})
			})	

		});	
	}
	
	page_getphoto();

});

</script>


</body>
</html>