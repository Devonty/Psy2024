package MyMath;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Segment2dTest {

    @Test
    void isOnLineStart() {
        Point2d point = new Point2d(0d, 0d);

        Point2d from = new Point2d(0d, 0d);
        Point2d to = new Point2d(1d, 1d);
        Segment2d segment2d = new Segment2d(from, to);

        assertTrue(segment2d.isOnLine(point));
    }

    @Test
    void isOnSegmentEnd() {
        Point2d point = new Point2d(1d, 1d);

        Point2d from = new Point2d(0d, 0d);
        Point2d to = new Point2d(1d, 1d);
        Segment2d segment2d = new Segment2d(from, to);

        assertTrue(segment2d.isOnSegment(point));
    }
    @Test
    void isOnSegment1False() {
        Point2d point = new Point2d(2d, 2d);

        Point2d from = new Point2d(0d, 0d);
        Point2d to = new Point2d(1d, 1d);
        Segment2d segment2d = new Segment2d(from, to);

        assertFalse(segment2d.isOnSegment(point));
    }

    @Test
    void isOnSegment2True() {
        Point2d point = new Point2d(0.5d, 0.5d);

        Point2d from = new Point2d(0d, 0d);
        Point2d to = new Point2d(1d, 1d);
        Segment2d segment2d = new Segment2d(from, to);

        assertTrue(segment2d.isOnSegment(point));
    }
    @Test
    void isOnSegment3False() {
        Point2d point = new Point2d(-2d, -2d);

        Point2d from = new Point2d(0d, 0d);
        Point2d to = new Point2d(1d, 1d);
        Segment2d segment2d = new Segment2d(from, to);

        assertFalse(segment2d.isOnSegment(point));
    }
    @Test
    void isOnSegment4False() {
        Point2d point = new Point2d(0d, 0.5d);

        Point2d from = new Point2d(0d, 0d);
        Point2d to = new Point2d(1d, 1d);
        Segment2d segment2d = new Segment2d(from, to);

        assertFalse(segment2d.isOnSegment(point));
    }
}