package com.dept.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DeptDAO implements DeptDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds =(DataSource)ctx.lookup("java:comp/env/jdbc/resort");
		}catch (NamingException e){
			e.printStackTrace();
		}
	}
	
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
			Connection con = null;
			PreparedStatement pstmt =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, deptVO.getDept_no());
			pstmt.setString(2, deptVO.getDept());
			
			pstmt.executeUpdate();
			}catch (SQLException se){
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
					}catch(Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			
		}
		@Override
		public void update(DeptVO deptVO) {
			Connection con = null;
			PreparedStatement pstmt =null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, deptVO.getDept());
				pstmt.setString(2, deptVO.getDept_no());
				
				pstmt.executeUpdate();
				
			}catch (SQLException se){
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
					}catch(Exception e) {
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
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setString(1, dept_no);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					deptVO = new DeptVO();
					deptVO.setDept_no(rs.getString("dept_no"));
					deptVO.setDept(rs.getString("dept"));
				}
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured."+ se.getMessage());
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

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					DeptVO deptVO = new DeptVO();
					deptVO.setDept_no(rs.getString("dept_no"));
					deptVO.setDept(rs.getString("dept"));
					list.add(deptVO);
				}
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured."+se.getMessage());
			} finally{
				if (rs != null) {
					try {
						rs.close();
					}catch (SQLException se) {
						se.printStackTrace (System.err);
					}
				}if (pstmt != null) {
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
