package com.title.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TitleJDBCDAO implements TitleDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url =  "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "resort";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
	"INSERT INTO EMP_TITLE (title_no, title) VALUES (?,?)";
	private static final String GET_ALL_STMT =
    "SELECT * FROM EMP_TITLE order by title_no";
	private static final String GET_ONE_STMT =
	"SELECT title_no, title FROM EMP_TITLE where title_no=?";
//	private static final String DELETE =
//	"DELETE FROM FUNC where title_no=?";
	private static final String UPDATE =
	"UPDATE EMP_TITLE set title=? where title_no=?";
	
	
	@Override
	public void insert(TitleVO titleVO) {
		Connection con =null;
		PreparedStatement pstmt =null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, titleVO.getTitle_no());
			pstmt.setString(2, titleVO.getTitle());
			
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."+e.getMessage());
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
			} 
		}
	}
	@Override
	public void update(TitleVO titleVO) {
		Connection con =null;
		PreparedStatement pstmt =null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid,passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, titleVO.getTitle());
			pstmt.setString(2, titleVO.getTitle_no());
			
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."+e.getMessage());
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
			} 
		}
	}
	@Override
	public TitleVO findByPrimaryKey(String title_no) {
		TitleVO titleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, title_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				titleVO = new TitleVO();
				titleVO.setTitle_no(rs.getString("title_no"));
				titleVO.setTitle(rs.getString("title"));
			}

			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return titleVO;
	}
	@Override
	public List<TitleVO> getAll() {
		List<TitleVO> list = new ArrayList<TitleVO>();
		TitleVO titleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				titleVO = new TitleVO();
				titleVO.setTitle_no(rs.getString("title_no"));
				titleVO.setTitle(rs.getString("title"));
				
				list.add(titleVO); 
			}

			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public static void main(String[] args) {

		TitleJDBCDAO dao = new TitleJDBCDAO();
		
        //新增資料
		TitleVO titleVO1 = new TitleVO();
		titleVO1.setTitle_no("14");
		titleVO1.setTitle("客服員");
		dao.insert(titleVO1);
		System.out.println("新增成功");
		
		//修改資料
		TitleVO titleVO2 = new TitleVO();
		titleVO2.setTitle_no("12");
		titleVO2.setTitle("接待員");
		dao.update(titleVO2);
		System.out.println("修改成功");
		
		//查title_no=?資料
		TitleVO funcVO3 = dao.findByPrimaryKey("01");
		System.out.print(funcVO3.getTitle_no() + ",");
		System.out.print(funcVO3.getTitle() + ",");
		
		System.out.println();
		System.out.println("---------------------");

		//查全部資料
		List<TitleVO> list = dao.getAll();
		for (TitleVO Title : list) {
			System.out.print(Title.getTitle_no() + ",");
            System.out.print(Title.getTitle() + ",");
            
			System.out.println();
		}
	}
	
	
}

