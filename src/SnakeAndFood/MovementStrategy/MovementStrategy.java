package SnakeAndFood.MovementStrategy;

import SnakeAndFood.Pair;

public interface MovementStrategy {
    Pair getNextPosition(Pair currentHead, String direction);
}
