package com.pickup.model;

import java.util.List;

public interface PickupDAO_interface {
	public PickupVO insert(PickupVO pkupvo);
	public void update(PickupVO pkupvo);
	public void updatePkupTime(String pkup_no, String pkup_status);
	public void delete(String pkup_no);
	public List<PickupVO> getAll();
	public List<PickupVO> getAllByPkupStatus(String pkup_status);
	public List<PickupVO> getAllByBkNo(String bk_no);
	public PickupVO getOneByPkupNo(String pkup_no);
	
	
}
