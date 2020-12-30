package com.auth.model;

import java.sql.*;
import java.util.*;

public class AuthJDBCDAO implements AuthDAO_interface {


	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G1";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
	"INSERT INTO EMP_AUTH (emp_id, func_no) VALUES (?,?)";
	private static final String GET_ALL_STMT =
    "SELECT * FROM EMP_AUTH order by emp_id";
	private static final String GET_ONE_STMT =
	"SELECT emp_id, func_no FROM EMP_AUTH where emp_id=?";
	private static final String DELETE =
	"DELETE FROM EMP_AUTH where emp_id=? and func_no=?";
//	private static final String UPDATE =
//	"UPDATE EMP_AUTH set func_no=? where emp_id=?";
    private static final String GetOneByEmp =
    "SELECT * FROM EMP_AUTH WHERE EMP_ID =?";
	private static final String GetOneByFunc =
    "SELECT * FROM EMP_AUTH WHERE FUNC_NO=?";
	
	@Override
	public List<AuthVO> findByFunc(String func_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthVO> findByEmp(String emp_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void insert(AuthVO authVO) {
		
		Connection con =null;
		PreparedStatement pstmt =null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, authVO.getEmp_id());
			pstmt.setString(2, authVO.getFunc_no());

			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."+e.getMessage());
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
			} 
		}
		
	}
//	@Override
//	public void update(AuthVO authVO) {
//		Connection con =null;
//		PreparedStatement pstmt =null;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid,passwd);
//			pstmt = con.prepareStatement(UPDATE);
//			
//			pstmt.setString(1, authVO.getFunc_no());
//			pstmt.setString(2, authVO.getEmp_id());
//			
//			
//			pstmt.executeUpdate();
//			
//		}catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver."+e.getMessage());
//		}catch (SQLException se) {
//			throw new RuntimeException("A database error occured."+se.getMessage());
//		}finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				}catch(SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				}catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			} 
//		}
//		
//	}
	@Override
	public AuthVO findByPrimaryKey(String emp_id) {
		
		AuthVO authVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, emp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				authVO = new AuthVO();
				authVO.setEmp_id(rs.getString("emp_id"));
				authVO.setFunc_no(rs.getString("func_no"));
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
		return authVO;
	}
	@Override
	public List<AuthVO> getAll() {
		List<AuthVO> list = new ArrayList<AuthVO>();
		AuthVO authVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				authVO = new AuthVO();
				authVO.setEmp_id(rs.getString("emp_id"));
				authVO.setFunc_no(rs.getString("func_no"));
				
				list.add(authVO); 
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
    
	@Override
	public void delete(String emp_id, String func_no) {
    	int updateCount = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 刪除
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, emp_id);
			pstmt.setString(2, func_no);
			updateCount = pstmt.executeUpdate();
		
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除功能編號" + func_no + "時,共有員工" + updateCount
					+ "人同時被刪除");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
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
	public void insert2(AuthVO authVO, Connection con) {
		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, authVO.getEmp_id());
			pstmt.setString(2, authVO.getFunc_no());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-auth");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}
		
	

	public static void main(String[] args) {

		AuthJDBCDAO dao = new AuthJDBCDAO();
      //新增
//		AuthVO authVO1 = new AuthVO();
//		authVO1.setEmp_id("EMP0000010");
//		authVO1.setFunc_no("10");
//		dao.insert(authVO1);
//		System.out.println("新增成功");
//
//		//修改
//		AuthVO authVO2 = new AuthVO();
//		authVO2.setEmp_id("EMP0000001");
//		authVO2.setFunc_no("09");
//		dao.update(authVO2);
//		System.out.println("修改成功");

		//查詢mp_id=?
		AuthVO authVO3 = dao.findByPrimaryKey("EMP0000001");
		System.out.print(authVO3.getEmp_id() + ",");
		System.out.print(authVO3.getFunc_no() + ",");
//		System.out.println("---------------------");

		//查詢全部
//		List<AuthVO> list = dao.getAll();
//		for (AuthVO Auth : list) {
//			System.out.print(Auth.getEmp_id() + ",");
//			System.out.print(Auth.getFunc_no() + ",");
//
//			System.out.println();
			
//			dao.delete("EMP0000001","01");
			
//		}
	}
	
}

