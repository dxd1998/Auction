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
 * ��Ʒ������
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
	 * �ϴ�����Ʒҳ��
	 * @return
	 */
	@RequestMapping("toAdd")
	public String toAdd() {
		return "addProduct";
	}
	/**
	 * �ϴ�����Ʒ
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		//�ϴ�·��
		String realPath = request.getSession().getServletContext().getRealPath("/static/img");
		String fileUploadName = "";  //�ļ���
		String fileName = ""; //�ֶ���
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> ito = items.iterator();
		//��ǰʱ��
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Product pro = new Product();
		pro.setJoinDate(df.format(date));
		//����
		while(ito.hasNext()) {
			FileItem item = ito.next();
			//��ͨ�ֶ�
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
			}else {  //�ļ�����
				fileUploadName = item.getName();
				System.out.println(fileUploadName);
				pro.setpImg(fileUploadName);
				File saveFile = new File(realPath,fileUploadName);
				item.write(saveFile);
			}
		}
		//�ϴ�����Ʒ
		int count = ps.insertProduct(pro);
		//�󶨸�����ƷΪ�����
		Map<String,Object> map = new HashMap<>();
		map.put("pId", pro.getpId());
		map.put("tId", "1");
		ps.insertProductType(map);
		//������Ʒ���û�(��ǰ��¼�û�)
		User user = (User)request.getSession().getAttribute("loginUser");
		map.put("uId", user.getuId());
		ps.insertProductUser(map);
		ReturnResult result = new ReturnResult();
		if(count > 0) {
			result.getSuccess("�ϴ�����Ʒ�ɹ�!��ȴ����!");
		}else {
			result.getFail("ϵͳ�����쳣!����ϵ����Ա!");
		}
		PrintUtils.getJsonString(response, result);
	}
	/**
	 * ��תȥ�������Ʒҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping("checkproduct")
	public String toCheckProduct(Model model) {
		//�õ����д��������Ʒ
		List<Product> product = ps.getProductTypeBy1();
		
		//�洢
		model.addAttribute("productList",product);
		return "checkProduct";
	}
	/**
	 * �������Ʒ
	 * @param array
	 * @param response
	 */
	@RequestMapping(value="updateProduct",method=RequestMethod.POST)
	public void updateProduct(String[] array,HttpServletResponse response) {
		ReturnResult result = new ReturnResult();
		Map<String,Object> map = new HashMap<>();
		map.put("tId", "2");
		int count = 0;
		//ѭ������
		for(int i=0;i<array.length;i++) {
			//�����Ʒ��id
			map.put("pId", array[i]);
			//�޸�
			count = ps.updateProductType(map);
		}
		if(count > 0) {
			result.getSuccess("��˳ɹ�!");
		}else {
			result.getFail("ϵͳ�����쳣!����ϵ����Ա!");
		}
		PrintUtils.getJsonString(response, result);
	}
	/**
	 * ��˲�ͨ��
	 * @param array
	 * @param response
	 */
	@RequestMapping(value="updateProduct2",method=RequestMethod.POST)
	public void updateProduct2(String[] array,HttpServletResponse response) {
		ReturnResult result = new ReturnResult();
		Map<String,Object> map = new HashMap<>();
		map.put("tId", "6");
		int count = 0;
		//ѭ������
		for(int i=0;i<array.length;i++) {
			//�����Ʒ��id
			map.put("pId", array[i]);
			//�޸�
			count = ps.updateProductType(map);
		}
		if(count > 0) {
			result.getSuccess("��˳ɹ�!");
		}else {
			result.getFail("ϵͳ�����쳣!����ϵ����Ա!");
		}
		PrintUtils.getJsonString(response, result);
	}
	/**
	 * �ҵ���Ʒ
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
			//�õ���ǰ��¼�û���������Ʒ
			productList = ps.getProductByUser(map);
		}
		model.addAttribute("productList",productList);
		return "myProduct";
	}
	/**
	 * �¼���Ʒ
	 * @param array
	 * @param response
	 */
	@RequestMapping(value="dropDown",method=RequestMethod.POST)
	public void dropDown(String[] array,HttpServletResponse response) {
		ReturnResult result = new ReturnResult();
		Map<String,Object> map = new HashMap<>();
		int count = 0;
		//�ı���Ʒ״̬Ϊ5 �¼�״̬
		map.put("tId", "5");
		//ѭ������
		for(int i=0;i<array.length;i++) {
			//�жϸ���Ʒid�Ƿ��ظ��¼�
			int typeId = ps.getProductType(Integer.parseInt(array[i]));
			if(typeId == 5) {
				count = 0;
				break;
			}else {
				//�洢��Ʒid
				map.put("pId", array[i]);
				//�޸�
				count = ps.updateProductType(map);
			}
		}
		if(count > 0) {
			result.getSuccess("�¼ܳɹ�!");
		}else {
			result.getFail("��Ʒ�����ظ��¼�!");
		}
		PrintUtils.getJsonString(response, result);
	}
	/**
	 * �ϼ���Ʒ
	 * @param array
	 * @param response
	 */
	@RequestMapping(value="alterUp",method = RequestMethod.POST)
	public void alterUp(String[] array,HttpServletResponse response) {
		ReturnResult result = new ReturnResult();
		Map<String,Object> map = new HashMap<>();
		map.put("tId", "1");
		int count = 0;
		//ѭ������
		for(int i=0;i<array.length;i++) {
			//�жϸ���Ʒid�Ƿ��ظ��ϼ�
			int typeId = ps.getProductType(Integer.parseInt(array[i]));
			if(typeId == 1) {
				count = 0;
				break;
			}else {
				//�洢��Ʒid
				map.put("pId", array[i]);
				//�޸�
				count = ps.updateProductType(map);
			}
			
		}
		if(count > 0) {
			result.getSuccess("�ϼܳɹ�!��ȴ�����Ա���!");
		}else {
			result.getFail("��Ʒ�޷��ظ��ϼ�!");
		}
		PrintUtils.getJsonString(response, result);
	}
	/**
	 * ɾ����Ʒ
	 * @param array
	 * @param response
	 */
	@RequestMapping(value="deleteProduct",method = RequestMethod.POST)
	public void deleteProduct(String[] array,HttpServletResponse response) {
		ReturnResult result = new ReturnResult();
		int count = 0;
		//ѭ������
		for(int i=0;i<array.length;i++) {
			count = ps.deleteProduct(Integer.parseInt(array[i]));
		}
		if(count > 0) {
			result.getSuccess("ɾ���ɹ�!");
		}else {
			result.getFail("ϵͳ�����쳣!����ϵ����Ա!");
		}
		PrintUtils.getJsonString(response, result);
	}
	/**
	 * ��ʼ����
	 * @param pId
	 * @param pPrice
	 * @param response
	 */
	@RequestMapping(value="startAuction",method = RequestMethod.POST)
	public void startAuction(String pId,int countdown,HttpServletResponse response) {
		ReturnResult result = new ReturnResult();
		//�õ�������Ʒ
		List<Product> productList = ps.getAllProduct();
		Product pro = null;
		//ѭ���õ������ĵ���Ʒ
		for(Product pro1 : productList) {
			if(pro1.getpId() == Integer.parseInt(pId)) {
				pro = pro1;
			}
		}
		double givePrice = pro.getFirstPrice();  //���ļ�
		//��������û�
		List<User> userList = us.getAllUser();
		//������ǰʱ��
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//������¼�����
		Record record = null;
		//���۶���(��)
		double[] priceNum = {1,2,3};
		Random r = new Random(); //���������
		boolean flag = false; //��¼�Ƿ�����
		Map<String,Object> map = new HashMap<>();
		map.put("tId", "4");
		map.put("pId", pro.getpId());
		Map<String,Object> map2 = new HashMap<>();
		String name = ""; //���ĳɹ�������
		int num = r.nextInt(3); //�������
		int personNum = r.nextInt(userList.size()-1);
		User user = userList.get(personNum); //������۵��û�
		name = user.getuName();
		//ѭ��ģ����˾���
			//�ж�����ʱ���Ƿ����
			if(countdown == 0 && givePrice >= pro.getpPrice()) {
				//�޸ĵ�ǰ������Ʒ��״̬
				ps.updateProductType(map);
				//��ǰ�û����ĳɹ�,����һ������,�󶨵�ǰ�û�
				map2.put("uId",user.getuId());
				map2.put("pId", pro.getpId());
				map2.put("createDate", df.format(date));
				//���
				ps.insertOrder(map2);
				//��ӳɹ���¼
				record = new Record();
				givePrice = givePrice+priceNum[num];//���ۺ���ܽ��
				//�洢����¼������
				record.setpName(pro.getpName());
				record.setGiveDate(df.format(date));
				record.setOnePrice(givePrice);
				record.setuName(user.getuName());
				//�洢���ۼ�¼
				ps.insertRecord(record);
				flag = true; //���ĳɹ�
			}else {
				record = new Record();
				givePrice = givePrice+priceNum[num];//���ۺ���ܽ��
				//�洢����¼������
				record.setpName(pro.getpName());
				record.setGiveDate(df.format(date));
				record.setOnePrice(givePrice);
				record.setuName(user.getuName());
				//�洢���ۼ�¼
				ps.insertRecord(record);
			}
			//��������Ʒ��ֵ
			Map<String,Object> map3 = new HashMap<>();
			map3.put("price", givePrice);
			map3.put("pId", pro.getpId());
			ps.updatePrice(map3);
		//�ж�
		if(flag) {  //���ĳɹ�
			result.getSuccess("��ϲ�û�"+name+"�ɹ�����:"+pro.getpName()+"�۸�Ϊ:"+givePrice+"��!");
		}else { //20�γ���δ������  ����
			result.getFail("�û�:"+name+",����Ϊ:"+givePrice+"��Ԫ!");
		}
		PrintUtils.getJsonString(response, result);
	}
	/**
	 * �õ���ǰ�û����ж���
	 * @param model
	 * @return
	 */
	@RequestMapping("toOrder")
	public String getOrder(Model model,HttpServletRequest request) {
		//��õ�ǰ��¼�û�
		User user = (User)request.getSession().getAttribute("loginUser");
		//�õ����ж���
		List<Order> orderList = ps.getAllOrder(user.getuId());
		//�洢
		model.addAttribute("orderList",orderList);
		return "orderList";
	}
	/**
	 * �õ�����������¼
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
		//�õ�����������¼
		List<Record> recordList = ps.getAllRecord(map);
		
		model.addAttribute("pager",pager);
		model.addAttribute("recordList",recordList);
		return "recordList";
	}
}
