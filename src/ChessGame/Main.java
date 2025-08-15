package ChessGame;

public class Main {
    public static void main(String[] args) {
        Player playerOne = new Player("Kaisar", true);
        Player playerTwo = new Player("Tahir", false);

        ChessGame chessGame = new ChessGame(playerOne, playerTwo);

        chessGame.start();
    }
}
