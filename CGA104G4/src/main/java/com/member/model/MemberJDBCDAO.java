package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.utils.JDBCUtils;

public class MemberJDBCDAO implements MemberDAO_interface {

	private static final String INSERT_SQL = "insert into MEMBER(MEM_EMAIL, MEM_PWD, ACC_STAT, MEM_NAME, MEM_MOBILE, MEM_CITY, MEM_DIST, MEM_ADR,MEM_PIC)values(?,?,?,?,?,?,?,?,?,?)";
	private static final String GETMEMID_SQL = "select MEM_ID from MEMBER";
	private static final String FBPK_SQL = "select * from MEMBER where MEM_ID = ?";
	private static final String GETALL_SQL = "select * from MEMBER";
	private static final String DELETE_SQL = "delete from MEMBER where MEM_ID = ?";
	private static final String UPDATE_SQL = "update MEMBER set MEM_EMAIL=?,MEM_PWD=?,ACC_STAT=?,MEM_NAME=?,MEM_MOBILE=?,MEM_CITY=?,MEM_DIST=?,MEM_ADR=?,MEM_TOKEN=? where MEM_ID =?";

	public void insert(MemberVO memberVO) {
		try (Connection conn = JDBCUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

			ps.setString(1, memberVO.getMemEmail());
			ps.setString(2, memberVO.getMemPwd());
			ps.setInt(3, memberVO.getAccStat());
			ps.setString(4, memberVO.getMemName());
			ps.setString(5, memberVO.getMemMobile());
			ps.setString(6, memberVO.getMemCity());
			ps.setString(7, memberVO.getMemDist());
			ps.setString(8, memberVO.getMemAdr());
			ps.setBytes(9, memberVO.getMemPic());

			ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void update(MemberVO memberVO) {
		try (Connection conn = JDBCUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(UPDATE_SQL);) {

			ps.setString(1, memberVO.getMemEmail());
			ps.setString(2, memberVO.getMemPwd());
			ps.setInt(3, memberVO.getAccStat());
			ps.setString(4, memberVO.getMemName());
			ps.setString(5, memberVO.getMemMobile());
			ps.setString(6, memberVO.getMemCity());
			ps.setString(7, memberVO.getMemDist());
			ps.setString(8, memberVO.getMemAdr());
			ps.setInt(9, memberVO.getMemToken());
			ps.setInt(10, memberVO.getMemId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer memId) {
		try (Connection conn = JDBCUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE_SQL);) {
			ps.setInt(1, memId);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> members = new ArrayList<>();
		try (Connection conn = JDBCUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(GETALL_SQL);) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MemberVO memberVO = new MemberVO();
				memberVO.setMemId(rs.getInt(1));
				memberVO.setMemEmail(rs.getString(2));
				memberVO.setMemPwd(rs.getString(3));
				memberVO.setAccStat(rs.getInt(4));
				memberVO.setMemName(rs.getString(5));
				memberVO.setMemMobile(rs.getString(6));
				memberVO.setMemCity(rs.getString(7));
				memberVO.setMemDist(rs.getString(8));
				memberVO.setMemAdr(rs.getString(9));
				memberVO.setMemRegTime(rs.getDate(10));
				memberVO.setMemPic(rs.getBytes(11));
				memberVO.setMemToken(rs.getInt(12));
				members.add(memberVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return members;
	}

	@Override
	public MemberVO findByPrimaryKey(Integer memId) {
		MemberVO memberVO = new MemberVO();
		try (Connection conn = JDBCUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(FBPK_SQL);) {
			ps.setInt(1, memId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				memberVO.setMemId(rs.getInt(1));
				memberVO.setMemEmail(rs.getString(2));
				memberVO.setMemPwd(rs.getString(3));
				memberVO.setAccStat(rs.getInt(4));
				memberVO.setMemName(rs.getString(5));
				memberVO.setMemMobile(rs.getString(6));
				memberVO.setMemCity(rs.getString(7));
				memberVO.setMemDist(rs.getString(8));
				memberVO.setMemAdr(rs.getString(9));
				memberVO.setMemRegTime(rs.getDate(10));
				memberVO.setMemPic(rs.getBytes(11));
				memberVO.setMemToken(rs.getInt(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberVO;
	}

	@Override
	public List<Integer> getMemId() {
		List<Integer> memIdList = new ArrayList<>();
		try (Connection conn = JDBCUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(GETMEMID_SQL);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				memIdList.add(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return memIdList;
	}

	@Override
	public MemberVO login(String memEmail, String memPwd) {
		return null;
	}

	@Override
	public boolean selectByMemEmail(String memEmail) {
		return false;
	}

	@Override
	public Integer insertWithReturn(MemberVO memberVO) {
		return null;
	}

}
