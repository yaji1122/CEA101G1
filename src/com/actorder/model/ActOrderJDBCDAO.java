package com.actorder.model;
import java.util.*;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActOrderJDBCDAO implements ActOrderDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "CEA101G1";
	String password = "123456";
	
	private static final String INSERT_STMT =
			"INSERT INTO ACT_ORDER(ACT_ODNO,ACT_NO,BK_NO,OD_TIME,OD_STATUS,PPL,TOTAL_PRICE)"
			+ "VALUES(('ACTOD' || LPAD(to_char(ACTODNO_SEQ.NEXTVAL) ,? ,? ,? ,? ,? ,?)";
	
	private static final String GET_ALL_STMT =
			"SELECT ACT_ODNO,ACT_NO,BK_NO,OD_TIME,OD_STATUS,PPL,TOTAL_PRICE FROM ACT_ORDER order By ACT_ODNO";
	
	private static final String GET_ONE_STMT = 
			"SELECT ACT_ODNO,ACT_NO,BK_NO,OD_TIME,OD_STATUS,PPL,TOTAL_PRICE FROM ACT_ORDER where ACT_ODNO = ?";
	
	private static final String DELETE =
			"DELETE FROM ACT_ORDER where ACT_ODNO = ?";
	
	private static final String UPDATE =
			"UPDATE ACT_ORDER SET ACT_NO=?,BK_NO=?,OD_TIME=?,OD_STATUS=?,PPL=?,TOTAL_PRICE=? where ACT_ODNO = ?";
	

	@Override
	public void insert(ActOrderVO actOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,actOrderVO.getActNo());
			pstmt.setString(2,actOrderVO.getBkNo());
			pstmt.setTime(3,java.sql.Time.valueOf(actOrderVO.getOdTime()));
			pstmt.setString(4,actOrderVO.getOdStatus());
			pstmt.setInt(5,actOrderVO.getPpl());
			pstmt.setInt(6,actOrderVO.getTotalPrice());
			
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
	public void update(ActOrderVO actOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1,actOrderVO.getActNo());
			pstmt.setString(2,actOrderVO.getBkNo());
			pstmt.setTime(3,java.sql.Time.valueOf(actOrderVO.getOdTime()));
			pstmt.setString(4,actOrderVO.getOdStatus());
			pstmt.setInt(5,actOrderVO.getPpl());
			pstmt.setInt(6,actOrderVO.getTotalPrice());
			pstmt.setString(7,actOrderVO.getActOdno());
			
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
	public void delete(String actOdno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1,actOdno);
			
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
	public ActOrderVO findByPrimaryKey(String actOdno) {
		ActOrderVO actOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,actOdno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				actOrderVO = new ActOrderVO();
				
				actOrderVO.setActOdno(rs.getString("ACT_ODNO"));
				actOrderVO.setActNo(rs.getString("ACT_NO"));
				actOrderVO.setBkNo(rs.getString("BK_NO"));
				actOrderVO.setOdTime(rs.getTime("OD_TIME").toLocalTime());
				actOrderVO.setOdStatus(rs.getString("OD_STATUS"));
				actOrderVO.setPpl(rs.getInt("PPL"));
				actOrderVO.setTotalPrice(rs.getInt("TOTAL_PRICE"));
				
		       		
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
		
		return actOrderVO;
	}

	@Override
	public List<ActOrderVO> getAll() {
		List<ActOrderVO> list = new ArrayList<ActOrderVO>();
		ActOrderVO actOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
                actOrderVO = new ActOrderVO();
				
				actOrderVO.setActOdno(rs.getString("ACT_ODNO"));
				actOrderVO.setActNo(rs.getString("ACT_NO"));
				actOrderVO.setBkNo(rs.getString("BK_NO"));
				actOrderVO.setOdTime(rs.getTime("OD_TIME").toLocalTime());
				actOrderVO.setOdStatus(rs.getString("OD_STATUS"));
				actOrderVO.setPpl(rs.getInt("PPL"));
				actOrderVO.setTotalPrice(rs.getInt("TOTAL_PRICE"));
				list.add(actOrderVO);
		       		
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
		ActOrderJDBCDAO dao = new ActOrderJDBCDAO();
		
		//�s�W
//		ActOrderVO actOrderVO1 = new ActOrderVO();
//		actOrderVO1.setActNo("1000000012");
//		actOrderVO1.setMbId("10");
//		actOrderVO1.setOdTime(java.sql.Date.valueOf("2020-12-09"));
//		actOrderVO1.setOdStatus("2");
//		actOrderVO1.setPpl(2);
//		actOrderVO1.setTotalPrice(1000);
//		dao.insert(actOrderVO1);
		
		
		//�ק�
//		ActOrderVO actOrderVO2 = new ActOrderVO();
//		actOrderVO2.setActOdno("1000000015");
//		actOrderVO2.setActNo("1000000012");
//		actOrderVO2.setMbId("11");
//		actOrderVO2.setOdTime(java.sql.Date.valueOf("2020-12-12"));
//		actOrderVO2.setOdStatus("2");
//		actOrderVO2.setPpl(0);
//		actOrderVO2.setTotalPrice(3000);
//		dao.update(actOrderVO2);
//		
//		// �R��
//		dao.delete("1000000015");
//		
//		//�涵�d��		
//		ActOrderVO actOrderVO3 = dao.findByPrimaryKey("ACTOD0000000001");
//		System.out.print(actOrderVO3.getActOdno() + ",");
//		System.out.print(actOrderVO3.getActNo() + ",");
//		System.out.print(actOrderVO3.getMbId() + ",");
//		System.out.print(actOrderVO3.getOdTime() + ",");
//		System.out.print(actOrderVO3.getOdStatus() + ",");
//		System.out.print(actOrderVO3.getPpl() + ",");
//		System.out.print(actOrderVO3.getTotalPrice());
//		System.out.println("---------------------");
		
//		// �d��
//		List<ActOrderVO> list = dao.getAll();
//			for (ActOrderVO actOrderVO : list) {
//				System.out.print(actOrderVO.getActOdno() + ",");
//				System.out.print(actOrderVO.getActNo() + ",");
//				System.out.print(actOrderVO.getMbId() + ",");
//				System.out.print(actOrderVO.getOdTime() + ",");
//				System.out.print(actOrderVO.getOdStatus() + ",");
//				System.out.print(actOrderVO.getPpl() + ",");
//				System.out.print(actOrderVO.getTotalPrice());
//				System.out.println();
//				}
	}

	@Override
	public List<ActOrderVO> getAllByBkNo(String bk_no) {
		// TODO Auto-generated method stub
		return null;
	}

}
