package com.show.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.show.image.pojo.MyImage;
import com.show.image.service.MyImageService;

public class IndexServlet extends HttpServlet {
	private MyImageService mService = new MyImageService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MyImage> indexImage = mService.getIndexImage();
		request.setAttribute("imageList", indexImage);
		List<MyImage> hotImage = mService.getHotImage();
		request.setAttribute("hotImage", hotImage);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
