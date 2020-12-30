package com.item_pics.model;

import java.io.BufferedInputStream;
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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;
import javax.sql.DataSource;

public class Item_picsDAO implements Item_picsDAO_interface {

	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO ITEM_PICS (ITEM_PIC_NO, ITEM_NO, ITEM_PIC) VALUES ('P'||LPAD(to_char(PIC_SEQ.NEXTVAL), 4, '0'),?,?)";
	private static final String GET_ALL_STMT = "SELECT ITEM_PIC_NO, ITEM_NO, ITEM_PIC FROM ITEM_PICS WHERE ITEM_NO=? ORDER BY ITEM_NO";
	private static final String GET_ONE_STMT = "SELECT ITEM_PIC_NO, ITEM_NO, ITEM_PIC FROM ITEM_PICS where ITEM_PIC_NO = ?";
	private static final String UPDATE = "UPDATE ITEM_PICS set ITEM_NO=?, ITEM_PIC=? where ITEM_PIC_NO = ?";
	private static final String DELETE = "DELETE FROM ITEM_PICS WHERE ITEM_PIC_NO = ?";
	
	
	@Override
	public void insert(Item_picsVO item_picsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		BufferedInputStream bis = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			String item_no = item_picsVO.getItem_no();
			Part part = item_picsVO.getPart();
			bis = new BufferedInputStream(part.getInputStream());
			byte[] buffer = new byte[bis.available()];
			bis.read(buffer);			
			pstmt.setString(1, item_no);
			pstmt.setBytes(2, buffer);

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, item_picsVO.getItem_no());
			pstmt.setBytes(2, item_picsVO.getItem_pic());
			pstmt.setString(3, item_picsVO.getItem_pic_no());

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
	public Item_picsVO findByPrimaryKey(String item_pic_no) {

		Item_picsVO item_picsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, item_pic_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				item_picsVO = new Item_picsVO();
				item_picsVO.setItem_pic_no(rs.getString("item_pic_no"));
				item_picsVO.setItem_no(rs.getString("item_no"));
				item_picsVO.setItem_pic(rs.getBytes("item_pic"));
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
		return item_picsVO;
	}
	
	public void delete(String item_pic_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, item_pic_no);
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
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	
	@Override
	public List<Item_picsVO> getAll(String item_no) {
		List<Item_picsVO> list = new ArrayList<Item_picsVO>();
		Item_picsVO item_picsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1, item_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				item_picsVO = new Item_picsVO();
				item_picsVO.setItem_pic_no(rs.getString("item_pic_no"));
				item_picsVO.setItem_no(rs.getString("item_no"));
				item_picsVO.setItem_pic(rs.getBytes("item_pic"));
				list.add(item_picsVO); // Store the row in the list
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

//	public static void readPicture(byte[] bytes) throws IOException {
//		FileOutputStream fos = new FileOutputStream("P_pic");
//		fos.write(bytes);
//		fos.flush();
//		fos.close();
//	}

	
}
