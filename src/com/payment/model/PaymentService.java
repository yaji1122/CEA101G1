package com.payment.model;

import java.util.List;

public class PaymentService {
	private PaymentDAO_interface dao;
	
	public PaymentService() {
		dao = new PaymentDAO();
	}
	
	public PaymentVO insertCrdt(String mb_id, String card_no, String card_name, String exp_mon, String exp_year, String csc) {
		PaymentVO payvo = new PaymentVO();
		payvo.setMb_id(mb_id);
		payvo.setCard_no(card_no);
		payvo.setCard_name(card_name);
		payvo.setExp_mon(exp_mon);
		payvo.setExp_year(exp_year);
		payvo.setCsc(csc);
		return dao.insert(payvo);
	}
	
	public void deleteCrdt(String pay_no) {
		dao.delete(pay_no);
	}
	
	public List<PaymentVO> getAll(){
		return dao.getAll();
	}
	
	public List<PaymentVO> getAllByMbId(String mb_id){
		return dao.getAllByMbId(mb_id);
	}
}
