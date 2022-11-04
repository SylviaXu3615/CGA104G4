package com.comment.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comment.model.CommentService;
import com.comment.model.CommentVO;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet(urlPatterns = { "/comment/CommentServlet", "/comment/comment.do" })
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println("我有連到喔");

		switch (action) {
		case "getByPK":
			getByPK(request, response);
			break;
		case "getAll":
			getAll(request, response);
			break;
		case "delete":
			delete(request, response);
			break;
		case "addComment":
			insert(request, response);
			break;
		case "goUpdate":
			goUpdate(request, response);
			break;
		case "update":
			uppdate(request, response);
			break;
		}

	}

	private void uppdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommentVO commentVO = new CommentVO();
		List<String> errorMsgs = new LinkedList<String>();
		request.setAttribute("errorMsgs", errorMsgs);

		Integer comtId = Integer.parseInt(request.getParameter("comtId"));
		Integer memId = Integer.parseInt(request.getParameter("memId"));
		Integer storeId = Integer.parseInt(request.getParameter("storeId"));
		Integer comtType = Integer.parseInt(request.getParameter("comtType"));
		Integer comtVal = Integer.parseInt(request.getParameter("comtVal"));
		String comtCont = request.getParameter("comtCont");
		Integer comtStat = Integer.parseInt(request.getParameter("comtStat"));
		Timestamp comtTime = null;
		try {
			comtTime = new Timestamp(
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getParameter("comtTime")).getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (comtCont == null || (comtCont.trim()).length() == 0) {
			errorMsgs.add("請輸入評論");
		}
		commentVO.setComtId(comtId);
		commentVO.setMemId(memId);
		commentVO.setStoreId(storeId);
		commentVO.setComtType(comtType);
		commentVO.setComtVal(comtVal);
		commentVO.setComtCont(comtCont);
		commentVO.setComtStat(comtStat);
		commentVO.setComtTime(comtTime);

		if (!errorMsgs.isEmpty()) {
			request.setAttribute("commentVO", commentVO);
			RequestDispatcher failureView = request.getRequestDispatcher("/comment/updateComment.jsp");
			failureView.forward(request, response);
			return;// 程式中斷
		}
		CommentService comSvc = new CommentService();
		comSvc.update(commentVO);
		getAll(request, response);

	}

	private void goUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommentService comSvc = new CommentService();
		Integer comtId = Integer.parseInt(request.getParameter("comtId"));

		CommentVO commentVO = comSvc.findByPrimaryKey(comtId);

		request.setAttribute("commentVO", commentVO);
		String url = "/comment/updateComment.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentVO commentVO = new CommentVO();
		List<String> errorMsgs = new LinkedList<String>();
		request.setAttribute("errorMsgs", errorMsgs);

		Integer memId = Integer.parseInt(request.getParameter("memId"));
		Integer storeId = Integer.parseInt(request.getParameter("storeId"));
		Integer comtType = Integer.parseInt(request.getParameter("comtType"));
		Integer comtVal = Integer.parseInt(request.getParameter("comtVal"));
		String comtCont = request.getParameter("comtCont");

		if (comtCont == null || (comtCont.trim()).length() == 0) {
			errorMsgs.add("請輸入評論");
		}

		commentVO.setMemId(memId);
		commentVO.setStoreId(storeId);
		commentVO.setComtType(comtType);
		commentVO.setComtVal(comtVal);
		commentVO.setComtCont(comtCont);

		if (!errorMsgs.isEmpty()) {
			request.setAttribute("commentVO", commentVO);
			RequestDispatcher failureView = request.getRequestDispatcher("/comment/addComment.jsp");
			failureView.forward(request, response);
			return;// 程式中斷
		}
		CommentService comSvc = new CommentService();
		comSvc.insert(commentVO);
		getAll(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentService comSvc = new CommentService();
		Integer comtId = Integer.parseInt(request.getParameter("comtId"));
		comSvc.delete(comtId);
		System.out.println(comtId);
		getAll(request, response);
	}

	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CommentService comSvc = new CommentService();
		List<CommentVO> list = comSvc.getAll();

		request.setAttribute("commentVOlist", list);
		String url = "/comment/listAllComment.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);

	}

	private void getByPK(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommentService comSvc = new CommentService();
		Integer comtId = Integer.parseInt(request.getParameter("comtId"));

		CommentVO commentVO = comSvc.findByPrimaryKey(comtId);

		request.setAttribute("commentVO", commentVO);
		String url = "/comment/listOneComment.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
