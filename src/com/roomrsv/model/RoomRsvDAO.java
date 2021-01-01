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
	private static final String UPDATE = "UPDATE ROOM_RSV SET RM_LEFT =? WHERE RSV_DATE = ? AND RM_TYPE = ?";
	private static final String DELETE = "DELETE * FROM ROOM_RSV WHERE RSV_DATE = ?";
	private static final String GETONEBYDATENRMTYPE = "SELECT * FROM ROOM_RSV WHERE RSV_DATE = ? AND RM_TYPE = ?";
	private static final String GETONEDAYBYDATE = "SELECT * FROM ROOM_RSV WHERE RSV_DATE = ?";
	private static final String GETALL = "SELECT * FROM ROOM_RSV ORDER BY RSV_DATE";
	private static final String GETALLBYRMTYPE = "SELECT * FROM ROOM_RSV WHERE RM_TYPE = ? ORDER BY RSV_DATE";
	
	@Override
	public void insert(LocalDate rsvDate, Connection conn) {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(CREATERSVDATE);
			RoomTypeService rmTypeSvc = new RoomTypeService();
			List<RoomTypeVO> rmtypes = rmTypeSvc.getAll();
			for (RoomTypeVO rmtypevo : rmtypes) {
				pstmt.setDate(1, java.sql.Date.valueOf(rsvDate));
				pstmt.setString(2, rmtypevo.getRm_type());
				pstmt.setInt(3, rmtypevo.getRm_qty());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			if (conn != null) {
				try {
					System.err.print("有內鬼，交易撤回");
					conn.rollback();
				} catch (SQLException re){
					throw new RuntimeException("rollback發生錯誤:" + re.getMessage());
				}
			}
			e.printStackTrace();
			throw new RuntimeException("A database error occured:" + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(JSONObject bkitem, Connection conn) {
		PreparedStatement pstmt = null;
		RoomRsvVO rsvvo = null;
		try {
			Integer stay = Integer.parseInt(bkitem.getString("stay"));
			LocalDate date = LocalDate.parse(bkitem.getString("startDate"));
			String rmtype = bkitem.getString("rmtype");
			pstmt = conn.prepareStatement(UPDATE);
			for (int i = 0; i < stay; i++) { //訂幾天，就更新幾天的資料
				date = date.plusDays(i);
				rsvvo = getOneByDateNRmType(date, rmtype, conn); //取得該天該房型的資料
				if (rsvvo == null) {
					insert(date, conn); //如果該日期尚未創建空房表，
					rsvvo = getOneByDateNRmType(date, rmtype, conn);
				}
				Integer rmLeft = rsvvo.getRm_left() - 1;
				pstmt.setInt(1, rmLeft);
				pstmt.setDate(2, java.sql.Date.valueOf(date));
				pstmt.setString(3, rmtype);
				pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			if (conn != null) {
				try {
					System.err.print("有內鬼，交易撤回");
					conn.rollback();
				} catch (SQLException re){
					throw new RuntimeException("rollback發生錯誤:" + re.getMessage());
				}
			}
			e.printStackTrace();
			throw new RuntimeException("A database error occured:" + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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
	public RoomRsvVO getOneByDateNRmType(LocalDate rsvDate, String rm_type, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RoomRsvVO rsvvo = null;
		try {
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
		Connection conn = null;
		Integer rmLeft = null;
		try {
			conn = ds.getConnection();
			RoomTypeService rmtypeSvc = new RoomTypeService();
			RoomTypeVO rmtypevo = rmtypeSvc.getOne(rmType);
			rmLeft = rmtypevo.getRm_qty();
			for (int i = 0; i < stay; i++) {
				RoomRsvVO rsvvo = getOneByDateNRmType(rsvDate.plusDays(i), rmType, conn);
				if (rsvvo == null) {
					continue;
				} else if (rsvvo.getRm_left() == 0){
					rmLeft = 0;
					break;
				} else {
					rmLeft = Math.min(rsvvo.getRm_left(), rmLeft); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return rmLeft;
	}
}
