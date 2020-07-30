package com.show.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.show.image.pojo.MyImage;
import com.show.image.service.MyImageService;
import com.show.user.pojo.Friend;
import com.show.user.pojo.User;
import com.show.user.service.FriendService;
import com.show.user.service.UserService;
import com.show.util.MD5Util;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

/**
 * 用户Servlet
 * 
 * @author 1.实现登录功能 >封装表单数据到use >校验表单数据，若有错误将错误添加到map中 >查看用户是否存在，如果不存在：
 *         保存错误信息：用户名或密码错误 保存用户数据：为了回显 转发到login.jsp * 保存错误信息：您没有激活 * 保存表单数据：为了回显
 *         * 转发到login.jsp >登录成功： * 保存当前查询出的user到session中
 */
public class UserServlet extends BaseServlet {
	private UserService userService = new UserService();
	private MyImageService mService = new MyImageService();
	private FriendService friendService = new FriendService();

	/**
	 * 退出
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String quit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		return "r:/jsps/login.jsp";
	}

	/**
	 * 登录
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */

	public String login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		formUser.setPassword(MD5Util.MD5(formUser.getPassword()));
		User user = userService.login(formUser);
		String verifyCode = req.getParameter("verifyCode");
		String vcode = (String) req.getSession().getAttribute("vCode");
		boolean bool = verifyCode.equalsIgnoreCase(vcode);
		if (!bool) {
			req.setAttribute("msg", "验证码错误！！！");
			req.setAttribute("username", formUser.getUsername());
			return "f:/jsps/login.jsp";
		} else if (user == null) {
			req.setAttribute("msg", "用户名或密码错误！！！");
			req.setAttribute("username", formUser.getUsername());
			return "f:/jsps/login.jsp";
		} else {
			req.getSession().setAttribute("sessionUser", user);
			String loginname = user.getUsername();
			List<MyImage> indexImage = mService.getIndexImage();
			req.setAttribute("imageList", indexImage);
			List<Friend> friendList = friendService.findFriendByUid(user.getUid());
			req.getSession().setAttribute("friendList", friendList);
			return "f:/IndexServlet";
		}
	}

	/**
	 * 1.注册方法 2.校验用户名是否已经被使用 3.校验验证码是否正确 4.校验两次输入的密码是否一致
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		String passwordsignup_confirm = req.getParameter("passwordsignup_confirm");
		String verifyCode = req.getParameter("verifyCode");
		String vcode = (String) req.getSession().getAttribute("vCode");
		boolean bool = verifyCode.equalsIgnoreCase(vcode);
		req.setAttribute("regsiter_username", formUser.getUsername());
		if (!formUser.getPassword().equals(passwordsignup_confirm)) {
			req.setAttribute("regsiter_msg", "两次密码不一致，请重新设置");
			return "f:/jsps/register.jsp";
		} else if (!bool) {
			req.setAttribute("regsiter_msg", "验证码不正确");
			return "f:/jsps/register.jsp";
		} else if (userService.valiteUseName(formUser.getUsername())) {
			req.setAttribute("regsiter_msg", "用户名已被使用，请重新选择一个邮箱");
			return "f:/jsps/register.jsp";
		} else {
			// MD5加密
			formUser.setPassword(MD5Util.MD5(formUser.getPassword()));
			userService.save(formUser);
			return "f:/jsps/login.jsp";
		}
	}
}
