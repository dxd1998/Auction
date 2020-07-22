package com.dxd.dao;

import java.util.List;
import java.util.Map;

import com.dxd.pojo.Order;
import com.dxd.pojo.Product;
import com.dxd.pojo.Record;
import com.dxd.pojo.Type;
import com.dxd.util.Pager;

/**
 * ����Ʒ�ӿ�
 * @author 99266
 *
 */
public interface ProductMapper {
	/**
	 * ���������Ʒ
	 * @return
	 */
	List<Product> getAllProduct();
	/**
	 * �ϴ�����Ʒ
	 * @return
	 */
	int insertProduct(Product pro);
	/**
	 * ������Ʒ״̬
	 * @param type
	 * @return
	 */
	int insertProductType(Map<String,Object> map);
	/**
	 * �û�������Ʒ
	 * @param map
	 * @return
	 */
	int insertProductUser(Map<String,Object> map);
	/**
	 * ������д���˵�����Ʒ
	 * @return
	 */
	List<Product> getProductTypeBy1();
	/**
	 * �ı�����Ʒ״̬
	 * @param map
	 * @return
	 */
	int updateProductType(Map<String,Object> map);
	/**
	 * �õ����û�������Ʒ
	 * @param map
	 * @return
	 */
	List<Product> getProductByUser(Map<String,Object> map);
	/**
	 * �����Ʒ״̬id
	 * @param pId
	 * @return
	 */
	int getProductType(Integer pId);
	/**
	 * ɾ����Ʒ
	 * @param pId
	 * @return
	 */
	int deleteProduct(Integer pId);
	/**
	 * ��¼���ļ�¼
	 * @param record
	 * @return
	 */
	int insertRecord(Record record);
	/**
	 * ���һ�������ɹ�����
	 * @param order
	 * @return
	 */
	int insertOrder(Map<String,Object> map);
	/**
	 * ��ѯ����ָ���û����ж���
	 * @return
	 */
	List<Order> getAllOrder(Integer uId);
	/**
	 * ������о��ļ�¼
	 * @return
	 */
	List<Record> getAllRecord(Map<String,Object> map);
	/**
	 * �ܼ�¼��
	 * @return
	 */
	int getAllRecordCount();
	/**
	 * �޸���Ʒ��ǰ���ļ۸�
	 * @param price
	 * @return
	 */
	int updatePrice(Map<String,Object> map);
}
