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
import com.javaweb.utils.FormUtil;

@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet{
	
private static final long serialVersionUID = -5976268535978928077L;
	
	@Inject
	private INewService newService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewModel newModel = FormUtil.toModel(NewModel.class, req);
		
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
