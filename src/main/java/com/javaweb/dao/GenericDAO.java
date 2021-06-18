package com.javaweb.dao;

import java.util.List;

import com.javaweb.mapper.RowMapper;

public interface GenericDAO<T> {
	List<T> query(String sql, RowMapper<T> rowMapper ,Object... parameter);
}
