package com.blue.wxmp.sdk.handle;

import com.blue.wxmp.sdk.bean.WxInMsg;
import com.blue.wxmp.sdk.bean.WxOutMsg;

public interface WxHandler {

	boolean check(String signature, String timestamp, String nonce);

	WxOutMsg text(WxInMsg msg);

	WxOutMsg image(WxInMsg msg);

	WxOutMsg voice(WxInMsg msg);

	WxOutMsg video(WxInMsg msg);

	WxOutMsg location(WxInMsg msg);

	WxOutMsg link(WxInMsg msg);

	// WxOutMsg event(WxInMsg msg);

	WxOutMsg eventSubscribe(WxInMsg msg);

	WxOutMsg eventUnsubscribe(WxInMsg msg);

	WxOutMsg eventScan(WxInMsg msg);

	WxOutMsg eventLocation(WxInMsg msg);

	WxOutMsg eventClick(WxInMsg msg);

	WxOutMsg eventView(WxInMsg msg);

	WxOutMsg eventTemplateJobFinish(WxInMsg msg);

	WxOutMsg eventScancodePush(WxInMsg msg);

	WxOutMsg eventScancodeWaitMsg(WxInMsg msg);

	WxOutMsg eventScancodePicSysphoto(WxInMsg msg);

	WxOutMsg eventScancodePicPhotoOrAlbum(WxInMsg msg);

	WxOutMsg eventScancodePicWeixin(WxInMsg msg);

	WxOutMsg eventLocationSelect(WxInMsg msg);

	WxOutMsg defaultMsg(WxInMsg msg);

	WxOutMsg handle(WxInMsg in);

}
