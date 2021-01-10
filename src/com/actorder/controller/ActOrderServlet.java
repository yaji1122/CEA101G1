package com.actorder.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.act.model.ActService;
import com.act.model.ActVO;
import com.actorder.model.*;

@MultipartConfig
public class ActOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {
			try {
				String actOdno = req.getParameter("actOdno");
				ActOrderService actOrderSvc = new ActOrderService();
				ActOrderVO actOrderVO = actOrderSvc.getOneActOrder(actOdno);
				List<ActOrderVO> actOrderList = new ArrayList<>();
				actOrderList.add(actOrderVO);
				req.setAttribute("actOrderList", actOrderList);
				String url = "/backend/actorder/actorderInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("getOne_For_Update".equals(action)) {
			try {
				String actOdno = req.getParameter("actOdno");
				ActOrderService actOrderSvc = new ActOrderService();
				ActOrderVO actOrderVO = actOrderSvc.getOneActOrder(actOdno);
				req.setAttribute("actOrderVO", actOrderVO);
				String url = "/backend/actorder/act_order_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("update".equals(action)) {
			res.setContentType("utf-8");
			PrintWriter out = res.getWriter();
			try {
				String actOdno = new String(req.getParameter("actOdno").trim());
				String odStatus = req.getParameter("order_status");
				String pplstr = req.getParameter("ppl").trim();
				Integer ppl = null;
				try {
					ppl = new Integer(pplstr);
				} catch (Exception e) {
					out.print("請輸入數字");
					return;
				}
				String actNo = req.getParameter("actNo");
				ActService actSvc = new ActService();
				ActVO act = actSvc.getOneAct(actNo);
				Integer total_price = ppl * act.getActPrice();
				ActOrderService actOrderSvc = new ActOrderService();
				actOrderSvc.updateActOrder(actOdno, odStatus, ppl, total_price);
				out.print("success");
			} catch (Exception e) {
				e.printStackTrace();
				out.print("fail");
			}
		}

		if ("getAllByOdStatus".equals(action)) {
			try {
				String odStatus = req.getParameter("odstatus");
				ActOrderService actOrderSvc = new ActOrderService();
				List<ActOrderVO> actOrderList = actOrderSvc.getAllByStatus(odStatus);
				req.setAttribute("actOrderList", actOrderList);
				String url = "/backend/actorder/actorderInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addAct.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 *************************/
				String actOdno = new String(req.getParameter("actOdno").trim());
				if (actOdno == null || actOdno.trim().length() == 0) {
					errorMsgs.add("�q��s��");
				} else if (actOdno.equals("actOdno")) {
					errorMsgs.add("�w���ۦP�q��s��");
				}

				String odStatus = req.getParameter("odStatus");
				if (odStatus == null || odStatus.trim().length() == 0) {
					errorMsgs.add("�q�檬�A");
				}

				String actNo = req.getParameter("actNo");
				if (actNo == null || actNo.trim().length() == 0) {
					errorMsgs.add("���ʽs��: �ФŪť�");
				}

				String ppl = req.getParameter("ppl");
				if (ppl == null || ppl.trim().length() == 0) {
					errorMsgs.add("�ѥ[�H��: �ФŪť�");
				}
				Integer Numppl = null;
				try {
					Numppl = new Integer(ppl);
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/actorder/backend-order_add.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String bkNo = req.getParameter("bkNo").trim();
				if (bkNo == null || bkNo.trim().length() == 0) {
					errorMsgs.add("�нT�{���ʽs���榡�O�_���T");
				}

				java.sql.Date odtime = null;
				try {
					odtime = java.sql.Date.valueOf(req.getParameter("odTime").trim());
				} catch (IllegalArgumentException e) {
					odtime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				String totalPrice = req.getParameter("totalPrice");
				if (totalPrice == null || totalPrice.trim().length() == 0) {
					errorMsgs.add("�ѥ[�H��: �ФŪť�");
				}
				Integer total_price = null;
				try {
					total_price = new Integer(totalPrice);
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/actorder/backend-order_add.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				ActOrderVO actOrderVO = new ActOrderVO();
				actOrderVO.setActOdno(actOdno);
				actOrderVO.setActNo(actNo);
				actOrderVO.setBkNo(bkNo);
				actOrderVO.setOdTime(odtime);
				actOrderVO.setOdStatus(odStatus);
				actOrderVO.setPpl(Numppl);
				actOrderVO.setTotalPrice(total_price);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actOrderVO", actOrderVO); // �t����J�榡���~��actEventVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/actorder/backend-order_add.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************
				 * 2.�}�l�s�W���
				 ***************************************/
				ActOrderService ActOrderSvc = new ActOrderService();
				actOrderVO = ActOrderSvc.addActOrder(actOdno, actNo, bkNo, odtime, odStatus, Numppl, total_price);

				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				String url = "/backend/actorder/backend-order_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAll.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("��J����Ƭ��ŭ�");
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/actorder/backend-order_add.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
