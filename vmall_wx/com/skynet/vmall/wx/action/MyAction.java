package com.skynet.vmall.wx.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;

import com.blue.wxmp.sdk.handle.WxHandler;
import com.blue.wxmp.sdk.mvc.AbstractMsgAction;
import com.skynet.app.organ.service.UserService;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.member.service.MemberService;

@IocBean
public class MyAction extends AbstractMsgAction
{
	@Inject
	protected WxHandler myHandle;
	
	@Inject
	WXActionHelper myWxHelper;	

	@Inject
	private UserService userService;

	@Inject
	private MemberService memberService;	

	@Override
	public WxHandler getWxHandler()
	{
		// TODO Auto-generated method stub
		return myHandle;
	}

	protected void set_author(String oldwxopenid, String newwxopenid) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		session.removeAttribute(GlobalConstants.sys_login_token);
		DynamicObject obj = memberService.newwxuser(oldwxopenid, newwxopenid);
		session.setAttribute(GlobalConstants.sys_login_token, obj);			
	}
	
}
