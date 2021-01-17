package com.rooms.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.bookingorder.model.BookingOrderService;
import com.roomrsv.model.RoomRsvService;
import com.roomtype.model.RoomTypeService;

public class RoomsDAO implements RoomsDAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO ROOMS (RM_NO, RM_TYPE) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE ROOMS SET RM_STATUS = ? WHERE RM_NO = ?";
	private static final String UPDATE_CHECKIN = "UPDATE ROOMS SET RM_STATUS = ?, MB_ID = ?, BK_NO = ? WHERE RM_NO = ?";
	private static final String UPDATE_CHECKOUT = "UPDATE ROOMS SET RM_STATUS = ?, MB_ID = ?, BK_NO = ? WHERE RM_NO = ?";
	private static final String DELETE = "DELETE FROM ROOMS WHERE RM_NO = ?";
	private static final String GETALLBYBKNO = "SELECT * FROM ROOMS WHERE BK_NO = ? ORDER BY RM_NO";
	private static final String GETALLBYSTATUS = "SELECT * FROM ROOMS WHERE RM_STATUS = ? ORDER BY RM_NO";
	private static final String GETALLBYRMTYPE = "SELECT * FROM ROOMS WHERE RM_TYPE = ? ORDER BY RM_NO";
	private static final String GETALLBYMBID = "SELECT * FROM ROOMS WHERE MB_ID = ? ORDER BY RM_NO";
	private static final String GETALL = "SELECT * FROM ROOMS ORDER BY RM_NO";

	@Override
	public String insert(RoomsVO rmvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String rm_no = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			String rm_type = rmvo.getRm_type();
			rm_no = rmvo.getRm_no();
			
			if (rm_no.equals("")) { // 如果沒有自訂房號，自動新增
				for (int i = 0; i < 100; i++) {
					List<RoomsVO> list = getAllByRmType(rm_type);
					List<String> rm_no_list = new ArrayList<>();
					for (RoomsVO rm_vo : list) {
						rm_no_list.add(rm_vo.getRm_no());
					}
					String number = String.format("%02d", i);
					if (rm_no_list.contains(rm_type + number)) {
						continue;
					} else {
						rm_no = rm_type + number;
						break;
					}
				}
			}
			pstmt.setString(1, rm_no);
			pstmt.setString(2, rm_type);
			pstmt.executeUpdate();
			RoomRsvService rsvSvc = new RoomRsvService();
			RoomTypeService rmtypeSvc = new RoomTypeService();
			rsvSvc.renewQty(rm_type, 1, conn);
			rmtypeSvc.updateRoomQty(rmvo.getRm_type(), 1, conn);
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
		return rm_no;
	}

	@Override
	public void update(RoomsVO rmvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, rmvo.getRm_status());
			pstmt.setString(2, rmvo.getRm_no());
			pstmt.executeUpdate();
			
			RoomRsvService rsvSvc = new RoomRsvService();
			RoomTypeService rmtypeSvc = new RoomTypeService();
			if (rmvo.getRm_status().equals("3") || rmvo.getRm_status().equals("2")) {
				rsvSvc.renewQty(rmvo.getRm_type(), -1, conn);
				rmtypeSvc.updateRoomQty(rmvo.getRm_type(), -1, conn);
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
	public void update_checkin(RoomsVO rmvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE_CHECKIN);
			pstmt.setString(1, "1");
			pstmt.setString(2, rmvo.getMb_id());
			pstmt.setString(3, rmvo.getBk_no());
			pstmt.setString(4, rmvo.getRm_no());
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
	public void update_checkout(RoomsVO rmvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE_CHECKOUT);
			pstmt.setString(1, "0");
			pstmt.setNull(2, java.sql.Types.CHAR);
			pstmt.setNull(3, java.sql.Types.CHAR);
			pstmt.setString(4, rmvo.getRm_no());
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
	public void delete(String rm_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setString(1, rm_no);
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
	public List<RoomsVO> getAllByStatus(String rmstatus) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<RoomsVO> rooms = new ArrayList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALLBYSTATUS);
			pstmt.setString(1, rmstatus);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RoomsVO rmvo = new RoomsVO();
				String rm_no = rs.getString("RM_NO");
				String rm_type = rs.getString("RM_TYPE");
				String rm_status = rs.getString("RM_STATUS");
				String mb_id = rs.getString("MB_ID");
				rmvo.setRm_no(rm_no);
				rmvo.setRm_type(rm_type);
				rmvo.setRm_status(rm_status);
				rmvo.setMb_id(mb_id);
				rmvo.setBk_no(rs.getString("BK_NO"));
				rooms.add(rmvo);
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
		return rooms;
	}

	@Override
	public List<RoomsVO> getAllByRmType(String rmtype) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<RoomsVO> rooms = new ArrayList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALLBYRMTYPE);
			pstmt.setString(1, rmtype);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RoomsVO rmvo = new RoomsVO();
				String rm_no = rs.getString("RM_NO");
				String rm_type = rs.getString("RM_TYPE");
				String rm_status = rs.getString("RM_STATUS");
				String mb_id = rs.getString("MB_ID");
				rmvo.setBk_no(rs.getString("BK_NO"));
				rmvo.setRm_no(rm_no);
				rmvo.setRm_type(rm_type);
				rmvo.setRm_status(rm_status);
				rmvo.setMb_id(mb_id);
				rooms.add(rmvo);
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
		return rooms;
	}

	@Override
	public List<RoomsVO> getAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<RoomsVO> rooms = new ArrayList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RoomsVO rmvo = new RoomsVO();
				String rm_no = rs.getString("RM_NO");
				String rm_type = rs.getString("RM_TYPE");
				String rm_status = rs.getString("RM_STATUS");
				String mb_id = rs.getString("MB_ID");
				rmvo.setBk_no(rs.getString("BK_NO"));
				rmvo.setRm_no(rm_no);
				rmvo.setRm_type(rm_type);
				rmvo.setRm_status(rm_status);
				rmvo.setMb_id(mb_id);
				rooms.add(rmvo);
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
		return rooms;
	}

	@Override
	public List<RoomsVO> getAllByMbId(String mb_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RoomsVO> rooms = new ArrayList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALLBYMBID);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RoomsVO rmvo = new RoomsVO();
				rmvo.setRm_no(rs.getString("RM_NO"));
				rmvo.setRm_type( rs.getString("RM_TYPE"));
				rmvo.setBk_no(rs.getString("BK_NO"));
				rmvo.setMb_id(mb_id);
				rooms.add(rmvo);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch(SQLException e) {
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
		return rooms;
	}
	
	@Override
	public List<RoomsVO> getAllByBkNo(String bk_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RoomsVO> rooms = new ArrayList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALLBYBKNO);
			pstmt.setString(1, bk_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RoomsVO rmvo = new RoomsVO();
				rmvo.setRm_no(rs.getString("RM_NO"));
				rmvo.setRm_type( rs.getString("RM_TYPE"));
				rmvo.setMb_id(rs.getString("MB_ID"));
				rmvo.setBk_no(bk_no);
				rooms.add(rmvo);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch(SQLException e) {
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
		return rooms;
	}
}
