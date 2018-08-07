package org.cn.kkl.dep;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class TestCharacter {
	
	char a;
	
	@Test
	public void test(){
		String valueOf = String.valueOf(a);
		System.out.println(String.valueOf(valueOf));
		System.out.println(StringUtils.isNotBlank(valueOf.trim()));
		System.out.println();
	}

}
