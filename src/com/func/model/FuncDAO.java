package com.func.model;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

public class FuncDAO implements FuncDAO_interface{

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
			"INSERT INTO FUNC (func_no, func_name) VALUES (?,?)";
			private static final String GET_ALL_STMT =
		    "SELECT * FROM FUNC order by func_no";
			private static final String GET_ONE_STMT =
			"SELECT func_no, func_name FROM FUNC where func_no=?";
			private static final String UPDATE =
			"UPDATE FUNC set func_name=? where func_no=?";
		    private static final String DELETE =
		    "DELETE FROM FUNC WHERE FUNC_NO=?";
			
			@Override
			public void insert(FuncVO funcVO) {
				
				Connection con = null;
				PreparedStatement pstmt =null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1, funcVO.getFunc_no());
				pstmt.setString(2, funcVO.getFunc_name());
				
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
			public void update(FuncVO funcVO) {
				Connection con = null;
				PreparedStatement pstmt =null;
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(UPDATE);
					
					pstmt.setString(1,funcVO.getFunc_name());
					pstmt.setString(2,funcVO.getFunc_no());
					
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
			public FuncVO findByPrimaryKey(String func_no) {
			
				FuncVO funcVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ONE_STMT);
					
					pstmt.setString(1, func_no);
					
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						
						funcVO = new FuncVO();
						funcVO.setFunc_no(rs.getString("func_no"));
						funcVO.setFunc_name(rs.getString("func_name"));
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
				return funcVO;
			}
			@Override
			public List<FuncVO> getAll() {
				List<FuncVO> list = new ArrayList<FuncVO>();
				
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ALL_STMT);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						FuncVO funcVO =new FuncVO();
						funcVO.setFunc_no(rs.getString("func_no"));
						funcVO.setFunc_name(rs.getString("func_name"));
						list.add(funcVO);
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
			@Override
			public void delete(String func_no) {
				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(DELETE);

					pstmt.setString(1, func_no);

					pstmt.executeUpdate();

					// Handle any driver errors
				} catch (SQLException se) {
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
