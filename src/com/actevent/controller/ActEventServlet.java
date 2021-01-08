package com.actevent.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.actevent.model.*;


public class ActEventServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

     
	
	//�涵�d��
	if ("getOne_For_Display".equals(action)) { // �Ӧ�act_event_select_page.jsp���ШD

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
			String str = req.getParameter("actEventNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("�п�ܽs���ζ��ئW��");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/actevent/act_event_select_page.jsp");
				failureView.forward(req, res);
				return;//�{�����_
			}
			
			String actEventNo = null;
			try {
				actEventNo = new String(str);
			} catch (Exception e) {
				errorMsgs.add("�s���榡�����T");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/actevent/act_event_select_page.jsp");
				failureView.forward(req, res);
				return;//�{�����_
			}
			
			/***************************2.�}�l�d�߸��*****************************************/
			ActEventService actEventSvc = new ActEventService();
			ActEventVO actEventVO = actEventSvc.getOneActEvent(actEventNo);
			if (actEventVO == null) {
				errorMsgs.add("�d�L���");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/actevent/act_event_select_page.jsp");
				failureView.forward(req, res);
				return;//�{�����_
			}
			
			/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
			req.setAttribute("actEventVO", actEventVO); // ��Ʈw���X��actEventVO����,�s�Jreq
			String url = "/backend/actevent/act_event_listOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneAct.jsp
			successView.forward(req, res);

			/***************************��L�i�઺���~�B�z*************************************/
		} catch (Exception e) {
			errorMsgs.add("�L�k���o���:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/backend/actevent/act_event_select_page.jsp");
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
			String actEventNo = req.getParameter("actEventNo").trim();
			if (actEventNo == null || actEventNo.trim().length() == 0) {
				errorMsgs.add("�ж�g�s���ʶ��ؽs��");
			} 
			
			String actEventName = req.getParameter("actEventName");
			if (actEventName == null || actEventName.trim().length() == 0) {
				errorMsgs.add("���ʶ��ئW��: �ФŪť�");
			}
			
			
			ActEventVO actEventVO = new ActEventVO();
			actEventVO.setActEventNo(actEventNo);
			actEventVO.setActEventName(actEventName);

		

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("actEventVO", actEventVO); // �t����J�榡���~��actEventVO����,�]�s�Jreq
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/actevent/act_event_add.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.�}�l�s�W���***************************************/
			ActEventService ActEventSvc = new ActEventService();
			actEventVO = ActEventSvc.addActEvent(actEventNo, actEventName);
			
			/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
			String url = "/backend/actevent/act_event_listAll.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);				
			
			/***************************��L�i�઺���~�B�z**********************************/
		} catch (Exception e) {
			errorMsgs.add("��J����Ƭ��ŭ�");
			e.printStackTrace();
			RequestDispatcher failureView = req
					.getRequestDispatcher("/backend/actevent/act_event_add.jsp");
			failureView.forward(req, res);
		}
	} 
    
    //�R��
    if ("delete".equals(action)) { // �Ӧ�listAllAct.jsp

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.�����ШD�Ѽ�***************************************/
			String actEventNo = req.getParameter("actEventNo");
			
			/***************************2.�}�l�R�����***************************************/
			ActEventService ActEventSvc = new ActEventService();
			ActEventSvc.deleteActEvent(actEventNo);
			
			/***************************3.�R������,�ǳ����(Send the Success view)***********/								
			String url = "/backend/actevent/act_event_listAll.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
			successView.forward(req, res);
			
			/***************************��L�i�઺���~�B�z**********************************/
		} catch (Exception e) {
			errorMsgs.add("�R����ƥ���:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/backend/actevent/act_event_listAll.jsp");
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
  				String actEventNo = req.getParameter("actEventNo");
  				
  				/***************************2.�}�l�d�߸��****************************************/
  				ActEventService actEventSvc = new ActEventService();
  				ActEventVO actEventVO = actEventSvc.getOneActEvent(actEventNo);
  								
  				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
  				req.setAttribute("actEventVO", actEventVO);         // ��Ʈw���X��empVO����,�s�Jreq
  				String url = "//backend/actevent/act_event_input_update.jsp";
  				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
  				successView.forward(req, res);

  				/***************************��L�i�઺���~�B�z**********************************/
  			} catch (Exception e) {
  				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
  				RequestDispatcher failureView = req
  						.getRequestDispatcher("/backend/actevent/act_event_listAllAct.jsp");
  				failureView.forward(req, res);
  			}
  		}
  		
  		//��s
        if ("update".equals(action)) { // �Ӧ�act_event_update_input.jsp���ШD
  			
  			List<String> errorMsgs = new LinkedList<String>();
  			// Store this set in the request scope, in case we need to
  			// send the ErrorPage view.
  			req.setAttribute("errorMsgs", errorMsgs);
  		
  			try {
  				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
  				String actEventNo = new String(req.getParameter("actEventNo").trim());
  				if(actEventNo == null || actEventNo.trim().length() == 0) {
  					errorMsgs.add("�п�J�s���ʶ��ؽs��");
  				}

  				
  				String actEventName = req.getParameter("actEventName");
				String actNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (actEventName == null || actEventName.trim().length() == 0) {
					errorMsgs.add("���ʶ��ئW��: �ФŪť�");
				} else if(!actEventName.trim().matches(actNameReg)) {
					errorMsgs.add("�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				
			
				ActEventVO actEventVO = new ActEventVO();
				actEventVO.setActEventNo(actEventNo);
				actEventVO.setActEventName(actEventName);

  				
  				
  				// Send the use back to the form, if there were errors
  				if (!errorMsgs.isEmpty()) {
  					req.setAttribute("actEventVO", actEventVO); // �t����J�榡���~��actTypeVO����,�]�s�Jreq
  					RequestDispatcher failureView = req
  							.getRequestDispatcher("/backend/actevent/act_event_input_update.jsp");
  					failureView.forward(req, res);
  					return; //�{�����_
  				}
  				
  				/***************************2.�}�l�ק���*****************************************/
  				ActEventService ActEventSvc = new ActEventService();
  				actEventVO = ActEventSvc.updateActEvent(actEventNo,actEventName);
  				
  				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
  				req.setAttribute("actEventVO", actEventVO); // ��Ʈwupdate���\��,���T����actTypeVO����,�s�Jreq
  				String url = "/backend/actevent/act_event_listAll.jsp";
  				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
  				successView.forward(req, res);

  				/***************************��L�i�઺���~�B�z*************************************/
  			} catch (Exception e) {
  				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
  				RequestDispatcher failureView = req
  						.getRequestDispatcher("/backend/actevent/act_event_input_update.jsp");
  				failureView.forward(req, res);
  			}
  		}
    
    
  }
}
