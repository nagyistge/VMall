package com.skynet.vmall.goods.action;

import java.util.Map;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.skynet.framework.action.BaseAction;

@IocBean
@At("/goods/goodsclass")
public class GoodsClassAction extends BaseAction
{
	@At("/index")
	@Ok("->:/page/goods/goodsclass/index.ftl")
	public Map index(@Param("..") Map map) throws Exception
	{
		return ro;
	}
}
