package com.skynet.vmall.system.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.skynet.framework.common.generator.TimeGenerator;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.pojo.Material;
import com.skynet.vmall.base.service.MaterialService;
import com.skynet.vmall.system.service.AppMaterialService;

@IocBean
@At("/system/material")
@Filters(
{ @By(type = CheckSession.class, args =
{ "sys_login_token", "/author/login/log.action" }) })
public class MaterialAction
{
	@Inject
	MaterialService materialService;
	
//	@Inject
//	MaterialItemService materialitemService;
	
	@Inject
	AppMaterialService appmaterialService;
	
	@At("/jsonlist")
	@Ok("json")
	public Map jsonlist(@Param("..") Map map) throws Exception
	{
		List<Material> materials = materialService.sdao().query(Material.class, Cnd.where("1", "=", "1"));
		
		StringBuffer s = new StringBuffer();
		int nums = materials.size();
		for(int i=0;i<nums;i++)
		{
			Material materialitem = materials.get(i);
			s.append(" { ");
			s.append("\"material_one_id\":\""+materialitem.getId()).append("\",");	
			s.append("\"title\":\""+materialitem.getTitle()).append("\",");
			s.append("\"summary\":\""+materialitem.getDescription()).append("\",");
			s.append("\"content\":\""+materialitem.getContent()).append("\",");
			s.append("\"link\":\""+materialitem.getLinkurl()).append("\",");
			s.append("\"coverimg\":\""+materialitem.getPic()).append("\",");
			s.append("\"datetime\":\""+TimeGenerator.getDateStr()).append("\"");
		
			s.append(" } ");
			if(i<nums-1)
			{
				s.append(",");
			}
		}
		
		String str = "{\"status\":1,\"list\":["+s.toString()+"],\"page\":\"\"}";
		Map ro = (Map)Json.fromJson(str);
		
		return ro;
	}
	
	@At("/inputone")
	@Ok("->:/page/system/material/inputone.ftl")
	public Map inputone(@Param("..") Map map) throws Exception
	{
		Map ro = new DynamicObject();
		ro.put("id", "");
		return ro;
	}
	
	@At("/saveone")
	@Ok("->:/page/system/material/inputone.ftl")
	public Map saveone(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		
		String id = (String)map.get("link_id");
		if(StringToolKit.isBlank(id))
		{
			id = appmaterialService.insert(map, login_token);
		}
		else
		{
			id = appmaterialService.update(map, login_token);
		}
		
		DynamicObject material = materialService.locate(id);
		
		Map ro = new DynamicObject();
		ro.put("id", id);
		ro.put("material", material);

		return ro;
	}


}
