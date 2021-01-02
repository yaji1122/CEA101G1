package com.bookingdetail.model;

import java.sql.Connection;
import java.util.List;

import org.json.JSONObject;

import com.bookingorder.model.BookingOrderVO;

public class BookingDetailService {
	private BookingDetailDAO_interface dao;
	
	public BookingDetailService() {
		dao = new BookingDetailDAO();
	}
	
	public BookingDetailVO addBkDetail(BookingOrderVO bkodvo, JSONObject bkitem, Connection conn) {
		BookingDetailVO bkdetailvo = new BookingDetailVO();
		dao.insert(bkodvo, bkitem, conn);
		return bkdetailvo;
	}  
	
	public List<BookingDetailVO> getAllByBkNo(String bk_no) {
		return dao.getAllByBkNo(bk_no);
	}
}
