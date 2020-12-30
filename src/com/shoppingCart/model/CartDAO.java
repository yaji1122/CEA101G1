package com.shoppingCart.model;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.members.model.*;
import com.item.model.*;
import org.json.*;
import redis.clients.jedis.*;

public class CartDAO implements CartDAO_interface {
	String host = "localhost";
	Integer port = 6379;
	String passwd = "123456";

//	private HttpServletRequest req;
//	HttpSession session = req.getSession();
//	String user_session_id = (String) session.getAttribute("user_session_id");
	
	@Override
	public void insert(MembersVO membersVO, ItemVO itemVO) {
		Jedis jedis = null;
		JedisPool pool = null;
		try {
			pool = new JedisPool(host, port);
			jedis = pool.getResource();
			jedis.auth(passwd);
			
			jedis.rpush(membersVO.getMb_id() + ":2",itemVO.getItem_no());
			jedis.hset(membersVO.getMb_id() + ":1",itemVO.getItem_no(),itemVO.getQuantity().toString());
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		// Clean up Jedis resources
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if (pool != null) {
				try {
					pool.destroy();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
//	public void insertCo(String user_session_id, ItemVO itemVO) {
//		Jedis jedis = null;
//		JedisPool pool = null;
//		try {
//			pool = new JedisPool(host, port);
//			jedis = pool.getResource();
//			jedis.auth(passwd);
//			
//			jedis.rpush(user_session_id + ":2",itemVO.getItem_no());
//			jedis.hset(user_session_id + ":1",itemVO.getItem_no(),itemVO.getQuantity().toString());
//			
//			
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		// Clean up Jedis resources
//		} finally {
//			if (jedis != null) {
//				try {
//					jedis.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//			if (pool != null) {
//				try {
//					pool.destroy();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
	
	@Override
	public void update(MembersVO membersVO, ItemVO itemVO) {
		Jedis jedis = null;
		JedisPool pool = null;
		
		try {
			pool = new JedisPool(host, port);
			jedis = pool.getResource();
			jedis.auth(passwd);
			
			jedis.hincrBy(membersVO.getMb_id() + ":1", itemVO.getItem_no(), itemVO.getQuantity());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		// Clean up Jedis resources
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if (pool != null) {
				try {
					pool.destroy();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void replace(MembersVO membersVO, ItemVO itemVO) {
		Jedis jedis = null;
		JedisPool pool = null;
		
		try {
			pool = new JedisPool(host, port);
			jedis = pool.getResource();
			jedis.auth(passwd);
			
			jedis.hset(membersVO.getMb_id() + ":1", itemVO.getItem_no(), itemVO.getQuantity().toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		// Clean up Jedis resources
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if (pool != null) {
				try {
					pool.destroy();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void delete(MembersVO membersVO, ItemVO itemVO) {
		Jedis jedis = null;
		JedisPool pool = null;
		try {
			pool = new JedisPool(host, port);
			jedis = pool.getResource();
			jedis.auth(passwd);
			
			jedis.hdel(membersVO.getMb_id() + ":1", itemVO.getItem_no(), itemVO.getQuantity().toString());
			jedis.lrem(membersVO.getMb_id() + ":2",1, itemVO.getItem_no());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		// Clean up Jedis resources
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if (pool != null) {
				try {
					pool.destroy();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
	}
	
	@Override
	public List<String> getAllItem_noByMb_id(String mb_id) {
		List<String> list = new ArrayList<String>();
		Jedis jedis = null;
		JedisPool pool = null;
		try {
			
			pool = new JedisPool(host, port);
			jedis = pool.getResource();
			jedis.auth(passwd);
			
			list = jedis.lrange(mb_id + ":2",0,-1);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		// Clean up Jedis resources
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if (pool != null) {
				try {
					pool.destroy();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public Integer getValueByItem_no(String mb_id,String item_no) {
		Integer quantity = 0;
		Jedis jedis = null;
		JedisPool pool = null;
		try {
			
			pool = new JedisPool(host, port);
			jedis = pool.getResource();
			jedis.auth(passwd);

			quantity = new Integer(jedis.hget(mb_id + ":1", item_no));

				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		// Clean up Jedis resources
		} finally {
			if (jedis != null) {
				try {
					jedis.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if (pool != null) {
				try {
					pool.destroy();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return quantity;
	}
	
	
	public static void main(String[] args) {

		CartDAO dao = new CartDAO();

		// 新增
		ItemVO itemVO = new ItemVO();
		itemVO.setItem_no("I0007");
		itemVO.setQuantity(61);	
		MembersVO membersVO = new MembersVO();
		membersVO.setMb_id("MEM0000001");
		dao.insert(membersVO, itemVO);

		// 增加
		ItemVO itemVO2 = new ItemVO();
		itemVO2.setItem_no("I0012");
		itemVO2.setQuantity(20);	
		MembersVO membersVO2 = new MembersVO();
		membersVO2.setMb_id("MEM0000002");
		dao.update(membersVO2, itemVO2);

		//刪除
//		ItemVO itemVO3 = new ItemVO();
//		itemVO3.setItem_no("I0010");
//		itemVO3.setQuantity(61);	
//		MembersVO membersVO3 = new MembersVO();
//		membersVO3.setMb_id("MEM0000002");
//		dao.delete(membersVO3,itemVO3);
		
		//查詢
		
		System.out.println(dao.getAllItem_noByMb_id("MEM0000001"));
		System.out.println(dao.getValueByItem_no("MEM0000001","I0001"));		
	}
}