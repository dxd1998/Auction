package com.dxd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxd.dao.ProductMapper;
import com.dxd.pojo.Order;
import com.dxd.pojo.Product;
import com.dxd.pojo.Record;
import com.dxd.service.ProductService;

/**
 * 拍卖品业务层实现类
 * @author 99266
 *
 */
@Service("productService")
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductMapper mapper;
	
	/**
	 * 查询所有拍卖品
	 */
	public List<Product> getAllProduct() {
		return mapper.getAllProduct();
	}

	/**
	 * 上传拍卖品
	 */
	public int insertProduct(Product pro) {
		return mapper.insertProduct(pro);
	}

	/**
	 * 绑定拍卖品状态
	 */
	public int insertProductType(Map<String, Object> map) {
		return mapper.insertProductType(map);
	}

	/**
	 * 标记拍卖品属于哪个用户
	 */
	public int insertProductUser(Map<String, Object> map) {
		return mapper.insertProductUser(map);
	}

	/**
	 * 查询拍卖品状态为待审核的
	 */
	public List<Product> getProductTypeBy1() {
		return mapper.getProductTypeBy1();
	}

	/**
	 * 改变拍卖品状态
	 */
	public int updateProductType(Map<String, Object> map) {
		return mapper.updateProductType(map);
	}

	/**
	 * 得到该用户所有拍品
	 */
	public List<Product> getProductByUser(Map<String, Object> map) {
		return mapper.getProductByUser(map);
	}

	/**
	 * 获得拍品状态id
	 */
	public int getProductType(Integer pId) {
		return mapper.getProductType(pId);
	}

	/**
	 * 删除指定拍品
	 */
	public int deleteProduct(Integer pId) {
		return mapper.deleteProduct(pId);
	}

	/**
	 * 记录拍卖记录
	 */
	public int insertRecord(Record record) {
		return mapper.insertRecord(record);
	}

	/**
	 * 添加一条拍卖成功订单信息
	 */
	public int insertOrder(Map<String,Object> map) {
		return mapper.insertOrder(map);
	}

	/**
	 * 得打登录用户所有的订单
	 */
	public List<Order> getAllOrder(Integer uId) {
		return mapper.getAllOrder(uId);
	}

	/**
	 * 获得所有拍卖记录
	 */
	public List<Record> getAllRecord(Map<String,Object> map) {
		return mapper.getAllRecord(map);
	}

	/**
	 * 总记录数
	 */
	public int getAllRecordCount() {
		return mapper.getAllRecordCount();
	}

	/**
	 * 改变拍品的价格
	 */
	public int updatePrice(Map<String, Object> map) {
		return mapper.updatePrice(map);
	}

}
