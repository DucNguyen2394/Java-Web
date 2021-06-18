package com.javaweb.controller.web;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.javaweb.service.ICategoryService;
import com.javaweb.service.INewService;

@WebServlet(urlPatterns = {"/trang-chu"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = -5976268535978928077L;

	@Inject
	private ICategoryService categoryService;
	
	@Inject
	private INewService newService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long categoryId = 1l;
		req.setAttribute("news", newService.findByCategoryId(categoryId));
		
		req.setAttribute("categories", categoryService.findAll());
		
		
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/home.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
