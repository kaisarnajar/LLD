package SnakeAndFood.MovementStrategy;

import SnakeAndFood.Pair;

public class HumanMovementStrategy implements MovementStrategy {
    @Override
    public Pair getNextPosition(Pair currentHead, String direction) {
        int row = currentHead.getRow();
        int col = currentHead.getCol();

        switch (direction) {
            case "U":
                return new Pair(row - 1, col);
            case "D":
                return new Pair(row + 1, col);
            case "R":
                return new Pair(row, col + 1);
            case "L":
                return new Pair(row, col - 1);
            default:
                return currentHead;
        }
    }
}
