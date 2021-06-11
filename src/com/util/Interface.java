package com.util;

import java.util.List;

public interface Interface<T> {
	
	public List<T> list();

	public <E> T find(E id);

	public void insert(T o);

	public void update(T o);

	public <E> void delete(E o);

	public <E> T findByField(String column, E o);

	public <E> List<T> findByFieldList(String column, E o);

	public long count();
}
