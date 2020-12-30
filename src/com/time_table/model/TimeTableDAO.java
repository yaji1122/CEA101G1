package com.time_table.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TimeTableDAO implements TimeTableDAO_interface {
	
	private static DataSource ds = null;

	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		} catch (NamingException e) {
			e.printStackTrace(System.err);
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO TIME_TABLE (SERV_PERIOD, SERV_NO, MAX_SERV_PPL) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT SERV_PERIOD, SERV_NO, MAX_SERV_PPL FROM TIME_TABLE ORDER BY SERV_NO";
	private static final String GET_ONE_STMT = "SELECT SERV_PERIOD, SERV_NO, MAX_SERV_PPL FROM TIME_TABLE WHERE SERV_NO =? AND SERV_PERIOD =?";
	private static final String DELETE = "DELETE FROM TIME_TABLE WHERE SERV_NO=? AND SERV_PERIOD=?";
	private static final String UPDATE = "UPDATE TIME_TABLE SET MAX_SERV_PPL=? WHERE SERV_NO=? AND SERV_PERIOD=?";

	@Override
	public void insert(TimeTableVO timeTableVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, timeTableVO.getServ_period());
			pstmt.setString(2, timeTableVO.getServ_no());
			pstmt.setInt(3, timeTableVO.getMax_serv_ppl());

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
	public void update(TimeTableVO timeTableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, timeTableVO.getMax_serv_ppl());
			pstmt.setString(2, timeTableVO.getServ_no());
			pstmt.setInt(3, timeTableVO.getServ_period());

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
	public void delete(String serv_no, Integer serv_period) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, serv_no);
			pstmt.setInt(2, serv_period);

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
	public TimeTableVO findByPrimaryKey(String serv_no, Integer serv_period) {
		TimeTableVO timeTableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, serv_no);
	        pstmt.setInt(2, serv_period);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// timeTableVO 也稱為 Domain objects
				timeTableVO = new TimeTableVO();
				timeTableVO.setServ_no(rs.getString("serv_no"));
				timeTableVO.setServ_period(rs.getInt("serv_period"));
				timeTableVO.setMax_serv_ppl(rs.getInt("max_serv_ppl"));
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
		return timeTableVO;
	}

	@Override
	public List<TimeTableVO> getAll() {
		List<TimeTableVO> list = new ArrayList<TimeTableVO>();
		TimeTableVO timeTableVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// timeTableVO 也稱為 Domain objects
				timeTableVO = new TimeTableVO();
				timeTableVO.setServ_no(rs.getString("serv_no"));
				timeTableVO.setServ_period(rs.getInt("serv_period"));
				timeTableVO.setMax_serv_ppl(rs.getInt("max_serv_ppl"));
				list.add(timeTableVO); // Store the row in the list
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

	public static void main(String[] args) {
		TimeTableDAO_interface dao = new TimeTableJDBCDAO();

		// 新增
//		TimeTableVO serv1 = new TimeTableVO();
//		serv1.setServ_period(6);
//		serv1.setServ_no("005");
//		serv1.setMax_serv_ppl(4);
//		dao.insert(serv1);

		// 修改
//		TimeTableVO serv2 = new TimeTableVO();
//		serv2.setServ_no("005");
//		serv2.setServ_period(6);
//		serv2.setMax_serv_ppl(0);
//		dao.update(serv2);

		// 刪除
//		dao.delete("001", 1);

		// 查詢
//		TimeTableVO serv3 = dao.findByPrimaryKey("001");
//		System.out.print(serv3.getServ_period() + ",");
//		System.out.print(serv3.getServ_no() + ",");
//		System.out.println(serv3.getMax_serv_ppl());
//		System.out.println("-----------------------------");

		// 查詢
//		List<TimeTableVO> list = dao.getAll();
//		for (TimeTableVO serv : list) {
//			System.out.print(serv.getServ_period() + ",");
//			System.out.print(serv.getServ_no() + ",");
//			System.out.print(serv.getMax_serv_ppl());
//			System.out.println();
//		}
	}

}
