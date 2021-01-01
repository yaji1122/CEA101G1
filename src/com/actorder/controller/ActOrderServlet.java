package com.actorder.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.actorder.model.*;


public class ActOrderServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//�d��
				if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
						String actOdno = req.getParameter("actOdno");
						if (actOdno == null || (actOdno.trim()).length() == 0) {
							errorMsgs.add("�п�J�q��s��");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/backend/actorder/backend-order_select_page.jsp");
							failureView.forward(req, res);
							return;//�{�����_
						}
						
						
//						String act_odno = null;
//						try {
//							act_odno = new String(actOdno);
//						} catch (Exception e) {
//							errorMsgs.add("�s���榡�����T");
//						}
//						// Send the use back to the form, if there were errors
//						if (!errorMsgs.isEmpty()) {
//							RequestDispatcher failureView = req
//									.getRequestDispatcher("/back-end/actorder/act_order_select_page.jsp");
//							failureView.forward(req, res);
//							return;//�{�����_
//						}
						
						/***************************2.�}�l�d�߸��*****************************************/
						ActOrderService actOrderSvc = new ActOrderService();
						ActOrderVO actOrderVO =   actOrderSvc.getOneActOrder(actOdno);
						
						if (actOrderVO == null) {
							errorMsgs.add("�d�L���");
						}
						
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/backend/actorder/backend-order_select_page.jsp");
							failureView.forward(req, res);
							return;//�{�����_
						}
						
						/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
						req.setAttribute("actOrderVO", actOrderVO); // ��Ʈw���X��actTypeVO����,�s�Jreq
						String url = "/backend/actorder/backend-order_listOne.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneAct.jsp
						successView.forward(req, res);

						/***************************��L�i�઺���~�B�z*************************************/
					} catch (Exception e) {
						errorMsgs.add("�L�k���o���:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/actorder/backend-order_select_page.jsp");
						failureView.forward(req, res);
					}
					
				}
				
				//��s
				if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllAct.jsp���ШD

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					
					try {
						/***************************1.�����ШD�Ѽ�****************************************/
						String actOdno = new String(req.getParameter("actOdno"));
						
						/***************************2.�}�l�d�߸��****************************************/
						ActOrderService actOrderSvc = new ActOrderService();
						ActOrderVO actOrderVO = actOrderSvc.getOneActOrder(actOdno);
										
						/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
						req.setAttribute("actOrderVO", actOrderVO);         // ��Ʈw���X��empVO����,�s�Jreq
						String url = "/backend/actorder/backend-order_update.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
						successView.forward(req, res);

						/***************************��L�i�઺���~�B�z**********************************/
					} catch (Exception e) {
						errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/actorder/backend-order_listAll.jsp");
						failureView.forward(req, res);
					}
				}
				
		            if ("update".equals(action)) { // �Ӧ�update_act_type_input.jsp���ШD
					
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
				
					try {
						/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
						String actOdno = new String(req.getParameter("actOdno").trim());
						if(actOdno == null || actOdno.trim().length() == 0) {
							errorMsgs.add("�q��s��");
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
							RequestDispatcher failureView = req
									.getRequestDispatcher("/backend/actorder/backend-order_update.jsp");
							failureView.forward(req, res);
							return;//�{�����_
						}
						
						String mbId = req.getParameter("mbId").trim();
						if (mbId == null || mbId.trim().length() == 0) {
							errorMsgs.add("�нT�{���ʽs���榡�O�_���T");
						} 
						
						java.sql.Date odTime = null;
						try {
							odTime = java.sql.Date.valueOf(req.getParameter("odTime").trim());
						} catch (IllegalArgumentException e) {
							odTime = new java.sql.Date(System.currentTimeMillis());
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
								RequestDispatcher failureView = req
										.getRequestDispatcher("/backend/actorder/backend-order_update.jsp");
								failureView.forward(req, res);
								return;//�{�����_
							}
							
						
						ActOrderVO actOrderVO = new ActOrderVO();
						actOrderVO.setActOdno(actOdno);
						actOrderVO.setActNo(actNo);
						actOrderVO.setMbId(mbId);
						actOrderVO.setOdTime(odTime);
						actOrderVO.setOdStatus(odStatus);
						actOrderVO.setPpl(Numppl);
						actOrderVO.setTotalPrice(total_price);
						
						
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("actOrderVO", actOrderVO); // �t����J�榡���~��actTypeVO����,�]�s�Jreq
							RequestDispatcher failureView = req
									.getRequestDispatcher("/backend/actorder/backend-order_update.jsp");
							failureView.forward(req, res);
							return; //�{�����_
						}
						
						/***************************2.�}�l�ק���*****************************************/
						ActOrderService ActOrderSvc = new ActOrderService();
						actOrderVO = ActOrderSvc.updateActOrder(actOdno, actNo, mbId, odTime, odStatus, Numppl, total_price);
						
						/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
						req.setAttribute("actOrderVO", actOrderVO); // ��Ʈwupdate���\��,���T����actOrderVO����,�s�Jreq
						String url = "/backend/actorder/backend-order_listAll.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
						successView.forward(req, res);

						/***************************��L�i�઺���~�B�z*************************************/
					} catch (Exception e) {
						errorMsgs.add("�ק��ƥ���:"+e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/actorder/backend-order_update.jsp");
						failureView.forward(req, res);
					}
				}
		            
		          //�s�W
		            if ("insert".equals(action)) { // �Ӧ�addAct.jsp���ШD  
		        		
		        		List<String> errorMsgs = new LinkedList<String>();
		        		// Store this set in the request scope, in case we need to
		        		// send the ErrorPage view.
		        		req.setAttribute("errorMsgs", errorMsgs);

		        		try {
		        			/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
		        			String actOdno = new String(req.getParameter("actOdno").trim());
							if(actOdno == null || actOdno.trim().length() == 0) {
								errorMsgs.add("�q��s��");
							}else if(actOdno.equals("actOdno")) {
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
								RequestDispatcher failureView = req
										.getRequestDispatcher("/backend/actorder/backend-order_add.jsp");
								failureView.forward(req, res);
								return;//�{�����_
							}
							
							String mbId = req.getParameter("mbId").trim();
							if (mbId == null || mbId.trim().length() == 0) {
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
									RequestDispatcher failureView = req
											.getRequestDispatcher("/backend/actorder/backend-order_add.jsp");
									failureView.forward(req, res);
									return;//�{�����_
								}
								
							
							ActOrderVO actOrderVO = new ActOrderVO();
							actOrderVO.setActOdno(actOdno);
							actOrderVO.setActNo(actNo);
							actOrderVO.setMbId(mbId);
							actOrderVO.setOdTime(odtime);
							actOrderVO.setOdStatus(odStatus);
							actOrderVO.setPpl(Numppl);
							actOrderVO.setTotalPrice(total_price);

		        			// Send the use back to the form, if there were errors
		        			if (!errorMsgs.isEmpty()) {
		        				req.setAttribute("actOrderVO", actOrderVO); // �t����J�榡���~��actEventVO����,�]�s�Jreq
		        				RequestDispatcher failureView = req
		        						.getRequestDispatcher("/backend/actorder/backend-order_add.jsp");
		        				failureView.forward(req, res);
		        				return;
		        			}
		        			
		        			/***************************2.�}�l�s�W���***************************************/
		        			ActOrderService ActOrderSvc = new ActOrderService();
		        			actOrderVO = ActOrderSvc.addActOrder(actOdno, actNo, mbId, odtime, odStatus, Numppl, total_price);
		        			
		        			/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
		        			String url = "/backend/actorder/backend-order_listAll.jsp";
		        			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAll.jsp
		        			successView.forward(req, res);				
		        			
		        			/***************************��L�i�઺���~�B�z**********************************/
		        		} catch (Exception e) {
		        			errorMsgs.add("��J����Ƭ��ŭ�");
		        			e.printStackTrace();
		        			RequestDispatcher failureView = req
		        					.getRequestDispatcher("/backend/actorder/backend-order_add.jsp");
		        			failureView.forward(req, res);
		        		}
		        	} 
		
		}

}
