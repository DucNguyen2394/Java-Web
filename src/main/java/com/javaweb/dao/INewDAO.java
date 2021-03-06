package com.javaweb.dao;

import java.util.List;
import com.javaweb.model.NewModel;

public interface INewDAO extends GenericDAO<NewModel> {
	List<NewModel> findByCategoryId(Long categoryId);
	
	Long save(NewModel newModel);
	
	List<NewModel> findAll(Integer offset, Integer limit);
	
	int getTotalItem();
}	

