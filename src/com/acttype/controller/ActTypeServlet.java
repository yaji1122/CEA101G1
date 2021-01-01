package com.acttype.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.acttype.model.*;

public class ActTypeServlet extends HttpServlet{
	
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
				String str = req.getParameter("actTypeNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�ܬ��ʦW��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/acttype/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String actTypeno = null;
				try {
					actTypeno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/acttype/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				ActTypeService actTypeSvc = new ActTypeService();
				ActTypeVO actTypeVO =  actTypeSvc.getOneActType(actTypeno);
				
				if (actTypeVO == null) {
					errorMsgs.add("�d�L���");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/acttype/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("actTypeVO", actTypeVO); // ��Ʈw���X��actTypeVO����,�s�Jreq
				String url = "/backend/acttype/listOneAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneAct.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/acttype/select_page.jsp");
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
				String actTypeno = new String(req.getParameter("ActTypeNo"));
				
				/***************************2.�}�l�d�߸��****************************************/
				ActTypeService acttypeSvc = new ActTypeService();
				ActTypeVO actTypeVO = acttypeSvc.getOneActType(actTypeno);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("actTypeVO", actTypeVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/backend/acttype/update_act_type_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/acttype/listAllAct.jsp");
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
				String actTypeno = new String(req.getParameter("ActTypeNo").trim());
				if(actTypeno == null || actTypeno.trim().length() == 0) {
					errorMsgs.add("�п�J�����s��");
				}
				
				String actTypeName = req.getParameter("ActTypeName");
//				String actNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (actTypeName == null || actTypeName.trim().length() == 0) {
					errorMsgs.add("���������W��: �ФŪť�");
				} 
				
				ActTypeVO actTypeVO = new ActTypeVO();
				actTypeVO.setActTypeNo(actTypeno);
				actTypeVO.setActTypeName(actTypeName);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actTypeVO", actTypeVO); // �t����J�榡���~��actTypeVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/acttype/update_act_type_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				ActTypeService ActTypeSvc = new ActTypeService();
				actTypeVO = ActTypeSvc.updateActType(actTypeno,actTypeName);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("actTypeVO", actTypeVO); // ��Ʈwupdate���\��,���T����actTypeVO����,�s�Jreq
				String url = "/backend/acttype/listAllAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/update_act_type_input.jsp");
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
//    				String actTypeno = req.getParameter("ActTypeNo").trim();
//    				if (actTypeno == null || actTypeno.trim().length() == 0) {
//    					errorMsgs.add("�ж�g�s���ʺ������s��");
//    				} 
    				
    				String actTypeName = req.getParameter("ActTypeName");
    				if (actTypeName == null || actTypeName.trim().length() == 0) {
    					errorMsgs.add("���������W��: �ФŪť�");
    				}
    				
    				String actTypeno = new String(req.getParameter("ActTypeNo").trim());

    				ActTypeVO actTypeVO = new ActTypeVO();
    				actTypeVO.setActTypeNo(actTypeno);
    				actTypeVO.setActTypeName(actTypeName);
    			

    				// Send the use back to the form, if there were errors
    				if (!errorMsgs.isEmpty()) {
    					req.setAttribute("actTypeVO", actTypeVO); // �t����J�榡���~��actTypeVO����,�]�s�Jreq
    					RequestDispatcher failureView = req
    							.getRequestDispatcher("/backend/acttype/addAct.jsp");
    					failureView.forward(req, res);
    					return;
    				}
    				
    				/***************************2.�}�l�s�W���***************************************/
    				ActTypeService ActTypeSvc = new ActTypeService();
    				actTypeVO = ActTypeSvc.addActType(actTypeno, actTypeName);
    				
    				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
    				String url = "/backend/acttype/listAllAct.jsp";
    				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
    				successView.forward(req, res);				
    				
    				/***************************��L�i�઺���~�B�z**********************************/
    			} catch (Exception e) {
    				errorMsgs.add("��J����Ƭ��ŭ�");
    				e.printStackTrace();
    				RequestDispatcher failureView = req
    						.getRequestDispatcher("/backend/acttype/addAct.jsp");
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
    				String actTypeno = req.getParameter("ActTypeno");
    				
    				/***************************2.�}�l�R�����***************************************/
    				ActTypeService ActTypeSvc = new ActTypeService();
    				ActTypeSvc.deleteActType(actTypeno);
    				
    				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
    				String url = "/backend/acttype/listAllAct.jsp";
    				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
    				successView.forward(req, res);
    				
    				/***************************��L�i�઺���~�B�z**********************************/
    			} catch (Exception e) {
    				errorMsgs.add("�R����ƥ���:"+e.getMessage());
    				RequestDispatcher failureView = req
    						.getRequestDispatcher("/backend/acttype/listAllAct.jsp");
    				failureView.forward(req, res);
    			}
    		}
            
            
	}
}
