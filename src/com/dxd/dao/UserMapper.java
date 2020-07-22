package com.dxd.dao;

import java.util.List;
import java.util.Map;

import com.dxd.pojo.User;

/**
 * 用户接口
 */
public interface UserMapper {
	/**
	 * 注册新用户(默认普通用户权限)
	 * @param user
	 * @return
	 */
	public int Regist(User user);
	/**
	 * 添加该用户身份
	 * @param map
	 * @return
	 */
	public int insertRole(Map<String,Object> map);
	/**
	 * 验证登录
	 * @param uLoginName
	 * @return
	 */
	public User checkLogin(String uLoginName);
	/**
	 * 判断用户登录名是否已存在
	 * @param uLoginName
	 * @return
	 */
	public int checkLoginName(String uLoginName);
	/**
	 * 获得所有用户
	 * @return
	 */
	public List<User> getAllUser();
	
}
