package SnakeAndFood;

import SnakeAndFood.MovementStrategy.MovementStrategy;

public class SnakeGame {
    private GameBoard gameBoard;
    private int[][] food;
    private int foodIndex;
    private MovementStrategy movementStrategy;
    private Snake snake;

    public SnakeGame(int width, int height, int[][] food, MovementStrategy movementStrategy) {
        this.gameBoard = GameBoard.getInstance(height, width);
        this.food = food;
        this.foodIndex = 0;
        this.snake = new Snake(0, 0);
        this.movementStrategy = movementStrategy;
    }

    public int move(String direction) {
        Pair currentHead = snake.getHead();
        Pair newHead = movementStrategy.getNextPosition(currentHead, direction);
        int newHeadRow = newHead.getRow();
        int newHeadCol = newHead.getCol();

        //check boundary conditions
        boolean crossesBoundary = newHeadRow < 0 || newHeadRow >= gameBoard.getHeight() ||
                newHeadCol < 0 || newHeadCol >= gameBoard.getWidth();

        Pair currentTail = snake.getTail();

        boolean bitesItself = snake.containsKey(newHead) && !(newHead.getRow() == currentTail.getRow() &&
                newHead.getCol() == currentTail.getCol());

        if (crossesBoundary || bitesItself) {
            return -1;
        }

        boolean ateFood = (this.foodIndex < this.food.length) &&
                (this.food[this.foodIndex][0] == newHeadRow) &&
                (this.food[this.foodIndex][1] == newHeadCol);

        if (ateFood) {
            // Increment food index to move to next food
            this.foodIndex++;
        } else {
            // If no food eaten, remove tail
            snake.pollLast();
            snake.removeTail(currentTail);
        }

        snake.addFirst(newHead);
        // Calculate ans return score
        return snake.getSize() - 1;
    }

    Snake getSnake() {
        return snake;
    }
}
