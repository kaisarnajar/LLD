package ChessGame.MovementStrategy;

import ChessGame.Board;
import ChessGame.Cell;

public class KingMovementStrategy implements MovementStrategy {
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return false;
    }
}
