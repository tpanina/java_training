package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance() {
        Point p1 = new Point(1,2);
        Point p2 = new Point(2,3);
        Assert.assertEquals(p1.distance(p1,p2), 1.4142135623730951);
    }
}
