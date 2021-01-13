package com.actorder.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActOrderDAO implements ActOrderDAO_interface{
	
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
			"INSERT INTO ACT_ORDER(ACT_ODNO,ACT_NO,BK_NO,OD_STATUS,PPL,TOTAL_PRICE)"
			+ "VALUES('ACTOD' || LPAD(to_char(ACTODNO_SEQ.NEXTVAL), 10, '0') ,? ,? ,? ,? ,?)";
	
	private static final String GET_ALL_STMT =
			"SELECT ACT_ODNO,ACT_NO,BK_NO,OD_TIME,OD_STATUS,PPL,TOTAL_PRICE FROM ACT_ORDER order By ACT_ODNO";
	
	private static final String GET_ONE_STMT = 
			"SELECT ACT_ODNO,ACT_NO,BK_NO,OD_TIME,OD_STATUS,PPL,TOTAL_PRICE FROM ACT_ORDER where ACT_ODNO = ?";
	
	private static final String DELETE =
			"DELETE FROM ACT_ORDER where ACT_ODNO = ?";
	
	private static final String UPDATE =
			"UPDATE ACT_ORDER SET OD_STATUS=?, PPL=?, TOTAL_PRICE=? where ACT_ODNO = ?";
	
	private static final String GETALLBYBKNO = 
			"SELECT * FROM ACT_ORDER WHERE BK_NO = ?";
	
	@Override
	public void insert(ActOrderVO actOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,actOrderVO.getActNo());
			pstmt.setString(2,actOrderVO.getBkNo());
//			pstmt.setTime(3,java.sql.Time.valueOf(actOrderVO.getOdTime()));
			pstmt.setString(3,"1");
			pstmt.setInt(4,actOrderVO.getPpl());
			pstmt.setInt(5,actOrderVO.getTotalPrice());
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
	public void update(ActOrderVO actOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,actOrderVO.getOdStatus());
			pstmt.setInt(2,actOrderVO.getPpl());
			pstmt.setInt(3,actOrderVO.getTotalPrice());
			pstmt.setString(4,actOrderVO.getActOdno());
			
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
	public void delete(String actOdno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1,actOdno);
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
	public ActOrderVO findByPrimaryKey(String actOdno) {
		ActOrderVO actOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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
	public List<ActOrderVO> getAllByBkNo(String bk_no) {
		List<ActOrderVO> list = new ArrayList<ActOrderVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALLBYBKNO);
			pstmt.setString(1, bk_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ActOrderVO actOrderVO = new ActOrderVO();
				actOrderVO.setActOdno(rs.getString("ACT_ODNO"));
				actOrderVO.setActNo(rs.getString("ACT_NO"));
				actOrderVO.setBkNo(rs.getString("BK_NO"));
				actOrderVO.setOdTime(rs.getTime("OD_TIME").toLocalTime());
				actOrderVO.setOdStatus(rs.getString("OD_STATUS"));
				actOrderVO.setPpl(rs.getInt("PPL"));
				actOrderVO.setTotalPrice(rs.getInt("TOTAL_PRICE"));
				list.add(actOrderVO);
			}
			
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	

}
