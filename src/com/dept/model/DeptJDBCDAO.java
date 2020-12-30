package com.dept.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DeptJDBCDAO implements DeptDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "resort";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
	"INSERT INTO EMP_DEPT (dept_no, dept) VALUES (?,?)";
	private static final String GET_ALL_STMT =
    "SELECT * FROM EMP_DEPT order by dept_no";
	private static final String GET_ONE_STMT =
	"SELECT dept_no, dept FROM EMP_DEPT where dept_no=?";
	private static final String UPDATE =
	"UPDATE EMP_DEPT set dept=? where dept_no=?";
	
	
	@Override
	public void insert(DeptVO deptVO) {
		Connection con =null;
		PreparedStatement pstmt =null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, deptVO.getDept_no());
			pstmt.setString(2, deptVO.getDept());
			
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
	@Override
	public void update(DeptVO deptVO) {
		Connection con =null;
		PreparedStatement pstmt =null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid,passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, deptVO.getDept());
			pstmt.setString(2, deptVO.getDept_no());
			
			
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
	@Override
	public DeptVO findByPrimaryKey(String dept_no) {
		DeptVO deptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, dept_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				deptVO = new DeptVO();
				deptVO.setDept_no(rs.getString("dept_no"));
				deptVO.setDept(rs.getString("dept"));
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
		return deptVO;
	}
	@Override
	public List<DeptVO> getAll() {
		List<DeptVO> list = new ArrayList<DeptVO>();
		DeptVO deptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				deptVO = new DeptVO();
				deptVO.setDept_no(rs.getString("dept_no"));
				deptVO.setDept(rs.getString("dept"));
				
				list.add(deptVO); 
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

	public static void main(String[] args) {

		DeptJDBCDAO dao = new DeptJDBCDAO();
		
		//新增部門
		DeptVO deptVO1 = new DeptVO();
		deptVO1.setDept_no("11");;
		deptVO1.setDept("業務二部");
		dao.insert(deptVO1);
		System.out.println("新增成功");

		//修改部門名稱
		DeptVO deptVO2 = new DeptVO();
		deptVO2.setDept_no("10");
		deptVO2.setDept("人事部門");
		dao.update(deptVO2);
		System.out.println("修改成功");

        //查詢dept_no=?資料
		DeptVO deptVO3 = dao.findByPrimaryKey("10");
		System.out.print(deptVO3.getDept_no() + ",");
		System.out.print(deptVO3.getDept() + ",");
		System.out.println("---------------------");

		//查詢全部部門資料
		List<DeptVO> list = dao.getAll();
		for (DeptVO Dept : list) {
			System.out.print(Dept.getDept_no() + ",");
			System.out.print(Dept.getDept() + ",");

			System.out.println();
		}
	}
	
}
