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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sale_event.model.Sale_eventJDBCDAO;
import com.shop_order_detail.model.Shop_order_detailVO;


public class Shop_orderDAO implements Shop_orderDAO_interface{
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, shop_orderVO.getMb_id());
			pstmt.setString(2, shop_orderVO.getSp_status());
			pstmt.setDouble(3, shop_orderVO.getTotal_price());
			pstmt.setInt(4, shop_orderVO.getPoints_total());
			pstmt.setString(5, shop_orderVO.getRm_no());

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
	public void update(Shop_orderVO shop_orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, shop_orderVO.getSp_status());
			pstmt.setTimestamp(2, shop_orderVO.getSp_dlvr());
			pstmt.setString(3, shop_orderVO.getSp_odno());

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
	public Shop_orderVO findByPrimaryKey(String sp_odno) {

		Shop_orderVO shop_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETSp_odnoByMb_id);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				shop_orderVO = new Shop_orderVO();
				shop_orderVO.setSp_odno(rs.getString("sp_odno"));
				list.add(shop_orderVO); // Store the row in the list
			}
			
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
}
