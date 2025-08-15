package ChessGame.Pieces;

import ChessGame.Board;
import ChessGame.Cell;
import ChessGame.MovementStrategy.RookMovementStrategy;

public class Rook extends Piece {

    public Rook(boolean isWhitePiece) {
        super(isWhitePiece, new RookMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}