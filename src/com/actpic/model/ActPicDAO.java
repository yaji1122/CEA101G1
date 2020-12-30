package com.actpic.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


    public class ActPicDAO implements ActPicDAO_interface{
	
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
			"INSERT INTO ACT_PIC (ACT_PIC_NO,ACT_EVENT_NO,ACT_PIC) VALUES (?,?,?)";
	private static final String GET_ALL_STMT =
			"SELECT ACT_PIC_NO,ACT_EVENT_NO,ACT_PIC FROM ACT_PIC order By ACT_PIC_NO ";
	private static final String GET_ONE_STMT = 
			"SELECT ACT_PIC_NO,ACT_EVENT_NO,ACT_PIC FROM ACT_PIC WHERE ACT_PIC_NO = ? ";
	private static final String DELETE = 
			"DELETE FROM ACT_PIC WHERE ACT_PIC_NO = ?";
	private static final String UPDATE = 
			"UPDATE ACT_PIC SET ACT_EVENT_NO=?,ACT_PIC=? WHERE ACT_PIC_NO=? ";

	@Override
	public void insert(ActPicVO actPicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,actPicVO.getActPicNo());
			pstmt.setString(2,actPicVO.getActEventNo());
			pstmt.setBytes(3,actPicVO.getActPic());
			
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
	public void update(ActPicVO actPicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,actPicVO.getActEventNo());
			pstmt.setBytes(2,actPicVO.getActPic());
			pstmt.setString(3,actPicVO.getActPicNo());
			
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
	public void delete(String actPicNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1,actPicNo);
			
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
	public ActPicVO findByPrimaryKey(String actPicNo) {
		ActPicVO actPicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,actPicNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
                actPicVO = new ActPicVO();
				actPicVO.setActPicNo(rs.getString("ACT_PIC_NO"));
				actPicVO.setActEventNo(rs.getString("ACT_EVENT_NO"));
				actPicVO.setActPic(rs.getBytes("ACT_PIC"));
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
		
		return actPicVO;
	}

	@Override
	public List<ActPicVO> getAll() {
		List<ActPicVO> list = new ArrayList<ActPicVO>();
		ActPicVO actPicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				actPicVO = new ActPicVO();
				
				actPicVO.setActPicNo(rs.getString("ACT_PIC_NO"));
				actPicVO.setActEventNo(rs.getString("ACT_EVENT_NO"));
				actPicVO.setActPic(rs.getBytes("ACT_PIC"));
				list.add(actPicVO);
		       		
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
	@Override
	public byte[] getOnePic(String actPicNo) {
		byte[] actpic = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1,actPicNo);
			ResultSet rs = pstmt.executeQuery();
		
			rs.next();
                
		    actpic = rs.getBytes("ACT_PIC");
			
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
		
		return actpic;
		
	}	
	

}
