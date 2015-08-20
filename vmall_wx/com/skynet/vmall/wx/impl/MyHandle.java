package com.skynet.vmall.wx.impl;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.Mvcs;

import com.blue.wxmp.sdk.bean.WxInMsg;
import com.blue.wxmp.sdk.bean.WxOutMsg;
import com.blue.wxmp.sdk.handle.AbstractWxHandle;
import com.blue.wxmp.sdk.util.WxUtils;
import com.skynet.app.log.pojo.Log;
import com.skynet.app.log.service.LogService;
import com.skynet.app.organ.pojo.User;
import com.skynet.app.organ.service.UserService;
import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.function.HttpToolKit;
import com.skynet.vmall.member.service.AppMemberService;

@IocBean
public class MyHandle extends AbstractWxHandle
{

	@Inject
	AppMemberService appmemberService;
	
	@Inject
	LogService logService;
	
	@Inject
	UserService userService;	

	@Override
	
	public WxOutMsg eventSubscribe(WxInMsg msg)
	{
		// TODO Auto-generated method stub

		try
		{
			String fromusername = msg.getFromUserName();
			String tousername = msg.getToUserName();
			

			log.debugf("关注消息来啦！%s,关注人的openid是[%s]", msg, fromusername);
			
			appmemberService.newwxuser(tousername, fromusername);
			
			log_user(msg, "关注");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return super.eventSubscribe(msg);
	}

	@Override
	public WxOutMsg eventUnsubscribe(WxInMsg msg)
	{
		// TODO Auto-generated method stub
		log.debugf("会员[%s]不关注咱了", msg, msg.getFromUserName());
		
		
		log_user(msg, "取消关注");

		return super.eventSubscribe(msg);
	}

	@Override
	public WxOutMsg eventScan(WxInMsg msg)
	{
		// TODO Auto-generated method stub

		log.debugf("扫描消息来了！关注人的openid是[%s],eventkey=%s", msg.getFromUserName(), msg.getEventKey());

		// 记录

		return super.eventScan(msg);
	}
	
	@Override
	public WxOutMsg defaultMsg(WxInMsg msg) {
		return WxUtils.respText(null, "谢谢您的关注，欢迎来到天狗微商城：）");
	}
	
	protected void log_user(WxInMsg msg, String actionname)
	{
		HttpServletRequest req = Mvcs.getReq();
		String uri = req.getRequestURI();
		String url = req.getRequestURL().toString();
		String cip = HttpToolKit.getIpAddr(req);
		String sip = req.getLocalAddr();
		int sport = req.getLocalPort();
		Timestamp logtime = new Timestamp(System.currentTimeMillis());
		
		double locx = msg.getLocation_X();
		double locy = msg.getLocation_Y();
		
		User user = userService.fetch(Cnd.where("wxopenid", "=", msg.getFromUserName()));
		String userid = user.getId();
		String username = user.getCname();
		String loginname = user.getLoginname();
		String userwxopenid = user.getWxopenid();
		
		String id = UUIDGenerator.getInstance().getNextValue();
		Log log = new Log();
		log.setId(id);
		log.setActionname(actionname);
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
		log.setLocx(locx);
		log.setLocy(locy);

		LogService logService = (LogService)Mvcs.getIoc().get(LogService.class, "logService");
		logService.sdao().insert(log);
	}

}
