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

	//�����ϴ��ļ����������ֵ
	private static final long MAX_SIZE = 3*1024*1024;
	
	public Object getInstanceByUploadForm(HttpServletRequest request,ServletContext context,Class clazz,String filePath){
		
		Object object = null;
		
		//1.�����ļ��������ֿ⣬��������Ϣʵ���е�ÿһ����Ŀ��װ�ɵ�����DiskFileItem����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(20*1024*1024);//���û�������СΪ20M
		factory.setRepository(new File(request.getSession().getServletContext().getRealPath(filePath)));//1.request.getSession().getServletContext().getRealPath()�õ�վ��ľ��Ե�ַ  2.�����ϴ��ļ�����ʱĿ¼
		
		//2.���ϴ����ļ������趨
		ServletFileUpload handler = new ServletFileUpload(factory);
		handler.setSizeMax(MAX_SIZE);
		handler.setHeaderEncoding("utf-8");
		
		Map<String , Object> paramsMap = new HashMap<String, Object>();
		//3.�����������ģ���ȡ�ϴ��ļ�
		try {
			List<FileItem> files = handler.parseRequest(request);
			Iterator<FileItem> it = files.iterator();
			
			object = clazz.newInstance();
			
			while(it.hasNext()){
				FileItem item = it.next();
				
				if(item.isFormField()){
					//��ͨ����
					paramsMap.put(item.getFieldName(), item.getString("utf-8"));//��Ϊ m_content
				}
				else{
					String filename = item.getName();
					//��ֹͼƬ��������ͼƬ����
					String format=filename.substring(filename.lastIndexOf("."));//�õ����ļ�������չ��  public String substring(int beginIndex, int endIndex)��beginIndex��ʼȡ����endIndex��������0��ʼ�������в�����endIndexλ�õ��ַ�
					
					String file_name=filename.substring(filename.lastIndexOf("\\")+1, filename.lastIndexOf("."));
					
					Date m_date_now=new Date();
					SimpleDateFormat timeformat=new SimpleDateFormat("yyyyMMddHHmmss",java.util.Locale.CHINA);//
					String m_date=timeformat.format(m_date_now);
					
					filename = file_name+m_date+format;
					File file = new File(filename);
					File filetoserver = new File(context.getRealPath(filePath),file.getName());
					item.write(filetoserver);
					System.out.println("�ļ�" +filetoserver.getName()+"�ϴ��ɹ�");
					String filetodb = request.getContextPath()+"/"+filePath+"/"+filename.substring(filename.lastIndexOf("\\")+1);
					
					paramsMap.put(item.getFieldName(), filetodb);//��Ϊ m_images
				}
			}
			BeanUtils.populate(object, paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	
}
