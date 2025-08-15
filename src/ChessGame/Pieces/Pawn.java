package ChessGame.Pieces;

import ChessGame.Board;
import ChessGame.Cell;
import ChessGame.MovementStrategy.PawnMovementStrategy;

public class Pawn extends Piece {
    public Pawn(boolean isWhitePiece) {
        super(isWhitePiece, new PawnMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}
