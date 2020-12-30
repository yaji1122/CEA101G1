package com.pickup.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PickupDAO implements PickupDAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO PICKUP (PKUP_NO, BK_NO, CHOP_NO, ARRIVE_DATETIME) VALUES ('PKUP' || LPAD(to_char(PKUP_SEQ.NEXTVAL), 6, '0'),?, ?, ?)";
	private static final String UPDATE = "UPDATE PICKUP SET CHOP_NO = ?, ARRIVE_DATETIME = ? WHERE PKUP_NO = ?";
	private static final String UPDATEPKUPTIME = "UPDATE PICKUP SET PKUP_TIME = ?, PKUP_STATUS = ? WHERE PKUP_NO = ?";
	private static final String DELETE = "DELETE FROM PICKUP WHERE PKUP_NO = ?";
	private static final String GETALL = "SELECT * FROM PICKUP ORDER BY PKUP_NO";
	private static final String GETALLBYPKUPSTATUS = "SELECT * FROM PICKUP WHERE PKUP_STATUS = ? ORDER BY PKUP_NO";
	private static final String GETONEBYPKUPNO = "SELECT * FROM PICKUP WHERE PKUP_NO = ? ";
	private static final String GETONEBYBKNO = "SELECT * FROM PICKUP WHERE BK_NO = ?";
	
	@Override
	public synchronized PickupVO insert(PickupVO pkupvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try  {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			String bk_no = pkupvo.getBk_no();
			String chop_no = pkupvo.getChop_no();
			Timestamp arrive_datetime = pkupvo.getArrive_datetime();
			pstmt.setString(1, bk_no);
			pstmt.setString(2, chop_no);
			pstmt.setTimestamp(3, arrive_datetime);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				pkupvo.setPkup_no(rs.getString("pkup_no"));
			}
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
		return pkupvo;
	}

	@Override
	public void update(PickupVO pkupvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE);
			String chop_no = pkupvo.getChop_no();
			Timestamp arrive_datetime = pkupvo.getArrive_datetime();
			String pkup_no = pkupvo.getPkup_no();
			pstmt.setString(1, chop_no);
			pstmt.setTimestamp(2, arrive_datetime);
			pstmt.setString(3, pkup_no);
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
	public void updatePkupTime(String pkup_no, String pkup_status) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATEPKUPTIME);
			if (pkup_status.equals("1")) {
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.MILLISECOND, 0);
				Timestamp now = new Timestamp(calendar.getTimeInMillis());
				pstmt.setTimestamp(1, now);
			} else {
				pstmt.setNull(1, java.sql.Types.DATE);
			}
			pstmt.setString(2, pkup_status);
			pstmt.setString(3, pkup_no);
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
	public void delete(String pkup_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setString(1, pkup_no);
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
	public List<PickupVO> getAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PickupVO> pickups = new LinkedList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PickupVO pkupvo = new PickupVO();
				String pkup_no = rs.getString("PKUP_NO");
				String bk_no = rs.getString("BK_NO");
				String chop_no = rs.getString("CHOP_NO");
				Timestamp pkup_time = rs.getTimestamp("PKUP_TIME");
				Timestamp arrive_datetime = rs.getTimestamp("ARRIVE_DATETIME");
				String pkup_status = rs.getString("PKUP_STATUS");
				pkupvo.setPkup_no(pkup_no);
				pkupvo.setBk_no(bk_no);
				pkupvo.setChop_no(chop_no);
				pkupvo.setPkup_time(pkup_time);
				pkupvo.setArrive_datetime(arrive_datetime);
				pkupvo.setPkup_status(pkup_status);
				pickups.add(pkupvo);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
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
		return pickups;
	}
	
	@Override
	public List<PickupVO> getAllByPkupStatus(String pkup_status) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PickupVO> pickups = new LinkedList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALLBYPKUPSTATUS);
			pstmt.setString(1, pkup_status);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PickupVO pkupvo = new PickupVO();
				String pkup_no = rs.getString("PKUP_NO");
				String bk_no = rs.getString("BK_NO");
				String chop_no = rs.getString("CHOP_NO");
				Timestamp pkup_time = rs.getTimestamp("PKUP_TIME");
				Timestamp arrive_datetime = rs.getTimestamp("ARRIVE_DATETIME");
				pkupvo.setPkup_no(pkup_no);
				pkupvo.setBk_no(bk_no);
				pkupvo.setChop_no(chop_no);
				pkupvo.setPkup_time(pkup_time);
				pkupvo.setArrive_datetime(arrive_datetime);
				pkupvo.setPkup_status(pkup_status);
				pickups.add(pkupvo);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
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
		return pickups;
	}
	
	@Override
	public List<PickupVO> getAllByBkNo(String bk_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PickupVO> pickups = new LinkedList<>();
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETONEBYBKNO);
			pstmt.setString(1, bk_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				PickupVO pkupvo = new PickupVO();
				String pkup_no = rs.getString("PKUP_NO");
				String chop_no = rs.getString("CHOP_NO");
				Timestamp pkup_time = rs.getTimestamp("PKUP_TIME");
				Timestamp arrive_datetime = rs.getTimestamp("ARRIVE_DATETIME");
				String pkup_status = rs.getString("PKUP_STATUS");
				pkupvo.setPkup_no(pkup_no);
				pkupvo.setBk_no(bk_no);
				pkupvo.setChop_no(chop_no);
				pkupvo.setPkup_time(pkup_time);
				pkupvo.setArrive_datetime(arrive_datetime);
				pkupvo.setPkup_status(pkup_status);
				pickups.add(pkupvo);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}	
			}
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
		return pickups;
	}
	
	@Override
	public PickupVO getOneByPkupNo(String pkup_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PickupVO pkupvo = new PickupVO();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETONEBYPKUPNO);
			pstmt.setString(1, pkup_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String bk_no = rs.getString("BK_NO");
				String chop_no = rs.getString("CHOP_NO");
				Timestamp pkup_time = rs.getTimestamp("PKUP_TIME");
				Timestamp arrive_datetime = rs.getTimestamp("ARRIVE_DATETIME");
				String pkup_status = rs.getString("PKUP_STATUS");
				pkupvo.setPkup_no(pkup_no);
				pkupvo.setBk_no(bk_no);
				pkupvo.setChop_no(chop_no);
				pkupvo.setPkup_time(pkup_time);
				pkupvo.setArrive_datetime(arrive_datetime);
				pkupvo.setPkup_status(pkup_status);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
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
		return pkupvo;
	}
}
