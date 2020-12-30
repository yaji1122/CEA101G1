package com.bookingdetail.model;

import java.util.List;

public class BookingDetailService {
	private BookingDetailDAO_interface dao;
	
	public BookingDetailService() {
		dao = new BookingDetailDAO();
	}
	
	public BookingDetailVO addBkDetail(String bk_no, String rm_type, Integer rm_price, Integer qty) {
		BookingDetailVO bkdetailvo = new BookingDetailVO();
		bkdetailvo.setBk_no(bk_no);
		bkdetailvo.setRm_type(rm_type);
		bkdetailvo.setRm_price(rm_price);
		bkdetailvo.setQty(qty);
		dao.insert(bkdetailvo);
		return bkdetailvo;
	}
	
	public BookingDetailVO updateBkDetail(String bk_no, String rm_type, Integer qty) {
		BookingDetailVO bkdetailvo = new BookingDetailVO();
		bkdetailvo.setBk_no(bk_no);
		bkdetailvo.setRm_type(rm_type);
		bkdetailvo.setQty(qty);
		dao.update(bkdetailvo);
		return bkdetailvo;
	}
	
	public List<BookingDetailVO> getAllByBkNo(String bk_no) {
		return dao.getAllByBkNo(bk_no);
	}
}
