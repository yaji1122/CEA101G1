package com.bookingdetail.model;

import java.util.List;

public interface BookingDetailDAO_interface {
	public void insert(BookingDetailVO bkdetailvo);
	public void update(BookingDetailVO bkdetailvo);
	public List<BookingDetailVO> getAllByBkNo(String bk_no);
}
