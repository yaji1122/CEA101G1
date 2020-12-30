package com.item.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
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
				String str = req.getParameter("item_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item/select_item.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String item_no = null;
				try {
					item_no = new String(str);
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
				ItemService itemSvc = new ItemService();
				ItemVO itemVO = itemSvc.getOneItem(item_no);
				if (itemVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/item/select_item.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("itemVO", itemVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/item/listOneItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/item/select_item.jsp");
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
				String item_no = new String(req.getParameter("item_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				ItemService itemSvc = new ItemService();
				ItemVO itemVO = itemSvc.getOneItem(item_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("itemVO", itemVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/item/update_item_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/item/listAllItem.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getItemByItem_type_no_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("item_type_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品類別");
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
					errorMsgs.add("商品類別編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/item/select_item.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ItemService itemSvc = new ItemService();
				
				List<ItemVO> list = itemSvc.getAllByItem_type_no(item_type_no);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/item/select_item.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/backend/item/listAllByItem_type_no.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/item/select_item.jsp");
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
				String item_no = req.getParameter("item_no").trim();
							
				String item_name = req.getParameter("item_name");
				if (item_name == null || item_name.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} 

				String item_type_no = null;
				try {
					item_type_no = req.getParameter("item_type_no").trim();
				} catch (NumberFormatException e) {
					item_type_no = null;
					errorMsgs.add("請填商品類別");
				}

				Double item_price = null;
				try {
					item_price = new Double(req.getParameter("item_price").trim());
				} catch (NumberFormatException e) {
					item_price = 1000.00;
					errorMsgs.add("請填商品價格");
				}

				String item_status = null;
				try {
					item_status = req.getParameter("item_status").trim();
				} catch (NumberFormatException e) {
					item_status = null;
					errorMsgs.add("請選擇商品狀態");
				}

				String item_detail = req.getParameter("item_detail");
				if (item_detail == null || item_detail.trim().length() == 0) {
					errorMsgs.add("請填商品詳情");
				}

				String item_on_sale = null;
				try {
					item_on_sale = req.getParameter("item_on_sale").trim();
				} catch (NumberFormatException e) {
					item_on_sale = null;
					errorMsgs.add("商品促銷請填數字");
				}
				
				Integer points = null;
				try {
					points = new Integer(req.getParameter("points").trim());
				} catch (NumberFormatException e) {
					points = 0;
					errorMsgs.add("請輸入商品積分或輸入0");
				}
				ItemVO itemVO = new ItemVO();
				itemVO.setItem_no(item_no);
				itemVO.setItem_name(item_name);
				itemVO.setItem_type_no(item_type_no);
				itemVO.setItem_price(item_price);
				itemVO.setItem_status(item_status);
				itemVO.setItem_on_sale(item_on_sale);
				itemVO.setItem_detail(item_detail);
				itemVO.setPoints(points);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemVO", itemVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/item/update_item_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ItemService itemSvc = new ItemService();
				itemVO = itemSvc.updateItem(item_name, item_type_no, item_price, item_status, item_on_sale, item_detail,
						points, item_no);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("itemVO", itemVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/item/itemInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/item/update_item_input.jsp");
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
				String item_name = req.getParameter("item_name");
				if (item_name == null || item_name.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} 

				String item_type_no = null;
				try {
					item_type_no = req.getParameter("item_type_no").trim();
				} catch (NumberFormatException e) {
					item_type_no = null;
					errorMsgs.add("商品類別請填數字");
				}

				Double item_price = null;
				try {
					item_price = new Double(req.getParameter("item_price").trim());
				} catch (NumberFormatException e) {
					item_price = 1000.0;
					errorMsgs.add("請填商品價格");
				}

				String item_status = null;
				try {
					item_status = req.getParameter("item_status").trim();
				} catch (NumberFormatException e) {
					item_status = null;
					errorMsgs.add("請填商品狀態");
				}

				String item_detail = req.getParameter("item_detail");
				if (item_detail == null || item_detail.trim().length() == 0) {
					errorMsgs.add("商品詳情");
				}

				String item_on_sale = null;
				try {
					item_on_sale = req.getParameter("item_on_sale").trim();
				} catch (NumberFormatException e) {
					item_on_sale = null;
					errorMsgs.add("請選擇商品促銷狀態");
				}
				
				
				Integer points = null;
				try {
					points = new Integer(req.getParameter("points").trim());
				} catch (NumberFormatException e) {
					points = 0;
					errorMsgs.add("積分請填數字");
				}
				
				ItemVO itemVO = new ItemVO();
				itemVO.setItem_name(item_name);
				itemVO.setItem_type_no(item_type_no);
				itemVO.setItem_price(item_price);
				itemVO.setItem_status(item_status);
				itemVO.setItem_on_sale(item_on_sale);
				itemVO.setItem_detail(item_detail);
				itemVO.setPoints(points);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemVO", itemVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/item/addItem.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ItemService itemSvc = new ItemService();
				itemVO = itemSvc.addItem(item_name, item_type_no, item_price, item_status, item_on_sale, item_detail,
						points);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/backend/item/itemInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/item/addItem.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
