package com.dxd.dao;

import java.util.List;
import java.util.Map;

import com.dxd.pojo.Order;
import com.dxd.pojo.Product;
import com.dxd.pojo.Record;
import com.dxd.pojo.Type;
import com.dxd.util.Pager;

/**
 * 拍卖品接口
 * @author 99266
 *
 */
public interface ProductMapper {
	/**
	 * 获得所有拍品
	 * @return
	 */
	List<Product> getAllProduct();
	/**
	 * 上传拍卖品
	 * @return
	 */
	int insertProduct(Product pro);
	/**
	 * 绑定拍卖品状态
	 * @param type
	 * @return
	 */
	int insertProductType(Map<String,Object> map);
	/**
	 * 用户绑定拍卖品
	 * @param map
	 * @return
	 */
	int insertProductUser(Map<String,Object> map);
	/**
	 * 获得所有待审核的拍卖品
	 * @return
	 */
	List<Product> getProductTypeBy1();
	/**
	 * 改变拍卖品状态
	 * @param map
	 * @return
	 */
	int updateProductType(Map<String,Object> map);
	/**
	 * 得到该用户所有拍品
	 * @param map
	 * @return
	 */
	List<Product> getProductByUser(Map<String,Object> map);
	/**
	 * 获得拍品状态id
	 * @param pId
	 * @return
	 */
	int getProductType(Integer pId);
	/**
	 * 删除拍品
	 * @param pId
	 * @return
	 */
	int deleteProduct(Integer pId);
	/**
	 * 记录竞拍记录
	 * @param record
	 * @return
	 */
	int insertRecord(Record record);
	/**
	 * 添加一条拍卖成功订单
	 * @param order
	 * @return
	 */
	int insertOrder(Map<String,Object> map);
	/**
	 * 查询所有指定用户所有订单
	 * @return
	 */
	List<Order> getAllOrder(Integer uId);
	/**
	 * 获得所有竞拍记录
	 * @return
	 */
	List<Record> getAllRecord(Map<String,Object> map);
	/**
	 * 总记录数
	 * @return
	 */
	int getAllRecordCount();
	/**
	 * 修改商品当前竞拍价格
	 * @param price
	 * @return
	 */
	int updatePrice(Map<String,Object> map);
}
