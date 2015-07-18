package com.skynet.vmall.system.service;

import java.sql.Timestamp;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.service.SkynetDaoService;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.pojo.Material;
import com.skynet.vmall.base.pojo.MaterialItem;
import com.skynet.vmall.base.service.MaterialItemService;
import com.skynet.vmall.base.service.MaterialService;

@InjectName("appmaterialService")
@IocBean(args =
{ "refer:dao" })
public class AppMaterialService extends SkynetDaoService
{
	public AppMaterialService()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public AppMaterialService(Dao dao)
	{
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Inject
	MaterialService materialService;
	
	@Inject
	MaterialItemService materialitemService;
	
	public String insert(Map map, DynamicObject login_token)
	{
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		String username = login_token.getFormatAttr(GlobalConstants.sys_login_username);
		
		String title = (String)map.get("title");
		String summary = (String)map.get("summary");
		String content = (String)map.get("content");
		String author = (String)map.get("author");
		String redirect = (String)map.get("redirect");
		String coverimg = (String)map.get("cover_img");
		String linkname = (String)map.get("link_name");
		
		
		String id = UUIDGenerator.getInstance().getNextValue();
		Material material = new Material();
		material.setId(id);
		material.setCreater(userid);
		material.setCreatername(username);
		material.setCreatetime(new Timestamp(System.currentTimeMillis()));
		
		material.setTitle(title);
		material.setDescription(summary);
		material.setContent(content);
		material.setAuthor(author);
		material.setLinkurl(redirect);
		material.setPic(coverimg);
		material.setLinkname(linkname);		
		
		sdao().insert(material);
		
//		MaterialItem materialitem = new MaterialItem();
//		materialitem.setId(UUIDGenerator.getInstance().getNextValue());
//		materialitem.setMaterialid(id);
//		materialitem.setTitle(title);
//		materialitem.setDescription(summary);
//		materialitem.setContent(content);
//		materialitem.setAuthor(author);
//		materialitem.setLinkurl(redirect);
//		materialitem.setPic(coverimg);
//		materialitem.setLinkname(linkname);
		
//		sdao().insert(materialitem);
		
		return id;
	}
	
	public String update(Map map, DynamicObject login_token) throws Exception
	{
		String id = (String)map.get("link_id");
		String title = (String)map.get("title");
		String summary = (String)map.get("summary");
		String content = (String)map.get("content");
		String author = (String)map.get("author");
		String redirect = (String)map.get("redirect");
		String coverimg = (String)map.get("cover_img");
		String linkname = (String)map.get("link_name");
		
		Material materialitem = materialService.get(id);
		materialitem.setTitle(title);
		materialitem.setDescription(summary);
		materialitem.setContent(content);
		materialitem.setAuthor(author);
		materialitem.setLinkurl(redirect);
		materialitem.setPic(coverimg);
		materialitem.setLinkname(linkname);
		
		sdao().update(materialitem);		
		
//		MaterialItem materialitem = materialitemService.fetch(Cnd.where("materialid", "=", id));
//		materialitem.setTitle(title);
//		materialitem.setDescription(summary);
//		materialitem.setContent(content);
//		materialitem.setAuthor(author);
//		materialitem.setLinkurl(redirect);
//		materialitem.setPic(coverimg);
//		materialitem.setLinkname(linkname);
//		
//		sdao().update(materialitem);
		
		return id;
	}
}
