package CollideModel.Movable;

import MyMath.Vector2d;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovableObjectTest {

    private static CircleModel circle;

    @BeforeEach
    void init(){
        circle = new CircleModel(0, 0, 10);
        circle.setStableAtDirection(new Vector2d(1, 0));
        circle.setStableAtDirection(new Vector2d(0, 1));
    }

    @Test
    void getClearedBlockedDirections1() {
        Vector2d toClear = new Vector2d(10, 10);
        Vector2d cleared = circle.getClearedBlockedDirections(toClear);
        Vector2d expected = new Vector2d(0, 0);

        assertEquals(expected, cleared);
    }

    @Test
    void getClearedBlockedDirections2() {
        Vector2d toClear = new Vector2d(10, -10);
        Vector2d cleared = circle.getClearedBlockedDirections(toClear);
        Vector2d expected = new Vector2d(0, -10);

        assertEquals(expected, cleared);
    }

    @Test
    void getClearedBlockedDirections3() {
        Vector2d toClear = new Vector2d(-10, -10);
        Vector2d cleared = circle.getClearedBlockedDirections(toClear);
        Vector2d expected = new Vector2d(-10, -10);

        assertEquals(expected, cleared);
    }
}