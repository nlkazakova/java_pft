package ru.stqa.pft.sandbox;

import com.sun.source.tree.AssertTree;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    //Проверка расстояния по прямой
    public void testDistanceLine(){
        Point p1 = new Point(100,100);
        Point p2 = new Point(200,100);
        Assert.assertEquals(p1.distance(p2), 100.0);
    }

    @Test
    //Проверка нулевого расстояния
    public void testDistanceZero(){
        Point p1 = new Point(100,100);
        Point p2 = new Point(100,100);
        Assert.assertEquals(p1.distance(p2), 0.0);
    }

    @Test
    //Проверка расстояния точки в другую сторону
    public void testDistanceNegative(){
        Point p1 = new Point(200,100);
        Point p2 = new Point(100,100);
        Assert.assertEquals(p1.distance(p2), 100.0);
    }

    @Test
    //Проверка расстояния по диагонали
    public void testDistanceDiagonal(){
        Point p1 = new Point(300,0);
        Point p2 = new Point(200,100);
        Assert.assertEquals((double)Math.round(p1.distance(p2) * 100d) / 100d, 141.42);
    }

}
