package com.promo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.utils.JDBCUtils;

public class PromoJDBCDAO implements PromoDAO_interface {
	String insertsql = "insert into PROMO (PROMO_CONT,PROMO_TIME_S,PROMO_TIME_E,PROMO_VAL) values(?,?,?,?)";
	String updatesql = "UPDATE PROMO SET PROMO_CONT = ?, PROMO_TIME_S = ?, PROMO_TIME_E =? WHERE PROMO_ID = ?";
	String deletesql = "DELETE FROM PROMO WHERE PROMO_ID = ?";
	String findByPrimaryKeysqlsql = "select *from PROMO where PROMO_ID =?";
	String getAllsql = "select * from PROMO";

	@Override
	public void insert(PromoVO promoVO) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(insertsql);
			ps.setString(1, promoVO.getPromoCont());
			ps.setDate(2, promoVO.getPromoTimeS());
			ps.setDate(3, promoVO.getPromoTimeE());
			ps.setInt(4, promoVO.getPromoId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps, conn);
		}

	}

	@Override
	public void update(PromoVO promoVO) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(updatesql);
			ps.setString(1, promoVO.getPromoCont());
			ps.setDate(2, promoVO.getPromoTimeS());
			ps.setDate(3, promoVO.getPromoTimeE());
			ps.setInt(4, promoVO.getPromoId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps, conn);
		}
		;

	}

	@Override
	public void delete(Integer promoId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(deletesql);
			ps.setInt(1, promoId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps, conn);
		}
		;

	}

	@Override
	public PromoVO findByPrimaryKey(Integer promoId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PromoVO promoVO = new PromoVO();
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(findByPrimaryKeysqlsql);
			ps.setInt(1, promoId);
			rs = ps.executeQuery();
			if (rs.next()) {
				promoVO.setPromoId(rs.getInt(1));
				promoVO.setPromoCont(rs.getString(2));
				promoVO.setPromoTimeS(rs.getDate(3));
				promoVO.setPromoTimeE(rs.getDate(4));
				promoVO.setPromoVal(rs.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps, conn);
		}

		return promoVO;
	}

	@Override
	public List<PromoVO> getAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PromoVO> list = new ArrayList<>();
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(getAllsql);
			rs = ps.executeQuery();
			while (rs.next()) {
				PromoVO promoVO = new PromoVO();
				promoVO.setPromoId(rs.getInt(1));
				promoVO.setPromoCont(rs.getString(2));
				promoVO.setPromoTimeS(rs.getDate(3));
				promoVO.setPromoTimeE(rs.getDate(4));
				promoVO.setPromoVal(rs.getInt(5));
				list.add(promoVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps, conn);
		}

		return list;
	}

	public static void main(String[] args) {

		PromoDAO_interface dao = new PromoJDBCDAO();

		// INSERT測試
//		PromoVO promoVO = new PromoVO();
//		promoVO.setPromoCont("我是測試用");
//		Calendar gc = new GregorianCalendar(2022, 11, 20);
//		promoVO.setPromoTimeS(new Date(gc.getTimeInMillis()));
//		Calendar gc2 = new GregorianCalendar(2022, 12, 20);
//		promoVO.setPromoTimeE(new Date(gc2.getTimeInMillis()));
//		promoVO.setPromoVal(70);
//		dao.insert(promoVO);

		// UPDATE測試
//		PromoVO promoVO2 = new PromoVO();
//		promoVO2.setPromoId(4);
//		promoVO2.setPromoCont("我是測試用2");
//		Calendar gc3 = new GregorianCalendar(2022, 8, 20);
//		promoVO2.setPromoTimeS(new Date(gc3.getTimeInMillis()));
//		Calendar gc4 = new GregorianCalendar(2022, 9, 20);
//		promoVO2.setPromoTimeE(new Date(gc4.getTimeInMillis()));
//		promoVO2.setPromoVal(80);
//		dao.update(promoVO2);

		// 刪除測試
//		dao.delete(4);

		// 主鍵查找
//		PromoVO promoVO3 = dao.findByPrimaryKey(2);
//		System.out.println(promoVO3);

		List<PromoVO> list = dao.getAll();
		for (PromoVO promoVO : list) {
			System.out.println(promoVO);
		}

	}

}
