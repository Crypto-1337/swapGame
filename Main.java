import swapGame.PuzzleBoard;
import swapGame.Player;

/**
 * Die Main-Klasse steuert den Ablauf des Spiels.
 */
public class Main {
    public void start() {
        Player player = new Player(); // Spieler für Benutzereingaben
        boolean playAgain;

        do {
            // Neues Puzzle-Spielbrett erstellen
            PuzzleBoard board = new PuzzleBoard(3);
            board.mixBoard(60); // Spielfeld mischen
            int moves = 0; // Zähler für die Spielzüge

            System.out.println("Das Spiel beginnt!");
            while (!board.isSolved()) {
                board.printBoard(); // Spielfeld ausgeben
                int number = player.getMove(); // Zahl vom Spieler abfragen
                if (board.move(number)) { // Zahl bewegen
                    moves++;
                }
            }

            System.out.println("Herzlichen Glückwunsch! Sie haben das Puzzle gelöst.");
			board.printBoard();
            System.out.println("Anzahl der Züge: " + moves);
            playAgain = player.playAgain(); // Nach erneutem Spiel fragen
        } while (playAgain);

        System.out.println("Danke fürs Spielen!");
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
