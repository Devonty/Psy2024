package GameParts;

import CollideModel.Movable.CircleModel;
import CollideModel.Movable.MovableObject;
import CollideModel.NotMovable.NotMovableObject;
import CollideModel.NotMovable.WallModel;
import GameParts.FieldParts.CollideController;
import MyMath.Point2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollideControllerTest {

    @Test
    void collide1() {
        MovableObject circle = new CircleModel(0, 10, 10);
        NotMovableObject wall = new WallModel(new Point2d(-10, 0), new Point2d(10, 0), 2);

        CollideController.collide(circle, wall);

        Point2d circleCenterExpected = new Point2d(0, 12);
        assertEquals(circle.center(), circleCenterExpected);
    }
    @Test
    void collide2() {
        MovableObject circle = new CircleModel(1, 10, 10);
        NotMovableObject wall = new WallModel(new Point2d(-10, 0), new Point2d(10, 0), 2);

        CollideController.collide(circle, wall);

        Point2d circleCenterExpected = new Point2d(1, 12);
        assertEquals(circle.center(), circleCenterExpected);
    }
}