package com.dxd.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dxd.pojo.Product;
import com.dxd.pojo.User;
import com.dxd.service.ProductService;
import com.dxd.service.UserService;
import com.dxd.util.PrintUtils;
import com.dxd.util.ReturnResult;
import com.dxd.util.SecurityUtils;

/**
 * 用户控制器
 * @author 99266
 *
 */
@Controller
@RequestMapping("user/")
public class UserController {
	@Autowired
	private UserService us;
	@Autowired
	private ProductService ps;
	/**
	 * 用户注册
	 * @param user
	 * @param response
	 */
	@RequestMapping(value="regist",method = RequestMethod.POST)
	public void regist(User user,HttpServletResponse response) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		user.setCreateDate(df.format(date));
		//使用md5加密
		String md5pwd = SecurityUtils.md5Hex(user.getuPassword());
		user.setuPassword(md5pwd);
		Map<String,Object> map = new HashMap<>();
		//添加用户
		int count = us.Regist(user);
		map.put("uId", user.getuId());
		map.put("rId", "2");
		//添加用户身份
		us.insertRole(map);
		ReturnResult result = new ReturnResult();
		if(count > 0) {
			result.getSuccess("注册成功!");
		}else {
			result.getFail("程序出现异常!请联系管理员!");
		}
		PrintUtils.getJsonString(response, result);
	}
	/**
	 * 登录
	 * @param user
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="login",method = RequestMethod.POST)
	public void checkLogin(User user,HttpServletResponse response,HttpServletRequest request) {
		ReturnResult result = new ReturnResult();
		//转换用户输入的密码
		String inputPwd = SecurityUtils.md5Hex(user.getuPassword());
		//查询与该用户名对应的用户
		User user1 = us.checkLogin(user.getuLoginName());
		if(user1  == null) {
			result.getFail("该用户名不存在!");
		}else {
			//判断密码
			if(user1.getuPassword().equals(inputPwd)) {
				//存储到session中
				request.getSession().setAttribute("loginUser", user1);
				result.getSuccess("登录成功!");
			}else {
				result.getFail("密码输入错误!");
			}
		}
		PrintUtils.getJsonString(response, result);
	}
	
	/**
	 * 跳转至注册页面
	 * @return
	 */
	@RequestMapping("toRegist")
	public String toRegist() {
		
		return "regist";
	}
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("toLogin")
	public String toLogin() {
		return "Login";
	}
	/**
	 * 主页面
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model) {
		//获得所有拍卖品
		List<Product> list = ps.getAllProduct();
		
		model.addAttribute("proList",list);
		return "index";
	}
	/**
	 * 退出
	 * @param request
	 * @return
	 */
	@RequestMapping("exit")
	public String exit(HttpServletRequest request) {
		request.getSession().removeAttribute("loginUser");
		return "Login";
	}
	/**
	 * 验证登录名
	 * @param response
	 */
	@RequestMapping(value="checkLoginName",method = RequestMethod.POST)
	public void checkLoginName(String uLoginName,HttpServletResponse response) {
		ReturnResult result = new ReturnResult();
		int count = us.checkLoginName(uLoginName);
		if(count > 0) {
			result.getFail("该用户名已存在!不能使用!");
		}else {
			result.getSuccess("可以使用!");
		}
		PrintUtils.getJsonString(response, result);
	}
}
