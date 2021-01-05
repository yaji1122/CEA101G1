package com.mealorder.model;

import java.util.*;

import com.mealorderdetail.model.MealOrderDetailDAO;
import com.mealorderdetail.model.MealOrderDetailVO;

import java.sql.*;

public class MealOrderJDBCDAO implements MealOrderDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G1";
	String password = "123456";

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1,  mealOrderVO.getBk_no());
			pstmt.setString(2, mealOrderVO.getRm_no());
			pstmt.setInt(3, mealOrderVO.getTotal_price());

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
	public void insertWithDetails(MealOrderVO mealOrderVO, List<MealOrderDetailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			con.setAutoCommit(false);
			
			String cols[] = {"MEAL_ODNO"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1,  mealOrderVO.getBk_no());
			pstmt.setString(2, mealOrderVO.getRm_no());
			pstmt.setInt(3, mealOrderVO.getTotal_price());
			
			pstmt.executeUpdate();
			
			String next_mealodno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				next_mealodno = rs.getString(1);
			}
			rs.close();
			MealOrderDetailDAO dao = new MealOrderDetailDAO();
			for(MealOrderDetailVO aDetail : list) {
				aDetail.setMeal_odno(next_mealodno);
				dao.insert(aDetail, con);
			}
			
			con.commit();
			con.setAutoCommit(true);
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		}catch (SQLException se) {
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

	@Override
	public void update(MealOrderVO mealOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, mealOrderVO.getRm_no());
			pstmt.setInt(2, mealOrderVO.getTotal_price());
			pstmt.setString(3, mealOrderVO.getOd_status());
			pstmt.setString(4, mealOrderVO.getMeal_odno());

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
	public MealOrderVO findByPrimaryKey(String meal_odno) {
		MealOrderVO mealOrderVO = null;
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
				mealOrderVO = new MealOrderVO();
				mealOrderVO.setMeal_odno(rs.getString("meal_odno"));
				mealOrderVO.setBk_no(rs.getString("bk_no"));
				mealOrderVO.setRm_no(rs.getString("rm_no"));
				mealOrderVO.setOd_time(rs.getTimestamp("od_time"));
				mealOrderVO.setTotal_price(rs.getInt("total_price"));
				mealOrderVO.setOd_status(rs.getString("od_status"));
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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
		MealOrderJDBCDAO dao = new MealOrderJDBCDAO();
		// 新增
//		MealOrderVO mealOrderVO1 = new MealOrderVO();
//
//		mealOrderVO1.setMb_id("MEM0000001");
//		mealOrderVO1.setRm_no("101");
//		mealOrderVO1.setTotal_price(20000);
//
//		dao.insert(mealOrderVO1);
//
//		System.out.println("新增成功");
		
		//修改
//		MealOrderVO mealOrderVO2 = new MealOrderVO();
//		
//		mealOrderVO2.setMeal_odno("MEALOD0001");
//		mealOrderVO2.setRm_no("101");
//		mealOrderVO2.setTotal_price(50000);
//		mealOrderVO2.setOd_status("3");
//		
//		dao.update(mealOrderVO2);
//		
//		System.out.println("修改成功");
		
		// 刪除
//		dao.delete("MEALOD0013");
//		System.out.println("刪除成功");

		// 查詢
//		MealOrderVO mealOrderVO3 = dao.findByPrimaryKey("MEALOD0001");
//		System.out.print(mealOrderVO3.getMeal_odno() + ",");
//		System.out.print(mealOrderVO3.getMb_id() + ",");
//		System.out.print(mealOrderVO3.getRm_no() + ",");
//		System.out.print(mealOrderVO3.getOd_time() + ",");
//		System.out.print(mealOrderVO3.getTotal_price() + ",");
//		System.out.print(mealOrderVO3.getOd_status() + ",");
//		System.out.println();
//		System.out.println("===========================================");
		
		// 查詢
//		List<MealOrderVO> list = dao.getAll();
//		for (MealOrderVO aMealOrder: list) {
//			System.out.print(aMealOrder.getMeal_odno() + ",");
//			System.out.print(aMealOrder.getMb_id() + ",");
//			System.out.print(aMealOrder.getRm_no() + ",");
//			System.out.print(aMealOrder.getOd_time() + ",");
//			System.out.print(aMealOrder.getTotal_price() + ",");
//			System.out.print(aMealOrder.getOd_status() + ",");
//			System.out.println();
//		}	
	}
}
