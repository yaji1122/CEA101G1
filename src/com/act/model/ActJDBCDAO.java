package com.act.model;

import java.sql.*;
import java.util.*;



public class ActJDBCDAO implements ActDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "CEA101G1";
	String password = "123456";
	
	private static final String INSERT_STMT =
			"INSERT INTO ACT (ACT_NO,ACT_EVENT_NO,ACT_NAME,ACT_STATUS,ACT_REG_TIME,ACT_DATE,DEADLINE,ACT_TIME,PARTICIPANT,ACT_PRICE,ACT_PIC,ACT_INFO)"
			+ "VALUES (('ACT' || LPAD(to_char(ACTNO_SEQ.NEXTVAL), 7, '0'),?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String GET_ALL_STMT =
			"SELECT ACT_NO,ACT_EVENT_NO,ACT_NAME,ACT_STATUS,TO_CHAR(ACT_REG_TIME,'yyyy-mm-dd')ACT_REG_TIME,"
			+ "TO_CHAR(ACT_DATE,'yyyy-mm-dd')ACT_DATE,TO_CHAR(DEADLINE,'yyyy-mm-dd')DEADLINE,ACT_TIME," + 
			"PARTICIPANT,ACT_PRICE,ACT_PRICE,ACT_PIC,ACT_INFO FROM ACT order By ACT_NO";
	
	private static final String GET_ONE_STMT = 
			"SELECT ACT_NO,ACT_EVENT_NO,ACT_NAME,ACT_STATUS,TO_CHAR(ACT_REG_TIME,'yyyy-mm-dd')ACT_REG_TIME,"
			+ "TO_CHAR(ACT_DATE,'yyyy-mm-dd')ACT_DATE,TO_CHAR(DEADLINE,'yyyy-mm-dd')DEADLINE,ACT_TIME" + 
			",PARTICIPANT,ACT_PRICE,ACT_PRICE,ACT_PIC,ACT_INFO FROM ACT where ACT_NO=?";
	
	private static final String DELETE =
			"DELETE FROM ACT WHERE ACT_NO = ?";
	
	private static final String UPDATE =
			"UPDATE ACT set ACT_EVENT_NO=?,ACT_NAME=?,ACT_STATUS=?,ACT_REG_TIME=?,ACT_DATE=?,"
			+ "DEADLINE=?,ACT_TIME=?,PARTICIPANT=?,ACT_PRICE=?,ACT_PRICE,ACT_PIC=?,ACT_INFO=? where ACT_NO=?";
	
	       
	
	
	@Override
	public void insert(ActVO actVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,actVO.getActEventNo());
			pstmt.setString(2,actVO.getActName());
			pstmt.setString(3,actVO.getActStatus());
			pstmt.setDate(4,actVO.getActRegTime());
			pstmt.setDate(5,actVO.getActDate());
			pstmt.setDate(6,actVO.getDeadLine());
			pstmt.setString(7,actVO.getActTime());
			pstmt.setString(8,actVO.getParticipant());
			pstmt.setInt(9,actVO.getActPrice());
			pstmt.setBytes(10, actVO.getActPic());
			pstmt.setString(11, actVO.getActInfo());
			
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
	public void update(ActVO actVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1,actVO.getActEventNo());
			pstmt.setString(2,actVO.getActName());
			pstmt.setString(3,actVO.getActStatus());
			pstmt.setDate(4,actVO.getActRegTime());
			pstmt.setDate(5,actVO.getActDate());
			pstmt.setDate(6,actVO.getDeadLine());
			pstmt.setString(7,actVO.getActTime());
			pstmt.setString(8,actVO.getParticipant());
			pstmt.setInt(9,actVO.getActPrice());
			pstmt.setBytes(10, actVO.getActPic());
			pstmt.setString(11, actVO.getActInfo());
			pstmt.setString(12,actVO.getActNo());
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
	public void delete(String actNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1,actNo);
			
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
	public ActVO findByPrimaryKey(String actNo) {
		ActVO actVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,actNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				actVO = new ActVO();
				actVO.setActNo(rs.getString("ACT_NO"));
				actVO.setActEventNo(rs.getString("ACT_EVENT_NO"));
				actVO.setActName(rs.getString("ACT_NAME"));
				actVO.setActStatus(rs.getString("ACT_STATUS"));
				actVO.setActRegTime(rs.getDate("ACT_REG_TIME"));
				actVO.setActDate(rs.getDate("ACT_DATE"));
				actVO.setDeadLine(rs.getDate("DEADLINE"));
				actVO.setActTime(rs.getString("ACT_TIME"));
				actVO.setParticipant(rs.getString("PARTICIPANT"));
				actVO.setActPrice(rs.getInt("ACT_PRICE"));
				actVO.setActPic(rs.getBytes("ACT_PIC"));
				actVO.setActInfo(rs.getString("ACT_INFO"));
		       		
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
		
		return actVO;
	}
	@Override
	public List<ActVO> getAll() {
		List<ActVO> list = new ArrayList<ActVO>();
		ActVO actVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				actVO = new ActVO();
				actVO.setActNo(rs.getString("ACT_NO"));
				actVO.setActEventNo(rs.getString("ACT_EVENT_NO"));
				actVO.setActName(rs.getString("ACT_NAME"));
				actVO.setActStatus(rs.getString("ACT_STATUS"));
				actVO.setActRegTime(rs.getDate("ACT_REG_TIME"));
				actVO.setActDate(rs.getDate("ACT_DATE"));
				actVO.setDeadLine(rs.getDate("DEADLINE"));
				actVO.setActTime(rs.getString("ACT_TIME"));
				actVO.setParticipant(rs.getString("PARTICIPANT"));
				actVO.setActPrice(rs.getInt("ACT_PRICE"));
				actVO.setActPic(rs.getBytes("ACT_PIC"));
				actVO.setActInfo(rs.getString("ACT_INFO"));
				list.add(actVO);
		       		
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
		ActJDBCDAO dao = new  ActJDBCDAO();
		
		//�s�W
		ActVO actVO1 = new ActVO();
//		actVO1.setActEventNo("11");
//		actVO1.setActName("10");
//		actVO1.setActStatus("0");
//		actVO1.setActRegTime(java.sql.Date.valueOf("2020-12-09"));
//		actVO1.setActDate(java.sql.Date.valueOf("2020-12-15"));
//		actVO1.setDeadLine(java.sql.Date.valueOf("2020-12-25"));
//		actVO1.setActTime("1800");
//		actVO1.setParticipant("NJJKK");
//		actVO1.setActPrice(1000);
//		actVO1.setActPic(null);
//		actVO1.setActInfo(null);
//		dao.insert(actVO1);
				
		// �ק�
//		ActVO actVO2 = new ActVO();
//		actVO2.setActNo("ACT0000001");
//		actVO2.setActEventNo("10");
//		actVO2.setActName("AAAA");
//		actVO2.setActStatus("0");
//		actVO2.setActRegTime(java.sql.Date.valueOf("2020-12-10"));
//		actVO2.setActDate(java.sql.Date.valueOf("2020-12-16"));
//		actVO2.setDeadLine(java.sql.Date.valueOf("2020-12-26"));
//		actVO2.setActTime("1000");
//		actVO2.setParticipant("NURAF");
//		actVO2.setActPrice(2000);
//		actVO2.setActPic(null);
//		actVO2.setActInfo(null);
//		dao.update(actVO2);		
				
		// �R��
//		dao.delete("1000000012");
				
		//�涵�d��		
//		ActVO actVO3 = dao.findByPrimaryKey("1000000004");
//		System.out.print(actVO3.getActNo() + ",");
//		System.out.print(actVO3.getActName() + ",");
//		System.out.print(actVO3.getActStatus() + ",");
//		System.out.print(actVO3.getActRegTime() + ",");
//		System.out.print(actVO3.getActDate() + ",");
//		System.out.print(actVO3.getDeadLine() + ",");
//		System.out.print(actVO3.getActTime());
//		System.out.print(actVO3.getParticipant());
//		System.out.print(actVO3.getActPrice());
//		System.out.println("---------------------");
//		
		// �d��
//		List<ActVO> list = dao.getAll();
//			for (ActVO actVO : list) {
//				System.out.print(actVO.getActNo() + ",");
//				System.out.print(actVO.getActName() + ",");
//				System.out.print(actVO.getActStatus() + ",");
//				System.out.print(actVO.getActRegTime() + ",");
//				System.out.print(actVO.getActDate() + ",");
//				System.out.print(actVO.getDeadLine() + ",");
//				System.out.print(actVO.getActTime());
//				System.out.print(actVO.getParticipant());
//				System.out.print(actVO.getActPrice());
//				System.out.println();
//				}

	}
	@Override
	public List<ActVO> getAllByActStatus() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public byte[] getOnePic(String actEventNo) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
