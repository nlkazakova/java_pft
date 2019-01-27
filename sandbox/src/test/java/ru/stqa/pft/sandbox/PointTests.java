package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    //Расстояние - целое число, точка на одной прямой справа
    public void testDistanceIntRight(){
        Point p1 = new Point(1000,100);
        Point p2 = new Point(2000,100);
        Assert.assertEquals(p1.distance(p2), 100.0);
    }

    @Test
    //Нулевое расстояние
    public void testDistanceZero(){
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);
        Assert.assertEquals(p1.distance(p2), 0.0);
    }

    @Test
    //Расстояние - целое число, точка на одной прямой слева
    public void testDistanceIntLeft(){
        Point p1 = new Point(200,100);
        Point p2 = new Point(100,100);
        Assert.assertEquals(p1.distance(p2), 100.0);
    }

    @Test
    //Расстояние - дробное число
    public void testDistanceDouble(){
        Point p1 = new Point(300,0);
        Point p2 = new Point(200,100);
        Assert.assertEquals((double)Math.round(p1.distance(p2) * 100d) / 100d, 141.42);
    }

}
