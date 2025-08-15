package ChessGame.Pieces;

import ChessGame.Board;
import ChessGame.Cell;
import ChessGame.MovementStrategy.QueenMovementStrategy;

public class Queen extends Piece {
    public Queen(boolean isWhitePiece) {
        super(isWhitePiece, new QueenMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}