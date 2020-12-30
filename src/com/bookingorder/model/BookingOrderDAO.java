package com.bookingorder.model;

import java.util.LinkedList;
import java.util.List;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class BookingOrderDAO implements BookingOrderDAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		} catch (NamingException e) {
			e.printStackTrace(System.err);
		}
	}

	private static final String INSERT = "INSERT INTO BOOKING_ORDER (BK_NO, MB_ID, DATEIN, DATEOUT, TOTAL_PRICE) VALUES ('BKOD' || LPAD(to_char(BK_SEQ.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?)";
	private static final String UPDATEDATEINOUT = "UPDATE BOOKING_ORDER SET DATEIN = ?, DATEOUT = ? WHERE BK_NO = ?";
	private static final String ORDERPAID = "UPDATE BOOKING_ORDER SET BK_STATUS = 1 WHERE BK_NO = ?";
	private static final String CHECKIN = "UPDATE BOOKING_ORDER SET CHECKIN = CURRENT_TIMESTAMP, BK_STATUS = 2 WHERE BK_NO = ?";
	private static final String CHECKOUT = "UPDATE BOOKING_ORDER SET CHECKOUT = CURRENT_TIMESTAMP, BK_STATUS = 3 WHERE BK_NO = ?";
	private static final String GETALL = "SELECT * FROM BOOKING_ORDER ORDER BY BK_NO";
	private static final String GETALLBYBKSTATUS = "SELECT * FROM BOOKING_ORDER WHERE BK_STATUS = ? ORDER BY BK_NO";
	private static final String GETALLBYDATEIN = "SELECT * FROM BOOKING_ORDER WHERE DATEIN = ? ORDER BY BK_NO";
	private static final String GETONEBYBKNO = "SELECT * FROM BOOKING_ORDER WHERE BK_NO = ?";
	private static final String GETONEBYMBID = "SELECT * FROM BOOKING_ORDER WHERE MB_ID = ?";

	@Override
	public BookingOrderVO insert(BookingOrderVO bkodvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String[] genKey = new String[1];
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT, genKey);
			pstmt.setString(1, bkodvo.getMb_id());
			pstmt.setDate(2, bkodvo.getDateIn());
			pstmt.setDate(3, bkodvo.getDateOut());
			pstmt.setInt(4, bkodvo.getTotal_price());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				bkodvo.setBk_no(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return bkodvo;
	}

	@Override
	public void update(BookingOrderVO bkodvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATEDATEINOUT);
			pstmt.setDate(1, bkodvo.getDateIn());
			pstmt.setDate(2, bkodvo.getDateOut());
			pstmt.setString(3, bkodvo.getBk_no());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void updateOrderPaid(String bk_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(ORDERPAID);
			pstmt.setString(1, bk_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void updateCheckIn(String bk_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(CHECKIN);
			pstmt.setString(1, bk_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void updateCheckOut(String bk_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(CHECKOUT);
			pstmt.setString(1, bk_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public List<BookingOrderVO> getAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookingOrderVO> bkodvoList = new LinkedList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookingOrderVO bkodvo = new BookingOrderVO();
				bkodvo.setBk_no(rs.getString("BK_NO"));
				bkodvo.setMb_id(rs.getString("MB_ID"));
				bkodvo.setBk_date(rs.getDate("BK_DATE"));
				bkodvo.setDateIn(rs.getDate("DATEIN"));
				bkodvo.setDateOut(rs.getDate("DATEOUT"));
				bkodvo.setCheckIn(rs.getDate("CHECKIN"));
				bkodvo.setCheckOut(rs.getDate("CHECKOUT"));
				bkodvo.setBk_status(rs.getString("BK_STATUS"));
				bkodvo.setTotal_price(rs.getInt("TOTAL_PRICE"));
				bkodvoList.add(bkodvo);
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return bkodvoList;
	}

	@Override
	public List<BookingOrderVO> getAllByBkStatus(String bk_status) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookingOrderVO> bkodvoList = new LinkedList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALLBYBKSTATUS);
			pstmt.setString(1, bk_status);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookingOrderVO bkodvo = new BookingOrderVO();
				bkodvo.setBk_no(rs.getString("BK_NO"));
				bkodvo.setMb_id(rs.getString("MB_ID"));
				bkodvo.setBk_date(rs.getDate("BK_DATE"));
				bkodvo.setDateIn(rs.getDate("DATEIN"));
				bkodvo.setDateOut(rs.getDate("DATEOUT"));
				bkodvo.setCheckIn(rs.getDate("CHECKIN"));
				bkodvo.setCheckOut(rs.getDate("CHECKOUT"));
				bkodvo.setBk_status(bk_status);
				bkodvo.setTotal_price(rs.getInt("TOTAL_PRICE"));
				bkodvoList.add(bkodvo);
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return bkodvoList;
	}

	@Override
	public List<BookingOrderVO> getAllByDateIn(Date date_in) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookingOrderVO> bkodvoList = new LinkedList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALLBYDATEIN);
			pstmt.setDate(1, date_in);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookingOrderVO bkodvo = new BookingOrderVO();
				bkodvo.setBk_no(rs.getString("BK_NO"));
				bkodvo.setMb_id(rs.getString("MB_ID"));
				bkodvo.setBk_date(rs.getDate("BK_DATE"));
				bkodvo.setDateIn(date_in);
				bkodvo.setDateOut(rs.getDate("DATEOUT"));
				bkodvo.setCheckIn(rs.getDate("CHECKIN"));
				bkodvo.setCheckOut(rs.getDate("CHECKOUT"));
				bkodvo.setBk_status(rs.getString("BK_STATUS"));
				bkodvo.setTotal_price(rs.getInt("TOTAL_PRICE"));
				bkodvoList.add(bkodvo);
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return bkodvoList;
	}

	@Override
	public BookingOrderVO getOneByBkNo(String bk_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookingOrderVO bkodvo = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETONEBYBKNO);
			pstmt.setString(1, bk_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bkodvo = new BookingOrderVO();
				bkodvo.setBk_no(bk_no);
				bkodvo.setMb_id(rs.getString("MB_ID"));
				bkodvo.setBk_date(rs.getDate("BK_DATE"));
				bkodvo.setDateIn(rs.getDate("DATEIN"));
				bkodvo.setDateOut(rs.getDate("DATEOUT"));
				bkodvo.setCheckIn(rs.getDate("CHECKIN"));
				bkodvo.setCheckOut(rs.getDate("CHECKOUT"));
				bkodvo.setBk_status(rs.getString("BK_STATUS"));
				bkodvo.setTotal_price(rs.getInt("TOTAL_PRICE"));
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return bkodvo;
	}

	@Override
	public BookingOrderVO getOneByMbId(String mb_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookingOrderVO bkodvo = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETONEBYMBID);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bkodvo = new BookingOrderVO();
				bkodvo.setBk_no(rs.getString("BK_NO"));
				bkodvo.setMb_id(mb_id);
				bkodvo.setBk_date(rs.getDate("BK_DATE"));
				bkodvo.setDateIn(rs.getDate("DATEIN"));
				bkodvo.setDateOut(rs.getDate("DATEOUT"));
				bkodvo.setCheckIn(rs.getDate("CHECKIN"));
				bkodvo.setCheckOut(rs.getDate("CHECKOUT"));
				bkodvo.setBk_status(rs.getString("BK_STATUS"));
				bkodvo.setTotal_price(rs.getInt("TOTAL_PRICE"));
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return bkodvo;
	}

}
