package com.item_type.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.item.model.ItemService;
import com.item.model.ItemVO;
import com.item_type.model.Item_typeService;
import com.item_type.model.Item_typeVO;

public class Item_typeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Item_typeServlet() {
        super();
     
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("item_type_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/item/select_item.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String item_type_no = null;
				try {
					item_type_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/item/select_item.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Item_typeService item_typeSvc = new Item_typeService();
				Item_typeVO item_typeVO = item_typeSvc.getOneItem_type(item_type_no);
				if (item_typeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/item/select_item.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("item_typeVO", item_typeVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/item_type/listOneItem_type.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/item/select_item.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String type_name = req.getParameter("type_name");
				if (type_name == null || type_name.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} 

				String item_type_no = req.getParameter("item_type_no").trim();
				String item_type_noReg = "^[(0-9)] {2,2}$";
				if (item_type_no == null || item_type_no.trim().length() == 0) {
					errorMsgs.add("商品類別編號: 請勿空白");
				} 
//				else if(item_type_no == ) {
//					errorMsgs.add("商品類別編號:已存在的類別編號不可新增");
//				}
				else if(!item_type_no.trim().matches(item_type_noReg)) {
					errorMsgs.add("商品類別編號:請檢察是否為兩位字數字");
				}
				
				Item_typeVO item_typeVO = new Item_typeVO();
				item_typeVO.setItem_type_no(item_type_no);
				item_typeVO.setType_name(type_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("item_typeVO", item_typeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/item_type/addItem_type.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Item_typeService item_typeSvc = new Item_typeService();
				item_typeVO = item_typeSvc.updateItem_type(item_type_no, type_name);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/backend/item/itemInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/item_type/addItem_type.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String item_type_no = req.getParameter("item_type_no").trim();
					System.out.println(item_type_no);		
				String type_name = req.getParameter("type_name");
				if (type_name == null || type_name.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} 

				Item_typeVO item_typeVO = new Item_typeVO();
				item_typeVO.setItem_type_no(item_type_no);
				item_typeVO.setType_name(type_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("item_typeVO", item_typeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/item_type/update_item_type_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Item_typeService item_typeSvc = new Item_typeService();
				item_typeVO = item_typeSvc.updateItem_type(type_name, item_type_no);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("item_typeVO", item_typeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/item/itemInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/item_type/update_item_type_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String item_type_no = new String(req.getParameter("item_type_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				Item_typeService item_typeSvc = new Item_typeService();
				Item_typeVO item_typeVO = item_typeSvc.getOneItem_type(item_type_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("item_typeVO", item_typeVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/item_type/update_item_type_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/item_type/listAllItem_type.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
