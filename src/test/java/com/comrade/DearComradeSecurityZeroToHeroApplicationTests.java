package com.comrade;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
class DearComradeSecurityZeroToHeroApplicationTests {

	@WithMockUser("shiva")
	@Test
	void contextLoads() {
	}

}
