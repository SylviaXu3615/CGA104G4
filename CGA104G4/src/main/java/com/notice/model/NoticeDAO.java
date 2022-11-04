package com.notice.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class NoticeDAO implements NoticeDAO_interface {
	String insertsql = "insert into NOTICE(MEM_ID,NOTE_TIME,NOTE_CONT)values(?,?,?)";
	String updatesql = "UPDATE NOTICE SET NOTE_TIME = ?, NOTE_CONT = ?, NOTE_STAT = ? WHERE NOTE_ID = ?";
	String deletesql = "DELETE FROM NOTICE WHERE NOTE_ID = ?";
	String findByPrimaryKeysqlsql = "select *from NOTICE where NOTE_ID =?";
	String getAllsql = "select *from NOTICE";

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
	public void insert(NoticeVO noticeVO) {

		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(insertsql);) {

			ps.setInt(1, noticeVO.getMemId());
			ps.setTimestamp(2, noticeVO.getNoteTime());
			ps.setString(3, noticeVO.getNoteCont());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(NoticeVO noticeVO) {

		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(updatesql);) {

			ps.setTimestamp(1, noticeVO.getNoteTime());
			ps.setString(2, noticeVO.getNoteCont());
			ps.setInt(3, noticeVO.getNoteStat());
			ps.setInt(4, noticeVO.getNoteId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer noteId) {

		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(deletesql);) {
			ps.setInt(1, noteId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public NoticeVO findByPrimaryKey(Integer noteId) {

		NoticeVO noticeVO = new NoticeVO();
		try (Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(findByPrimaryKeysqlsql);) {

			ps.setInt(1, noteId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				noticeVO.setNoteId(rs.getInt(1));
				noticeVO.setMemId(rs.getInt(2));
				noticeVO.setNoteTime(rs.getTimestamp(3));
				noticeVO.setNoteCont(rs.getString(4));
				noticeVO.setNoteStat(rs.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noticeVO;
	}

	@Override
	public List<NoticeVO> getAll() {

		List<NoticeVO> notices = new ArrayList<>();
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(getAllsql);) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NoticeVO noticeVO = new NoticeVO();
				noticeVO.setNoteId(rs.getInt(1));
				noticeVO.setMemId(rs.getInt(2));
				noticeVO.setNoteTime(rs.getTimestamp(3));
				noticeVO.setNoteCont(rs.getString(4));
				noticeVO.setNoteStat(rs.getInt(5));
				notices.add(noticeVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notices;
	}

}
