package com.payment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PaymentDAO implements PaymentDAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO PAYMENT (PAY_NO, MB_ID, CARD_NO, CARD_NAME, EXP_MON, EXP_YEAR, CSC) VALUES ('CRDT'|| LPAD(TO_CHAR(PAYNO_SEQ.NEXTVAL) , 6,'0') ,?, ?, ?, ?, ?, ?)";
	private static final String DELETE = "DELETE FROM PAYMENT WHERE PAY_NO = ?";
	private static final String GETALL = "SELECT * FROM PAYMENT ORDER BY MB_ID";
	private static final String GETALLBYMBID = "SELECT * FROM PAYMENT WHERE MB_ID = ?";

	@Override
	public PaymentVO insert(PaymentVO payvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String[] genKey = new String[]{"PAY_NO"};
			pstmt = conn.prepareStatement(INSERT, genKey);
			pstmt.setString(1, payvo.getMb_id());
			pstmt.setString(2, payvo.getCard_no());
			pstmt.setString(3, payvo.getCard_name());
			pstmt.setString(4, payvo.getExp_mon());
			pstmt.setString(5, payvo.getExp_year());
			pstmt.setString(6, payvo.getCsc());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				payvo.setPay_no(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if( rs != null) {
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
		return payvo;
	}

	@Override
	public void delete(String pay_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setString(1, pay_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
	public List<PaymentVO> getAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PaymentVO> list = new LinkedList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PaymentVO payvo = new PaymentVO();
				payvo.setCard_no(rs.getString("PAY_NO"));
				payvo.setMb_id(rs.getString("MB_ID"));
				payvo.setCard_no(rs.getString("CARD_NO"));
				payvo.setCard_name(rs.getString("CARD_NAME"));
				payvo.setExp_mon(rs.getString("EXP_MON"));
				payvo.setExp_year(rs.getString("EXP_YEAR"));
				payvo.setCsc(rs.getString("CSC"));
				list.add(payvo);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
		return list;
	}

	@Override
	public List<PaymentVO> getAllByMbId(String mb_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PaymentVO> list = new LinkedList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALLBYMBID);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PaymentVO payvo = new PaymentVO();
				payvo.setPay_no(rs.getString("PAY_NO"));
				payvo.setMb_id(rs.getString("MB_ID"));
				payvo.setCard_no(rs.getString("CARD_NO"));
				payvo.setCard_name(rs.getString("CARD_NAME"));
				payvo.setExp_mon(rs.getString("EXP_MON"));
				payvo.setExp_year(rs.getString("EXP_YEAR"));
				payvo.setCsc(rs.getString("CSC"));
				list.add(payvo);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
		return list;
	}
}
