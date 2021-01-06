package com.mealorder.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.JSONObject;

import com.mealorderdetail.model.MealOrderDetailDAO;
import com.mealorderdetail.model.MealOrderDetailService;
import com.mealorderdetail.model.MealOrderDetailVO;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class MealOrderDAO implements MealOrderDAO_interface {
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
			"INSERT INTO MEAL_ORDER (MEAL_ODNO, BK_NO, RM_NO, TOTAL_PRICE) VALUES ('MEALOD' || LPAD(to_char(MEALODNO_SEQ.NEXTVAL), 4, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT MEAL_ODNO, BK_NO, RM_NO, OD_TIME, TOTAL_PRICE, OD_STATUS FROM MEAL_ORDER ORDER BY MEAL_ODNO DESC";
	private static final String GET_ONE_STMT = 
			"SELECT MEAL_ODNO, BK_NO, RM_NO, OD_TIME, TOTAL_PRICE, OD_STATUS FROM MEAL_ORDER WHERE MEAL_ODNO= ?";
	private static final String DELETE = 
			"DELETE FROM MEAL_ORDER WHERE MEAL_ODNO = ?";
	private static final String UPDATE = 
			"UPDATE MEAL_ORDER SET RM_NO=?, TOTAL_PRICE=?, OD_STATUS=? WHERE MEAL_ODNO =?";

	@Override
	public void insert(MealOrderVO mealOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1,  mealOrderVO.getBk_no());
			pstmt.setString(2, mealOrderVO.getRm_no());
			pstmt.setInt(3, mealOrderVO.getTotal_price());

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
	public void update(MealOrderVO mealOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, mealOrderVO.getRm_no());
			pstmt.setInt(2, mealOrderVO.getTotal_price());
			pstmt.setString(3, mealOrderVO.getOd_status());
			pstmt.setString(4, mealOrderVO.getMeal_odno());

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
	public MealOrderVO findByPrimaryKey(String meal_odno) {
		MealOrderVO mealOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, meal_odno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				mealOrderVO = new MealOrderVO();
				mealOrderVO.setMeal_odno(rs.getString("meal_odno"));
				mealOrderVO.setBk_no(rs.getString("bk_no"));
				mealOrderVO.setRm_no(rs.getString("rm_no"));
				mealOrderVO.setOd_time(rs.getTimestamp("od_time"));
				mealOrderVO.setTotal_price(rs.getInt("total_price"));
				mealOrderVO.setOd_status(rs.getString("od_status"));
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
		return mealOrderVO;
	}

	@Override
	public List<MealOrderVO> getAll() {
		List<MealOrderVO> list = new ArrayList<MealOrderVO>();
		MealOrderVO mealOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				mealOrderVO = new MealOrderVO();
				mealOrderVO.setMeal_odno(rs.getString("meal_odno"));
				mealOrderVO.setBk_no(rs.getString("bk_no"));
				mealOrderVO.setRm_no(rs.getString("rm_no"));
				mealOrderVO.setOd_time(rs.getTimestamp("od_time"));
				mealOrderVO.setTotal_price(rs.getInt("total_price"));
				mealOrderVO.setOd_status(rs.getString("od_status"));
				list.add(mealOrderVO);
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
	
	public void insertWithDetails(MealOrderVO mealOrderVO, List<MealOrderDetailVO> meallist) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			String cols[] = {"MEAL_ODNO"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1,  mealOrderVO.getBk_no());
			pstmt.setString(2, mealOrderVO.getRm_no());
			pstmt.setInt(3, mealOrderVO.getTotal_price());
			
			pstmt.executeUpdate();
			String next_odno = null;
			rs = pstmt.getGeneratedKeys();			
			if(rs.next()) {
				next_odno = rs.getString(1); 
			}
			rs.close();
			MealOrderDetailDAO dao = new MealOrderDetailDAO();
			for(MealOrderDetailVO aDetail : meallist) {
				aDetail.setMeal_odno(next_odno);
				dao.insert(aDetail, con);
			}
			
			con.commit();
			con.setAutoCommit(true);
			System.out.println(meallist.size());
		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
	
}
