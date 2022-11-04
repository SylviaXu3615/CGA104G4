package com.comment.model;

import java.util.List;

import com.basicDAO.BasicDAO_interface;

public interface CommentDAO_Interface extends BasicDAO_interface<CommentVO> {

	public List<CommentVO> getAllPK();

	public List<Integer> getStoreId();

}
