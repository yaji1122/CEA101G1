package com.news.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsJDBCDAO implements NewsDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G1";
	String password = "123456";
	
	private static final String INSERT_STMT =
			"INSERT INTO NEWS (NEWS_NO, NEWS_CONTENT, EMP_ID) VALUES ('NEWS' || LPAD(to_char(NEWS_SEQ.NEXTVAL), 6, 0), ?, ?)";
	private static final String UPDATE = 
			"UPDATE NEWS SET NEWS_CONTENT=?, EMP_ID=? WHERE NEWS_NO=?";
	private static final String DELETE=
			"DELETE FROM NEWS WHERE NEWS_NO=?";
	private static final String GET_ONE_STMT=
			"SELECT * FROM NEWS WHERE NEWS_NO=?";
	private static final String GET_ALL_STMT=
			"SELECT * FROM NEWS ORDER BY NEWS_NO";
	
	public void insert(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, newsVO.getNews_content());
			pstmt.setString(2, newsVO.getEmp_id());
			
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
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
	public void update(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, newsVO.getNews_content());
			pstmt.setString(2, newsVO.getEmp_id());
			pstmt.setString(3, newsVO.getNews_no());
			
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
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
	public void delete(String news_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, news_no);
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public NewsVO findByNewsno(String news_no) {
		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,  news_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				newsVO = new NewsVO();
				newsVO.setNews_no(rs.getString("news_no"));
				newsVO.setNews_content(rs.getString("news_content"));
				newsVO.setNews_time(rs.getTimestamp("news_time"));
				newsVO.setEmp_id(rs.getString("emp_id"));
			}
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return newsVO;
	}

	@Override
	public List<NewsVO> getAll() {
		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				newsVO = new NewsVO();
				newsVO.setNews_no(rs.getString("news_no"));
				newsVO.setNews_content(rs.getString("news_content"));
				newsVO.setNews_time(rs.getTimestamp("news_time"));
				newsVO.setEmp_id(rs.getString("emp_id"));
				list.add(newsVO);
			}
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		NewsJDBCDAO dao = new NewsJDBCDAO();
		//新增
//		NewsVO newsVO = new NewsVO();
//		
//		newsVO.setNews_content("fuck");
//		newsVO.setEmp_id("EMP0000002");
//		
//		dao.insert(newsVO);
//		System.out.println("新增成功");
		
		//修改
//		NewsVO newsVO = new NewsVO();
//		newsVO.setNews_no("NEWS000001");
//		newsVO.setNews_content("fuck");
//		newsVO.setEmp_id("EMP0000001");
//		
//		dao.update(newsVO);
//		System.out.println("修改成功");
		
		//刪除
//		dao.delete("NEWS000001");
//		System.out.println("刪除成功");
		
		//查詢
		NewsVO newsVO = dao.findByNewsno("NEWS000002");
		System.out.print(newsVO.getNews_no() + ",");
		System.out.print(newsVO.getNews_content() + ",");
		System.out.print(newsVO.getNews_time() + ",");
		System.out.print(newsVO.getEmp_id() + ",");
		System.out.println();
		System.out.println("=========================");
		
		//查詢
		List<NewsVO> list = dao.getAll();
		for(NewsVO aNews : list) {
			System.out.print(aNews.getNews_no() + ",");
			System.out.print(aNews.getNews_content() + ",");
			System.out.print(aNews.getNews_time() + ",");
			System.out.print(aNews.getEmp_id() + ",");
			System.out.println();
		}
		
	}
}
