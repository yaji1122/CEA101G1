package com.item_type.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Item_typeJDBCDAO implements Item_typeDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "SHOP";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO ITEM_TYPE (ITEM_TYPE_NO, TYPE_NAME) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ITEM_TYPE_NO, TYPE_NAME FROM ITEM_TYPE order by ITEM_TYPE_NO";
	private static final String GET_ONE_STMT = 
		"SELECT ITEM_TYPE_NO, TYPE_NAME FROM ITEM_TYPE where ITEM_TYPE_NO = ?";
	private static final String UPDATE = 
		"UPDATE ITEM_TYPE set TYPE_NAME=? where ITEM_TYPE_NO = ?";

	@Override
	public void insert(Item_typeVO item_typeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, item_typeVO.getItem_type_no());
			pstmt.setString(2, item_typeVO.getType_name());
			

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
	public void update(Item_typeVO item_typeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, item_typeVO.getType_name());
			pstmt.setString(2, item_typeVO.getItem_type_no());
			

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
	public Item_typeVO findByPrimaryKey(String item_type_no) {

		Item_typeVO item_typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, item_type_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				item_typeVO = new Item_typeVO();
				item_typeVO.setItem_type_no(rs.getString("item_type_no"));
				item_typeVO.setType_name(rs.getString("type_name"));
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
		return item_typeVO;
	}

	@Override
	public List<Item_typeVO> getAll() {
		List<Item_typeVO> list = new ArrayList<Item_typeVO>();
		Item_typeVO item_typeVO = null;

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
				item_typeVO = new Item_typeVO();
				item_typeVO.setItem_type_no(rs.getString("item_type_no"));
				item_typeVO.setType_name(rs.getString("type_name"));
				list.add(item_typeVO); // Store the row in the list
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

	public static void main(String[] args) {

		Item_typeJDBCDAO dao = new Item_typeJDBCDAO();

//		// 新增
//		Item_typeVO item_typeVO1 = new Item_typeVO();
//		item_typeVO1.setItem_type_no("17");
//		item_typeVO1.setType_name("化妝品");
//		dao.insert(item_typeVO1);
//
//		// 修改
		Item_typeVO item_typeVO2 = new Item_typeVO();
		item_typeVO2.setItem_type_no("11");
		item_typeVO2.setType_name("精選皮件");
		dao.update(item_typeVO2);

		// 查詢
//		Item_typeVO item_typeVO3 = dao.findByPrimaryKey("12");
//		System.out.print(item_typeVO3.getItem_type_no() + ",");
//		System.out.println(item_typeVO3.getType_name());
//		System.out.println("---------------------");

		// 查詢
//		List<Item_typeVO> list = dao.getAll();
//		for (Item_typeVO aItem_type : list) {
//			System.out.print( aItem_type.getItem_type_no() + ",");
//			System.out.print( aItem_type.getType_name() );
//			System.out.println();
//		}
	}
}
