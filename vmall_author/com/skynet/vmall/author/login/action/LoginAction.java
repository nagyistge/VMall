package com.skynet.vmall.author.login.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.blue.wxmp.sdk.api.ApiConfigKit;
import com.blue.wxmp.sdk.api.OAuthAccessTokenApi;
import com.blue.wxmp.sdk.api.WxApi;
import com.blue.wxmp.sdk.encrypt.BlueDes;
import com.skynet.app.organ.pojo.User;
import com.skynet.app.organ.service.GroupUserService;
import com.skynet.app.organ.service.OrganService;
import com.skynet.app.organ.service.UserService;
import com.skynet.framework.action.BaseAction;
import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.spec.GlobalConstants;

@IocBean
@At("/author/login")
public class LoginAction extends BaseAction
{
	private Log log = Logs.getLog(LoginAction.class);
	
	@Inject
	WxApi myWxApi;
	
	@Inject
	private OrganService organService;

	@Inject
	private UserService userService;

	@Inject
	private GroupUserService groupuserService;

	@At("/wxlogin")
	@Ok(">>:/mall/mall/index.action")
	public NutMap wxlogin(String info, HttpServletRequest req) throws Exception
	{
		// 清除现有会话信息
		HttpSession session = Mvcs.getHttpSession(true);
		session.removeAttribute(GlobalConstants.sys_login_token);

		String uri = myWxApi.getCompleteUri(req);// 获取完整uri，否则jsticket无法使用
		Logs.get().debugf("request url=%s", uri);
		
		String decinfo = BlueDes.decrypt(info);
		HashMap<String, String> minfo = Json.fromJson(HashMap.class, decinfo);
		
		String wxopenid = (String)minfo.get("openid");
		
		User newuserobj = userService.sdao().fetch(User.class, Cnd.where("wxopenid", "=", wxopenid));
		if(newuserobj==null)
		{
			newuserobj = new User();
			String newuserid = UUIDGenerator.getInstance().getNextValue();
			newuserobj.setId(newuserid);
			newuserobj.setWxopenid(wxopenid);
			newuserobj.setLoginname(wxopenid);
			newuserobj.setCname(wxopenid);
			newuserobj.setCreatetime(new Timestamp(System.currentTimeMillis()));
			userService.sdao().insert(newuserobj);
		}
		
		DynamicObject user = userService.locate(newuserobj.getId());

		String loginname = newuserobj.getLoginname();

		DynamicObject obj = new DynamicObject();
		obj.setAttr(GlobalConstants.sys_login_user, loginname);
		obj.setAttr(GlobalConstants.sys_login_username, user.getFormatAttr("cname"));
		obj.setAttr(GlobalConstants.sys_login_userid, user.getFormatAttr("id"));
		obj.setAttr(GlobalConstants.sys_login_userwxopenid, user.getFormatAttr("wxopenid"));

		session.setAttribute(GlobalConstants.sys_login_token, obj);	
		
		NutMap rowx = wxconfig(uri, minfo);
		
		session.setAttribute(GlobalConstants.sys_wxconfig, rowx);			
		
		return rowx;
	}
	
	private NutMap wxconfig(String uri, HashMap<String, String> minfo) throws Exception
	{
		NutMap jscfg = myWxApi.genJsSDKConfig(uri, "onMenuShareTimeline", "onMenuShareAppMessage");

		NutMap rowx = NutMap.NEW();

		String redirecturl = ApiConfigKit.apiConfig.getServercontext() + "/oauth?info=";
		String realurl = "/sharetofriend.action?recommender=" + minfo.get("openid"); // minfo.get("openid")就是刚点进来链接的人;
		realurl = BlueDes.encrypt(realurl);

		redirecturl += realurl;

		String url = String.format(OAuthAccessTokenApi.oauthurl, ApiConfigKit.apiConfig.getAppId(), redirecturl);
		
		rowx.put("shareurl", url);
		rowx.put("openid", minfo.get("openid"));
		rowx.put("jscfg", jscfg);
		
		log.debugf("openid=%s", minfo.get("openid"));
		
		return rowx;
	}
	
	
	@At("/login_test")
	@Ok(">>:/mall/mall/index_test.action")
	public Map login_test(@Param("username") String loginname, String password) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		session.removeAttribute(GlobalConstants.sys_login_token);

		int num = userService.count(Cnd.where("loginname", "=", loginname).and("password", "=", password));
		
		if (num==0)
		{
			session.removeAttribute(GlobalConstants.sys_login_token);
			ro.put("status", "failed");
			return ro;
		}
		
		DynamicObject user = userService.locateBy(Cnd.where("loginname", "=", loginname).and("password", "=", password));

		DynamicObject dept = userService.getPrimaryDept(loginname);
		DynamicObject org = userService.getPrimaryOrg(loginname);

		DynamicObject obj = new DynamicObject();
		obj.setAttr(GlobalConstants.sys_login_user, loginname);
		obj.setAttr(GlobalConstants.sys_login_username, user.getFormatAttr("cname"));
		obj.setAttr(GlobalConstants.sys_login_userid, user.getFormatAttr("id"));
		obj.setAttr(GlobalConstants.sys_login_userwxopenid, user.getFormatAttr("wxopenid"));

		obj.setAttr(GlobalConstants.sys_login_dept, dept.getFormatAttr("id"));
		obj.setAttr(GlobalConstants.sys_login_deptname, dept.getFormatAttr("cname"));
		obj.setAttr(GlobalConstants.sys_login_dept_internal, dept.getFormatAttr("internal"));

		obj.setAttr(GlobalConstants.sys_login_org, org.getFormatAttr("id"));
		obj.setAttr(GlobalConstants.sys_login_orgname, org.getFormatAttr("cname"));
		obj.setAttr(GlobalConstants.sys_login_org_internal, org.getFormatAttr("internal"));

		session.setAttribute(GlobalConstants.sys_login_token, obj);

		return ro;
	}
	
	public OrganService getOrganService()
	{
		return organService;
	}

	public void setOrganService(OrganService organService)
	{
		this.organService = organService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public GroupUserService getGroupuserService()
	{
		return groupuserService;
	}

	public void setGroupuserService(GroupUserService groupuserService)
	{
		this.groupuserService = groupuserService;
	}
	
	

}
