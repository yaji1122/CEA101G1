package com.shop_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sale_event.model.Sale_eventJDBCDAO;
import com.shop_order_detail.model.Shop_order_detailVO;


public class Shop_orderJDBCDAO implements Shop_orderDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "shop";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO SHOP_ORDER (SP_ODNO,MB_ID,SP_STATUS,TOTAL_PRICE,POINTS_TOTAL,RM_NO) VALUES ('SPOD' || LPAD(to_char(SHOPODNO_SEQ.NEXTVAL), 11, '0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT SP_ODNO,MB_ID,to_char(SP_TIME,'yyyy-mm-dd HH24:MI:SS') SP_TIME,SP_STATUS,to_char(SP_DLVR,'yyyy-mm-dd HH24:MI:SS') SP_DLVR,TOTAL_PRICE, POINTS_TOTAL, RM_NO FROM SHOP_ORDER ORDER BY SP_ODNO";
	private static final String GET_ONE_STMT = 
		"SELECT SP_ODNO,MB_ID,to_char(SP_TIME,'yyyy-mm-dd HH24:MI:SS') SP_TIME,SP_STATUS,to_char(SP_DLVR,'yyyy-mm-dd HH24:MI:SS') SP_DLVR,TOTAL_PRICE,POINTS_TOTAL,RM_NO FROM SHOP_ORDER WHERE SP_ODNO = ?";
	private static final String UPDATE = 
		"UPDATE SHOP_ORDER SET SP_STATUS=? ,SP_DLVR=? where SP_ODNO = ?";
	private static final String GETSp_odnoByMb_id = 
		"SELECT SP_ODNO FROM SHOP_ORDER WHERE MB_ID = ? ORDER BY SP_ODNO";
	
	@Override
	public void insert(Shop_orderVO shop_orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, shop_orderVO.getMb_id());
			pstmt.setString(2, shop_orderVO.getSp_status());
			pstmt.setDouble(3, shop_orderVO.getTotal_price());
			pstmt.setInt(4, shop_orderVO.getPoints_total());
			pstmt.setString(5, shop_orderVO.getRm_no());

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
	public void update(Shop_orderVO shop_orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, shop_orderVO.getSp_status());
			pstmt.setTimestamp(2, shop_orderVO.getSp_dlvr());
			pstmt.setString(3, shop_orderVO.getSp_odno());

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
	public Shop_orderVO findByPrimaryKey(String sp_odno) {

		Shop_orderVO shop_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, sp_odno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// order_masterVO 也稱為 Domain objects
				shop_orderVO = new Shop_orderVO();
				shop_orderVO.setSp_odno(rs.getString("sp_odno"));
				shop_orderVO.setMb_id(rs.getString("mb_id"));
				shop_orderVO.setSp_time(rs.getTimestamp("sp_time"));
				shop_orderVO.setSp_status(rs.getString("sp_status"));
				shop_orderVO.setSp_dlvr(rs.getTimestamp("sp_dlvr"));
				shop_orderVO.setTotal_price(rs.getDouble("total_price"));
				shop_orderVO.setPoints_total(rs.getInt("points_total"));
				shop_orderVO.setRm_no(rs.getString("rm_no"));
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
		return shop_orderVO;
	}

	@Override
	public List<Shop_orderVO> getAll() {
		List<Shop_orderVO> list = new ArrayList<Shop_orderVO>();
		Shop_orderVO shop_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Order_masterVO 也稱為 Domain objects
				shop_orderVO = new Shop_orderVO();
				shop_orderVO.setSp_odno(rs.getString("sp_odno"));
				shop_orderVO.setMb_id(rs.getString("mb_id"));
				shop_orderVO.setSp_time(rs.getTimestamp("sp_time"));
				shop_orderVO.setSp_status(rs.getString("sp_status"));
				shop_orderVO.setSp_dlvr(rs.getTimestamp("sp_dlvr"));
				shop_orderVO.setTotal_price(rs.getDouble("total_price"));
				shop_orderVO.setPoints_total(rs.getInt("points_total"));
				shop_orderVO.setRm_no(rs.getString("rm_no"));
				list.add(shop_orderVO); // Store the row in the list
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
	
	@Override
	public List<Shop_orderVO> getSp_odnoByMb_id(String mb_id) {
		
		List<Shop_orderVO> list = new ArrayList<Shop_orderVO>();
		Shop_orderVO shop_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETSp_odnoByMb_id);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				shop_orderVO = new Shop_orderVO();
				shop_orderVO.setSp_odno(rs.getString("sp_odno"));
				list.add(shop_orderVO); // Store the row in the list
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any driver errors
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
		

		Shop_orderJDBCDAO dao = new Shop_orderJDBCDAO();
		
		// 新增
//		Shop_orderVO shop_orderVO1 = new Shop_orderVO();
//		shop_orderVO1.setMb_id("MEM0000001");
//		shop_orderVO1.setSp_status("0");
//		shop_orderVO1.setTotal_price(2000);
//		shop_orderVO1.setPoints_total(2000);
//		shop_orderVO1.setRm_no("203");
//		dao.insert(shop_orderVO1);

		
		

//		SimpleDateFormat sfm1 = new SimpleDateFormat(" yyyy-MM-dd hh:mm:ss");
//		
//		java.util.Date date = sfm1.parse("2020/01/22");
//		new java.sql.Date(date.getTime());
//		java.sql.Timestamp ts = valueOf("2011-05-09 11:49:45");
		
		// 修改
//		Shop_orderVO shop_orderVO2 = new Shop_orderVO();
//		shop_orderVO2.setSp_status("3");
//		shop_orderVO2.setSp_dlvr(Timestamp.valueOf("2011-05-09 11:49:45"));
//		shop_orderVO2.setSp_odno("SPOD00000000001");
//		dao.update(shop_orderVO2);


		// 查詢
//		Shop_orderVO shop_orderVO3 = dao.findByPrimaryKey("SPOD00000000001");
//		System.out.print(shop_orderVO3.getSp_odno() + ",");
//		System.out.print(shop_orderVO3.getMb_id() + ",");
//		System.out.print(shop_orderVO3.getSp_time() + ",");
//		System.out.print(shop_orderVO3.getSp_status() + ",");
//		System.out.print(shop_orderVO3.getSp_dlvr() + ",");
//		System.out.print(shop_orderVO3.getTotal_price() + ",");
//		System.out.print(shop_orderVO3.getPoints_total() + ",");
//		System.out.print(shop_orderVO3.getRm_no());
//		System.out.println("---------------------");

		// 查詢
		List<Shop_orderVO> list = dao.getAll();
		for (Shop_orderVO aShop_order : list) {
			System.out.print(aShop_order.getSp_odno() + ",");
			System.out.print(aShop_order.getMb_id() + ",");
			System.out.print(aShop_order.getSp_time() + ",");
			System.out.print(aShop_order.getSp_status() + ",");
			System.out.print(aShop_order.getSp_dlvr() + ",");
			System.out.print(aShop_order.getTotal_price() + ",");
			System.out.print(aShop_order.getPoints_total() + ",");
			System.out.print(aShop_order.getRm_no());
			System.out.println();
		}
		
		// 查詢
//		List<Shop_orderVO> list = dao.getSp_odnoByMb_id("MEM0000001");
//		for (Shop_orderVO aShop_order : list) {
//			System.out.print(aShop_order.getSp_odno() + ",");
//			System.out.println();
//		}
//	}


	}
	
}
