<html>
<head>
<title>优品365.营销管理.微信群发</title>
</head>
<body>
<link rel="stylesheet" href="${base}/page/mall/message/pubMarketing.css">

<h1 class="content-right-title">微信群发<a class="gicon-info-sign gicon_linkother" href="http://www.wifenxiao.com/Index/help_show/lm/help/id/62.html" target="_blank"></a></h1>
    
    
    <div class="title_tab" id="topTab">
        <ul class="tab_navs title_tab" data-index="0">
            <li data-index="0" class="tab_nav first js_top selected" data-id="media10" data-type="0"><a href="/Ump/massSend">微信群发</a></li>
            <li data-index="1" class="tab_nav  js_top " data-id="media2" data-type="1"><a href="/Ump/sendLog">群发记录</a></li>
        </ul>
    </div>
    
    <div class="new-tit"></div>

    <form id="form1" method="post" action="${base}/mall/message/sendmsg.action">
	<div class="relative">
		<div class="formitems mgb10" style="display:none;">
			<label class="fi-name"><span class="colorRed">*</span>群发平台：</label> 
			<div class="form-controls">
				<div class="checkbox-group ptck_box">
					<label><input type="checkbox" name="pub_type[]" value="1"><i class="pt_ico wx act"></i>微信</label>
				  <label><input type="checkbox" name="pub_type[]" value="2"><i class="pt_ico fwc "></i>服务窗</label>
					<label><input type="checkbox" name="pub_type[]" value="3"><i class="pt_ico wb "></i>微博</label>
				</div>
			</div>
		</div>
		
		<div class="formitems">  
			<label class="fi-name"><span class="colorRed">*</span>标题：</label>
			<div class="form-controls">
				<input type="text" class="input xxlarge" name="title">
				<span class="fi-help-text"></span>
			</div>
		</div>

		<div class="formitems">
	<label class="fi-name"><span class="colorRed">*</span>类型：</label> 
	<div class="form-controls">
		<div class="radio-group ptrd_box">
			<label><input type="radio" name="message_type" class="j-sendType" data-type="1" value="1" checked><span><i class="pt_ico wenb act"></i><br/>文本内容</span></label>
			<label><input type="radio" name="message_type" class="j-sendType" data-type="2" value="2" ><span><i class="pt_ico onewenb"></i><br/>单条图文</span></label>
			<label><input type="radio" name="message_type" class="j-sendType" data-type="3" value="3" ><span><i class="pt_ico morewenb"></i><br/>多条图文</span></label>
			
        </div>
		<span class="fi-help-text"></span>
	</div>
</div><div class="formitems">
	<div class="form-controls j-sendTypeCon" data-type="1" style="display:block;">
    	<input name="content" type="hidden" id="j-materialText" value=''/>
    	<div id="js_editorArea"></div>
    	    	<!--<label class="fi-name"><span class="colorRed">*</span>内容：</label>
		<textarea name="content" class="textarea" id="j-materialText"></textarea>
		<span class="fi-help-text"></span>-->
	</div>
	<!-- end 文本内容 -->

	<div class="form-controls j-sendTypeCon pdt5" data-type="2" style="display:none;">
		<!--<a href="javascript:;" class="btn btn-primary btn-small" id="j-selectMaterialSingle">重新选择图文素材</a>-->
		<!--<a href="/MaterialOne/add" class="btn btn-success btn-small" target="_blank">添加单条图文</a>-->
	</div>
	<!-- end 单条图文 -->

	<div class="form-controls j-sendTypeCon pdt5" data-type="3" style="display:none;">
		<!--<a href="javascript:;" class="btn btn-primary btn-small" id="j-selectMaterialMutil">重新选择图文素材</a>-->
		<!--<a href="/MaterialMore/add" class="btn btn-success btn-small" target="_blank">添加多条图文</a>-->
	</div>
	<!-- end 多条图文 -->

	<input type="hidden" id="j-initDataID" value="" name="material_id"><!-- 用于储存选中的图文消息的id -->
	<input type="hidden" id="material_id" value=""><!-- 用于储存选中的图文消息的id -->

