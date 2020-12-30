package com.bookingdetail.model;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BookingDetailDAO implements BookingDetailDAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		} catch (NamingException e) {
			e.printStackTrace(System.err);
		}
	}

	private static final String INSERT = "INSERT INTO BOOKING_DETAIL (BK_NO, RM_TYPE, RM_PRICE, QTY) VALUES (?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE BOOKING_DETAIL SET QTY = ? WHERE BK_NO = ? AND RM_TYPE = ?";
	private static final String GETALLBYBKNO = "SELECT * FROM BOOKING_DETAIL WHERE BK_NO = ?";

	@Override
	public void insert(BookingDetailVO bkdetailvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			String bk_no = bkdetailvo.getBk_no();
			String rm_type = bkdetailvo.getRm_type();
			Integer rm_price = bkdetailvo.getRm_price();
			Integer qty = bkdetailvo.getQty();
			pstmt.setString(1, bk_no);
			pstmt.setString(2, rm_type);
			pstmt.setInt(3, rm_price);
			pstmt.setInt(4, qty);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(BookingDetailVO bkdetailvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE);
			String bk_no = bkdetailvo.getBk_no();
			String rm_type = bkdetailvo.getRm_type();
			Integer qty = bkdetailvo.getQty();
			pstmt.setInt(1, qty);
			pstmt.setString(2, bk_no);
			pstmt.setString(3, rm_type);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<BookingDetailVO> getAllByBkNo(String bk_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookingDetailVO> bkdetailvoList = new LinkedList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALLBYBKNO);
			pstmt.setString(1, bk_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookingDetailVO bkdetailvo = new BookingDetailVO();
				bkdetailvo.setBk_no(rs.getString("BK_NO"));
				bkdetailvo.setRm_type(rs.getString("RM_TYPE"));
				bkdetailvo.setRm_price(rs.getInt("RM_PRICE"));
				bkdetailvo.setQty(rs.getInt("QTY"));
				bkdetailvoList.add(bkdetailvo);
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bkdetailvoList;
	}

}
