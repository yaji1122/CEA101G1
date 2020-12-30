package com.mealorderdetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealOrderDetailJDBCDAO implements MealOrderDetailDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G1";
	String password = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO MEAL_ORDER_DETAIL (MEAL_ODNO, MEAL_NO, PRICE, QTY) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT MEAL_ODNO, MEAL_NO, PRICE, QTY FROM MEAL_ORDER_DETAIL ORDER BY MEAL_ODNO";
	private static final String GET_ONE_STMT = 
			"SELECT MEAL_ODNO, MEAL_NO, PRICE, QTY FROM MEAL_ORDER_DETAIL WHERE MEAL_ODNO= ?";
	private static final String DELETE = 
			"DELETE FROM MEAL_ORDER_DETAIL WHERE MEAL_ODNO = ?";
	private static final String UPDATE = 
			"UPDATE MEAL_ORDER_DETAIL SET PRICE=?, QTY=? WHERE MEAL_ODNO =?";
	
	@Override
	public void insert(MealOrderDetailVO mealOrderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1,  mealOrderDetailVO.getMeal_odno());
			pstmt.setString(2, mealOrderDetailVO.getMeal_no());
			pstmt.setInt(3, mealOrderDetailVO.getPrice());
			pstmt.setInt(4, mealOrderDetailVO.getQty());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, mealOrderDetailVO.getPrice());
			pstmt.setInt(2, mealOrderDetailVO.getQty());
			pstmt.setString(3, mealOrderDetailVO.getMeal_odno());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(String meal_odno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, meal_odno);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	
	public static void main(String[] args) {
		MealOrderDetailJDBCDAO dao = new MealOrderDetailJDBCDAO();
		// 新增
		MealOrderDetailVO mealOrderDetailVO1 = new MealOrderDetailVO();

		mealOrderDetailVO1.setMeal_odno("MEALOD0001");
		mealOrderDetailVO1.setMeal_no("M0001");
		mealOrderDetailVO1.setPrice(10000);
		mealOrderDetailVO1.setQty(5);

		dao.insert(mealOrderDetailVO1);

		System.out.println("新增成功");
		
		//修改
//		MealOrderDetailVO mealOrderDetailVO2 = new MealOrderDetailVO();
//		
//		mealOrderDetailVO2.setMeal_odno("MEALOD0001");
//		mealOrderDetailVO2.setPrice(50000);
//		mealOrderDetailVO2.setQty(10);
//		
//		dao.update(mealOrderDetailVO2);
//		
//		System.out.println("修改成功");
		
		// 刪除
//		dao.delete("MEALOD0004");
//		System.out.println("刪除成功");

		// 查詢
//		MealOrderDetailVO mealOrderDetailVO3 = dao.findByPrimaryKey("MEALOD0001");
//		System.out.print(mealOrderDetailVO3.getMeal_odno() + ",");
//		System.out.print(mealOrderDetailVO3.getMeal_no() + ",");
//		System.out.print(mealOrderDetailVO3.getPrice() + ",");
//		System.out.print(mealOrderDetailVO3.getQty() + ",");
//		System.out.println();
//		System.out.println("===========================================");
//
//		// 查詢
//		List<MealOrderDetailVO> list = dao.getAll();
//		for (MealOrderDetailVO aMealOrderDetail: list) {
//			System.out.print(aMealOrderDetail.getMeal_odno() + ",");
//			System.out.print(aMealOrderDetail.getMeal_no() + ",");
//			System.out.print(aMealOrderDetail.getPrice() + ",");
//			System.out.print(aMealOrderDetail.getQty() + ",");
//			System.out.println();
//		}	
	}
}
