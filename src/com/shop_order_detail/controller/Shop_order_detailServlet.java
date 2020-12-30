package com.shop_order_detail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.item.model.ItemService;
import com.item.model.ItemVO;
import com.shop_order_detail.model.Shop_order_detailService;
import com.shop_order_detail.model.Shop_order_detailVO;

@WebServlet("/Shop_order_detailServlet")
public class Shop_order_detailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Shop_order_detailServlet() {
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String item_no = new String(req.getParameter("item_no"));
				String sp_odno = new String(req.getParameter("sp_odno"));

				/*************************** 2.開始查詢資料 ****************************************/
				Shop_order_detailService shop_order_detailSvc = new Shop_order_detailService();
				Shop_order_detailVO shop_order_detailVO = shop_order_detailSvc. getOneShop_order_detail(sp_odno, item_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("shop_order_detailVO", shop_order_detailVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/shop_order_detail/update_shop_order_detail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/shop_order_detail/listAllDetail.jsp");
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
						
				String sp_odno = req.getParameter("sp_odno").trim();
				
				Integer qty = null;
				try {
					qty = new Integer(req.getParameter("qty").trim());
				} catch (NumberFormatException e) {
					
					errorMsgs.add("請填入商品訂單數量為數字");
				}
							
				Shop_order_detailVO shop_order_detailVO = new Shop_order_detailVO();
				shop_order_detailVO.setItem_no(item_no);
				shop_order_detailVO.setSp_odno(sp_odno);
				shop_order_detailVO.setQty(qty);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("shop_order_detailVO", shop_order_detailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/shop_order_detail/update_shop_order_detail_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Shop_order_detailService shop_order_detailSvc = new Shop_order_detailService();
				shop_order_detailVO = shop_order_detailSvc.updateShop_order_detail(qty, sp_odno, item_no);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("shop_order_detailVO", shop_order_detailVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/shop_order_detail/listAllDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/item/update_item_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
//		if ("getDetailBySp_odno_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				String str = req.getParameter("sp_odno");
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item/select_item.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//
//				String sp_odno = null;
//				try {
//					sp_odno = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("商品類別編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item/select_item.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//
//				/*************************** 2.開始查詢資料 *****************************************/
//				Shop_order_detailService shop_order_detailSvc = new Shop_order_detailService();
//				
//				Set<Shop_order_detailVO> set = shop_order_detailSvc.getShop_order_detailBySp_odno(sp_odno);
//				if (set == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item/select_item.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("set", set); // 資料庫取出的empVO物件,存入req
//				String url = "/back-end/item/listAllByDetail.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/item/select_item.jsp");
//				failureView.forward(req, res);
//			}
//		}

		
	}

}
