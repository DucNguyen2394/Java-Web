package com.javaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	public List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			// set params
			setParameter(ps, parameters);
			
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

	private void setParameter(PreparedStatement ps, Object... parameters) {
		try {
			for(int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i + 1;
				if(parameter instanceof Long) {
						ps.setLong(index,(Long) parameter);
					}
				else if(parameter instanceof Integer) {
					ps.setInt(index, (Integer) parameter);
				}
				else if(parameter instanceof String) {
					ps.setString(index, (String) parameter);
				}
				else if(parameter instanceof Timestamp) {
					ps.setTimestamp(index, (Timestamp) parameter);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(String sql, Object... parameter) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			setParameter(ps, parameter);
			ps.executeUpdate();
			conn.commit();
		}catch(SQLException e) {
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}if(conn != null) {
					conn.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Long insert(String sql, Object... parameter) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Long id = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
			setParameter(ps, parameter);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				id = rs.getLong(1);
			}
			conn.commit();
			return id;
		}catch(SQLException e) {
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}if(conn != null) {
					conn.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
