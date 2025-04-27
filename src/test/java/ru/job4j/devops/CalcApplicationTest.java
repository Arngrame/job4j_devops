package ru.job4j.devops;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalcApplicationTest {

    @Test
    void contextLoads() {
    }

    @Test
    void mainMethodTest() {
        CalcApplication.main(new String[] {});
    }
}