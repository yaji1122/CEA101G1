package com.payment.model;

import java.util.List;

public interface PaymentDAO_interface {
	public PaymentVO insert(PaymentVO payvo); 
	public void delete(String pay_no);
	public List<PaymentVO> getAll();
	public List<PaymentVO> getAllByMbId(String mb_id);
}
