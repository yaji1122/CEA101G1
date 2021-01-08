package com.roomtype.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class RoomTypeDAO implements RoomTypeDAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO ROOM_TYPE (RM_TYPE, TYPE_NAME, TYPE_ENG_NAME, RM_PRICE, RM_CAPACITY, RM_INFO_TITLE, RM_INFO) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE ROOM_TYPE SET TYPE_NAME = ?, TYPE_ENG_NAME = ?, RM_PRICE = ?, RM_CAPACITY = ?, RM_INFO_TITLE = ?, RM_INFO = ? WHERE RM_TYPE = ?";
	private static final String UPDATEQTY = "UPDATE ROOM_TYPE SET RM_QTY = ? WHERE RM_TYPE = ?";
	private static final String DELETE = "DELETE FROM ROOM_TYPE WHERE RM_TYPE = ?";
	private static final String GETALLROOMTYPE = "SELECT * FROM ROOM_TYPE ORDER BY RM_TYPE";
	private static final String GETONEROOMTYPE = "SELECT * FROM ROOM_TYPE WHERE RM_TYPE = ?";

	@Override
	public void insert(RoomTypeVO rmtypevo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, rmtypevo.getRm_type());
			pstmt.setString(2, rmtypevo.getType_name());
			pstmt.setString(3, rmtypevo.getType_eng_name());
			pstmt.setInt(4, rmtypevo.getRm_price());
			pstmt.setInt(5, rmtypevo.getRm_capacity());
			pstmt.setString(6, rmtypevo.getRm_info_title());
			if (rmtypevo.getRm_info() == "") {
				pstmt.setString(7, "待填入資料");
			} else {
				pstmt.setString(7, rmtypevo.getRm_info());
			}

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
	public void update(RoomTypeVO rmtypevo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE);

			pstmt.setString(1, rmtypevo.getType_name());
			pstmt.setString(2, rmtypevo.getType_eng_name());
			pstmt.setInt(3, rmtypevo.getRm_price());
			pstmt.setInt(4, rmtypevo.getRm_capacity());
			pstmt.setString(5, rmtypevo.getRm_info_title());
			if (rmtypevo.getRm_info() == "") {
				pstmt.setString(6, "待填入資料");
			} else {
				pstmt.setString(6, rmtypevo.getRm_info());
			}
			pstmt.setString(7, rmtypevo.getRm_type());
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
	public List<RoomTypeVO> getallRoomType() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<RoomTypeVO> rmtypeList = new ArrayList<>();

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALLROOMTYPE);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				RoomTypeVO rmtypevo = new RoomTypeVO();
				rmtypevo.setRm_type(rs.getString("RM_TYPE")); 
				rmtypevo.setType_name(rs.getString("TYPE_NAME"));
				rmtypevo.setType_eng_name(rs.getString("TYPE_ENG_NAME"));
				rmtypevo.setRm_qty(rs.getInt("RM_QTY"));
				rmtypevo.setRm_price(rs.getInt("RM_PRICE"));
				rmtypevo.setRm_capacity(rs.getInt("RM_CAPACITY"));
				rmtypevo.setRm_info_title(rs.getString("RM_INFO_TITLE"));
				rmtypevo.setRm_info(rs.getString("RM_INFO"));
				rmtypeList.add(rmtypevo);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
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
		return rmtypeList;
	}
	
	public void delete(String rmtype) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
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
	}
	@Override
	public void updateQty(RoomTypeVO rmtypevo, int number, Connection conn) {
		PreparedStatement pstmt = null;
		Integer qty = getOneRmType(rmtypevo.getRm_type()).getRm_qty();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATEQTY);
			pstmt.setInt(1, qty + number);
			pstmt.setString(2, rmtypevo.getRm_type());
			pstmt.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public RoomTypeVO getOneRmType(String rmtype) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		RoomTypeVO rmtypevo = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETONEROOMTYPE);
			pstmt.setString(1, rmtype);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				rmtypevo = new RoomTypeVO();
				rmtypevo.setRm_type(rmtype);
				rmtypevo.setType_eng_name(rs.getString("TYPE_ENG_NAME"));
				rmtypevo.setRm_price(rs.getInt("RM_PRICE"));
				rmtypevo.setRm_capacity(rs.getInt("RM_CAPACITY"));
				rmtypevo.setRm_qty(rs.getInt("RM_QTY"));
				rmtypevo.setRm_info(rs.getString("RM_INFO"));
				rmtypevo.setRm_info_title(rs.getString("RM_INFO_TITLE"));
				rmtypevo.setType_name(rs.getString("TYPE_NAME"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
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
		return rmtypevo;
	}
}
