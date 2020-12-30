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

import com.sale_event.model.Sale_eventJDBCDAO;
import com.sale_event.model.Sale_eventVO;

public class Sale_event_listJDBCDAO implements Sale_event_listDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "SHOP";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO SALE_EVENT_LIST (SALE_NO, ITEM_NO, SALE_DISCOUNT) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT SALE_NO, ITEM_NO, SALE_DISCOUNT FROM SALE_EVENT_LIST order by SALE_NO";
	private static final String GET_ONE_STMT = 
		"SELECT SALE_NO, ITEM_NO, SALE_DISCOUNT FROM SALE_EVENT_LIST where SALE_NO = ? AND ITEM_NO =?";
	private static final String UPDATE = 
		"UPDATE SALE_EVENT_LIST SET SALE_DISCOUNT=? where SALE_NO = ? AND ITEM_NO=?";

	@Override
	public void insert(Sale_event_listVO sale_event_listVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, sale_event_listVO.getSale_no());
			pstmt.setString(2, sale_event_listVO.getItem_no());
			pstmt.setDouble(3, sale_event_listVO.getSale_discount());
			

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
	public void update(Sale_event_listVO sale_event_listVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDouble(1, sale_event_listVO.getSale_discount());
			pstmt.setString(2, sale_event_listVO.getSale_no());
			pstmt.setString(3, sale_event_listVO.getItem_no());
			
			

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
	public Sale_event_listVO  findByPrimaryKey(String sale_no, String item_no) {

		Sale_event_listVO sale_event_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		Sale_event_listJDBCDAO dao = new Sale_event_listJDBCDAO();
		
	
		
		// 新增
//		Sale_event_listVO sale_event_listVO1 = new Sale_event_listVO();
//		sale_event_listVO1.setSale_no("2020120801");
//		sale_event_listVO1.setItem_no("I0005");
//		sale_event_listVO1.setSale_discount(0.9);
//		dao.insert(sale_event_listVO1);

		// 修改
//		Sale_event_listVO sale_event_listVO2 = new Sale_event_listVO();
//		sale_event_listVO2.setSale_discount(0.8);
//		sale_event_listVO2.setSale_no("2020120801");
//		sale_event_listVO2.setItem_no("I0005");
//		dao.update(sale_event_listVO2);

		// 查詢
		Sale_event_listVO sale_event_listVO3 = dao.findByPrimaryKey("2020120801", "I0005");
		System.out.print(sale_event_listVO3.getSale_no() + ",");
		System.out.print(sale_event_listVO3.getItem_no() + ",");
		System.out.println(sale_event_listVO3.getSale_discount());
		System.out.println("---------------------");

		// 查詢
		List<Sale_event_listVO> list = dao.getAll();
		for (Sale_event_listVO aSale_event_list : list) {
			System.out.print( aSale_event_list.getSale_no() + ",");
			System.out.print( aSale_event_list.getItem_no() + ",");
			System.out.print( aSale_event_list.getSale_discount() );
			System.out.println();
		}
	}
}
