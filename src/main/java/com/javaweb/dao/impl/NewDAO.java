package com.javaweb.dao.impl;

import java.util.List;
import com.javaweb.dao.INewDAO;
import com.javaweb.mapper.NewMapper;
import com.javaweb.model.NewModel;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {
	
	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		String sql = "SELECT * FROM news WHERE categoryId = ?";
		return query(sql, new NewMapper(),categoryId);
	}

	@Override
	public Long save(NewModel newModel) {
		String sql = "INSERT INTO news (title, content, categoryId) VALUES (?,?,?)";
		return insert(sql, newModel.getTitle(), newModel.getContent(), newModel.getCategoryId());
	}

	@Override
	public List<NewModel> findAll(Integer offset, Integer limit) {
		String sql = "SELECT * FROM news LIMIT ?,?";
		return query(sql, new NewMapper(),offset, limit);
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT COUNT(*) FROM news";
		return count(sql);
	}
	
}
