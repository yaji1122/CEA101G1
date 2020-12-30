package com.sale_event_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sale_event.model.Sale_eventJDBCDAO;
import com.sale_event.model.Sale_eventVO;

public class Sale_event_listDAO implements Sale_event_listDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO SALE_EVENT_LIST (SALE_NO, ITEM_NO, SALE_DISCOUNT) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT SALE_NO, ITEM_NO, SALE_DISCOUNT FROM SALE_EVENT_LIST order by SALE_NO";
	private static final String GET_ONE_STMT = "SELECT SALE_NO, ITEM_NO, SALE_DISCOUNT FROM SALE_EVENT_LIST where SALE_NO = ? AND ITEM_NO =?";
	private static final String UPDATE = "UPDATE SALE_EVENT_LIST SET SALE_DISCOUNT=? where SALE_NO = ? AND ITEM_NO=?";

	@Override
	public void insert(Sale_event_listVO sale_event_listVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, sale_event_listVO.getSale_no());
			pstmt.setString(2, sale_event_listVO.getItem_no());
			pstmt.setDouble(3, sale_event_listVO.getSale_discount());

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
	public void update(Sale_event_listVO sale_event_listVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDouble(1, sale_event_listVO.getSale_discount());
			pstmt.setString(2, sale_event_listVO.getSale_no());
			pstmt.setString(3, sale_event_listVO.getItem_no());

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
	public Sale_event_listVO findByPrimaryKey(String sale_no, String item_no) {

		Sale_event_listVO sale_event_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, sale_no);
			pstmt.setString(2, item_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				sale_event_listVO = new Sale_event_listVO();
				sale_event_listVO.setSale_no(rs.getString("sale_no"));
				sale_event_listVO.setItem_no(rs.getString("item_no"));
				sale_event_listVO.setSale_discount(rs.getDouble("sale_discount"));
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
		return sale_event_listVO;
	}

	@Override
	public List<Sale_event_listVO> getAll() {
		List<Sale_event_listVO> list = new ArrayList<Sale_event_listVO>();
		Sale_event_listVO sale_event_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				sale_event_listVO = new Sale_event_listVO();
				sale_event_listVO.setSale_no(rs.getString("sale_no"));
				sale_event_listVO.setItem_no(rs.getString("item_no"));
				sale_event_listVO.setSale_discount(rs.getDouble("sale_discount"));
				list.add(sale_event_listVO); // Store the row in the list
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
