package com.javaweb.controller.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.model.NewModel;
import com.javaweb.service.INewService;

@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet {

	private static final long serialVersionUID = -5569131350846736227L;
	@Inject INewService newService;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
//		NewModel newModel =  HttpUtil.of(req.getReader()).toModel(NewModel.class);
//		newModel = newService.save(newModel);
//		
//		mapper.writeValue(resp.getOutputStream(), newModel);
	}
	
}
