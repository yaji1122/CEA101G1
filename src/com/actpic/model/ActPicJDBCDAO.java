package com.actpic.model;

import java.sql.*;
import java.util.*;


public class ActPicJDBCDAO implements ActPicDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "CEA101G1";
	String password = "123456";
	
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,actPicVO.getActPicNo());
			pstmt.setString(2,actPicVO.getActEventNo());
			pstmt.setBytes(3,actPicVO.getActPic());
			
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,actPicVO.getActEventNo());
			pstmt.setBytes(2,actPicVO.getActPic());
			pstmt.setString(3,actPicVO.getActPicNo());
			
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1,actPicNo);
			
			
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,actPicNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
                actPicVO = new ActPicVO();
				actPicVO.setActPicNo(rs.getString("ACT_PIC_NO"));
				actPicVO.setActEventNo(rs.getString("ACT_EVENT_NO"));
		       		
			}
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				actPicVO = new ActPicVO();
				
				actPicVO.setActPicNo(rs.getString("ACT_PIC_NO"));
				actPicVO.setActEventNo(rs.getString("ACT_EVENT_NO"));
				list.add(actPicVO);
		       		
			}
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+e.getMessage());
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
	
	public static void main(String[] args) {
		ActPicJDBCDAO dao = new  ActPicJDBCDAO();
		
		//新增
//		ActPicVO actPicVO1 = new ActPicVO();
//		actPicVO1.setActPicNo("00010");
//		actPicVO1.setActEventNo("90");
//		actPicVO1.setActPic(null);
//		dao.insert(actPicVO1);
		
		//修改--(無效)
//		ActPicVO actPicVO2 = new ActPicVO();
//		actPicVO2.setActPicNo("00010");
//		actPicVO2.setActEventNo("90");
//		actPicVO2.setActPic(null);
//		dao.update(actPicVO2);
		
		//刪除
//		dao.delete("10009");
		
		//單項查詢
//		ActPicVO actPicVO3 = dao.findByPrimaryKey("00001");
//		System.out.print(actPicVO3.getActPicNo()+",");
//		System.out.print(actPicVO3.getActEventNo()+",");
//		System.out.print(actPicVO3.getActPicNo()+",");
//		System.out.println("===========================");
		
		//查詢全部
//		List<ActPicVO> list = dao.getAll();
//		for(ActPicVO actPic : list) {
//			System.out.print(actPic.getActPicNo()+",");
//			System.out.print(actPic.getActEventNo()+",");
//			System.out.print(actPic.getActPicNo()+",");
//		}
		
	}
	@Override
	public byte[] getOnePic(String actPicNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
