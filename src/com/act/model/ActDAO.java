package com.act.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ActDAO implements ActDAO_interface{
	
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
			"INSERT INTO ACT (ACT_NO,ACT_EVENT_NO,ACT_NAME,ACT_STATUS,ACT_REG_TIME,ACT_DATE,DEADLINE,ACT_TIME,PARTICIPANT,ACT_PRICE,ACT_PIC,ACT_INFO)"
			+ "VALUES ('ACT' || LPAD(to_char(ACTNO_SEQ.NEXTVAL), 7, '0'),?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String GET_ALL_STMT =
			"SELECT ACT_NO,ACT_EVENT_NO,ACT_NAME,ACT_STATUS,TO_CHAR(ACT_REG_TIME,'yyyy-mm-dd')ACT_REG_TIME,"
			+ "TO_CHAR(ACT_DATE,'yyyy-mm-dd')ACT_DATE,TO_CHAR(DEADLINE,'yyyy-mm-dd')DEADLINE,ACT_TIME," + 
			"PARTICIPANT,ACT_PRICE,ACT_PIC,ACT_INFO FROM ACT order By ACT_NO";
	
	private static final String GET_ONE_STMT = 
			"SELECT ACT_NO,ACT_EVENT_NO,ACT_NAME,ACT_STATUS,TO_CHAR(ACT_REG_TIME,'yyyy-mm-dd')ACT_REG_TIME,"
			+ "TO_CHAR(ACT_DATE,'yyyy-mm-dd')ACT_DATE,TO_CHAR(DEADLINE,'yyyy-mm-dd')DEADLINE,ACT_TIME" + 
			",PARTICIPANT,ACT_PRICE,ACT_PIC,ACT_INFO FROM ACT where ACT_NO=?";
	
	private static final String DELETE =
			"DELETE FROM ACT WHERE ACT_NO = ?";
	
	private static final String UPDATE =
			"UPDATE ACT set ACT_EVENT_NO=?,ACT_NAME=?,ACT_STATUS=?,ACT_REG_TIME=?,ACT_DATE=?,"
			+ "DEADLINE=?,ACT_TIME=?,PARTICIPANT=?,ACT_PRICE=?,ACT_PIC=?,ACT_INFO=? where ACT_NO=?";

	private static final String GET_ALL_BY_ACT_STATUS =
			"SELECT ACT_NO,ACT_EVENT_NO,ACT_NAME,ACT_STATUS,TO_CHAR(ACT_REG_TIME,'yyyy-mm-dd')ACT_REG_TIME,"
			+ "TO_CHAR(ACT_DATE,'yyyy-mm-dd')ACT_DATE,TO_CHAR(DEADLINE,'yyyy-mm-dd')DEADLINE,ACT_TIME," + 
			"ACT_PRICE FROM ACT order WHERE ACT_STATUS <> '0' ";
	
	@Override
	public void insert(ActVO actVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
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

			// Handle any SQL errors
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

	@Override
	public void update(ActVO actVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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

	@Override
	public void delete(String actNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,actNo);

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

	@Override
	public ActVO findByPrimaryKey(String actNo) {
		ActVO actVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1,actNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
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
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ALL_STMT);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			// empVO �]�٬� Domain objects
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
	
		// Handle any driver errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
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
	public List<ActVO> getAllActStatus(String actNo) {
		List<ActVO> list = new ArrayList<ActVO>();
		ActVO actVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_ACT_STATUS);
			pstmt.setString(1, actNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				actVO = new ActVO();
				actVO.setActNo(rs.getString("ACT_NO"));
				actVO.setActEventNo(rs.getString("ACT_EVENT_NO"));
				actVO.setActName(rs.getString("ACT_NAME"));
				actVO.setActStatus(rs.getString("ACT_STATUS"));
				actVO.setActRegTime(rs.getDate("ACT_REG_TIME"));
				actVO.setActDate(rs.getDate("ACT_DATE"));
				actVO.setDeadLine(rs.getDate("DEADLINE"));
				actVO.setActTime(rs.getString("ACT_TIME"));
				actVO.setActPrice(rs.getInt("ACT_PRICE"));
				actVO.setActPic(rs.getBytes("ACT_PIC"));
				actVO.setActInfo(rs.getString("ACT_INFO"));
				list.add(actVO);
			}
		
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public byte[] getOnePic(String actNo) {
		byte[] actpic = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1,actNo);
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

	@Override
	public List<ActVO> getAllByActStatus() {
		// TODO Auto-generated method stub
		return null;
	}
}

