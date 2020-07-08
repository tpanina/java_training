package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistanceMethod() {
        Point p1 = new Point(1,2);
        Point p2 = new Point(2,3);
        Assert.assertEquals(p1.distance(p2), 1.4142135623730951);
    }

    @Test
    public void testDistanceMethod2() {
        Point p1 = new Point(5,3);
        Point p2 = new Point(3,3);
        Assert.assertEquals(p1.distance(p2), 2.0);
    }

    @Test
    public void testDistanceMethod3() {
        Point p1 = new Point(1,-1);
        Point p2 = new Point(-2,4);
        Assert.assertEquals(p1.distance(p2), 5.830951894845301);
    }
}
