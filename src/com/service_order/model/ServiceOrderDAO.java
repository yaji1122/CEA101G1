package com.service_order.model;

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

public class ServiceOrderDAO implements ServiceOrderDAO_interface{
	
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
			"INSERT INTO SERVICE_ORDER (SERV_ODNO, BK_NO, OD_STATUS, SERV_NO, SERV_TIME, SERV_COUNT, TOTAL_PRICE, LOCATIONS) VALUES ('SERV' || LPAD(to_char(SERVODNO_SEQ.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT SERV_ODNO, BK_NO, OD_TIME, OD_STATUS, SERV_NO, SERV_TIME, SERV_COUNT, TOTAL_PRICE, LOCATIONS FROM SERVICE_ORDER order by SERV_ODNO";
	private static final String GET_ONE_STMT = 
			"SELECT SERV_ODNO, BK_NO, OD_TIME, OD_STATUS, SERV_NO, SERV_TIME, SERV_COUNT, TOTAL_PRICE, LOCATIONS FROM SERVICE_ORDER where SERV_ODNO = ?";
	private static final String DELETE = 
			"DELETE FROM SERVICE_ORDER where SERV_ODNO = ?";
	private static final String UPDATE = 
			"UPDATE SERVICE_ORDER set BK_NO=?, OD_STATUS=?, SERV_NO=?, SERV_TIME=?, SERV_COUNT=?, TOTAL_PRICE=?, LOCATIONS=? where SERV_ODNO=?";
	private static final String GET_ALL_BY_BKNO_STMT = 
			"SELECT * FROM SERVICE_ORDER WHERE BK_NO = ?";

	@Override
	public void insert(ServiceOrderVO serviceOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, serviceOrderVO.getBk_no());
			pstmt.setString(2, "1");
			pstmt.setString(3, serviceOrderVO.getServ_no());
			pstmt.setTimestamp(4, serviceOrderVO.getServ_time());
			pstmt.setInt(5, serviceOrderVO.getServ_count());
			pstmt.setInt(6, serviceOrderVO.getTotal_price());
			pstmt.setString(7, serviceOrderVO.getLocations());

