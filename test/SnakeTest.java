import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeTest {
    private Snake snake;
/** Sets up a snake class at the start of each test case.*/
    @BeforeEach
    public void setUp() throws Exception {
        snake = new Snake();
    }
    /** Checks to see if the snake is at the proper starting location*/
    @Test
    public void constructorPlacesSnakeProperly() {
        assertEquals(4, snake.getHeadX());
        assertEquals(4, snake.getHeadY());
    }
    /**Checks to see if the snake is able to move right*/
    @Test
    public void movesRightProperly() {
        snake.right();
        snake.move();
        assertEquals(4, snake.getHeadX());
        assertEquals(5, snake.getHeadY());
        assertEquals("right", snake.getDirection());
    }
    /**Checks to see if the snake doesn't move backwards */
    @Test
    public void movesLeftProperly() {
        snake.left();
        snake.move();
        assertEquals(4, snake.getHeadX());
        assertEquals(5, snake.getHeadY());
        assertNotEquals("left", snake.getDirection());
    }
    /**Checks to see if the snake is able to move down*/
    @Test
    public void movesDownProperly() {
        snake.down();
        snake.move();
        assertEquals(3, snake.getHeadX());
        assertEquals(4, snake.getHeadY());
        assertEquals("down", snake.getDirection());
    }
    /**Checks to see if the snake is able to move up*/
    @Test
    public void movesUpProperly() {
        snake.up();
        snake.move();
        assertEquals(5, snake.getHeadX());
        assertEquals(4, snake.getHeadY());
        assertEquals("up", snake.getDirection());
    }
    @Test
    /**Checks to see if the snake is able to move right*/
    public void returnsCorrectOccupiedCoords(){
        ArrayList<int[]> testCase = new ArrayList<>();
        testCase.add(new int[]{4,4});
        assertNotEquals(Arrays.toString(testCase.getFirst()), Arrays.toString(snake.getOccupied().get(1)));
        assertEquals(Arrays.toString(testCase.getFirst()), Arrays.toString(snake.getOccupied().getFirst()));
    }
    /**Checks to see if collision check works*/
    @Test
    public void collisionCheck(){
        snake.up();
        snake.move();
        assertFalse(snake.checkCollision());
        snake.addOccupiedCoords(4,4);
        assertTrue(snake.checkCollision());

    }
    /**Checks to see if the snake is able tp grow properly*/
    @Test
    public void snakeGrowth(){
        snake.up();
        snake.move();
        snake.grow();
        ArrayList<int[]> testCase = new ArrayList<>();
        testCase.add(new int[]{5,4});
        assertEquals(Arrays.toString(testCase.getFirst()), Arrays.toString(snake.getOccupied().getFirst()));
    }
    }


