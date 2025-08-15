import java.util.*;

// ===================== Pair Class =====================
class Pair<T, U> {
    private final T first;
    private final U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() { return first; }
    public U getSecond() { return second; }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) return false;
        Pair<?, ?> other = (Pair<?, ?>) obj;
        return Objects.equals(first, other.first) && Objects.equals(second, other.second);
    }
}

// ===================== PieceType Enum =====================
enum PieceType {
    X, O
}

// ===================== PlayingPiece =====================
class PlayingPiece {
    public PieceType pieceType;
    PlayingPiece(PieceType pieceType) {
        this.pieceType = pieceType;
    }
}

// ===================== Player =====================
class Player {
    public String name;
    public PlayingPiece playingPiece;
    public MoveStrategy moveStrategy; // Strategy Pattern

    public Player(String name, PlayingPiece playingPiece, MoveStrategy moveStrategy) {
        this.name = name;
        this.playingPiece = playingPiece;
        this.moveStrategy = moveStrategy;
    }

    public String getName() { return name; }
    public PlayingPiece getPlayingPiece() { return playingPiece; }
    public MoveStrategy getMoveStrategy() { return moveStrategy; }
}

// ===================== Board =====================
class Board {
    public int size;
    public PlayingPiece[][] board;

    public Board(int size) {
        this.size = size;
        board = new PlayingPiece[size][size];
    }

    public boolean addPiece(int row, int column, PlayingPiece playingPiece) {
        if (board[row][column] != null) {
            return false;
        }
        board[row][column] = playingPiece;
        return true;
    }

    List<Pair<Integer, Integer>> getFreeCells() {
        List<Pair<Integer, Integer>> freeCells = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    freeCells.add(new Pair<>(i, j));
                }
            }
        }
        return freeCells;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    System.out.print("_ ");
                } else {
                    System.out.print(board[i][j].pieceType + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

// ===================== Strategy Interface =====================
interface MoveStrategy {
    Pair<Integer, Integer> getMove(Board board, Player player);
}

// ===================== HumanMoveStrategy =====================
class HumanMoveStrategy implements MoveStrategy {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public Pair<Integer, Integer> getMove(Board board, Player player) {
        System.out.println(player.getName() + " (" + player.getPlayingPiece().pieceType + ") - Enter your move (row col): ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        return new Pair<>(row, col);
    }
}

// ===================== AIMoveStrategy =====================
class AIMoveStrategy implements MoveStrategy {
    private Random random = new Random();

    @Override
    public Pair<Integer, Integer> getMove(Board board, Player player) {
        List<Pair<Integer, Integer>> freeCells = board.getFreeCells();
        return freeCells.get(random.nextInt(freeCells.size()));
    }
}

// ===================== TicTacToeGame =====================
class Game {
    Deque<Player> players;
    Board gameBoard;
    private String winner = "";

    public void initializeGame() {
        players = new LinkedList<>();

        // Player 1: Human
        Player playerOne = new Player("Player1", new PlayingPiece(PieceType.X), new HumanMoveStrategy());

        // Player 2: AI
        Player playerTwo = new Player("AI_Player", new PlayingPiece(PieceType.O), new AIMoveStrategy());

        players.add(playerOne);
        players.add(playerTwo);

        gameBoard = new Board(3);
    }

    public void startGame() {
        boolean winnerFound = false;
        while (!winnerFound) {
            Player playerTurn = players.removeFirst();
            gameBoard.printBoard();

            List<Pair<Integer, Integer>> freeCells = gameBoard.getFreeCells();
            if (freeCells.isEmpty()) break;

            Pair<Integer, Integer> nextMove = playerTurn.getMoveStrategy().getMove(gameBoard, playerTurn);
            boolean pieceAdded = gameBoard.addPiece(nextMove.getFirst(), nextMove.getSecond(), playerTurn.playingPiece);

            if (!pieceAdded) {
                System.out.println("Invalid move! Try again.");
                players.addFirst(playerTurn);
                continue;
            }

            players.addLast(playerTurn);

            if (isWinner(nextMove.getFirst(), nextMove.getSecond(), playerTurn.playingPiece.pieceType)) {
                gameBoard.printBoard();
                winner = playerTurn.getName();
                winnerFound = true;
            }
        }
    }

    public boolean isWinner(int row, int column, PieceType pieceType) {
        boolean rowMatch = true, columnMatch = true, diagonalMatch = true, antiDiagonalMatch = true;

        for (int i = 0; i < gameBoard.size; i++) {
            if (gameBoard.board[row][i] == null || gameBoard.board[row][i].pieceType != pieceType) {
                rowMatch = false;
            }
            if (gameBoard.board[i][column] == null || gameBoard.board[i][column].pieceType != pieceType) {
                columnMatch = false;
            }
            if (gameBoard.board[i][i] == null || gameBoard.board[i][i].pieceType != pieceType) {
                diagonalMatch = false;
            }
            if (gameBoard.board[i][gameBoard.size - i - 1] == null || gameBoard.board[i][gameBoard.size - i - 1].pieceType != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }

    public String getWinner() { return winner; }
}

// ===================== Main =====================
public class TicTacToeGame {
    public static void main(String[] args) {
        Game game = new Game();
        game.initializeGame();
        game.startGame();

        String winner = game.getWinner();
        if (winner.isEmpty()) {
            System.out.println("It's a draw!");
        } else {
            System.out.println("Winner is: " + winner);
        }
    }
}
