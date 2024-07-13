package MyMath;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Line2dTest {


    @Test
    void isOnLine1() {
        Point2d point = new Point2d(1d, 1d);

        Point2d from = new Point2d(0d, 0d);
        Point2d to = new Point2d(1d, 1d);
        Line2d line = new Line2d(from, to);

        assertTrue(line.isOnLine(point));
    }

    @Test
    void isOnLine2() {
        Point2d point = new Point2d(4d, 4d);

        Point2d from = new Point2d(0d, 0d);
        Point2d to = new Point2d(1d, 1d);
        Line2d line = new Line2d(from, to);

        assertTrue(line.isOnLine(point));
    }

    @Test
    void isOnLine3() {
        Point2d point = new Point2d(-4d, 4d);

        Point2d from = new Point2d(0d, 0d);
        Point2d to = new Point2d(1d, 1d);
        Line2d line = new Line2d(from, to);

        assertFalse(line.isOnLine(point));
    }
    @Test
    void isOnLine3Mirror() {
        Point2d point = new Point2d(4d, -4d);

        Point2d from = new Point2d(0d, 0d);
        Point2d to = new Point2d(1d, 1d);
        Line2d line = new Line2d(from, to);

        assertFalse(line.isOnLine(point));
    }

    @Test
    void isOnLine4() {
        Point2d point = new Point2d(-4d, -4d);

        Point2d from = new Point2d(0d, 0d);
        Point2d to = new Point2d(1d, 1d);
        Line2d line = new Line2d(from, to);

        assertTrue(line.isOnLine(point));
    }

    @Test
    void projectionOnLine1Horizontal() {
        Point2d point = new Point2d(2d, 2d);
        Point2d expected = new Point2d(2d, 1d);

        Point2d from = new Point2d(0d, 1d);
        Point2d to = new Point2d(1d, 1d);
        Line2d line = new Line2d(from, to);

        assertEquals(expected, line.projectionOnLine(point));
    }
    @Test
    void projectionOnLine2Horizontal() {
        Point2d point = new Point2d(2d, 1d);
        Point2d expected = new Point2d(2d, 1d);

        Point2d from = new Point2d(0d, 1d);
        Point2d to = new Point2d(1d, 1d);
        Line2d line = new Line2d(from, to);

        assertEquals(expected, line.projectionOnLine(point));
    }
    @Test
    void projectionOnLine3Diagonal() {
        Point2d point = new Point2d(0d, 2d);
        Point2d expected = new Point2d(1d, 1d);

        Point2d from = new Point2d(0d, 0d);
        Point2d to = new Point2d(1d, 1d);
        Line2d line = new Line2d(from, to);

        assertEquals(expected, line.projectionOnLine(point));
    }
    @Test
    void projectionOnLine4Diagonal() {
        Point2d point = new Point2d(1d, 1d);
        Point2d expected = new Point2d(1d, 1d);

        Point2d from = new Point2d(0d, 0d);
        Point2d to = new Point2d(1d, 1d);
        Line2d line = new Line2d(from, to);

        assertEquals(expected, line.projectionOnLine(point));
    }


}