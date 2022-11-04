package com.promo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PromoDAO implements PromoDAO_interface {

	String insertsql = "insert into PROMO (PROMO_CONT,PROMO_TIME_S,PROMO_TIME_E,PROMO_VAL) values(?,?,?,?)";
	String updatesql = "UPDATE PROMO SET PROMO_CONT = ?, PROMO_TIME_S = ?, PROMO_TIME_E =? WHERE PROMO_ID = ?";
	String deletesql = "DELETE FROM PROMO WHERE PROMO_ID = ?";
	String findByPrimaryKeysqlsql = "select *from PROMO where PROMO_ID =?";
	String getAllsql = "select * from PROMO";

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FOOD");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(PromoVO promoVO) {

		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(insertsql);) {

			ps.setString(1, promoVO.getPromoCont());
			ps.setDate(2, promoVO.getPromoTimeS());
			ps.setDate(3, promoVO.getPromoTimeE());
			ps.setInt(4, promoVO.getPromoId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(PromoVO promoVO) {
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(updatesql);) {

			ps.setString(1, promoVO.getPromoCont());
			ps.setDate(2, promoVO.getPromoTimeS());
			ps.setDate(3, promoVO.getPromoTimeE());
			ps.setInt(4, promoVO.getPromoId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer promoId) {
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(deletesql);) {

			ps.setInt(1, promoId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public PromoVO findByPrimaryKey(Integer promoId) {

		PromoVO promoVO = new PromoVO();
		try (Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(findByPrimaryKeysqlsql);) {

			ps.setInt(1, promoId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				promoVO.setPromoId(rs.getInt(1));
				promoVO.setPromoCont(rs.getString(2));
				promoVO.setPromoTimeS(rs.getDate(3));
				promoVO.setPromoTimeE(rs.getDate(4));
				promoVO.setPromoVal(rs.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return promoVO;
	}

	@Override
	public List<PromoVO> getAll() {

		List<PromoVO> promos = new ArrayList<>();
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(getAllsql);) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PromoVO promoVO = new PromoVO();
				promoVO.setPromoId(rs.getInt(1));
				promoVO.setPromoCont(rs.getString(2));
				promoVO.setPromoTimeS(rs.getDate(3));
				promoVO.setPromoTimeE(rs.getDate(4));
				promoVO.setPromoVal(rs.getInt(5));
				promos.add(promoVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return promos;
	}

}
