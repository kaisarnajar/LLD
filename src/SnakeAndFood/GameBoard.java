package SnakeAndFood;

public class GameBoard {
    private static GameBoard instance;
    private int width;
    private int height;

    private GameBoard(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public static GameBoard getInstance(int height, int width) {
        if (instance == null) {
            instance = new GameBoard(height, width);
        }

        return instance;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
