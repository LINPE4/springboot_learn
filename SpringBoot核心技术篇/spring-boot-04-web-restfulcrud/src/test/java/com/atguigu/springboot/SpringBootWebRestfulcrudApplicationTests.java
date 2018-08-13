package com.atguigu.springboot;

import com.atguigu.springboot.entities.Person;
import com.atguigu.springboot.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class SpringBootWebRestfulcrudApplicationTests {

	@Autowired
	DemoService demoService;
	@Test
	public void contextLoads() {
		Person p = new Person();
		p.setAge(10);
		p.setName("fuck");
		p.setAddress("fuck add");
		demoService.savePersonWithRollBack(p);
	}

	@Test
	public void test() {
		demoService.saveAC();
	}

	@Test
	public void test2() {
		demoService.saveA();
	}

}
