package com.dxd.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dxd.pojo.Order;
import com.dxd.pojo.Product;
import com.dxd.pojo.Record;
import com.dxd.pojo.User;
import com.dxd.service.ProductService;
import com.dxd.service.UserService;
import com.dxd.util.Pager;
import com.dxd.util.PrintUtils;
import com.dxd.util.ReturnResult;

/**
 * 商品控制器
 * @author 99266
 *
 */
@Controller
@RequestMapping("product/")
public class ProductController {
	@Autowired
	private ProductService ps;
	@Autowired
	private UserService us;
	/**
	 * 上传拍卖品页面
	 * @return
	 */
	@RequestMapping("toAdd")
	public String toAdd() {
		return "addProduct";
	}
	/**
	 * 上传拍卖品
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		//上传路径
		String realPath = request.getSession().getServletContext().getRealPath("/static/img");
		String fileUploadName = "";  //文件名
		String fileName = ""; //字段名
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> ito = items.iterator();
		//当前时间
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Product pro = new Product();
		pro.setJoinDate(df.format(date));
		//遍历
		while(ito.hasNext()) {
			FileItem item = ito.next();
			//普通字段
			if(item.isFormField()) {
				fileName = item.getFieldName();
				if(fileName.equals("pName")) {
					pro.setpName(item.getString("utf-8"));
				}else if(fileName.equals("pPrice")) {
					pro.setpPrice(Double.parseDouble(item.getString("utf-8")));
				}else if(fileName.equals("startDate")) {
					pro.setStartDate(item.getString("utf-8"));
				}else if(fileName.equals("endDate")) {
					pro.setEndDate(item.getString("utf-8"));
				}else if(fileName.equals("firstPrice")) {
					pro.setFirstPrice(Double.parseDouble(item.getString("utf-8")));
				}
			}else {  //文件类型
				fileUploadName = item.getName();
				System.out.println(fileUploadName);
				pro.setpImg(fileUploadName);
				File saveFile = new File(realPath,fileUploadName);
				item.write(saveFile);
			}
		}
		//上传拍卖品
		int count = ps.insertProduct(pro);
		//绑定该拍卖品为待审核
		Map<String,Object> map = new HashMap<>();
		map.put("pId", pro.getpId());
		map.put("tId", "1");
		ps.insertProductType(map);
		//给拍卖品绑定用户(当前登录用户)
		User user = (User)request.getSession().getAttribute("loginUser");
		map.put("uId", user.getuId());
		ps.insertProductUser(map);
		ReturnResult result = new ReturnResult();
		if(count > 0) {
			result.getSuccess("上传拍卖品成功!请等待审核!");
		}else {
			result.getFail("系统出现异常!请联系管理员!");
		}
		PrintUtils.getJsonString(response, result);
	}
	/**
	 * 跳转去审核拍卖品页面
	 * @param model
	 * @return
	 */
	@RequestMapping("checkproduct")
	public String toCheckProduct(Model model) {
		//得到所有待审核拍卖品
		List<Product> product = ps.getProductTypeBy1();
		
		//存储
		model.addAttribute("productList",product);
		return "checkProduct";
	}
	/**
	 * 审核拍卖品
	 * @param array
	 * @param response
	 */
	@RequestMapping(value="updateProduct",method=RequestMethod.POST)
	public void updateProduct(String[] array,HttpServletResponse response) {
		ReturnResult result = new ReturnResult();
		Map<String,Object> map = new HashMap<>();
		map.put("tId", "2");
		int count = 0;
		//循环数组
		for(int i=0;i<array.length;i++) {
			//添加商品的id
			map.put("pId", array[i]);
			//修改
			count = ps.updateProductType(map);
		}
		if(count > 0) {
			result.getSuccess("审核成功!");
		}else {
			result.getFail("系统出现异常!请联系管理员!");
		}
		PrintUtils.getJsonString(response, result);
	}
	/**
	 * 审核不通过
	 * @param array
	 * @param response
	 */
	@RequestMapping(value="updateProduct2",method=RequestMethod.POST)
	public void updateProduct2(String[] array,HttpServletResponse response) {
		ReturnResult result = new ReturnResult();
		Map<String,Object> map = new HashMap<>();
		map.put("tId", "6");
		int count = 0;
		//循环数组
		for(int i=0;i<array.length;i++) {
			//添加商品的id
			map.put("pId", array[i]);
			//修改
			count = ps.updateProductType(map);
		}
		if(count > 0) {
			result.getSuccess("审核成功!");
		}else {
			result.getFail("系统出现异常!请联系管理员!");
		}
		PrintUtils.getJsonString(response, result);
	}
	/**
	 * 我的拍品
	 * @param model
	 * @return
	 */
	@RequestMapping("myproduct")
	public String myProduct(Model model,HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("loginUser");
		Map<String,Object> map = new HashMap<>();
		map.put("uId", user.getuId());
		List<Product> productList;
		if(user.getRole().getrId() == 1) {
			productList = ps.getAllProduct();
		}else {
			//得到当前登录用户的所有拍品
			productList = ps.getProductByUser(map);
		}
		model.addAttribute("productList",productList);
		return "myProduct";
	}
	/**
	 * 下架拍品
	 * @param array
	 * @param response
	 */
	@RequestMapping(value="dropDown",method=RequestMethod.POST)
	public void dropDown(String[] array,HttpServletResponse response) {
		ReturnResult result = new ReturnResult();
		Map<String,Object> map = new HashMap<>();
		int count = 0;
		//改变拍品状态为5 下架状态
		map.put("tId", "5");
		//循环数组
		for(int i=0;i<array.length;i++) {
			//判断该拍品id是否重复下架
			int typeId = ps.getProductType(Integer.parseInt(array[i]));
			if(typeId == 5) {
				count = 0;
				break;
			}else {
				//存储拍品id
				map.put("pId", array[i]);
				//修改
				count = ps.updateProductType(map);
			}
		}
		if(count > 0) {
			result.getSuccess("下架成功!");
		}else {
			result.getFail("拍品不能重复下架!");
		}
		PrintUtils.getJsonString(response, result);
	}
	/**
	 * 上架拍品
	 * @param array
	 * @param response
	 */
	@RequestMapping(value="alterUp",method = RequestMethod.POST)
	public void alterUp(String[] array,HttpServletResponse response) {
		ReturnResult result = new ReturnResult();
		Map<String,Object> map = new HashMap<>();
		map.put("tId", "1");
		int count = 0;
		//循环数组
		for(int i=0;i<array.length;i++) {
			//判断该拍品id是否重复上架
			int typeId = ps.getProductType(Integer.parseInt(array[i]));
			if(typeId == 1) {
				count = 0;
				break;
			}else {
				//存储拍品id
				map.put("pId", array[i]);
				//修改
				count = ps.updateProductType(map);
			}
			
		}
		if(count > 0) {
			result.getSuccess("上架成功!请等待管理员审核!");
		}else {
			result.getFail("拍品无法重复上架!");
		}
		PrintUtils.getJsonString(response, result);
	}
	/**
	 * 删除拍品
	 * @param array
	 * @param response
	 */
	@RequestMapping(value="deleteProduct",method = RequestMethod.POST)
	public void deleteProduct(String[] array,HttpServletResponse response) {
		ReturnResult result = new ReturnResult();
		int count = 0;
		//循环数组
		for(int i=0;i<array.length;i++) {
			count = ps.deleteProduct(Integer.parseInt(array[i]));
		}
		if(count > 0) {
			result.getSuccess("删除成功!");
		}else {
			result.getFail("系统出现异常!请联系管理员!");
		}
		PrintUtils.getJsonString(response, result);
	}
	/**
	 * 开始拍卖
	 * @param pId
	 * @param pPrice
	 * @param response
	 */
	@RequestMapping(value="startAuction",method = RequestMethod.POST)
	public void startAuction(String pId,int countdown,HttpServletResponse response) {
		ReturnResult result = new ReturnResult();
		//得到所有拍品
		List<Product> productList = ps.getAllProduct();
		Product pro = null;
		//循环得到被竞拍的拍品
		for(Product pro1 : productList) {
			if(pro1.getpId() == Integer.parseInt(pId)) {
				pro = pro1;
			}
		}
		double givePrice = pro.getFirstPrice();  //起拍价
		//获得所有用户
		List<User> userList = us.getAllUser();
		//创建当前时间
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//创建记录类对象
		Record record = null;
		//出价定义(万)
		double[] priceNum = {1,2,3};
		Random r = new Random(); //随机数对象
		boolean flag = false; //记录是否流拍
		Map<String,Object> map = new HashMap<>();
		map.put("tId", "4");
		map.put("pId", pro.getpId());
		Map<String,Object> map2 = new HashMap<>();
		String name = ""; //竞拍成功者名称
		int num = r.nextInt(3); //随机出价
		int personNum = r.nextInt(userList.size()-1);
		User user = userList.get(personNum); //随机出价的用户
		name = user.getuName();
		//循环模拟多人竞拍
			//判断拍卖时间是否结束
			if(countdown == 0 && givePrice >= pro.getpPrice()) {
				//修改当前竞拍商品的状态
				ps.updateProductType(map);
				//当前用户竞拍成功,生成一条订单,绑定当前用户
				map2.put("uId",user.getuId());
				map2.put("pId", pro.getpId());
				map2.put("createDate", df.format(date));
				//添加
				ps.insertOrder(map2);
				//添加成功记录
				record = new Record();
				givePrice = givePrice+priceNum[num];//出价后的总金额
				//存储进记录对象中
				record.setpName(pro.getpName());
				record.setGiveDate(df.format(date));
				record.setOnePrice(givePrice);
				record.setuName(user.getuName());
				//存储出价记录
				ps.insertRecord(record);
				flag = true; //竞拍成功
			}else {
				record = new Record();
				givePrice = givePrice+priceNum[num];//出价后的总金额
				//存储进记录对象中
				record.setpName(pro.getpName());
				record.setGiveDate(df.format(date));
				record.setOnePrice(givePrice);
				record.setuName(user.getuName());
				//存储出价记录
				ps.insertRecord(record);
			}
			//给竞拍商品赋值
			Map<String,Object> map3 = new HashMap<>();
			map3.put("price", givePrice);
			map3.put("pId", pro.getpId());
			ps.updatePrice(map3);
		//判断
		if(flag) {  //竞拍成功
			result.getSuccess("恭喜用户"+name+"成功竞拍:"+pro.getpName()+"价格为:"+givePrice+"万!");
		}else { //20次出价未能拍下  流拍
			result.getFail("用户:"+name+",出价为:"+givePrice+"万元!");
		}
		PrintUtils.getJsonString(response, result);
	}
	/**
	 * 得到当前用户所有订单
	 * @param model
	 * @return
	 */
	@RequestMapping("toOrder")
	public String getOrder(Model model,HttpServletRequest request) {
		//获得当前登录用户
		User user = (User)request.getSession().getAttribute("loginUser");
		//得到所有订单
		List<Order> orderList = ps.getAllOrder(user.getuId());
		//存储
		model.addAttribute("orderList",orderList);
		return "orderList";
	}
	/**
	 * 得到所有拍卖记录
	 * @param model
	 * @return
	 */
	@RequestMapping("toRecord")
	public String getRecord(Model model,@RequestParam(value="currentPage",required=false)String currentPage) {
		int count = ps.getAllRecordCount();
		int currentPage1 = 1;
		if(currentPage != null) {
			currentPage1 = Integer.parseInt(currentPage);
		}
		Pager pager = new Pager(count,10,currentPage1);
		pager.setUrl("spring/product/toRecord");
		Map<String,Object> map = new HashMap<>();
		map.put("currentPage", pager.getCurrentPage());
		map.put("rowPerPage", pager.getRowPerPage());
		//得到所有拍卖记录
		List<Record> recordList = ps.getAllRecord(map);
		
		model.addAttribute("pager",pager);
		model.addAttribute("recordList",recordList);
		return "recordList";
	}
}
