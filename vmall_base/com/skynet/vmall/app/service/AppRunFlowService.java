package com.skynet.vmall.app.service;

import java.sql.Timestamp;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.service.SkynetDaoService;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.constants.VMallConstants;
import com.skynet.vmall.base.pojo.RunFlow;
import com.skynet.vmall.base.pojo.RunFlowLog;
import com.skynet.vmall.base.service.RunFlowLogService;
import com.skynet.vmall.base.service.RunFlowService;

@InjectName("apprunflowService")
@IocBean(args =
{ "refer:dao" })
public class AppRunFlowService extends SkynetDaoService
{
	@Inject
	RunFlowService runflowService;

	@Inject
	RunFlowLogService runflowlogService;
	
	public AppRunFlowService()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public AppRunFlowService(Dao dao)
	{
		super(dao);
		// TODO Auto-generated constructor stub
	}
	
	public String flow_create(DynamicObject form, DynamicObject login_token)
	{
		String dataid = form.getFormatAttr("dataid");
		String flowname = form.getFormatAttr("flowname");
		String[] flow = (String[])form.getObj("flowdef");
		
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		String username = login_token.getFormatAttr(GlobalConstants.sys_login_username);
		
		// 生成流程记录
		String runflowkey = UUIDGenerator.getInstance().getNextValue();
		RunFlow runflow = new RunFlow();
		runflow.setId(runflowkey);
		runflow.setFlowname(flowname);
		runflow.setDataid(dataid);
		runflow.setUserid(userid);
		runflow.setUsername(username);
		runflow.setCreatetime(new Timestamp(System.currentTimeMillis()));
		
		sdao().insert(runflow);
		
		RunFlowLog runflowlog = new RunFlowLog();
		runflowlog.setId(UUIDGenerator.getInstance().getNextValue());
		runflowlog.setRunflowkey(runflowkey);
		runflowlog.setSname(flow[0]);
		runflowlog.setDname(flow[0]);
		runflowlog.setCtype(VMallConstants.flow_create);
		runflowlog.setSuserid(userid);
		runflowlog.setSusername(username);
		runflowlog.setDuserid(userid);
		runflowlog.setDusername(username);
		runflowlog.setCreatetime(new Timestamp(System.currentTimeMillis()));
		
		sdao().insert(runflowlog);
		
		return runflowkey;
	}
	
	public String flow_forward(DynamicObject form, DynamicObject login_token)
	{
		String runflowkey = form.getFormatAttr("runflowkey");
		String sname = form.getFormatAttr("sname");
		String dname = form.getFormatAttr("dname");

		String suserid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		String susername = login_token.getFormatAttr(GlobalConstants.sys_login_username);
		
		RunFlowLog runflowlog = new RunFlowLog();
		runflowlog.setId(UUIDGenerator.getInstance().getNextValue());
		runflowlog.setRunflowkey(runflowkey);
		runflowlog.setSname(sname);
		runflowlog.setDname(dname);
		runflowlog.setCtype(VMallConstants.flow_forward);
		runflowlog.setSuserid(suserid);
		runflowlog.setSusername(susername);
		runflowlog.setCreatetime(new Timestamp(System.currentTimeMillis()));
		
		sdao().insert(runflowlog);
		
		return runflowkey;
	}

}
