package com.dxd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dxd.dao.UserMapper;
import com.dxd.pojo.User;
import com.dxd.service.UserService;

/**
 * 用户业务层实现类
 * @author 99266
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper mapper;
	
	/**
	 * 注册
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public int Regist(User user) {
		return mapper.Regist(user);
	}

	/**
	 * 绑定身份
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public int insertRole(Map<String, Object> map) {
		return mapper.insertRole(map);
	}

	/**
	 * 验证登录
	 */
	public User checkLogin(String uLoginName) {
		return mapper.checkLogin(uLoginName);
	}

	/**
	 * 验证登录名是否存在
	 */
	public int checkLoginName(String uLoginName) {
		return mapper.checkLoginName(uLoginName);
	}

	/**
	 * 查询所有用户
	 */
	public List<User> getAllUser() {
		return mapper.getAllUser();
	}
	
}
