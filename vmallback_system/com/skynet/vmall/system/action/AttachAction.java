package com.skynet.vmall.system.action;

import java.io.File;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;
import org.nutz.mvc.upload.UploadAdaptor;

import com.skynet.app.dictionary.pojo.Dictionary;
import com.skynet.app.dictionary.service.DictionaryService;
import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.pojo.Attach;
import com.skynet.vmall.system.service.AppAttachService;

@IocBean
@At("/system/attach")
@Filters(
{ @By(type = CheckSession.class, args =
{ "sys_login_token", "/author/login/log.action" }) })
public class AttachAction
{
	private File fupload;

	private String fuploadFileName;

	@Inject
	DictionaryService dictionaryService;

	@Inject
	AppAttachService appattachService;

	@At("/upload")
	@Ok("json")
	@AdaptBy(type = UploadAdaptor.class, args =
	{ "${app.root}/WEB-INF/tmp" })
	public Map upload(@Param("..") Map form, @Param("fupload") File f) throws Exception
	{
		fupload = f;
		fuploadFileName = (String) form.get("Filename");
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);

		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);
		String username = login_token.getFormatAttr(GlobalConstants.sys_login_username);
		Timestamp nowtime = new Timestamp(System.currentTimeMillis());

		String kid = (String) form.get("kid");
		String cclass = (String) form.get("cclass");
		String curl = "";
		// 保存上传附件
		String dir = String.valueOf(new GregorianCalendar().get(Calendar.YEAR)) + "\\" + cclass;

		try
		{
			Map<String, String> map = uploaddoc(dir);
			// 保存附件记录;

			Attach attach = new Attach();

			attach.setFilename(map.get("filename"));
			attach.setFileextname(map.get("fileextname"));
			attach.setCname(fuploadFileName);
			attach.setCurl(dir.replace('\\', '/'));
			attach.setCclass(cclass);

			attach.setCreateuser(username);
			attach.setCreateuserid(loginname);
			attach.setCreatetime(nowtime);

			appattachService.uploadref(attach, kid, cclass);
			
			String weburl = "upload/" + attach.getCurl() + "/" + attach.getFilename();
			System.out.println(weburl);
			
			DynamicObject ro = new DynamicObject();
			ro.put("state", "success");
			ro.put("file_path", weburl);
			ro.put("attach", attach);
			return ro;
			
			// 返回
			// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			// HttpServletResponse response = Mvcs.getResp();
			// response.getWriter().flush();
			// response.getWriter().append("['" + attach.getCurl() + "','" + attach.getId() + "','" + attach.getCname() + "','" + attach.getCreateuser() + "','" + simpleDateFormat.format(attach.getCreatetime()) + "']");
			
		}
		catch (Exception e)
		{
			// 返回
//			HttpServletResponse response = Mvcs.getResp();
//			response.getWriter().append(e.getMessage());
			DynamicObject ro = new DynamicObject();
			ro.put("state", "error");
			ro.put("message",e.getMessage());	
			return ro;
		}
	}

	protected Map<String, String> uploaddoc(String dir) throws Exception
	{
		// 根据classid查找分类目录并按照层级转换为完整目录名；
		// 根据完整目录名创建附件目录，并上传附件至该目录；
		Map map = new DynamicObject();

		Dictionary dictionary = dictionaryService.fetch(Cnd.where("dkey", "=", "app.system.attach.root"));
		String root = dictionary.getDvalue();

		if (StringToolKit.isBlank(root))
		{
			// throw new Exception("系统未设定上传文档目录，无法上传，请联系系统管理员！");
			throw new Exception("error");
		}

		String rootdir = root + "upload";
		String webrootdir = "/upload";

		File rootdirfile = new File(rootdir);
		if (!rootdirfile.isDirectory())
		{
			// throw new Exception("系统未创建上传文档目录，无法上传，请联系系统管理员！");
			throw new Exception("error");
		}

		String extName = "";
		String newFileName = "";
		String nowTimeStr = "";

		Random r = new Random();

		HttpServletResponse response = Mvcs.getResp();

		response.setCharacterEncoding("utf-8");

		if (fuploadFileName.lastIndexOf(".") >= 0)
		{
			extName = fuploadFileName.substring(fuploadFileName.lastIndexOf(".") + 1);
		}

		newFileName = UUIDGenerator.getInstance().getNextValue() + "." + extName;

		File dirname = new File(rootdir + "\\" + dir);

		if (!dirname.isDirectory())
		{
			// 目录不存在
			dirname.mkdirs(); // 创建目录
		}

		fupload.renameTo(new File(rootdir + "\\" + dir + "\\" + newFileName));

		map.put("filename", newFileName);
		map.put("fileextname", extName);

		return map;
	}

	public File getFupload()
	{
		return fupload;
	}

	public void setFupload(File fupload)
	{
		this.fupload = fupload;
	}

	public String getFuploadFileName()
	{
		return fuploadFileName;
	}

	public void setFuploadFileName(String fuploadFileName)
	{
		this.fuploadFileName = fuploadFileName;
	}

}
