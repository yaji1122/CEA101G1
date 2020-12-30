package com.roompic.model;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.Part;
import javax.sql.DataSource;

public class RoomPicDAO implements RoomPicDAO_interface {
	private static DataSource ds = null;

	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");

		} catch (NamingException ne) {
			ne.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO ROOM_PIC (RM_PIC_NO, RM_TYPE, RM_PIC) VALUES ( 'RMP' || LPAD(to_char(BK_SEQ.NEXTVAL), 3, '0') ,? , ?)";
	private static final String DELETE = "DELETE FROM ROOM_PIC WHERE RM_PIC_NO = ?";
	private static final String GETALL = "SELECT * FROM ROOM_PIC WHERE RM_TYPE = ?";
	private static final String GETONE = "SELECT * FROM ROOM_PIC WHERE RM_PIC_NO = ?";
	@Override
	public void insert(RoomPicVO rmpicvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		BufferedInputStream bis = null;
		try {
			conn = ds.getConnection();
			System.out.println("成功取得連線");
			pstmt = conn.prepareStatement(INSERT);
			String rmtype = rmpicvo.getRm_type();
			Part part = rmpicvo.getPart();
			bis = new BufferedInputStream(part.getInputStream());
			byte[] buffer = new byte[bis.available()];
			bis.read(buffer);
			pstmt.setString(1, rmtype);
			pstmt.setBytes(2, buffer);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} catch (IOException ioe) {
			throw new RuntimeException("An error occured during pic insertion. " + ioe.getMessage());
		}finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
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
	}

	@Override
	public void delete(String picno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setString(1, picno);
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
	public List<RoomPicVO> getAllByRoomType(String rmtype) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<RoomPicVO> pics = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALL);
			pstmt.setString(1, rmtype);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				RoomPicVO rmpicvo = new RoomPicVO();
				String rm_pic_no = rs.getString("rm_pic_no");
				String rm_type = rs.getString("rm_type");
				byte[] rm_pic = rs.getBytes("rm_pic");
				rmpicvo.setRm_pic_no(rm_pic_no);
				rmpicvo.setRm_type(rm_type);
				rmpicvo.setRm_pic(rm_pic);
				pics.add(rmpicvo);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}  finally {
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
		return pics;
	}

	@Override
	public byte[] getOneRmPic(String rm_pic_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		byte[] rm_pic = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETONE);
			pstmt.setString(1, rm_pic_no);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			rm_pic = rs.getBytes("rm_pic");
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}  finally {
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
		return rm_pic;
	}
}
