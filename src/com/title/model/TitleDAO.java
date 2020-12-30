package com.title.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class TitleDAO implements TitleDAO_interface {

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
	"INSERT INTO EMP_TITLE (title_no, title) VALUES (?,?)";
	private static final String GET_ALL_STMT =
    "SELECT * FROM EMP_TITLE order by title_no";
	private static final String GET_ONE_STMT =
	"SELECT title_no, title FROM EMP_TITLE where title_no=?";
//	private static final String DELETE =
//	"DELETE FROM FUNC where title_no=?";
	private static final String UPDATE =
	"UPDATE EMP_TITLE set title=? where title_no=?";
	@Override
	public void insert(TitleVO titleVO) {
		Connection con = null;
		PreparedStatement pstmt =null;
	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(INSERT_STMT);
		
		pstmt.setString(1, titleVO.getTitle_no());
		pstmt.setString(2, titleVO.getTitle());
		
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
	public void update(TitleVO titleVO) {
		Connection con = null;
		PreparedStatement pstmt =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,titleVO.getTitle());
			pstmt.setString(2,titleVO.getTitle_no());
			
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
	public TitleVO findByPrimaryKey(String title_no) {
		TitleVO titleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, title_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				titleVO = new TitleVO();
				titleVO.setTitle_no(rs.getString("title_no"));
				titleVO.setTitle(rs.getString("title"));
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
		return titleVO;
	}
	@Override
	public List<TitleVO> getAll() {
		List<TitleVO> list = new ArrayList<TitleVO>();
		TitleVO titleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				titleVO =new TitleVO();
				titleVO.setTitle_no(rs.getString("title_no"));
				titleVO.setTitle(rs.getString("title"));
				list.add(titleVO);
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
