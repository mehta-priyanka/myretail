package com.myretail.myretailApp;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.myretail.myretailApp.controller.ProductControllerTest;
import com.myretail.myretailApp.service.ProductServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ProductControllerTest.class,ProductServiceTest.class
})

@SpringBootTest
@AutoConfigureMockMvc
class MyretailAppApplicationTests {

	@Test
	void contextLoads() {
	}

}
