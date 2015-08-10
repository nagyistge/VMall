$(function(){

	$("#bt_submit").click(function() {page_submit()});
    $("a.addspec").click(function() {page_addspec()}); // 添加规格

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
        $(":checkbox.checkspec").each(function(index){$(this).change(function() {page_checkspec();})}); // 选中规格
        $(":checkbox.checkspecvalue").each(function(index){$(this).change(function() {page_checkspecvalue();})}); // 选中型号
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
				console.log(pdspecs);
				checked_specvalues = pdspecs;
				console.log(checked_specvalues);
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
		console.log("checkspec begin.");
		
		var e = event.target;
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
	
	function page_checkspecvalue()
	{
		
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
})