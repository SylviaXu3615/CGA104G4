package com.comment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CommentDAO implements CommentDAO_Interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FOOD");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	String GETALLPKSQL = "select COMT_ID from COMMENT order by COMT_ID asc;";
	String FBPKSQL = "select * from COMMENT where COMT_ID = ?";
	String GETALLSQL = "select * from COMMENT";
	String DELETESQL = "delete from COMMENT where COMT_ID  = ?";
	String GETSTOREID = "select STORE_ID from STORE";
	String INSERTSQL = "insert into COMMENT(MEM_ID,STORE_ID,COMT_TYPE,COMT_CONT,COMT_VAL) values(?,?,?,?,?);";
	String UPDATESQL = "UPDATE COMMENT SET MEM_ID = ?, STORE_ID = ?, COMT_TYPE = ?, COMT_CONT = ?, COMT_VAL = ?, COMT_TIME = ?, COMT_STAT = ? WHERE COMT_ID = ?;";

	@Override
	public void update(CommentVO commentVO) {
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(UPDATESQL)) {

			ps.setInt(1, commentVO.getMemId());
			ps.setInt(2, commentVO.getStoreId());
			ps.setInt(3, commentVO.getComtType());
			ps.setString(4, commentVO.getComtCont());
			ps.setInt(5, commentVO.getComtVal());
			ps.setTimestamp(6, commentVO.getComtTime());
			ps.setInt(7, commentVO.getComtStat());
			ps.setInt(8, commentVO.getComtId());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void insert(CommentVO commentVO) {
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(INSERTSQL)) {

			ps.setInt(1, commentVO.getMemId());
			ps.setInt(2, commentVO.getStoreId());
			ps.setInt(3, commentVO.getComtType());
			ps.setString(4, commentVO.getComtCont());
			ps.setInt(5, commentVO.getComtVal());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Integer> getStoreId() {
		List<Integer> list = new ArrayList<>();

		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(GETSTOREID)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Integer storeId = Integer.valueOf(rs.getInt(1));
				list.add(storeId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void delete(Integer comtId) {
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(DELETESQL)) {
			ps.setInt(1, comtId);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<CommentVO> getAll() {
		List<CommentVO> list = new ArrayList<>();

		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(GETALLSQL)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CommentVO commentVO = new CommentVO();
				commentVO.setComtId(rs.getInt(1));
				commentVO.setMemId(rs.getInt(2));
				commentVO.setStoreId(rs.getInt(3));
				commentVO.setComtType(rs.getInt(4));
				commentVO.setComtCont(rs.getString(5));
				commentVO.setComtVal(rs.getInt(6));
				commentVO.setComtTime(rs.getTimestamp(7));
				commentVO.setComtStat(rs.getInt(8));

				list.add(commentVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<CommentVO> getAllPK() {
		List<CommentVO> list = new ArrayList<>();

		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(GETALLPKSQL)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CommentVO commentVO = new CommentVO();
				commentVO.setComtId(rs.getInt(1));
				list.add(commentVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public CommentVO findByPrimaryKey(Integer comtId) {
		CommentVO commentVO = new CommentVO();

		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(FBPKSQL)) {
			ps.setInt(1, comtId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				commentVO.setComtId(rs.getInt(1));
				commentVO.setMemId(rs.getInt(2));
				commentVO.setStoreId(rs.getInt(3));
				commentVO.setComtType(rs.getInt(4));
				commentVO.setComtCont(rs.getString(5));
				commentVO.setComtVal(rs.getInt(6));
				commentVO.setComtTime(rs.getTimestamp(7));
				commentVO.setComtStat(rs.getInt(8));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return commentVO;
	}

}
