package com.members.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MembersDAO implements MembersDAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/resort");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = 
			"INSERT INTO MEMBERS (MB_ID, MB_NAME,  MB_PWD, MB_SALT, MB_BD, MB_PIC, MB_PHONE, MB_EMAIL, MB_CITY, MB_TOWN, MB_ADDRESS) VALUES ('MEM' || LPAD(to_char(MB_SEQ.NEXTVAL), 7, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE MEMBERS SET MB_NAME = ?, MB_BD = ?, MB_PHONE = ?, MB_CITY = ?, MB_TOWN = ?, MB_ADDRESS = ? WHERE MB_ID = ?";
	private static final String UPDATESTATUS = "UPDATE MEMBERS SET MB_STATUS = ? WHERE MB_ID = ?";
	private static final String UPDATEPIC = "UPDATE MEMBERS SET MB_PIC = ? WHERE MB_ID = ?";
	private static final String UPDATEPWD = "UPDATE MEMBERS SET MB_PWD = ?, MB_SALT = ? WHERE MB_ID = ?";
	private static final String UPDATEPOINT = "UPDATE MEMBERS SET MB_POINT = ? WHERE MB_ID = ?";
	private static final String GETONEBYACC = "SELECT * FROM MEMBERS WHERE MB_ACC = ?";
	private static final String GETONEBYEMAIL = "SELECT * FROM MEMBERS WHERE MB_EMAIL = ?";
	private static final String GETONEBYID = "SELECT * FROM MEMBERS WHERE MB_ID = ?";
	private static final String GETALL = "SELECT * FROM MEMBERS ORDER BY MB_ID";
	private static final String GETALLBYSTATUS = "SELECT * FROM MEMBERS WHERE MB_STATUS = ? ORDER BY MB_ID";

	@Override
	public void insert(MembersVO membervo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			
			pstmt.setString(1, membervo.getMb_name());
			pstmt.setString(2, membervo.getMb_pwd());
			pstmt.setString(3, membervo.getMb_salt());
			pstmt.setDate(4, membervo.getMb_bd());
			pstmt.setBytes(5, membervo.getMb_pic());
			pstmt.setString(6, membervo.getMb_phone());
			pstmt.setString(7, membervo.getMb_email());
			pstmt.setString(8, membervo.getMb_city());
			pstmt.setString(9, membervo.getMb_town());
			pstmt.setString(10, membervo.getMb_address());
			
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
	public void update(MembersVO membervo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE);
			
			pstmt.setString(1, membervo.getMb_name());
			pstmt.setDate(2, membervo.getMb_bd());
			pstmt.setString(3, membervo.getMb_phone());
			pstmt.setString(4, membervo.getMb_city());
			pstmt.setString(5, membervo.getMb_town());
			pstmt.setString(6, membervo.getMb_address());
			pstmt.setString(7, membervo.getMb_id());
			
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
	public void updateStatus(MembersVO membervo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATESTATUS);
			
			pstmt.setString(1, membervo.getMb_status());
			pstmt.setString(2, membervo.getMb_id());
			
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
	public void updatePwd(MembersVO membervo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATEPWD);
			
			pstmt.setString(1, membervo.getMb_pwd());
			pstmt.setString(2, membervo.getMb_salt());
			pstmt.setString(3, membervo.getMb_id());
			
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
	public void updatePic(MembersVO membervo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATEPIC);
			
			pstmt.setBytes(1, membervo.getMb_pic());
			pstmt.setString(2, membervo.getMb_id());
			
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
	public void updatePoint(MembersVO membervo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATEPOINT);
			
			pstmt.setInt(1, membervo.getMb_point());
			pstmt.setString(2, membervo.getMb_id());
			
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
	public MembersVO getOneByAcc(String mb_acc) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MembersVO membervo = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETONEBYACC);
			pstmt.setString(1, mb_acc);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				membervo = new MembersVO();
				membervo.setMb_id(rs.getString("MB_ID"));
				membervo.setMb_name(rs.getString("MB_NAME"));
				membervo.setMb_pwd(rs.getString("MB_PWD"));
				membervo.setMb_salt(rs.getString("MB_SALT"));
				membervo.setMb_bd(rs.getDate("MB_BD"));
				membervo.setMb_phone(rs.getString("MB_PHONE"));
				membervo.setMb_email(rs.getString("MB_EMAIL"));
				membervo.setMb_city(rs.getString("MB_CITY"));
				membervo.setMb_town(rs.getString("MB_TOWN"));
				membervo.setMb_address(rs.getString("MB_ADDRESS"));
				membervo.setMb_status(rs.getString("MB_STATUS"));
				membervo.setCreate_date(rs.getDate("CREATE_DATE"));
				membervo.setMb_point(rs.getInt("MB_POINT"));
			}
			
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if(rs != null) {
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
		return membervo;
	}

	@Override
	public MembersVO getOneById(String mb_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MembersVO membervo = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETONEBYID);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				membervo = new MembersVO();
				membervo.setMb_id(rs.getString("MB_ID"));
				membervo.setMb_name(rs.getString("MB_NAME"));
				membervo.setMb_pwd(rs.getString("MB_PWD"));
				membervo.setMb_salt(rs.getString("MB_SALT"));
				membervo.setMb_bd(rs.getDate("MB_BD"));
				membervo.setMb_phone(rs.getString("MB_PHONE"));
				membervo.setMb_email(rs.getString("MB_EMAIL"));
				membervo.setMb_city(rs.getString("MB_CITY"));
				membervo.setMb_town(rs.getString("MB_TOWN"));
				membervo.setMb_address(rs.getString("MB_ADDRESS"));
				membervo.setMb_status(rs.getString("MB_STATUS"));
				membervo.setCreate_date(rs.getDate("CREATE_DATE"));
				membervo.setMb_point(rs.getInt("MB_POINT"));
				membervo.setMb_pic(rs.getBytes("MB_PIC"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if(rs != null) {
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
		return membervo;
	}
	
	@Override
	public MembersVO getOneByEmail(String mb_email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MembersVO membervo = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETONEBYEMAIL);
			pstmt.setString(1, mb_email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				membervo = new MembersVO();
				membervo.setMb_id(rs.getString("MB_ID"));
				membervo.setMb_name(rs.getString("MB_NAME"));
				membervo.setMb_pwd(rs.getString("MB_PWD"));
				membervo.setMb_salt(rs.getString("MB_SALT"));
				membervo.setMb_bd(rs.getDate("MB_BD"));
				membervo.setMb_phone(rs.getString("MB_PHONE"));
				membervo.setMb_email(rs.getString("MB_EMAIL"));
				membervo.setMb_city(rs.getString("MB_CITY"));
				membervo.setMb_town(rs.getString("MB_TOWN"));
				membervo.setMb_address(rs.getString("MB_ADDRESS"));
				membervo.setMb_status(rs.getString("MB_STATUS"));
				membervo.setCreate_date(rs.getDate("CREATE_DATE"));
				membervo.setMb_point(rs.getInt("MB_POINT"));
				membervo.setMb_pic(rs.getBytes("MB_PIC"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if(rs != null) {
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
		return membervo;
	}
	
	@Override
	public List<MembersVO> getAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MembersVO> list = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MembersVO membervo = new MembersVO();
				membervo.setMb_id(rs.getString("MB_ID"));
				membervo.setMb_name(rs.getString("MB_NAME"));
				membervo.setMb_bd(rs.getDate("MB_BD"));
				membervo.setMb_phone(rs.getString("MB_PHONE"));
				membervo.setMb_email(rs.getString("MB_EMAIL"));
				membervo.setMb_city(rs.getString("MB_CITY"));
				membervo.setMb_town(rs.getString("MB_TOWN"));
				membervo.setMb_address(rs.getString("MB_ADDRESS"));
				membervo.setMb_status(rs.getString("MB_STATUS"));
				membervo.setCreate_date(rs.getDate("CREATE_DATE"));
				membervo.setMb_point(rs.getInt("MB_POINT"));
				list.add(membervo);
			}
			
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if(rs != null) {
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
		return list;
	}

	@Override
	public List<MembersVO> getAllByStatus(String mb_status) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MembersVO> list = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GETALLBYSTATUS);
			pstmt.setString(1, mb_status);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MembersVO membervo = new MembersVO();
				membervo.setMb_id(rs.getString("MB_ID"));
				membervo.setMb_name(rs.getString("MB_NAME"));
				membervo.setMb_bd(rs.getDate("MB_BD"));
				membervo.setMb_phone(rs.getString("MB_PHONE"));
				membervo.setMb_email(rs.getString("MB_EMAIL"));
				membervo.setMb_city(rs.getString("MB_CITY"));
				membervo.setMb_town(rs.getString("MB_TOWN"));
				membervo.setMb_address(rs.getString("MB_ADDRESS"));
				membervo.setMb_status(rs.getString("MB_STATUS"));
				membervo.setCreate_date(rs.getDate("CREATE_DATE"));
				membervo.setMb_point(rs.getInt("MB_POINT"));
				list.add(membervo);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if(rs != null) {
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
		return list;
	}



}
