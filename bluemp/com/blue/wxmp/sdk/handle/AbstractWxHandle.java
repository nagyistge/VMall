package com.blue.wxmp.sdk.handle;

import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.blue.wxmp.sdk.bean.WxInMsg;
import com.blue.wxmp.sdk.bean.WxOutMsg;
import com.blue.wxmp.sdk.util.SignUtils;
import com.blue.wxmp.sdk.util.WxUtils;

public abstract class AbstractWxHandle implements WxHandler {
	public  static final Log log = Logs.get();
	
	
	public boolean check(String signature, String timestamp, String nonce) {
		// TODO Auto-generated method stub
		return SignUtils.me.checkSignature(signature, timestamp, nonce);
	}
	
	public WxOutMsg text(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg image(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg voice(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg video(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg location(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg link(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg eventSubscribe(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg eventUnsubscribe(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg eventScan(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg eventLocation(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg eventClick(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg eventView(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg eventTemplateJobFinish(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg eventScancodePush(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg eventScancodeWaitMsg(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg eventScancodePicSysphoto(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg eventScancodePicPhotoOrAlbum(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg eventScancodePicWeixin(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg eventLocationSelect(WxInMsg msg) {
		return defaultMsg(msg);
	}

	public WxOutMsg defaultMsg(WxInMsg msg) {
		return WxUtils.respText(null, "欢迎来这里啊 -> " + msg.getCreateTime());
	}

	public WxOutMsg handle(WxInMsg in) {
		return WxUtils.handle(in, this);
	}
}
