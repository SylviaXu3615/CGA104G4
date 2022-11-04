package com.orders.model;

import java.util.*;

import com.emp.model.DetailVO;
import com.emp.model.EmpVO;

import java.sql.*;

public class OrdersJDBCDAO implements OrdersDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/FOOD?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "insert into ORDERS (MEM_ID, STORE_ID, ORD_AMT, ORD_STAT, ORD_TIME) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "select ORD_ID, MEM_ID, STORE_ID, ORD_AMT, ORD_STAT, ORD_TIME from ORDERS";
	private static final String GET_ONE_STMT = "select ORD_ID, MEM_ID, STORE_ID, ORD_AMT, ORD_STAT, ORD_TIME from ORDERS where ORD_ID = ?";
	private static final String GET_Details_ByOrdId_STMT = "select PROD_ID, PROD_QTY, ORD_ID from DETAIL where ORDID = ? order by PROD_ID";
	private static final String DELETE_DETAILs = "delete from DETAIL where ORD_ID = ?";
	private static final String DELETE_ORDERS = "delete from ORDERS where ORD_ID = ?";
	private static final String UPDATE = "update ORDERS set MEM_ID=?, STORE_ID=?, ORD_AMT=?, ORD_STAT=?, ORD_TIME=?, where ORD_ID = ?";

	@Override
	public void insert(OrdersVO ordersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, ordersVO.getMemId());
			pstmt.setInt(2, ordersVO.getStoreId());
			pstmt.setInt(3, ordersVO.getOrdAmt());
			pstmt.setInt(4, ordersVO.getOrdStat());
			pstmt.setDate(5, ordersVO.getOrdTime());

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
	public void update(OrdersVO ordersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ordersVO.getMemId());
			pstmt.setInt(2, ordersVO.getStoreId());
			pstmt.setInt(3, ordersVO.getOrdAmt());
			pstmt.setInt(4, ordersVO.getOrdStat());
			pstmt.setDate(5, ordersVO.getOrdTime());
			pstmt.setInt(6, ordersVO.getOrdId());

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
	public void delete(Integer ordId) {
		int updateCount_ORDERS = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			
			// 先刪除明細
			pstmt = con.prepareStatement(DELETE_DETAILs);
			pstmt.setInt(1, ordId);
			pstmt.executeUpdate();
			// 再刪除訂單
			pstmt = con.prepareStatement(DELETE_ORDERS);
			pstmt.setInt(1, ordId);
			pstmt.executeUpdate();
			
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除訂單編號" + ordId + "時,共有明細" + updateCount_DETAILs
					+ "筆同時被刪除");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
	public OrdersVO findByPrimaryKey(Integer ordId) {

		OrdersVO ordersVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ordId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ordersVo 也稱為 Domain objects
				ordersVO = new OrdersVO();
				ordersVO.setOrdId(rs.getInt("ORD_ID"));
				ordersVO.setMemId(rs.getInt("MEM_ID"));
				ordersVO.setStoreId(rs.getInt("STORE_ID"));
				ordersVO.setOrdAmt(rs.getInt("ORD_AMT"));
				ordersVO.setOrdStat(rs.getInt("ORD_STAT"));
				ordersVO.setOrdTime(rs.getDate("ORD_TIME"));
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
		return ordersVO;
	}

	@Override
	public List<OrdersVO> getAll() {
		List<OrdersVO> list = new ArrayList<OrdersVO>();
		OrdersVO ordersVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ordersVO = new OrdersVO();
				ordersVO.setOrdId(rs.getInt("ORD_ID"));
				ordersVO.setMemId(rs.getInt("MEM_ID"));
				ordersVO.setStoreId(rs.getInt("STORE_ID"));
				ordersVO.setOrdAmt(rs.getInt("ORD_AMT"));
				ordersVO.setOrdStat(rs.getInt("ORD_STAT"));
				ordersVO.setOrdTime(rs.getDate("ORD_TIME"));
				list.add(ordersVO); // Store the row in the list
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
	public Set<DetailVO> getDetailsByOrdId(Integer ordId) {
		Set<DetailVO> set = new LinkedHashSet<DetailVO>();
		DetailVO detailVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Details_ByOrdId_STMT);
			pstmt.setInt(1, ordId);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				detailVO = new DetailVO();
				detailVO.setProdId(rs.getInt("prodId"));
				detailVO.setProdQty(rs.getInt("prodQty"));
				detailVO.setOrdId(rs.getInt("ordId"));
				set.add(detailVO); // Store the row in the vector
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
		return set;
	}
	
	public static void main(String[] args) {

		OrdersJDBCDAO dao = new OrdersJDBCDAO();

		// 新增
//		OrdersVO ordersVO1 = new OrdersVO();
//		ordersVO1.setOrdId(1);
//		ordersVO1.setMemId(1);
//		ordersVO1.setStoreId(1);
//		ordersVO1.setOrdAmt(100);
//		ordersVO1.setOrdStat(0);
//		ordersVO1.setOrdTime(java.sql.Date.valueOf("2022-10-16"));
//		dao.insert(ordersVO1);

		// 修改
//		OrdersVO ordersVO2 = new OrdersVO();
//		ordersVO2.setOrdId(2);
//		ordersVO2.setMemId(2);
//		ordersVO2.setStoreId(2);
//		ordersVO2.setOrdAmt(110);
//		ordersVO2.setOrdStat(1);
//		ordersVO2.setOrdTime(java.sql.Date.valueOf("2022-10-17"));
//		dao.update(ordersVO2);

		// 刪除
//		dao.delete(2);

		// 查詢
		OrdersVO ordersVO3 = dao.findByPrimaryKey(1);
		System.out.print(ordersVO3.getOrdId() + ",");
		System.out.print(ordersVO3.getMemId() + ",");
		System.out.print(ordersVO3.getStoreId() + ",");
		System.out.print(ordersVO3.getOrdAmt() + ",");
		System.out.print(ordersVO3.getOrdStat() + ",");
		System.out.print(ordersVO3.getOrdTime());
		System.out.println("---------------------");

		// 查詢訂單
		List<OrdersVO> list = dao.getAll();
		for (OrdersVO aOrders : list) {
			System.out.print(aOrders.getOrdId() + ",");
			System.out.print(aOrders.getMemId() + ",");
			System.out.print(aOrders.getStoreId() + ",");
			System.out.print(aOrders.getOrdAmt() + ",");
			System.out.print(aOrders.getOrdStat() + ",");
			System.out.print(aOrders.getOrdTime());
			System.out.println();
		}
		
		// 查詢某部門的員工
		Set<DetailVO> set = dao.getDetailsByOrdId(1);
		for (DetailVO aDetail : set) {
			System.out.print(aDetail.getProdId() + ",");
			System.out.print(aDetail.getProdQty() + ",");
			System.out.print(aDetail.getOrdId() + ",");
			System.out.println();
		}
	}
}