package com.crescentd;

import com.crescentd.entity.User;
import com.crescentd.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EblogApplicationTests {
	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
		User user = userService.getById(1L);
		System.out.println(user);
	}

}
