package com.sale_event.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Sale_eventDAO implements Sale_eventDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO SALE_EVENT (SALE_NO, SALE_START, SALE_END, SALE_STATUS) VALUES (to_char(SYSDATE, 'YYYYMMDD')||LPAD(to_char(SALE_SEQ.NEXTVAL), 2, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT SALE_NO, SALE_START, SALE_END, SALE_STATUS FROM SALE_EVENT order by SALE_NO";
	private static final String GET_ONE_STMT = "SELECT SALE_NO, SALE_START, SALE_END, SALE_STATUS FROM SALE_EVENT where SALE_NO = ?";
	private static final String UPDATE = "UPDATE SALE_EVENT set SALE_START=?, SALE_END=?,SALE_STATUS=? where SALE_NO = ?";

	@Override
	public void insert(Sale_eventVO sale_eventVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setDate(1, sale_eventVO.getSale_start());
			pstmt.setDate(2, sale_eventVO.getSale_end());
			pstmt.setString(3, sale_eventVO.getSale_status());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Sale_eventVO sale_eventVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, sale_eventVO.getSale_no());
			pstmt.setDate(2, sale_eventVO.getSale_start());
			pstmt.setDate(3, sale_eventVO.getSale_end());
			pstmt.setString(4, sale_eventVO.getSale_status());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public Sale_eventVO findByPrimaryKey(String sale_no) {

		Sale_eventVO sale_eventVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, sale_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				sale_eventVO = new Sale_eventVO();
				sale_eventVO.setSale_no(rs.getString("sale_no"));
				sale_eventVO.setSale_start(rs.getDate("sale_start"));
				sale_eventVO.setSale_end(rs.getDate("sale_end"));
				sale_eventVO.setSale_status(rs.getString("sale_status"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return sale_eventVO;
	}

	@Override
	public List<Sale_eventVO> getAll() {
		List<Sale_eventVO> list = new ArrayList<Sale_eventVO>();
		Sale_eventVO sale_eventVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				sale_eventVO = new Sale_eventVO();
				sale_eventVO.setSale_no(rs.getString("sale_no"));
				sale_eventVO.setSale_start(rs.getDate("sale_start"));
				sale_eventVO.setSale_end(rs.getDate("sale_end"));
				sale_eventVO.setSale_status(rs.getString("sale_status"));
				list.add(sale_eventVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
