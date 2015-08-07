	//添加编辑单条图文
	$(function(){
	/////
	
    var tpl_material_con=$("#tpl_material_con").html();//手机模板
    var tpl_material_ctrl=$("#tpl_material_ctrl").html();//控制模板
    
    //默认数据
	var defaults={
		link:"链接",
		title:"标题",
        datetime:"",
        link_type:"",
        link_id:"",
        link_name:"",
		redirect:"",
		coverimg:"/Public/images/demo_news.gif",
		summary:"摘要"
	};


	//如果初始化数据为空，则设置默认参数
	if(!initData){
        initData=defaults;
	}

	//输出数据到页面
	var setVal=function(){
		$("#j-initData").val(JSON.stringify(initData));
    }

	//渲染预览视图
	var reRender_material_con=function(){
		var html=_.template(tpl_material_con,initData);
		$("#j-render-con").empty().append(html);
		setVal();
	};

	//渲染控制视图
	var reRender_material_ctrl=function(){
		var html=_.template(tpl_material_ctrl,initData);
		$("#j-render-ctrl").empty().append(html);
		
		console.log(initData['link_id']);

	        //选择文件
	        $("#j-imgcover").uploadify({
	            "debug": false,
	            "auto": true,
	            "formData": {'cclass':'Material', 'kid':'test'},	            
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
	                
	                var data = eval('(' + data + ')')
	                initData["coverimg"] = data.file_path;
	                $("#cover_img").val(data.file_path);
	                reRender_material_con();
	              
	            },
	            onUploadError: function(file, errorCode, errorMsg, errorString) {
	                HYD.hint("danger", "对不起：" + file.name + "上传失败：" + errorString);
	            },
	            'onUploadStart': function (file) {  
                    $("#j-imgcover").uploadify("settings", "formData", {'cclass':'Material', 'kid':initData['link_id']});  
                    //在onUploadStart事件中，也就是上传之前，把参数写好传递到后台。  
                }  
	        });			

	        
			if(initData==false || initData['link_id']=="")
			{
				$("#j-imgcover").hide();
			}
			else
			{
				$("#j-imgcover").show();
			}
		


        setVal();

	};
    
    //首次加载渲染所有视图
	reRender_material_con();
	reRender_material_ctrl();
    
	//标题文字重新渲染
	$(".j-renderEle").keyup(function(){
        var name=$(this).data("name"),
            val=$(this).val();

        initData[name]=val;//更新值
        reRender_material_con();//重新渲染预览视图
    }).keyup();
	
	//封面图片重新渲染
	var imgUrl = $("#img-cover-img").attr("src");
    if(imgUrl){
        initData["coverimg"] = imgUrl;
        reRender_material_con();
    }
    
 // 设置链接
    var tpl={
        menu_tab:$("#tpl_menu_tab").html(), //自定义菜单列表切换
        menu:$("#tpl_menu_lst").html(), //自定义菜单列表
        menu_ump:$("#tpl_menu_ump").html(), //自定义菜单营销活动列表
        menu_detail:$("#tpl_menu_detail").html(), //自定义菜单商品列表
        menu_group:$("#tpl_menu_group").html(), //自定义菜单商品分组列表
        menu_magazine:$("#tpl_menu_magazine").html(), //自定义菜单专题列表
        menu_sort:$("#tpl_menu_sort").html() //自定义菜单专题分类列表
    };
    
 // 隐藏显示菜单链接选项
    $(".fx-reset").hover(function() {
        $(this).children('.setLinks').show();
    }, function() {
        $(this).children('.setLinks').hide();
    });
    
 // 点击设置链接
    $("#setLinks>li>a").click(function() {
        var type=$(this).data('type');
        var key=$(this).data('key');
        var link_type=$(this).data('keys');
        var link_id=$(this).data('link_id');
        var $this=$(this);
        var titleText=$(this).text()+" | ";
        $(".j-show-btn").find('b').html(titleText);
        $('.J-diyUrl').data('link_type', link_type);
        
        console.log("key:"+key);
        if(type==0){
                //赋值link_type
                initData['link_type'] = link_type;
                $('.J-link_type').val(link_type);
                var TextVal="链接到"+$this.html();
            if(key!="diy"){
                var url=$this.data('url');

                initData['link_id'] = link_id;
                initData['link_name'] = TextVal;
                initData['redirect'] = url;
                $('.J-link_id').val(link_id);
                $('.J-link_name').val(TextVal);


                $(".j-show-btn").find('em').html(TextVal);
                $(".J-diyUrl").hide();
                $(".j-link").val(url).attr('readonly', 'readonly');
                $this.parents(".setLinks").hide();
            }else{
                $(".J-diyUrl").show();
                $(".j-show-btn").find('em').html(TextVal);

                initData['link_id'] = link_id;
                initData['link_name'] = TextVal;
                $('.J-link_id').val(link_id);
                $('.J-link_name').val(TextVal);

                $(".j-link").removeAttr('readonly').val("").focus();
                $this.parents(".setLinks").hide();
            }

        }else{
        	console.log("ajax begin.");
            $.ajax({
                url: "/vmallback/mall/mall/ajaxmenu.action?t="+(new Date().getTime()),
                type: "post",
                dataType: "json",
                data: {
                    "key": key
                },
                beforeSend: function() {
                    $.jBox.showloading();
                	console.log("ajax loading....");
                },
                success: function(data) {
                   if(key=="ump"){
                        //var html_head=_.template(tpl.menu_tab,data);
                        var html_head=$this.html();
                        var html=_.template(tpl.menu_tab,data);//渲染营销活动模板
                    }else if(key=="detail"){
                    	console.log("hello detail.");
                    	console.log(data);
                        var html_head=$this.html();
                        list = data;
                        var html=_.template(tpl.menu_detail,data);//渲染商品模板
                    }else if(key=="group"){
                        var html_head=$this.html();
                        var html=_.template(tpl.menu,data);//渲染商品分组模板
                    }else if(key=="magazine"){
                        var html_head=$this.html();
                        var html=_.template(tpl.menu,data);//渲染专题模板
                    }else if(key=="sort"){
                        var html_head=$this.html();
                        var html=_.template(tpl.menu,data);//渲染专题分类模板
                    };
                    
                    //console.log(html);
                    
                    $.jBox.show({
                        title: html_head,
                        content: html,
                        btnOK: {show:false},
                        btnCancel:{show:false},
                        onOpen:function(jbox){
                            $.jBox.hideloading();
                        }
                    });
                }
            });
            $this.parents(".setLinks").hide();
        };
    });    
    
    
 // 选择链接
    $(document).on('click', '.j-select', function(event) {
        if ($("#GoodsAndGroupPicker>ul>li>a:first>div").length != 2) {
            //var _this=$(this).parents("tr").find('td:first>input[type=hidden]');
        	console.log($(this).prev("a"));
        	console.log($(this).prev("a").attr("href"));
            var LinkVal=$(this).prev("a").attr("href");
            var link_id=$(this).data('link_id');
            var TextVal="链接到"+$(this).prev("a").text();
            var link_type = $('.J-diyUrl').data('link_type');
            
            $(".J-diyUrl").hide();

            //赋值link_type
            initData['link_type'] = link_type;
            initData['link_id'] = link_id;
            initData['link_name'] = TextVal;
            initData['redirect'] = LinkVal;
            $('.J-link_type').val(link_type);
            $('.J-link_id').val(link_id);
            $('.J-link_name').val(TextVal);

            $(".j-show-btn").find('em').html(TextVal);
            $(".j-link").val(LinkVal);
            $(".jbox").remove();
            $("#jbox-overlay").hide();
        } else{
        	
        	console.log($(this).prev("a"));
        	console.log($(this).prev("a").attr("href"));
        	
            //var _this=$(this).prev("a").attr('href');
            var LinkVal=$(this).prev("a").attr('href');
            var link_id=$(this).data('link_id');
            var TextVal="链接到"+$(this).prev("a").find('p').text();
            var link_type = $('.J-diyUrl').data('link_type');
            
            $(".J-diyUrl").hide();

            //赋值link_type
            initData['link_type'] = link_type;
            initData['link_id'] = link_id;
            initData['link_name'] = TextVal;
            initData['redirect'] = LinkVal;
            $('.J-link_type').val(link_type);
            $('.J-link_id').val(link_id);
            $('.J-link_name').val(TextVal);

            $(".j-show-btn").find('em').html(TextVal);
            $(".j-link").val(LinkVal);
            $(".jbox").remove();
            $("#jbox-overlay").hide();
        }
    });
	
    /////
	});