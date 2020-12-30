package com.choppers.model;

import java.util.List;

public interface ChoppersDAO_interface {
	public void insert(ChoppersVO chopvo);
	public void update(ChoppersVO chopvo);
	public void update_status(String chop_no, String chop_status);
	public void update_pic(String chop_no, byte[] chop_pic);
	public void delete(String chop_no);
	public List<ChoppersVO> getAll();
	public ChoppersVO getOneByChopNo(String chop_no);
}