			pstmt.executeUpdate();

			
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
	public void update(ServiceOrderVO serviceOrderVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(9, serviceOrderVO.getServ_odno());
			pstmt.setString(1, serviceOrderVO.getBk_no());
//			pstmt.setTimestamp(2, serviceOrderVO.getOd_time());
			pstmt.setString(2, serviceOrderVO.getOd_status());
			pstmt.setString(3, serviceOrderVO.getServ_no());
			pstmt.setTimestamp(4, serviceOrderVO.getServ_time());
			pstmt.setInt(5, serviceOrderVO.getServ_count());
			pstmt.setInt(6, serviceOrderVO.getTotal_price());
			pstmt.setString(7, serviceOrderVO.getLocations());

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
	public void delete(String serv_odno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, serv_odno);

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
	public ServiceOrderVO findByPrimaryKey(String serv_odno) {
		ServiceOrderVO serviceOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, serv_odno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// serviceOrderVO 也稱為 Domain objects
				serviceOrderVO = new ServiceOrderVO();
				serviceOrderVO.setServ_odno(rs.getString("serv_odno"));
				serviceOrderVO.setBk_no(rs.getString("bk_no"));
				serviceOrderVO.setOd_time(rs.getTimestamp("od_time"));
				serviceOrderVO.setOd_status(rs.getString("od_status"));
				serviceOrderVO.setServ_no(rs.getString("serv_no"));
				serviceOrderVO.setServ_time(rs.getTimestamp("serv_time"));
				serviceOrderVO.setServ_count(rs.getInt("serv_count"));
				serviceOrderVO.setTotal_price(rs.getInt("total_price"));
				serviceOrderVO.setLocations(rs.getString("locations"));
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
		return serviceOrderVO;
	}

	@Override
	public List<ServiceOrderVO> getAll() {
		List<ServiceOrderVO> list = new ArrayList<ServiceOrderVO>();
		ServiceOrderVO serviceOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// serviceOrderVO 也稱為 Domain objects
				serviceOrderVO = new ServiceOrderVO();
				serviceOrderVO.setServ_odno(rs.getString("serv_odno"));
				serviceOrderVO.setBk_no(rs.getString("bk_no"));
				serviceOrderVO.setOd_time(rs.getTimestamp("od_time"));
				serviceOrderVO.setOd_status(rs.getString("od_status"));
				serviceOrderVO.setServ_no(rs.getString("serv_no"));
				serviceOrderVO.setServ_time(rs.getTimestamp("serv_time"));
				serviceOrderVO.setServ_count(rs.getInt("serv_count"));
				serviceOrderVO.setTotal_price(rs.getInt("total_price"));
				serviceOrderVO.setLocations(rs.getString("locations"));
				list.add(serviceOrderVO); // Store the row in the list
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
	
	@Override
	public List<ServiceOrderVO> getAllByBkNo(String bk_no) {
		List<ServiceOrderVO> list = new ArrayList<ServiceOrderVO>();
		ServiceOrderVO serviceOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_BKNO_STMT);
			
			pstmt.setString(1, bk_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// serviceOrderVO 也稱為 Domain objects
				serviceOrderVO = new ServiceOrderVO();
				serviceOrderVO.setBk_no(rs.getString("bk_no"));
				serviceOrderVO.setServ_odno(rs.getString("serv_odno"));
				serviceOrderVO.setOd_time(rs.getTimestamp("od_time"));
				serviceOrderVO.setOd_status(rs.getString("od_status"));
				serviceOrderVO.setServ_no(rs.getString("serv_no"));
				serviceOrderVO.setServ_time(rs.getTimestamp("serv_time"));
				serviceOrderVO.setServ_count(rs.getInt("serv_count"));
				serviceOrderVO.setTotal_price(rs.getInt("total_price"));
				serviceOrderVO.setLocations(rs.getString("locations"));
				list.add(serviceOrderVO); // Store the row in the list
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
	
	public static void main(String[] args) {
		ServiceOrderDAO_interface dao = new ServiceOrderJDBCDAO();

		// 新增
//		ServiceOrderVO serv1 = new ServiceOrderVO();
//		serv1.setBk_no("MEM0000010");
//		serv1.setOd_status("0");
//		serv1.setRm_no("101");
//		serv1.setServ_no("001");
//		serv1.setServ_time(java.sql.Timestamp.valueOf("2021-01-05 14:00:00"));
//		serv1.setServ_count(5);
//		serv1.setTotal_price(12000);
//		dao.insert(serv1);

		// 修改
//		ServiceOrderVO serv2 = new ServiceOrderVO();
//		serv2.setServ_odno("SERV000005");
//		serv2.setBk_no("MEM0000003");
//		serv2.setOd_status("1");
//		serv2.setRm_no("102");
//		serv2.setServ_no("002");
//		serv2.setServ_time(java.sql.Timestamp.valueOf("2021-01-05 15:00:00"));
//		serv2.setServ_count(4);
//		serv2.setTotal_price(9999);
//		dao.update(serv2);

		// 刪除
//		dao.delete("SERV000005");

		// 查詢
//		ServiceOrderVO serv3 = dao.findByPrimaryKey("SERV000005");
//		System.out.print(serv3.getServ_odno() + ",");
//		System.out.print(serv3.getBk_no() + ",");
//		System.out.print(serv3.getOd_time() + ",");
//		System.out.print(serv3.getOd_status() + ",");
//		System.out.print(serv3.getRm_no() + ",");
//		System.out.print(serv3.getServ_no() + ",");
//		System.out.print(serv3.getServ_time() + ",");
//		System.out.print(serv3.getServ_count() + ",");
//		System.out.println(serv3.getTotal_price());
//		System.out.println("-----------------------------");

		// 查詢
//		List<ServiceOrderVO> list = dao.getAll();
//		for (ServiceOrderVO serv : list) {
//			System.out.print(serv.getServ_odno() + ",");
//			System.out.print(serv.getBk_no() + ",");
//			System.out.print(serv.getOd_time() + ",");
//			System.out.print(serv.getOd_status() + ",");
//			System.out.print(serv.getRm_no() + ",");
//			System.out.print(serv.getServ_no() + ",");
//			System.out.print(serv.getServ_time() + ",");
//			System.out.print(serv.getServ_count() + ",");
//			System.out.print(serv.getTotal_price());
//			System.out.println();
//		}
		
//		List<ServiceOrderVO> list = dao.getAllByBkNo();
//		for (ServiceOrderVO serv : list) {
//			System.out.print(serv.getServ_odno() + ",");
//			System.out.print(serv.getBk_no() + ",");
//			System.out.print(serv.getOd_time() + ",");
//			System.out.print(serv.getOd_status() + ",");
//			System.out.print(serv.getRm_no() + ",");
//			System.out.print(serv.getServ_no() + ",");
//			System.out.print(serv.getServ_time() + ",");
//			System.out.print(serv.getServ_count() + ",");
//			System.out.print(serv.getTotal_price());
//			System.out.println();
//		}
	}

}
