package com.skynet.vmall.frame.action;

import java.util.Map;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.skynet.framework.action.BaseAction;


@IocBean
@At("/frame/frame")
public class FrameAction extends BaseAction
{
	@At("/menu_goods")
	@Ok("->:/page/frame/menu_goods.ftl")
	public Map menu_goods() throws Exception
	{
		return ro;
	}
}
