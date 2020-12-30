package com.func.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.auth.model.AuthJDBCDAO;
import com.auth.model.AuthVO;

public class FuncJDBCDAO implements FuncDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url =  "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "resort";
	String passwd = "123456";

	private static final String INSERT_STMT = 
	"INSERT INTO FUNC (func_no, func_name) VALUES (?,?)";
	private static final String GET_ALL_STMT =
    "SELECT * FROM FUNC order by func_no";
	private static final String GET_ONE_STMT =
	"SELECT func_no, func_name FROM FUNC where func_no=?";
	private static final String DELETE =
	"DELETE FROM FUNC WHERE func_no=?";
	private static final String UPDATE =
	"UPDATE FUNC set func_name=? where func_no=?";

		
	
	@Override
	public void insert(FuncVO funcVO) {
		Connection con =null;
		PreparedStatement pstmt =null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, funcVO.getFunc_no());
			pstmt.setString(2, funcVO.getFunc_name());
			
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
	public void update(FuncVO funcVO) {
		Connection con =null;
		PreparedStatement pstmt =null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid,passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, funcVO.getFunc_name());
			pstmt.setString(2, funcVO.getFunc_no());
			
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
	public FuncVO findByPrimaryKey(String func_no) {
		FuncVO funcVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, func_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				funcVO = new FuncVO();
				funcVO.setFunc_no(rs.getString("func_no"));
				funcVO.setFunc_name(rs.getString("func_name"));
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
		return funcVO;
	}
	@Override
	public List<FuncVO> getAll() {
		List<FuncVO> list = new ArrayList<FuncVO>();
		FuncVO funcVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				funcVO = new FuncVO();
				funcVO.setFunc_no(rs.getString("func_no"));
				funcVO.setFunc_name(rs.getString("func_name"));
				
				list.add(funcVO); 
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
	
	@Override
	public void delete(String func_no) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {

		FuncJDBCDAO dao = new FuncJDBCDAO();
		
        //新增資料
		FuncVO funcVO1 = new FuncVO();
		funcVO1.setFunc_no("30");
		funcVO1.setFunc_name("會員管理");
		dao.insert(funcVO1);
		System.out.println("新增成功");
		
		//修改資料
		FuncVO funcVO2 = new FuncVO();
		funcVO2.setFunc_no("12");
		funcVO2.setFunc_name("餐飲管理");
		dao.update(funcVO2);
		System.out.println("修改成功");
		
		//查func_no=?資料
		FuncVO funcVO3 = dao.findByPrimaryKey("01");
		System.out.print(funcVO3.getFunc_no() + ",");
		System.out.print(funcVO3.getFunc_name() + ",");
		
		System.out.println();
		System.out.println("---------------------");

		//查全部資料
		List<FuncVO> list = dao.getAll();
		for (FuncVO Func : list) {
			System.out.print(Func.getFunc_no() + ",");
            System.out.print(Func.getFunc_name() + ",");
            
			System.out.println();
		}
	}

}

