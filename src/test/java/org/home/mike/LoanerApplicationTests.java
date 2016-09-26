package org.home.mike;

import org.home.mike.util.IntegrationTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Category(IntegrationTest.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanerApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void test(){
		System.out.println("hello");
	}

}
