package com.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.notice.model.NoticeService;
import com.notice.model.NoticeVO;

@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		NoticeService noticeService = new NoticeService();

		List<NoticeVO> notices = noticeService.getAll();

		for (NoticeVO noticeVO : notices) {
			System.out.println(noticeVO);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