</div>



		<!-- 小箭头 -->
		<i class="arrow arrow_out"></i>
		<i class="arrow arrow_in"></i>
	</div>
    <!--手机-->
    <div id="diy-phone">
	<div class="diy-phone-header">
		<i class="diy-phone-receiver"></i>
		<div class="diy-phone-title j-pagetitle"></div>
	</div>
	<div class="diy-phone-contain" id="j-materialPrev"></div>
	<i class="diy-phone-footer"></i>
</div>
    <div class="clear"></div>
    <div class="mgt40 mgb10 bt_g">
        <input type="hidden" name="send_type" value="1">
        <a href="javascript:;" id="all-send" onclick="" class="btn bigbutton btn-primary">群发接口发送</a>
        <a href="javascript:;" id="custom-send" onclick="" class="btn bigbutton btn-warning">客服接口发送</a>
    </div>
    </form>

</div>


<script src="${base}/public/js/dist/lib-min.js"></script>
<script src="${base}/public/plugins/jbox/jquery.jbox-min.js"></script>
<script src="${base}/public/plugins/zclip/jquery.zclip-min.js"></script>
<script src="${base}/public/plugins/uploadify/jquery.uploadify.min.js"></script>

<script>
$(function(){
	$("#leftMenu").load('${base}/page/mall/leftmenu.ftl');
});
</script>


    <script src="${base}/public/plugins/ueditor/ueditor.config.js"></script>
    <script src="${base}/public/plugins/ueditor/ueditor.all.js"></script>
    <script src="${base}/public/plugins/ueditor/ueditor.noimg.js"></script>
	<script src="${base}/public/js/dist/common_materialPicker.js"></script>
	<script src="${base}/public/js/jquery.form.js"></script>
    <script>
        $(function(){
            $o = $(":checkbox[value='1']").prop("checked", true);
            $("#form1>.relative").attr('style', 'width:554px;float:right;');
            $("#j-materialText").attr('style','width:524px;');
            $("#all-send").click(function(){
                $.jBox.show({
                    title: "群发接口发送",
                    content: '会员每月只能接收4条群发消息，是否立即发送？',
                    btnOK: {
                        onBtnClick: function(jbox) {
                            $.jBox.close(jbox);
                            $.jBox.showloading();
                            $(":hidden[name='send_type']").val(1);
                            
                            $('#form1').ajaxSubmit(function(data){
                                $.jBox.hideloading();
                                HYD.hint("warning",data.info);
                            });
                        }
                    }
                });

                return false;
            });

            $("#custom-send").click(function(){
                $.jBox.show({
                    title: "客服接口发送",
                    content: '使用该接口没有接收次数限制，且点击图文消息能直接进行链接跳转，但是只有48小时内与公众号有过交互的会员才能收到，是否立即发送？',
                    btnOK: {
                        onBtnClick: function(jbox) {
                            $.jBox.close(jbox);
                            $.jBox.showloading();
                            $(":hidden[name='send_type']").val(2);
                            $('#form1').ajaxSubmit(function(data){
                                $.jBox.hideloading();
                                console.log(data);
                                // HYD.hint("warning",data.info);
                            });
                        }
                    }
                });

                return false;
            });

            $(".content-right").addClass('pd20');
        })
    </script>



	<!--图文素材弹窗选择器 -->
<!-- start 本文图文 -->
<script type="text/j-template" id="tpl_materialPicker_text_pre">
	<dl class="materialPrePanel mgt20">
		<dt>
			<div class="single-summary pd10"><%= summary %></div>
		</dt>
	</dl>
</script>
<!-- end 本文图文 -->

