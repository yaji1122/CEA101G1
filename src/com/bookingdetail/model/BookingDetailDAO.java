package com.bookingdetail.model;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.JSONObject;

import com.bookingorder.model.BookingOrderVO;
import com.roomrsv.model.RoomRsvService;

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

	private static final String INSERT = "INSERT INTO BOOKING_DETAIL (SEQ_NO, BK_NO, RM_TYPE, RM_SUBTOTAL, RM_GUEST) VALUES ('BKDT'|| LPAD(to_char(BKDT_SEQ.NEXTVAL), 6, '0'), ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE BOOKING_DETAIL SET QTY = ? WHERE BK_NO = ? AND RM_TYPE = ?";
	private static final String GETALLBYBKNO = "SELECT * FROM BOOKING_DETAIL WHERE BK_NO = ?";
	private static final String GETALL = "SELECT * FROM BOOKING_DETAIL ORDER BY SEQ_NO";
	
	@Override
	public void insert(BookingOrderVO bkodvo, JSONObject bkitem, Connection conn) {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, bkodvo.getBk_no());
			pstmt.setString(2, bkitem.getString("rmtype"));
			pstmt.setInt(3, Integer.parseInt(bkitem.getString("subtotal")));
			pstmt.setInt(4, Integer.parseInt(bkitem.getString("guest")));
			pstmt.executeUpdate();
			RoomRsvService rsvSvc = new RoomRsvService();
			rsvSvc.updateRmLeft(bkitem, conn);
		} catch (SQLException e) {
			if (conn != null) {
				try {
					System.err.print("有內鬼，交易撤回");
					conn.rollback();
				} catch (SQLException re){
					throw new RuntimeException("rollback發生錯誤:" + re.getMessage());
				}
			}
			e.printStackTrace();
			throw new RuntimeException("A database error occured:" + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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
			Integer rm_guest = bkdetailvo.getRm_guest();
			pstmt.setInt(1, rm_guest);
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
				bkdetailvo.setRm_subtotal(rs.getInt("RM_SUBTOTAL"));
				bkdetailvo.setRm_guest(rs.getInt("RM_GUEST"));
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

	@Override
	public List<BookingDetailVO> getAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookingDetailVO> bkdetailvoList = new LinkedList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookingDetailVO bkdetailvo = new BookingDetailVO();
				bkdetailvo.setBk_no(rs.getString("BK_NO"));
				bkdetailvo.setRm_type(rs.getString("RM_TYPE"));
				bkdetailvo.setRm_subtotal(rs.getInt("RM_SUBTOTAL"));
				bkdetailvo.setRm_guest(rs.getInt("RM_GUEST"));
				bkdetailvoList.add(bkdetailvo);
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			if (rs != null ) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
