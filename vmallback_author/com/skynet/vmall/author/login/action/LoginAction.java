package com.skynet.vmall.author.login.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.skynet.app.organ.service.GroupUserService;
import com.skynet.app.organ.service.OrganService;
import com.skynet.app.organ.service.UserService;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.spec.GlobalConstants;

@IocBean
@At("/author/login")
public class LoginAction
{
	private Log log = Logs.getLog(LoginAction.class);

	@Inject
	private OrganService organService;

	@Inject
	private UserService userService;

	@Inject
	private GroupUserService groupuserService;

	@At("/log")
	@Ok("->:/login.ftl")
	public Map log() throws Exception
	{
		DynamicObject ro = new DynamicObject();
		return ro;
	}

	@At("/login")
	@Ok("json")
	public Map login(String loginname, String password) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		session.removeAttribute(GlobalConstants.sys_login_token);

		DynamicObject ro = new DynamicObject();

		int num = userService.count(Cnd.where("loginname", "=", loginname).and("password", "=", password));

		if (num == 0)
		{
			session.removeAttribute(GlobalConstants.sys_login_token);
			ro.put("state", "error");
			ro.put("message", "用户名或密码错误，请重新输入。");
			return ro;
		}

		DynamicObject user = userService.locateBy(Cnd.where("loginname", "=", loginname).and("password", "=", password));

		// DynamicObject dept = userService.getPrimaryDept(loginname);
		// DynamicObject org = userService.getPrimaryOrg(loginname);

		DynamicObject obj = new DynamicObject();
		obj.setAttr(GlobalConstants.sys_login_user, loginname);
		obj.setAttr(GlobalConstants.sys_login_username, user.getFormatAttr("cname"));
		obj.setAttr(GlobalConstants.sys_login_userid, user.getFormatAttr("id"));
		obj.setAttr(GlobalConstants.sys_login_userwxopenid, user.getFormatAttr("wxopenid"));

		// obj.setAttr(GlobalConstants.sys_login_dept,
		// dept.getFormatAttr("id"));
		// obj.setAttr(GlobalConstants.sys_login_deptname,
		// dept.getFormatAttr("cname"));
		// obj.setAttr(GlobalConstants.sys_login_dept_internal,
		// dept.getFormatAttr("internal"));

		// obj.setAttr(GlobalConstants.sys_login_org, org.getFormatAttr("id"));
		// obj.setAttr(GlobalConstants.sys_login_orgname,
		// org.getFormatAttr("cname"));
		// obj.setAttr(GlobalConstants.sys_login_org_internal,
		// org.getFormatAttr("internal"));

		session.setAttribute(GlobalConstants.sys_login_token, obj);

		ro.put("state", "success");

		return ro;
	}

	@At("/home")
	@Ok("->:/page/home/home/index.ftl")
	@Filters(
	{ @By(type = CheckSession.class, args =
	{ "sys_login_token", "/author/login/log.action" }) })
	public Map home(String loginname, String password) throws Exception
	{
		DynamicObject ro = new DynamicObject();
		return ro;
	}
}
