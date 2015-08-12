<html>
<head>
<title>优品365.商品管理.设置图片</title>
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
		<table class="wxtables table-order mgt20">
            <colgroup>
                <col width="20%" />
                <col width="60%" />
                <col width="20%" />
            </colgroup>
            <thead>
                <tr>
                	<td>图片类型</td>
                    <td>预览</td>
                    <td></td>
                </tr>
            </thead>
            <tbody id="tbody">
                <tr>
                	<td>商品缩略图片（单张）</td>
                  	<td><div class="coer_img"><img src="/Public/images/demo_news.gif"></div></td>                  	
                	<td>
						
		                <div class="form-controls">
	                    <img src="" class="img-cover " style="display:none;" id="img-cover-img">
	                    <div id="j-imgcover" class="uploadify" style="height: 30px; width: 75px;"><object id="SWFUpload_0" type="application/x-shockwave-flash" data="/Public/plugins/uploadify/uploadify.swf?preventswfcaching=1439280451038" width="75" height="30" class="swfupload" style="position: absolute; z-index: 1;"><param name="wmode" value="transparent"><param name="movie" value="/Public/plugins/uploadify/uploadify.swf?preventswfcaching=1439280451038"><param name="quality" value="high"><param name="menu" value="false"><param name="allowScriptAccess" value="always"><param name="flashvars" value="movieName=SWFUpload_0&amp;uploadURL=%2Fvmallback%2Fsystem%2Fattach%2Fupload.action&amp;useQueryString=false&amp;requeueOnError=false&amp;httpSuccess=&amp;assumeSuccessTimeout=30&amp;params=cclass%3DMaterial%26amp%3Bkid%3Dtest&amp;filePostName=fupload&amp;fileTypes=*.jpg%3B%20*.jpeg%3B%20*.png%3B%20*.gif%3B%20*.bmp&amp;fileTypesDescription=All%20Files&amp;fileSizeLimit=5MB&amp;fileUploadLimit=0&amp;fileQueueLimit=999&amp;debugEnabled=false&amp;buttonImageURL=%2Fvmallback%2Fsystem%2Fmaterial%2F&amp;buttonWidth=75&amp;buttonHeight=30&amp;buttonText=&amp;buttonTextTopPadding=0&amp;buttonTextLeftPadding=0&amp;buttonTextStyle=color%3A%20%23000000%3B%20font-size%3A%2016pt%3B&amp;buttonAction=-100&amp;buttonDisabled=false&amp;buttonCursor=-2"></object><div id="j-imgcover-button" class="uploadify-button " style="background-image: url(http://localhost:9080/Public/plugins/uploadify/uploadify-image.png); text-indent: -9999px; height: 30px; line-height: 30px; width: 75px;"><span class="uploadify-button-text">+</span></div></div><div id="j-imgcover-queue" class="uploadify-queue"></div>
	                    <span class="fi-help-text mgl90"></span>
	
	                    <input class="validate[required]" type="text" style="width: 1px;border: 0;height: 1px;display: inline" name="cover_img" value="" id="cover_img" data-errormessage="上传图片才行哦">
	                    <span id="hidden-img-error" style="width: 150px;display: block"></span>
            			</div>
			            <i class="arrow arrow_out" style="margin-top: 0px;"></i>
			            <i class="arrow arrow_in" style="margin-top: 0px;"></i>
			            <input type="hidden" name="is_preview" value="0">
                  	
                	</td>
				</tr> 
                <tr>
                	<td>商品详情介绍图片（多张）</td>
                  	<td><div class="coer_img"><img src="/Public/images/demo_news.gif"></div></td>                  	
                	<td>

		                <div class="form-controls">
	                    <img src="" class="img-cover " style="display:none;" id="img-cover-img">
	                    <div id="j-imgcover" class="uploadify" style="height: 30px; width: 75px;"><object id="SWFUpload_0" type="application/x-shockwave-flash" data="/Public/plugins/uploadify/uploadify.swf?preventswfcaching=1439280451038" width="75" height="30" class="swfupload" style="position: absolute; z-index: 1;"><param name="wmode" value="transparent"><param name="movie" value="/Public/plugins/uploadify/uploadify.swf?preventswfcaching=1439280451038"><param name="quality" value="high"><param name="menu" value="false"><param name="allowScriptAccess" value="always"><param name="flashvars" value="movieName=SWFUpload_0&amp;uploadURL=%2Fvmallback%2Fsystem%2Fattach%2Fupload.action&amp;useQueryString=false&amp;requeueOnError=false&amp;httpSuccess=&amp;assumeSuccessTimeout=30&amp;params=cclass%3DMaterial%26amp%3Bkid%3Dtest&amp;filePostName=fupload&amp;fileTypes=*.jpg%3B%20*.jpeg%3B%20*.png%3B%20*.gif%3B%20*.bmp&amp;fileTypesDescription=All%20Files&amp;fileSizeLimit=5MB&amp;fileUploadLimit=0&amp;fileQueueLimit=999&amp;debugEnabled=false&amp;buttonImageURL=%2Fvmallback%2Fsystem%2Fmaterial%2F&amp;buttonWidth=75&amp;buttonHeight=30&amp;buttonText=&amp;buttonTextTopPadding=0&amp;buttonTextLeftPadding=0&amp;buttonTextStyle=color%3A%20%23000000%3B%20font-size%3A%2016pt%3B&amp;buttonAction=-100&amp;buttonDisabled=false&amp;buttonCursor=-2"></object><div id="j-imgcover-button" class="uploadify-button " style="background-image: url(http://localhost:9080/Public/plugins/uploadify/uploadify-image.png); text-indent: -9999px; height: 30px; line-height: 30px; width: 75px;"><span class="uploadify-button-text">+</span></div></div><div id="j-imgcover-queue" class="uploadify-queue"></div>
	                    <span class="fi-help-text mgl90"></span>
	
	                    <input class="validate[required]" type="text" style="width: 1px;border: 0;height: 1px;display: inline" name="cover_img" value="" id="cover_img" data-errormessage="上传图片才行哦">
	                    <span id="hidden-img-error" style="width: 150px;display: block"></span>
            			</div>
			            <i class="arrow arrow_out" style="margin-top: 0px;"></i>
			            <i class="arrow arrow_in" style="margin-top: 0px;"></i>
			            <input type="hidden" name="is_preview" value="0">
                	
                	</td>
				</tr>				   				
            </tbody>
            
        </table>
		
		</form>
   
	
	
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
	
	<script>

	initData = {
		link:"链接",
		title:"标题",
		author:"",
        datetime:"2015-08-11",
        content:"",
        link_type:"",
        link_id:"1bccc859c0a80169787f7957e5000ab3",
        link_name:"",
		redirect:"",
		coverimg:'/Public/images/demo_news.gif',
		summary:"摘要"
	};
    </script>
    
	<script src="/vmallback/page/system/material/inputone.js"></script>
	<!-- 配置文件 -->
    <script src="/Public/plugins/uploadify/jquery.uploadify.min.js?ver=5429"></script>

	

	
	
	
	</div>
	<!-- end script template -->
	


</body>
</html>