package com.blue.wxmp.sdk.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;

import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.Xmls;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.HttpStatusView;
import org.nutz.mvc.view.RawView;
import org.nutz.mvc.view.ViewWrapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.blue.wxmp.sdk.bean.WxArticle;
import com.blue.wxmp.sdk.bean.WxEventType;
import com.blue.wxmp.sdk.bean.WxImage;
import com.blue.wxmp.sdk.bean.WxInMsg;
import com.blue.wxmp.sdk.bean.WxMsgType;
import com.blue.wxmp.sdk.bean.WxMusic;
import com.blue.wxmp.sdk.bean.WxOutMsg;
import com.blue.wxmp.sdk.bean.WxVideo;
import com.blue.wxmp.sdk.bean.WxVoice;
import com.blue.wxmp.sdk.handle.WxHandler;
import com.blue.wxmp.sdk.mvc.WxView;

public class WxUtils {

	private static Log log = Logs.getLog(WxUtils.class);

	/**
	 * 创建一条文本响应
	 */
	public static WxOutMsg respText(String to, String content) {
		WxOutMsg out = new WxOutMsg("text");
		out.setContent(content);
		if (to != null)
			out.setToUserName(to);
		return out;
	}

	/**
	 * 创建一条图片响应
	 */
	public static WxOutMsg respImage(String to, String mediaId) {
		WxOutMsg out = new WxOutMsg("image");
		out.setImage(new WxImage(mediaId));
		if (to != null)
			out.setToUserName(to);
		return out;
	}

	/**
	 * 创建一个语音响应
	 */
	public static WxOutMsg respVoice(String to, String mediaId) {
		WxOutMsg out = new WxOutMsg("voice");
		out.setVoice(new WxVoice(mediaId));
		if (to != null)
			out.setToUserName(to);
		return out;
	}

	/**
	 * 创建一个视频响应
	 */
	public static WxOutMsg respVideo(String to, String mediaId, String title, String description) {
		WxOutMsg out = new WxOutMsg("video");
		out.setVideo(new WxVideo(mediaId, title, description));
		if (to != null)
			out.setToUserName(to);
		return out;
	}

	/**
	 * 创建一个音乐响应
	 */
	public static WxOutMsg respMusic(String to, String title, String description, String musicURL, String hQMusicUrl, String thumbMediaId) {
		WxOutMsg out = new WxOutMsg("music");
		out.setMusic(new WxMusic(title, description, musicURL, hQMusicUrl, thumbMediaId));
		if (to != null)
			out.setToUserName(to);
		return out;
	}

	/**
	 * 创建一个图文响应
	 */
	public static WxOutMsg respNews(String to, WxArticle... articles) {
		return respNews(to, Arrays.asList(articles));
	}

	/**
	 * 创建一个图文响应
	 */
	public static WxOutMsg respNews(String to, List<WxArticle> articles) {
		WxOutMsg out = new WxOutMsg("news");
		out.setArticles(articles);
		if (to != null)
			out.setToUserName(to);
		return out;
	}

	public static String cdata(String str) {
		if (Strings.isBlank(str))
			return "";
		return "<![CDATA[" + str.replaceAll("]]", "__") + "]]>";
	}

	public static String tag(String key, String val) {
		StringBuilder sb = new StringBuilder();
		sb.append("<").append(key).append(">");
		sb.append(val).append("");
		sb.append("</").append(key).append(">\n");
		return sb.toString();
	}

	/**
	 * @see #asXml(Writer, WxOutMsg)
	 */
	public static String asXml(WxOutMsg msg) {
		StringWriter sw = new StringWriter();
		asXml(sw, msg);
		return sw.toString();
	}

