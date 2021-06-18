package com.javaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaweb.dao.GenericDAO;
import com.javaweb.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {
	Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/newdb";
			String user = "root";
			String password = "ducnguyen@94";
			
			return DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}

	@Override
	public List<T> query(String sql, RowMapper<T> rowMapper, Object... parameter) {
		List<T> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			// set params
			rs = ps.executeQuery();
			while(rs.next()) {
				result.add(rowMapper.mapRow(rs));
			}
			return result;			
		}catch(SQLException e) {
			return null;
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}if(ps != null) {
					ps.close();
				}if(conn != null) {
					conn.close();
				}
			}catch(SQLException e) {
				return null;
			}
		}
	}

	

	
	
}
