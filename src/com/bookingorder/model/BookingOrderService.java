package com.bookingorder.model;

import java.time.LocalDate;
import java.util.List;

import org.json.JSONObject;

public class BookingOrderService {
	private BookingOrderDAO_interface dao;
	
	public BookingOrderService() {
		dao = new BookingOrderDAO();
	}
	
	public BookingOrderVO addBkOd(String mb_id, LocalDate dateIn, LocalDate dateOut, Integer total_price, List<JSONObject> dateGroup, String card_no) {
		BookingOrderVO bkodvo = new BookingOrderVO();
		bkodvo.setMb_id(mb_id);
		bkodvo.setDateIn(dateIn);
		bkodvo.setDateOut(dateOut);
		bkodvo.setTotal_price(total_price);
		bkodvo.setCard_no(card_no);
		return dao.insert(bkodvo, dateGroup);
	}
	
	public void updateDateInOut(String bk_no, LocalDate dateIn, LocalDate dateOut) {
		BookingOrderVO bkodvo = new BookingOrderVO();
		bkodvo.setBk_no(bk_no);
		bkodvo.setDateIn(dateIn);
		bkodvo.setDateOut(dateOut);
		dao.update(bkodvo);
	}
	
	public void checkIn(String bk_no) {
		dao.updateCheckIn(bk_no);
	}
	
	public void checkOut(String bk_no) {
		dao.updateCheckOut(bk_no);
	}
	
	public void bkOdPaid(String bk_no) {
		dao.updateOrderPaid(bk_no);
	}
	
	public void cancelBooking(String bk_no) {
		dao.cancel(bk_no);
	}
	
	public List<BookingOrderVO> getAllBooking(){
		return dao.getAll();
	}
	
	public List<BookingOrderVO> getAllByBkStatus(String bk_status){
		return dao.getAllByBkStatus(bk_status);
	}
	
	public List<BookingOrderVO> getAllByDateIn(LocalDate dateIn){
		return dao.getAllByDateIn(dateIn);
	}
	
	public List<BookingOrderVO> getAllBeforeToday(LocalDate today){
		return dao.getAllBeforeToday(today);
	}
	
	public List<BookingOrderVO> getAllByDateOut(LocalDate dateOut){
		return dao.getAllByDateOut(dateOut);
	}
	
	public BookingOrderVO getOneByBkNo(String bk_no) {
		return dao.getOneByBkNo(bk_no);
	}
	
	public List<BookingOrderVO> getAllByMbId(String mb_id) {
		return dao.getAllByMbId(mb_id);
	}
}
