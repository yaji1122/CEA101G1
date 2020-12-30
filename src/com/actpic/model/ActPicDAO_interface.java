package com.actpic.model;

import java.util.*;

public interface ActPicDAO_interface {
	public void insert(ActPicVO actPicVO);
	public void update(ActPicVO actPicVO);
	public void delete(String actPicNo);
	public ActPicVO findByPrimaryKey(String actPicNo);
	public byte[] getOnePic(String  actPicNo);
	public List<ActPicVO> getAll();
}
