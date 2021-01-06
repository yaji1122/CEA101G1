package com.choppers.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ChoppersDAO implements ChoppersDAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO CHOPPERS (CHOP_NO, CHOP_PIC, CHOP_NAME, CHOP_PRICE, CHOP_INFO) VALUES (LPAD(to_char(CHOP_SEQ.NEXTVAL), 2, '0'), ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE CHOPPERS SET CHOP_NAME = ?, CHOP_PRICE = ?, CHOP_INFO = ? WHERE CHOP_NO = ?";
	private static final String UPDATE_STATUS = "UPDATE CHOPPERS SET CHOP_STATUS = ? WHERE CHOP_NO = ?";
	private static final String UPDATE_PIC = "UPDATE CHOPPERS SET CHOP_PIC = ? WHERE CHOP_NO = ?";
	private static final String DELETE = "DELETE FROM CHOPPERS WHERE CHOP_NO = ?";
	private static final String GETALL = "SELECT * FROM CHOPPERS ORDER BY CHOP_NO";
	private static final String GETONEBYCHOPNO = "SELECT * FROM CHOPPERS WHERE CHOP_NO = ?";

	@Override
	public void insert(ChoppersVO chopvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setBytes(1, chopvo.getChop_pic());
			pstmt.setString(2, chopvo.getChop_name());
			pstmt.setInt(3, chopvo.getChop_price());
			pstmt.setString(4,  chopvo.getChop_info());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(ChoppersVO chopvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, chopvo.getChop_name());
			pstmt.setInt(2, chopvo.getChop_price());
			pstmt.setString(3,  chopvo.getChop_info());
			pstmt.setString(4, chopvo.getChop_no());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update_status(String chop_no, String chop_status) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE_STATUS);
			pstmt.setString(1, chop_status);
			pstmt.setString(2, chop_no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void update_pic(String chop_no, byte[] chop_pic) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE_PIC);
			pstmt.setBytes(1, chop_pic);
			pstmt.setString(2, chop_no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	@Override
	public void delete(String chop_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setString(1, chop_no);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	@Override
	public List<ChoppersVO> getAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ChoppersVO> choppers = new ArrayList<>();

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ChoppersVO chopvo = new ChoppersVO();
				String chop_no = rs.getString("chop_no");
				byte[] chop_pic = rs.getBytes("chop_pic");
				String chop_name = rs.getString("chop_name");
				Integer chop_price =rs.getInt("chop_price");
				String chop_status = rs.getString("chop_status");
				String chop_info = rs.getString("chop_info");
				chopvo.setChop_no(chop_no);
				chopvo.setChop_pic(chop_pic);
				chopvo.setChop_name(chop_name);
				chopvo.setChop_price(chop_price);
				chopvo.setChop_info(chop_info);
				chopvo.setChop_status(chop_status);
				choppers.add(chopvo);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return choppers;
	}

	@Override
	public ChoppersVO getOneByChopNo(String chop_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ChoppersVO chopvo = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETONEBYCHOPNO);
			pstmt.setString(1, chop_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chopvo = new ChoppersVO();
				byte[] chop_pic = rs.getBytes("chop_pic");
				String chop_name = rs.getString("chop_name");
				String chop_status = rs.getString("chop_status");
				Integer chop_price = rs.getInt("chop_price");
				String chop_info = rs.getString("chop_info");
				chopvo.setChop_no(chop_no);
				chopvo.setChop_pic(chop_pic);
				chopvo.setChop_name(chop_name);
				chopvo.setChop_status(chop_status);
				chopvo.setChop_price(chop_price);
				chopvo.setChop_info(chop_info);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return chopvo;
	}
}
