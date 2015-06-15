var citytemp;
var areatemp;
var towntemp;
function provinceData(idprovince) {
	if (typeof (idprovince) == "undefined") {
		idprovince = 1
	}
	jQuery
			.get(
					"/norder/area.action?sid=" + $("#sid").val(),
					{
						idProvince : 0
					},
					function(data) {
						var data = eval("(" + data + ")");
						if (data != null) {
							$("#address_province").empty();
							for (var i = 0; i < data.length; i++) {
								if (data[i].id == idprovince) {
									$("#province_label").text(data[i].name)
								}
								$("#address_province")
										.append(
												"<option "
														+ (idprovince == data[i].id ? "selected"
																: "")
														+ " id='option_add_"
														+ data[i].id
														+ "' value="
														+ data[i].id + ">"
														+ data[i].name
														+ "</option>")
							}
						}
						$("#address_province").change()
					})
}
var citychange = function cityData() {
	$("#province_label").text(
			$("#address_province").find("option:selected").text());
	var idprovince = $("#address_province").val();
	var idcity = citytemp;
	jQuery.get("/norder/area.action?sid=" + $("#sid").val(), {
		idProvince : idprovince
	}, function(data) {
		var data = eval("(" + data + ")");
		if (data != null) {
			$("#address_city").empty();
			for (var i = 0; i < data.length; i++) {
				if (data[i].id == idcity) {
					$("#city_label").text(data[i].name)
				}
				$("#address_city")
						.append(
								"<option "
										+ (idcity == data[i].id ? "selected"
												: "") + " id='option_add_"
										+ data[i].id + "' value=" + data[i].id
										+ ">" + data[i].name + "</option>")
			}
		}
		$("#address_city").change()
	})
};
var areachange = function areaData() {
	$("#city_label").text($("#address_city").find("option:selected").text());
	var idcity = $("#address_city").val();
	var idarea = areatemp;
	if (idcity == null) {
		$("#address_area").empty();
		$("#address_area").change();
		return
	}
	jQuery.get("/norder/area.action?sid=" + $("#sid").val(), {
		idCity : idcity
	}, function(data) {
		var data = eval("(" + data + ")");
		if (data != null) {
			$("#address_area").empty();
			for (var i = 0; i < data.length; i++) {
				if (data[i].id == idarea) {
					$("#area_label").text(data[i].name)
				}
				$("#address_area")
						.append(
								"<option "
										+ (idarea == data[i].id ? "selected"
												: "") + " id='option_add_"
										+ data[i].id + "' value=" + data[i].id
										+ ">" + data[i].name + "</option>")
			}
		}
		$("#address_area").change()
	})
};
var townchange = function townData() {
	$("#area_label").text($("#address_area").find("option:selected").text());
	var idarea = $("#address_area").val();
	var idtown = towntemp;
	if (idarea == null) {
		$("#address_town").empty();
		$("#address_town").change();
		return
	}
	jQuery.get("/norder/area.action?sid=" + $("#sid").val(), {
		idArea : idarea
	}, function(data) {
		var data = eval("(" + data + ")");
		if (!!data && data.length > 0) {
			$("#townaddress").show();
			$("#address_town").empty();
			for (var i = 0; i < data.length; i++) {
				if (data[i].id == idtown) {
					$("#town_label").text(data[i].name)
				}
				$("#address_town")
						.append(
								"<option "
										+ (idtown == data[i].id ? "selected"
												: "") + " id='option_add_"
										+ data[i].id + "' value=" + data[i].id
										+ ">" + data[i].name + "</option>")
			}
		} else {
			$("#address_town").empty();
			$("#townaddress").hide()
		}
		$("#address_town").change()
	})
};
var labelchange = function setLabel() {
	$("#town_label").text($("#address_town").find("option:selected").text());
	var c = $("#address_province").find("option:selected").text();
	var a = $("#address_city").find("option:selected").text();
	var b = $("#address_area").find("option:selected").text();
	var e = $("#address_town").find("option:selected").text();
	var d = $("#address_where").val().replace(c, "");
	d = d.replace(a, "");
	d = d.replace(b, "");
	d = d.replace(e, "");
	$("#address_where").val(d)
};
function getData(a, i, d, j, g, b, e, h, f, c) {
	$("#address_name").val(i);
	$("#address_where").val(d);
	$("#address_mobile").val(j);
	$("#address_email").val(g);
	$("#address_zip").val(b);
	citytemp = h;
	areatemp = f;
	towntemp = c;
	provinceData(e)
}
var validateName = function() {
	var a = $("#address_name").val();
	if (a == null || a.trim() == "") {
		$("#nameErrorMsg").show();
		return false
	} else {
		$("#nameErrorMsg").hide();
		return true
	}
};
var validateWhere = function() {
	var a = $("#address_where").val();
	if (a == null || a.trim() == "") {
		$("#addressErrorMsg").show();
		return false
	} else {
		$("#addressErrorMsg").hide();
		return true
	}
};
var validateAddressMobile = function() {
	var a = $("#address_mobile").val();
	var d = $("#old_address_mobile").val();
	if (a == null || a.trim() == "") {
		$("#mobileErrorMsg").text(
				"\u7535\u8bdd\u53f7\u7801\u4e0d\u80fd\u4e3a\u7a7a");
		$("#mobileErrorMsgDiv").show();
		return false
	} else {
		if (a == d) {
			$("#mobileErrorMsgDiv").hide();
			return true
		} else {
			if (!/^1(\d){10}$/.exec(a)) {
				$("#mobileErrorMsg").text(
						"\u7535\u8bdd\u53f7\u7801\u683c\u5f0f\u9519\u8bef");
				$("#mobileErrorMsgDiv").show();
				return false
			} else {
				$("#mobileErrorMsgDiv").hide();
				return true
			}
		}
	}
};
var validateSubmit = function() {
	var a = true;
	if (!validateWhere()) {
		a = false
	}
	if (!validateName()) {
		a = false
	}
	if (!validateAddressMobile()) {
		a = false
	}
	if (a) {
		return true
	} else {
		return false
	}
};