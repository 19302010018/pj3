package com.show.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.show.image.pojo.MyImage;
import com.show.image.service.MyImageService;
import com.show.user.service.FriendService;
import com.show.util.pager.PageBean;

import cn.itcast.servlet.BaseServlet;

/**
 * 好友servlet 主要用来查询好友信息
 */
public class FriendServlet extends BaseServlet {
	private FriendService friendService = new FriendService();
	private MyImageService myImageService = new MyImageService();

	/**
	 * 1.获取好友的收藏列表 2.存放到req中 3.转发到收藏列表页（复用我的收藏列表页）
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getFriendCollectioon(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int pc = getPc(req);
		String url = getUrl(req);
		Integer fid = Integer.parseInt(req.getParameter("fid"));
		String fname = req.getParameter("fname");
		String isShow = req.getParameter("isShow");
		String uid = req.getParameter("uid");
		PageBean<MyImage> pb = friendService.findImageByFid(fid, pc);
		List<MyImage> userImages = myImageService.findCollectionByUid(Integer.parseInt(uid));
		for (MyImage myImage : pb.getBeanList()) {
			myImage.setIsCollection(0);
			for (MyImage userImage : userImages) {
				if (userImage.getImageId() == myImage.getImageId()) {
					myImage.setIsCollection(1);
				}
			}
		}
		req.setAttribute("pb", pb);
		req.setAttribute("fname", fname);
		req.setAttribute("isShow", isShow);
		req.setAttribute("CollectionType", 2);
		return "f:/jsps/MyCollection.jsp";
	}

	/**
	 * 截取请求地址
	 * 
	 * @param req
	 * @return
	 */
	private String getUrl(HttpServletRequest req) {
		String url = req.getRequestURI() + "?" + req.getQueryString();

		/*
		 * 如果url中存在pc参数，截取掉，如果不存在那就不用截取。
		 */
		int index = url.lastIndexOf("&pc=");
		if (index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}

	/**
	 * 获取第几页
	 * 
	 * @param req
	 * @return
	 */
	private int getPc(HttpServletRequest req) {
		int pc = 1;
		String param = req.getParameter("pc");
		if (param != null && !param.trim().isEmpty()) {
			try {
				pc = Integer.parseInt(param);
			} catch (RuntimeException e) {
			}
		}
		return pc;
	}
}
