package com.show.image.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.show.city.pojo.GeoCities;
import com.show.city.service.GeoService;
import com.show.city.vo.Geo;
import com.show.image.pojo.MyImage;
import com.show.image.service.MyImageService;
import com.show.image.vo.ImageDetails;
import com.show.util.pager.PageBean;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

/**
 * 1. 图片管理
 * 
 * @author
 *
 */
public class MyImageServlet extends BaseServlet {

	private MyImageService mService = new MyImageService();
	private GeoService geoService = new GeoService();

	/**
	 * 多条件查询
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCombination(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		MyImage myImage = CommonUtils.toBean(req.getParameterMap(), MyImage.class);
		System.out.println(myImage);
		int pc = getPc(req);
		String url = getUrl(req);
		PageBean<MyImage> pb = mService.findByCombination(myImage, pc);
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/ImageList.jsp";
	}

	/**
	 * 标题模糊查询
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByTitle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String uid = req.getParameter("uid");
		int pc = getPc(req);
		String url = getUrl(req);
		PageBean<MyImage> pb = mService.findByTitle(title, uid, pc);
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/ImageList.jsp";
	}

	/**
	 * 取消收藏
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deleteCollection(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String imageId = req.getParameter("imageId");
		mService.deleteCollection(uid, imageId);
		return "r:/MyImageServlet?method=getMyCollectioon&uid=" + uid;
	}

	/**
	 * 加入收藏
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addCollection(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String imageId = req.getParameter("imageId");
		mService.addCollection(uid, imageId);
		return "r:/MyImageServlet?method=getMyCollectioon&uid=" + uid;
	}

	/**
	 * 图片详情
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String imageDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String imageId = req.getParameter("imageId");
		String uid = req.getParameter("uid");
		ImageDetails details = mService.getImageDetils(imageId, uid);
		req.setAttribute("details", details);
		return "f:/jsps/Details.jsp";
	}

	/**
	 * 删除图片
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deleteImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer uid = Integer.parseInt(req.getParameter("uid"));
		Integer imageId = Integer.parseInt(req.getParameter("imageId"));
		mService.deleteImage(uid, imageId);
		resp.sendRedirect("/ShowImage/MyImageServlet?method=getMyImage&uid=" + uid);
		return null;
	}

	/**
	 * 获取我的收藏
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getMyCollectioon(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer uid = Integer.parseInt(req.getParameter("uid"));
		int pc = getPc(req);
		String url = getUrl(req);
		PageBean<MyImage> pb = mService.findCollectionByUid(uid, pc);
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		req.setAttribute("CollectionType", 1);
		return "f:/jsps/MyCollection.jsp";
	}

	/**
	 * 修改跳转信息
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer ImageID = Integer.parseInt(req.getParameter("ImageID"));
		MyImage myImage = mService.findImageByImageID(ImageID);
		req.setAttribute("myImage", myImage);
		List<Geo> countryList = geoService.findAllGeoCountry();
		req.setAttribute("countryList", countryList);
		List<GeoCities> city = geoService.findAllCity(myImage.getCountryCodeISO());
		req.setAttribute("cityList", city);
		return "f:/jsps/UpdateImage.jsp";
	}

	/**
	 * 搜索准备信息
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Geo> countryList = geoService.findAllGeoCountry();
		req.setAttribute("countryList", countryList);
		List<GeoCities> city = geoService.findAllCity("CA");
		req.setAttribute("cityList", city);
		return "f:/jsps/Search.jsp";
	}

	/***
	 * 修改图片信息
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */

	public String retImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map map = req.getParameterMap();
		MyImage myImage = CommonUtils.toBean(map, MyImage.class);
		mService.updateImage(myImage);
		resp.sendRedirect("/ShowImage/MyImageServlet?method=getMyImage&uid=" + myImage.getUid());
//		req.getRequestDispatcher("/MyImageServlet?method=getMyImage&uid=" + myImage.getUid()).forward(req, resp);
		return null;
	}

	/**
	 * 获取我上传的图片
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getMyImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer uid = Integer.parseInt(req.getParameter("uid"));
		int pc = getPc(req);
		String url = getUrl(req);
		PageBean<MyImage> pb = mService.findMyImageByUid(uid, pc);
		pb.setUrl(url);
		req.setAttribute("pb", pb);

		return "f:/jsps/MyImage.jsp";
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
