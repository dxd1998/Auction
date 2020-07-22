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
 * �û�ҵ���ʵ����
 * @author 99266
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper mapper;
	
	/**
	 * ע��
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public int Regist(User user) {
		return mapper.Regist(user);
	}

	/**
	 * �����
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public int insertRole(Map<String, Object> map) {
		return mapper.insertRole(map);
	}

	/**
	 * ��֤��¼
	 */
	public User checkLogin(String uLoginName) {
		return mapper.checkLogin(uLoginName);
	}

	/**
	 * ��֤��¼���Ƿ����
	 */
	public int checkLoginName(String uLoginName) {
		return mapper.checkLoginName(uLoginName);
	}

	/**
	 * ��ѯ�����û�
	 */
	public List<User> getAllUser() {
		return mapper.getAllUser();
	}
	
}
