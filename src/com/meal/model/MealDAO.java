package com.meal.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class MealDAO implements MealDAO_interface {
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
			"INSERT INTO MEAL (MEAL_NO, MEAL_TYPE_NO, MEAL_NAME, PRICE, MEAL_PIC, MEAL_INFO, MAKING_TIME) VALUES ('M' || LPAD(to_char(MEALNO_SEQ.NEXTVAL), 4, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT MEAL_NO, MEAL_TYPE_NO, MEAL_NAME, PRICE, MEAL_PIC, MEAL_INFO, MAKING_TIME, MEAL_STATUS FROM MEAL ORDER BY MEAL_NO";
	private static final String GET_ONE_STMT = 
			"SELECT MEAL_NO, MEAL_TYPE_NO, MEAL_NAME, PRICE, MEAL_PIC, MEAL_INFO, MAKING_TIME, MEAL_STATUS FROM MEAL WHERE MEAL_NO = ?";
	private static final String DELETE = 
			"DELETE FROM MEAL WHERE MEAL_NO = ?";
	private static final String UPDATE = 
			"UPDATE MEAL SET MEAL_TYPE_NO=?, MEAL_NAME=?, PRICE=?, MEAL_PIC=?, MEAL_INFO=?, MAKING_TIME=?, MEAL_STATUS = ? WHERE MEAL_NO=?";
	private static final String GET_ONE_PIC = 
			"SELECT MEAL_PIC FROM MEAL WHERE MEAL_NO=?";
	private static final String GET_ACTIVE_MEAL = 
			"SELECT * FROM MEAL WHERE MEAL_STATUS = 1";
	private static final String LET_MEAL_ON=
			"UPDATE MEAL SET MEAL_STATUS=1 WHERE MEAL_STATUS=0";
	private static final String UPDATE_ON_MEALSTATUS=
			"UPDATE MEAL SET MEAL_STATUS=1 WHERE MEAL_NO=?";
	private static final String UPDATE_OFF_MEALSTATUS=
			"UPDATE MEAL SET MEAL_STATUS=0 WHERE MEAL_NO=?";



	public void insert(MealVO mealVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, mealVO.getMeal_type_no());
			pstmt.setString(2, mealVO.getMeal_name());
			pstmt.setInt(3, mealVO.getPrice());
			pstmt.setBytes(4, mealVO.getMeal_pic());
			pstmt.setString(5, mealVO.getMeal_info());
			pstmt.setInt(6, mealVO.getMaking_time());

			pstmt.executeUpdate();
			
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, mealVO.getMeal_type_no());
			pstmt.setString(2, mealVO.getMeal_name());
			pstmt.setInt(3, mealVO.getPrice());
			pstmt.setBytes(4, mealVO.getMeal_pic());
			pstmt.setString(5, mealVO.getMeal_info());
			pstmt.setInt(6, mealVO.getMaking_time());
			pstmt.setString(7, mealVO.getMeal_status());
			pstmt.setString(8, mealVO.getMeal_no());

			pstmt.executeUpdate();
			
		}catch (SQLException se) {
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(LET_MEAL_ON);
			
			pstmt.executeUpdate();
			
		}catch(SQLException se) {
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
	public void updateOnMealStatus(MealVO mealVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ON_MEALSTATUS);
			
			pstmt.setString(1, mealVO.getMeal_no());
			
			pstmt.executeUpdate();
			
		}catch(SQLException se) {
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

	public void updateOffMealStatus(MealVO mealVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_OFF_MEALSTATUS);
			
			pstmt.setString(1, mealVO.getMeal_no());
			
			pstmt.executeUpdate();
			
		}catch(SQLException se) {
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, meal_no);

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, meal_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				mealVO = new MealVO();
				mealVO.setMeal_no(rs.getString("meal_no"));
				mealVO.setMeal_type_no(rs.getString("meal_type_no"));
				mealVO.setMeal_name(rs.getString("meal_name"));
				mealVO.setPrice(rs.getInt("price"));
				mealVO.setMeal_info(rs.getString("meal_info"));
				mealVO.setMaking_time(rs.getInt("making_time"));
				mealVO.setMeal_status(rs.getString("meal_status"));				
			}

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
	
	@Override
	public List<MealVO> getActiveMeal(String meal_status) {
		List<MealVO> list = new ArrayList<MealVO>();
		MealVO mealVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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

		}catch (SQLException se) {
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PIC);

			pstmt.setString(1, meal_no);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				mealVO = new MealVO();
				mealVO.setMeal_pic(rs.getBytes("meal_pic"));
			}

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
}
