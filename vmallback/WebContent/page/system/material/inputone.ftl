
<!DOCTYPE html>
<html lang="en">
<head>
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

	<h1 class="content-right-title">添加单条图文</h1>
	
	<div class="txtRight ">
        <a href="/Material/index/type/1" class="btn btn-success">单图文管理</a>
	</div>   
 	
	<div class="mgt15">
	
		<div class="materialPanel pb30 h-auto ovh">
		
			<div id="j-render-ctrl">

			
			</div>
			<!-- end j-render-ctrl -->

    		<div id="j-render-con">

	   		</div>
	   		<!-- end j-render-con -->
	   		
		</div>
		<!-- end materialPanel -->
		
		<div class="materialActions txtCenter w_all border-top_1">
            <a href="javascript:;" class="btn bigbutton btn-primary btn-submit" onclick="$('#form1').submit();"><i class="gicon-check white"></i>保存素材</a>
            <a href="javascript:;" class="bigbutton btn set-yl">保存并预览</a>
        </div>
        <!-- end materialActions -->
 	</div>
	<!-- end mgt15 -->
	
	<div class="setLinks" style="display: none;">
		<ul id="setLinks">
		<li><a href="javascript:;" data-type="1" data-key="detail" data-url="">选择商品</a></li>
		<li><a href="javascript:;" data-type="1" data-key="group" data-url="">商品分组</a></li>
		<li><a href="javascript:;" data-type="1" data-key="magazine" data-url="">专题页面</a></li>
		<li><a href="javascript:;" data-type="1" data-key="sort" data-url="">专题分类</a></li>
		<li><a href="javascript:;" data-type="1" data-key="ump" data-url="">营销活动</a></li>
		<li><a href="javascript:;" data-type="0" data-key="shop" data-url="http://m.wxfenxiao.com/Shop/index/sid/2003360.html">店铺主页</a></li>
		<li><a href="javascript:;" data-type="0" data-key="user" data-url="http://m.wxfenxiao.com/User/index/sid/2003360.html">会员主页</a></li>
		<li><a href="javascript:;" data-type="0" data-key="fxsq" data-url="http://m.wxfenxiao.com/User/dist_apply/sid/2003360.html">分销申请</a></li>
		<li><a href="javascript:;" data-type="0" data-key="diy" data-url="">自定义外链</a></li>        
		</ul>
	</div>


	<!-- 脚本模板 -->
	<div>
	
	<script type="text/j-template" id="tpl_material_con">
        <div id="materialPre">
            <dl class="materialPrePanel new-mater">
                    
                    <div class="diy-phone-contain new-contain" style="min-height:200px;width:auto;border:0;">
                        <dl class="materialPrePanel">
                            <dt>
                                <h1 class="single-title cor8d"><%= title %></h1>
                                <p class="single-datetime pd5_0"><%= datetime %></p>
                                <div class="coer_img"><img src="${base}/<%= coverimg %>" ></div>
                                <p class="single-summary b0 cor8d"><%= summary %></p>
                            </dt>
                        </dl>
                    </div> 
                </dt>
            </dl>
        </div>
    </script>
    <!-- end tpl_material_con -->
	
	<script type="text/j-template" id="tpl_material_ctrl">
        <form action="${base}/system/material/saveone.action" method="post" id="form1">
        <div class="material-item fr" data-origin="header">
            <div class="formitems">
                <label class="fi-name"><span class="colorRed">*</span>标题：</label>
                <div class="form-controls">
                    <input type="text" class="input xxlarge j-renderEle validate[required,maxSize[30]]" data-name="title" value="<%=initData.title%>" name="title" id="txt-title">
                    <span class="fi-help-text db">建议不多于30个字</span>
                </div>
            </div>
            <div class="formitems">
                <label class="fi-name">作者：</label>
                <div class="form-controls">
                    <input type="text" class="input xxlarge j-renderEle validate[required,maxSize[8]]" data-name="author" value="<%=initData.author%>" name="author" id="txt-author">
                    <span class="fi-help-text db">最多8个字</span>
                </div>
            </div>
            <div class="formitems">
                <label class="fi-name"><span class="colorRed">*</span>封面：</label>
                <div class="form-controls">
                    <img src="" class="img-cover " style="display:none;" id="img-cover-img">
                    <input type="file" class="btn btn-primary btn-small btn-imgcover j-imgcover" id="j-imgcover" name="fupload"/>
                    <span class="fi-help-text mgl90">大图片建议尺寸：900像素 × 500像素</span>

                    <input class="validate[required]" type="text" style="width: 1px;border: 0;height: 1px;display: inline" name="cover_img" value="<%=initData.cover_img%>" id="cover_img"  data-errormessage="上传图片才行哦" >
                    <span id="hidden-img-error" style="width: 150px;display: block"></span>
                </div>
            </div>
            <div class="formitems">
                <label class="fi-name">摘要：</label>
                <div class="form-controls">
                    <textarea data-name="summary" class="textarea j-renderEle" style="width:526px; height:60px;" name="summary"><%=initData.summary%></textarea>
                    <span class="fi-help-text"></span>
                </div>
            </div>
            <div class="formitems">
                <label class="fi-name">正文：</label>
                <div class="form-controls" id="form-controls">
                    <textarea id="content" name="content" class=""><%=initData.content%></textarea>
                </div>
            </div>
            <div class="formitems">
                <div class="fx-links">
                   <label class="fi-name j-show-btn"><a href="" class="badge badge-success" title=""><b></b><em><% if(link_name){%><%=link_name%><%}else{%>阅读原文链接<%}%></em></a> <s class="caret-up"></s> <span class="fx-reset">修改</span></label>
                    <span class="disi" style="display:none;">(非微信平台，正文与链接并存时，仅链接有效。)</span>
                </div>
                <div class="form-controls J-diyUrl" <% if(link_type != 0){ %>style="display:none;"<% } %>>
                    <input type="text" class="input xxlarge j-link" data-name="link" value="<%=initData.redirect%>" <% if(link_type != 0){ %>readonly<% } %> name="redirect">

                    <input type="hidden" name="link_type" class="J-link_type" value="" />
                    <input type="hidden" name="link_id" class="J-link_id"  value="<%=initData.link_id%>" />
                    <input type="hidden" name="link_name" class="J-link_name"  value="<%=initData.link_name%>" />


                    <span class="fi-help-text"></span>
                </div>
            </div>
            <i class="arrow arrow_out" style="margin-top: 0px;"></i>
            <i class="arrow arrow_in" style="margin-top: 0px;"></i>
            <input type="hidden" name="is_preview" value="0">
        </div>
        </form>
    </script>
    <!-- end tpl_material_ctrl -->
	
	<script>
	var list,page;
	</script>
	<!-- 自定义菜单 选择商品 -->
	<script type="text/j-template" id="tpl_menu_detail">
		
		<div id="GoodsAndGroupPicker">
			<ul class="gagp-goodslist">
				<% _.each(list,function(data){%>
					<li class="clearfix">
						<a href="<%= data.link %><%= data.urlview %>" class="fl" target="_blank" title="<%= data.title %>">
						    <div class="table-item-img">
						        <img src="<%= data.file_path %>" alt="<%= data.title %>">
						    </div>
						    <div class="table-item-info">
						        <p><%= data.title %></p>
						        <span class="price">&yen;<%= data.price %></span>
						    </div>
						</a>
						<a href="javascript:;" data-link_id="<%= data.link_id%>" class="btn fr j-select mgt10">选取</a>
					</li>
				<% }) %>
			</ul>
			<div class="clearfix mgt15">
	        	<div class="paginate fr"><%= page %></div>
	    	</div>
		</div>
	</script>    
	
	
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
	alert("${obj.id}");
	<#if obj.id=="">
	initData = false;
	<#else>
	initData = {
		link:"链接",
		title:"${obj.material.title}",
		author:"${obj.material.author}",
        datetime:"${obj.material.createtime?datetime?string('yyyy-MM-dd')}",
        content:"${obj.material.content}",
        link_type:"",
        link_id:"${obj.id}",
        link_name:"${obj.material.linkname}",
		redirect:"${obj.material.linkurl}",
		coverimg:'<#if obj.material.pic!="">${obj.material.pic}<#else>/Public/images/demo_news.gif</#if>',
		summary:"<#if obj.material.description!="">${obj.material.description}<#else>摘要</#if>"
	};
	</#if>
    </script>
    
	<script src="${base}/page/system/material/inputone.js"></script>
	<!-- 配置文件 -->
    <script src="/Public/plugins/uploadify/jquery.uploadify.min.js?ver=5429"></script>
    <script type="text/javascript" src="/Public/plugins/ueditor/ueditor.config.js"></script>
    
    <!-- 编辑器源码文件 -->
    <!--<script type="text/javascript" src="/Public/plugins/ueditor/ueditor.all.js"></script>-->
    <script type="text/javascript" src="/Public/plugins/ueditor/diyUeditor-min.js"></script>
	
	
	<!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('content', {
            autoHeight: false
        });
        ue.ready(function() {
            //设置编辑器的内容
            //ue.setContent('hello');
            //获取html内容，返回: <p>hello</p>
            //var html = ue.getContent();
            //获取纯文本内容，返回: hello
            var txt = ue.getContentTxt();
        });
        $(function(){
            $(".content-right").attr('style', 'padding:20px;');
            $("#form1").submit(function(){
                ue.sync();
            });
            //将链接菜单栏添加到相应位置(必须要有)
            var html=$(".setLinks");
            $(".fx-reset").append(html);

            //发送预览弹窗
            $('.set-yl').click(function() {
                $(":hidden[name='is_preview']").val(1);
                $("#form1").submit();
//                var html=$("#pop-set").html(),
//                    $render=$(html);
//
//                $.jBox.show({
//                    width:720,
//                    title: "发送预览",
//                    content: $render
//                });
            });
        })
    </script>
	

	
	
	
	</div>
	<!-- end script template -->
	
</body>
</html>