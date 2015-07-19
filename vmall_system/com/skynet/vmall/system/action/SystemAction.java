package com.skynet.vmall.system.action;

import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.skynet.app.organ.service.OrganService;
import com.skynet.framework.action.BaseAction;
import com.skynet.vmall.base.pojo.Attach;
import com.skynet.vmall.base.pojo.AttachRef;
import com.skynet.vmall.base.pojo.Material;
import com.skynet.vmall.base.pojo.MaterialItem;
import com.skynet.vmall.base.pojo.Order;
import com.skynet.vmall.base.pojo.OrderGoods;
import com.skynet.vmall.base.pojo.OrderGoodsRebate;
import com.skynet.vmall.base.pojo.ShopCart;
import com.skynet.vmall.base.pojo.ShopCartGoods;

@IocBean
@At("/system/system")
public class SystemAction extends BaseAction
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
		
		organService.dao().create(Order.class, true);	
		organService.dao().create(OrderGoods.class, true);		
		organService.dao().create(OrderGoodsRebate.class, true);	
		organService.dao().create(ShopCart.class, true);
		organService.dao().create(ShopCartGoods.class, true);
		
		organService.dao().create(Material.class, true);
		organService.dao().create(MaterialItem.class, true);	
		
		organService.dao().create(Attach.class, true);
		organService.dao().create(AttachRef.class, true);		
//		organService.dao().create(RunFlow.class, true);
//		organService.dao().create(RunFlowLog.class, true);
		
//		Daos.createTablesInPackage(organService.dao(), "com.skynet.app.flow.pojo", true);
		
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
