package com.skynet.vmall.order.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.pojo.DrawCash;
import com.skynet.vmall.member.service.MemberService;
import com.skynet.vmall.order.service.DrawCashService;

@IocBean
@At("/order/drawcash")
public class DrawCashAction extends BaseAction
{
	@Inject
	private DrawCashService drawcashService;
	
	@Inject
	private MemberService memberService;	
	
	@At("/memberdraw")
	@Ok("->:/page/order/drawcash/memberdraw.ftl")
	public Map memberdraw(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		map.put("memberid", userid);
		List<DynamicObject> drawcashs = drawcashService.browse(map);
		ro.put("drawcashs", drawcashs);
		return ro;
	}
	
	@At("/apply")
	@Ok("->:/page/order/drawcash/apply.ftl")
	public Map apply(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		
		DynamicObject member = memberService.locate(userid);
		ro.put("member", member);
	
		return ro;
	}	
	
	@At("/insert")
	@Ok(">>:locate.action?id=${obj.id}")
	public Map insert(@Param("..") DrawCash drawcash) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		String username = login_token.getFormatAttr(GlobalConstants.sys_login_username);
		String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);
		
		drawcash.setMemberid(userid);
		drawcash.setMembercname(username);
		drawcash.setMemberwxopenid(userwxopenid);
		
		String drawcashid = drawcashService.insert(drawcash);
		
		ro.put("id", drawcashid); 
		
		return ro;
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