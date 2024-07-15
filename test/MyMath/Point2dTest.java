package MyMath;

import ModelDrawer.ObjectDrawer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point2dTest {

    @Test
    void getDistanceTo1() {
        Point2d p1 = new Point2d(0, 0);
        Point2d p2 = new Point2d(100, 0);

        double expected = 100d;

        assertEquals(expected, p1.getDistanceTo(p2), 1E-6);
    }

    @Test
    void getDistanceTo2() {
        Point2d p1 = new Point2d(0, 0);
        Point2d p2 = new Point2d(100, 100);

        double expected = 141.421356237d;

        assertEquals(expected, p1.getDistanceTo(p2), 1E-6);
    }


}