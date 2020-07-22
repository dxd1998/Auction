package com.dxd.pojo;

import java.util.List;

/**
 * 用户类
 * @author 99266
 *
 */
public class User {
	private int uId;
	private String uName;
	private String uLoginName;
	private String uPassword;
	private String createDate;
	//用户名下所有拍卖品
	private List<Product> productList;
	//用户权限
	private Role role;
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuLoginName() {
		return uLoginName;
	}
	public void setuLoginName(String uLoginName) {
		this.uLoginName = uLoginName;
	}
	public String getuPassword() {
		return uPassword;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
