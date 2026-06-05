import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;

    /**Creates a new board at the start of each test case*/
    @BeforeEach
    public void setUp() throws Exception {
        board = new Board();


    }
    /**Checks to see if the board has the correct dimensions when the created*/
    @Test
    public void correctDimensions() {
        assertEquals(15, board.getGrid().length);
        assertEquals(15, board.getGrid()[0].length);
    }
    /**Checks to see if an apple exists */
    @Test
    public void appleExists() {
        assertNotNull(board.getApple());
    }
    /**Checks to see if board clears the initial spot the snakes resides in at the start*/
    @Test
    public void clearBoardFunctionality () {
        board.clearBoard();
        assertEquals(0,board.getGrid()[4][4]);

    }
    /**Checks to see if snake is occupying space on the grid */
    @Test
    public void occupiedSnakeExists () {
        board.advance();
        assertEquals(1,board.getGrid()[4][4]);
    }
    /**Checks to see if snake is occupying space on the grid when it moves */
    @Test
    public void advanceMovesSnake(){
        board.snakeLeft();
        board.advance();
        board.refreshBoard();
        assertEquals(1,board.getGrid()[4][5]);
    }








}
