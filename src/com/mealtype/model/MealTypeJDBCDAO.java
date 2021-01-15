package com.mealtype.model;

import java.util.*;
import java.sql.*;

public class MealTypeJDBCDAO implements MealTypeDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G1";
	String password = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO MEAL_TYPE (MEAL_TYPE_NO, TYPE_NAME) VALUES ('TYP' || LPAD(to_char(MEALTYPE_SEQ.NEXTVAL), 2, '0'), ?)";
	private static final String GET_ALL_STMT = 
			"SELECT MEAL_TYPE_NO, TYPE_NAME FROM MEAL_TYPE ORDER BY MEAL_TYPE_NO";
	private static final String GET_ONE_STMT = 
			"SELECT MEAL_TYPE_NO, TYPE_NAME FROM MEAL_TYPE WHERE MEAL_TYPE_NO= ?";
	private static final String DELETE = 
			"DELETE FROM MEAL_TYPE WHERE MEAL_TYPE_NO = ?";
	private static final String UPDATE = 
			"UPDATE MEAL_TYPE SET TYPE_NAME = ? WHERE MEAL_TYPE_NO =?";

	public void insert(MealTypeVO mealTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, mealTypeVO.getType_name());

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

	public void update(MealTypeVO mealTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, mealTypeVO.getType_name());
			pstmt.setString(2, mealTypeVO.getMeal_type_no());

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

	public void delete(String meal_type_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, meal_type_no);

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

	public MealTypeVO findByPrimaryKey(String meal_type_no) {
		MealTypeVO mealTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, meal_type_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				mealTypeVO = new MealTypeVO();
				mealTypeVO.setMeal_type_no(rs.getString("meal_type_no"));
				mealTypeVO.setType_name(rs.getString("type_name"));
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
		return mealTypeVO;
	}

	public List<MealTypeVO> getAll() {
		List<MealTypeVO> list = new ArrayList<MealTypeVO>();
		MealTypeVO mealTypeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mealTypeVO = new MealTypeVO();
				mealTypeVO.setMeal_type_no(rs.getString("meal_type_no"));
				mealTypeVO.setType_name(rs.getString("type_name"));
				list.add(mealTypeVO);
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
		MealTypeJDBCDAO dao = new MealTypeJDBCDAO();

		// 新增
		MealTypeVO mealTypeVO1 = new MealTypeVO();

		mealTypeVO1.setMeal_type_no("TYP01");
		mealTypeVO1.setType_name("義大利麵");
		dao.insert(mealTypeVO1);

		System.out.println("新增成功");

		// 修改
//		MealTypeVO mealTypeVO2 = new MealTypeVO();
//
//		mealTypeVO2.setMeal_type_no("TYP01");
//		mealTypeVO2.setType_name("牛排");
//		dao.update(mealTypeVO2);
//
//		System.out.println("修改成功");
		
		//刪除
//		dao.delete("TYP01");
//		System.out.println("刪除成功");
		
		//查詢
//		MealTypeVO mealTypeVO3 = dao.findByPrimaryKey("TYP02");
//		System.out.print(mealTypeVO3.getMeal_type_no() + ",");
//		System.out.print(mealTypeVO3.getType_name() + ",");
//		System.out.println();
//		System.out.println("===========================================");

		// 查詢
//		List<MealTypeVO> list = dao.getAll();
//		for (MealTypeVO aMealType : list) {
//			System.out.print(aMealType.getMeal_type_no() + ",");
//			System.out.print(aMealType.getType_name() + ",");
//			System.out.println();
//		}
	}
}

