package ChessGame;

public class Move {
    private Cell startCell;
    private Cell endCell;

    public Move(Cell startCell, Cell endCell) {
        this.startCell = startCell;
        this.endCell = endCell;
    }

    public boolean isValid() {
        if (endCell.getPiece() == null) return true;

        boolean isSame = startCell.getPiece().isWhite() == endCell.getPiece().isWhite();

        return !isSame;
    }

    public Cell getStartCell() {
        return startCell;
    }

    public Cell getEndCell() {
        return endCell;
    }
}
