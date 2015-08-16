$(function(){

	$("#bt_submit").click(function() {page_submit()});
	$("#bt_onsale").click(function() {page_onsale()});
	$("#bt_offsale").click(function() {page_offsale()});
	
    $("a.addspec").click(function() {page_addspec()}); // 添加规格
    
	$("#bt_selectsupplier").click(function() {page_selectsupplier()});    
 
    page_showspecvalue();

    function page_initspecclass()
    {
    	console.log("page_initspecclass begin.");
        var i = 0;
    	$("input.checkspec").each(function(){
    		console.log("initspecclass " + i);
    		
    		checked_specclass[i] = {"specclass":$(this).attr("specclass")};
    		i++;

    	});
    	console.log("page_initspecclass end.");
    }
    
    function page_initsku()
    {
        $("a.addspecvalue").each(function(index){$(this).click(function() {page_addspecvalue();})}); // 添加规格型号
        $(":checkbox.checkspec").each(function(index){$(this).click(function() {page_checkspec();})}); // 选中规格
        $(":checkbox.checkspecvalue").each(function(index){$(this).click(function() {page_checkspecvalue();})}); // 选中型号

        $("a.defspec").each(function(index){$(this).click(function() {page_defspec();})}); // 添加规格型号
    }
    
    function page_showspecvalue()
    {
    	console.log("page_showspecvalue begin.");
    	var goodsid = $("input[name='id']").val();
		$.ajax({
			type:'POST',
			url:'/vmallback/goods/goods/getspecvalue.action',
			data:{"goodsid":goodsid},
			cache:false,
			async:true,
			success:function(data)
			{
				console.log(data);
				if(data=="")
				{
					return;
				}
				specvalues = eval("(" + data + ")");
				$("#div_specvalues").html(_.template($("#tpl_item_specvalue").html(), specvalues));
				page_initspecclass();
				page_showsku();
				page_initsku();
			},
			error:function(data)
			{
				console.log(data);
				console.log("服务请求异常！");
			}
		})    	
		
    	console.log("page_showspecvalue end.");
    }
    
    function page_showsku()
    {
		console.log("showsku begin.");
    	var goodsid = $("input[name='id']").val();
		$.ajax({
			type:'POST',
			url:'/vmallback/goods/goods/getpdspec.action',
			data:{"goodsid":goodsid},
			cache:false,
			async:true,
			success:function(data)
			{
				console.log(data);
				if(data=="")
				{
					return;
				}
				pdspecs = eval("(" + data + ")");
				checked_specvalues = pdspecs;
				$("#div_sku").html(_.template($("#tpl_item_sku").html()));
				page_initsku();
			},
			error:function(data)
			{
				console.log(data);
				console.log("服务请求异常！");
			}
		}) 
		
		console.log("showsku end.");
    	
    }
    
    
	function page_addspec()
	{
        $.jBox.show({
            title: "添加规格",
            content: _.template($("#tpl_add_spec").html()),
            btnOK: {
            	onBtnClick: function(a) {
            		var goodsid = $("input[name='id']").val();
            		var specclass = a.find("input[name='specclass']").val();
            		
            		console.log("goodsid:"+goodsid);
            		console.log("specclass:"+specclass);

            		if($.trim(specclass)=="")
            		{
            			return false;
            		}
            		
            		$.jBox.close(a);
            		
            		$.ajax({
    					type:'POST',
    					url:'/vmallback/goods/goods/addspec.action',
    					data:{"goodsid":goodsid, "specclass":specclass},
    					cache:false,
    					async:true,
    					success:function(data)
    					{
    						console.log(data);
    						if(data=="")
    						{
    							return;
    						}

    						page_showspecvalue();	

    					},
    					error:function(data)
    					{
    						console.log(data);
    						console.log("服务请求异常！");
    					}
    				})
            	}
            }
        })
	}
	
	function page_addspecvalue()
	{
		var e = event.target;
		current_specclass = e.getAttribute("specclass");
        $.jBox.show({
            title: "添加型号",
            content: _.template($("#tpl_add_specvalue").html(), current_specclass),
            btnOK: {
            	onBtnClick: function(a) {
            		var goodsid = $("input[name='id']").val();
            		var specclass = a.find("input[name='specclass']").val();
            		var spec = a.find("input[name='spec']").val();
            		
            		console.log("goodsid:"+goodsid);
            		console.log("specclass:"+specclass);
            		console.log("spec:"+spec);
            		
            		if($.trim(specclass)=="")
            		{
            			return false;
            		}
            		
            		if($.trim(spec)=="")
            		{
            			return false;
            		}
            		
            		$.jBox.close(a);
            		
            		$.ajax({
    					type:'POST',
    					url:'/vmallback/goods/goods/addspecvalue.action',
    					data:{"goodsid":goodsid, "specclass":specclass,"spec":spec},
    					cache:false,
    					async:true,
    					success:function(data)
    					{
    						console.log(data);
    						if(data=="")
    						{
    							return;
    						}

    						page_showspecvalue();	
    						page_initsku();
    					},
    					error:function(data)
    					{
    						console.log(data);
    						console.log("服务请求异常！");
    					}
    				})
            	}
            }
        })
	}
	
	function page_checkspec()
	{
		var e = event.target;
		var $e = $(e);
		$e.closest("dl").find(".checkspecvalue").each(function(){
			$(this).prop("checked", $(e).prop("checked"));
			page_checkspecvalue();
		});		
	}
	
	function page_checkspecvalue()
	{
		console.log("checkspec begin.");
		
		var i = 0;
		var indexs = [];
		$("input.checkspec").each(function(){
			
			var j = 0;
			var values = [];
			$(this).closest("dl").find(".checkspecvalue:checked").each(function(){
				values[j] = {"value":$(this).val()};
				j++;	
			});
			
			checked_specclass[i] = {"specclass":$(this).attr("specclass"), "specvalues":values};
			console.log(checked_specclass);
			indexs[i] = 0;
			i++;
		});
		
		var s = checked_specclass;
		var rows = [];
		set(s, 0, checked_specclass.length, indexs, rows);
		checked_specvalues = rows;
		$("#div_sku").html(_.template($("#tpl_item_sku").html()));
		
		console.log("checkspec end.");
	}
	
	function set(s, level, maxlevel, indexs, rows)
	{
		var cmax = (s[level].specvalues).length;
		for(var i=0;i<cmax;i++)
		{
			if(level==maxlevel-1)
			{
				console.log(indexs);
				
				var row = {};
				var rowvalues = [];
				for(var j=0;j<maxlevel;j++)
				{
					var avalue = (s[j].specvalues)[indexs[j]].value;
					var aspecclass = s[j].specclass;
					var ajson = {};
					ajson[aspecclass] = avalue;
					rowvalues[j] = ajson;
				}
				
				row["specvalues"] = rowvalues;
				
				console.log(row);
				rows.push(row);
			}
			else
			{
				set(s, level+1, maxlevel, indexs, rows);
			}
			
			indexs[level]=(indexs[level]+1)%cmax;
		}
	}  

    function page_submit()
	{
    	var s = JSON.stringify(checked_specvalues);
    	console.log(s);
    	$("input[name='specproducts']").val(s);
		$("#mainform").submit();
	}
    
    function page_onsale()
	{
    	var goodsid = $("input[name='id']").val();
    	
		$.ajax({
			type:'POST',
			url:'/vmallback/goods/goods/onsale.action',
			data:{"goodsid":goodsid},
			cache:false,
			async:true,
			success:function(data)
			{
				console.log(data);
				if(data=="")
				{
					pub_alert("商品上架异常，请检查后再试。");
					return;
				}
				
				var json = eval("(" + data + ")");
				if(json.state=="success")
				{
					pub_alert("商品成功上架！");
					//window.location.reload();
				}
				else
				{
					pub_alert(json.message);
				}
				
			},
			error:function(data)
			{
				console.log(data);
				console.log("服务请求异常！");
			}
		})    	
	}
    
    function page_offsale()
	{
    	var goodsid = $("input[name='id']").val();
    	
		$.ajax({
			type:'POST',
			url:'/vmallback/goods/goods/offsale.action',
			data:{"goodsid":goodsid},
			cache:false,
			async:true,
			success:function(data)
			{
				console.log(data);
				if(data=="")
				{
					pub_alert("商品下架异常，请检查后再试。");
					return;
				}
				
				var json = eval("(" + data + ")");
				if(json.state=="success")
				{
					pub_alert("商品下架完成！");
					//window.location.reload();
				}
				else
				{
					pub_alert(json.message);
				}
			},
			error:function(data)
			{
				console.log(data);
				console.log("服务请求异常！");
			}
		})    	
	}
    
    function page_defspec()
	{
    	var e = event.target;
    	var $e = $(e);
    	var goodsid = $e.attr("goodsid");
    	
		$.ajax({
			type:'POST',
			url:'/vmallback/goods/goods/defspec.action',
			data:{"goodsid":goodsid},
			cache:false,
			async:true,
			success:function(data)
			{
				console.log(data);
				if(data=="")
				{
					return;
				}
				
				var json = eval("(" + data + ")");
				if(json.state=="success")
				{
					//window.location.reload();
					$("a.defspec").each(function(){$(this).html("否")});
					$e.html("是");
				}
				else
				{
					pub_alert(json.message);
				}
			},
			error:function(data)
			{
				console.log(data);
				console.log("服务请求异常！");
			}
		})    	
	}
    
	function page_selectsupplier()
	{
        $.ajax({
            url: "/vmallback/goods/goods/selectsupplier.action?t="+(new Date().getTime()),
            type: "post",
            beforeSend: function() {
                $.jBox.showloading();
            	console.log("ajax loading....");
            },
            success: function(data) {
            	console.log(data);
            	var json = eval("(" + data + ")");
            	console.log(json);
            	var html_head="选择厂商";
            	var html=_.template($("#tpl_selectsupplier").html(), json);
            	console.log(html);
            	
                $.jBox.show({
                    title: html_head,
                    content: html,
                    btnOK: {
                    	onBtnClick: function(a) {
                    		$.jBox.close(a);
                    	}
                    },
                    btnCancel:{show:false},
                    onOpen:function(jbox){
                        $.jBox.hideloading();
                    }
                });
            	
            }
        });
	}
})

function page_setsupplier()
{
	var e = event.target;
	var $e = $(e);
	$("input[name='supplierid']").val($e.parent().attr("dataid"));
	$("input[name='suppliername']").val($e.parent().attr("cname"));
}