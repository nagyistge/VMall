package com.skynet.vmall.member.action;

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
		catch(Exception e)
		{
			ro.put("state", "error");
			ro.put("message", e.getMessage());
		}
		
		return ro;
	}
	
	@At("/group")
	@Ok("->:/page/member/member/group.ftl")
	public Map group(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		List<DynamicObject> members = memberService.findsubmembers(userid, 3);
		ro.put("members", members);
		return ro;
	}	
}
