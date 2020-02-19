package com.ice.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sun.org.apache.commons.beanutils.BeanUtils;

public class ApacheFormUtil {

	//设置上传文件的最大属性值
	private static final long MAX_SIZE = 3*1024*1024;
	
	public Object getInstanceByUploadForm(HttpServletRequest request,ServletContext context,Class clazz,String filePath){
		
		Object object = null;
		
		//1.建立文件工厂即仓库，将请求消息实体中的每一个项目封装成单独的DiskFileItem对象
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(20*1024*1024);//设置缓冲区大小为20M
		factory.setRepository(new File(request.getSession().getServletContext().getRealPath(filePath)));//1.request.getSession().getServletContext().getRealPath()得到站点的绝对地址  2.设置上传文件的临时目录
		
		//2.对上传的文件进行设定
		ServletFileUpload handler = new ServletFileUpload(factory);
		handler.setSizeMax(MAX_SIZE);
		handler.setHeaderEncoding("utf-8");
		
		Map<String , Object> paramsMap = new HashMap<String, Object>();
		//3.解析请求正文，获取上传文件
		try {
			List<FileItem> files = handler.parseRequest(request);
			Iterator<FileItem> it = files.iterator();
			
			object = clazz.newInstance();
			
			while(it.hasNext()){
				FileItem item = it.next();
				
				if(item.isFormField()){
					//普通表单项
					paramsMap.put(item.getFieldName(), item.getString("utf-8"));//键为 m_content
				}
				else{
					String filename = item.getName();
					//防止图片重名，将图片改名
					String format=filename.substring(filename.lastIndexOf("."));//得到此文件名的扩展名  public String substring(int beginIndex, int endIndex)从beginIndex开始取，到endIndex结束，从0开始数，其中不包括endIndex位置的字符
					
					String file_name=filename.substring(filename.lastIndexOf("\\")+1, filename.lastIndexOf("."));
					
					Date m_date_now=new Date();
					SimpleDateFormat timeformat=new SimpleDateFormat("yyyyMMddHHmmss",java.util.Locale.CHINA);//
					String m_date=timeformat.format(m_date_now);
					
					filename = file_name+m_date+format;
					File file = new File(filename);
					File filetoserver = new File(context.getRealPath(filePath),file.getName());
					item.write(filetoserver);
					System.out.println("文件" +filetoserver.getName()+"上传成功");
					String filetodb = request.getContextPath()+"/"+filePath+"/"+filename.substring(filename.lastIndexOf("\\")+1);
					
					paramsMap.put(item.getFieldName(), filetodb);//键为 m_images
				}
			}
			BeanUtils.populate(object, paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	
}
