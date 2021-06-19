package com.javaweb.service;

import java.util.List;
import com.javaweb.model.NewModel;

public interface INewService {
	List<NewModel> findByCategoryId(Long categoryId);
	
	NewModel save(NewModel newModel);
	
	List<NewModel> findAll();
	
	void delete(Long[] id);
}
