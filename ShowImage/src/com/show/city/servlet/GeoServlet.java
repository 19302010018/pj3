package com.show.city.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.show.city.pojo.GeoCities;
import com.show.city.service.GeoService;
import com.show.city.vo.Geo;

import cn.itcast.servlet.BaseServlet;
import net.sf.json.JSONArray;

/**
 * 1.地理位置管理
 * 
 * @author
 *
 */
public class GeoServlet extends BaseServlet {
	private GeoService geoService = new GeoService();

	public String getCountry(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Geo> country = geoService.findAllGeoCountry();
		request.setAttribute("countryList", country);
		return "/jsps/upload.jsp";
	}

	public String getCity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String iso = request.getParameter("iso");
		List<GeoCities> cities = geoService.findAllCity(iso);
		JSONArray json = JSONArray.fromObject(cities);
		String json_str = json.toString();
		response.getWriter().print(json_str);
		return null;
	}
}
