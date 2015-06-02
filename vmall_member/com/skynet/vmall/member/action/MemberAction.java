package com.skynet.vmall.member.action;

import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.skynet.framework.action.BaseAction;
import com.skynet.vmall.member.service.MemberService;

@IocBean
@At("/member/member")
public class MemberAction extends BaseAction
{
	@Inject
	private MemberService memberService;
	
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
