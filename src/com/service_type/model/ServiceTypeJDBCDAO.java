package com.service_type.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceTypeJDBCDAO implements ServiceTypeDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G1";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO SERVICE_TYPE (SERV_TYPE_NO, SERV_TYPE_NAME) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT SERV_TYPE_NO, SERV_TYPE_NAME FROM SERVICE_TYPE order by SERV_TYPE_NO";
	private static final String GET_ONE_STMT = "SELECT SERV_TYPE_NO, SERV_TYPE_NAME FROM SERVICE_TYPE where SERV_TYPE_NO = ?";
	private static final String DELETE = "DELETE FROM SERVICE_TYPE where SERV_TYPE_NO = ?";
	private static final String UPDATE = "UPDATE SERVICE_TYPE set SERV_TYPE_NAME=? where SERV_TYPE_NO=?";

	@Override
	public void insert(ServiceTypeVO serviceTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, serviceTypeVO.getServ_type_no());
			pstmt.setString(2, serviceTypeVO.getServ_type_name());

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
	public void update(ServiceTypeVO serviceTypeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(2, serviceTypeVO.getServ_type_no());
			pstmt.setString(1, serviceTypeVO.getServ_type_name());

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
	public void delete(String serv_type_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, serv_type_no);

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
	public ServiceTypeVO findByPrimaryKey(String serv_type_no) {
		ServiceTypeVO serviceTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, serv_type_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// serviceTypeVO 也稱為 Domain objects
				serviceTypeVO = new ServiceTypeVO();
				serviceTypeVO.setServ_type_no(rs.getString("serv_type_no"));
				serviceTypeVO.setServ_type_name(rs.getString("Serv_type_name"));
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
		return serviceTypeVO;
	}

	@Override
	public List<ServiceTypeVO> getAll() {
		List<ServiceTypeVO> list = new ArrayList<ServiceTypeVO>();
		ServiceTypeVO serviceTypeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// serviceTypeVO 也稱為 Domain objects
				serviceTypeVO = new ServiceTypeVO();
				serviceTypeVO.setServ_type_no(rs.getString("serv_type_no"));
				serviceTypeVO.setServ_type_name(rs.getString("serv_type_name"));
				list.add(serviceTypeVO); // Store the row in the list
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

	public static void main(String[] args) {
		ServiceTypeDAO_interface dao = new ServiceTypeJDBCDAO();

		// 新增
		ServiceTypeVO serv1 = new ServiceTypeVO();
		serv1.setServ_type_no("5");
		serv1.setServ_type_name("其他服務");
		dao.insert(serv1);

		// 修改
//		ServiceTypeVO serv2 = new ServiceTypeVO();
//		serv2.setServ_type_no("3");
//		serv2.setServ_type_name("rrrrrrrr");
//		dao.update(serv2);

		// 刪除
//		dao.delete("3");

		// 查詢
//		ServiceTypeVO serv3 = dao.findByPrimaryKey("2");
//		System.out.print(serv3.getServ_type_no() + ",");
//		System.out.println(serv3.getServ_type_name());
//		System.out.println("-----------------------------");

		// 查詢
//		List<ServiceTypeVO> list = dao.getAll();
//		for (ServiceTypeVO serv : list) {
//			System.out.print(serv.getServ_type_no() + ",");
//			System.out.print(serv.getServ_type_name());
//			System.out.println();
//		}
	}
}
