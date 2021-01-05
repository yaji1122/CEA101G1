package com.item_pics.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.item_pics.model.Item_picsService;
import com.item_pics.model.Item_picsVO;


@MultipartConfig
public class Item_picsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
    public Item_picsServlet() {
        super();
    }
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
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("item_pic_no");
				
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入照片編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/item/select_item.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String item_pic_no = null;
				try {
					item_pic_no = str;
				} catch (Exception e) {
					errorMsgs.add("照片編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/item_pics/listAllByItem_no.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Item_picsService item_picsSvc = new Item_picsService();
				Item_picsVO item_picsVO = item_picsSvc.getOnePic(item_pic_no);
				
				
				if (item_picsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("//backend/item_pics/listAllByItem_no.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("item_picsVO", item_picsVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/item_pics/listAllByItem_no.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/item_pics/listAllByItem_no.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String item_pic_no = req.getParameter("item_pic_no");
				
				/***************************2.開始查詢資料****************************************/
				Item_picsService item_picsSvc = new Item_picsService();
				Item_picsVO item_picsVO = item_picsSvc.getOnePic(item_pic_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("item_picsVO", item_picsVO);         // 資料庫取出的empVO物件,存入req
				String url = "/backend/item_pics/update_item_pic_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/item/listAllItem.jsp");
				failureView.forward(req, res);
			}
		}
		

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

				String item_no = req.getParameter("item_no").trim();
				if (item_no == null || item_no.trim().length() == 0) {
					errorMsgs.add("商品編號請勿空白");
				}	
				
				Part part = req.getPart("item_pic");
//				try {
//					part =  req.getPart("item_pic");
//					InputStream in = part.getInputStream();
//					byte[] buf = new byte[in.available()];
//					in.read(buf);
//					in.close();
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//					part = null;
//					errorMsgs.add("請輸入商品圖片");
//				}

				
//				item_picsVO.setItem_no(item_no);
//				item_picsVO.setItem_pic(buf);

				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("item_picsVO", item_picsVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/item_pics/update_item_pic_input.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				
				/***************************2.開始新增資料***************************************/
				Item_picsService item_picsSvc = new Item_picsService();
				Item_picsVO item_picsVO = item_picsSvc.addPics(item_no, part);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/item_pics/listAllByItem_no.jsp?=" + item_no;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/item/select_item.jsp");
				failureView.forward(req, res);
			}
		}
        if ("getOne_Pic_Display".equals(action)) {
        	
        	req.setCharacterEncoding("UTF-8");
    		res.setContentType("image/gif, image/jpg, image/jpeg, image/png");
    			InputStream ispu = null;
    			String item_pic_no = req.getParameter("item_pic_no").trim();
    			
    			Item_picsService is = new Item_picsService();
    			Item_picsVO isvo = is.getOnePic(item_pic_no);
    			if(isvo.getItem_pic()!=null) {
    			res.getOutputStream().write(isvo.getItem_pic());
    			} else {
    				ispu = req.getServletContext().getResourceAsStream("/img/nodata.png");
    				byte[] pic = new byte[ispu.available()];
    				ispu.read(pic);
    				res.getOutputStream().write(pic);
    				ispu.close();
    			}
        }
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				String item_pic_no = req.getParameter("item_pic_no");
				String item_no = req.getParameter("item_no");
				System.out.print(item_no);
				/***************************2.開始刪除資料***************************************/
				Item_picsService item_picsSvc = new Item_picsService();
				item_picsSvc.deletePic(item_pic_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/backend/item_pics/listAllByItem_no.jsp?item_no="+item_no;
				req.setAttribute("item_no", item_no);
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/item_pics/listAllByItem_no.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
