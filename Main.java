import java.util.Scanner;
import swapGame.PuzzleBoard;
import swapGame.Player;

/**
 * Die Main-Klasse steuert den Ablauf des Spiels.
 */
public class Main {

    public void start() {
        Scanner scanner = new Scanner(System.in); // Scanner für Benutzereingaben
        Player player = new Player(); // Spieler für Eingaben
        boolean playAgain;

        do {
            // Spielbrettgröße vom Spieler abfragen
            int size;
            while (true) {
                System.out.print("Bitte geben Sie die Größe des Spielbretts ein (z. B. 3 für 3x3): ");
                try {
                    size = Integer.parseInt(scanner.nextLine());
                    if (size >= 2) {
                        break; // Gültige Größe eingegeben
                    } else {
                        System.out.println("Die Größe muss mindestens 2 sein.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ungültige Eingabe. Bitte eine Zahl eingeben.");
                }
            }

            // Neues Puzzle-Spielbrett erstellen
            PuzzleBoard board = new PuzzleBoard(size);
            board.mixBoard(size * size * 10); // Spielfeld mischen, basierend auf Größe
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
            System.out.println("Anzahl der Züge: " + moves);
            playAgain = player.playAgain(); // Nach erneutem Spiel fragen
        } while (playAgain);

        System.out.println("Danke fürs Spielen!");
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
