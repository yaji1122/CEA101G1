package com.bookingorder.model;

import java.util.List;
import org.json.JSONObject;
import java.time.LocalDate;

public interface BookingOrderDAO_interface {
	public BookingOrderVO insert(BookingOrderVO bkodvo, List<JSONObject> bkList);
	public void cancel(String bk_no);
	public void update(BookingOrderVO bkodvo);
	public void updateOrderPaid(String bk_no);
	public void updateCheckIn(String bk_no);
	public void updateCheckOut(String bk_no);
	public List<BookingOrderVO> getAll();
	public List<BookingOrderVO> getAllByMbId(String mb_id);
	public List<BookingOrderVO> getAllByBkStatus(String bk_status);
	public List<BookingOrderVO> getAllByDateIn(LocalDate date_in);
	public List<BookingOrderVO> getAllByDateOut(LocalDate date_out);
	public BookingOrderVO getOneByBkNo(String bk_no);
	
}
