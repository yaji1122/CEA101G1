package com.actevent.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActEventDAO implements ActEventDAO_interface{
	
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
			"INSERT INTO ACT_EVENT (ACT_EVENT_NO,ACT_EVENT_NAME) VALUES(?,?)";
	private static final String GET_ALL_STMT =
			"SELECT ACT_EVENT_NO,ACT_EVENT_NAME FROM ACT_EVENT ORDER BY ACT_EVENT_NO";
	private static final String GET_ONE_STMT = 
			"SELECT ACT_EVENT_NO,ACT_EVENT_NAME FROM ACT_EVENT WHERE ACT_EVENT_NO = ?";
	private static final String DELETE =
			"DELETE FROM ACT_EVENT WHERE ACT_EVENT_NO = ?";
	private static final String UPDATE =
			"UPDATE ACT_EVENT SET ACT_EVENT_NAME=? WHERE ACT_EVENT_NO = ?";

	@Override
	public void insert(ActEventVO actEventVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,actEventVO.getActEventNo());
			pstmt.setString(3,actEventVO.getActEventName());
			
			pstmt.executeUpdate();
		
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
		            +se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
	}

	@Override
	public void update(ActEventVO actEventVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(2,actEventVO.getActEventName());
			pstmt.setString(4,actEventVO.getActEventNo());
			
			pstmt.executeUpdate();
		
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
		            +se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(String actEventNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1,actEventNo);
			
			pstmt.executeUpdate();
		
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
		            +se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public ActEventVO findByPrimaryKey(String actEventNo) {
		ActEventVO actEventVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			
			pstmt.setString(1,actEventNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				actEventVO = new ActEventVO();
				
				actEventVO.setActEventNo(rs.getString("ACT_EVENT_NO"));
				actEventVO.setActEventName(rs.getString("ACT_EVENT_NAME"));
		       		
			}
		
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
		            +se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return actEventVO;
	}

	@Override
	public List<ActEventVO> getAll() {
		List<ActEventVO> list = new ArrayList<ActEventVO>();
		ActEventVO actEventVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				actEventVO = new ActEventVO();
				actEventVO.setActEventNo(rs.getString("ACT_EVENT_NO"));
				actEventVO.setActEventName(rs.getString("ACT_EVENT_NAME"));
				list.add(actEventVO);
			}
		
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
		            +se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
}
