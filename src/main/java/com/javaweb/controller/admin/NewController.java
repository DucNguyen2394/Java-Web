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
		String pageStr = req.getParameter("page");
		String maxPagestr = req.getParameter("maxPageItem");
		if(pageStr != null) {
			newModel.setPage(Integer.parseInt(pageStr));
		}else {
			newModel.setPage(1);
		}
		if(maxPagestr != null) {
			newModel.setMaxPageItem(Integer.parseInt(maxPagestr));
		}
		
		Integer offset = (newModel.getPage() - 1) * newModel.getMaxPageItem();
		
		newModel.setListResult(newService.findAll(offset,newModel.getMaxPageItem()));
		
		req.setAttribute(SystemConstant.MODEL, newModel);
		
		newModel.setTotalItem(newService.getTotalItem());
		
		newModel.setTotalPage((int) Math.ceil((double)newModel.getTotalItem() / newModel.getMaxPageItem() ));
		
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/new/list.jsp");
		rd.forward(req, resp);
	}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			super.doPost(req, resp);
		}
}
