package com.show.upload.servlet;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.show.image.pojo.MyImage;
import com.show.upload.service.UploadService;

import cn.itcast.commons.CommonUtils;

/**
 * 1.文件上传 2.上传到服务的webapps目录下
 *
 */
public class UploadServlet extends HttpServlet {
	private UploadService uploadService = new UploadService();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(1024 * 1024);// 设置单个上传的文件上限为80KB
		List<FileItem> fileItemList = null;
		try {
			fileItemList = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			// 如果出现这个异步，说明单个文件超出了80KB
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		for (FileItem fileItem : fileItemList) {
			if (fileItem.isFormField()) {// 如果是普通表单字段
				map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
			}
		}
		Integer uid = Integer.parseInt(map.get("uid").toString());
		MyImage myImage = CommonUtils.toBean(map, MyImage.class);// 把Map中大部分数据封装到Book对象中
		myImage.setTitle(map.get("Title").toString());
		myImage.setDescription(map.get("Description").toString());
		myImage.setCityCode(Double.parseDouble(map.get("CityCode").toString()));
		myImage.setCountryCodeISO(map.get("CountryCodeISO").toString());
		myImage.setUid(uid);
		myImage.setTopic(map.get("Topic").toString());

		FileItem fileItem = fileItemList.get(1);
		String filename = fileItem.getName();
		int index = filename.lastIndexOf("\\");
		if (index != -1) {
			filename = filename.substring(index + 1);
		}
		// 给文件名添加uuid前缀，避免文件同名现象
		filename = CommonUtils.uuid() + "_" + filename;

		String savepath = this.getServletContext().getRealPath("/images");

		File destFile = new File(savepath, filename);
		try {
			fileItem.write(destFile);// 它会把临时文件重定向到指定的路径，再删除临时文件
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// 校验尺寸
		// 1. 使用文件路径创建ImageIcon
		ImageIcon icon = new ImageIcon(destFile.getAbsolutePath());
		// 2. 通过ImageIcon得到Image对象
		Image image = icon.getImage();
		myImage.setPATH(filename);
		uploadService.add(myImage);
		request.getRequestDispatcher("/MyImageServlet?method=getMyImage&uid=" + uid).forward(request, response);
	}
}
