package ChessGame.Pieces;

import ChessGame.Board;
import ChessGame.Cell;
import ChessGame.MovementStrategy.BishopMovementStrategy;

public class Bishop extends Piece {
    public Bishop(boolean isWhitePiece) {
        super(isWhitePiece, new BishopMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}