package com.skynet.vmall.finance.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.skynet.app.flow.pojo.BAct;
import com.skynet.app.flow.pojo.BFlow;
import com.skynet.app.flow.service.BActService;
import com.skynet.app.flow.service.BFlowService;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.Types;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.constants.VMallConstants;
import com.skynet.vmall.base.service.DrawCashService;
import com.skynet.vmall.base.service.MemberService;
import com.skynet.vmall.finance.service.AppDrawCashService;

@IocBean
@At("/finance/drawcash")
@Filters(
{ @By(type = CheckSession.class, args =
{ "sys_login_token", "/author/login/log.action" }) })
public class DrawCashAction
{
	@Inject
	BFlowService bflowService;

	@Inject
	BActService bactService;
	
	@Inject
	private MemberService memberService;	

	@Inject
	private DrawCashService drawcashService;

	@Inject
	private AppDrawCashService appdrawcashService;

	@At("/browse")
	@Ok("->:/page/finance/drawcash/browse.ftl")
	public Map browse(@Param("..") Map map, @Param("_page") String page, @Param("_pagesize") String pagesize) throws Exception
	{
		map.put("_page", Types.parseInt(page, 1));
		map.put("_pagesize", Types.parseInt(pagesize, VMallConstants.pagesize));

		List<DynamicObject> drawcashes = appdrawcashService.browse(map);

		DynamicObject ro = new DynamicObject();

		ro.put("drawcashes", drawcashes);
		ro.setAttr("state", (String) map.get("state"));

		ro.put("_page", map.get("_page"));
		ro.put("_pagesize", map.get("_pagesize"));
		ro.put("_maxpage", map.get("_maxpage"));
		ro.put("_startpage", map.get("_startpage"));
		ro.put("_endpage", map.get("_endpage"));

		return ro;
	}

	@At("/locate")
	@Ok("->:/page/finance/drawcash/locate.ftl")
	public Map locate(String id) throws Exception
	{
		DynamicObject drawcash = drawcashService.locate(id);
		String flowstate = drawcash.getFormatAttr("state");
		BFlow bflow = bflowService.fetch(Cnd.where("cname", "=", VMallConstants.flow_drawcash_name));
		String bflowid = bflow.getId();
		BAct bact = bactService.fetch(Cnd.where("bflowid", "=", bflowid).and("cname", "=", flowstate));

		int sno = bact.getSno();
		String flownextstate = flowstate;
		String flowbackstate = flowstate;

		if (sno > 1)
		{
			flowbackstate = bactService.fetch(Cnd.where("bflowid", "=", bflowid).and("sno", "=", (sno - 1))).getCname();
		}

		if (!("结束".equals(bact.getCname())))
		{
			flownextstate = bactService.fetch(Cnd.where("bflowid", "=", bflowid).and("sno", "=", (sno + 1))).getCname();
		}
		
		DynamicObject drawcashmember = memberService.locate(drawcash.getFormatAttr("memberid"));

		Map map = new DynamicObject();
		map.put("drawcash", drawcash);

		DynamicObject ro = new DynamicObject();

		set_author(map, ro);

		ro.put("drawcashmember", drawcashmember);
		ro.put("drawcash", drawcash);
		ro.put("flowstate", flowstate);
		ro.put("flownextstate", flownextstate);
		ro.put("flowbackstate", flowbackstate);

		return ro;
	}

	@At("/forward")
	@Ok("json")
	public Map forward(String id) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		DynamicObject form = new DynamicObject();
		form.setAttr("id", id);
		Map map = appdrawcashService.foward(form, login_token);
		return map;
	}

	// 设置操作权限
	public void set_author(Map map, DynamicObject ro) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		map.put("login_token", login_token);

		boolean isread = false; // 是否可读
		boolean isedit = false; // 是否可修改
		boolean issave = issave(map); // 是否可保存

		boolean isforward = isforward(map); // 是否可转发
		boolean isbackward = isbackward(map); // 是否可回退

		boolean issavepay = true; // 是否可修改付款信息

		ro.put("isread", isread);
		ro.put("isedit", isedit);
		ro.put("issave", issave);
		ro.put("isforward", isforward);
		ro.put("isbackward", isbackward);

		ro.put("issavepay", issavepay);
	}

	// 保存功能权限
	public boolean issave(Map map) throws Exception
	{
		boolean sign = true;

		DynamicObject drawcash = (DynamicObject) map.get("drawcash");
		DynamicObject login_token = (DynamicObject) map.get("login_token");

		// 提现单处于申请状态
		if (!"申请".equals(drawcash.getFormatAttr("state")))
		{
			sign = false;
			return sign;
		}

		return sign;
	}

	// 转发功能权限
	public boolean isforward(Map map) throws Exception
	{
		boolean sign = true;

		DynamicObject drawcash = (DynamicObject) map.get("drawcash");
		DynamicObject login_token = (DynamicObject) map.get("login_token");

		// 评审处于评审状态；
		if ("结束".equals(drawcash.getFormatAttr("state")))
		{
			sign = false;
			return sign;
		}

		return sign;
	}

	// 退回功能权限
	public boolean isbackward(Map map) throws Exception
	{
		boolean sign = true;

		DynamicObject drawcash = (DynamicObject) map.get("drawcash");
		DynamicObject login_token = (DynamicObject) map.get("login_token");

		if ("申请".equals(drawcash.getFormatAttr("state")))
		{
			sign = false;
			return sign;
		}

		if ("结束".equals(drawcash.getFormatAttr("state")))
		{
			sign = false;
			return sign;
		}

		return sign;
	}

	@At("/leftmenu")
	@Ok("->:/page/finance/leftmenu.ftl")
	public Map lefmenu(String id) throws Exception
	{
		DynamicObject ro = new DynamicObject();
		return ro;
	}

}
