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

public class Sale_eventJDBCDAO implements Sale_eventDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "SHOP";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO SALE_EVENT (SALE_NO, SALE_START, SALE_END, SALE_STATUS) VALUES (to_char(SYSDATE, 'YYYYMMDD')||LPAD(to_char(SALE_SEQ.NEXTVAL), 2, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT SALE_NO, SALE_START, SALE_END, SALE_STATUS FROM SALE_EVENT order by SALE_NO";
	private static final String GET_ONE_STMT = 
		"SELECT SALE_NO, SALE_START, SALE_END, SALE_STATUS FROM SALE_EVENT where SALE_NO = ?";
	private static final String UPDATE = 
		"UPDATE SALE_EVENT set SALE_START=?, SALE_END=?,SALE_STATUS=? where SALE_NO = ?";

	@Override
	public void insert(Sale_eventVO sale_eventVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setDate(1, sale_eventVO.getSale_start());
			pstmt.setDate(2, sale_eventVO.getSale_end());
			pstmt.setString(3, sale_eventVO.getSale_status());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, sale_eventVO.getSale_no());
			pstmt.setDate(2, sale_eventVO.getSale_start());
			pstmt.setDate(3, sale_eventVO.getSale_end());
			pstmt.setString(4, sale_eventVO.getSale_status());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	public static void main(String[] args) throws ParseException  {

		Sale_eventJDBCDAO dao = new Sale_eventJDBCDAO();
		
		SimpleDateFormat sfm1 = new SimpleDateFormat("yyyy/MM/dd");
		
		java.util.Date date = sfm1.parse("2020/01/22");
		
		// 新增
		Sale_eventVO sale_eventVO1 = new Sale_eventVO();
		sale_eventVO1.setSale_start(new java.sql.Date(date.getTime()));
		sale_eventVO1.setSale_end(null);
		sale_eventVO1.setSale_status("0");
		dao.insert(sale_eventVO1);

		// 修改
		Sale_eventVO sale_eventVO2 = new Sale_eventVO();
		sale_eventVO2.setSale_start(new java.sql.Date(date.getTime()));
		sale_eventVO2.setSale_end(null);
		sale_eventVO2.setSale_status("1");
		sale_eventVO2.setSale_no("2020120801");
		dao.update(sale_eventVO2);

		// 查詢
		Sale_eventVO sale_eventVO3 = dao.findByPrimaryKey("2020120801");
		System.out.print(sale_eventVO3.getSale_no() + ",");
		System.out.print(sale_eventVO3.getSale_start() + ",");
		System.out.print(sale_eventVO3.getSale_end() + ",");
		System.out.println(sale_eventVO3.getSale_status());
		System.out.println("---------------------");

		// 查詢
		List<Sale_eventVO> list = dao.getAll();
		for (Sale_eventVO aSale_event : list) {
			System.out.print( aSale_event.getSale_no() + ",");
			System.out.print( aSale_event.getSale_start() + ",");
			System.out.print( aSale_event.getSale_end() + ",");
			System.out.print( aSale_event.getSale_status() );
			System.out.println();
		}
	}
}
