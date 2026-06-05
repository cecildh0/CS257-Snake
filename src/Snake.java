import java.util.ArrayList;
import java.util.Arrays;

public class Snake {

    /** The x coordinate of the head of the snake */
    private int headX;

    /** The y coordinate of the head of the snake */
    private int headY;

    /** The current direction the snake is facing */
    private String direction;

    /** The x coordinate of previous position of the snake's tail */
    private int lastX;

    /** The x coordinate of the previous position of the snake's tail */
    private int lastY;

    private ArrayList<int[]> occupiedCoords;

    /** Initializes the snake */
    public Snake(){
        headX = 4;
        headY = 4;
        lastX = 4;
        lastY = 2;
        direction = "right";
        occupiedCoords = new ArrayList<int[]>();
        occupiedCoords.add(new int[]{4,4});
        occupiedCoords.add(new int[]{4,3});

    }

    /** Returns the x coordinates of the snake's head */
    public int getHeadX() {
        return headX;
    }

    /** Returns the y coordinates of the snake's head */
    public int getHeadY() {
        return headY;
    }

    /** Returns the current direction the snake is facing */
    public String getDirection() {
        return direction;
    }

    /** Returns the x coordinates of the previous position of the snake's tail */
    public int getLastX() {
        return lastX;
    }

    /** Returns the x coordinates of the previous position of the snake's tail */
    public int getLastY() {
        return lastY;
    }

    /** Changes the current direction the snake is moving to up */
    public void up(){
        if (!direction.equals("down"))
        {
            direction = "up";
        }

    }

    /** Changes the current direction the snake is moving to down */
    public void down() {
        if (!direction.equals("up"))
        {
            direction = "down";
        }
    }

    /** Changes the current direction the snake is moving to left */
    public void left() {
        if (!direction.equals("right"))
        {
            direction = "left";
        }
    }

    /** Changes the current direction the snake is moving to right */
    public void right() {
        if (!direction.equals("left"))
        {
            direction = "right";
        }
    }

    /** Moves the snake forwards one spot in the current direction */
    public boolean move() {
        if (direction.equals("up")) {
            headX += 1;
            if (headX == 15) {
               return false;
            }
        }
        if(direction.equals("down")) {
            headX -= 1;
            if (headX == -1) {
                return false;
            }
            
        }
        if (direction.equals("left")) {
            headY -= 1;
            if (headY == -1) {
                return false;
            }
        }
        if (direction.equals("right")) {
            headY += 1;
            if (headY == 15) {
                return false;
            }
        }

        lastX = occupiedCoords.get(occupiedCoords.size() - 1)[0];
        lastY = occupiedCoords.get(occupiedCoords.size() - 1)[1];
        ArrayList<int[]> temp = (ArrayList<int[]>) occupiedCoords.clone();
        for (int n = 1; n < occupiedCoords.size(); n++) {
            occupiedCoords.set(n, new int[]{temp.get(n-1)[0], temp.get(n-1)[1]});
        }
        occupiedCoords.set(0, new int[]{headX, headY});
        if(checkCollision()) {
            return false;
        }
        return true;
    }

    /** Increases the length of the snake by one. The coordinates of the added spot of the snake is
     * the coordinates of the last spot the snake was in */
    public void grow() {
        occupiedCoords.add(new int[]{lastX,lastY});
    }

    /** Goes through the entire snake to see if there are any coordinate spots being occupied by the snake twice
     * meaning it's collided with itself. Returns true if there is a collision, false otherwise */
    public boolean checkCollision() {
        for (int i = 0; i < occupiedCoords.size(); i++) {
            for (int j = i + 1; j < occupiedCoords.size(); j++) {
                if (Arrays.equals(occupiedCoords.get(i), occupiedCoords.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Returns the coordinates of every spot occupied by the snake */
    public ArrayList<int[]> getOccupied() {
        return occupiedCoords;
    }

    /** Used to add coordinates to test collision */
    public void addOccupiedCoords(int x, int g){
            occupiedCoords.add(new int[]{x,g});
    }
}
