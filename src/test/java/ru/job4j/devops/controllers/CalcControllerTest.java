package ru.job4j.devops.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import ru.job4j.devops.models.Result;
import ru.job4j.devops.models.TwoArgs;
import ru.job4j.devops.services.fake.ResultFakeService;

import static org.assertj.core.api.Assertions.assertThat;

class CalcControllerTest {

    private static ResultFakeService resultFakeService;
    private static CalcController calcController;

    @BeforeAll
    public static void init() {
        resultFakeService = new ResultFakeService();
        calcController = new CalcController(resultFakeService);
    }

    @Test
    public void whenOnePlusOneThenTwo() {
        TwoArgs input = new TwoArgs(1, 1);
        var actualResult = calcController.summarise(input);

        Result expectedResult = new Result();
        expectedResult.setResult(2D);

        assertThat(actualResult.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(actualResult.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void whenNegativeNumber() {
        TwoArgs input = new TwoArgs(-1, -1);
        var actualResult = calcController.summarise(input);

        Result expectedResult = new Result();
        expectedResult.setResult(-2D);

        assertThat(actualResult.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(actualResult.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void whenZeroPlusZero() {
        TwoArgs input = new TwoArgs(0, 0);
        var actualResult = calcController.summarise(input);

        Result expectedResult = new Result();
        expectedResult.setResult(0D);

        assertThat(actualResult.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(actualResult.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void whenTwoTimesTwoThenFour() {
        TwoArgs input = new TwoArgs(2, 2);
        var actualResult = calcController.times(input);

        Result expectedResult = new Result();
        expectedResult.setResult(4D);

        assertThat(actualResult.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(actualResult.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void whenZeroTimesZero() {
        TwoArgs input = new TwoArgs(0, 0);
        var actualResult = calcController.times(input);

        Result expectedResult = new Result();
        expectedResult.setResult(0D);

        assertThat(actualResult.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(actualResult.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void whenTimesNegatives() {
        TwoArgs input = new TwoArgs(-3, -3);
        var actualResult = calcController.times(input);

        Result expectedResult = new Result();
        expectedResult.setResult(9D);

        assertThat(actualResult.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(actualResult.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void whenNegativeAndZeroNumbers() {
        TwoArgs input = new TwoArgs(0, -1);
        var actualResult = calcController.summarise(input);

        Result expectedResult = new Result();
        expectedResult.setResult(-1D);

        assertThat(actualResult.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(actualResult.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void whenNegativeAndPositiveNumbers() {
        TwoArgs input = new TwoArgs(-10, 10);
        var actualResult = calcController.summarise(input);

        Result expectedResult = new Result();
        expectedResult.setResult(0D);

        assertThat(actualResult.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(actualResult.getBody()).isEqualTo(expectedResult);
    }
}