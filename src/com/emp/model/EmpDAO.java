package com.emp.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

import com.auth.model.AuthDAO;
import com.auth.model.AuthJDBCDAO;
import com.auth.model.AuthVO;

public class EmpDAO implements EmpDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds =(DataSource)ctx.lookup("java:comp/env/jdbc/resort");
		}catch (NamingException e){
			e.printStackTrace();
		}
	}
	        private static final String INSERT_STMT =
			  "INSERT INTO EMPLOYEE (emp_id, emp_name, emp_pwd, emp_pic, "
			  + "emp_phone,emp_email,emp_city, emp_town, emp_address, emp_status, title_no, dept_no)"
			  + "VALUES('EMP' || LPAD(to_char(EMP_SEQ.NEXTVAL), 7, '0'),?,?,?,?,?,?,?,?,?,?,?)";
					
			
			private static final String GET_ALL_STMT =
			"SELECT * FROM EMPLOYEE order by emp_id";
			
			private static final String GET_ONE_STMT =
			  "SELECT * FROM EMPLOYEE where emp_id=?";
			
			private static final String UPDATE =
			  "UPDATE EMPLOYEE set emp_name=?, emp_pwd=?, emp_pic=?, emp_phone=?, emp_email=?,emp_city=?,"
			  + "emp_town=?, emp_address=?,emp_status=?, title_no=?, dept_no=? where emp_id=?";
			
			private static final String GetOneByDept =
			  "SELECT * FROM EMPLOYEE WHERE dept_no=?";
			
			private static final String GetOneByTitle =
			 "SELECT * FROM EMPLOYEE WHERE title_no=?";
			
			private static final String GetOnePic =
			 "SELECT emp_pic FROM EMPLOYEE WHERE emp_id=?";

			private static final String GetOneByID =
			  "SELECT * FROM EMPLOYEE where emp_id=? and emp_pwd=?";
			
			@Override
			public EmpVO insert(EmpVO empVO) {

				Connection con = null;
				PreparedStatement pstmt =null;
			try {
				con = ds.getConnection();
				String[] generatedColumns = {"EMP_ID"};
				pstmt = con.prepareStatement(INSERT_STMT, generatedColumns);
				
				pstmt.setString(1, empVO.getEmp_name());
				pstmt.setString(2, empVO.getEmp_pwd());
				pstmt.setBytes(3, empVO.getEmp_pic());
				pstmt.setString(4, empVO.getEmp_phone());
				pstmt.setString(5, empVO.getEmp_email());
				pstmt.setString(6, empVO.getEmp_city());
				pstmt.setString(7, empVO.getEmp_town());
				pstmt.setString(8, empVO.getEmp_address());
				pstmt.setString(9, empVO.getEmp_status());
				pstmt.setString(10, empVO.getTitle_no());
				pstmt.setString(11, empVO.getDept_no());
				
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				rs.next();
				String new_emp_id = rs.getString(1);
				System.out.print(new_emp_id);
				empVO.setEmp_id(new_emp_id);
				return empVO;
				}catch (SQLException se){
					se.printStackTrace();
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
						}catch(Exception e) {
							e.printStackTrace(System.err);
						}
					}
				}
				
			}

			
			@Override
			public void update(EmpVO empVO) {
				Connection con = null;
				PreparedStatement pstmt =null;
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(UPDATE);
					
					pstmt.setString(1, empVO.getEmp_name());
					pstmt.setString(2, empVO.getEmp_pwd());
					pstmt.setBytes(3,empVO.getEmp_pic());
					pstmt.setString(4,empVO.getEmp_phone());
					pstmt.setString(5, empVO.getEmp_email());
					pstmt.setString(6, empVO.getEmp_city());
					pstmt.setString(7,empVO.getEmp_town());
					pstmt.setString(8, empVO.getEmp_address());
					pstmt.setString(9,empVO.getEmp_status());
					pstmt.setString(10, empVO.getTitle_no());
					pstmt.setString(11, empVO.getDept_no());
					pstmt.setString(12, empVO.getEmp_id());
					
					pstmt.executeUpdate();
					
				}catch (SQLException se){
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
						}catch(Exception e) {
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
					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ONE_STMT);
					
					pstmt.setString(1, emp_id);				
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						
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
				} catch (SQLException se) {
					throw new RuntimeException("A database error occured."+ se.getMessage());
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
					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ALL_STMT);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						empVO =new EmpVO();
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
				} catch (SQLException se) {
					throw new RuntimeException("A database error occured."+se.getMessage());
				} finally{
					if (rs != null) {
						try {
							rs.close();
						}catch (SQLException se) {
							se.printStackTrace (System.err);
						}
					}if (pstmt != null) {
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
			public List<EmpVO> findByDept(String dept_no) {
				List<EmpVO> list = new ArrayList<EmpVO>();
				EmpVO empVO = null;
				
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(GetOneByDept);
					pstmt.setString(1, dept_no);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						empVO =new EmpVO();
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
				} catch (SQLException se) {
					throw new RuntimeException("A database error occured."+se.getMessage());
				} finally{
					if (rs != null) {
						try {
							rs.close();
						}catch (SQLException se) {
							se.printStackTrace (System.err);
						}
					}if (pstmt != null) {
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
			public List<EmpVO> findByTitle(String title_no) {
				List<EmpVO> list = new ArrayList<EmpVO>();
				EmpVO empVO = null;
				
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(GetOneByTitle);
					pstmt.setString(1, title_no);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						empVO =new EmpVO();
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
				} catch (SQLException se) {
					throw new RuntimeException("A database error occured."+se.getMessage());
				} finally{
					if (rs != null) {
						try {
							rs.close();
						}catch (SQLException se) {
							se.printStackTrace (System.err);
						}
					}if (pstmt != null) {
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

			public void insertWithAuths(EmpVO empVO, List<AuthVO> list) {
				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(INSERT_STMT);
					
					// 1●設定於 pstm.executeUpdate()之前
		    		con.setAutoCommit(false);
					
		    		// 先新增員工
					String cols[] = {"emp_id"};
					pstmt = con.prepareStatement(INSERT_STMT , cols);	
					
					pstmt.setString(1, empVO.getEmp_name());
					pstmt.setString(2, empVO.getEmp_pwd());
					pstmt.setBytes(3, empVO.getEmp_pic());
					pstmt.setString(4, empVO.getEmp_phone());
					pstmt.setString(5, empVO.getEmp_email());
					pstmt.setString(6, empVO.getEmp_city());
					pstmt.setString(7, empVO.getEmp_town());
					pstmt.setString(8, empVO.getEmp_address());
					pstmt.setString(9, empVO.getEmp_status());
					pstmt.setString(10, empVO.getTitle_no());
					pstmt.setString(11, empVO.getDept_no());

					pstmt.executeUpdate();
					//掘取對應的自增主鍵值
					String next_emp_id = null;
					ResultSet rs = pstmt.getGeneratedKeys();
					if (rs.next()) {
						next_emp_id = rs.getString(1);
						System.out.println("自增主鍵值= " + next_emp_id +"(剛新增成功的員工編號)");
					} else {
						System.out.println("未取得自增主鍵值");
					}
					rs.close();
					// 再同時新增權限
					AuthDAO dao = new AuthDAO();
					System.out.println("list.size()-A="+list.size());
					for (AuthVO auth : list) {
						auth.setEmp_id(next_emp_id); ;
						dao.insert2(auth,con);
					}

					// 2●設定於 pstm.executeUpdate()之後
					con.commit();
					con.setAutoCommit(true);//若使用連線池一定要做
					System.out.println("list.size()-B="+list.size());
					System.out.println("新增員工編號" + next_emp_id + "時,共有權限" + list.size()
							+ "項同時被新增");
					
					// Handle any driver errors
				}  catch (SQLException se) {
					if (con != null) {
						try {
							// 3●設定於當有exception發生時之catch區塊內
							System.err.print("Transaction is being ");
							System.err.println("rolled back-由-emp");
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
			public EmpVO getOnePic(String emp_id) {
				EmpVO empVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(GetOnePic);
					
					pstmt.setString(1, emp_id);					
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						
						empVO = new EmpVO();
						empVO.setEmp_pic(rs.getBytes("emp_pic"));
			
					}
				} catch (SQLException se) {
					throw new RuntimeException("A database error occured."+ se.getMessage());
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
			public EmpVO login(String emp_id, String emp_pwd) {
				EmpVO empVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(GetOneByID);
					
					pstmt.setString(1, emp_id);
					pstmt.setString(2, emp_pwd);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						
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
				} catch (SQLException se) {
					empVO = null;
					throw new RuntimeException("A database error occured."+ se.getMessage());					
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

			
}
