package com.item.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ItemDAO implements ItemDAO_interface {
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
	private static final String INSERT_STMT = "INSERT INTO ITEM (item_no,item_name,item_type_no,item_price,item_status,item_on_sale,item_detail,points) VALUES ('I'||LPAD(to_char(ITEM_SEQ.NEXTVAL), 4, '0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT item_no , item_name, item_type_no, item_price, item_renew, item_status, item_on_sale, item_detail, points FROM item";
	private static final String GET_ONE_STMT = "SELECT item_no , item_name, item_type_no, item_price, item_renew, item_status, item_on_sale, item_detail, points FROM item where item_no = ?";
	private static final String GET_ALL_STMT_TYPE_NO = "SELECT item_no , item_name, item_type_no, item_price, item_renew, item_status, item_on_sale, item_detail, points FROM item WHERE ITEM_TYPE_NO=?";
	private static final String UPDATE = "UPDATE item set item_name = ?, item_type_no = ?, item_price = ?, item_status = ?, item_on_sale = ?, item_detail = ?, points = ? where item_no = ?";
	private static final String GET_ALL_TYPE_NO_BY_ST = "SELECT item_no , item_name, item_type_no, item_price, item_renew, item_status, item_on_sale, item_detail, points FROM item WHERE ITEM_TYPE_NO=? AND ITEM_STATUS <> '0'";
	private static final String GET_ALL_STMT_BY_ST = "SELECT item_no , item_name, item_type_no, item_price, item_renew, item_status, item_on_sale, item_detail, points FROM item WHERE ITEM_STATUS <> '0'";
	
	@Override
	public void insert(ItemVO itemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, itemVO.getItem_name());
			pstmt.setString(2, itemVO.getItem_type_no());
			pstmt.setDouble(3, itemVO.getItem_price());
			pstmt.setString(4, itemVO.getItem_status());
			pstmt.setString(5, itemVO.getItem_on_sale());
			pstmt.setString(6, itemVO.getItem_detail());
			pstmt.setInt(7, itemVO.getPoints());

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
	public void update(ItemVO itemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, itemVO.getItem_name());
			pstmt.setString(2, itemVO.getItem_type_no());
			pstmt.setDouble(3, itemVO.getItem_price());
			pstmt.setString(4, itemVO.getItem_status());
			pstmt.setString(5, itemVO.getItem_on_sale());
			pstmt.setString(6, itemVO.getItem_detail());
			pstmt.setInt(7, itemVO.getPoints());
			pstmt.setString(8, itemVO.getItem_no());

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
	public ItemVO findByPrimaryKey(String item_no) {

		ItemVO itemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, item_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				itemVO = new ItemVO();
				itemVO.setItem_no(rs.getString("item_no"));
				itemVO.setItem_name(rs.getString("item_name"));
				itemVO.setItem_type_no(rs.getString("item_type_no"));
				itemVO.setItem_price(rs.getDouble("item_price"));
				itemVO.setItem_renew(rs.getTimestamp("item_renew"));
				itemVO.setItem_status(rs.getString("item_status"));
				itemVO.setItem_on_sale(rs.getString("item_on_sale"));
				itemVO.setItem_detail(rs.getString("item_detail"));
				itemVO.setPoints(rs.getInt("points"));

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
		return itemVO;
	}

	@Override
	public List<ItemVO> getAll() {
		List<ItemVO> list = new ArrayList<ItemVO>();
		ItemVO itemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itemVO = new ItemVO();
				itemVO.setItem_name(rs.getString("item_name"));
				itemVO.setItem_type_no(rs.getString("item_type_no"));
				itemVO.setItem_price(rs.getDouble("item_price"));
				itemVO.setItem_renew(rs.getTimestamp("item_renew"));
				itemVO.setItem_status(rs.getString("item_status"));
				itemVO.setItem_on_sale(rs.getString("item_on_sale"));
				itemVO.setItem_detail(rs.getString("item_detail"));
				itemVO.setPoints(rs.getInt("points"));
				itemVO.setItem_no(rs.getString("item_no"));
				list.add(itemVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public List<ItemVO> getAllByStatus() {
		List<ItemVO> list = new ArrayList<ItemVO>();
		ItemVO itemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_BY_ST);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itemVO = new ItemVO();
				itemVO.setItem_name(rs.getString("item_name"));
				itemVO.setItem_type_no(rs.getString("item_type_no"));
				itemVO.setItem_price(rs.getDouble("item_price"));
				itemVO.setItem_renew(rs.getTimestamp("item_renew"));
				itemVO.setItem_status(rs.getString("item_status"));
				itemVO.setItem_on_sale(rs.getString("item_on_sale"));
				itemVO.setItem_detail(rs.getString("item_detail"));
				itemVO.setPoints(rs.getInt("points"));
				itemVO.setItem_no(rs.getString("item_no"));
				list.add(itemVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	public List<ItemVO> getAllByItem_type_no(String item_type_no) {

		List<ItemVO> list = new ArrayList<ItemVO>();
		ItemVO itemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_TYPE_NO);
			pstmt.setString(1, item_type_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itemVO = new ItemVO();
				itemVO.setItem_name(rs.getString("item_name"));
				itemVO.setItem_type_no(rs.getString("item_type_no"));
				itemVO.setItem_price(rs.getDouble("item_price"));
				itemVO.setItem_renew(rs.getTimestamp("item_renew"));
				itemVO.setItem_status(rs.getString("item_status"));
				itemVO.setItem_on_sale(rs.getString("item_on_sale"));
				itemVO.setItem_detail(rs.getString("item_detail"));
				itemVO.setPoints(rs.getInt("points"));
				itemVO.setItem_no(rs.getString("item_no"));
				list.add(itemVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public List<ItemVO> getAllByItem_type_noByStatus(String item_type_no) {
		List<ItemVO> list = new ArrayList<ItemVO>();
		ItemVO itemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_TYPE_NO_BY_ST);
			pstmt.setString(1, item_type_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itemVO = new ItemVO();
				itemVO.setItem_name(rs.getString("item_name"));
				itemVO.setItem_type_no(rs.getString("item_type_no"));
				itemVO.setItem_price(rs.getDouble("item_price"));
				itemVO.setItem_renew(rs.getTimestamp("item_renew"));
				itemVO.setItem_status(rs.getString("item_status"));
				itemVO.setItem_on_sale(rs.getString("item_on_sale"));
				itemVO.setItem_detail(rs.getString("item_detail"));
				itemVO.setPoints(rs.getInt("points"));
				itemVO.setItem_no(rs.getString("item_no"));
				list.add(itemVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
