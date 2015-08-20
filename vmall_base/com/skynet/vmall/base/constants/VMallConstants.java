package com.skynet.vmall.base.constants;

public class VMallConstants
{
	public final static String vmallname = "四季豆.微商城"; // 商城名称
	
	public static int pagesize = 10; // 单页显示记录行数
	
	public static int pagenavnums = 10; // 分页导航页码个数	
	
	public static final String flow_order_name = "订单";
	
	public static final String flow_drawcash_name = "提现";
	
	public static final String rootdir_upload_default = "e:\\upload";

	public static final String rootdir_upload_web_default = "/upload";	
	
	public static final String[] flow_order = new String[]
	{ "下单", "收款", "发货", "收货", "结算", "结束" };
	public static final String[] flow_drawcash = new String[]
	{ "申请", "受理", "审核", "付款", "结束" };
	
	public static final String flow_create = "创建";
	
	public static final String flow_forward = "转发";
	
	public static final String flow_backward = "退回";
	
	public static final String svr_domianname = "www.tiangouvmall.com";
	
	public static final String app_webcontext = "vmall";
}
