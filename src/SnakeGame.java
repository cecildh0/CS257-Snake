public class SnakeGame {

    /** Board containing everything onscreen in the game, as well as serving as the logical model for the game */
    private Board board;

    /** The length of one square on the board */
    private double squareLength;

    /** Half of the length of one square on the board */
    private double halfSquareLength;

    /** Initializes the game */
    public SnakeGame() {
        board = new Board();
        squareLength = (double) 1 / board.getLength();
        halfSquareLength = squareLength / 2;
    }

    /** Main method */
    public static void main(String[] args) throws InterruptedException {
        new SnakeGame().run();
    }

    /** Handles key presses. */
    public void movement() {
        if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_A)) {
            board.snakeLeft();
        }
        if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_D)) {
            board.snakeRight();
        }
        if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_W)) {
            board.snakeUp();
        }
        if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_S)) {
            board.snakeDown();
        }
    }

    /** Draws the checkerboard background. */
    private void drawCheckerboard(int rows, int cols) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if ((r + c) % 2 == 0) {
                    StdDraw.setPenColor(170, 215, 81);
                } else {
                    StdDraw.setPenColor(162, 209, 73);
                }
                StdDraw.filledSquare(
                    squareLength * c + halfSquareLength,
                    squareLength * r + halfSquareLength,
                    halfSquareLength
                );
            }
        }
    }

    /** Draws the snake head sprite facing the snake's current direction. */
    private void drawSnakeHead(int r, int c, String imageName) {
        double x = squareLength * c + halfSquareLength;
        double y = squareLength * r + halfSquareLength;
        String direction = board.getSnakeDirection();

        if (direction.equals("right")) {
            StdDraw.picture(x, y, imageName, squareLength, squareLength);
        } else if (direction.equals("up")) {
            StdDraw.picture(x, y, imageName, squareLength, squareLength, 90);
        } else if (direction.equals("left")) {
            StdDraw.picture(x, y, imageName, squareLength, squareLength, 180);
        } else if (direction.equals("down")) {
            StdDraw.picture(x, y, imageName, squareLength, squareLength, 270);
        }
    }

    /** Draws snake segments and the apple on top of the checkerboard. */
    private void drawGridContents(String headImageName) {
        int[][] grid = board.getGrid();
        int[] head = board.getSnakeOccupied().get(0);

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    if (r == head[0] && c == head[1]) {
                        drawSnakeHead(r, c, headImageName);
                    } else {
                        StdDraw.setPenColor(68, 114, 231);
                        StdDraw.filledSquare(
                            squareLength * c + halfSquareLength,
                            squareLength * r + halfSquareLength,
                            halfSquareLength
                        );
                    }
                }
                if (grid[r][c] == 2) {
                    StdDraw.picture(
                        squareLength * c + halfSquareLength,
                        squareLength * r + halfSquareLength,
                        "apple.png",
                        squareLength,
                        squareLength
                    );
                }
            }
        }
    }

    /** Draws the checkerboard, grid contents, and pauses for the given duration. */
    private void drawFrame(String headImageName, int pauseMs) {
        int[][] grid = board.getGrid();
        StdDraw.clear(StdDraw.WHITE);
        drawCheckerboard(grid.length, grid[0].length);
        drawGridContents(headImageName);
        StdDraw.show();
        StdDraw.pause(pauseMs);
    }

    /** Draws the state of the game. */
    public void draw() {
        drawFrame("snakehead.png", 100);
    }

    /** Draws the state of the game when you get a game over. */
    public void drawGameOver() {
        drawFrame("snakeheadgameover.png", 25);
    }

    /** Runs the game */
    public void run() throws InterruptedException {
        StdDraw.enableDoubleBuffering();
        titleScreen();
        while (true) {
            boolean gameOver = board.getGameOver();
            while (!gameOver) {
                draw();
                movement();
                board.advance();
                gameOver = board.getGameOver();
            }
            drawGameOver();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(0.5, 0.75, "Game over!");
            StdDraw.text(0.5, 0.65, "Score: " + board.getSnakeOccupied().size());
            StdDraw.text(0.5, 0.25, "Press space to play again.");
            StdDraw.show();
            while (!StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_SPACE)) {
                // Wait for spacebar
            }
            board = new Board();
        }
    }

    /** Displays the game title and instructions. */
    public void titleScreen() {
        StdDraw.clear(StdDraw.WHITE);
        drawCheckerboard(board.getLength(), board.getLength());

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.picture(0.5, 0.75, "logo.png");
        StdDraw.text(0.5, 0.4, "Move with WASD");
        StdDraw.text(0.5, 0.2, "Eat apples, don't collide with yourself or the wall");
        StdDraw.text(0.5, 0.1, "Press space to start.");
        StdDraw.show();
        while (!StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_SPACE)) {
            // Wait for spacebar
        }
        StdDraw.clear(StdDraw.WHITE);
    }

}
