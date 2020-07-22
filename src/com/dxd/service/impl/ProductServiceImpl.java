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
 * ����Ʒҵ���ʵ����
 * @author 99266
 *
 */
@Service("productService")
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductMapper mapper;
	
	/**
	 * ��ѯ��������Ʒ
	 */
	public List<Product> getAllProduct() {
		return mapper.getAllProduct();
	}

	/**
	 * �ϴ�����Ʒ
	 */
	public int insertProduct(Product pro) {
		return mapper.insertProduct(pro);
	}

	/**
	 * ������Ʒ״̬
	 */
	public int insertProductType(Map<String, Object> map) {
		return mapper.insertProductType(map);
	}

	/**
	 * �������Ʒ�����ĸ��û�
	 */
	public int insertProductUser(Map<String, Object> map) {
		return mapper.insertProductUser(map);
	}

	/**
	 * ��ѯ����Ʒ״̬Ϊ����˵�
	 */
	public List<Product> getProductTypeBy1() {
		return mapper.getProductTypeBy1();
	}

	/**
	 * �ı�����Ʒ״̬
	 */
	public int updateProductType(Map<String, Object> map) {
		return mapper.updateProductType(map);
	}

	/**
	 * �õ����û�������Ʒ
	 */
	public List<Product> getProductByUser(Map<String, Object> map) {
		return mapper.getProductByUser(map);
	}

	/**
	 * �����Ʒ״̬id
	 */
	public int getProductType(Integer pId) {
		return mapper.getProductType(pId);
	}

	/**
	 * ɾ��ָ����Ʒ
	 */
	public int deleteProduct(Integer pId) {
		return mapper.deleteProduct(pId);
	}

	/**
	 * ��¼������¼
	 */
	public int insertRecord(Record record) {
		return mapper.insertRecord(record);
	}

	/**
	 * ���һ�������ɹ�������Ϣ
	 */
	public int insertOrder(Map<String,Object> map) {
		return mapper.insertOrder(map);
	}

	/**
	 * �ô��¼�û����еĶ���
	 */
	public List<Order> getAllOrder(Integer uId) {
		return mapper.getAllOrder(uId);
	}

	/**
	 * �������������¼
	 */
	public List<Record> getAllRecord(Map<String,Object> map) {
		return mapper.getAllRecord(map);
	}

	/**
	 * �ܼ�¼��
	 */
	public int getAllRecordCount() {
		return mapper.getAllRecordCount();
	}

	/**
	 * �ı���Ʒ�ļ۸�
	 */
	public int updatePrice(Map<String, Object> map) {
		return mapper.updatePrice(map);
	}

}
