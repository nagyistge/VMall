package com.skynet.vmall.system.action;

import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.skynet.app.organ.service.OrganService;
import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.vmall.base.pojo.DrawCash;
import com.skynet.vmall.base.pojo.Goods;
import com.skynet.vmall.base.pojo.GoodsClassSpec;
import com.skynet.vmall.base.pojo.GoodsClassSpecValue;
import com.skynet.vmall.base.pojo.GoodsProductSpec;
import com.skynet.vmall.base.pojo.GoodsSpec;
import com.skynet.vmall.base.pojo.GoodsSpecValue;
import com.skynet.vmall.base.pojo.OrderGoodsProductSpec;
import com.skynet.vmall.base.pojo.ShopCartGoodsProductSpec;
import com.skynet.vmall.base.pojo.Supplier;

@IocBean
@At("/system/system")
public class SystemAction
{
	@Inject
	private OrganService organService;
	
	@At("/inittable")
	@Ok("json")
	public Map inittable() throws Exception
	{
		// 初始化表结构
//		Daos.createTablesInPackage(organService.dao(), "com.skynet.app.log.pojo", true);
//		Daos.createTablesInPackage(organService.dao(), "com.skynet.app.organ.pojo", true);
//		Daos.createTablesInPackage(organService.dao(), "com.skynet.vmall.base.pojo", true);
//		Daos.createTablesInPackage(organService.dao(), "com.skynet.app.dictionary.pojo", true);
//		Daos.createTablesInPackage(organService.dao(), "com.skynet.vmall.base.pojo.Event", false);
//		organService.dao().create(Event.class, true);
//		organService.dao().create(EventItem.class, true);
//		organService.dao().create(EventItemGoods.class, true);
//		organService.dao().create(EventItemMember.class, true);	

//		organService.dao().create(GoodsPrice.class, true);
		
//		organService.dao().create(Order.class, true);	
//		organService.dao().create(OrderGoods.class, true);
//		organService.dao().create(OrderGoodsProduct.class, true);
//		
//		organService.dao().create(OrderGoodsRebate.class, true);	
//		organService.dao().create(ShopCart.class, true);
//		organService.dao().create(ShopCartGoods.class, true);
//		organService.dao().create(ShopCartGoodsProduct.class, true);
		
		organService.dao().create(Supplier.class, true);
		
		organService.dao().create(DrawCash.class, true);
		organService.dao().create(GoodsClassSpec.class, true);
		organService.dao().create(GoodsClassSpecValue.class, true);
		organService.dao().create(Goods.class, true);
		organService.dao().create(GoodsSpec.class, true);
		organService.dao().create(GoodsSpecValue.class, true);		
		organService.dao().create(GoodsProductSpec.class, true);	
		organService.dao().create(OrderGoodsProductSpec.class, true);
		organService.dao().create(ShopCartGoodsProductSpec.class, true);

//		organService.dao().create(Material.class, true);
//		organService.dao().create(MaterialItem.class, true);	
//		
//		organService.dao().create(Attach.class, true);
//		organService.dao().create(AttachRef.class, true);		
//		organService.dao().create(RunFlow.class, true);
//		organService.dao().create(RunFlowLog.class, true);
		
//		Daos.createTablesInPackage(organService.dao(), "com.skynet.app.flow.pojo", true);
		DynamicObject ro = new DynamicObject();
		return ro;
	}

	public OrganService getOrganService()
	{
		return organService;
	}

	public void setOrganService(OrganService organService)
	{
		this.organService = organService;
	}
	
}
