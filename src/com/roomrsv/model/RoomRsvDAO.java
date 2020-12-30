package com.roomrsv.model;

import java.util.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.json.*;
import com.roomtype.model.*;

public class RoomRsvDAO implements RoomRsvDAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String CREATERSVDATE = "INSERT INTO ROOM_RSV (RSV_DATE, RM_TYPE, RM_LEFT) VALUES (?, ?, ?)";
	private static final String UPDATE = "UPDATE ROOM_RSV SET RM_TYPE = ?, RM_LEFT =? WHERE RSV_DATE = ?";
	private static final String DELETE = "DELETE FROM ROOM_RSV WHERE RSV_DATE = ?";
	private static final String GETONEBYDATENRMTYPE = "SELECT * FROM ROOM_RSV WHERE RSV_DATE = ? AND RM_TYPE = ?";
	private static final String GETONEDAYBYDATE = "SELECT * FROM ROOM_RSV WHERE RSV_DATE = ?";
	private static final String GETALL = "SELECT * FROM ROOM_RSV ORDER BY RSV_DATE";
	private static final String GETALLBYRMTYPE = "SELECT * FROM ROOM_RSV WHERE RM_TYPE = ? ORDER BY RSV_DATE";
	
	@Override
	public void insert(LocalDate rsvDate) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(CREATERSVDATE);
			RoomTypeService rmTypeSvc = new RoomTypeService();
			List<RoomTypeVO> rmtypes = rmTypeSvc.getAll();
			for (RoomTypeVO rmtypevo : rmtypes) {
				pstmt.setObject(1, rsvDate);
				pstmt.setString(2, rmtypevo.getRm_type());
				pstmt.setInt(3, rmtypevo.getRm_qty());
				pstmt.executeUpdate();
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
	}

	@Override
	public void update(LocalDate rsvDate, String rmType, Integer rmLeft) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, rmType);
			pstmt.setInt(2, rmLeft);
			pstmt.setObject(3, rsvDate);
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
	public void delete(LocalDate rsvDate) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setObject(1, rsvDate);
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
	public RoomRsvVO getOneByDateNRmType(LocalDate rsvDate, String rm_type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RoomRsvVO rsvvo = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETONEBYDATENRMTYPE);
			pstmt.setDate(1, java.sql.Date.valueOf(rsvDate));
			pstmt.setString(2, rm_type);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				rsvvo = new RoomRsvVO();
				rsvvo.setRsv_date(rs.getDate("RSV_DATE").toLocalDate());
				rsvvo.setRm_type(rs.getString("RM_TYPE"));
				rsvvo.setRm_left(rs.getInt("RM_LEFT"));
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
		return rsvvo;
	}
	
	@Override
	public List<RoomRsvVO> getAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RoomRsvVO> roomRsv = new ArrayList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				RoomRsvVO rsvvo = new RoomRsvVO();
				rsvvo.setRsv_date(rs.getDate("RSV_DATE").toLocalDate());
				rsvvo.setRm_type(rs.getString("RM_TYPE"));
				rsvvo.setRm_left(rs.getInt("RM_LEFT"));
				roomRsv.add(rsvvo);
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
		return roomRsv;
	}

	@Override
	public List<RoomRsvVO> getOneDayByDate(LocalDate rsvDate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RoomRsvVO> roomRsv = new ArrayList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETONEDAYBYDATE);
			pstmt.setDate(1, java.sql.Date.valueOf(rsvDate));
			rs = pstmt.executeQuery();

			while(rs.next()) {
				RoomRsvVO rsvvo = new RoomRsvVO();
				rsvvo = new RoomRsvVO();
				rsvvo.setRsv_date(rs.getDate("RSV_DATE").toLocalDate());
				rsvvo.setRm_type(rs.getString("RM_TYPE"));
				rsvvo.setRm_left(rs.getInt("RM_LEFT"));
				roomRsv.add(rsvvo);
			}
			
			if (roomRsv.size() == 0) {
				RoomTypeService rmtypeSvc = new RoomTypeService();
				List<RoomTypeVO> rmtypevoList = rmtypeSvc.getAll();
				for (RoomTypeVO rmtypevo: rmtypevoList) {
					RoomRsvVO rsvvo = new RoomRsvVO();
					rsvvo.setRsv_date(rsvDate);
					rsvvo.setRm_type(rmtypevo.getRm_type());
					rsvvo.setRm_left(rmtypevo.getRm_capacity());
					roomRsv.add(rsvvo);
				}
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
		return roomRsv;
	}

	@Override
	public List<RoomRsvVO> getAllByRmType(String rmType) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RoomRsvVO> roomRsv = new ArrayList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALLBYRMTYPE);
			pstmt.setString(1, rmType);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RoomRsvVO rsvvo = new RoomRsvVO();
				rsvvo.setRsv_date(rs.getDate("RSV_DATE").toLocalDate());
				rsvvo.setRm_type(rs.getString("RM_TYPE"));
				rsvvo.setRm_left(rs.getInt("RM_LEFT"));
				roomRsv.add(rsvvo);
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
		return roomRsv;
	}
	
	public Integer roomCheck(LocalDate rsvDate, Integer stay, String rmType) {
		RoomTypeService rmtypeSvc = new RoomTypeService();
		RoomTypeVO rmtypevo = rmtypeSvc.getOne(rmType);
		Integer rmLeft = rmtypevo.getRm_qty();
		for (int i = 0; i < stay; i++) {
			RoomRsvVO rsvvo = getOneByDateNRmType(rsvDate.plusDays(i), rmType);
			if (rsvvo == null) {
				continue;
			} else if (rsvvo.getRm_left() == 0){
				rmLeft = 0;
				break;
			} else {
				rmLeft = Math.min(rsvvo.getRm_left(), rmLeft); 
			}
			
		}
		return rmLeft;
	}
}
