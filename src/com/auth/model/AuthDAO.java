package com.auth.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class AuthDAO implements AuthDAO_interface {

	private static DataSource ds =null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	    private static final String INSERT_STMT = 
	    "INSERT INTO EMP_AUTH (emp_id, func_no) VALUES (?,?)";
	    private static final String GET_ALL_STMT =
	    "SELECT * FROM EMP_AUTH order by emp_id";
	    private static final String GET_ONE_STMT =
	    "SELECT emp_id, func_no FROM EMP_AUTH where emp_id=?";
//	    private static final String UPDATE =
//	    "UPDATE EMP_AUTH set func_no=? where emp_id=?";
	    private static final String GetOneByEmp =
	    "SELECT * FROM EMP_AUTH WHERE EMP_ID =?";
	    private static final String GetOneByFunc =
	    "SELECT * FROM EMP_AUTH WHERE FUNC_NO=?";
		private static final String DELETE =
		"DELETE FROM EMP_AUTH where emp_id=? and func_no=?";
		
	@Override
		public List<AuthVO> findByFunc(String func_no) {
		List<AuthVO> list = new ArrayList<AuthVO>();
		AuthVO authVO = null;

	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	
	  try {
		  con = ds.getConnection();
		  pstmt = con.prepareStatement(GetOneByFunc);
		
		  pstmt.setString(1, func_no);
		  rs = pstmt.executeQuery();
		
	      while(rs.next()) {
			    authVO = new AuthVO();
			    authVO.setEmp_id(rs.getString("emp_id"));
			    authVO.setFunc_no(rs.getString("func_no"));
			    list.add(authVO);
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
		return list;
		}
	@Override
		public List<AuthVO> findByEmp(String emp_id) {
		List<AuthVO> list = new ArrayList<AuthVO>();
		AuthVO authVO = null;

	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	
	  try {
		  con = ds.getConnection();
		  pstmt = con.prepareStatement(GetOneByEmp);
		
		  pstmt.setString(1, emp_id);
		  rs = pstmt.executeQuery();
		
	      while(rs.next()) {
			    authVO = new AuthVO();
			    authVO.setEmp_id(rs.getString("emp_id"));
			    authVO.setFunc_no(rs.getString("func_no"));
			    list.add(authVO);
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
		return list;
		}
	@Override
	    public void insert(AuthVO authVO) {
		   Connection con = null;
		   PreparedStatement pstmt =null;
	   try {
		   con = ds.getConnection();
		   pstmt = con.prepareStatement(INSERT_STMT);
		
		   pstmt.setString(1, authVO.getEmp_id());
		   pstmt.setString(2, authVO.getFunc_no());
		
		   pstmt.executeUpdate();
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
//	    public void update(AuthVO authVO) {
//		    Connection con = null;
//		    PreparedStatement pstmt =null;
//		  try {
//			  con = ds.getConnection();
//			  pstmt = con.prepareStatement(UPDATE);
//			  pstmt.setString(1, authVO.getFunc_no());
//			  pstmt.setString(2, authVO.getEmp_id());
//			
//			  pstmt.executeUpdate();
//		      }catch (SQLException se) {
//			      throw new RuntimeException("A database error occured."+se.getMessage());
//		      }finally {
//		  if (pstmt != null) {
//				try {
//					pstmt.close();
//				}catch(SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//		  if (con != null) {
//				try {
//					con.close();
//				}catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			} 
//		}
//	}
	@Override
	    public AuthVO findByPrimaryKey(String emp_id) {
		    
		    AuthVO authVO = null;
		    Connection con = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		
		  try {
			  con = ds.getConnection();
			  pstmt = con.prepareStatement(GET_ONE_STMT);
			
			  pstmt.setString(1, emp_id);
			  rs = pstmt.executeQuery();
			
		      while(rs.next()) {
				    authVO = new AuthVO();
				    authVO.setEmp_id(rs.getString("emp_id"));
				    authVO.setFunc_no(rs.getString("func_no"));
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				authVO = new AuthVO();
				authVO.setEmp_id(rs.getString("emp_id"));
				authVO.setFunc_no(rs.getString("func_no"));
				
				list.add(authVO); 
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
		return list;
	}
	@Override
	    public void delete(String emp_id, String func_no) {
		int updateCount = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

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
					+ "人被刪除");
			
			// Handle any driver errors
		}  catch (SQLException se) {
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

		PreparedStatement pstmt =null;

		try {

			con = ds.getConnection();
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

}
