package ru.stqa.pft.soap;

import org.tempuri.Calculator;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CalculatorTests {

    @Test
    public void TestSumTwoIntegers() {
        int sum = new Calculator().getCalculatorSoap12().add(5, 7);
        assertEquals(sum, 12);
    }
}
