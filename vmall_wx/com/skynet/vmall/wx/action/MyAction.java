package com.skynet.vmall.wx.action;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.blue.wxmp.sdk.handle.WxHandler;
import com.blue.wxmp.sdk.mvc.AbstractMsgAction;

@IocBean
public class MyAction extends AbstractMsgAction {

	@Inject
	protected WxHandler myHandle;

	@Override
	public WxHandler getWxHandler() {
		// TODO Auto-generated method stub
		return myHandle;
	}

}
