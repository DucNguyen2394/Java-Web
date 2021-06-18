package com.javaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaweb.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel> {

	@Override
	public CategoryModel mapRow(ResultSet rs) {
		CategoryModel categoryModel = new CategoryModel();
		try {
			categoryModel.setId(rs.getLong("id"));
			categoryModel.setName(rs.getString("name"));
			categoryModel.setCode(rs.getString("code"));
		} catch (SQLException e) {
			return null;
		}
		return categoryModel;
	}
	
	
}
