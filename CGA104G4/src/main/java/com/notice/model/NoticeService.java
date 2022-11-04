package com.notice.model;

import java.util.List;

public class NoticeService {

	NoticeDAO_interface dao = new NoticeDAO();

	public List<NoticeVO> getAll() {

		return dao.getAll();
	}

}
