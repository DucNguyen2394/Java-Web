package com.javaweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaweb.dao.INewDAO;
import com.javaweb.model.NewModel;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {
	
	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		List<NewModel> result = new ArrayList<>();
		String sql = "SELECT * FROM news WHERE categoryId = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		if(conn != null) {
			try {
				ps = conn.prepareStatement(sql);
				ps.setLong(1, categoryId);
				rs = ps.executeQuery();
				while(rs.next()) {
					NewModel newModel = new NewModel();
					newModel.setId(rs.getLong("id"));
					newModel.setTitle(rs.getString("title"));
					newModel.setCategoryId(categoryId);
					result.add(newModel);
				}
				return result;
			} catch (SQLException e) {
				return null;
			}
		}
		return null;
	}

}
