import java.util.ArrayList;

public class Board {

    /** The current board, with everything currently on it */
    private int[] [] grid;

    /** The current snake */
    private Snake snake;

    /** The coordinates of the current apple */
    private int[] apple;

    /** Whether the game is over */
    private boolean gameOver;

    /** The length of the grid */
    private int length;

    /** The previous direction the snake was facing before the current movement */
    private String snakePreviousDirection;

    /** Initializes the board */
    public Board() {
        length = 15;
        grid = new int[length] [length];
        clearBoard();
        snake = new Snake();
        createApple();
        gameOver = false;
        snakePreviousDirection = "right";
    }

    /** Returns the length of the grid */
    public int getLength() {
        return length;
    }

    /** Returns the coordinates currently occupied by the snake*/
    public ArrayList<int[]> getSnakeOccupied() {
        return snake.getOccupied();
    }

    /** Places snake in any spots that the snake should currently be occupying */
    public void checkOccupiedSnake() {
        ArrayList<int[]> occupiedSpots = snake.getOccupied();
        for (int[] occupiedSpot : occupiedSpots) {
            grid[occupiedSpot[0]][occupiedSpot[1]] = 1;
        }
    }

    /** Clears the board completely */
    public void clearBoard() {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                grid[r][c] = 0;
            }
        }
    }

    /** Refreshes the board. Does this by clearing the board, checks if the snake has overlapped with the apple and if so places another apple,
     * otherwise replaces the current apple. Then replaces the snake on the board */
    public void refreshBoard() {
        clearBoard();
        if(checkEaten()) {
            snake.grow();
            checkOccupiedSnake();
            createApple();
        }
        else {
            checkOccupiedSnake();
        }
        grid[apple[0]][apple[1]] = 2;

    }

    /** Places a new apple on a blank space on the grid */
    public void createApple() {
        ArrayList<int[]> blankSpots = new ArrayList<int[]>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 0) {
                    blankSpots.add(new int[]{r,c});
                }

            }
        }
        int appleBlank1 = StdRandom.uniformInt(blankSpots.size());
        apple = blankSpots.get(appleBlank1);

    }

    /** Returns the current board with all that is on it */
    public int[] [] getGrid() {
        return grid;
    }

    /** Returns the coordinates of the current apple */
    public int[] getApple() {
        return apple;
    }

    /** Returns if the game is over or not */
    public boolean getGameOver() {
        return gameOver;
    }

    /** Changes the current direction the snake is moving to up */
    public void snakeUp() {
        snakePreviousDirection = snake.getDirection();
        snake.up();
    }

    /** Changes the current direction the snake is moving to down */
    public void snakeDown() {
        snake.down();
    }

    /** Changes the current direction the snake is moving to left */
    public void snakeLeft() {
        snake.left();
    }

    /** Changes the current direction the snake is moving to right */
    public void snakeRight() {
        snake.right();
    }

    /** Places a new apple on a blank space on the grid */
    public boolean checkEaten() {
        ArrayList<int[]> occupiedSpots = snake.getOccupied();
        for (int[] occupiedSpot : occupiedSpots) {
            if(occupiedSpot[0] == apple[0] && occupiedSpot[1] == apple[1]) {
                return true;
            }
        }
        return false;
    }

    /** Moves the snake forward once, then refreshes the board */
    public void advance() {
        boolean possibleMove = snake.move();
        if (!possibleMove) {
            gameOver = true;
            return;
        }
        refreshBoard();
    }

    /** Returns the direction the snake is currently facing. For graphic purposes. */
    public String getSnakeDirection() {
        return snake.getDirection();
    }

}
