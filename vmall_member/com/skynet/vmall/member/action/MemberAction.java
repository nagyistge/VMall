package com.skynet.vmall.member.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.member.service.MemberService;

@IocBean
@At("/member/member")
public class MemberAction extends BaseAction
{
	@Inject
	private MemberService memberService;

	@At("/index")
	@Ok("->:/page/member/member/index.ftl")
	public Map index(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		DynamicObject member = memberService.locate(userid);
		ro.put("member", member);
		return ro;
	}

	@At("/follow")
	@Ok("json")
	public Map follow(String swxopenid, String swxnickname, String dwxopenid, String dwxnickname) throws Exception
	{
		try
		{
			memberService.follow(swxopenid, swxnickname, dwxopenid, dwxnickname);
			ro.put("state", "success");
		}
		catch (Exception e)
		{
			ro.put("state", "error");
			ro.put("message", e.getMessage());
		}

		return ro;
	}

	@At("/myorder")
	@Ok("->:/page/member/member/myorder.ftl")
	public Map myorder(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		DynamicObject member = memberService.locate(userid);
		ro.put("member", member);
		return ro;
	}

	@At("/browsemyorder")
	@Ok("->:/page/member/member/myorderbrowse.ftl")
	public Map browsemyorder(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		map.put("memberid", userid);
		List<DynamicObject> orders = memberService.browsemyorder(map);
		ro.put("orders", orders);
		return ro;
	}

	@At("/showordergoods")
	@Ok("->:/page/member/member/showordergoods.ftl")
	public Map showordergoods(@Param("..") Map map) throws Exception
	{
		String id = (String) map.get("id");
		List<DynamicObject> ordergoodses = memberService.showmyordergoods(map);
		ro.put("ordergoodses", ordergoodses);
		return ro;
	}

	@At("/mydraw")
	@Ok("->:/page/member/member/mydraw.ftl")
	public Map mydraw(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		DynamicObject member = memberService.locate(userid);
		ro.put("member", member);
		return ro;
	}

	@At("/mydraw/browse")
	@Ok("->:/page/member/member/mydrawbrowse.ftl")
	public Map mydrawbrowse(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		map.put("memberid", userid);
		List<DynamicObject> draws = memberService.browsemydraw(map);
		ro.put("draws", draws);
		return ro;
	}

	@At("/mygroup")
	@Ok("->:/page/member/member/mygroup/mygroup.ftl")
	public Map mygroup(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		DynamicObject member = memberService.locate(userid);
		ro.put("member", member);
		return ro;
	}
	
	@At("/mygroup/show")
	@Ok("->:/page/member/member/mygroup/showgroup.ftl")
	public Map mygroupshow(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		map.put("memberid", userid);
		List<DynamicObject> members = memberService.findsubmembers(userid, 1);
		ro.put("members", members);
		return ro;
	}
	
	@At("/mygroup/subcount")
	@Ok("json")
	public Map mygroupsubcount(@Param("..") Map map) throws Exception
	{
		String id = (String)map.get("id");
		int nums = memberService.count(Cnd.where("supid", "=", id));
		ro.clear();
		ro.put("nums", nums);
		ro.put("id", id);
		return ro;
	}
}
