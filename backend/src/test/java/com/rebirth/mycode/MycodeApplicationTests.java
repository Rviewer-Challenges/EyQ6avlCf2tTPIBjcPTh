package com.rebirth.mycode;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@CucumberContextConfiguration
@SpringBootTest(classes = MycodeApplication.class)
class MycodeApplicationTests {

    @Test
    void contextLoads() {
    }

}
