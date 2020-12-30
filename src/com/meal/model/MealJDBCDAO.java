package com.meal.model;

import java.util.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class MealJDBCDAO implements MealDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G1";
	String password = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO MEAL (MEAL_NO, MEAL_TYPE_NO, MEAL_NAME, MEAL_PIC, MEAL_INFO, MAKING_TIME) VALUES ('M' || LPAD(to_char(MEALNO_SEQ.NEXTVAL), 4, '0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT MEAL_NO, MEAL_TYPE_NO, MEAL_NAME, MEAL_PIC, MEAL_INFO, MAKING_TIME, MEAL_STATUS FROM MEAL ORDER BY MEAL_NO";
	private static final String GET_ONE_STMT = 
			"SELECT MEAL_NO, MEAL_TYPE_NO, MEAL_NAME, MEAL_PIC, MEAL_INFO, MAKING_TIME, MEAL_STATUS FROM MEAL WHERE MEAL_NO = ?";
	private static final String DELETE = 
			"DELETE FROM MEAL WHERE MEAL_NO = ?";
	private static final String UPDATE = 
			"UPDATE MEAL SET MEAL_TYPE_NO=?, MEAL_NAME=?, MEAL_PIC=?, MEAL_INFO=?, MAKING_TIME=?, MEAL_STATUS = ? WHERE MEAL_NO=?";
	private static final String GET_ONE_PIC = 
			"SELECT MEAL_PIC FROM MEAL WHERE MEAL_NO=?";
	private static final String GET_ACTIVE_MEAL = 
			"SELECT * FROM MEAL WHERE MEAL_STATUS = 1";
	private static final String LET_MEAL_ON=
			"UPDATE MEAL SET MEAL_STATUS=1 WHERE MEAL_STATUS=0";
	
	public void insert(MealVO mealVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, mealVO.getMeal_type_no());
			pstmt.setString(2, mealVO.getMeal_name());
			pstmt.setBytes(3, mealVO.getMeal_pic());
			pstmt.setString(4, mealVO.getMeal_info());
			pstmt.setInt(5, mealVO.getMaking_time());

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
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
	public void update(MealVO mealVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, mealVO.getMeal_type_no());
			pstmt.setString(2, mealVO.getMeal_name());
			pstmt.setBytes(3, mealVO.getMeal_pic());
			pstmt.setString(4, mealVO.getMeal_info());
			pstmt.setInt(5, mealVO.getMaking_time());
			pstmt.setString(6, mealVO.getMeal_status());
			pstmt.setString(7, mealVO.getMeal_no());

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
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
	
	public void updateMealStatus(String meal_status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(LET_MEAL_ON);
			
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
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
	public void delete(String meal_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, meal_no);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public MealVO findByPrimaryKey(String meal_no) {
		MealVO mealVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, meal_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				mealVO = new MealVO();
				mealVO.setMeal_no(rs.getString("meal_no"));
				mealVO.setMeal_type_no(rs.getString("meal_type_no"));
				mealVO.setMeal_name(rs.getString("meal_name"));
				mealVO.setMeal_info(rs.getString("meal_info"));
				mealVO.setMaking_time(rs.getInt("making_time"));
				mealVO.setMeal_status(rs.getString("meal_status"));				
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return mealVO;
	}

	public List<MealVO> getActiveMeal(String meal_status) {
		List<MealVO> list = new ArrayList<MealVO>();
		MealVO mealVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ACTIVE_MEAL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mealVO = new MealVO();
				mealVO.setMeal_no(rs.getString("meal_no"));
				mealVO.setMeal_type_no(rs.getString("meal_type_no"));
				mealVO.setMeal_name(rs.getString("meal_name"));
				mealVO.setPrice(rs.getInt("price"));
				mealVO.setMeal_info(rs.getString("meal_info"));
				mealVO.setMaking_time(rs.getInt("making_time"));
				mealVO.setMeal_status(rs.getString("meal_status"));				
				list.add(mealVO);
			}
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
	public List<MealVO> getAll() {
		List<MealVO> list = new ArrayList<MealVO>();
		MealVO mealVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mealVO = new MealVO();
				mealVO.setMeal_no(rs.getString("meal_no"));
				mealVO.setMeal_type_no(rs.getString("meal_type_no"));
				mealVO.setMeal_name(rs.getString("meal_name"));
				mealVO.setMeal_info(rs.getString("meal_info"));
				mealVO.setMaking_time(rs.getInt("making_time"));
				mealVO.setMeal_status(rs.getString("meal_status"));				
				list.add(mealVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	public MealVO getOnePic(String meal_no) {
		MealVO mealVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_PIC);

			pstmt.setString(1, meal_no);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				mealVO = new MealVO();
				mealVO.setMeal_pic(rs.getBytes("meal_pic"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return mealVO;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	
	public static void main(String[] args) throws IOException{
		MealJDBCDAO dao = new MealJDBCDAO();
		
		//新增
		MealVO mealVO1 = new MealVO();
		
		mealVO1.setMeal_type_no("TYP01");
		mealVO1.setMeal_name("超好吃義大利麵");
		byte[] pic = getPictureByteArray("C:/Users/CEA101_07/Desktop/CEA101G1/front-end/template/img/card-img.jpg");
		mealVO1.setMeal_pic(pic);
		mealVO1.setMeal_info("真的超好吃！");
		mealVO1.setMaking_time(10);
		
		dao.insert(mealVO1);
		
		System.out.println("新增成功");
		
		//修改
//		MealVO mealVO2 = new MealVO();
//		
//		mealVO2.setMeal_no("M0001");
//		mealVO2.setMeal_type_no("TYP01");
//		mealVO2.setMeal_name("超香義大利麵");
//		byte[] pic = getPictureByteArray("C:/Users/CEA101_07/Desktop/CEA101G1/front-end/template/img/card-img.jpg");
//		mealVO2.setMeal_pic(pic);
//		mealVO2.setMeal_info("真的很好吃！");
//		mealVO2.setMaking_time(5);
//		mealVO2.setMeal_status("1");
//		
//		dao.update(mealVO2);
//		
//		System.out.println("修改成功");
		
		//刪除
//		dao.delete("M0002");
//		System.out.println("刪除成功");
		
		//查詢
//		MealVO mealVO3 = dao.findByPrimaryKey("M0003");
//		System.out.print(mealVO3.getMeal_no() + ",");
//		System.out.print(mealVO3.getMeal_type_no() + ",");
//		System.out.print(mealVO3.getMeal_name() + ",");
//		System.out.print(mealVO3.getMeal_info() + ",");
//		System.out.print(mealVO3.getMaking_time() + ",");
//		System.out.print(mealVO3.getMeal_status() + ",");
//		System.out.println();
//		System.out.println("===========================================");
		
		//查詢
//		List<MealVO> list = dao.getAll();
//		for (MealVO aMeal : list) {
//			System.out.print(aMeal.getMeal_no() + ",");
//			System.out.print(aMeal.getMeal_type_no() + ",");
//			System.out.print(aMeal.getMeal_name() + ",");
//			System.out.print(aMeal.getMeal_info() + ",");
//			System.out.print(aMeal.getMaking_time() + ",");
//			System.out.print(aMeal.getMeal_status() + ",");
//			System.out.println();
//		}
	}
}
