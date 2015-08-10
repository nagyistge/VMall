<html>
<head>
<title>优品365.营销管理.微信群发</title>
</head>
<body>
<link rel="stylesheet" href="/Public/css/dist/home/PubMarketing/pubMarketing.css">
<link rel="stylesheet" href="/Public/plugins/jbox/jbox-min.css">

<h1 class="content-right-title">微信群发<a class="gicon-info-sign gicon_linkother" href="" target="_blank"></a></h1>
    
    
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
<script src="${base}/public/js/dist/component-min.js"></script>
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
	<div style="text-align:right;"><a href="${base}/system/material/inputone.action" class="btn btn-success btn-small" target="_blank">添加单条图文</a></div>
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
				<img src="${base}/<%= coverimg %>" >
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

<script>
//选择发送图文消息通用代码
//选择发送图文消息通用代码
$(function(){
	//编辑器实例化
	var ue = UE.getEditor('js_editorArea',{
		toolbars: [
			['RemoveFormat','Link', 'Unlink']
		],
		initialFrameHeight:200,
		maximumWords:600
	});
	$("#ss").click(function(){
		console.log($("input[name=message_type]:checked").val())
	});
	$('.Jfastbtn').click(function(event) {
        var me = $(this);
        var con = me.data('con');
        var ueCon = ue.getContent();
        ueCon = ueCon+con;
        ue.setContent(ueCon);
    });
	//模板
	var tpl={
		text_pre:$("#tpl_materialPicker_text_pre").html(),//文本预览
		single_table:$("#tpl_materialPicker_single_table").html(),//单图文列表
		single_pre:$("#tpl_materialPicker_single_pre").html(),//单图文预览
		mutil_table:$("#tpl_materialPicker_mutil_table").html(),//多图文列表
		mutil_pre:$("#tpl_materialPicker_mutil_pre").html()//多图文预览
	};

	//设置单条、多条图文消息的id到页面的input中
	var setVal=function(data){
        $("#j-initDataID").val(data.id);

	};

	//渲染文本预览视图
	var reRenderMaterialPre_Text=function(data){
		if(!data.length) return;
		var html=_.template(tpl.text_pre,{summary:data});//渲染模板
		$("#j-materialPrev").empty().append(html);//插入dom
	}

	//渲染单图文预览视图
	var reRenderMaterialPre_Single=function(data){
		var html=_.template(tpl.single_pre,data);//渲染模板
		$("#j-materialPrev").empty().append(html);//插入dom
		setVal(data);//设置数据内容到input中
	};

	//渲染多图文预览视图
	var reRenderMaterialPre_Mutil=function(data){
		var html=_.template(tpl.mutil_pre,data);//渲染模板
		$("#j-materialPrev").empty().append(html);//插入dom
		setVal(data);//设置数据内容到input中
	};

	//选择单图文事件
	var do_pickerMaterial_Single=function(){
		HYD.ajaxPopTable({
			title:"选择单条图文",
	        url:"${base}/system/material/jsonlist.action",
	        tpl:tpl.single_table,
	        onPageChange:function(jbox,ajaxdata){
	        	//选择事件
	        	jbox.find(".j-select").click(function(){
	        		var index=$(this).parents("tr").index();//获取索引
	        		reRenderMaterialPre_Single(ajaxdata.list[index]);//渲染对应视图
	        		$.jBox.close(jbox);
                    $("#j-initDataID").val(ajaxdata.list[index].material_one_id);
	        	});

	        }
	    });
	};

	//选择多图文事件
	var do_pickerMaterial_Mutil=function(){
		HYD.ajaxPopTable({
			title:"选择多条图文",
	        url:"/MaterialMore/jsonList",
	        tpl:tpl.mutil_table,
	        onPageChange:function(jbox,ajaxdata){
	        	//选择事件
	        	jbox.find(".j-select").click(function(){
	        		var index=$(this).parents("tr").index();//获取索引
	        		reRenderMaterialPre_Mutil(ajaxdata.list[index]);//渲染对应视图
	        		$.jBox.close(jbox);
                    $("#j-initDataID").val(ajaxdata.list[index].material_more_id);
                });
	        }
	    });
	};


	//文本图文消息的实时预览
	/*$("#j-materialText").keyup(function(){
		reRenderMaterialPre_Text($(this).val());
	}).keyup();*/
	ue.addListener( "selectionchange", function () {
		var _html = UE.getEditor('js_editorArea').getContent();
		reRenderMaterialPre_Text(_html);
		$('#j-materialText').val(_html);
	});
	$("#j-selectMaterialSingle").click(do_pickerMaterial_Single);//重新选择单条图文
	$("#j-selectMaterialMutil").click(do_pickerMaterial_Mutil);//重新选择多条图文

	//如果有图文id存在则渲染 [编辑状态]
	var initDataID=$("#j-initDataID").val();
	if(initDataID){
		var url="",//接口
			doAction=null,//执行的动作
			type=$(".j-sendType:checked").data("type");//图文类型

		switch(type){
			case 2:
				doAction=reRenderMaterialPre_Single;
				url="/MaterialOne/jsonList";
				break;
			case 3:
				doAction=reRenderMaterialPre_Mutil;
				url="/MaterialMore/jsonList";
				break;
		}
		//异步获取图文数据，然后渲染
        if(url){
            $.ajax({
                url: url,
                type: "post",
                dataType: "json",
                data: {
                    "id": initDataID
                },
                success: function(data) {
                    if (data.status == 1) {
                        doAction(data.list[0]);
                    } else {
                        HYD.hint("danger", "对不起，获取数据：" + data.msg);
                    }
                }
            });
        }
	}

    //切换选择的类型
    var materialId = $("#material_id").val();
	//var _html = $("#j-materialText").val();
    $(".j-sendType").click(function(){
        if(!$(this).is(":checked")) return;

        //if(!initDataID){
        //    $("#j-materialPrev").empty();//清空之前的预览数据
        //    $("#j-initDataID").val("");//清空选择的图文消息id
        //}

        var type=$(this).data("type");//选中的类型
        $(".j-sendTypeCon[data-type='"+type+"']").show().siblings(".j-sendTypeCon").hide();
        if(!materialId){
            // 自动打开选择器
            switch(type){
                case 1:reRenderMaterialPre_Text($("#j-materialText").val());break;
                case 2:do_pickerMaterial_Single();break;
                case 3:do_pickerMaterial_Mutil();break;
            }
        }
        materialId = 0;

    }).change();


	//发送类型为文本则直接渲染预览视图 [编辑状态]
	if($(".j-sendType:checked").data("type")==1){
		reRenderMaterialPre_Text($("#j-materialText").val());
		ue.addListener("ready",function(){ue.setContent($("#j-materialText").val());});
	}

	$('.ptck_box label').click(function(){
		if($(this).children(':checkbox').is(":checked")){
			$(this).children('i').addClass('act');
		}else{
			$(this).children('i').removeClass('act');
		}
	});
	$('.ptrd_box label').click(function(){
		if($(this).children('input:radio').val() ==1){
			$('.ptrd_box label').children('span').children('i').removeClass('act');
			$(this).children('span').children('.wenb').addClass('act');
		}else if($(this).children('input:radio').val() ==2){
			$('.ptrd_box label').children('span').children('i').removeClass('act');
			$(this).children('span').children('.onewenb').addClass('act');
		}else if($(this).children('input:radio').val() ==3){
			$('.ptrd_box label').children('span').children('i').removeClass('act');
			$(this).children('span').children('.morewenb').addClass('act');
		}
	});



});

</script>
</body>
</html>