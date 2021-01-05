package com.bookingdetail.model;

import java.sql.Connection;
import java.util.List;
import org.json.JSONObject;
import com.bookingorder.model.BookingOrderVO;

public interface BookingDetailDAO_interface {
	public void insert(BookingOrderVO bkodvo, JSONObject bkitem, Connection conn);
	public void update(BookingDetailVO bkdetailvo);
	public List<BookingDetailVO> getAllByBkNo(String bk_no);
	public List<BookingDetailVO> getAll();
}
