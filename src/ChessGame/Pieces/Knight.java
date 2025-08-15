package ChessGame.Pieces;

import ChessGame.Board;
import ChessGame.Cell;
import ChessGame.MovementStrategy.KingMovementStrategy;

public class Knight extends Piece {
    public Knight(boolean isWhitePiece) {
        super(isWhitePiece, new KingMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}