	/**
	 * 将一个WxOutMsg转为被动响应所需要的XML文本
	 * 
	 * @param msg
	 *            微信消息输出对象
	 * 
	 * @return 输出的 XML 文本
	 */
	public static void asXml(Writer writer, WxOutMsg msg) {
		try {
			Writer _out = writer;

			writer = new StringWriter();

			writer.write("<xml>\n");
			writer.write(tag("ToUserName", cdata(msg.getToUserName())));
			writer.write(tag("FromUserName", cdata(msg.getFromUserName())));
			writer.write(tag("CreateTime", "" + msg.getCreateTime()));
			writer.write(tag("MsgType", cdata(msg.getMsgType())));
			switch (WxMsgType.valueOf(msg.getMsgType())) {
			case text:
				writer.write(tag("Content", cdata(msg.getContent())));
				break;
			case image:
				writer.write(tag("Image", tag("MediaId", msg.getImage().getMediaId())));
				break;
			case voice:
				writer.write(tag("Voice", tag("MediaId", msg.getVoice().getMediaId())));
				break;
			case video:
				writer.write("<Video>\n");
				writer.write(tag("MediaId", cdata(msg.getVideo().getMediaId())));
				if (msg.getVideo().getTitle() != null)
					writer.write(tag("Title", cdata(msg.getVideo().getTitle())));
				if (msg.getVideo().getDescription() != null)
					writer.write(tag("Description", cdata(msg.getVideo().getDescription())));
				writer.write("</Video>\n");
				break;
			case music:
				writer.write("<Music>\n");
				WxMusic music = msg.getMusic();
				if (music.getTitle() != null)
					writer.write(tag("Title", cdata(music.getTitle())));
				if (music.getDescription() != null)
					writer.write(tag("Description", cdata(music.getDescription())));
				if (music.getMusicUrl() != null)
					writer.write(tag("MusicUrl", cdata(music.getMusicUrl())));
				if (music.getHQMusicUrl() != null)
					writer.write(tag("HQMusicUrl", cdata(music.getHQMusicUrl())));
				writer.write(tag("ThumbMediaId", cdata(music.getThumbMediaId())));
				writer.write("</Music>\n");
				break;
			case news:
				writer.write(tag("ArticleCount", "" + msg.getArticles().size()));
				writer.write("<Articles>\n");
				for (WxArticle article : msg.getArticles()) {
					writer.write("<item>\n");
					if (article.getTitle() != null)
						writer.write(tag("Title", cdata(article.getTitle())));
					if (article.getDescription() != null)
						writer.write(tag("Description", cdata(article.getDescription())));
					if (article.getPicUrl() != null)
						writer.write(tag("PicUrl", cdata(article.getPicUrl())));
					if (article.getUrl() != null)
						writer.write(tag("Url", cdata(article.getUrl())));
					writer.write("</item>\n");
				}
				writer.write("</Articles>\n");
				break;
			default:
				break;
			}
			writer.write("</xml>");

			String str = writer.toString();
			log.debug("Outcome >>\n" + str);
			_out.write(str);

		} catch (IOException e) {
			throw Lang.wrapThrow(e);
		}
	}

	/**
	 * @see #asJson(Writer, WxOutMsg)
	 */
	public static String asJson(WxOutMsg msg) {
		StringWriter sw = new StringWriter();
		asJson(sw, msg);
		return sw.toString();
	}

	/**
	 * 将一个WxOutMsg转为主动信息所需要的Json文本
	 * 
	 * @param msg
	 *            微信消息输出对象
	 * 
	 * @return 输出的 JSON 文本
	 */
	public static void asJson(Writer writer, WxOutMsg msg) {
		NutMap map = new NutMap();
		map.put("touser", msg.getToUserName());
		map.put("msgtype", msg.getMsgType());
		switch (WxMsgType.valueOf(msg.getMsgType())) {
		case text:
			map.put("text", new NutMap().setv("content", msg.getContent()));
			break;
		case image:
			map.put("image", new NutMap().setv("media_id", msg.getImage().getMediaId()));
			break;
		case voice:
			map.put("voice", new NutMap().setv("media_id", msg.getVoice().getMediaId()));
			break;
		case video:
			NutMap _video = new NutMap();
			_video.setv("media_id", msg.getVideo().getMediaId());
			if (msg.getVideo().getTitle() != null)
				_video.put("title", (msg.getVideo().getTitle()));
			if (msg.getVideo().getDescription() != null)
				_video.put("description", (msg.getVideo().getDescription()));
			map.put("video", _video);
			break;
		case music:
			NutMap _music = new NutMap();
			WxMusic music = msg.getMusic();
			if (music.getTitle() != null)
				_music.put("title", (music.getTitle()));
			if (music.getDescription() != null)
				_music.put("description", (music.getDescription()));
			if (music.getMusicUrl() != null)
				_music.put("musicurl", (music.getMusicUrl()));
			if (music.getHQMusicUrl() != null)
				_music.put("hqmusicurl", (music.getHQMusicUrl()));
			_music.put("thumb_media_id", (music.getThumbMediaId()));
			break;
		case news:
			NutMap _news = new NutMap();
			List<NutMap> list = new ArrayList<NutMap>();
			for (WxArticle article : msg.getArticles()) {
				NutMap item = new NutMap();
				if (article.getTitle() != null)
					item.put("title", (article.getTitle()));
				if (article.getDescription() != null)
					item.put("description", (article.getDescription()));
				if (article.getPicUrl() != null)
					item.put("picurl", (article.getPicUrl()));
				if (article.getUrl() != null)
					item.put("url", (article.getUrl()));
				list.add(item);
			}
			_news.put("articles", list);
			map.put("news", _news);
			break;
		default:
			break;
		}
		Json.toJson(writer, map);
	}

	/**
	 * 根据不同的消息类型,调用WxHandler不同的方法
	 */
	public static WxOutMsg handle(WxInMsg msg, WxHandler handler) {
		WxOutMsg out = null;
		switch (WxMsgType.valueOf(msg.getMsgType())) {
		case text:
			out = handler.text(msg);
			break;
		case image:
			out = handler.image(msg);
			break;
		case voice:
			out = handler.voice(msg);
			break;
		case video:
			out = handler.video(msg);
			break;
		case location:
			out = handler.location(msg);
			break;
		case link:
			out = handler.link(msg);
			break;
		case event:
			out = handleEvent(msg, handler);
			break;
		default:
			log.infof("New MsyType=%s ? fallback to defaultMsg", msg.getMsgType());
			out = handler.defaultMsg(msg);
			break;
		}
		return out;
	}

