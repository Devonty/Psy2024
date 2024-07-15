package CollideModel.Movable;

import MyMath.Point2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleModelTest {

    @Test
    void calcClosestPointTo() {
        CircleModel c1 = new CircleModel(0, 0, 100);
        CircleModel c2 = new CircleModel(100, 0, 100);

        c1.collide(c2);

        Point2d expected1 = new Point2d(-50, 0);
        Point2d expected2 = new Point2d(150, 0);

        assertEquals(expected1, c1.center());
        assertEquals(expected2, c2.center());
    }

    @Test
    void isPointInside() {
    }
}