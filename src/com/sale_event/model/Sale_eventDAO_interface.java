package com.sale_event.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Sale_eventDAO_interface {

	public void insert(Sale_eventVO sale_eventVO);

	public void update(Sale_eventVO sale_eventVO);

	public Sale_eventVO findByPrimaryKey(String sale_no);

	public List<Sale_eventVO> getAll();
	
}
