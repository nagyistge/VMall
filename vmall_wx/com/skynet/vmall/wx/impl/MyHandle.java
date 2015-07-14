package com.skynet.vmall.wx.impl;

import org.nutz.ioc.loader.annotation.IocBean;

import com.blue.wxmp.sdk.bean.WxInMsg;
import com.blue.wxmp.sdk.bean.WxOutMsg;
import com.blue.wxmp.sdk.handle.AbstractWxHandle;

@IocBean
public class MyHandle extends AbstractWxHandle {
	
	
	@Override
	public WxOutMsg eventSubscribe(WxInMsg msg) {
		// TODO Auto-generated method stub
		log.debugf("关注消息来啦！%s,关注人的openid是[%s]", msg,msg.getFromUserName());
		
		
		return super.eventSubscribe(msg);
	}
	
	@Override
	public WxOutMsg eventUnsubscribe(WxInMsg msg) {
		// TODO Auto-generated method stub
		log.debugf("这个货[%s]不关注咱了", msg,msg.getFromUserName());
		
		
		return super.eventSubscribe(msg);
	}
	
	@Override
	public WxOutMsg eventScan(WxInMsg msg) {
		// TODO Auto-generated method stub
		
		log.debugf("扫描消息来了！关注人的openid是[%s],eventkey=%s", msg.getFromUserName(),msg.getEventKey());
		
		// 记录
		
		
		return super.eventScan(msg);
	}
	
	
}
