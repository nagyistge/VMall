package com.skynet.vmall.member.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.pojo.Member;
import com.skynet.vmall.member.service.MemberService;

@IocBean
@At("/member/member")
@Filters({@By(type=CheckSession.class, args={"sys_login_token", "/checksession.html"})})	
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

	@At("/myinfo")
	@Ok("->:/page/member/member/myinfo/myinfo.ftl")
	public Map myinfo(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		DynamicObject member = memberService.locate(userid);
		ro.put("member", member);
		return ro;
	}
	
	@At("/myinfo/saveinfo")
	@Ok("json")
	public Map saveinfo(@Param("..") Member newmember) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);

		Map remap = new DynamicObject();
		try
		{
			remap = memberService.saveinfo(newmember, login_token);
			// 更新当前用户会话姓名信息
			login_token.setAttr(GlobalConstants.sys_login_username, newmember.getCname());
		}
		catch (Exception e)
		{
			System.out.println(e);
			remap.put("state", "error");
			remap.put("message", "保存个人资料异常，请稍候再试。");
		}
		
		return remap;
	}
	
	@At("/myorder")
	@Ok("->:/page/member/member/myorder/myorder.ftl")
	public Map myorder(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		DynamicObject member = memberService.locate(userid);
		ro.put("member", member);
		return ro;
	}
	
	@At("/myorder/showorder")
	@Ok("->:/page/member/member/myorder/showorder.ftl")
	public Map showmyorder(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		map.put("memberid", userid);
		List<DynamicObject> orders = memberService.showmyorder(map);
		ro.put("orders", orders);
		return ro;
	}	
	
	@At("/myorder/showordergoods")
	@Ok("->:/page/member/member/myorder/showordergoods.ftl")
	public Map showmyordergoods(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		map.put("memberid", userid);
		List<DynamicObject> ordergoodses = memberService.showmyordergoods(map);
		ro.put("ordergoodses", ordergoodses);
		return ro;
	}	

	@At("/mydraw")
	@Ok("->:/page/member/member/mydraw/mydraw.ftl")
	public Map mydraw(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		DynamicObject member = memberService.locate(userid);
		ro.put("member", member);
		return ro;
	}

	@At("/mydraw/showdraw")
	@Ok("->:/page/member/member/mydraw/showdraw.ftl")
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
	
	@At("/mygroup/lookother")
	@Ok("->:/page/member/member/mygroup/lookother.ftl")
	public Map lookother(@Param("..") Map map) throws Exception
	{
		String userid = (String)map.get("id");
		DynamicObject member = memberService.locate(userid);
		ro.put("member", member);
		return ro;
	}	
	
	@At("/myrebate")
	@Ok("->:/page/member/member/myrebate/myrebate.ftl")
	public Map myrebate(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		DynamicObject member = memberService.locate(userid);
		ro.put("member", member);
		return ro;
	}
	
	@At("/myrebate/showbygroup")
	@AdaptBy(type = JsonAdaptor.class)		
	@Ok("->:/page/member/member/myrebate/showbygroup.ftl")
	public Map myrebateshowbygroup(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		map.put("memberid", userid);
		List<DynamicObject> rebates = memberService.myrebateshowbygroup(map);
		ro.put("rebates", rebates);
		return ro;
	}
	
	@At("/myrebate/showbygoods")
	@AdaptBy(type = JsonAdaptor.class)		
	@Ok("->:/page/member/member/myrebate/showbygoods.ftl")
	public Map myrebateshowbygoods(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		map.put("memberid", userid);
		List<DynamicObject> rebates = memberService.myrebateshowbygoods(map);
		ro.put("rebates", rebates);
		return ro;
	}
	
	@At("/myrebate/showbyorder")
	@AdaptBy(type = JsonAdaptor.class)		
	@Ok("->:/page/member/member/myrebate/showbyorder.ftl")
	public Map myrebateshowbyorder(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		map.put("memberid", userid);
		List<DynamicObject> rebates = memberService.myrebateshowbyorder(map);
		ro.put("rebates", rebates);
		return ro;
	}	

	@At("/myrebate/showsum")
	@AdaptBy(type = JsonAdaptor.class)	
	@Ok("json")
	public Map myrebateshowsum(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		map.put("memberid", userid);
		BigDecimal score = memberService.myrebateshowsum(map);
		Map remap = new DynamicObject();
		remap.put("score", score);
		return remap;
	}
	
}
