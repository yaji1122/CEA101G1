package com.actevent.model;

import java.sql.*;
import java.util.*;

public class ActEventJDBCDAO implements ActEventDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "CEA101G1";
	String password = "123456";
	
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,actEventVO.getActEventNo());
			pstmt.setString(3,actEventVO.getActEventName());
			
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
	public void update(ActEventVO actEventVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(2,actEventVO.getActEventName());
			pstmt.setString(4,actEventVO.getActEventNo());
			
			
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
	public void delete(String actEventNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1,actEventNo);
			
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
	public ActEventVO findByPrimaryKey(String actEventNo) {
		
		ActEventVO actEventVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,actEventNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				actEventVO = new ActEventVO();
				
				actEventVO.setActEventNo(rs.getString("ACT_EVENT_NO"));
				actEventVO.setActEventName(rs.getString("ACT_EVENT_NAME"));
		       		
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				actEventVO = new ActEventVO();
				
				actEventVO.setActEventNo(rs.getString("ACT_EVENT_NO"));
				actEventVO.setActEventName(rs.getString("ACT_EVENT_NAME"));
				list.add(actEventVO);
		       		
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
		ActEventJDBCDAO dao = new ActEventJDBCDAO();
		
		//�s�W
//		ActEventVO actEventVO1 = new ActEventVO();
//		actEventVO1.setActEventNo("91");
//		actEventVO1.setActTypeNo("2");
//		actEventVO1.setActEventName("����");
//		actEventVO1.setActInfo("�w");
//		dao.insert(actEventVO1);
		
		//�ק�
//		ActEventVO actEventVO2 = new ActEventVO();
//		actEventVO2.setActTypeNo("0");
//		actEventVO2.setActEventName("�S��");
//		actEventVO2.setActInfo("�w�w");
//		actEventVO2.setActEventNo("91");
//		dao.update(actEventVO2);
//		
//		//�R��
//		dao.delete("1000011110");
//		
//		//�涵�d��
//		ActEventVO actEventVO3 = dao.findByPrimaryKey("1000000001");
//		System.out.print(actEventVO3.getActEventNo()+",");
//		System.out.print(actEventVO3.getActTypeNo()+",");
//		System.out.print(actEventVO3.getActEventName()+",");
//		System.out.print(actEventVO3.getActInfo()+",");
//		System.out.println("===========================");
//		
//		//�d�ߥ���
//		List<ActEventVO> list = dao.getAll();
//		for(ActEventVO actEvent : list) {
//			System.out.print(actEvent.getActEventNo()+",");
//			System.out.print(actEvent.getActTypeNo()+",");
//			System.out.print(actEvent.getActEventName()+",");
//			System.out.println(actEvent.getActInfo()+",");
//		}
		
	}
}
