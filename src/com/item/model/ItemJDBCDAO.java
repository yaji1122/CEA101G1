package com.item.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class ItemJDBCDAO implements ItemDAO_interface {
	
	@Override
	public List<ItemVO> getAllByStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemVO> getAllByItem_type_noByStatus(String item_type_no) {
		// TODO Auto-generated method stub
		return null;
	}

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "shop";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO ITEM (item_no,item_name,item_type_no,item_price,item_status,item_on_sale,item_detail,points) VALUES ('I'||LPAD(to_char(ITEM_SEQ.NEXTVAL), 4, '0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT item_no , item_name, item_type_no, item_price, item_renew, item_status, item_on_sale, item_detail, points FROM item";
	private static final String GET_ONE_STMT = "SELECT item_no , item_name, item_type_no, item_price, item_renew, item_status, item_on_sale, item_detail, points FROM item where item_no = ?";
	private static final String GET_Emps_ByDeptno_STMT = "SELECT empno,ename,job,to_char(hiredate,'yyyy-mm-dd') hiredate,sal,comm,deptno FROM emp2 where deptno = ? order by empno";
	
	
	private static final String UPDATE = "UPDATE item set item_name = ?, item_type_no = ?, item_price = ?, item_status = ?, item_on_sale = ?, item_detail = ?, points = ? where item_no = ?";

	@Override
	public void insert(ItemVO itemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, itemVO.getItem_name());
			pstmt.setString(2, itemVO.getItem_type_no());
			pstmt.setDouble(3, itemVO.getItem_price());
			pstmt.setString(4, itemVO.getItem_status());
			pstmt.setString(5, itemVO.getItem_on_sale());
			pstmt.setString(6, itemVO.getItem_detail());
			pstmt.setInt(7, itemVO.getPoints());

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
	public void update(ItemVO itemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, itemVO.getItem_name());
			pstmt.setString(2, itemVO.getItem_type_no());
			pstmt.setDouble(3, itemVO.getItem_price());
			pstmt.setString(4, itemVO.getItem_status());
			pstmt.setString(5, itemVO.getItem_on_sale());
			pstmt.setString(6, itemVO.getItem_detail());
			pstmt.setInt(7, itemVO.getPoints());
			pstmt.setString(8, itemVO.getItem_no());

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
	public ItemVO findByPrimaryKey(String item_no) {

		ItemVO itemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, item_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				itemVO = new ItemVO();
				itemVO.setItem_no(rs.getString("item_no"));
				itemVO.setItem_name(rs.getString("item_name"));
				itemVO.setItem_type_no(rs.getString("item_type_no"));
				itemVO.setItem_price(rs.getDouble("item_price"));
				itemVO.setItem_renew(rs.getTimestamp("item_renew"));
				itemVO.setItem_status(rs.getString("item_status"));
				itemVO.setItem_on_sale(rs.getString("item_on_sale"));
				itemVO.setItem_detail(rs.getString("item_detail"));
				itemVO.setPoints(rs.getInt("points"));
				
			}

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
		return itemVO;
	}

	@Override
	public List<ItemVO> getAll() {
		List<ItemVO> list = new ArrayList<ItemVO>();
		ItemVO itemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itemVO = new ItemVO();
				itemVO.setItem_name(rs.getString("item_name"));
				itemVO.setItem_type_no(rs.getString("item_type_no"));
				itemVO.setItem_price(rs.getDouble("item_price"));
				itemVO.setItem_renew(rs.getTimestamp("item_renew"));
				itemVO.setItem_status(rs.getString("item_status"));
				itemVO.setItem_on_sale(rs.getString("item_on_sale"));
				itemVO.setPoints(rs.getInt("points"));
				itemVO.setItem_no(rs.getString("item_no"));
				list.add(itemVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public List<ItemVO> getAllByItem_type_no(String item_no) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {

		ItemJDBCDAO dao = new ItemJDBCDAO();

		// 新增
		ItemVO itemVO1 = new ItemVO();
		itemVO1.setItem_name("HERME EAU");
		itemVO1.setItem_type_no("12");
		itemVO1.setItem_price(2000.00);
		itemVO1.setItem_status("0");
		itemVO1.setItem_on_sale("1");
		itemVO1.setItem_detail("中國江西");
		itemVO1.setPoints(300);
		dao.insert(itemVO1);

		// 修改
		ItemVO itemVO2 = new ItemVO();
		itemVO2.setItem_no("I0007");
		itemVO2.setItem_name("CASIO G-SHOCK 5524");
		itemVO2.setItem_type_no("10");
		itemVO2.setItem_price((double) 1680);
		itemVO2.setItem_status("1");
		itemVO2.setItem_on_sale("0");
		itemVO2.setItem_detail("商品詳情");
		itemVO2.setPoints(300);
		dao.update(itemVO2);


		// 查詢
//		ItemVO itemVO3 = dao.findByPrimaryKey("I0003");
//		System.out.print(itemVO3.getItem_no() + ",");
//		System.out.print(itemVO3.getItem_name() + ",");
//		System.out.print(itemVO3.getItem_type_no() + ",");
//		System.out.print(itemVO3.getItem_price() + ",");
//		System.out.print(itemVO3.getItem_renew() + ",");
//		System.out.print(itemVO3.getItem_status() + ",");
//		System.out.print(itemVO3.getItem_on_sale() + ",");
//		System.out.print(itemVO3.getItem_detail() + ",");
//		System.out.print(itemVO3.getPoints());
//		

		// 查詢商品
//		List<ItemVO> list = dao.getAll();
//		for (ItemVO aItem : list) {
//			System.out.print(aItem.getItem_no() + ",");
//			System.out.print(aItem.getItem_name() + ",");
//			System.out.print(aItem.getItem_type_no() + ",");
//			System.out.print(aItem.getItem_price() + ",");
//			System.out.print(aItem.getItem_renew() + ",");
//			System.out.print(aItem.getItem_status() + ",");
//			System.out.print(aItem.getItem_on_sale() + ",");
//			System.out.print(aItem.getItem_detail() + ",");
//			System.out.print(aItem.getPoints());
//			System.out.println();
//		}
		
		// 查詢某部門的員工
//		Set<EmpVO> set = dao.getEmpsByDeptno(10);
//		for (EmpVO aEmp : set) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getEname() + ",");
//			System.out.print(aEmp.getJob() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getSal() + ",");
//			System.out.print(aEmp.getComm() + ",");
//			System.out.print(aEmp.getDeptno());
//			System.out.println();
//		}
	}

}
