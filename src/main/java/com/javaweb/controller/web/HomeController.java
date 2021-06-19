package com.javaweb.controller.web;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.model.NewModel;
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
		req.setAttribute("categories", categoryService.findAll());
		
		NewModel newModel = new NewModel();
		String title = "Bai viet truyen";
		String content = "kenshin";
		Long categoryId = 2l;
		newModel.setTitle(title);
		newModel.setContent(content);
		newModel.setCategoryId(categoryId);
		newService.save(newModel);
		
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/home.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}

