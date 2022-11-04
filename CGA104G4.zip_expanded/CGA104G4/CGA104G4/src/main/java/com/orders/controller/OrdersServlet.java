package com.orders.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;

public class OrdersServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			String str = req.getParameter("ordId");
			
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("錯誤");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/orders/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer ordId = null;
			
			try {
				ordId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("錯誤");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/orders/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			OrdersService ordersSvc = new OrdersService();
			OrdersVO ordersVO = ordersSvc.getOneOrders(ordId);
			if (ordersVO == null) {
				errorMsgs.add("錯誤");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/orders/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			req.setAttribute("ordersVO", ordersVO);
			String url = "/orders/listOneOrders.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			Integer ordId = Integer.valueOf(req.getParameter("ordId"));

			OrdersService ordersSvc = new OrdersService();
			OrdersVO ordersVO = ordersSvc.getOneOrders(ordId);

			req.setAttribute("ordersVO", ordersVO);
			String url = "/orders/update_orders_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			Integer ordId = Integer.valueOf(req.getParameter("ordId").trim());
			Integer memId = Integer.valueOf(req.getParameter("memId").trim());
			Integer storeId = Integer.valueOf(req.getParameter("storeId").trim());
			
			Integer ordAmt = null;
			try {
				ordAmt = Integer.valueOf(req.getParameter("ordAmt").trim());
			} catch (NumberFormatException e) {
				ordAmt = 0;
				errorMsgs.add("訂單金額錯誤");
			}

			Integer ordStat = null;
			try {
				ordStat = Integer.valueOf(req.getParameter("ordStat").trim());
			} catch (NumberFormatException e) {
				ordStat = 0;
				errorMsgs.add("訂單狀態錯誤");
			}

			Date ordTime = null;
			try {
				ordTime = Date.valueOf(req.getParameter("ordTime").trim());
			} catch (IllegalArgumentException e) {
				ordTime = new Date(System.currentTimeMillis());
				errorMsgs.add("訂單成立時間錯誤");
			}

			OrdersVO ordersVO = new OrdersVO();
			ordersVO.setOrdId(ordId);
			ordersVO.setMemId(memId);
			ordersVO.setStoreId(storeId);
			ordersVO.setOrdAmt(ordAmt);
			ordersVO.setOrdStat(ordStat);
			ordersVO.setOrdTime(ordTime);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("ordersVO", ordersVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/orders/update_orders_input.jsp");
				failureView.forward(req, res);
				return;
			}

			OrdersService ordersSvc = new OrdersService();
			ordersVO = ordersSvc.updateOrders(ordId, memId, storeId, ordAmt, ordStat, ordTime);

			req.setAttribute("ordersVO", ordersVO);
			String url = "/orders/listOneOrders.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			Integer ordId = Integer.valueOf(req.getParameter("ordId").trim());
			Integer memId = Integer.valueOf(req.getParameter("memId").trim());
			Integer storeId = Integer.valueOf(req.getParameter("storeId").trim());
			
			Integer ordAmt = null;
			try {
				ordAmt = Integer.valueOf(req.getParameter("ordAmt").trim());
			} catch (NumberFormatException e) {
				ordAmt = 0;
				errorMsgs.add("訂單金額錯誤");
			}

			Integer ordStat = null;
			try {
				ordStat = Integer.valueOf(req.getParameter("ordStat").trim());
			} catch (NumberFormatException e) {
				ordStat = 0;
				errorMsgs.add("訂單狀態錯誤");
			}

			Date ordTime = null;
			try {
				ordTime = Date.valueOf(req.getParameter("ordTime").trim());
			} catch (IllegalArgumentException e) {
				ordTime = new Date(System.currentTimeMillis());
				errorMsgs.add("訂單成立時間錯誤");
			}

			OrdersVO ordersVO = new OrdersVO();
			ordersVO.setOrdId(ordId);
			ordersVO.setMemId(memId);
			ordersVO.setStoreId(storeId);
			ordersVO.setOrdAmt(ordAmt);
			ordersVO.setOrdStat(ordStat);
			ordersVO.setOrdTime(ordTime);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("ordersVO", ordersVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/orders/addOrders.jsp");
				failureView.forward(req, res);
				return;
			}

			OrdersService ordersSvc = new OrdersService();
			ordersVO = ordersSvc.addOrders(ordId, memId, storeId, ordAmt, ordStat, ordTime);

			String url = "/orders/listAllOrders.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			Integer ordId = Integer.valueOf(req.getParameter("ordId"));

			OrdersService ordersSvc = new OrdersService();
			ordersSvc.deleteOrders(ordId);

			String url = "/orders/listAllOrders.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}