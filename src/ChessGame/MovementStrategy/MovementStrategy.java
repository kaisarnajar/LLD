package ChessGame.MovementStrategy;

import ChessGame.Board;
import ChessGame.Cell;

public interface MovementStrategy {
    boolean canMove(Board board, Cell startCell, Cell endCell);
}
