package com.mealorderdetail.model;

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

public class MealOrderDetailDAO implements MealOrderDetailDAO_interface{
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
			"INSERT INTO MEAL_ORDER_DETAIL (MEAL_ODNO, MEAL_NO, PRICE, QTY) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT MEAL_ODNO, MEAL_NO, PRICE, QTY FROM MEAL_ORDER_DETAIL ORDER BY MEAL_ODNO";
	private static final String GET_ONE_STMT = 
			"SELECT MEAL_ODNO, MEAL_NO, PRICE, QTY FROM MEAL_ORDER_DETAIL WHERE MEAL_ODNO= ?";
	private static final String DELETE = 
			"DELETE FROM MEAL_ORDER_DETAIL WHERE MEAL_ODNO = ?";
	private static final String UPDATE = 
			"UPDATE MEAL_ORDER_DETAIL SET QTY=? WHERE MEAL_ODNO =? AND MEAL_NO=?";
	
	@Override
	public void insert(MealOrderDetailVO mealOrderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1,  mealOrderDetailVO.getMeal_odno());
			pstmt.setString(2, mealOrderDetailVO.getMeal_no());
			pstmt.setInt(3, mealOrderDetailVO.getPrice());
			pstmt.setInt(4, mealOrderDetailVO.getQty());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(MealOrderDetailVO mealOrderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, mealOrderDetailVO.getQty());
			pstmt.setString(2, mealOrderDetailVO.getMeal_odno());
			pstmt.setString(3,  mealOrderDetailVO.getMeal_no());

			pstmt.executeUpdate();

		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String meal_odno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, meal_odno);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public MealOrderDetailVO findByMealOdno(String meal_odno) {
		MealOrderDetailVO mealOrderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, meal_odno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				mealOrderDetailVO = new MealOrderDetailVO();
				mealOrderDetailVO.setMeal_odno(rs.getString("meal_odno"));
				mealOrderDetailVO.setMeal_no(rs.getString("meal_no"));
				mealOrderDetailVO.setPrice(rs.getInt("price"));
				mealOrderDetailVO.setQty(rs.getInt("qty"));			
			}

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
		return mealOrderDetailVO;
	}

	@Override
	public List<MealOrderDetailVO> getAll() {
		List<MealOrderDetailVO> list = new ArrayList<MealOrderDetailVO>();
		MealOrderDetailVO mealOrderDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mealOrderDetailVO = new MealOrderDetailVO();
				mealOrderDetailVO.setMeal_odno(rs.getString("meal_odno"));
				mealOrderDetailVO.setMeal_no(rs.getString("meal_no"));
				mealOrderDetailVO.setPrice(rs.getInt("price"));
				mealOrderDetailVO.setQty(rs.getInt("qty"));	
				list.add(mealOrderDetailVO);
			}
			
		}  catch (SQLException se) {
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
