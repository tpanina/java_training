package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance() {
        Point p = new Point(1,2);
        Assert.assertEquals(p.distance(), 2.23606797749979);
    }
}
