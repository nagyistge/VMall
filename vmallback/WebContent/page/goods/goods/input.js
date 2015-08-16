$(function () {

	$("#bt_submit").click(function() {page_submit()});
	$("#bt_selectdealer").click(function() {page_selectdealer()});
	$("#bt_selectsupplier").click(function() {page_selectsupplier()});
	
	
	function page_selectdealer()
	{
        $.ajax({
            url: "/vmallback/goods/goods/selectdealer.action?t="+(new Date().getTime()),
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
            	var html=_.template($("#tpl_selectdealer").html(), json);
            	console.log(html);
            	
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
	
	function page_submit()
	{
		$("#add_step2").submit();
	}

})

	function page_setsupplier()
	{
		var e = event.target;
		var $e = $(e);
		$("input[name='supplierid']").val($e.parent().attr("dataid"));
		$("input[name='suppliername']").val($e.parent().attr("cname"));
	}

