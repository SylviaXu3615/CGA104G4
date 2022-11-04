package com.notice.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.utils.JDBCUtils;

public class NoticeJDBCDAO implements NoticeDAO_interface {
	String insertsql = "insert into NOTICE(MEM_ID,NOTE_TIME,NOTE_CONT)values(?,?,?)";
	String updatesql = "UPDATE NOTICE SET NOTE_TIME = ?, NOTE_CONT = ?, NOTE_STAT = ? WHERE NOTE_ID = ?";
	String deletesql = "DELETE FROM NOTICE WHERE NOTE_ID = ?";
	String findByPrimaryKeysqlsql = "select *from NOTICE where NOTE_ID =?";
	String getAllsql = "select *from NOTICE";

	@Override
	public void insert(NoticeVO noticeVO) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(insertsql);
			ps.setInt(1, noticeVO.getMemId());
			ps.setTimestamp(2, noticeVO.getNoteTime());
			ps.setString(3, noticeVO.getNoteCont());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps, conn);
		}
	}

	@Override
	public void update(NoticeVO noticeVO) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(updatesql);
			ps.setTimestamp(1, noticeVO.getNoteTime());
			ps.setString(2, noticeVO.getNoteCont());
			ps.setInt(3, noticeVO.getNoteStat());
			ps.setInt(4, noticeVO.getNoteId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps, conn);
		}

	}

	@Override
	public void delete(Integer noteId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(deletesql);
			ps.setInt(1, noteId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps, conn);
		}

	}

	@Override
	public NoticeVO findByPrimaryKey(Integer noteId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		NoticeVO noticeVO = new NoticeVO();
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(findByPrimaryKeysqlsql);
			ps.setInt(1, noteId);
			rs = ps.executeQuery();
			if (rs.next()) {
				noticeVO.setNoteId(rs.getInt(1));
				noticeVO.setMemId(rs.getInt(2));
				noticeVO.setNoteTime(rs.getTimestamp(3));
				noticeVO.setNoteCont(rs.getString(4));
				noticeVO.setNoteStat(rs.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps, conn);
		}
		return noticeVO;
	}

	@Override
	public List<NoticeVO> getAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<NoticeVO> list = new ArrayList<>();
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(getAllsql);
			rs = ps.executeQuery();
			while (rs.next()) {
				NoticeVO noticeVO = new NoticeVO();
				noticeVO.setNoteId(rs.getInt(1));
				noticeVO.setMemId(rs.getInt(2));
				noticeVO.setNoteTime(rs.getTimestamp(3));
				noticeVO.setNoteCont(rs.getString(4));
				noticeVO.setNoteStat(rs.getInt(5));
				list.add(noticeVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps, conn);
		}

		return list;
	}

	public static void main(String[] args) {
		// 測試INSERT
		NoticeDAO_interface dao = new NoticeJDBCDAO();
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setMemId(2);
//		Calendar gc = new GregorianCalendar(2022, 11, 20, 16, 20, 47);
//		noticeVO.setNoteTime(new Date(gc.getTimeInMillis()));
//		noticeVO.setNoteCont("您的餐點已完成交易");
//		dao.insert(noticeVO);

		// 測試UPDATE
//		NoticeVO noticeVO2 = new NoticeVO();
//		noticeVO2.setNoteId(6);
//		Calendar gc2 = new GregorianCalendar(2022, 11, 20, 18, 20, 47);
//		noticeVO2.setNoteTime(new Date(gc2.getTimeInMillis()));
//		noticeVO2.setNoteStat(1);
//		noticeVO2.setNoteCont("您的餐點已完成交易2");
//		dao.update(noticeVO2);

		// 測試DELETE
//		dao.delete(6);

		// 測試findByPrimaryKey
//		NoticeVO noticeVO3 = dao.findByPrimaryKey(5);
//		System.out.println(noticeVO3);

		// 測試getAll
//		List<NoticeVO> list = dao.getAll();
//		for (NoticeVO noticeVO : list) {
//			System.out.println(noticeVO);
//		}

	}

}
