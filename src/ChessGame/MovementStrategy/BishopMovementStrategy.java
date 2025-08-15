package ChessGame.MovementStrategy;

import ChessGame.Board;
import ChessGame.Cell;

public class BishopMovementStrategy implements MovementStrategy {
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return true;
    }
}
