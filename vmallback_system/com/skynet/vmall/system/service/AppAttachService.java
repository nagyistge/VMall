package com.skynet.vmall.system.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.service.SkynetDaoService;
import com.skynet.vmall.base.pojo.Attach;
import com.skynet.vmall.base.pojo.AttachRef;

@InjectName("appattachService")
@IocBean(args =
{ "refer:dao" })
public class AppAttachService extends SkynetDaoService
{
	public AppAttachService()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public AppAttachService(Dao dao)
	{
		super(dao);
		// TODO Auto-generated constructor stub
	}

	public void uploadref(Attach attach, String kid, String cclass) throws Exception
	{
		attach.setId(UUIDGenerator.getInstance().getNextValue());
		sdao().insert(attach);
		AttachRef AttachRef = new AttachRef();
		AttachRef.setId(UUIDGenerator.getInstance().getNextValue());
		AttachRef.setAttachid(attach.getId());
		AttachRef.setAttachname(attach.getCname());
		AttachRef.setCclass(cclass);
		AttachRef.setKid(kid);
		sdao().insert(AttachRef);
	}

}
