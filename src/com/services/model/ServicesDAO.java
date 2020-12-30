package com.services.model;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ServicesDAO implements ServicesDAO_interface{
	
	private static DataSource ds = null;

	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		} catch (NamingException e) {
			e.printStackTrace(System.err);
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO SERVICES (SERV_NO, SERV_TYPE_NO, SERV_STATUS, SERV_PRICE, SERV_PIC, SERV_INFO, SERV_NAME, SERV_DURA, SERV_PPL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT SERV_NO, SERV_TYPE_NO, SERV_STATUS, SERV_PRICE, SERV_PIC, SERV_INFO, SERV_NAME, SERV_DURA, SERV_PPL FROM SERVICES order by SERV_NO";
	private static final String GET_ONE_STMT = 
			"SELECT SERV_NO, SERV_TYPE_NO, SERV_STATUS, SERV_PRICE, SERV_PIC, SERV_INFO, SERV_NAME, SERV_DURA, SERV_PPL FROM SERVICES where SERV_NO = ?";
	private static final String DELETE = 
			"DELETE FROM SERVICES where SERV_NO = ?";
	private static final String UPDATE = 
			"UPDATE SERVICES set SERV_TYPE_NO=?, SERV_STATUS=?, SERV_PRICE=?, SERV_PIC=?, SERV_INFO=?, SERV_NAME=?, SERV_DURA=?, SERV_PPL=? where SERV_NO=?";
	private static final String GETONE =
	"SELECT * FROM SERVICES WHERE SERV_NO =?";

	@Override
	public void insert(ServicesVO servicesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, servicesVO.getServ_no());
			pstmt.setString(2, servicesVO.getServ_type_no());
			pstmt.setString(3, servicesVO.getServ_status());
			pstmt.setInt(4, servicesVO.getServ_price());
			pstmt.setBytes(5, servicesVO.getServ_pic());
			pstmt.setString(6, servicesVO.getServ_info());
			pstmt.setString(7, servicesVO.getServ_name());
			pstmt.setInt(8, servicesVO.getServ_dura());
			pstmt.setInt(9, servicesVO.getServ_ppl());

			pstmt.executeUpdate();
			
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
	public void update(ServicesVO servicesVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(9, servicesVO.getServ_no());
			pstmt.setString(1, servicesVO.getServ_type_no());
			pstmt.setString(2, servicesVO.getServ_status());
			pstmt.setInt(3, servicesVO.getServ_price());
			pstmt.setBytes(4, servicesVO.getServ_pic());
			pstmt.setString(5, servicesVO.getServ_info());
			pstmt.setString(6, servicesVO.getServ_name());
			pstmt.setInt(7, servicesVO.getServ_dura());
			pstmt.setInt(8, servicesVO.getServ_ppl());

			pstmt.executeUpdate();

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
	public void delete(String serv_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, serv_no);

			pstmt.executeUpdate();

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
	public ServicesVO findByPrimaryKey(String serv_no) {
		ServicesVO servicesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, serv_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// servicesVO 也稱為 Domain objects
				servicesVO = new ServicesVO();
				servicesVO.setServ_no(rs.getString("serv_no"));
				servicesVO.setServ_type_no(rs.getString("serv_type_no"));
				servicesVO.setServ_status(rs.getString("serv_status"));
				servicesVO.setServ_price(rs.getInt("serv_price"));
				servicesVO.setServ_pic(rs.getBytes("serv_pic"));
				servicesVO.setServ_info(rs.getString("serv_info"));
				servicesVO.setServ_name(rs.getString("serv_name"));
				servicesVO.setServ_dura(rs.getInt("serv_dura"));
				servicesVO.setServ_ppl(rs.getInt("serv_ppl"));
			}

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
		return servicesVO;
	}

	@Override
	public List<ServicesVO> getAll() {
		List<ServicesVO> list = new ArrayList<ServicesVO>();
		ServicesVO servicesVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// servicesVO 也稱為 Domain objects
				servicesVO = new ServicesVO();
				servicesVO.setServ_no(rs.getString("serv_no"));
				servicesVO.setServ_type_no(rs.getString("serv_type_no"));
				servicesVO.setServ_status(rs.getString("serv_status"));
				servicesVO.setServ_price(rs.getInt("serv_price"));
				servicesVO.setServ_pic(rs.getBytes("serv_pic"));
				servicesVO.setServ_info(rs.getString("serv_info"));
				servicesVO.setServ_name(rs.getString("serv_name"));
				servicesVO.setServ_dura(rs.getInt("serv_dura"));
				servicesVO.setServ_ppl(rs.getInt("serv_ppl"));
				list.add(servicesVO); // Store the row in the list
			}

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
	
//	public static byte[] getPictureByteArray(String path) throws IOException {
//		File file = new File(path);
//		FileInputStream fis = new FileInputStream(file);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		byte[] buffer = new byte[8192];
//		int i;
//		while ((i = fis.read(buffer)) != -1) {
//			baos.write(buffer, 0, i);
//		}
//		baos.close();
//		fis.close();
//		return baos.toByteArray();
//	}
	
	@Override
	public byte[] getOneServicesPic(String serv_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		byte[] serv_pic = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETONE);
			pstmt.setString(1, serv_no);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			serv_pic = rs.getBytes("serv_pic");
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}  finally {
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
		return serv_pic;
	}
	
	public static void main(String[] args) {
		ServicesDAO_interface dao = new ServicesJDBCDAO();

		// 新增
//		ServicesVO serv1 = new ServicesVO();
//		serv1.setServ_no("066");
//		serv1.setServ_type_no("1");
//		serv1.setServ_status("0");
//		serv1.setServ_price(1850);
//		serv1.setServ_name("測試中");
//		serv1.setServ_dura(2);
//		serv1.setServ_ppl(18);
//		serv1.getServ_pic(getPictureByteArray("rr"));
//		dao.insert(serv1);

		// 修改
//		ServicesVO serv2 = new ServicesVO();
//		serv2.setServ_no("002");
//		serv2.setServ_type_no("001");
//		serv2.setServ_status("1");
//		serv2.setServ_price(9999);
//		serv2.setServ_name("ssssss");
//		serv2.setServ_dura(2);
//		serv2.setServ_ppl(18);
//		dao.update(serv2);

		// 刪除
//		dao.delete("012");

		// 查詢
//		ServicesVO serv3 = dao.findByPrimaryKey("001");
//		System.out.print(serv3.getServ_no() + ",");
//		System.out.print(serv3.getServ_type_no() + ",");
//		System.out.print(serv3.getServ_status() + ",");
//		System.out.print(serv3.getServ_price() + ",");
//		System.out.print(serv3.getServ_pic() + ",");
//		System.out.print(serv3.getServ_info() + ",");
//		System.out.print(serv3.getServ_name() + ",");
//		System.out.print(serv3.getServ_dura() + ",");
//		System.out.println(serv3.getServ_ppl());
//		System.out.println("-----------------------------");

		// 查詢
//		List<ServicesVO> list = dao.getAll();
//		for (ServicesVO serv : list) {
//			System.out.print(serv.getServ_no() + ",");
//			System.out.print(serv.getServ_type_no() + ",");
//			System.out.print(serv.getServ_status() + ",");
//			System.out.print(serv.getServ_price() + ",");
//			System.out.print(serv.getServ_pic() + ",");
//			System.out.print(serv.getServ_info() + ",");
//			System.out.print(serv.getServ_name() + ",");
//			System.out.print(serv.getServ_dura() + ",");
//			System.out.print(serv.getServ_ppl());
//			System.out.println();
//		}
	}

}
