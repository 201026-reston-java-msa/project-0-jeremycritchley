package com.dao.interfaces;

import java.util.List;

public interface GenericDAO<T> {

	public T create(T t);

	public T get(int id);

	public T update(T t);

	public boolean delete(T t);

	public List<T> getAll();

}
