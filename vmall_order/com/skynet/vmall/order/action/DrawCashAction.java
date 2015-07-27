package com.skynet.vmall.order.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.author.AuthorService;
import com.skynet.vmall.base.filter.LogFilter;
import com.skynet.vmall.base.pojo.DrawCash;
import com.skynet.vmall.base.service.DrawCashService;
import com.skynet.vmall.base.service.MemberService;
import com.skynet.vmall.member.service.AppMemberService;
import com.skynet.vmall.order.service.AppDrawCashService;

@IocBean
@At("/order/drawcash")
@Filters(
{ @By(type = CheckSession.class, args =
{ "sys_login_token", "/checksession.html" }) })
public class DrawCashAction extends BaseAction
{
	@Inject
	private DrawCashService drawcashService;

	@Inject
	private MemberService memberService;

	@Inject
	private AppDrawCashService appdrawcashService;

	@Inject
	private AppMemberService appmemberService;

	@At("/memberdraw")
	@Ok("->:/page/order/drawcash/memberdraw.ftl")
	public Map memberdraw(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		map.put("memberid", userid);
		List<DynamicObject> drawcashs = appdrawcashService.browse(map);
		ro.put("drawcashs", drawcashs);
		return ro;
	}

	@At("/apply")
	@Ok("->:/page/order/drawcash/apply.ftl")
	@Filters(
	{ @By(type = LogFilter.class, args =
	{ "提现申请" }), @By(type = CheckSession.class, args =
	{ "sys_login_token", "/checksession.html" }) })
	public Map apply(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);

		String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);
		String keysignature = AuthorService.encode(AuthorService.gentext(AuthorService.getip(Mvcs.getReq()), userwxopenid));

		DynamicObject member = memberService.locate(userid);

		ro.put("member", member);
		ro.put("keysignature", keysignature);
		return ro;
	}

	@At("/insert")
	@Ok("json")
	@Filters(
	{ @By(type = LogFilter.class, args =
	{ "提现记录" }), @By(type = CheckSession.class, args =
	{ "sys_login_token", "/checksession.html" }) })
	public Map insert(@Param("..") DrawCash drawcash, String keysignature) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		String username = login_token.getFormatAttr(GlobalConstants.sys_login_username);
		String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);

		drawcash.setMemberid(userid);
		drawcash.setMembercname(username);
		drawcash.setMemberwxopenid(userwxopenid);

		Map remap = new DynamicObject();

		try
		{
			String decode = AuthorService.decode(keysignature);
			String ip = AuthorService.getip(Mvcs.getReq());
			remap = appdrawcashService.checksignature(decode, ip, userwxopenid);
			if (!("success".equals(remap.get("state"))))
			{
				//return remap;
			}

			remap = appdrawcashService.insert(drawcash, login_token, decode);
		}
		catch (Exception e)
		{
			System.out.println(e);
			remap.put("state", "error");
			remap.put("message", "申请提现异常，请稍后再试。");
		}

		return remap;
	}

	@At("/locate")
	@Ok("->:/page/order/drawcash/locate.ftl")
	public Map locate(@Param("id") String id) throws Exception
	{
		DynamicObject drawcash = drawcashService.locate(id);
		ro.put("drawcash", drawcash);
		return ro;
	}

}
