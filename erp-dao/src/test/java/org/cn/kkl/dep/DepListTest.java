package org.cn.kkl.dep;

import java.util.List;

import org.cn.kkl.erp.dao.IDepDao;
import org.cn.kkl.erp.entity.Dep;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DepListTest {
	
	private ApplicationContext applicationContext;
	private IDepDao depDao;

	@Before
	public void init(){
		applicationContext = new ClassPathXmlApplicationContext("classpath*:applicationContext_*.xml");
		depDao=(IDepDao) applicationContext.getBean("depDao");
	}
	
	@Test
	public void testDepList(){
		List<Dep> depList = depDao.getDepList();
		for (Dep dep : depList) {
			System.out.println(dep.getName()+" || "+dep.getTele()+" || "+dep.getUuid());
		}
	}

}
