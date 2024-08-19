package GameParts;

import CollideModel.Movable.CircleModel;
import CollideModel.Movable.MovableObject;
import CollideModel.NotMovable.NotMovableObject;
import CollideModel.NotMovable.WallModel;
import Components.CollideComponent.CollideController;
import MyMath.Point2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollideControllerTest {

    @Test
    void collide1() {
        MovableObject circle = new CircleModel(0, 10, 10);
        NotMovableObject wall = new WallModel(new Point2d(-10, 0), new Point2d(10, 0), 4);

        CollideController.collide(circle, wall);

        Point2d circleCenterExpected = new Point2d(0, 14);
        assertEquals(circleCenterExpected, circle.center());
    }
    @Test
    void collide2() {
        MovableObject circle = new CircleModel(1, 10, 10);
        NotMovableObject wall = new WallModel(new Point2d(-10, 0), new Point2d(10, 0), 2);

        CollideController.collide(circle, wall);

        Point2d circleCenterExpected = new Point2d(1, 12);
        assertEquals(circleCenterExpected, circle.center());
    }

    @Test
    void testBlockDirections1() {
        /*
        NOT FINISHED
         */
        MovableObject circleTop = new CircleModel(1, 32, 10);
        MovableObject circleBottom = new CircleModel(1, 12, 10);
        NotMovableObject wall = new WallModel(new Point2d(-10, 0), new Point2d(10, 0), 2);

        CollideController.collide(circleTop, wall);
        CollideController.collide(circleBottom, wall);
        CollideController.collide(circleBottom, circleTop);

        Point2d circleCenterExpected = new Point2d(1, 12);
        assertEquals(circleCenterExpected, circleBottom.center());
    }
}