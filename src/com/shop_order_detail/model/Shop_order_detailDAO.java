package com.shop_order_detail.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Shop_order_detailDAO implements Shop_order_detailDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO SHOP_ORDER_DETAIL (SP_ODNO,ITEM_NO,QTY,SALE_DISCOUNT,ITEM_PRICE,POINTS) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT SP_ODNO,ITEM_NO,QTY,SALE_DISCOUNT,ITEM_PRICE,POINTS FROM SHOP_ORDER_DETAIL ORDER BY ITEM_NO";
	private static final String GET_ONE_STMT = "SELECT SP_ODNO,ITEM_NO,QTY,SALE_DISCOUNT,ITEM_PRICE,POINTS FROM SHOP_ORDER_DETAIL WHERE SP_ODNO = ? AND ITEM_NO = ?";
	private static final String UPDATE = "UPDATE SHOP_ORDER_DETAIL SET QTY = ? WHERE SP_ODNO = ? AND ITEM_NO = ?";
	private static final String GET_Shop_order_detailBySp_odno = "SELECT * FROM SHOP_ORDER_DETAIL WHERE SP_ODNO = ?";

	@Override
	public void insert(Shop_order_detailVO shop_order_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, shop_order_detailVO.getSp_odno());
			pstmt.setString(2, shop_order_detailVO.getItem_no());
			pstmt.setInt(3, shop_order_detailVO.getQty());
			pstmt.setDouble(4, shop_order_detailVO.getSale_discount());
			pstmt.setDouble(5, shop_order_detailVO.getItem_price());
			pstmt.setInt(6, shop_order_detailVO.getPoints());

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
	public void update(Shop_order_detailVO shop_order_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, shop_order_detailVO.getQty());
			pstmt.setString(2, shop_order_detailVO.getSp_odno());
			pstmt.setString(3, shop_order_detailVO.getItem_no());

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
	public Shop_order_detailVO findByPrimaryKey(String sp_odno, String item_no) {

		Shop_order_detailVO shop_order_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, sp_odno);
			pstmt.setString(2, item_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// order_detailVO 也稱為 Domain objects
				shop_order_detailVO = new Shop_order_detailVO();
				shop_order_detailVO.setSp_odno(rs.getString("sp_odno"));
				shop_order_detailVO.setItem_no(rs.getString("item_no"));
				shop_order_detailVO.setQty(rs.getInt("qty"));
				shop_order_detailVO.setSale_discount(rs.getDouble("sale_discount"));
				shop_order_detailVO.setItem_price(rs.getDouble("item_price"));
				shop_order_detailVO.setPoints(rs.getInt("points"));
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
		return shop_order_detailVO;
	}

	@Override
	public List<Shop_order_detailVO> getAll() {
		List<Shop_order_detailVO> list = new ArrayList<Shop_order_detailVO>();
		Shop_order_detailVO shop_order_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// order_detailVO 也稱為 Domain objects
				shop_order_detailVO = new Shop_order_detailVO();
				shop_order_detailVO.setSp_odno(rs.getString("sp_odno"));
				shop_order_detailVO.setItem_no(rs.getString("item_no"));
				shop_order_detailVO.setQty(rs.getInt("qty"));
				shop_order_detailVO.setSale_discount(rs.getDouble("sale_discount"));
				shop_order_detailVO.setItem_price(rs.getDouble("item_price"));
				shop_order_detailVO.setPoints(rs.getInt("points"));
				list.add(shop_order_detailVO); // Store the row in the list
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

	@Override
	public Set<Shop_order_detailVO> getShop_order_detailBySp_odno_inSet(String sp_odno) {
		Set<Shop_order_detailVO> set = new LinkedHashSet<Shop_order_detailVO>();
		Shop_order_detailVO shop_order_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Shop_order_detailBySp_odno);
			pstmt.setString(1, sp_odno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				shop_order_detailVO = new Shop_order_detailVO();
				shop_order_detailVO.setSp_odno(rs.getString("sp_odno"));
				shop_order_detailVO.setItem_no(rs.getString("item_no"));
				shop_order_detailVO.setQty(rs.getInt("qty"));
				shop_order_detailVO.setSale_discount(rs.getDouble("sale_discount"));
				shop_order_detailVO.setItem_price(rs.getDouble("item_price"));
				shop_order_detailVO.setPoints(rs.getInt("points"));
				set.add(shop_order_detailVO);
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
		return set;
	}

	
	@Override
	public List<Shop_order_detailVO> getShop_order_detailBySp_odno(String sp_odno) {
		List<Shop_order_detailVO> list = new ArrayList<Shop_order_detailVO>();
		Shop_order_detailVO shop_order_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Shop_order_detailBySp_odno);
			pstmt.setString(1, sp_odno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// order_detailVO 也稱為 Domain objects
				shop_order_detailVO = new Shop_order_detailVO();
				shop_order_detailVO.setSp_odno(rs.getString("sp_odno"));
				shop_order_detailVO.setItem_no(rs.getString("item_no"));
				shop_order_detailVO.setQty(rs.getInt("qty"));
				shop_order_detailVO.setSale_discount(rs.getDouble("sale_discount"));
				shop_order_detailVO.setItem_price(rs.getDouble("item_price"));
				shop_order_detailVO.setPoints(rs.getInt("points"));
				list.add(shop_order_detailVO); // Store the row in the list
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

}
