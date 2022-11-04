package com.comment.model;

import java.util.List;

import com.member.model.MemberDAO;
import com.member.model.MemberDAO_interface;

public class CommentService {
	CommentDAO_Interface dao = new CommentDAO();

	public List<CommentVO> getAllPK() {
		return dao.getAllPK();
	}

	public List<Integer> getMemId() {
		MemberDAO_interface memdao = new MemberDAO();
		return memdao.getMemId();
	}

	public List<Integer> getStoreId() {

		return dao.getStoreId();
	}

	public CommentVO findByPrimaryKey(Integer comtId) {

		return dao.findByPrimaryKey(comtId);

	}

	public List<CommentVO> getAll() {

		return dao.getAll();
	}

	public void delete(Integer comtId) {
		dao.delete(comtId);

	}

	public void insert(CommentVO commentVO) {
		dao.insert(commentVO);

	}

	public void update(CommentVO commentVO) {
		dao.update(commentVO);

	}

}
