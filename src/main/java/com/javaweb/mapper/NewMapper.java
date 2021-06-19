package com.javaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaweb.model.NewModel;

public class NewMapper implements RowMapper<NewModel> {

	@Override
	public NewModel mapRow(ResultSet rs) {
		NewModel newModel = new NewModel();
		try {
			newModel.setId(rs.getLong("id"));
			newModel.setTitle(rs.getString("title"));
			newModel.setThumbnail(rs.getString("thumbnail"));
			newModel.setContent(rs.getString("content"));
			newModel.setDescription(rs.getString("description"));
			newModel.setCategoryId(rs.getLong("categoryId"));
			newModel.setCreateDate(rs.getTimestamp("createDate"));
			newModel.setCreateBy(rs.getString("createBy"));
			if(rs.getTimestamp("modifiedDate") != null) {
				newModel.setModifiedDate(rs.getTimestamp("modifiedDate"));
			}
			if(rs.getString("modifiedBy") != null) {
				newModel.setModifiedBy(rs.getString("modifiedBy"));
			}
		} catch (SQLException e) {
			return null;
		}	
		return newModel;
	}
	
}
