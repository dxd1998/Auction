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
 * �û�������
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
	 * �û�ע��
	 * @param user
	 * @param response
	 */
	@RequestMapping(value="regist",method = RequestMethod.POST)
	public void regist(User user,HttpServletResponse response) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		user.setCreateDate(df.format(date));
		//ʹ��md5����
		String md5pwd = SecurityUtils.md5Hex(user.getuPassword());
		user.setuPassword(md5pwd);
		Map<String,Object> map = new HashMap<>();
		//����û�
		int count = us.Regist(user);
		map.put("uId", user.getuId());
		map.put("rId", "2");
		//����û����
		us.insertRole(map);
		ReturnResult result = new ReturnResult();
		if(count > 0) {
			result.getSuccess("ע��ɹ�!");
		}else {
			result.getFail("��������쳣!����ϵ����Ա!");
		}
		PrintUtils.getJsonString(response, result);
	}
	/**
	 * ��¼
	 * @param user
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="login",method = RequestMethod.POST)
	public void checkLogin(User user,HttpServletResponse response,HttpServletRequest request) {
		ReturnResult result = new ReturnResult();
		//ת���û����������
		String inputPwd = SecurityUtils.md5Hex(user.getuPassword());
		//��ѯ����û�����Ӧ���û�
		User user1 = us.checkLogin(user.getuLoginName());
		if(user1  == null) {
			result.getFail("���û���������!");
		}else {
			//�ж�����
			if(user1.getuPassword().equals(inputPwd)) {
				//�洢��session��
				request.getSession().setAttribute("loginUser", user1);
				result.getSuccess("��¼�ɹ�!");
			}else {
				result.getFail("�����������!");
			}
		}
		PrintUtils.getJsonString(response, result);
	}
	
	/**
	 * ��ת��ע��ҳ��
	 * @return
	 */
	@RequestMapping("toRegist")
	public String toRegist() {
		
		return "regist";
	}
	/**
	 * ��¼ҳ��
	 * @return
	 */
	@RequestMapping("toLogin")
	public String toLogin() {
		return "Login";
	}
	/**
	 * ��ҳ��
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model) {
		//�����������Ʒ
		List<Product> list = ps.getAllProduct();
		
		model.addAttribute("proList",list);
		return "index";
	}
	/**
	 * �˳�
	 * @param request
	 * @return
	 */
	@RequestMapping("exit")
	public String exit(HttpServletRequest request) {
		request.getSession().removeAttribute("loginUser");
		return "Login";
	}
	/**
	 * ��֤��¼��
	 * @param response
	 */
	@RequestMapping(value="checkLoginName",method = RequestMethod.POST)
	public void checkLoginName(String uLoginName,HttpServletResponse response) {
		ReturnResult result = new ReturnResult();
		int count = us.checkLoginName(uLoginName);
		if(count > 0) {
			result.getFail("���û����Ѵ���!����ʹ��!");
		}else {
			result.getSuccess("����ʹ��!");
		}
		PrintUtils.getJsonString(response, result);
	}
}
