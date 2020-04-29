package com.lyy.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
@Slf4j
class SecurityApplicationTests {

	@Test
	void contextLoads() {
		log.info("结果：{}",Objects.equals("a",null));
	}

}
