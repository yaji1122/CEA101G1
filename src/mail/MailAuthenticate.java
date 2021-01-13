package mail;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import jedispool.JedisPoolUtil;
public class MailAuthenticate {

	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	
	public static String getAuthCode() {
		StringBuffer code = new StringBuffer();
		String elements = "01234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < 8; i++)
			code.append(elements.charAt((int) (Math.random() * elements.length())));
		return code.toString();
	}
	
	public String insertCode(String mb_id) {
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		String code = getAuthCode();
		jedis.set(mb_id, code);
		jedis.close();
		return code;
	}
	
	public boolean verifyCode(String mb_id, String code) {
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		if (jedis.get(mb_id) != null && jedis.get(mb_id).equals(code)) {
			jedis.close();
			return true;
		} else {
			jedis.close();
			return false;
		}
	}
}
