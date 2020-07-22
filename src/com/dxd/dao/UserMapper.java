package com.dxd.dao;

import java.util.List;
import java.util.Map;

import com.dxd.pojo.User;

/**
 * �û��ӿ�
 */
public interface UserMapper {
	/**
	 * ע�����û�(Ĭ����ͨ�û�Ȩ��)
	 * @param user
	 * @return
	 */
	public int Regist(User user);
	/**
	 * ��Ӹ��û����
	 * @param map
	 * @return
	 */
	public int insertRole(Map<String,Object> map);
	/**
	 * ��֤��¼
	 * @param uLoginName
	 * @return
	 */
	public User checkLogin(String uLoginName);
	/**
	 * �ж��û���¼���Ƿ��Ѵ���
	 * @param uLoginName
	 * @return
	 */
	public int checkLoginName(String uLoginName);
	/**
	 * ��������û�
	 * @return
	 */
	public List<User> getAllUser();
	
}
