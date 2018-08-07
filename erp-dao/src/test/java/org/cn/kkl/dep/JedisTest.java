package org.cn.kkl.dep;


import org.cn.kkl.erp.redis.dao.JedisClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class JedisTest {
	
	private ApplicationContext applicationContext;
	private JedisClient jedisClient;

	@Before
	public void init(){
		applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-redis.xml");
		jedisClient = (JedisClient) applicationContext.getBean(JedisClient.class);
	}
	
	@Test
	public void add(){
		jedisClient.set("erp", "test-erp-01");
	}
	
	@Test
	public void get(){
		String string = jedisClient.get("erp");
		System.out.println(string);
	}
	
	@Test
	public void del(){
		jedisClient.del("erp");
	}

}
