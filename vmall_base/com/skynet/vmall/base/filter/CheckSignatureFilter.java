package com.skynet.vmall.base.filter;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ServerRedirectView;

import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.author.AuthorService;

@IocBean
public class CheckSignatureFilter implements ActionFilter
{
	private String actionname;

	public CheckSignatureFilter(String actionname)
	{
		this.actionname = actionname;
	}

	@Override
	public View match(ActionContext paramActionContext)
	{
		try
		{
			HttpSession session = Mvcs.getHttpSession(true);
			DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
			String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
			String username = login_token.getFormatAttr(GlobalConstants.sys_login_username);
			String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);

			try
			{
				String keysignature = Mvcs.getReq().getParameter("keysignature");
				String decode = AuthorService.decode(keysignature);
				String ip = AuthorService.getip(Mvcs.getReq());
				String state = AuthorService.checksignature(decode, ip, userwxopenid);
				if(!("success".equals(state)))
				{
					return new ServerRedirectView("/checksignature.html?state="+state);
				}

				return null;
			}
			catch (Exception e)
			{
				System.out.println(e);
				return new ServerRedirectView("/checksignature.html?state=error");
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
			return new ServerRedirectView("/checksignature.html?state=error");
		}
	}
}
