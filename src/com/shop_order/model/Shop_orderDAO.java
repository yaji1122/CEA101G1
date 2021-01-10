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
import com.shop_order_detail.model.*;

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
		"INSERT INTO SHOP_ORDER (SP_ODNO,MB_ID,TOTAL_PRICE,POINTS_TOTAL,RM_NO) VALUES ('SPOD' || LPAD(to_char(SHOPODNO_SEQ.NEXTVAL), 11, '0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT SP_ODNO,MB_ID,to_char(SP_TIME,'yyyy-mm-dd HH24:MI:SS') SP_TIME,SP_STATUS,to_char(SP_DLVR,'yyyy-mm-dd HH24:MI:SS') SP_DLVR,TOTAL_PRICE, POINTS_TOTAL, RM_NO FROM SHOP_ORDER ORDER BY SP_ODNO DESC";
	private static final String GET_ONE_STMT = 
		"SELECT SP_ODNO,MB_ID,to_char(SP_TIME,'yyyy-mm-dd HH24:MI:SS') SP_TIME,SP_STATUS,to_char(SP_DLVR,'yyyy-mm-dd HH24:MI:SS') SP_DLVR,TOTAL_PRICE,POINTS_TOTAL,RM_NO FROM SHOP_ORDER WHERE SP_ODNO = ?";
	private static final String UPDATE = 
		"UPDATE SHOP_ORDER SET SP_STATUS=? ,SP_DLVR=? where SP_ODNO = ?";
	private static final String GETSp_odnoByMb_id = 
		"SELECT * FROM SHOP_ORDER WHERE MB_ID = ? ORDER BY SP_ODNO DESC";
	
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
	
	public void insertWithShop_order_details(Shop_orderVO shop_orderVO , List<Shop_order_detailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增訂單主檔
			String cols[] = {"sp_odno"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);
			
			pstmt.setString(1, shop_orderVO.getMb_id());
			pstmt.setDouble(2, shop_orderVO.getTotal_price());
			pstmt.setInt(3, shop_orderVO.getPoints_total());
			pstmt.setString(4, shop_orderVO.getRm_no());
						
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_sp_odno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_sp_odno = rs.getString(1); //第1欄位
				System.out.println("自增主鍵值= " + next_sp_odno);
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增訂單明細
//			String sale_discount = null;
//			if() {
//				
//			} else {
//				
//			}
			Shop_order_detailDAO dao = new Shop_order_detailDAO();
			System.out.println("list.size()(執行前)="+list.size());
			for (Shop_order_detailVO newShop_order_detail : list) {
				newShop_order_detail.setSp_odno(next_sp_odno);
				dao.insertWhenShop_orderInsert(newShop_order_detail,con);
			}
			System.out.println("======");
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("新增訂單主檔編號" + next_sp_odno + " ,共有訂單明細" + list.size()
					+ "同時被新增");

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("訂單主檔錯誤，rolled back");
					se.printStackTrace();
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
	
}
