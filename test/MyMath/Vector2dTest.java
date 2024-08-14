package MyMath;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void getProjection1() {
        Vector2d toProject = new Vector2d(2, 2);
        Vector2d projectOn = new Vector2d(2, 0);
        Vector2d expected = new Vector2d(2, 0);
        Vector2d projection = Vector2d.getProjection(toProject, projectOn);
        assertEquals(expected, projection);
    }

    @Test
    void getProjection2() {
        Vector2d toProject = new Vector2d(-2, -2);
        Vector2d projectOn = new Vector2d(2, 0);
        Vector2d expected = new Vector2d(-2, 0);
        Vector2d projection = Vector2d.getProjection(toProject, projectOn);
        assertEquals(expected, projection);
    }
}