package com.skynet.tools.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class MuFileToZip
{
	public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName) throws Exception
	{
		boolean sign = false;
		
		// 默认的相对地址，为根路径
		String defaultParentPath = "";
		ZipOutputStream zos = null;
		try
		{
			// 创建一个Zip输出流
			createDirectory(zipFilePath);
			
			zos = new ZipOutputStream(new FileOutputStream(zipFilePath + "/" + fileName + ".zip"));
			// 启动压缩进程
			startCompress(zos, defaultParentPath, sourceFilePath);
			
			sign = true;
		}
		catch (FileNotFoundException e)
		{
			sign = false;
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (zos != null)
					zos.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return sign;
	}

	public static void startCompress(ZipOutputStream zos, String oppositePath, String directory) throws Exception
	{
		File file = new File(directory);
		if (file.isDirectory())
		{
			// 如果是压缩目录
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++)
			{
				File aFile = files[i];
				if (aFile.isDirectory())
				{
					// 如果是目录，修改相对地址
					String newOppositePath = oppositePath + aFile.getName() + "/";
					// 创建目录
					compressDirectory(zos, oppositePath, aFile);
					// 进行递归调用
					startCompress(zos, newOppositePath, aFile.getPath());
				}
				else
				{
					// 如果不是目录，则进行压缩
					compressFile(zos, oppositePath, aFile);
				}
			}
		}
		else
		{
			// 如果是压缩文件，直接调用压缩方法进行压缩
			compressFile(zos, oppositePath, file);
		}
	}

	public static void compressFile(ZipOutputStream zos, String oppositePath, File file) throws Exception
	{
		// 创建一个Zip条目，每个Zip条目都是必须相对于根路径
		ZipEntry entry = new ZipEntry(oppositePath + file.getName());
		InputStream is = null;
		try
		{
			// 将条目保存到Zip压缩文件当中
			zos.putNextEntry(entry);
			// 从文件输入流当中读取数据，并将数据写到输出流当中.
			is = new FileInputStream(file);
			int length = 0;
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			while ((length = is.read(buffer, 0, bufferSize)) >= 0)
			{
				zos.write(buffer, 0, length);
			}
			zos.closeEntry();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if (is != null)
					is.close();
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}

	public static void compressDirectory(ZipOutputStream zos, String oppositePath, File file) throws Exception
	{
		// 压缩目录，这是关键，创建一个目录的条目时，需要在目录名后面加多一个"/"
		ZipEntry entry = new ZipEntry(oppositePath + file.getName() + "/");
		try
		{
			zos.putNextEntry(entry);
			zos.closeEntry();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	// 新建目录，目录不存在，创建目录
	public static boolean createDirectory(String path) throws Exception
	{
		if (StringUtils.isEmpty(path))
		{
			return false;
		}
		try
		{
			// 获得文件对象
			File f = new File(path);
			if (!f.exists())
			{
				// 如果路径不存在,则创建
				f.mkdirs();
			}
			
			return true;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		// 初始化支持多级目录压缩的ZipMultiDirectoryCompress
		MuFileToZip zipCompress = new MuFileToZip();
		String sourceFilePath = "E:\\upload\\评审文档\\1505260001_测试01";
		String zipFilePath = "E:\\download\\评审文档\\1505260001_测试01";
		String fileName = "test";
		
		fileToZip(sourceFilePath, zipFilePath, fileName);
	}
}
