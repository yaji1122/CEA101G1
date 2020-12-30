package com.item_pics.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Item_picsJDBCDAO implements Item_picsDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "SHOP";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO ITEM_PICS (ITEM_PIC_NO, ITEM_NO, ITEM_PIC) VALUES ('P'||LPAD(to_char(PIC_SEQ.NEXTVAL), 4, '0'),?,?)";
	private static final String GET_ALL_STMT = "SELECT ITEM_PIC_NO, ITEM_NO, ITEM_PIC FROM ITEM_PICS WHERE ITEM_NO=?";
	private static final String GET_ONE_STMT = "SELECT ITEM_PIC_NO, ITEM_NO, ITEM_PIC FROM ITEM_PICS where ITEM_PIC_NO = ?";
	private static final String UPDATE = "UPDATE ITEM_PICS set ITEM_NO=?, ITEM_PIC=? where ITEM_PIC_NO = ?";
	private static final String DELETE = "DELETE FROM ITEM_PICS WHERE ITEM_PIC_NO = ?";

	@Override
	public void insert(Item_picsVO item_picsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, item_picsVO.getItem_no());
			pstmt.setBytes(2, item_picsVO.getItem_pic());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(Item_picsVO item_picsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, item_picsVO.getItem_no());
			pstmt.setBytes(2, item_picsVO.getItem_pic());
			pstmt.setString(3, item_picsVO.getItem_pic_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public Item_picsVO findByPrimaryKey(String item_pic_no) {

		Item_picsVO item_picsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, item_pic_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				item_picsVO = new Item_picsVO();
				item_picsVO.setItem_pic_no(rs.getString("item_pic_no"));
				item_picsVO.setItem_no(rs.getString("item_no"));
				item_picsVO.setItem_pic(rs.getBytes("item_pic"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return item_picsVO;
	}

	@Override
	public List<Item_picsVO> getAll(String item_no) {
		List<Item_picsVO> list = new ArrayList<Item_picsVO>();
		Item_picsVO item_picsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1,  item_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				item_picsVO = new Item_picsVO();
				item_picsVO.setItem_pic_no(rs.getString("item_pic_no"));
				item_picsVO.setItem_no(rs.getString("item_no"));
				item_picsVO.setItem_pic(rs.getBytes("item_pic"));
				list.add(item_picsVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	
	public void delete(String item_pic_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, item_pic_no);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
	
	public static void readPicture(byte[] bytes) throws IOException {
		FileOutputStream fos = new FileOutputStream("P_pic");
		fos.write(bytes);
		fos.flush();
		fos.close();
	}	
	
	public static void main(String[] args) throws IOException {

		Item_picsJDBCDAO dao = new Item_picsJDBCDAO();

		// 新增
//		Item_picsVO item_picsVO1 = new Item_picsVO();
//		byte[] pic = getPictureByteArray("C:\\專題\\圖檔\\chaneln51.jpg");
//		
//		item_picsVO1.setItem_no("I0001");
//		item_picsVO1.setItem_pic(pic);
//		dao.insert(item_picsVO1);

		// 修改
//		Item_picsVO item_picsVO2 = new Item_picsVO();
//		byte[] pic = getPictureByteArray("C:\\專題\\圖檔\\chaneln51jpg");
//		item_picsVO2.setItem_pic_no("P001");
//		item_picsVO2.setItem_no("I0002");
//		item_picsVO2.setItem_pic(pic);
//		dao.update(item_picsVO2);

		//刪除
		dao.delete("P0002");
		
		
		// 查詢
//		Item_picsVO item_picsVO3 = dao.findByPrimaryKey("P0001");
//		System.out.print(item_picsVO3.getItem_pic_no() + ",");
//		System.out.print(item_picsVO3.getItem_no() + ",");
//		System.out.println(item_picsVO3.getItem_pic());
//		System.out.println("---------------------");

		// 查詢
//		List<Item_picsVO> list = dao.getAll("I0002");
//		for (Item_picsVO aItem_pics : list) {
//			System.out.print(aItem_pics.getItem_pic_no() + ",");
//			System.out.print(aItem_pics.getItem_no() + ",");
//			System.out.print(aItem_pics.getItem_pic());
//			System.out.println();
//		}
	}
}