<!-- start 单条图文选择器 -->
<script type="text/j-template" id="tpl_materialPicker_single_table">
	<div style="text-align:right;"><a href="/MaterialOne/add" class="btn btn-success btn-small" target="_blank">添加单条图文</a></div>
	<table class="wxtables mgt15" style="width:650px;">
        <thead>
            <tr>
                <td>标题</td>
                <td>创建时间</td>
                <td>操作</td>
            </tr>
        </thead>
        <tbody>
	        <% if(list.length){ %>
	        	<% _.each(list,function(item){ %>
		            <tr>
		            	<td>
		            		<div class="ng ng_single">
							    <div class="ng_item">
							        <div class="td_cont with_label">
							            <span class="label label-success">图文</span>
							            <div class="text">
							                <a href="<%= item.link %>" target="_blank" class="part new_window" title="<%= item.title %>"><%= item.title %></a>
							            </div>
							        </div>
							    </div>
							    <div class="ng_item view_more">
							        <a href="<%= item.link %>" class="td_cont clearfix new_window">
							            <span class="pull-left">阅读全文</span>
							            <span class="pull-right">&gt;</span>
							        </a>
							    </div>
							</div>
		            	</td>
		            	<td><%= item.datetime %></td>
		            	<td><a href="javascript:;" class="btn btn-small btn-primary j-select">选择</a></td>
		            </tr>
	            <% }) %>
	        <% }else{ %>
				<tr><td colspan="4" class="txtCenter">暂无数据</td></tr>
        	<% } %>
        </tbody>
    </table>

    <div class="clearfix mgt15">
        <div class="paginate fr"><%= page %></div>
    </div>
</script>

<script type="text/j-template" id="tpl_materialPicker_single_pre">
	<dl class="materialPrePanel mgt20" style="border: 1px solid #E7E7EB;">
		<dt>
			<h1 class="single-title first-t"><%= title %></h1>
			<p class="single-datetime first-d"><%= datetime %></p>
			<div class="cover-wrap">
				<img src="<%= coverimg %>" >
			</div>
			<p class="single-summary first-p"><%= summary %></p>
			<a href="<%= link %>" target="_blank" class="single-link clearfix first-a">
				<span class="fl">阅读全文</span>
				<span class="fr symbol">&gt;</span>
			</a>
		</dt>
	</dl>
</script>
<!-- end 单条图文选择器 -->

<!-- start 多条图文选择器 -->
<script type="text/j-template" id="tpl_materialPicker_mutil_table">
	<div style="text-align:right;"><a href="/MaterialMore/add" class="btn btn-success btn-small" target="_blank">添加多条图文</a></div>
	<table class="wxtables mgt15" style="width:650px;">
        <thead>
            <tr>
                <td>标题</td>
                <td>创建时间</td>
                <td>操作</td>
            </tr>
        </thead>
        <tbody>
        	<% if(list.length){ %>
	        	<% _.each(list,function(item){ %>
		            <tr>
		            	<td>
		            		<div class="ng ng_multiple">
							    <div class="ng_item">
							        <div class="td_cont with_label">
							            <span class="label label-success">图文1</span>
							            <div class="text">
							                <a href="<%= item.link %>" target="_blank" class="part new_window" title="<%= item.title %>"><%= item.title %></a>
							            </div>
							        </div>
							    </div>
							    <% _.each(item.dataset,function(subitem){ %>
								    <div class="ng_item">
								        <div class="td_cont with_label">
								            <span class="label label-success">图文2</span>
								            <div class="text">
								                <a href="<%= subitem.link %>" target="_blank" class="part new_window" title="<%= subitem.title %>"><%= subitem.title %></a>
								            </div>
								        </div>
								    </div>
							    <% }) %>
							</div>
		            	</td>
		            	<td><%= item.datetime %></td>
		            	<td><a href="javascript:;" class="btn btn-small btn-primary j-select">选择</a></td>
		            </tr>
	            <% }) %>
            <% }else{ %>
				<tr><td colspan="4" class="txtCenter">暂无数据</td></tr>
        	<% } %>
        </tbody>
    </table>

    <div class="clearfix mgt15">
        <div class="paginate fr"><%= page %></div>
    </div>
</script>

<script type="text/j-template" id="tpl_materialPicker_mutil_pre">
	<dl class="materialPrePanel mgt20 bgcfff border">
		<dt class="mb10 mt10">
			<a href="<%= redirect %>" target="_blank">
				<div class="cover-wrap">
					<img src="<%= coverimg %>" class="img-cover">
				</div>
				<h2 class="w262"><%= title %></h2>
			</a>
		</dt>
		<% _.each(dataset,function(item){ %>
			<dd class="newWidth">
				<a class="border-top_1 p" href="<%= item.link %>" target="_blank">
					<h3><%= item.title %></h3>
					<div class="pic"><img src="<%= item.img %>" alt=""></div>
				</a>
			</dd>
		<% }) %>
	</dl>
</script>
<!-- end 多条图文选择器 -->



</body>
</html>