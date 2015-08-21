package com.skynet.vmall.goods.action;

import java.util.Map;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;

@IocBean
@At("/goods/goodsclass")
public class GoodsClassAction
{
	@At("/index")
	@Ok("->:/page/goods/goodsclass/index.ftl")
	public Map index(@Param("..") Map map) throws Exception
	{
		DynamicObject ro = new DynamicObject();
		return ro;
	}
}