	/**
	 * 根据msg中Event的类型,调用不同的WxHandler方法
	 */
	public static WxOutMsg handleEvent(WxInMsg msg, WxHandler handler) {

		WxOutMsg out = null;
		switch (WxEventType.valueOf(msg.getEvent())) {
		case subscribe:
			out = handler.eventSubscribe(msg);
			break;
		case unsubscribe:
			out = handler.eventUnsubscribe(msg);
			break;
		case LOCATION:
			out = handler.eventLocation(msg);
			break;
		case SCAN:
			out = handler.eventScan(msg);
			break;
		case CLICK:
			out = handler.eventClick(msg);
			break;
		case VIEW:
			out = handler.eventView(msg);
			break;
		case TEMPLATESENDJOBFINISH:
			out = handler.eventTemplateJobFinish(msg);
			break;
		default:
			log.infof("New EventType=%s ? fallback to defaultMsg", msg.getMsgType());
			out = handler.defaultMsg(msg);
			break;
		}
		return out;
	}

	/**
	 * 用一个wxHandler处理对应的用户请求
	 */
	public static View handle(WxHandler wxHandler, HttpServletRequest req) throws IOException {
		if (wxHandler == null) {
			log.info("WxHandler is NULL");
			return HttpStatusView.HTTP_502;
		}
		if (!wxHandler.check(req.getParameter("signature"), req.getParameter("timestamp"), req.getParameter("nonce"))) {
			log.info("token is invalid");
			return HttpStatusView.HTTP_502;
		}
		if ("GET".equalsIgnoreCase(req.getMethod())) {
			log.info("GET? return echostr=" + req.getParameter("echostr"));
			return new ViewWrapper(new RawView(null), req.getParameter("echostr"));
		}
		WxInMsg in = WxUtils.convert(req.getInputStream());
		WxOutMsg out = wxHandler.handle(in);
		if (out != null) {
			fix(in, out);
		}
		return new ViewWrapper(WxView.me, out);
	}

	/**
	 * 根据输入信息,修正发送信息的发送者和接受者
	 */
	public static WxOutMsg fix(WxInMsg in, WxOutMsg out) {
		out.setFromUserName(in.getToUserName());
		out.setToUserName(in.getFromUserName());
		out.setCreateTime(System.currentTimeMillis() / 1000);
		return out;
	}

	/**
	 * 将一个输入流转为WxInMsg
	 */
	public static WxInMsg convert(InputStream in) {
		Map<String, Object> map = Xmls.asMap(Xmls.xml(in).getDocumentElement());
		Map<String, Object> tmp = new HashMap<String, Object>();
		for (Entry<String, Object> en : map.entrySet()) {
			tmp.put(Strings.lowerFirst(en.getKey()), en.getValue());
		}

		log.debug("Income >> \n" + Json.toJson(map));

		return Lang.map2Object(tmp, WxInMsg.class);
	}

	public static WxInMsg convert(String data) {
		return convert(new ByteArrayInputStream(data.getBytes()));
	}

	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {

//		String myString = "appid=wxd986013eeb54f390&body=测试订单&mch_id=1247511701&nonce_str=FtPp34fiGWyesf5Jh4SD_-&notify_url=http://www.tiangouvmall.com/vmall/test&openid=ofcJis8jiU2TmU97p-sbOVZYtehs&out_trade_no=order00010000001&spbill_create_ip=10.0.0.1&total_fee=1&trade_type=JSAPI&key=arv2v6PHLmaVFDsXOH3vORzHvC7QcDvK";
//
//		byte[] bytesOfMessage = myString.getBytes("UTF-8");
//
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		byte[] array = md.digest(bytesOfMessage);
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < array.length; ++i) {
//			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
//		}
//
//		System.out.println(sb.toString());
//		
//		
//		System.out.println(Lang.md5(myString));
//		
//		System.out.println(Md5.MD5Encode(myString));

		
		
		
		String xmlStr = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wxd986013eeb54f390]]></appid><mch_id><![CDATA[1247511701]]></mch_id><nonce_str><![CDATA[yT7HM9b1RrZzCzkA]]></nonce_str><sign><![CDATA[6745BE7FB6DCB7CFC0B441EEE81161D2]]></sign><result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx201506181006189060ae01430185478398]]></prepay_id><trade_type><![CDATA[JSAPI]]></trade_type></xml>";
        Document doc = Xmls.xml(new ByteArrayInputStream(xmlStr.getBytes()));
        Element root = doc.getDocumentElement();
        Map<String, Object> map = Xmls.asMap(root);
        
        log.debugf("a=%s", map);
        
	}

	public static String createSign(SortedMap<String, String> packageParams, String paykey) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + paykey);

		String sign = Lang.md5(sb.toString()).toUpperCase(); 
		log.infof("paramsStr= %s \n sign=%s",sb.toString(),sign);														// "utf-8").toUpperCase();
		return sign;
	}

}
