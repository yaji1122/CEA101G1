package com.bookingorder.model;

import java.util.List;
import java.sql.Date;

public interface BookingOrderDAO_interface {
	public BookingOrderVO insert(BookingOrderVO bkodvo);
	public void update(BookingOrderVO bkodvo);
	public void updateOrderPaid(String bk_no);
	public void updateCheckIn(String bk_no);
	public void updateCheckOut(String bk_no);
	public List<BookingOrderVO> getAll();
	public List<BookingOrderVO> getAllByBkStatus(String bk_status);
	public List<BookingOrderVO> getAllByDateIn(Date date_in);
	public BookingOrderVO getOneByBkNo(String bk_no);
	public BookingOrderVO getOneByMbId(String mb_id);
}
