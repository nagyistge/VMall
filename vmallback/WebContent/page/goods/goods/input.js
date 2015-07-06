$(function () {

    norms = [],
    props = {},
    propsAlias = {},
    propsIds = [],
    sku = {},
    skuCache = {},
    skustyle = {
        skustyle: $(".j-skustyle:checked").val()
    };
    var a = function(a) {
        var b = $("#tpl_add_step2_sku").html(),
        c = _.template(b, {
            sku: sku,
            norms: norms,
            props: propsAlias,
            skustyle: skustyle
        });
        $("#j-skuPanel .wxtables").remove(),
        $("#j-skuPanel").append(c),
        $("#j-skuVal").val(JSON.stringify(sku)),
        $(".J-options-slideToggle").on("click",
        function() {
            $(this).parent().children(".setfxs-box").toggle()
        }),
        a && a()
    },
    b = function(a) {
        if (skuCache[a]) sku[a] = skuCache[a];
        else {
            var b = a.split(";"),
            c = b.join("-");
            skuCache[a] = sku[a] = {
                skuId: 0,
                o_price: 0,
                price: 0,
                stock: 0,
                code: "",
                salenum: 0,
                props: b,
                rank_props: c,
                rank_price: [],
                sku_img: ""
            }
        }
    },
    c = function(a) {
        var b = $("#j-totalStock");
        if (norms.length) {
            b.attr("disabled", !0);
            var c = 0;
            for (var d in sku) c += parseInt(sku[d].stock);
            b.val(c)
        } else b.attr("disabled", !1)
    },
    d = function(a) {
        var b, c, e, f, g = [],
        h = [];
        if (f = a.split(";"), h.push(a), f.length === propsIds.length) return h;
        for (b = 0; b < propsIds.length; b++) {
            for (c = 0; c < propsIds[b].length && f.length > 0 && propsIds[b][c] != f[0]; c++);
            if (! (c < propsIds[b].length && f.length > 0)) {
                for (e = 0; e < propsIds[b].length; e++) h = h.concat(d(g.concat(propsIds[b][e], f).join(";")));
                break
            }
            g.push(f.shift())
        }
        return h
    },
    e = function() {
        norms = [],
        propsIds = [],
        sku = {},
        propsCurNum = [],
        $(".j-selectProps").each(function() {
            var a = [],
            b = $(this).find(".j-normsid").val(),
            c = $(this).find(".j-normsid").data("name");
            $(this).find(".j-propid:checked").each(function() {
                a.push($(this).val())
            }),
            propsCurNum.push(a),
            a.length && (propsIds.push(a), norms.push({
                id: b,
                name: c,
                props: a
            }))
        }),
        $("#j-normsVal").val(JSON.stringify(norms));
        var a = propsIds.length,
        c = propsCurNum.length;
        a == c && _.each(propsIds[0],
        function(c) {
            var e = d(c.toString());
            _.each(e,
            function(c) {
                var d = c.split(";");
                d.length == a && b(c)
            })
        })
    },
    f = function() {
        $(".j-propid").each(function() {
            propsAlias[$(this).siblings("span").data("v")] = $(this).siblings("span").find("input").val() ? $(this).siblings("span").find("input").val() : $(this).siblings("span").data("n")
        }),
        $("#j-propsAlias").val(JSON.stringify(propsAlias)),
        a()
    },
    g = $("#j-propsVal").val();
    g.length && (props = $.parseJSON(g));
    var h = $("#j-normsVal").val();
    h.length && (norms = $.parseJSON(h));
    var i = $("#j-skuVal").val();
    i.length && (skuCache = sku = $.parseJSON(i), a()),
    c(),
    f(),
    $(document).on("change", ".j-price-modify",
    function() {
        var a = $(this).val(),
        b = $(this).data("name"),
        d = $(this).parents("tr").data("key");
        skuCache[d][b] = sku[d][b] = a,
        c(),
        $("#j-skuVal").val(JSON.stringify(sku))
    }),
    $(document).on("click", ".j-title-selectimg",
    function(a) {
        var b = $(this),
        d = b.parents("tr").data("key");
        /*
        HYD.popbox.ImgPicker(function(a) {
            b.find("img").attr("src", a[0]),
            skuCache[d].sku_img = sku[d].sku_img = a[0],
            c(),
            $("#j-skuVal").val(JSON.stringify(sku))
        })
        */
    }),
    $(document).on("change", ".j-propid",
    function() {
        var b = $(this).parents(".j-selectProps"),
        d = b.find(".j-propid"),
        g = b.find(".j-normsid");
        $(this).attr("checked") ? $(this).siblings("span").html('<input type="text" value="' + $(this).siblings("span").data("n") + '" class="J-aliasV w40" />') : $(this).attr("checked") || $(this).siblings("span").html($(this).siblings("span").data("n")),
        g.attr("checked", d.length == d.filter(":checked").length),
        f(),
        e(),
        a(),
        c()
    }),
    $(document).on("change", ".j-skustyle",
    function(b) {
        var d = $(this).val();
        skustyle.skustyle = d,
        f(),
        e(),
        a(),
        c()
    }),
    $(document).on("change", ".J-aliasV",
    function() {
        f()
    }),
    $(document).on("change", ".j-normsid",
    function() {
        var a = $(this),
        b = a.parents(".j-selectProps").find(".j-propid");
        b.attr("checked", a.is(":checked")).change()
    })
	

	
	$("#bt_submit").click(function() {page_submit()});
	
	function page_submit()
	{
		
		$("#add_step2").submit();
		// $("#add_step2").submit();
	}
})