package com.skynet.app.flow.service;

import java.sql.Timestamp;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.flow.pojo.BAct;
import com.skynet.app.flow.pojo.FlowLog;
import com.skynet.app.flow.pojo.RFlow;
import com.skynet.app.flow.spec.FlowConstants;
import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.service.SkynetDaoService;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.spec.GlobalConstants;



@InjectName("appflowService")
@IocBean(args =
{ "refer:dao" })
public class AppFlowService extends SkynetDaoService
{
	@Inject
	BFlowService bflowService;
	
	@Inject
	BActService bactService;	
	
	@Inject
	RFlowService rflowService;

	@Inject
	FlowLogService flowlogService;
	
	public AppFlowService()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public AppFlowService(Dao dao)
	{
		super(dao);
		// TODO Auto-generated constructor stub
	}
	
	public String create(DynamicObject swapflow, DynamicObject login_token)
	{
		String bflowid = swapflow.getFormatAttr("bflowid");
		String dataid = swapflow.getFormatAttr("dataid");
		
		BAct sbact = bactService.fetch(Cnd.where("bflowid", "=", bflowid).and("sno", "=", 1));
		String sname = sbact.getCname();
		String sbactid = sbact.getId();
		
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		String username = login_token.getFormatAttr(GlobalConstants.sys_login_username);
		
		// 生成流程记录
		String runflowkey = UUIDGenerator.getInstance().getNextValue();
		RFlow rflow = new RFlow();
		rflow.setRunflowkey(runflowkey);
		rflow.setBflowid(bflowid);
		rflow.setDataid(dataid);
		rflow.setUserid(userid);
		rflow.setUsername(username);
		rflow.setCreatetime(new Timestamp(System.currentTimeMillis()));
		rflow.setState(sname);
		
		sdao().insert(rflow);
		
		FlowLog flowlog = new FlowLog();
		flowlog.setId(UUIDGenerator.getInstance().getNextValue());
		flowlog.setRunflowkey(runflowkey);
		flowlog.setBflowid(bflowid);
		flowlog.setDataid(dataid);
		flowlog.setCtype(FlowConstants.flow_create);
		flowlog.setSbactid(sbactid);
		flowlog.setDbactid(sbactid);
		flowlog.setSname(sname);
		flowlog.setDname(sname);
		flowlog.setSuserid(userid);
		flowlog.setSusername(username);
		flowlog.setDuserid(userid);
		flowlog.setDusername(username);
		flowlog.setCreatetime(new Timestamp(System.currentTimeMillis()));
		flowlog.setIsuse("Y");
		
		sdao().update(FlowLog.class, Chain.make("isuse", "N"), Cnd.where("runflowkey", "=", runflowkey));
		
		sdao().insert(flowlog);
		
		return runflowkey;
	}
	
	public String forward(DynamicObject swapflow, DynamicObject login_token) throws Exception
	{
		String runflowkey = swapflow.getFormatAttr("runflowkey");
		String sname = swapflow.getFormatAttr("sname");
		// String dname = swapflow.getFormatAttr("dname");
		
		// 检查流程已结束异常
		if ("结束".equals(sname))
		{
			throw new Exception("流程已结束，不能再转发！");
		}		

		RFlow rflow = rflowService.fetch(runflowkey);
		
		// 检查流程已结束异常
		if ("结束".equals(rflow.getState()))
		{
			throw new Exception("流程已结束，不能再转发！");
		}
		
		String bflowid = rflow.getBflowid();
		String dataid = rflow.getDataid();
		
		BAct sbact = bactService.fetch(Cnd.where("bflowid", "=", bflowid).and("cname", "=", sname));
		String sbactid = sbact.getId();
		int sno = sbact.getSno();
		BAct dbact = bactService.fetch(Cnd.where("bflowid", "=", bflowid).and("sno", "=", sno+1));
		String dbactid = dbact.getId();
		String dname = dbact.getCname();

		String suserid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		String susername = login_token.getFormatAttr(GlobalConstants.sys_login_username);
		
		FlowLog flowlog = new FlowLog();
		flowlog.setId(UUIDGenerator.getInstance().getNextValue());
		flowlog.setRunflowkey(runflowkey);
		flowlog.setBflowid(rflow.getBflowid());
		flowlog.setDataid(dataid);
		flowlog.setCtype(FlowConstants.flow_forward);
		flowlog.setSbactid(sbactid);
		flowlog.setDbactid(dbactid);
		flowlog.setSname(sname);
		flowlog.setDname(dname);
		flowlog.setSuserid(suserid);
		flowlog.setSusername(susername);
		flowlog.setCreatetime(new Timestamp(System.currentTimeMillis()));
		flowlog.setIsuse("Y");
		
		sdao().update(FlowLog.class, Chain.make("isuse", "N"), Cnd.where("runflowkey", "=", runflowkey));		
		
		sdao().insert(flowlog);
		
		rflow.setState(dname);
		sdao().update(rflow);
		
		return dname;
	}
	
	public String backward(DynamicObject swapflow, DynamicObject login_token) throws Exception
	{
		String runflowkey = swapflow.getFormatAttr("runflowkey");
		String sname = swapflow.getFormatAttr("sname");
		// String dname = swapflow.getFormatAttr("dname");
		
		// 检查流程已结束异常
		if ("结束".equals(sname))
		{
			throw new Exception("流程已结束，不能再回退！");
		}		

		RFlow rflow = rflowService.fetch(runflowkey);
		
		// 检查流程已结束异常
		if ("结束".equals(rflow.getState()))
		{
			throw new Exception("流程已结束，不能再回退！");
		}
		
		String bflowid = rflow.getBflowid();
		String dataid = rflow.getDataid();
		
		BAct sbact = bactService.fetch(Cnd.where("bflowid", "=", bflowid).and("cname", "=", sname));
		String sbactid = sbact.getId();
		int sno = sbact.getSno();
		
		if(sno==1)
		{
			throw new Exception("流程在起始节点，不能再回退！");
		}
		
		BAct dbact = bactService.fetch(Cnd.where("bflowid", "=", bflowid).and("sno", "=", sno-1));
		String dbactid = dbact.getId();
		String dname = dbact.getCname();

		String suserid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		String susername = login_token.getFormatAttr(GlobalConstants.sys_login_username);
		
		FlowLog flowlog = new FlowLog();
		flowlog.setId(UUIDGenerator.getInstance().getNextValue());
		flowlog.setRunflowkey(runflowkey);
		flowlog.setBflowid(rflow.getBflowid());
		flowlog.setDataid(dataid);
		flowlog.setCtype(FlowConstants.flow_forward);
		flowlog.setSbactid(sbactid);
		flowlog.setDbactid(dbactid);
		flowlog.setSname(sname);
		flowlog.setDname(dname);
		flowlog.setSuserid(suserid);
		flowlog.setSusername(susername);
		flowlog.setCreatetime(new Timestamp(System.currentTimeMillis()));
		flowlog.setIsuse("Y");
		
		sdao().update(FlowLog.class, Chain.make("isuse", "N"), Cnd.where("runflowkey", "=", runflowkey));		
		
		sdao().insert(flowlog);
		
		rflow.setState(dname);
		sdao().update(rflow);
		
		return dname;
	}

}
