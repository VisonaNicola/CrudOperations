package org.example.Dao;

import java.util.ArrayList;

public interface Dao<T> {
	//public DataSource ds = null;
	public ArrayList<T> getAll();
}
