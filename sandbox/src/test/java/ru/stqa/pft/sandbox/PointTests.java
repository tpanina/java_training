package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static ru.stqa.pft.sandbox.MyFirstProgram.distance;

public class PointTests {

    @Test
    public void testDistanceMethod() {
        Point p1 = new Point(1,2);
        Point p2 = new Point(2,3);
        Assert.assertEquals(p1.distance(p2), 1.4142135623730951);
    }

    @Test
    public void testDistanceFunction() {
        Point p1 = new Point(1,2);
        Point p2 = new Point(2,3);
        Assert.assertEquals(distance(p1, p2), 1.4142135623730951);
    }
}
