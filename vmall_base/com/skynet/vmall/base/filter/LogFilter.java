package com.skynet.vmall.base.filter;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;

import com.skynet.app.log.pojo.Log;
import com.skynet.app.log.service.LogService;
import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.HttpToolKit;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.spec.GlobalConstants;

@IocBean
public class LogFilter implements ActionFilter
{


	private String actionname;

	public LogFilter(String actionname)
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
			String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);
			String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);

			HttpServletRequest req = paramActionContext.getRequest();
			String uri = req.getRequestURI();
			String url = req.getRequestURL().toString();
			String cip = HttpToolKit.getIpAddr(req);
			String sip = req.getLocalAddr();
			int sport = req.getLocalPort();
			Timestamp logtime = new Timestamp(System.currentTimeMillis());

			String id = UUIDGenerator.getInstance().getNextValue();
			Log log = new Log();
			log.setId(id);
			log.setActionname(StringToolKit.formatText(this.actionname, "Unkown"));
			log.setUserid(userid);
			log.setUsername(username);
			log.setLoginname(loginname);
			log.setCip(cip);
			log.setUri(uri);
			log.setUrl(url);
			log.setWxopenid(userwxopenid);
			log.setSip(sip);
			log.setSport(sport);
			log.setLogtime(logtime);

			LogService logService = (LogService)Mvcs.getIoc().get(LogService.class, "logService");
			logService.sdao().insert(log);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
}
