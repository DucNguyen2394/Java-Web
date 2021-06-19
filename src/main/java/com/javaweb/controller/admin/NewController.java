package com.javaweb.controller.admin;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.javaweb.constant.SystemConstant;
import com.javaweb.model.NewModel;
import com.javaweb.service.INewService;

@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet{
private static final long serialVersionUID = -5976268535978928077L;
	
	@Inject
	private INewService newService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewModel newModel = new NewModel();
		
		newModel.setListResult(newService.findAll());
		req.setAttribute(SystemConstant.MODEL, newModel);
		
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/new/list.jsp");
		rd.forward(req, resp);
	}
}
