package ChessGame.MovementStrategy;

import ChessGame.Board;
import ChessGame.Cell;

public class RookMovementStrategy implements MovementStrategy {
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return false;
    }
}
