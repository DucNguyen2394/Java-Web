package com.javaweb.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtil {
	
	public static <T> T toModel(Class<T> tClass, HttpServletRequest req) {
		T object = null;
		try {
			object = tClass.newInstance();
			try {
				BeanUtils.populate(object, req.getParameterMap());
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (InstantiationException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}
		return object;
	}
}
