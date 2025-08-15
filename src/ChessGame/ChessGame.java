package ChessGame;

import ChessGame.Pieces.King;
import ChessGame.Pieces.Piece;

import java.util.ArrayList;
import java.util.Scanner;

public class ChessGame {
    private Board board;
    private Player playerOne;
    private Player playerTwo;
    boolean isWhiteTurn;
    private ArrayList<Move> gameLog;
    private STATUS status;

    public ChessGame(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.board = Board.getInstance(8);
        this.isWhiteTurn = true;
        this.status = STATUS.ACTIVE;
        this.gameLog = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (this.status == STATUS.ACTIVE) {
            Player currentPlayer = isWhiteTurn ? playerOne : playerTwo;

            System.out.println(currentPlayer.getName() + "'s turn ");

            //Ask for source coordinates
            System.out.println("Enter the source coordinates");

            int startRow = scanner.nextInt();
            int startCol = scanner.nextInt();

            //Ask for destination coordinates

            int destRow = scanner.nextInt();
            int destCol = scanner.nextInt();

            Cell startCell = board.getCell(startRow, startCol);
            Cell destCell = board.getCell(destRow, destCol);

            if (startCell == null || destCell == null) {
                System.out.println("Invalid Move : Try again");
                continue;
            }

            makeMove(new Move(startCell, destCell), currentPlayer);
        }

        System.out.println("Game Over Status : " + this.status);
    }

    //make a move in the game

    public void makeMove(Move move, Player player) {

        if (move.isValid()) {
            Piece sourcePiece = move.getStartCell().getPiece();

            if (sourcePiece.canMove(this.board, move.getStartCell(), move.getEndCell())) {
                Piece destinationPiece = move.getEndCell().getPiece();

                if (destinationPiece != null) {
                    if (destinationPiece instanceof King && isWhiteTurn) {
                        this.status = STATUS.WHITE_WIN;
                        return;
                    }
                    // If the destination cell contains King and currently Black is
                    // playing --> Black wins
                    if (destinationPiece instanceof King && !isWhiteTurn) {
                        this.status = STATUS.BLACK_WIN;
                        return;
                    }

                    destinationPiece.setKilled(true);
                }

                gameLog.add(move);
                move.getEndCell().setPiece(sourcePiece);
                move.getStartCell().setPiece(null);
                this.isWhiteTurn = !isWhiteTurn;
                System.out.println("isWhiteTurn : isWhiteTurn");
            }
        }

    }
}

