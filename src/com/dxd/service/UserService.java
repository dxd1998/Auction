package com.dxd.service;

import java.util.List;
import java.util.Map;

import com.dxd.pojo.User;

/**
 * �û�ҵ���ӿ�
 * @author 99266
 *
 */
public interface UserService {
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
