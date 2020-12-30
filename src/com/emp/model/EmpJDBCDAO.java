package com.emp.model;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import com.auth.model.AuthJDBCDAO;
import com.auth.model.AuthVO;
import com.dept.model.DeptVO;

public class EmpJDBCDAO implements EmpDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G1";
	String passwd = "123456";
	
	private static final String INSERT_STMT =
	  "INSERT INTO EMPLOYEE (emp_id, emp_name, emp_pwd, emp_email, title_no, dept_no)"
	  + "VALUES('EMP' || LPAD(to_char(EMP_SEQ.NEXTVAL), 7, '0'),?,?,?,?,?)";
				
	private static final String GET_ALL_STMT =
	  "SELECT * FROM EMPLOYEE order by emp_id";
	
	private static final String GET_ONE_STMT =
	  "SELECT emp_id, emp_name, emp_pwd, emp_pic, emp_phone, emp_email, emp_city, emp_town,"  
	  + "emp_address, emp_status, emp_date, title_no, dept_no FROM EMPLOYEE where emp_id=?";
	
	private static final String UPDATE =
	  "UPDATE EMPLOYEE set emp_name=?, emp_pwd=?, emp_email=?, title_no=?, dept_no=? where emp_id=?";
	
	private static final String GetOneByDept =
		"SELECT * FROM EMPLOYEE WHERE dept_no=?";
	
	private static final String GetOneByTitle =
		"SELECT * FROM EMPLOYEE WHERE title_no=?";
	
	private static final String GetOneByID =
	    "SELECT * FROM EMPLOYEE where emp_id=? and emp_pwd=?";

	@Override
	public EmpVO insert(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empVO.getEmp_name());
			pstmt.setString(2, empVO.getEmp_pwd());
			pstmt.setString(3, empVO.getEmp_email());
			pstmt.setString(4, empVO.getTitle_no());
			pstmt.setString(5, empVO.getDept_no());

			pstmt.executeUpdate();
			return empVO;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, empVO.getEmp_name());
			pstmt.setString(2, empVO.getEmp_pwd());
			pstmt.setString(3, empVO.getEmp_email());
			pstmt.setString(4, empVO.getTitle_no());
			pstmt.setString(5, empVO.getDept_no());
			pstmt.setString(6, empVO.getEmp_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public EmpVO findByPrimaryKey(String emp_id) {
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, emp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				empVO = new EmpVO();
				
				empVO.setEmp_id(rs.getString("emp_id"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_pwd(rs.getString("emp_pwd"));
				empVO.setEmp_pic(rs.getBytes("emp_pic"));
				empVO.setEmp_phone(rs.getString("emp_phone"));
				empVO.setEmp_email(rs.getString("emp_email"));
				empVO.setEmp_city(rs.getString("emp_city"));
				empVO.setEmp_town(rs.getString("emp_town"));
				empVO.setEmp_address(rs.getString("emp_address"));
				empVO.setEmp_status(rs.getString("emp_status"));
				empVO.setEmp_date(rs.getDate("emp_date"));
				empVO.setTitle_no(rs.getString("title_no"));
				empVO.setDept_no(rs.getString("dept_no"));
				
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
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
	
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmp_id(rs.getString("emp_id"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_pwd(rs.getString("emp_pwd"));
				empVO.setEmp_pic(rs.getBytes("emp_pic"));
				empVO.setEmp_phone(rs.getString("emp_phone"));
				empVO.setEmp_email(rs.getString("emp_email"));
				empVO.setEmp_city(rs.getString("emp_city"));
				empVO.setEmp_town(rs.getString("emp_town"));
				empVO.setEmp_address(rs.getString("emp_address"));
				empVO.setEmp_status(rs.getString("emp_status"));
				empVO.setEmp_date(rs.getDate("emp_date"));
				empVO.setTitle_no(rs.getString("title_no"));
				empVO.setDept_no(rs.getString("dept_no"));
				
				list.add(empVO); 
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
	public void insertWithAuths(EmpVO empVO, List<AuthVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1��身摰 pstm.executeUpdate()銋��
    		con.setAutoCommit(false);
			
    		// ��憓撌�
			String cols[] = {"emp_id"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);	
			
			pstmt.setString(1, empVO.getEmp_name());
			pstmt.setString(2, empVO.getEmp_pwd());
			pstmt.setString(3, empVO.getEmp_email());
			pstmt.setString(4, empVO.getTitle_no());
			pstmt.setString(5, empVO.getDept_no());

			pstmt.executeUpdate();
			//������憓蜓���
			String next_emp_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_emp_id = rs.getString(1);
				System.out.println("�憓蜓���= " + next_emp_id +"(��憓���撌亦楊���)");
			} else {
				System.out.println("����憓蜓���");
			}
			rs.close();
			// ����憓���
			AuthJDBCDAO dao = new AuthJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (AuthVO auth : list) {
				auth.setEmp_id(next_emp_id); ;
				dao.insert2(auth,con);
			}

			// 2��身摰 pstm.executeUpdate()銋��
			con.commit();
			con.setAutoCommit(true);//�雿輻��蝺��摰���
			System.out.println("list.size()-B="+list.size());
			System.out.println("�憓撌亦楊���" + next_emp_id + "���,������" + list.size()
					+ "����◤�憓�");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3��身摰���xception�����atch��憛
					System.err.print("Transaction is being ");
					System.err.println("rolled back-�-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public EmpVO login(String emp_id, String emp_pwd) {
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GetOneByID);

			pstmt.setString(1, emp_id);
			pstmt.setString(2, emp_pwd);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				empVO = new EmpVO();
				
				empVO.setEmp_id(rs.getString("emp_id"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_pwd(rs.getString("emp_pwd"));
				empVO.setEmp_pic(rs.getBytes("emp_pic"));
				empVO.setEmp_phone(rs.getString("emp_phone"));
				empVO.setEmp_email(rs.getString("emp_email"));
				empVO.setEmp_city(rs.getString("emp_city"));
				empVO.setEmp_town(rs.getString("emp_town"));
				empVO.setEmp_address(rs.getString("emp_address"));
				empVO.setEmp_status(rs.getString("emp_status"));
				empVO.setEmp_date(rs.getDate("emp_date"));
				empVO.setTitle_no(rs.getString("title_no"));
				empVO.setDept_no(rs.getString("dept_no"));
				
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
		return empVO;
	}

	public static void main(String[] args) {

		EmpJDBCDAO dao = new EmpJDBCDAO();
		
		// 新增
//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEmp_name("Green");
//		empVO1.setEmp_pwd("123456");
//		empVO1.setEmp_email("green@gmail.com");
//		empVO1.setTitle_no("05");
//		empVO1.setDept_no("40");
//		dao.insert(empVO1);
//		System.out.println("新增成功");
//		
//		// 修改
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmp_name("Candy");
//		empVO2.setEmp_pwd("123456");
//		empVO2.setEmp_email("LuluWu@gmail.com");
//		empVO2.setTitle_no("05");
//		empVO2.setDept_no("40");
//		empVO2.setEmp_id("EMP0000010");
//		dao.update(empVO2);
//		System.out.println("修改成功");
		
		
		//查詢emp_id=?
//		EmpVO empVO3 = dao.findByPrimaryKey("EMP0000005");
//		System.out.print(empVO3.getEmp_id() + ",");
//		System.out.print(empVO3.getEmp_name() + ",");
//		System.out.print(empVO3.getEmp_pwd() + ",");
//		System.out.print(empVO3.getEmp_pic() + ",");
//		System.out.print(empVO3.getEmp_phone() + ",");
//		System.out.print(empVO3.getEmp_email() + ",");
//		System.out.print(empVO3.getEmp_city() + ",");
//		System.out.print(empVO3.getEmp_town() + ",");
//		System.out.print(empVO3.getEmp_address() + ",");
//		System.out.print(empVO3.getEmp_status() + ",");
//		System.out.print(empVO3.getEmp_date() + ",");
//		System.out.print(empVO3.getTitle_no() + ",");
//		System.out.print(empVO3.getDept_no() + ",");
//				
//		System.out.println();
//		System.out.println("---------------------");

		//查詢所有資料
//		List<EmpVO> list = dao.getAll();
//		for (EmpVO Emp : list) {
//			  System.out.print(Emp.getEmp_id() + ",");
//            System.out.print(Emp.getEmp_name() + ",");
//            System.out.print(Emp.getEmp_pwd() + ",");
//            System.out.print(Emp.getEmp_pic() + ",");
//            System.out.print(Emp.getEmp_phone() + ",");
//            System.out.print(Emp.getEmp_email() + ",");
//            System.out.print(Emp.getEmp_city() + ",");
//            System.out.print(Emp.getEmp_town() + ",");
//            System.out.print(Emp.getEmp_address() + ",");
//            System.out.print(Emp.getEmp_status() + ",");
//            System.out.print(Emp.getEmp_date() + ",");
//            System.out.print(Emp.getTitle_no() + ",");
//            System.out.print(Emp.getDept_no() + ",");
//			System.out.println();
//		}
		
//		List<EmpVO> list1 = dao.findByDept("20");
//		for (EmpVO Emp : list1) {
//			System.out.print(Emp.getEmp_id() + ",");
//          System.out.print(Emp.getEmp_name() + ",");
//          System.out.print(Emp.getEmp_pwd() + ",");
//          System.out.print(Emp.getEmp_pic() + ",");
//          System.out.print(Emp.getEmp_phone() + ",");
//          System.out.print(Emp.getEmp_email() + ",");
//          System.out.print(Emp.getEmp_city() + ",");
//          System.out.print(Emp.getEmp_town() + ",");
//          System.out.print(Emp.getEmp_address() + ",");
//          System.out.print(Emp.getEmp_status() + ",");
//          System.out.print(Emp.getEmp_date() + ",");
//          System.out.print(Emp.getTitle_no() + ",");
//          System.out.print(Emp.getDept_no() + ",");
//		  System.out.println();
//
//		}

		//同時新增員工與權限
//		EmpVO empVO = new EmpVO();
//		empVO.setEmp_name("Alex");
//		empVO.setEmp_pwd("12345");
//		empVO.setEmp_email("alex@gmail.com");
//		empVO.setTitle_no("04");
//		empVO.setDept_no("10");
//		
//		List<AuthVO> testList = new ArrayList<AuthVO>(); // 皞�蔭�甈����
//		AuthVO authXX = new AuthVO();   // 甈�OJO1
//		authXX.setFunc_no("12");
//
//
//		AuthVO authYY = new AuthVO();   // 甈�OJO2
//		authYY.setFunc_no("99");;
//
//
//		testList.add(authXX);
//		testList.add(authYY);
//		
//		dao.insertWithAuths(empVO , testList);
		
		
		EmpVO empVO4 = dao.login("EMP0000010", "123456");
		System.out.print(empVO4.getEmp_id() + ",");
		System.out.print(empVO4.getEmp_name() + ",");
		System.out.print(empVO4.getEmp_pwd() + ",");
		System.out.print(empVO4.getEmp_pic() + ",");
		System.out.print(empVO4.getEmp_phone() + ",");
		System.out.print(empVO4.getEmp_email() + ",");
		System.out.print(empVO4.getEmp_city() + ",");
		System.out.print(empVO4.getEmp_town() + ",");
		System.out.print(empVO4.getEmp_address() + ",");
		System.out.print(empVO4.getEmp_status() + ",");
		System.out.print(empVO4.getEmp_date() + ",");
		System.out.print(empVO4.getTitle_no() + ",");
		System.out.print(empVO4.getDept_no() + ",");
				
		System.out.println();
		System.out.println("---------------------");
		
	}

	@Override
	public List<EmpVO> findByDept(String dept_no) {
		
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GetOneByDept);

			pstmt.setString(1, dept_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				empVO = new EmpVO();
				
				empVO.setEmp_id(rs.getString("emp_id"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_pwd(rs.getString("emp_pwd"));
				empVO.setEmp_pic(rs.getBytes("emp_pic"));
				empVO.setEmp_phone(rs.getString("emp_phone"));
				empVO.setEmp_email(rs.getString("emp_email"));
				empVO.setEmp_city(rs.getString("emp_city"));
				empVO.setEmp_town(rs.getString("emp_town"));
				empVO.setEmp_address(rs.getString("emp_address"));
				empVO.setEmp_status(rs.getString("emp_status"));
				empVO.setEmp_date(rs.getDate("emp_date"));
				empVO.setTitle_no(rs.getString("title_no"));
				empVO.setDept_no(rs.getString("dept_no"));
				
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
	public List<EmpVO> findByTitle(String title_no) {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GetOneByTitle);

			pstmt.setString(1, title_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				empVO = new EmpVO();
				
				empVO.setEmp_id(rs.getString("emp_id"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_pwd(rs.getString("emp_pwd"));
				empVO.setEmp_pic(rs.getBytes("emp_pic"));
				empVO.setEmp_phone(rs.getString("emp_phone"));
				empVO.setEmp_email(rs.getString("emp_email"));
				empVO.setEmp_city(rs.getString("emp_city"));
				empVO.setEmp_town(rs.getString("emp_town"));
				empVO.setEmp_address(rs.getString("emp_address"));
				empVO.setEmp_status(rs.getString("emp_status"));
				empVO.setEmp_date(rs.getDate("emp_date"));
				empVO.setTitle_no(rs.getString("title_no"));
				empVO.setDept_no(rs.getString("dept_no"));
				
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
	public EmpVO getOnePic(String emp_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
