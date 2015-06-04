package com.skynet.vmall.member.action;

import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.skynet.framework.action.BaseAction;
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
}
